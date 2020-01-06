
package controlador;

import dao.GraficoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import vista.TopCalificacionesForm;
import vista.TopCarrerasForm;
import vista.TopHorariosForm;
import vista.TopSalas1Form;

/**
 *
 * @author María Paula
 */
public class ControladorGraficos implements ActionListener {
  
  public TopSalas1Form vista1;
  public GraficoDAO dao;
  public TopHorariosForm vista2;
  public TopCarrerasForm vista3;
  public TopCalificacionesForm vista4;

  public ControladorGraficos(TopSalas1Form pVista1, GraficoDAO pDao, TopHorariosForm pVista2, TopCarrerasForm pVista3, TopCalificacionesForm pVista4) {
    vista1 = pVista1;
    dao = pDao;
    vista2 = pVista2;
    vista3 = pVista3;
    vista4 = pVista4;
    this.vista4.btnCerrar.addActionListener(this);
    this.vista4.btnGraficar.addActionListener(this);
    this.vista3.btnCerrar.addActionListener(this);
    this.vista3.btnGraficar.addActionListener(this);
    this.vista2.btnGraficar.addActionListener(this);
    this.vista2.btnCerrar.addActionListener(this);
    this.vista1.btnCerrar.addActionListener(this);
    this.vista1.btnGraficar.addActionListener(this);
  }


  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "Cerrar Salas": 
        this.vista1.volverMenu();
        break;
      case "Graficar Salas":
        getTopSalas1();
        break;
      case "Cerrar Horarios": 
        this.vista2.volverMenu();
        break;
      case "Graficar Horarios":
        getTopHorarios();
        break;
      case "Cerrar Carreras": 
        this.vista3.volverMenu();
        break;
      case "Graficar Carreras":
        getTopCarreras();
        break;
      case "Cerrar Puntuacion": 
        this.vista4.volverMenu();
        break;
      case "Graficar Mayor Puntuacion":
        getTopCalificacion();
        break;  
      default:
        break;
    }
  }
  
  
  
 /**
  * Metodo para graficar las salas mas utilizadas
  */
  public void getTopSalas1(){
    ResultSet topSalas = dao.getSalasUtilizadas();
    if (topSalas == null){
        JOptionPane.showMessageDialog(vista1, "Error al cargar el top salas");
    }else{
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
     
        try {
          while(topSalas.next()){
            String idSala = topSalas.getString("idSala");
            int contador = topSalas.getInt("contador");
            datos.setValue(contador, "Salas", idSala);
            }
            JFreeChart ch = ChartFactory.createBarChart3D("Salas mas utilizadas", "Sala", "Cantidad", datos,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            cp.setBounds(500,40,500,400);
            this.vista1.getContentPane().add(cp);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }

  /**
   * Metodo para obtener los horarios mas utilizados
   */
  public void getTopHorarios(){
    ResultSet topHorarios = dao.getHorariosUtilizados();
    if (topHorarios == null){
        JOptionPane.showMessageDialog(vista1, "Error al cargar el top horarios");
    }else{
        DefaultPieDataset datos = new DefaultPieDataset();
     
        try {
          while(topHorarios.next()){
            String idHorario = topHorarios.getString("idHorario");
            int contador = topHorarios.getInt("contador");
            datos.setValue(idHorario,contador);
            }
            JFreeChart ch = ChartFactory.createPieChart3D("Horarios más utilizados", datos,true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            cp.setBounds(500,40,500,400);
            this.vista2.getContentPane().add(cp);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }

/**
 * Metodo para obtener el top 5 de las carreras que mas reservan
 */
  public void getTopCarreras(){
    ResultSet topCarreras = dao.getCarreras();
    if (topCarreras == null){
        JOptionPane.showMessageDialog(vista1, "Error al cargar el top carreras");
    }else{
        DefaultPieDataset datos = new DefaultPieDataset();
     
        try {
          while(topCarreras.next()){
            String carrera = topCarreras.getString("carrera");
            int contador = topCarreras.getInt("contador");
            datos.setValue(carrera,contador);
            }
            JFreeChart ch = ChartFactory.createPieChart3D("Carreras que más reservan", datos,true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            cp.setBounds(500,40,500,400);
            this.vista3.getContentPane().add(cp);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  
  
/**
 * Metodo para obtener el top 5 de las salas con mayor puntuacion
 */
  public void getTopCalificacion(){
    ResultSet topCalificaciones = dao.getCalificaciones();
    if (topCalificaciones == null){
        JOptionPane.showMessageDialog(vista1, "Error al cargar el top calificaciones");
    }else{
      DefaultCategoryDataset datos = new DefaultCategoryDataset();
     
        try {
          while(topCalificaciones.next()){
            String idSala = topCalificaciones.getString("identificador");
            int calificacion = topCalificaciones.getInt("calificacion");
            datos.setValue(calificacion, "Salas", idSala);
            }
            JFreeChart ch = ChartFactory.createBarChart3D("Salas con mayor puntuacion", "Sala", "Calificacion", datos,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            cp.setBounds(500,40,500,400);
            this.vista4.getContentPane().add(cp);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
  }  
}


     
   
