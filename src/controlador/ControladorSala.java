
package controlador;

import dao.SalaDAO;
import dao.HorarioDAO;
import dao.RecursoDAO;
import dao.HorarioExcepcionDAO;
import dao.ReservaDAO;
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
import vista.CalificarSala1Form;
import vista.CalificarSala2Form;
import vista.ConsultaSalaForm;
import vista.ModificarSalaForm;
import vista.MostrarSalaForm;
import vista.MostrarRecursosDispForm;

/**
 *
 * @author Raquel Rojas
 */
public class ControladorSala implements ActionListener{
  public SalaDAO dao;
  public HorarioDAO horario;
  public HorarioExcepcionDAO horarioExc;
  public RecursoDAO recurso;
  public ReservaDAO reserva;
  public Sala modelo;
  public RegistrarSalaForm vista;
  public SeleccionarHorarioForm vistaHorario;
  public AgregarRecursosSalaForm vistaRecurso;
  public MostrarSalaForm vistaMostrarSala = new MostrarSalaForm();
  public ModificarSalaForm vistaModSala = new ModificarSalaForm();
  public MostrarRecursosDispForm vistaRecursosDisp = new MostrarRecursosDispForm();
  public ConsultaSalaForm vistaConsulta = new ConsultaSalaForm();
  public CalificarSala1Form vistaCali = new CalificarSala1Form();
  public CalificarSala2Form vistaCali2 = new CalificarSala2Form();
          
  public ControladorSala(RegistrarSalaForm pVista , Sala pModelo,SeleccionarHorarioForm pVistaH,AgregarRecursosSalaForm pVistaR  ){
    vista = pVista;
    vistaHorario = pVistaH;
    vistaRecurso = pVistaR;
    modelo = pModelo;
    dao = new SalaDAO();
    horario = new HorarioDAO();
    recurso = new RecursoDAO();
    horarioExc = new HorarioExcepcionDAO();
    reserva = new ReservaDAO();
//Encargado de administrar la base de datos
  
    this.vista.btnRegreso.addActionListener(this);
    this.vista.btnContinuar.addActionListener(this);
    this.vistaHorario.btnSelect.addActionListener(this);
    this.vistaRecurso.btnAgregar.addActionListener(this);
    this.vistaRecurso.btnListo.addActionListener(this);
    this.vistaMostrarSala.btnGuardar.addActionListener(this);
    this.vistaModSala.btnBuscar.addActionListener(this);
    this.vistaMostrarSala.btnAgregarR.addActionListener(this);
    this.vistaMostrarSala.btnEliminar.addActionListener(this);
    this.vistaRecursosDisp.btnCerrar.addActionListener(this);
    this.vistaRecursosDisp.btnAgregarNuevoR.addActionListener(this);
    this.vistaConsulta.btnBuscar.addActionListener(this);
    this.vistaConsulta.btnCerrar.addActionListener(this);
    this.vistaCali.btnCalificar.addActionListener(this);
    this.vistaCali2.btnCalificarSala.addActionListener(this);
    this.vistaCali.btnMenu.addActionListener(this);

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
        this.vista.setVisible(false);
        break;
      case "Seleccionar Horario":
        setHorarioSala();
        break;
      case "Agregar recurso":
        setRecursosSala();
        break;
      case "Listo":
        registrarSala();
      case "Buscar":
        getIDMod();
        break;
      case "Agregar Nuevo Recurso":
        nuevoRecurso();
        break;
      case "Eliminar Recurso":
        eliminarRecurso();
        break;
      case "Agregar a Sala":
       agregarRecurso();
        break;
      case "Guardar Cambios":
        guardarCambios();
        break;
      case "Consultar":
        consultarSala();
        break;
      case "Calificar":
    {
        try {
            validarCodigo();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      case "Calificar Sala":
    {
        try {
            calificarSala();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      case "Cerrar":
        vistaRecursosDisp.setVisible(false);
        vistaConsulta.setVisible(false);
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
    System.out.print(dao.getSala(identificador));
    if( dao.getSala(identificador) != null){
      JOptionPane.showMessageDialog(vista, "Ya existe la sala " + identificador);
    }else{
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
    }
  }else {
    JOptionPane.showMessageDialog(null, "Error al ingresar los datos, compruebe los datos ingresados");}
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


public void getIDMod(){
  try {
    String id = vistaModSala.txtID.getText();
    ResultSet rs = dao.getSala(id);
    System.out.println(rs.next());
    if ( "".equals(id) || rs == null ){
      JOptionPane.showMessageDialog(vista, "No existe una sala con ese identificador");
    }else{
      try{
        String ubicacion = rs.getString("ubicacion");
        vistaMostrarSala.lblID.setText(id);
        vistaMostrarSala.txtUbicacion.setText(ubicacion);
        modelo.setIdentificador(id);
        
        
        ResultSet recursos = dao.getRecursosSala(id);
        if (recursos == null){
          JOptionPane.showMessageDialog(vista, "Error al cargar recursos");
        }else{
          updateTabla(recursos);
          vistaModSala.setVisible(false);
          vistaMostrarSala.setVisible(true);
          }
        }catch(SQLException ex) {
          Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
  } catch (SQLException ex) {
    Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
  }
}


  public void nuevoRecurso(){
    ResultSet recursos = dao.recursosNotSala(modelo.getIdentificador());
    vistaRecursosDisp.setVisible(true);
    if (recursos == null){
      JOptionPane.showMessageDialog(vista, "Error al cargar recursos");
    }else{
    DefaultTableModel table = new DefaultTableModel();
    vistaRecursosDisp.recursosNuevos.setModel(table);
    table.setColumnIdentifiers(new Object[]{"nombre","detalle"});
    try {
      while(recursos.next()){
        table.addRow(new Object[]{recursos.getString("nombre"), recursos.getString("detalle")});    
      }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(vistaRecursosDisp, "No hay mas recursos que mostrar");
        Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}


public void eliminarRecurso(){
  int row = vistaMostrarSala.recursosSalaTable.getSelectedRow();
  String nombre = vistaMostrarSala.recursosSalaTable.getValueAt(row, 0).toString();
  if( dao.deleteRecurso(modelo.getIdentificador(), nombre) == true){
     JOptionPane.showMessageDialog(vistaMostrarSala, "Se ha eliminado el recurso: "+ nombre +"exitosamente");
     ResultSet rs = dao.getRecursosSala(modelo.getIdentificador());
     updateTabla(rs);
    }else{
    JOptionPane.showMessageDialog(vistaMostrarSala, "Ha ocurrido un error");
  }
}

  public void guardarCambios(){
    modelo.setEstado(vistaMostrarSala.estadoCB.getSelectedItem().toString());
    modelo.setUbicacion(vistaMostrarSala.txtUbicacion.getText());
    if (dao.updateSala(modelo) == true){
      JOptionPane.showMessageDialog(vistaMostrarSala, "Se han guardado los cambios");
    }else{
      JOptionPane.showMessageDialog(vistaMostrarSala, "Ha ocurrido un error");
    } vistaMostrarSala.setVisible(false);
  }

private void updateTabla(ResultSet recursos){
  if (recursos == null){
    JOptionPane.showMessageDialog(vistaMostrarSala, "No existen recursos ");
  }
  DefaultTableModel table = new DefaultTableModel();
  vistaMostrarSala.recursosSalaTable.setModel(table);
  table.setColumnIdentifiers(new Object[]{"nombre","detalle"});
  try {
    while(recursos.next()){
      table.addRow(new Object[]{recursos.getString("nombreRecurso"), recursos.getString("detalle")});
    }
  } catch (SQLException ex) {
    Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
  }
}


public void agregarRecurso(){
  int row = vistaRecursosDisp.recursosNuevos.getSelectedRow();
  String nombre = vistaRecursosDisp.recursosNuevos.getValueAt(row, 0).toString();
  String detalle = vistaRecursosDisp.recursosNuevos.getValueAt(row, 1).toString();
  Recurso recurso = new Recurso(nombre, detalle);
  if (dao.agregarRecurso(modelo.getIdentificador(), recurso) == true){
    JOptionPane.showMessageDialog(vista, "Se ha agregado exitosamente el recurso: " + nombre);
    updateTabla(dao.getRecursosSala(modelo.getIdentificador()));
  }else{
    JOptionPane.showMessageDialog(vista, "error");
  }
 }


public void consultarSala(){
  try {
    String id = vistaConsulta.txtID.getText();
    ResultSet rs = dao.getSala(id);
    modelo.setIdentificador(id);
    rs.next();
    if ( "".equals(id) || rs == null ){
      JOptionPane.showMessageDialog(vista, "No existe una sala con ese identificador");
    }else{
      try{
        String ubicacion = rs.getString("ubicacion");
        int capacidad = rs.getInt("capacidadMax");
        int calificacion = rs.getInt("calificacion");
        String estado = rs.getString("estado");
        vistaConsulta.txtUbicacion.setText(ubicacion);
        vistaConsulta.txtEstado.setText(estado);
        vistaConsulta.txtCalificacion.setText(Integer.toString(calificacion));
        vistaConsulta.txtCapacidad.setText(Integer.toString(capacidad));
        modelo.setIdentificador(id);
        ResultSet recursos = dao.getRecursosSala(id);
        if (recursos == null){
          JOptionPane.showMessageDialog(vistaConsulta, "Error al cargar recursos");
        }else{
          DefaultTableModel table = new DefaultTableModel();
          vistaConsulta.recursosTable.setModel(table);
          table.setColumnIdentifiers(new Object[]{"Nombre Recurso ","Detalle"});
          try {
            while(recursos.next()){
            table.addRow(new Object[]{recursos.getString("nombreRecurso"), recursos.getString("detalle")});    
            }
          mostrarReservas();
          if (mostrarHorario() == false){
            JOptionPane.showMessageDialog(vistaConsulta, "Error al cargar horario");
 
          }
          } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        
        }catch(SQLException ex) {
          Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
  } catch (SQLException ex) {
    Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
  }
}

  public boolean mostrarHorario(){
    boolean res = false;
    ResultSet horario = this.horario.getHorarioSala(modelo.getIdentificador());
    if (horario == null){
      JOptionPane.showMessageDialog(vista, "Error al cargar horario");
    }else{
      try {
        horario.next();
        String horaInicio = horario.getString("horaInicio");
        String horaFin = horario.getString("horaFin");
        String dias = horario.getString("dias");
        vistaConsulta.txtDias.setText(dias);
        vistaConsulta.txtHoraF.setText(horaFin);
        vistaConsulta.txtHoraI.setText(horaInicio);
      } catch (SQLException ex) {
        Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
      }
      ResultSet horariosExp = horarioExc.getHorarios();
      if (horariosExp == null){
        JOptionPane.showMessageDialog(vistaConsulta, "Error al mostrar horarios ");
      }
      DefaultTableModel table = new DefaultTableModel();
      vistaConsulta.horarioTable.setModel(table);
      table.setColumnIdentifiers(new Object[]{"Hora de Inicio ","Hora de Cierre ", "Fecha", "Descripción"});
      try {
        while(horariosExp.next()){
        table.addRow(new Object[]{horariosExp.getString("horaInicio"), horariosExp.getString("horaFin"), horariosExp.getDate("fecha"), horariosExp.getString("descripcion")});
        }
      res = true;  
      } catch (SQLException ex) {
        Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
     } 
    }
    return res;
  }
  
  private void mostrarReservas() throws SQLException{
    ResultSet reservas = reserva.getProxReservasSala(modelo.getIdentificador());
  if (reservas == null){
    JOptionPane.showMessageDialog(vistaConsulta, "La sala no tiene reserva para los proximos 7 dias ");
  }
  DefaultTableModel table = new DefaultTableModel();
  vistaConsulta.reservasTable.setModel(table);
  table.setColumnIdentifiers(new Object[]{"Numero Reserva ", "Estado", "Fecha", "Desde ", "Haste ", "Asunto", "Carnet del Organizador"});
  try {
    while(reservas.next()){
      table.addRow(new Object[]{reservas.getInt("numero"), reservas.getString("estado"),reservas.getDate("fecha"), 
          reservas.getString("horaInicio"), reservas.getString("horaFin"),reservas.getString("asunto"), reservas.getInt("organizador")});
    }
  } catch (SQLException ex) {
    Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
  }
}
  


  private void validarCodigo () throws SQLException {
    String codigo = vistaCali.txtCodigo.getText();
    ResultSet sala = reserva.verificarCodigo(codigo);
    System.out.print(sala);
    if (sala == null){
      JOptionPane.showMessageDialog(vistaCali, " El código es inválido o ya fue utilizado");
    } else{
      if(sala.next()){
      String idSala = sala.getString("idSala");
      int idReserva = sala.getInt("numero");
      boolean usarCodigo = reserva.usarCodigo(idReserva);
      if (usarCodigo == true){
        modelo.setIdentificador(idSala);
        vistaCali2.txtIdSala.setText(modelo.getIdentificador());
        vistaCali.setVisible(false);
        vistaCali2.setVisible(true);
      }else{
        JOptionPane.showMessageDialog(vistaCali, "Ha ocurrido un error");
      }
      }else{
      JOptionPane.showMessageDialog(vistaCali, "El código es inválido o ya fue utilizado");
      }
    }
  }
  
  private void calificarSala () throws SQLException {
    if (vistaCali2.validarDatos() == false){
      JOptionPane.showMessageDialog(vistaCali2, "Nota no es valida");
    } else {
      int nota = Integer.parseInt(vistaCali2.txtNota.getText());
      boolean calificada = dao.califarSala(modelo.getIdentificador(), nota);
      if (calificada == false){
        JOptionPane.showMessageDialog(vistaCali2, "Ha ocurrido un error al calificar la sala");
      } else{
        JOptionPane.showMessageDialog(vistaCali2, "La Sala ha sido calificada.Gracias.");
        vistaCali2.setVisible(true);
      }
    }
  }
  
}
