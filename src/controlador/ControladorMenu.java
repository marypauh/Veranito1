package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicadenegocios.Estudiante;
import vista.MenuForm;
import vista.AgregarEstudianteForm;
import controlador.ControladorEstudiante;


/**
 *
 * @author María Paula
 */
public class ControladorMenu implements ActionListener {
    
  public MenuForm vista;
  AgregarEstudianteForm vistaAgregarEstudiante;
  Estudiante logicadenegocios = new Estudiante();
  ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaAgregarEstudiante,logicadenegocios);

  /**
   * Constructor
   * @param pVista
   */  
  public ControladorMenu(MenuForm pVista){ 
    vista = pVista;
    this.vista.btnAgregarEstudiante.addActionListener(this);
    this.vista.btnCerrar.addActionListener(this);
  }
  
  
  /**
   * Metodo que recibe y ejecuta una accion
   * @param e 
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()) {
      case "Agregar Estudiante": 
        controladorEstudiante.vista.abrirVentanaAnterior(vistaAgregarEstudiante);
        break;
      case "Cancelar Login":
        vista.cerrarMenu();
        break;
      default:
        break;
    }
  }
}
  
  