/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import datos.estructuras.Par;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;


/**
 *
 * @author LENOVO
 */
public class DB_CourseRoom_Server {
    
    private final Connection conexion;
    
    public DB_CourseRoom_Server() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        byte[] decoded = Base64.getDecoder().decode("QmgrMzMxMDcxMjAyMA==");
        String decodificacion = new String(decoded);
        this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseroom_server", "root", decodificacion);
    }
    
    public ResultSet Sp_ObtenerSolicitudes() throws SQLException{
        CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerSolicitudes()}");
        return ejecutor.executeQuery();
    }
    
    public ResultSet Sp_ObtenerRespuestas() throws SQLException{
        CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerRespuestas()}");
        return ejecutor.executeQuery();
    }
    
    public ResultSet Sp_ObtenerMetodos() throws SQLException{
        CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerMetodos()}");
        return ejecutor.executeQuery();
    }
    
    public ResultSet Sp_ObtenerTablasCourseRoom() throws SQLException{
        CallableStatement ejecutor = conexion.prepareCall("{CALL sp_ObtenerTablasCourseRoom()}");
        return ejecutor.executeQuery();
    }
    
    public Par<Boolean, String> sp_AgregarSolicitud(String solicitud, String cliente, String fecha_Solicitud){
        try {
            
            Boolean codigo;
            String mensaje;
            try (CallableStatement ejecutor = conexion.prepareCall("{CALL sp_AgregarSolicitud('?,?,?)}")) {
                ejecutor.setString("Solicitud", solicitud);
                ejecutor.setString("Cliente", cliente);
                ejecutor.setString("Fecha_Solicitud", fecha_Solicitud);
                ejecutor.execute();
                codigo = ejecutor.getBoolean(0);
                mensaje = ejecutor.getString(1);
            }
            return new Par<>(codigo, mensaje);
              
        } catch (SQLException ex) {
            
        }
        
        return new Par<>(Boolean.FALSE,"Error en MySQL");
    }
    
    public void Cerrar_Conexion() throws SQLException{
        conexion.close();
    }
}
