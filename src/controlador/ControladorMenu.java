package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicadenegocios.Estudiante;
import logicadenegocios.Incidente;
import logicadenegocios.Reserva;
import logicadenegocios.Sala;
import vista.MenuForm;
import vista.AgregarEstudianteForm;
import vista.AgregarRecursosSalaForm;
import vista.CancelarReservaForm;
import vista.ConsultarEstudianteForm;
import vista.IncidentesReservaForm;
import vista.RegistrarIncidenteReservaForm;
import vista.RegistrarSalaForm;
import vista.ReservarSalaForm;
import vista.ReservasEstudianteForm;
import vista.SeleccionarHorarioForm;


/**
 *
 * @author María Paula
 */
public class ControladorMenu implements ActionListener {
    
  public MenuForm vista;
  
  
  /**
   * Constructor
   * @param pVista
   */  
  public ControladorMenu(MenuForm pVista){ 
    vista = pVista;
    this.vista.btnConsultarE.addActionListener(this);
    this.vista.btnAgregarEstudiante.addActionListener(this);
    this.vista.btnCerrar.addActionListener(this);
    this.vista.btnAgregarSala.addActionListener(this);
    this.vista.btnModificarSala.addActionListener(this);
    this.vista.btnReservarSala.addActionListener(this);
    this.vista.btnRegistrarIncidente.addActionListener(this);
    this.vista.btnConsultarSala.addActionListener(this);
    this.vista.btnCancelarReserva.addActionListener(this);
    this.vista.btnCalificar.addActionListener(this);
  }
  
  
  /**
   * Metodo que recibe y ejecuta una accion
   * @param e 
   */
  public void actionPerformed(ActionEvent e){
    switch(e.getActionCommand()) {
      case "Agregar Estudiante": 
        agregarEstudiante();
        break;
      case "Agregar Sala": 
        agregarSala();
        break;
      case "Modificar Sala": 
        modificarSala();
        break;
      case "Reservar Sala": 
        reservarSala();
        break;
      case "Registrar Incidente": 
        registrarIncidente();
        break;
      case "Cerrar":
        this.vista.cerrarMenu();
        break;
      case "Consultar Sala":
        consultarSala();
        break;
      case "Calificar Sala":
        calificar();
        break;
      case "Cancelar Reserva":
        cancelarReserva();
        break;
      case "Consultar Estudiante":
       consultarEstudiante();
      default:
        break;
    }
  }
    /**
   * Metodo que abre la ventana para agregar sala
   */
  private void agregarSala(){
      RegistrarSalaForm r = new RegistrarSalaForm();
      SeleccionarHorarioForm h = new SeleccionarHorarioForm();
      AgregarRecursosSalaForm recurso = new AgregarRecursosSalaForm();
      Sala sala = new Sala();
      ControladorSala controlador = new ControladorSala(r,sala,h, recurso);
      controlador.vista.setVisible(true);
  }
  
  private void modificarSala(){
    RegistrarSalaForm r = new RegistrarSalaForm();
    SeleccionarHorarioForm h = new SeleccionarHorarioForm();
    AgregarRecursosSalaForm recurso = new AgregarRecursosSalaForm();
    Sala sala = new Sala();
    ControladorSala controlador = new ControladorSala(r,sala,h, recurso);
    controlador.vistaModSala.setVisible(true);
  }
  
  private void reservarSala(){
    ReservarSalaForm vistaAgregarReservas = new ReservarSalaForm();
    Reserva reserva = new Reserva();
    ControladorReserva controladorReserva = new ControladorReserva(vistaAgregarReservas, reserva);
    controladorReserva.vista.setVisible(true);
  }
  
  private void registrarIncidente(){
     RegistrarIncidenteReservaForm vistaRegistrarIncidentes = new RegistrarIncidenteReservaForm();
      Incidente incidente = new Incidente();
      ControladorIncidente controladorIncidente = new ControladorIncidente(vistaRegistrarIncidentes,incidente);
      controladorIncidente.vista.setVisible(true);
  }
  
  private void consultarSala(){
    RegistrarSalaForm r = new RegistrarSalaForm();
    SeleccionarHorarioForm h = new SeleccionarHorarioForm();
    AgregarRecursosSalaForm recurso = new AgregarRecursosSalaForm();
    Sala sala = new Sala();
    ControladorSala controlador = new ControladorSala(r,sala,h, recurso);
    controlador.vistaConsulta.setVisible(true);
  }
  
  private void cancelarReserva(){
    CancelarReservaForm vistaCancelarReserva = new CancelarReservaForm();
    Reserva reserva = new Reserva();
    ControladorReserva controladorReserva = new ControladorReserva(vistaCancelarReserva, reserva);
    controladorReserva.vistaCancelar.setVisible(true);
  }
  
  
  private void calificar(){
    RegistrarSalaForm r = new RegistrarSalaForm();
    SeleccionarHorarioForm h = new SeleccionarHorarioForm();
    AgregarRecursosSalaForm recurso = new AgregarRecursosSalaForm();
    Sala sala = new Sala();
    ControladorSala controlador = new ControladorSala(r,sala,h, recurso);
    controlador.vistaCali.setVisible(true);
  }
  
  
  /**
 * Metodo para abrir ventana registrar estudiante
 */
private void agregarEstudiante(){
  AgregarEstudianteForm vistaAgregarEstudiante = new AgregarEstudianteForm();
  Estudiante logicadenegocios = new Estudiante();
  ConsultarEstudianteForm vistaConsulta = new ConsultarEstudianteForm();
  ReservasEstudianteForm vistaReservas = new ReservasEstudianteForm();
  IncidentesReservaForm vistaIncidentes = new IncidentesReservaForm();
  ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaAgregarEstudiante,logicadenegocios,vistaConsulta,vistaReservas, vistaIncidentes);
  controladorEstudiante.vista.abrirVentanaAnterior(vistaAgregarEstudiante);
}


/**
 * Metodo para abrir ventana consultar estudiante
 */
private void consultarEstudiante(){
  AgregarEstudianteForm vistaAgregarEstudiante = new AgregarEstudianteForm();
  Estudiante logicadenegocios = new Estudiante();
  ConsultarEstudianteForm vistaConsulta = new ConsultarEstudianteForm();
  ReservasEstudianteForm vistaReservas = new ReservasEstudianteForm();
  IncidentesReservaForm vistaIncidentes = new IncidentesReservaForm();
  ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaAgregarEstudiante,logicadenegocios,vistaConsulta,vistaReservas, vistaIncidentes);
  controladorEstudiante.vistaConsulta.abrirVentanaAnterior(vistaConsulta);
}
}
  
  