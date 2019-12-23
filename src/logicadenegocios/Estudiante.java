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

  public Estudiante(String nombreCompleto, int carnet, String carrera, String email, String telefono) {
    this.nombreCompleto = nombreCompleto;
    this.carnet = carnet;
    this.carrera = carrera;
    this.email = email;
    this.telefono = telefono;
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
