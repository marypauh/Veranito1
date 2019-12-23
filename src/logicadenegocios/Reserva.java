/**
 * Clase de los objetos de tipo Reserva
 * 
 * @author Kevin Castillo, Ma Paula Rodriguez y Raquel Rojas
 * @version 1.0
 */
package logicadenegocios;

import java.util.ArrayList; import java.util.Date;

public class Reserva {
  private String estado = "activa";
  private Date fecha;
  private String horaInicio ;
  private String horaFin;
  private String codigoCalificacion;
  private String asunto;
  private int numero;
  private Estudiante organizador;
  private ArrayList<Participante> listaParticipantes;
  private Sala salaAsignada;
  public ArrayList<Incidente> listaIncidentes;

  public String getEstado() {
    return estado;
  }

  public void setEstado(String pEstado) {
    this.estado = pEstado;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

   
  public String getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(String pHoraInicio) {
    this.horaInicio = pHoraInicio;
  }

  public String getHoraFin() {
    return horaFin;
  }

  public void setHoraFin(String pHoraFin) {
    this.horaFin = pHoraFin;
  }

  public String getCodigoCalificacion() {
    return codigoCalificacion;
  }

  public void setCodigoCalificacion(String pCodigoCalificacion) {
    this.codigoCalificacion = pCodigoCalificacion;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String pAsunto) {
    this.asunto = pAsunto;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int pNumero) {
    this.numero = pNumero;
  }

  public Estudiante getOrganizador() {
    return organizador;
  }

  public void setOrganizador(Estudiante pOrganizador) {
    this.organizador = pOrganizador;
  }

  public ArrayList<Participante> getListaParticipantes() {
    return listaParticipantes;
  }

  public void setListaParticipantes(ArrayList<Participante> pListaParticipantes) {
    this.listaParticipantes = pListaParticipantes;
  }

  public Sala getSalaAsignada() {
    return salaAsignada;
  }

  public void setSalaAsignada(Sala pSalaAsignada) {
    this.salaAsignada = pSalaAsignada;
  }

  public ArrayList<Incidente> getListaIncidentes() {
    return listaIncidentes;
  }

  public void setListaIncidentes(ArrayList<Incidente> pListaIncidentes) {
    this.listaIncidentes = pListaIncidentes;
  }
  
  
  @Override
  public String toString(){
    return "";
  }
}
