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
import logicadenegocios.Participante;

/**
 *
 * @author María Paula
 */
public class ParticipanteDAO {
  public static Connection conexion; 
  
  public int agregarParticipantes(ArrayList<Participante> PlistaDeParticipantes,int idReserva,int pCapacidadMax) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    int contador = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarParticipantes(?,?,?)}");
    if(PlistaDeParticipantes.size()<=pCapacidadMax){
      while(PlistaDeParticipantes.size()-1>=contador){
        cstmt.setInt(1,idReserva);
        cstmt.setString(2,PlistaDeParticipantes.get(contador).getNombre());
        cstmt.setString(3,PlistaDeParticipantes.get(contador).getEmail());
        rs = cstmt.executeUpdate();
        contador++;
      }
      return contador+1;
    } else {
      return 0;
    }
  }    
}
