/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import datos.estructuras.Par;
import frames.CourseRoom_Server_Frame;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Servidor_DB {
    
    private static Connection db_CourseRoom_Server_Conexion;
    
    private Servidor_DB(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db_CourseRoom_Server_Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseroom_server", "courseroom_server", "Q291cnNlUm9vbQ==");
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
    
    public static Servidor_DB getInstance() {
        return RespuestasHolder.INSTANCE;
    }
    
    private static class RespuestasHolder {

        private static final Servidor_DB INSTANCE = new Servidor_DB();
    }
    
    public ResultSet Obtener_Solicitudes() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerSolicitudes()}");
        return ejecutor.executeQuery();
    }
    
    public ResultSet Obtener_Respuestas() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerRespuestas()}");
        return ejecutor.executeQuery();
    }
  
    public Par<Integer, String> Agregar_Solicitud(String solicitud, String cliente, String ip){
        
        Integer codigo = -1;
        String mensaje = new String();
        try {
            
            try (CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_AgregarSolicitud(?,?,?)}")) {
                ejecutor.setString("_Solicitud", solicitud);
                ejecutor.setString("_Cliente", cliente);
                ejecutor.setString("_DireccionIP", ip);
                try (ResultSet resultado = ejecutor.executeQuery()) {
                    if(resultado != null){
                        while(resultado.next()){
                            codigo = resultado.getInt("Codigo");
                            mensaje = resultado.getString("Mensaje");
                            
                            CourseRoom_Server_Frame.Agregar_Solicitud(codigo.toString(), solicitud, cliente, ip);
                            
                            break;
                        }
                    }
                }
                
            }
            
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
        
        return new Par<>(codigo,mensaje);
    }
    
    public Par<Integer, String> Agregar_Respuesta(String respuesta, String cliente, String ip){
        
        Integer codigo = -1;
        String mensaje = new String();
        
        try {
            
            try (CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_AgregarRespuesta(?,?,?)}")) {
                ejecutor.setString("_Respuesta", respuesta);
                ejecutor.setString("_Cliente", cliente);
                ejecutor.setString("_DireccionIP", ip);
                try (ResultSet resultado = ejecutor.executeQuery()) {
                    if(resultado != null){
                        while(resultado.next()){
                            codigo = resultado.getInt("Codigo");
                            mensaje = resultado.getString("Mensaje");
                            
                            CourseRoom_Server_Frame.Agregar_Respuesta(codigo.toString(), respuesta, cliente, ip);
                            
                            break;
                        }
                    }
                }
            }
            
              
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
        
        return new Par<>(codigo, mensaje);
    }
    
    public void Cerrar_Conexion(){
        try {
            db_CourseRoom_Server_Conexion.close();
        } catch (SQLException ex) {
            
        }
           
    }
    
}
