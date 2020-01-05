/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  
  
  public ResultSet getRecursosSala(String pIdentificador){
    ResultSet res = null;
    try{
      String identificador = pIdentificador;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery("execute dbo.getRecursoSala '" + identificador +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
  
  
  public ResultSet getSala(String pIdentificador){
    ResultSet res = null;
    try{
      String identificador = pIdentificador;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery(" execute dbo.getSala '" + identificador +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
  
  public boolean updateSala(Sala sala){
    boolean res = false;
    String pIdentificador = sala.getIdentificador();
    String pUbicacion = sala.getUbicacion();
    String pEstado = sala.getEstado();
    try{
      Connection conexion = Conexion.getConexion();
      String query = "dbo.modificarSala @idSala = ?, @ubicacion = ? , @estado = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, pIdentificador);
      consulta.setString(2,pUbicacion);
      consulta.setString(3,pEstado);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }
  
  public boolean deleteRecurso(String pIdentificador, String pNombre){
    boolean res = false;
    try{
      Connection conexion = Conexion.getConexion();
      String query = "dbo.deleteRecurso  @idSala = ? , @nombreR = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, pIdentificador);
      consulta.setString(2,pNombre);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }
  
  
    
  public ResultSet recursosNotSala(String pIdentificador){
    ResultSet res = null;
    try{
      String identificador = pIdentificador;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery("execute dbo.getRecursoNotSala '" + identificador +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
  
  public boolean agregarRecurso(String pIdentificador, Recurso pRecurso){
    String pNombre = pRecurso.getNombre();
    boolean res = false;
    try{
      Connection conexion = Conexion.getConexion();
      String query = "execute dbo.agregarSalaRecurso  @identificadorSala = ?, @Recurso = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, pIdentificador);
      consulta.setString(2,pNombre);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }
  
  public ResultSet getHorario(String pIdentificador){
    ResultSet res = null;
    try{
      String identificador = pIdentificador;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery("execute dbo.getRecursoNotSala '" + identificador +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
  
  
  public boolean califarSala(String pIdentificador, int pNota){
    boolean res = false;
    try{
      Connection conexion = Conexion.getConexion();
      String query = "execute esquema.nuevaCalificacion5 @idSala = ? , @nota = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setString(1, pIdentificador);
      consulta.setInt(2,pNota);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }
  
 
  
}
