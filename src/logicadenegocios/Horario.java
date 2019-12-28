package logicadenegocios;

/**
 *
 * @author María Paula
 */
public class Horario {
  private int idHorario;
  private String horaInicio;
  private String horaFin;
  private String dias;

  public Horario(int pIdHorario,String pHoraInicio, String pHoraFin, String pDias) {
    this.idHorario = pIdHorario;
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
  
  
  public int getIdHorario() {
    return idHorario;
  }

  public void setIdHorario(int idHorario) {
    this.idHorario = idHorario;
  }
    
}
