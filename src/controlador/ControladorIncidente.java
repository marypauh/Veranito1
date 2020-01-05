/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.IncidenteDAO;
import dao.ReservaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logicadenegocios.Incidente;
import vista.RegistrarIncidenteReservaForm;
/**
 *
 * @author Kevin Castillo
 */
public class ControladorIncidente implements ActionListener {
  public RegistrarIncidenteReservaForm vista;
  public IncidenteDAO dao;
  public ReservaDAO reservaDAO;
  public Incidente logicadenegocios;

  
  /**
   * Constructor
   * @param pVista
   * @param pModelo 
   */
  public ControladorIncidente(RegistrarIncidenteReservaForm pVista, Incidente pModelo) {
    vista = pVista;
    logicadenegocios = pModelo;
    dao = new IncidenteDAO();
    reservaDAO = new ReservaDAO();
    this.vista.btnRegistrarIncidente.addActionListener(this);
    this.vista.btnVolver.addActionListener(this);
    this.vista.btnCargarReservas.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e) {    
    switch(e.getActionCommand()) {
    case "Registrar Incidente":
    {
        try {
            registrarIncidente();
        } catch (ParseException ex) {
            Logger.getLogger(ControladorIncidente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIncidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      break;
    case "Volver":
      volverMenu();
      break;
    case "Cargar Reservas":
    {
        try {
            cargarReservas();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIncidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      break;
    default:
      break;
    } 
  }
  
  public void registrarIncidente() throws ParseException, SQLException{
    String asunto = (String)vista.cbIncidentes.getSelectedItem();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = formatter.parse(vista.txtFecha.getText());
    int valor = 0;
    String idSala = (String)vista.reservasTable.getValueAt(vista.reservasTable.getSelectedRow(),8);
    int organizador = (int)vista.reservasTable.getValueAt(vista.reservasTable.getSelectedRow(),7);
    int numero = (int)vista.reservasTable.getValueAt(vista.reservasTable.getSelectedRow(),0);
    if("Sala reservada no utilizada".equals(asunto)){
      valor = 15;
    }
    else if("Ruido excesivo".equals(asunto)){
      valor = 10;    
    }
    else if("Basura y desorden".equals(asunto)){
      valor = 7;
    }
    logicadenegocios = new Incidente(asunto,valor,fecha);
    Incidente incidente = dao.agregarIncidente(logicadenegocios);
    if(incidente != null){
      vista.setVisible(false);
      JOptionPane.showMessageDialog(vista, "Se agrego el incidente exitosamente");
      dao.enviarCorreoOrganizador(idSala,fecha,asunto,organizador);
      dao.agregarIncidenteReserva(numero);
      dao.bajarCalificacionEstudiante(organizador,valor);
      JOptionPane.showMessageDialog(vista,"Correo de notificacion de incidente enviado al organizador");
      vista.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(vista,"No es posible reservar la sala");
    }
  }
  
  public void volverMenu(){
    vista.volverMenu();
  }

  public void cargarReservas() throws SQLException{
    ResultSet reservas = reservaDAO.consultarReservas();
    if (reservas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar reservas");
    }else{
        DefaultTableModel table = new DefaultTableModel();
        vista.reservasTable.setModel(table);
        table.setColumnIdentifiers(new Object[]{"Numero","Estado", "Fecha", "Hora Inicio","Hora Fin", "Codigo Calificacion", "Asunto", "Organizador","Id Sala"});
        try {
            while(reservas.next()){
                table.addRow(new Object[]{reservas.getInt("numero"), reservas.getString("estado"), reservas.getDate("fecha"), reservas.getString("horaInicio"), reservas.getString("horaFin"), reservas.getString("codigoCalificacion"), reservas.getString("asunto"), reservas.getInt("organizador"),reservas.getString("idSala")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }  
}
