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

  public HorarioExcepcion(String horaInicio, String horaFin, Date fecha, String descripcion) {
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.fecha = fecha;
    this.descripcion = descripcion;
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
