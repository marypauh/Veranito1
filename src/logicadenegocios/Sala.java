package logicadenegocios;

/**
 *
 * @author María Paula
 */
public class Sala {
  private String identificador;
  private String ubicacion;
  private int capacidadMax;
  private String calificacion;

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String pIdentificador) {
    this.identificador = pIdentificador;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String pUbicacion) {
    this.ubicacion = pUbicacion;
  }

  public int getCapacidadMax() {
    return capacidadMax;
  }

  public void setCapacidadMax(int pCapacidadMax) {
    this.capacidadMax = pCapacidadMax;
  }

  public String getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(String pCalificacion) {
    this.calificacion = pCalificacion;
  }
}
