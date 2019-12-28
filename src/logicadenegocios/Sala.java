package logicadenegocios;

import java.util.ArrayList;

/**
 *
 * @author María Paula
 */
public class Sala {
  private String identificador;
  private String ubicacion;
  private int capacidadMax;
  private int calificacion = 100;
  private String estado = "inactiva";
  public ArrayList<Recurso> recursos = new  ArrayList<Recurso>();
  private ArrayList<HorarioExcepcion> horariosExcepcion = new ArrayList<HorarioExcepcion>();
  public Horario horario;
  

public Sala(String pID, String pUbicacion, int pCapacidad){
  identificador = pID;
  ubicacion = pUbicacion;
  capacidadMax = pCapacidad;
}
public Sala(){
}


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

  
  public int getCalificacion() {
    return calificacion;
  }

  
  public void setCalificacion(int pCalificacion) {
    this.calificacion = pCalificacion;
  }

  
  public String getEstado() {
    return estado;
  }

  
  public void setEstado(String estado) {
    this.estado = estado;
  }

  
  public ArrayList<Recurso> getRecursos() {
    return recursos;
  }

  
  public void setRecursos(ArrayList<Recurso> recursos) {
    this.recursos = recursos;
  }

  
  public ArrayList<HorarioExcepcion> getHorariosExcepcion() {
    return horariosExcepcion;
  }

  
  public void setHorariosExcepcion(ArrayList<HorarioExcepcion> horariosExcepcion) {
    this.horariosExcepcion = horariosExcepcion;
  }

  
  public Horario getHorario() {
    return horario;
  }

  
  public void setHorario(Horario horario) {
     this.horario = horario;
  }  
  
  public boolean addRecurso(Recurso pRecurso) {
    ArrayList<Recurso> recursos = this.getRecursos();
    if ( recursos.add(pRecurso)){
      this.setRecursos(recursos);
      return true;
    }else{
      return true;
    }
  } 
}
