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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicadenegocios.Estudiante;
import logicadenegocios.Participante;
import logicadenegocios.Reserva;
import logicadenegocios.Sala;
import vista.ReservarSalaForm;

/**
 *
 * @author Kevin Castillo
 */
public class ControladorReserva implements ActionListener {
  public ReservarSalaForm vista;
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
      case "Agregar":
        agregarParticipantes();
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
    logicadenegocios = new Reserva(fecha, horaInicio,horaFin,codigoCalificacion,asunto,organizador,idSala);
    Reserva reserva = dao.agregarReserva(logicadenegocios);
    int participantesAgregados = participanteDao.agregarParticipantes(listaParticipantes, idReserva,12);
    if (reserva != null&&participantesAgregados>0){
      vista.setVisible(false);
      JOptionPane.showMessageDialog(vista, "Se reservo la sala exitosamente");
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


}
