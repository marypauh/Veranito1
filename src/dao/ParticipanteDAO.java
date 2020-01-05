
package dao;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import logicadenegocios.Participante;
import util.EnviarCorreo;

/**
 *
 * @author María Paula
 */
public class ParticipanteDAO {
  public static Connection conexion; 
  
  /**
   * Metodo para agregar participantes a la reserva
   * @param PlistaParticipantes
   * @param idReserva
   * @param pCapacidadMax
   * @return
   * @throws SQLException 
   */
  public void agregarParticipantesReserva(ArrayList<Participante> PlistaParticipantes,int idReserva) throws SQLException{
    CallableStatement cstmt = null;
    int contador = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarParticipantesReserva(?,?,?)}");
    while(PlistaParticipantes.size()-1>=contador){
        cstmt.setInt(1,idReserva);
        cstmt.setString(2,PlistaParticipantes.get(contador).getNombre());
        cstmt.setString(3,PlistaParticipantes.get(contador).getEmail());
        cstmt.executeUpdate();
        contador++;
    }
  }
  
  public void agregarParticipantes(ArrayList<Participante> PlistaParticipantes) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    int contador = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarParticipantes(?,?)}");
    while(PlistaParticipantes.size()-1>=contador){
        cstmt.setString(1,PlistaParticipantes.get(contador).getNombre());
        cstmt.setString(2,PlistaParticipantes.get(contador).getEmail());
        cstmt.executeUpdate();
        contador++;
    }
  }
  
  /**
   * Metodo para enviar correo a los participantes de la reserva
   * @param pListaParticipantes
   * @param pIdSala
   * @param pHoraInicio
   * @param pHoraFin
   * @param pFecha 
   */
  public void enviarCorreoParticipantes(ArrayList<Participante> pListaParticipantes,String pIdSala, String pHoraInicio, String pHoraFin,Date pFecha){
    int contador = 0;
    String msg = "Identificador de la sala: " + pIdSala + "\nFecha: " +pFecha.toString()+"\nHora de inicio: "+pHoraInicio+"\nHora de finalización: "+pHoraFin;
    while(pListaParticipantes.size()-1>=contador){
      EnviarCorreo.enviarCorreo(pListaParticipantes.get(contador).getEmail(),"BiblioTEC - Invitación a reserva de sala",msg);
      contador++;
    }
  }
}
