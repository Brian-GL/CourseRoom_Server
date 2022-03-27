/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package clases;

import com.mysql.cj.jdbc.Blob;
import datos.estructuras.Par;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Vector;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LENOVO
 */
public class Stored_Procedures {
    
    private Connection db_CourseRoom_Conexion;
    
    private Stored_Procedures() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db_CourseRoom_Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseroom", "courseroom_server", "Q291cnNlUm9vbQ==");
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
    
    public static Stored_Procedures getInstance() {
        return StoredProceduresHolder.INSTANCE;
    }
    
    private static class StoredProceduresHolder {

        private static final Stored_Procedures INSTANCE = new Stored_Procedures();
    }
    
    public Vector<String> sp_ObtenerEstados() throws SQLException{
        Vector<String> response = new Vector<>();
        byte[] bytes;
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerEstados()}")){
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        bytes = resultado.getString("Estado").getBytes();
                        bytes = Base64.getEncoder().encode(bytes);
                        codificacion = new String(bytes);
                        response.add(codificacion);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerLocalidadesPorEstado(String estado) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        byte[] bytes;
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerLocalidadesPorEstado(?)}")){
            ejecutor.setString("_Estado", estado);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdLocalidad"));
                        bytes = resultado.getString("Localidad").getBytes();
                        bytes = Base64.getEncoder().encode(bytes);
                        codificacion = new String(bytes);
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
    }
    
    public Par<Integer, String> sp_AgregarUsuario(String correo_Electronico,String contrasenia ,String nombre,
    String paterno,String materno, int id_Localidad, String genero,String fecha_Nacimiento, String tipo_Usuario,
    byte[] Imagen, double promedio_General,String descripcion) throws SQLException, IOException{
        
        Par<Integer, String> par = new Par<>(-1,"Error");
        
        byte[] bytes;
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarUsuario(?,?,?,?,?,?,?,?,?,?,?,?)}")){
            ejecutor.setString("_CorreoElectronico", correo_Electronico);
            ejecutor.setString("_Contrasenia", contrasenia);
            ejecutor.setString("_Nombre", nombre);
            ejecutor.setString("_Paterno", paterno);
            ejecutor.setString("_Materno", materno);
            ejecutor.setInt("_IdLocalidad", id_Localidad);
            ejecutor.setString("_Genero", genero);
            ejecutor.setString("_FechaNacimiento", fecha_Nacimiento);
            ejecutor.setString("_TipoUsuario", tipo_Usuario);
            Blob blob = new Blob(Imagen,null);
            ejecutor.setBlob("_Imagen", blob);
            ejecutor.setDouble("_PromedioGeneral", promedio_General);
            ejecutor.setString("_Descripcion", descripcion);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        bytes = resultado.getString("Mensaje").getBytes();
                        bytes = Base64.getEncoder().encode(bytes);
                        codificacion = new String(bytes);
                        par.second(codificacion);
                        par.first(resultado.getInt("Codigo"));
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        return par;
    } 
    
    public void Cerrar_Conexion(){
        try {
            db_CourseRoom_Conexion.close();
        } catch (SQLException ex) {
            
        }
    }
}
