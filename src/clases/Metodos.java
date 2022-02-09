/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Metodos {
    
    private final ConexionMySQL conexionMySQL;
    
    public Metodos() throws ClassNotFoundException, SQLException{
        conexionMySQL = new ConexionMySQL();
    }
    
    public String Mensaje_Inicial(){
        return "Hola Desde El Servidor!";
    }
    
    public ResultSet Sp_ObtenerSolicitudes() throws SQLException{
        return conexionMySQL.Sp_ObtenerSolicitudes();
    }

    public ResultSet Sp_ObtenerRespuestas() throws SQLException {
        return conexionMySQL.Sp_ObtenerRespuestas();
    }

    public ResultSet Sp_ObtenerMetodos() throws SQLException {
        return conexionMySQL.Sp_ObtenerMetodos();
    }

    public ResultSet Sp_ObtenerTablasCourseRoom() throws SQLException {
        return conexionMySQL.Sp_ObtenerTablasCourseRoom();
    }

    public void Cerrar_Conexion() throws SQLException {
        conexionMySQL.Cerrar_Conexion();
    }
    
    
    
}
