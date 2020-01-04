package controlador;
import java.sql.SQLException;
import logicadenegocios.Usuario;
import vista.LoginForm;

/**
 *
 * @author María Paula
 */
public class Gestor {
    
  public static void main(String[] args) throws SQLException{
    
    LoginForm vista = new LoginForm();
    Usuario logicadenegocios = new Usuario();
  
    ControladorUsuario controladorUsuario = new ControladorUsuario(vista,logicadenegocios);
    controladorUsuario.vista.abrirVentanaAnterior(vista);
    
  }    

}
