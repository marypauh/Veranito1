/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
   * Metodo par agregar sala a la base de datos
     * @throws java.text.ParseException
   * @throws SQLException 
   */
  public void agregarReserva() throws ParseException, SQLException{
    int organizador = Integer.parseInt(vista.txtCarnetEstudiante.getText());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = formatter.parse(vista.txtFecha.getText());
    String horaInicio = vista.txtHoraInicio.getText();
    String horaFin = vista.txtHoraFin.getText();
    String asunto = vista.txtAsunto.getText();
    String idSala = (String)vista.salasTable.getValueAt(vista.salasTable.getSelectedRow(),0);
    int idReserva = dao.obtenerIdReserva();
    String codigoCalificacion = idSala+"-"+organizador+"-"+idReserva;
    logicadenegocios = new Reserva(fecha, horaInicio,horaFin,asunto,organizador,idSala);
    Reserva reserva = dao.agregarReserva(logicadenegocios);
    int participantesAgregados = participanteDao.agregarParticipantes(listaParticipantes, idReserva,12);
    if (reserva != null&&participantesAgregados>0){
      vista.setVisible(false);
      JOptionPane.showMessageDialog(vista, "Se reservo la sala exitosamente");
      participanteDao.enviarCorreoParticipantes(listaParticipantes,idSala,horaInicio,horaFin,fecha);
      JOptionPane.showMessageDialog(vista,"Correo de invitación enviado a los participantes");
      vista.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(vista,"No es posible reservar la sala");
    }
  }
  
  /**
   * 
     * @throws java.sql.SQLException
   */
  public void filtrarSalas() throws SQLException{
    ResultSet salas = dao.consultarSalas();
    if (salas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar reservas");
    }else{
        DefaultTableModel table = new DefaultTableModel();
        vista.salasTable.setModel(table);
        table.setColumnIdentifiers(new Object[]{"Identificador","Ubicacion","Capacidad Maxima","Estado","Calificacion","Hora Inicio","Hora Fin","Dias"});
        try {
            while(salas.next()){
                table.addRow(new Object[]{salas.getString("identificador"),salas.getString("ubicacion"),salas.getInt("capacidadMax"),salas.getString("estado"),salas.getInt("calificacion"),salas.getString("horaInicio"),salas.getString("horaFin"),salas.getString("dias")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
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

  public void cargarReservas() throws SQLException{
    ResultSet reservas = dao.consultarReservas();
    if (reservas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar reservas");
    }else{
        DefaultTableModel table = new DefaultTableModel();
        vistaCancelar.reservasTable.setModel(table);
        table.setColumnIdentifiers(new Object[]{"Numero","Estado", "Fecha", "Hora Inicio","Hora Fin", "Codigo Calificacion", "Asunto", "Organizador","Id Sala"});
        try {
            while(reservas.next()){
                if(verificarHoraInicio(reservas.getString("horaInicio"))==true&&!"Cancelada".equals(reservas.getString("estado"))){
                  table.addRow(new Object[]{reservas.getInt("numero"), reservas.getString("estado"), reservas.getDate("fecha"), reservas.getString("horaInicio"), reservas.getString("horaFin"), reservas.getString("codigoCalificacion"), reservas.getString("asunto"), reservas.getInt("organizador"),reservas.getString("idSala")});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  
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
      JOptionPane.showMessageDialog(vistaCancelar,"Notificacion enviada los participantes");
      cargarReservas();
      vistaCancelar.setVisible(true);
    } else{
      JOptionPane.showMessageDialog(vistaCancelar,"Error al cancelar la reserva"); 
    }
  }
  
  
  public boolean verificarHoraInicio(String pHoraInicio){
    int comprobarHora = 0;
    int comprobarMinutos = 0;
    Calendar calendario = Calendar.getInstance();
    int hora, minutos;
    hora =calendario.get(Calendar.HOUR_OF_DAY);
    minutos = calendario.get(Calendar.MINUTE);
    LocalTime t = LocalTime.parse(pHoraInicio);
    comprobarHora = t.getHour()-hora;
    comprobarMinutos = t.getMinute()-minutos;
    if(comprobarHora >= 1 && comprobarMinutos>=0){
      return true;
    } else {
      return false;
    }
    
  }

}
