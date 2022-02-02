/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Dictionary;
import java.util.Enumeration;


/**
 *
 * @author LENOVO
 */
public class ConexionMySQL {
    
    private static Connection conexion;
    
    public ConexionMySQL(){
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            byte[] decoded = Base64.getDecoder().decode("QmgrMzMxMDcxMjAyMA==");
            String decodificacion = new String(decoded);
            ConexionMySQL.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseroom_server", "root", decodificacion);
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
    
    public static ResultSet sp_ObtenerSolicitudes(){
        
        try {
            CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerSolicitudes()}");
            return ejecutor.executeQuery();
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
    public static ResultSet sp_ObtenerRespuestas(){
        
          try {
            CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerRespuestas()}");
            return ejecutor.executeQuery();
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
    public static ResultSet sp_ObtenerMetodos(){
        
        try {
            CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerMetodos()}");
            return ejecutor.executeQuery();
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
    public static ResultSet sp_ObtenerTablasCourseRoom(){
        
        try {
            CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerTablasCourseRoom()}");
            return ejecutor.executeQuery();
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
//    public static Dictionary<Boolean, String> sp_AgregarSolicitud(String solicitud, String cliente, String fecha_Solicitud){
//        try {
//            
//            CallableStatement ejecutor = conexion.prepareCall("{CALL sp_AgregarSolicitud('?,?,?)}");
//            
//            ejecutor.setString("Solicitud", solicitud);
//            ejecutor.setString("Cliente", cliente);
//            ejecutor.setString("Fecha_Solicitud", fecha_Solicitud);
//            
//            
//              
//        } catch (SQLException ex) {
//            
//        }
//        
//        
//    }
    
    public static void Cerrar_Conexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            
        }
    }
}
