/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
  
  public int agregarParticipantes(ArrayList<Participante> PlistaParticipantes,int idReserva,int pCapacidadMax) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    int contador = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarParticipantes(?,?,?)}");
    if(PlistaParticipantes.size()<=pCapacidadMax){
      while(PlistaParticipantes.size()-1>=contador){
        cstmt.setInt(1,idReserva);
        cstmt.setString(2,PlistaParticipantes.get(contador).getNombre());
        cstmt.setString(3,PlistaParticipantes.get(contador).getEmail());
        rs = cstmt.executeUpdate();
        contador++;
      }
      return contador+1;
    } else {
      return 0;
    }
  }
  
  public void enviarCorreoParticipantes(ArrayList<Participante> pListaParticipantes,String pIdSala, String pHoraInicio, String pHoraFin,Date pFecha){
    int contador = 0;
    String msg = "Identificador de la sala: " + pIdSala + "\nFecha: " +pFecha.toString()+"\nHora de inicio: "+pHoraInicio+"\nHora de finalización: "+pHoraFin;
    while(pListaParticipantes.size()-1>=contador){
      EnviarCorreo.enviarCorreo(pListaParticipantes.get(contador).getEmail(),"BiblioTEC - Invitación a reserva de sala",msg);
      contador++;
    }
  }
}
