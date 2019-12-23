package logicadenegocios;
import java.util.Date;

/**
 *
 * @author María Paula
 */
public class Incidente {
  private String detalle;
  private int valor;
  private Date fecha;

  public Incidente(String detalle, int valor, Date fecha) {
    this.detalle = detalle;
    this.valor = valor;
    this.fecha = fecha;
  }

  
  public String getDetalle() {
    return detalle;
  }

  
  public int getValor() {
    return valor;
  }

  
  public Date getFecha() {
    return fecha;
  }
  
}
