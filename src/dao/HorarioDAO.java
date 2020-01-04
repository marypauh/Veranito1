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
import logicadenegocios.Horario;

/**
 *
 * @author María Paula
 */
public class HorarioDAO {

  public ResultSet getHorarios(){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from esquema.Horario");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  public ResultSet getHorarioID(int pId){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from esquema.Horario where idHorario = " + pId );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
  public ResultSet getHorarioSala(String pIdSala){
    ResultSet res = null;
    try{
      String idSala = pIdSala;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery("dbo.getHorarioSala '" + idSala +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
}
 
