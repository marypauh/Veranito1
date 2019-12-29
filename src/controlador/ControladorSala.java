
package controlador;

import dao.SalaDAO;
import dao.HorarioDAO;
import dao.RecursoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicadenegocios.Horario;
import logicadenegocios.Recurso;
import logicadenegocios.Sala;
import vista.RegistrarSalaForm;
import vista.SeleccionarHorarioForm;
import vista.AgregarRecursosSalaForm;

/**
 *
 * @author Raquel Rojas
 */
public class ControladorSala implements ActionListener{
  public SalaDAO dao;
  public HorarioDAO horario;
  public RecursoDAO recurso;
  public Sala modelo;
  public RegistrarSalaForm vista;
  public SeleccionarHorarioForm vistaHorario;
  public AgregarRecursosSalaForm vistaRecurso;
          
  public ControladorSala(RegistrarSalaForm pVista , Sala pModelo,SeleccionarHorarioForm pVistaH,AgregarRecursosSalaForm pVistaR  ){
    vista = pVista;
    vistaHorario = pVistaH;
    vistaRecurso = pVistaR;
    modelo = pModelo;
    dao = new SalaDAO();
    horario = new HorarioDAO();
    recurso = new RecursoDAO();
//Encargado de administrar la base de datos
  
    this.vista.btnRegreso.addActionListener(this);
    this.vista.btnContinuar.addActionListener(this);
    this.vistaHorario.btnSelect.addActionListener(this);
    this.vistaRecurso.btnAgregar.addActionListener(this);
    this.vistaRecurso.btnListo.addActionListener(this);
  }
  
    /**
   * Metodo para determinar accion realizada por el usuario en vista
   * 
   * @param e accion realizada
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()){
      case "Continuar":
        getInfoSala();

        break;
      case "Regresar":
        break;
      case "Seleccionar Horario":
        setHorarioSala();
        break;
      case "Agregar recurso":
        setRecursosSala();
        break;
      case "Listo":
        registrarSala();
        break;
      default:
        break;
    }
  }
  
    /**
   * Metodo para registrar una sala
   */
public void getInfoSala(){
  if(vista.datosCorrectos() == true){
    int capacidad = Integer.parseInt(vista.txtCapacidad.getText());
    String ubicacion = vista.txtUbicacion.getText();
    String identificador = vista.txtId.getText();
    modelo = new Sala(identificador, ubicacion, capacidad);
    vista.setVisible(false);
    vistaHorario.setVisible(true);
    ResultSet rs;
    rs = horario.getHorarios();
    if (rs == null){
      JOptionPane.showMessageDialog(vista, "Error al cargar horarios");
    }else{
      DefaultTableModel table = new DefaultTableModel();
      vistaHorario.horariosTable.setModel(table);
      table.setColumnIdentifiers(new Object[]{"ID","Hora de Inicio", "Hora Fin ", "Dias"});
      try {
        while(rs.next()){
          table.addRow(new Object[]{rs.getInt("idHorario"), rs.getString("horaInicio"), rs.getString("horaFin"),rs.getString("dias")});
        }
        } catch (SQLException ex) {
          Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      } else {
        JOptionPane.showMessageDialog(null, "Error al ingresar los datos, compruebe los datos "
            + "ingresados");
      }
  } 

public void setHorarioSala(){
  int idHorario = vistaHorario.horariosTable.getSelectedRow() + 1;
  ResultSet rs = horario.getHorarioID(idHorario);
  try{
  rs.next();
  String horaInicio = rs.getString("horaInicio");
  String horaFin = rs.getString("horaFin");
  String dias = rs.getString("dias");
  Horario horarioSala = new Horario(idHorario, horaInicio, horaFin, dias);
  modelo.setHorario(horarioSala);
  vistaHorario.setVisible(false);
  vistaRecurso.setVisible(true);
  
  ResultSet recursos = recurso.getRecursos();
  if (recursos == null){
    JOptionPane.showMessageDialog(vista, "Error al cargar recursos");
  }else{
    DefaultTableModel table = new DefaultTableModel();
    vistaRecurso.recursosTable.setModel(table);
    table.setColumnIdentifiers(new Object[]{"nombre","detalle"});
    try {
      while(recursos.next()){
        table.addRow(new Object[]{recursos.getString("nombre"), recursos.getString("detalle")});
      }
    } catch (SQLException ex) {
        Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  }catch(SQLException ex) {
    Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
  }
}


public void setRecursosSala(){
  int row = vistaRecurso.recursosTable.getSelectedRow();
  String nombre = vistaRecurso.recursosTable.getValueAt(row, 0).toString();
  
  String detalle = vistaRecurso.recursosTable.getValueAt(row, 1).toString();
  Recurso recurso = new Recurso(nombre, detalle);
  if (modelo.addRecurso(recurso)){
    JOptionPane.showMessageDialog(vista, "Se ha agregado exitosamente el recurso: " + nombre);
  }else{
    JOptionPane.showMessageDialog(vista, "error");
  }
}

public void registrarSala(){
  boolean bandera = dao.registrarSala(modelo);
  if(bandera == true){
    JOptionPane.showMessageDialog(vista, "Sala ingresada correctamente");
    }else{
      JOptionPane.showMessageDialog(vista, "Error al ingresar la sala");
    }
}

}
