package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicadenegocios.Estudiante;
import logicadenegocios.Sala;
import vista.MenuForm;
import vista.AgregarEstudianteForm;
import vista.AgregarRecursosSala;
import vista.RegistrarSala;
import vista.SeleccionarHorario;


/**
 *
 * @author María Paula
 */
public class ControladorMenu implements ActionListener {
    
  public MenuForm vista;
  AgregarEstudianteForm vistaAgregarEstudiante = new AgregarEstudianteForm();
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
    this.vista.btnAgregarSala.addActionListener(this);
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
      case "Agregar Sala": 
        agregarSala();
        break;
      case "Cancelar Login":
        vista.cerrarMenu();
        break;
      default:
        break;
    }
  }
    /**
   * Metodo que abre la ventana para agregar sala
   */
  private void agregarSala(){
      RegistrarSala r = new RegistrarSala();
      SeleccionarHorario h = new SeleccionarHorario();
      AgregarRecursosSala recurso = new AgregarRecursosSala();
      Sala sala = new Sala();
      ControladorSala controlador = new ControladorSala(r,sala,h, recurso);
      controlador.vista.setVisible(true);
  }
}
  
  