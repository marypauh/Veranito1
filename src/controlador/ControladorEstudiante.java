package controlador;
import conexion.Conexion;
import vista.AgregarEstudianteForm; 
import dao.EstudianteDAO; 
import java.awt.event.ActionEvent; 
import logicadenegocios.Estudiante; 
import java.awt.event.ActionListener; 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.swing.JOptionPane;
import vista.ConsultarEstudianteForm;

/**
 *
 * @author María Paula
 */
public class ControladorEstudiante implements ActionListener {
    
  public AgregarEstudianteForm vista;
  public EstudianteDAO dao;
  public Estudiante logicadenegocios;
   public ConsultarEstudianteForm vistaConsulta;
  
  
   /**
   * Constructor
   * @param pVista
   * @param pModelo 
   */
  public ControladorEstudiante(AgregarEstudianteForm pVista, Estudiante pModelo, ConsultarEstudianteForm pVistaConsulta) {
    vista = pVista;
    logicadenegocios = pModelo;
    dao = new EstudianteDAO();
    vistaConsulta = pVistaConsulta;
    this.vistaConsulta.btnConsultar.addActionListener(this);
    this.vistaConsulta.btnReservas.addActionListener(this);
    this.vista.btnAgregar.addActionListener(this);
    this.vista.btnMenu.addActionListener(this);
  }

   /**
   * Metodo que recibe y ejecuta una accion
   * @param e 
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()) {
      case "Agregar": 
        agregarEstudiante();
        break;
      case "Menu":
        volverMenu();
        break;
      case "Consultar":
        getInfoEstudiante();
        break;
      case "Volver":
        this.vistaConsulta.volverMenu();
        break;
      default:
        break;
    }
  }
  
  /**
   * Metodo para agregar estudiante a la base de datos
   * @throws SQLException 
   */
  public void agregarEstudiante() {
    if (vista.EstudianteDatosCorrectos() == true) { 
        try {
            int carnet = Integer.parseInt(vista.txtCarnet.getText());
            String nombreCompleto = vista.txtNombre.getText();
            String carrera = vista.txtCarrera.getText();
            String email = vista.txtEmail.getText();
            String telefono = vista.txtTelefono.getText();
            int calificacion = Integer.parseInt(vista.txtCalificacion.getText());
            logicadenegocios = new Estudiante(carnet,nombreCompleto,carrera,email,calificacion,telefono);
            Estudiante estudianteActual = dao.agregarEstudiante(logicadenegocios);
            if (estudianteActual != null) {
                vista.setVisible(false);
                JOptionPane.showMessageDialog(vista, "Se agregó el estudiante exitosamente: ");
                vista.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vista, "No es posible agregar el estudiante");
            } } catch (SQLException ex) {
            Logger.getLogger(ControladorEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
      JOptionPane.showMessageDialog(vista, "Todos lo datos son requeridos"); 
    }
  }
  
  public void getInfoEstudiante(){
   try{
     int carnetEE = Integer.parseInt(vistaConsulta.txtCarnet.getText()); 
     Connection conexion = Conexion.getConexion(); 
     String query = "{call dbo.consultarEstudiante(?,?,?,?,?,?)}";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,carnetEE);
      consulta.registerOutParameter(2, java.sql.Types.VARCHAR);
      consulta.registerOutParameter(3, java.sql.Types.VARCHAR);
      consulta.registerOutParameter(4, java.sql.Types.VARCHAR);
      consulta.registerOutParameter(5, java.sql.Types.INTEGER);
      consulta.registerOutParameter(6, java.sql.Types.VARCHAR);
      consulta.execute();
      
      vistaConsulta.txtNombre.setText(consulta.getString(2));
      vistaConsulta.txtCarrera.setText(consulta.getString(3));
      vistaConsulta.txtEmail.setText(consulta.getString(4));
      vistaConsulta.txtCalificacion.setText(Integer.toString(consulta.getInt(5)));
      vistaConsulta.txtTelefono.setText(consulta.getString(6));
    } catch(SQLException e){
      System.out.println("Error: " + e.getMessage());
    }
  }
  

  /**
   * Metodo que regresa a la ventana menu
   */
  public void volverMenu(){
    vista.volverMenu();
    vistaConsulta.volverMenu();
  }
    
}
