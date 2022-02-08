/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import courseroom_server.CourseRoom_Server;
import java.sql.ResultSet;

/**
 *
 * @author LENOVO
 */
public class Metodos {
    
    private ConexionMySQL conexionMySQL;
    

    public Metodos(){
        conexionMySQL = new ConexionMySQL();
    }
    
    public String getSaludo(String name, String sentence){
        return CourseRoom_Server.Concatenar(name, " - ",sentence);
    }
    
    public ResultSet sp_ObtenerSolicitudes(){
        return conexionMySQL.sp_ObtenerSolicitudes();
    }

    public ResultSet sp_ObtenerRespuestas() {
        return conexionMySQL.sp_ObtenerRespuestas();
    }

    public ResultSet sp_ObtenerMetodos() {
        return conexionMySQL.sp_ObtenerMetodos();
    }

    public ResultSet sp_ObtenerTablasCourseRoom() {
        return conexionMySQL.sp_ObtenerTablasCourseRoom();
    }

    public void Cerrar_Conexion() {
        conexionMySQL.Cerrar_Conexion();
    }
    
    
    
}
