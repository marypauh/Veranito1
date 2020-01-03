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
import java.text.SimpleDateFormat;
import java.util.Date;
import logicadenegocios.Reserva;

/**
 *
 * @author María Paula
 */
public class ReservaDAO {
  public static Connection conexion; 
  
  
  public Reserva agregarReserva(Reserva reserva) throws SQLException{
    if(storeProcedureAgregarReserva(reserva.getEstado(),reserva.getFecha(),reserva.getHoraInicio(), reserva.getHoraFin(),reserva.getCodigoCalificacion(),reserva.getAsunto(),0,reserva.getSalaAsignada())>0){
      return reserva;    
    }
    else{
      return null;   
    }
  }
  
  
  public ResultSet consultarSalas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call esquema.consultarSalas}");
    return rs;
  }
  
  public int storeProcedureAgregarReserva(String pEstado ,Date pFecha,String pHoraInicio,
    String pHoraFin,String pCodigoCalificacion,String pAsunto,int pOrganizador,String pIdSala) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.agregarReserva(?,?,?,?,?,?,?,?)}");
    cstmt.setString(1,pEstado);
    java.sql.Date fecha = new java.sql.Date(pFecha.getTime());
    cstmt.setDate(2,fecha);
    cstmt.setString(3,pHoraInicio);
    cstmt.setString(4,pHoraFin);
    cstmt.setString(5,pCodigoCalificacion);
    cstmt.setString(6,pAsunto);
    cstmt.setInt(7,pOrganizador);
    cstmt.setString(8,pIdSala);
    //se utiliza executeUpdate porque retorna un 1 si se inserto el objeto, 0 en caso contrario
    rs = cstmt.executeUpdate();
    return rs;
  }
  }  
