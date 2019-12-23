package logicadenegocios;
import java.util.Date;

/**
 *
 * @author María Paula
 */
public class HorarioExcepcion {
  private String horaInicio;
  private String horaFin;
  private Date fecha;
  private String descripcion;

  public HorarioExcepcion(String pHoraInicio, String pHoraFin, Date pFecha, String pDescripcion) {
    this.horaInicio = pHoraInicio;
    this.horaFin = pHoraFin;
    this.fecha = pFecha;
    this.descripcion = pDescripcion;
  }

  
  public String getHoraInicio() {
    return horaInicio;
  }

  
  public String getHoraFin() {
    return horaFin;
  }

  
  public Date getFecha() {
    return fecha;
  }

  
  public String getDescripcion() {
    return descripcion;
  }
  
}
