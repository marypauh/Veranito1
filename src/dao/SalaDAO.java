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
import logicadenegocios.Horario;
import logicadenegocios.Recurso;
import logicadenegocios.Sala;

/**
 *
 * @author María Paula
 */
public class SalaDAO {
  public boolean registrarSala(Sala sala){
  try{
    String estado = sala.getEstado();
    String identificador = sala.getIdentificador();
    int capacidad = sala.getCapacidadMax();
    String ubicacion = sala.getUbicacion();
    int calificacion = sala.getCalificacion();
    Connection conexion = Conexion.getConexion(); 
    String query = " execute dbo.createSala @identificador = ? ,"
        + "@ubicacion = ? ,@capacidadMax = ? ,@estado = ?,@calificacion = ?";
    CallableStatement consulta = conexion.prepareCall(query);
    consulta.setString(1, identificador);
    consulta.setString(2,ubicacion);
    consulta.setInt(3,capacidad);
    consulta.setString(4,estado);
    consulta.setInt(5,calificacion);
    consulta.execute();
    if( registrarSalaHorario(sala.horario, identificador) && registrarSalaRecurso(sala.recursos, identificador)){
      return true;
    }
  }catch(SQLException e){
    System.out.println(e);
    return false;
  }return false;
}
    
  public boolean registrarSalaHorario(Horario pHorario, String pIdentificador){
    boolean res = false; 
    try{
      int idHorario = pHorario.getIdHorario();
      String identificador = pIdentificador;
      Connection conexion = Conexion.getConexion(); 
      String query = "execute dbo.agregarSalaHorario @identificadorSala =?, @idHorario =?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, identificador);
      consulta.setInt(2,idHorario);
      consulta.execute();
      res = true;

    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
    
  public boolean registrarSalaRecurso(ArrayList<Recurso> pRecurso, String pIdentificador){
    boolean res = false;

    try{
      String identificador = pIdentificador;
      for(int i=0; i < pRecurso.size(); i++){
        Connection conexion = Conexion.getConexion(); 
        String nombre = pRecurso.get(i).getNombre();
        String query = "execute dbo.agregarSalaRecurso @identificadorSala = ? , @Recurso = ?";
        CallableStatement consulta = conexion.prepareCall(query);
        consulta.setString(1, identificador);
        consulta.setString(2,nombre);
        consulta.execute();
        res = true; 
      }
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
  
}
