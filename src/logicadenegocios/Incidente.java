package logicadenegocios;
import java.util.Date;

/**
 *
 * @author María Paula
 */
public class Incidente {
  private int idIncidente;
  private String detalle;
  private int valor;
  private Date fecha;

  public Incidente(String pDetalle, int pValor, Date pFecha) {
    this.detalle = pDetalle;
    this.valor = pValor;
    this.fecha = pFecha;
  }

  public Incidente() {}
  
  public String getDetalle() {
    return detalle;
  }

  public int getValor() {
    return valor;
  }

  public Date getFecha() {
    return fecha;
  }

  public int getIdIncidente() {
      return idIncidente;
  }
  
  
  
}
