package controlador;

import dao.GraficoDAO;
import dao.ReservaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicadenegocios.Reserva;
import vista.BitacoraReservasForm;
import vista.MenuReportesForm;
import vista.TopCalificacionesForm;
import vista.TopCarrerasForm;
import vista.TopHorariosForm;
import vista.TopSalas1Form;



/**
 *
 * @author María Paula
 */
public class ControladorMenuReportes implements ActionListener {
    
  public MenuReportesForm vista;
  
  
  /**
   * Constructor
   * @param pVista
   */  
  public ControladorMenuReportes(MenuReportesForm pVista){ 
    vista = pVista;
    this.vista.btnCarreras.addActionListener(this);
    this.vista.btnHorariosUtilizados.addActionListener(this);
    this.vista.btnSalasPuntuacion.addActionListener(this);
    this.vista.btnSalasUtilizadas.addActionListener(this);
    this.vista.btnCerrar.addActionListener(this);
    this.vista.btnBitacoraReservas.addActionListener(this);
  }
  
  
  /**
   * Metodo que recibe y ejecuta una accion
   * @param e 
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()) {
      case "Salas más utilizadas": 
        abrirGrafico1();
        break;
      case "Horarios más utilizados": 
        abrirGrafico2();
        break;
      case "Carreras que más usan salas": 
        abrirGrafico3();
        break;
      case "Salas con mayor puntuación": 
        abrirGrafico4();
        break;
     case "Bitacora de Reservas": 
        abrirBitacoraReservas();
        break;
     case "Cerrar":
        this.vista.cerrarMenu();
        break;
      default:
        break;
    }
  }
  
  
/**
 * Metodo para abrir el grafico 1 de las 5 salas mas utilizadas
 */
  private void abrirGrafico1(){
    TopSalas1Form vista1 = new TopSalas1Form();
    GraficoDAO dao = new GraficoDAO();
    TopHorariosForm vista2 = new TopHorariosForm();
    TopCarrerasForm vista3 = new TopCarrerasForm();
    TopCalificacionesForm vista4 = new TopCalificacionesForm();
    ControladorGraficos controlador = new ControladorGraficos(vista1,dao, vista2, vista3, vista4);
    controlador.vista1.setVisible(true);
  }
  
  
/**
 * Metodo para abrir el grafico 2 de las 5 horarios mas utilizados
 */
  private void abrirGrafico2(){
    TopSalas1Form vista1 = new TopSalas1Form();
    GraficoDAO dao = new GraficoDAO();
    TopHorariosForm vista2 = new TopHorariosForm();
    TopCarrerasForm vista3 = new TopCarrerasForm();
    TopCalificacionesForm vista4 = new TopCalificacionesForm();
    ControladorGraficos controlador = new ControladorGraficos(vista1,dao, vista2, vista3, vista4);
    controlador.vista2.setVisible(true);
  }
  
  
  /**
 * Metodo para abrir el grafico 3 de las 5 carreras que mas reservan
 */
  private void abrirGrafico3(){
    TopSalas1Form vista1 = new TopSalas1Form();
    GraficoDAO dao = new GraficoDAO();
    TopHorariosForm vista2 = new TopHorariosForm();
    TopCarrerasForm vista3 = new TopCarrerasForm();
    TopCalificacionesForm vista4 = new TopCalificacionesForm();
    ControladorGraficos controlador = new ControladorGraficos(vista1,dao, vista2, vista3, vista4);
    controlador.vista3.setVisible(true);
  }
 
  
/**
 * Metodo para abrir el grafico 4 de las 5 salas con mayor puntuacion
 */
  private void abrirGrafico4(){
    TopSalas1Form vista1 = new TopSalas1Form();
    GraficoDAO dao = new GraficoDAO();
    TopHorariosForm vista2 = new TopHorariosForm();
    TopCarrerasForm vista3 = new TopCarrerasForm();
    TopCalificacionesForm vista4 = new TopCalificacionesForm();
    ControladorGraficos controlador = new ControladorGraficos(vista1,dao, vista2, vista3, vista4);
    controlador.vista4.setVisible(true);
  } 
  
  private void abrirBitacoraReservas(){
    BitacoraReservasForm vistaReservas = new BitacoraReservasForm();
    Reserva reserva = new Reserva();
    ControladorReserva controladorReserva = new ControladorReserva(vistaReservas,reserva);
    controladorReserva.vistaReservas.setVisible(true);
    controladorReserva.vistaReservas.setLocationRelativeTo(null);
  }
}
  
  