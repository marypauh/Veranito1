package logicadenegocios;
import java.util.ArrayList; 
import java.util.Date;

/**
 * Clase de los objetos de tipo Reserva
 * 
 * @author Kevin Castillo, Ma Paula Rodriguez y Raquel Rojas
 * @version 1.0
 */
public class Reserva {
  private String estado = "activa";
  private Date fecha;
  private String horaInicio ;
  private String horaFin;
  private String codigoCalificacion;
  private String asunto;
  private int numero;
  private int organizador;
  private ArrayList<Participante> listaParticipantes;
  private String salaAsignada;
  public ArrayList<Incidente> listaIncidentes;

  public Reserva(Date pFecha, String pHoraInicio, String pHoraFin, String pCodigoCalificacion, String pAsunto, int pOrganizador, String pSalaAsignada) {
    this.fecha = pFecha;
    this.horaInicio = pHoraInicio;
    this.horaFin = pHoraFin;
    this.codigoCalificacion = pCodigoCalificacion;
    this.asunto = pAsunto;
    this.organizador = pOrganizador;
    this.salaAsignada = pSalaAsignada;
  }

  public Reserva() {}
  
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

  public int getOrganizador() {
    return organizador;
  }

  public void setOrganizador(int pOrganizador) {
    this.organizador = pOrganizador;
  }

  public ArrayList<Participante> getListaParticipantes() {
    return listaParticipantes;
  }

  public void setListaParticipantes(ArrayList<Participante> pListaParticipantes) {
    this.listaParticipantes = pListaParticipantes;
  }

  public String getSalaAsignada() {
    return salaAsignada;
  }

  public void setSalaAsignada(String pSalaAsignada) {
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
