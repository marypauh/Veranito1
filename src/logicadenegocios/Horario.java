package logicadenegocios;

/**
 *
 * @author María Paula
 */
public class Horario {
  private String horaInicio;
  private String horaFin;
  private String dias;

  public Horario(String horaInicio, String horaFin, String dias) {
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.dias = dias;
  }

    
  public String getHoraInicio() {
    return horaInicio;
  }

    
  public String getHoraFin() {
    return horaFin;
  }

    
  public String getDias() {
    return dias;
  }
    
}
