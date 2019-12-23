package logicadenegocios;

/**
 * Clase Estudiante
 * @author Maria Paula 
 */
public class Estudiante {
  private String nombreCompleto;
  private int carnet;
  private String carrera;
  private String email;
  private int calificacion = 100;
  private String telefono;

  
  /**
   * Constructor
   */
  public Estudiante() {}

  /**
   * Constructor
   * @param pNombreCompleto
   * @param pCarnet
   * @param pCarrera
   * @param pEmail
   * @param pTelefono 
   */
  public Estudiante(int pCarnet,String pNombreCompleto, String pCarrera, String pEmail, int pCalificacion, String pTelefono) {
    this.nombreCompleto = pNombreCompleto;
    this.carnet = pCarnet;
    this.carrera = pCarrera;
    this.email = pEmail;
    this.calificacion = pCalificacion;
    this.telefono = pTelefono;
  }

  
  public int getCalificacion() {
    return calificacion;
  }

  
  public String getNombreCompleto() {
    return nombreCompleto;
  }

  
  public int getCarnet() {
    return carnet;
  }

  
  public String getCarrera() {
    return carrera;
  }

  
  public String getEmail() {
    return email;
  }

  
  public String getTelefono() {
    return telefono;
  }   
  
}
