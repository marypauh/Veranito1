/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import static dao.IncidenteDAO.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import logicadenegocios.Participante;
import logicadenegocios.Reserva;
import util.EnviarCorreo;
import util.EnviarSMS;

/**
 *
 * @author María Paula
 */
public class ReservaDAO {
  public static Connection conexion; 
  
  
  /**
   * Metodo para agregar reserva
   * @param reserva
   * @return reserva
   * @throws SQLException 
   */
  public Reserva agregarReserva(Reserva reserva) throws SQLException{
    if(storeProcedureAgregarReserva(reserva.getEstado(),reserva.getFecha(),reserva.getHoraInicio(), reserva.getHoraFin(),reserva.getCodigoCalificacion(),reserva.getAsunto(),reserva.getOrganizador(),reserva.getSalaAsignada())>0){
      return reserva;    
    }
    else{
      return null;   
    }
  }
  
  
  /**
   * Metodo para la consulta de la sala
     * @param pCapacidadMin
     * @param pNombreRecurso
   * @return salas
   * @throws SQLException 
   */
  public ResultSet consultarSalas(int pCapacidadMin,String pNombreRecurso) throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    if("".equals(pNombreRecurso)){
      CallableStatement cstmt = conexion.prepareCall("{call esquema.consultarSalas(?)}");
    cstmt.setInt(1, pCapacidadMin);
    rs = cstmt.executeQuery();
    return rs;  
    } else {
      CallableStatement cstmt = conexion.prepareCall("{call esquema.verificarRecursoSala(?,?)}");
      cstmt.setString(1, pNombreRecurso);
      cstmt.setInt(2, pCapacidadMin);
      rs = cstmt.executeQuery();
      return rs;
    }
  }
  
  
  /**
   * Metodo que ejecuta el procedimiento almacenado para agregar la reserva
   * @param pEstado
   * @param pFecha
   * @param pHoraInicio
   * @param pHoraFin
   * @param pCodigoCalificacion
   * @param pAsunto
   * @param pOrganizador
   * @param pIdSala
   * @return 1 si se inserto correctamente, 0 si no
   * @throws SQLException 
   */
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
  
  
  /**
   * Metodo para obtener el identificador de la reserva
   * @return idReserva
   * @throws SQLException 
   */
  public int obtenerIdReserva() throws SQLException{
    ResultSet rs = null;
    int idReserva =0;
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerIdReserva()}");
    rs = cstmt.executeQuery();
    while(rs.next()){
      idReserva = rs.getInt(1);
    }
    return idReserva+1;
  }
  
  
  /**
   * Metodo para obtener las proximas reservas
   * @param pIdentificador
   * @return reservas
   * @throws SQLException 
   */
  public ResultSet getProxReservasSala(String pIdentificador) throws SQLException{
    ResultSet res = null;
    String identificador = pIdentificador;
    Connection conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    res = ejecutor.executeQuery("execute dbo.getProxReservas '" + identificador +"'");
    return res;
  }
  
  
  /**
   * Metodo para consultar las reservas
   * @return reservas
   * @throws SQLException 
   */
  public ResultSet consultarReservas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call esquema.consultarReservas}");
    return rs;
  }
  
  
  /**
   * Metodo para cancelar una reserva
   * @param pNumero
   * @return
   * @throws SQLException 
   */
  public int cancelarReserva(int pNumero) throws SQLException{
    int rs = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.cancelarReserva(?)}");
    cstmt.setInt(1, pNumero);
    rs = cstmt.executeUpdate();
    return rs;
  }
  
 
   /**
   * Metodo para verificar el código para calificar una sala y obtener el idSala de la reserva, si existiese. 
   * 
   * @param pCodigo código a validar
   * @return ResultSet con el idSala de la reserva a la que corresponde el codigo
   */ 
   public ResultSet verificarCodigo(String pCodigo) throws SQLException{
    ResultSet res = null;
    Connection conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    res = ejecutor.executeQuery("execute esquema.codigoUsado '" + pCodigo +"'");
    return res;
  }
  
   
     /**
   * Metodo para utilizar el código al calificar la sala
   * 
   * @param pReserva el número de reserva en donde se utilizará el código
   * @return True si se logra con éxito, False en caso contrario
   */ 
  public boolean usarCodigo(int pReserva){
    boolean res = false;
    try{
      Connection conexion = Conexion.getConexion();
      String query = "Execute esquema.usarCodigo @idReserva = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,pReserva);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }

  
   /**
   * Metodo que ejecuta el procedimiento para obtener el correo de los participantes
   * 
   * @param 
   * @return 
   */ 
  public void notificarParticipantes(int pNumero,String pIdSala) throws SQLException{
    ResultSet rs = null;
    String msg = "Ha sido cancelada la reserva de la sala: "+pIdSala;
    String asunto = "Notificación de cancelacion de reserva";
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerEmailParticipantes(?)}");
    cstmt.setInt(1, pNumero);
    rs = cstmt.executeQuery();
    while(rs.next()){
      EnviarCorreo.enviarCorreo(rs.getString("email"),asunto,msg);
    }
  }
  
  
  /**
   * Metodo que obtiene el correo del organizador
   * @param pOrganizador
   * @param pIdSala
   * @throws SQLException 
   */
  public void notificarOrganizador(int pOrganizador,String pIdSala) throws SQLException{
    ResultSet rs = null;
    String msg = "Ha sido cancelada la reserva de la sala: "+pIdSala;
    String asunto = "Notificación de cancelacion de reserva";
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerCorreoEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      EnviarCorreo.enviarCorreo(rs.getString("email"),asunto,msg);
    }
  }
  
  public ResultSet consultarReservasValidas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call esquema.comprobarFechaReservas}");
    return rs;
  }
  
  public void bajarCalificacionEstudiante(int pOrganizador, int valor) throws SQLException{
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.bajarCalificacionEstudiante(?,?)}");
    cstmt.setInt(1,pOrganizador);
    cstmt.setInt(2,valor);
    cstmt.executeUpdate();
  }
  
  public int comprobarCalificacionEstudiante(int pOrganizador) throws SQLException{
    ResultSet rs = null;
    int calificacion = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerCalificacionEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      calificacion = rs.getInt("calificacion");
    }
    return calificacion;
  }
  
  public int comprobarCantidadReservas(Date pFecha, int pOrganizador) throws SQLException{
    ResultSet rs = null;
    int contador = 0;
    int numSemana = obtenerNumSemana(pFecha);
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerFechaReserva(?)}");
    cstmt.setInt(1,pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      if(obtenerNumSemana(pFecha)==numSemana){
        contador++;
      }
    }
    return contador;
  }
  
  
  public int obtenerNumSemana(Date pFecha) throws SQLException{
    ResultSet rs = null;
    int numSemana = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerNumSemana(?)}");
    java.sql.Date fecha = new java.sql.Date(pFecha.getTime());
    cstmt.setDate(1,fecha);
    rs = cstmt.executeQuery();
    while(rs.next()){
      numSemana = rs.getInt("numSemana");
    }
    return numSemana;
  }
  
  public int existeEstudiante(int pOrganizador) throws SQLException{
    CallableStatement cstmt = null;
    ResultSet rs = null;
    int resultado =0;
    Conexion cn = new Conexion();
    conexion = cn.getConexion();
    cstmt = conexion.prepareCall("{call esquema.existeEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    cstmt.executeQuery();
    rs = cstmt.getResultSet();
    if(rs.next()){
      resultado = 1;
    } else{
      resultado = 0;
    }
    return resultado;
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
  
  public void notificarOrganizador(String pCorreo,String pIdSala, String pHoraInicio, String pHoraFin,Date pFecha){
    String msg = "Identificador de la sala: " + pIdSala + "\nFecha: " +pFecha.toString()+"\nHora de inicio: "+pHoraInicio+"\nHora de finalización: "+pHoraFin;
      EnviarCorreo.enviarCorreo(pCorreo,"BiblioTEC - Invitación a reserva de sala",msg);
  }
  
  public void notificarOrganizadorSMS(String pTelefono,String pMensaje){
    EnviarSMS.enviarSMS(pTelefono, pMensaje);
  }
  
  public String obtenerTelefonoEstudiante(int pOrganizador) throws SQLException{
    String telefono = "";
    CallableStatement cstmt = null;
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call esquema.obtenerTelefonoEstudiante(?)}"); 
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      telefono = rs.getString(1);
    }
    return telefono;
  }
  
  
  
}  
