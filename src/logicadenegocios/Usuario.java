package logicadenegocios;

/**
 *
 * @author Maria Paula Rodriguez y Kevin Castillo
 */
public class Usuario {
    
  private String nombreUsuario;
  private String contraseña;

  
  /**
   * Constructor
   */
  public Usuario() {}
  
  
  /**
   * Constructor
   * @param pNombre
   * @param pContraseña 
   */
  public Usuario(String pNombre, String pContraseña) {
    this.nombreUsuario = pNombre;
    this.contraseña = pContraseña;
  }  

  
  public String getNombreUsuario() {      
    return nombreUsuario;
  }

  
  public void setNombreUsuario(String pNombreUsuario) {
    this.nombreUsuario = pNombreUsuario;
  }

  
  public String getContraseña() {
    return contraseña;
  }

  
  public void setContraseña(String pContraseña) {    
    this.contraseña = pContraseña;
  }
  
}
