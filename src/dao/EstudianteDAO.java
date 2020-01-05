package dao;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logicadenegocios.Estudiante;

/**
 *
 * @author María Paula
 */
public class EstudianteDAO {
    
  public static Connection conexion; 
  
  
  /**
   * Metodo donde se llama el procedimiento para agregar el estudiante
   * @param estudiante
   * @return
   * @throws SQLException 
   */
  public Estudiante agregarEstudiante(Estudiante estudiante) throws SQLException{
    if(storeProcedureAgregarEstudiante(estudiante.getCarnet(),estudiante.getNombreCompleto(),estudiante.getCarrera(),
      estudiante.getEmail(),estudiante.getCalificacion(),estudiante.getTelefono())>0){
        
      return estudiante;
    } else{
      return null;
    }
  }
  
  

  public int storeProcedureAgregarEstudiante(int pCarnet,String pNombreCompleto,String pCarrera,
    String pEmail, int pCalificacion ,String pTelefono) throws SQLException{
      
    CallableStatement cstmt = null;
    int rs = 0;
    Conexion cn = new Conexion();
    conexion = cn.getConexion();
    cstmt = conexion.prepareCall("{call dbo.agregarEstudiante(?,?,?,?,?,?)}");
    cstmt.setInt(1,pCarnet);
    cstmt.setString(2,pNombreCompleto);
    cstmt.setString(3,pCarrera);
    cstmt.setString(4,pEmail);
    cstmt.setInt(5,100);
    cstmt.setString(6,pTelefono);
    //se utiliza executeUpdate porque retorna un 1 si se inserto el objeto, 0 en caso contrario
    rs = cstmt.executeUpdate();
    System.out.println(rs);
    return rs;
  }
  
  public ResultSet storeProcedureConsultarEstudiante(int pCarnet){
    ResultSet rs = null;
    try{
      conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("{call dbo.consultarEstudiante(pCarnet)}");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  public ResultSet getReservasEstudiante(int pCarnet){
    ResultSet rs = null;
    try{
      conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select * from esquema.Reserva where organizador = " + pCarnet );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
   public ResultSet getIncidentesReserva(int pIdReserva){
    ResultSet rs = null;
    try{
      conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select numIncidente,detalle,valor,fecha from esquema.Incidente, esquema.IncidenteReserva where idIncidente = numIncidente and idReserva = " + pIdReserva );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
}
