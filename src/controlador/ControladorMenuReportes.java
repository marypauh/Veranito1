package controlador;

import dao.SalaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuReportesForm;
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
      
        break;
      case "Carreras que más usan salas": 
       
        break;
      case "Salas con mayor puntuación": 
       
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
    SalaDAO dao = new SalaDAO();
    ControladorGraficos controlador = new ControladorGraficos(vista1,dao);
    controlador.vista.setVisible(true);

}
  
}
  
  