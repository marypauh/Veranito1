package controlador;

import vista.LoginForm; 
import dao.UsuarioDAO; 
import java.awt.event.ActionEvent; 
import logicadenegocios.Usuario; 
import java.awt.event.ActionListener; 
import java.sql.SQLException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.swing.JOptionPane;
import logicadenegocios.Sala;
import vista.MenuForm;

/**
 *
 * @author Maria Paula Rodriguez 
 */
public class ControladorUsuario implements ActionListener {
    
  public LoginForm vista;
  public UsuarioDAO dao;
  public Usuario logicadenegocios;
  MenuForm vistaMenu = new MenuForm();
  ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
  
  /**
   * Constructor
   * @param pVista
   * @param pModelo 
   */  
  public ControladorUsuario(LoginForm pVista, Usuario pModelo){ 
    vista = pVista;
    logicadenegocios = pModelo;
    dao = new UsuarioDAO();
    this.vista.btIniciarLogin.addActionListener(this);
    this.vista.btCancelarLogin.addActionListener(this);
  }
  
  
  /**
   * Metodo que recibe y ejecuta una accion
   * @param e 
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()) {
        case "Iniciar Login": {
          try {
            logIn();
            break;
          } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        case "Cancelar Login":
          cerrarVentanaLogin();
          break;
        default:
          break;
    }
  }
  
  
  /**
   * Metodo para realizar el login
   * @throws SQLException 
   */
  public void logIn() throws SQLException {
    if (vista.logInDatosCorrectos() == true) {
   
      String nombreUsuario = vista.txtNombreUsuario.getText();
      String contraseña = vista.txtContraseña.getText();           
      logicadenegocios = new Usuario(nombreUsuario,contraseña);
      Usuario usuarioActual = dao.iniciarSesion(logicadenegocios);
      if (usuarioActual != null) { 
        vista.setVisible(false);
        JOptionPane.showMessageDialog(vista, "Bienvenido: " + 
        logicadenegocios.getNombreUsuario());
        controladorMenu.vista.abrirVentanaAnterior(vistaMenu);
      } else {
        JOptionPane.showMessageDialog(vista, "El usuario indicado  no existe");
      }
    } else {
      JOptionPane.showMessageDialog(vista, "Todos lo datos son requeridos");
    }
  }
  
  
  /**
   * Metodo para cerrar ventana Login
   */
  public void cerrarVentanaLogin() {
    vista.cancelarInicioSesion();
  }
  
}
