package controlador;
import vista.AgregarEstudianteForm; 
import dao.EstudianteDAO; 
import java.awt.event.ActionEvent; 
import logicadenegocios.Estudiante; 
import java.awt.event.ActionListener; 
import java.sql.SQLException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.swing.JOptionPane;

/**
 *
 * @author María Paula
 */
public class ControladorEstudiante implements ActionListener {
    
  public AgregarEstudianteForm vista;
  public EstudianteDAO dao;
  public Estudiante logicadenegocios;
  
  
   /**
   * Constructor
   * @param pVista
   * @param pModelo 
   */
  public ControladorEstudiante(AgregarEstudianteForm pVista, Estudiante pModelo) {
    vista = pVista;
    logicadenegocios = pModelo;
    dao = new EstudianteDAO();
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
        try {
          agregarEstudiante();
          break;
        } catch (SQLException ex) {
          Logger.getLogger(ControladorEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        break;
      case "Menu":
       volverMenu();
        break;
      default:
        break;
    }
  }
  
  /**
   * Metodo para agregar estudiante a la base de datos
   * @throws SQLException 
   */
  public void agregarEstudiante() throws SQLException{
    if (vista.EstudianteDatosCorrectos() == true) { 
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
      }
    } else {
      JOptionPane.showMessageDialog(vista, "Todos lo datos son requeridos"); 
    }
  }
  

  /**
   * Metodo que regresa a la ventana menu
   */
  public void volverMenu(){
    vista.volverMenu();
  }
    
}
