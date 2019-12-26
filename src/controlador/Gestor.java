package controlador;
import java.sql.SQLException;
import vista.MenuForm;
import controlador.ControladorMenu;
/**
 *
 * @author María Paula
 */
public class Gestor {
    
  public static void main(String[] args) throws SQLException{
      
    MenuForm vista = new MenuForm();
    ControladorMenu menu = new ControladorMenu(vista);
    
    menu.vista.abrirVentanaAnterior(vista);
    
  }    

}
