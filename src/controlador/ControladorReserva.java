package controlador;

import dao.ParticipanteDAO;
import dao.ReservaDAO;
import dao.SalaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicadenegocios.Estudiante;
import logicadenegocios.Participante;
import logicadenegocios.Reserva;
import logicadenegocios.Sala;
import vista.CancelarReservaForm;
import vista.ReservarSalaForm;

/**
 *
 * @author Kevin Castillo
 */
public class ControladorReserva implements ActionListener {
  public ReservarSalaForm vista;
  public CancelarReservaForm vistaCancelar;
  public ReservaDAO dao;
  public Reserva logicadenegocios;
  public ArrayList<Participante> listaParticipantes;
  public ParticipanteDAO participanteDao;

  
  /**
   * Constructor
   * @param pVista
   * @param pModelo 
   */
  public ControladorReserva(ReservarSalaForm pVista, Reserva pModelo) {
    vista = pVista;
    logicadenegocios = pModelo;
    dao = new ReservaDAO();
    listaParticipantes = new ArrayList<Participante>();
    participanteDao = new ParticipanteDAO();
    this.vista.btnFiltrarSalas.addActionListener(this);
    this.vista.btnReservarSala.addActionListener(this);
    this.vista.btnVolver.addActionListener(this);
    this.vista.btnAgregar.addActionListener(this);
  }
  
  public ControladorReserva(CancelarReservaForm pVista, Reserva pModelo) {
    vistaCancelar = pVista;
    logicadenegocios = pModelo;
    dao = new ReservaDAO();
    this.vistaCancelar.btnVolver.addActionListener(this);
    this.vistaCancelar.btnCargarReservas.addActionListener(this);
    this.vistaCancelar.btnCancelarReserva.addActionListener(this);
  }
    
  /**
   * Al dar click al boton este ejecuta el proceso agregarsala()
   * @param e 
   */
  public void actionPerformed(ActionEvent e) {    
    switch(e.getActionCommand()) {
      case "Filtrar Salas":
    {
        try {
            filtrarSalas();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      case "Reservar Sala":
    {
        try {
            agregarReserva();
        } catch (ParseException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      case "Volver":
        volverMenu();
        break;
      case "Menu":
        ventanaAnterior();
        break;
      case "Agregar":
        agregarParticipantes();
        break;
      case "Cargar Reservas":
    {
        try {
            cargarReservas();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      case "Cancelar Reserva":
    {
        try {
            cancelarReserva();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;
      default:
        break;
    } 
  }

  
  /**
   * Metodo para agregar reserva a la base de datos
     * @throws java.text.ParseException
   * @throws SQLException 
   */
  public void agregarReserva() throws ParseException, SQLException{
    if(vista.ReservarSalaDatosCorrectos()==true){
    int organizador = Integer.parseInt(vista.txtCarnetEstudiante.getText());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = formatter.parse(vista.txtFecha.getText());
    String horaInicio = vista.txtHoraInicio.getText();
    String horaFin = vista.txtHoraFin.getText();
    String asunto = vista.txtAsunto.getText();
    String idSala = (String)vista.salasTable.getValueAt(vista.salasTable.getSelectedRow(),0);
    int idReserva = dao.obtenerIdReserva();
    logicadenegocios = new Reserva(fecha, horaInicio,horaFin,asunto,organizador,idSala);
    int capacidadMax = (int)vista.salasTable.getValueAt(vista.salasTable.getSelectedRow(),2);
    System.out.println(dao.comprobarCantidadReservas(fecha, organizador));
    if(listaParticipantes.size()<=capacidadMax){
      if(dao.existeEstudiante(organizador)>0){
        if(dao.comprobarCalificacionEstudiante(organizador)>70){
          if(dao.comprobarCantidadReservas(fecha,organizador)<3){
            participanteDao.agregarParticipantesReserva(listaParticipantes, idReserva);
            participanteDao.agregarParticipantes(listaParticipantes);
            Reserva reserva = dao.agregarReserva(logicadenegocios);
            if (reserva != null){
              vista.setVisible(false);
              JOptionPane.showMessageDialog(vista, "Se reservo la sala exitosamente");
              participanteDao.enviarCorreoParticipantes(listaParticipantes,idSala,horaInicio,horaFin,fecha);
              dao.notificarOrganizador(dao.obtenerCorreoEstudiante(organizador),idSala,horaInicio,horaFin,fecha);
              JOptionPane.showMessageDialog(vista,"Correo de invitación enviado a los participantes");
              vista.setVisible(true);
            } else {
              JOptionPane.showMessageDialog(vista,"No es posible reservar la sala");
            }  
          } else{
            JOptionPane.showMessageDialog(vista,"Solo se pueden reservar un maximo de 3 salas por semana");      
          }
        } else {
          JOptionPane.showMessageDialog(vista,"El estudiante no cumple con la calificacion minima requerida");
        }
      } else {
        JOptionPane.showMessageDialog(vista,"El estudiante no esta registrado en el sistema");
      }
    } else {
      JOptionPane.showMessageDialog(vista,"La cantidad de participantes excede la capacidad maxima de la sala");
    }
  } else {
    JOptionPane.showMessageDialog(vista,"Todos los datos son requeridos");
    }
  }
  
  /**
   * 
     * @throws java.sql.SQLException
   */
  public void filtrarSalas() throws SQLException, ParseException{
    ResultSet salas = dao.consultarSalas(Integer.parseInt(vista.txtCapacidadMinima.getText()),(String)vista.txtRecurso.getText());
    if (salas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar las salas");
    }else{
        DefaultTableModel table = new DefaultTableModel();
        vista.salasTable.setModel(table);
        table.setColumnIdentifiers(new Object[]{"Identificador","Ubicacion","Capacidad Maxima","Estado","Calificacion","Hora Inicio","Hora Fin","Dias"});
        if(comprobarDiferenciaHoras(vista.txtHoraInicio.getText(),vista.txtHoraFin.getText())<0){
          try {
            while(salas.next()){
              if(comprobarHoraInicio(salas.getString("horaInicio"),vista.txtHoraInicio.getText())<=0&&comprobarHoraFin(salas.getString("horaFin"),vista.txtHoraFin.getText())>=0){
                table.addRow(new Object[]{salas.getString("identificador"),salas.getString("ubicacion"),salas.getInt("capacidadMax"),salas.getString("estado"),salas.getInt("calificacion"),salas.getString("horaInicio"),salas.getString("horaFin"),salas.getString("dias")});   
              }
            }
          } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
          JOptionPane.showMessageDialog(vista,"ERROR- La hora de inicio excede la hora de finalizacion");
        }
    }
  }
  
  public void agregarParticipantes(){
    String nombre = vista.txtNombre.getText();
    String email = vista.txtEmail.getText();
    Participante participante = new Participante(nombre, email);
    listaParticipantes.add(participante);
    JOptionPane.showMessageDialog(vista,"Se agrego el participante exitosamente");
    vista.txtNombre.setText(null);
    vista.txtEmail.setText(null);
  }
  
  
  public void volverMenu(){
    vista.volverMenu();
  }
  
  public void ventanaAnterior(){
    vistaCancelar.volverMenu();
  }

  /**
   * Metodo para cargar las reservas en la tabla de la vista
   * @throws SQLException 
   */
  public void cargarReservas() throws SQLException{
    ResultSet reservas = dao.consultarReservasValidas();
    if (reservas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar reservas");
    }else{
        DefaultTableModel table = new DefaultTableModel();
        vistaCancelar.reservasTable.setModel(table);
        table.setColumnIdentifiers(new Object[]{"Numero","Estado", "Fecha", "Hora Inicio","Hora Fin", "Codigo Calificacion", "Asunto", "Organizador","Id Sala"});
        try {
            while(reservas.next()){
                if(verificarHoraInicio(reservas.getString("horaInicio"),reservas.getDate("fecha"))==true&&!"Cancelada".equals(reservas.getString("estado"))){
                  table.addRow(new Object[]{reservas.getInt("numero"), reservas.getString("estado"), reservas.getDate("fecha"), reservas.getString("horaInicio"), reservas.getString("horaFin"), reservas.getString("codigoCalificacion"), reservas.getString("asunto"), reservas.getInt("organizador"),reservas.getString("idSala")});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  
  /**
   * Metodo para pasar el estado de la reserva a cancelado
   * @throws SQLException
   */
  public void cancelarReserva() throws SQLException{
    int numero = (int)vistaCancelar.reservasTable.getValueAt(vistaCancelar.reservasTable.getSelectedRow(),0);
    int cancelado = dao.cancelarReserva(numero);
    if(cancelado>0){
      vistaCancelar.setVisible(false);
      JOptionPane.showMessageDialog(vistaCancelar, "Se cancelo la reserva exitosamente");
      String idSala = (String)vistaCancelar.reservasTable.getValueAt(vistaCancelar.reservasTable.getSelectedRow(),8);
      int organizador = (int)vistaCancelar.reservasTable.getValueAt(vistaCancelar.reservasTable.getSelectedRow(),7);
      dao.notificarParticipantes(numero,idSala);
      dao.notificarOrganizador(organizador,idSala);
      dao.bajarCalificacionEstudiante(organizador,1);
      JOptionPane.showMessageDialog(vistaCancelar,"Notificacion enviada los participantes");
      cargarReservas();
      vistaCancelar.setVisible(true);
    } else{
      JOptionPane.showMessageDialog(vistaCancelar,"Error al cancelar la reserva"); 
    }
  }
  
  /**
   * Metodo para verificar si la reserva esta al menos de una hora de su comienzo
   * @param pHoraInicio hora de inicio de la reserva
   * @param pFecha fecha en que se da la reserva
   * @return true si la reserva esta al menos de una hora de su comienzo
   */
  public boolean verificarHoraInicio(String pHoraInicio,Date pFecha){
   Calendar fecha1 = Calendar.getInstance();
   fecha1.setTime(pFecha);
   Calendar fecha2 = Calendar.getInstance();
   if(fecha1.before(fecha2)==true){
     int comprobarHora = 0;
     int comprobarMinutos = 0;
     Calendar calendario = Calendar.getInstance();
     int hora, minutos;
     hora =calendario.get(Calendar.HOUR_OF_DAY);
     minutos = calendario.get(Calendar.MINUTE);
     LocalTime t = LocalTime.parse(pHoraInicio);
     comprobarHora = t.getHour()-hora;
     comprobarMinutos = t.getMinute()-minutos;
     if(comprobarHora > 1){
       return true;
      }
     else if(comprobarHora>=1&&comprobarMinutos>=0){
       return true;
     } else {
       return false;        
     } 
   }else {
    return true;
   }
  }
  
  public int comprobarHoraInicio(String pHoraInicio,String pHoraInicioSala) throws ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    java.util.Date hora1 = formatter.parse(pHoraInicio);
    java.util.Date hora2 = formatter.parse(pHoraInicioSala);
    return hora1.compareTo(hora2);  
  }
  
  public int comprobarHoraFin(String pHoraInicio,String pHoraInicioSala) throws ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    java.util.Date hora1 = formatter.parse(pHoraInicio);
    java.util.Date hora2 = formatter.parse(pHoraInicioSala);
    return hora1.compareTo(hora2);  
  }
  
  public int comprobarDiferenciaHoras(String pHoraInicio,String pHoraFin) throws ParseException{
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    java.util.Date hora1 = formatter.parse(pHoraInicio);
    java.util.Date hora2 = formatter.parse(pHoraFin);
    return hora1.compareTo(hora2);  
  }
}
