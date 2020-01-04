/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import static dao.ParticipanteDAO.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import logicadenegocios.Incidente;
import util.EnviarCorreo;
import dao.EstudianteDAO;

/**
 *
 * @author María Paula
 */
public class IncidenteDAO {
  public static Connection conexion;
  
  public ResultSet consultarReservas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call esquema.consultarReservas}");
    return rs;
  }
  
  public Incidente agregarIncidente(Incidente pIncidente) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarIncidente(?,?,?)}");
    cstmt.setString(1,pIncidente.getDetalle());
    cstmt.setInt(2,pIncidente.getValor());
    java.sql.Date fecha = new java.sql.Date(pIncidente.getFecha().getTime());
    cstmt.setDate(3,fecha);
    rs = cstmt.executeUpdate();
    if(rs>0){
      return pIncidente;
    } else {
      return null;
    }
  }
  
  public void enviarCorreoOrganizador(String pIdSala, Date pFecha, String pAsunto, int pOrganizador) throws SQLException{
    String msg = "Identificador de la sala: " + pIdSala + "\nFecha: " +pFecha.toString()+"\nDetalle del incidente: "+pAsunto;
    String email = obtenerCorreoEstudiante(pOrganizador);
    EnviarCorreo.enviarCorreo(email,"BiblioTEC - Notificacion de incidente",msg);
  }
  
  public String obtenerCorreoEstudiante(int pOrganizador) throws SQLException{
    String correo = "";
    CallableStatement cstmt = null;
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerCorreoEstudiante(?)}"); 
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      correo = rs.getString(1);
    }
    return correo;
  }
  
  public void agregarIncidenteReserva(int pNumero) throws SQLException{
    ResultSet rs = null;
    int idIncidente =0;
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarIncidenteReserva(?,?)}");
    cstmt.setInt(1, pNumero);
    cstmt.setInt(2, obtenerIdIncidente());
    cstmt.executeQuery();
  }  
  
  public int obtenerIdIncidente() throws SQLException{
    ResultSet rs = null;
    int idIncidente =0;
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerIncidenteId()}");
    rs = cstmt.executeQuery();
    while(rs.next()){
      idIncidente= rs.getInt(1);
    }
    return idIncidente;
  }
  
  public void bajarCalificacionEstudiante(int pOrganizador, int valor) throws SQLException{
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.bajarCalificacionEstudiante(?)}");
    cstmt.setInt(1,pOrganizador);
    cstmt.setInt(2,valor);
    cstmt.executeUpdate();
  }
}
  
  
 
