package logicadenegocios;

/**
 *
 * @author María Paula
 */
public class Horario {
  private String horaInicio;
  private String horaFin;
  private String dias;

  public Horario(String pHoraInicio, String pHoraFin, String pDias) {
    this.horaInicio = pHoraInicio;
    this.horaFin = pHoraFin;
    this.dias = pDias;
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
