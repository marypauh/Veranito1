/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author María Paula
 */
public class RecursoDAO {
  public ResultSet getRecursos(){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from esquema.Recurso");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  public ResultSet getRecursoID(String pNombre){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from esquema.Recurso where nombre = " + pNombre );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
}
   
