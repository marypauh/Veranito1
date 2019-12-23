package logicadenegocios;

/**
 *
 * @author Maria Paula 
 */
public class Estudiante {
  private String nombreCompleto;
  private int carnet;
  private String carrera;
  private String email;
  private int calificacion = 100;
  private String telefono;

  public Estudiante(String pNombreCompleto, int pCarnet, String pCarrera, String pEmail, String pTelefono) {
    this.nombreCompleto = pNombreCompleto;
    this.carnet = pCarnet;
    this.carrera = pCarrera;
    this.email = pEmail;
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
