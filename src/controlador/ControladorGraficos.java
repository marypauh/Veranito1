
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

  public ControladorGraficos(TopSalas1Form pVista) {
    vista = pVista;
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
            JFreeChart ch = ChartFactory.createBarChart("Salas mas utilizadas","Salas", "Reservas", datos, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel Panel = new ChartPanel(ch);
            this.vista.getContentPane().add(Panel);
            this.vista.setSize(800,500);
            this.vista.setVisible(true);
            this.vista.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGraficos.class.getName()).log(Level.SEVERE, null, ex);
        }
              
            }
      
            
            
          
    }
}
     
   
