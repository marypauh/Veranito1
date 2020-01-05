
package controlador;

import conexion.Conexion;
import dao.SalaDAO;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import vista.TopSalas1Form;

/**
 *
 * @author María Paula
 */
public class ControladorGraficos implements ActionListener {
  
  public TopSalas1Form vista;
  public SalaDAO dao;

  public ControladorGraficos(TopSalas1Form pVista, SalaDAO pDao) {
    vista = pVista;
    dao = pDao;
    this.vista.btnCerrar.addActionListener(this);
    this.vista.btnGraficar.addActionListener(this);
  }


  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "Cerrar": 
        this.vista.volverMenu();
        break;
      case "Graficar":
        getTopSalas1();
        break;
      default:
        break;
    }
  }
  
  
  
 public void getTopSalas1(){
    ResultSet topSalas = dao.getSalasUtilizadas();
    if (topSalas == null){
        JOptionPane.showMessageDialog(vista, "Error al cargar el top salas");
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
            this.vista.getContentPane().add(cp);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
          
    }

     
   
