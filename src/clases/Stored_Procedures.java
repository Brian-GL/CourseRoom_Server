/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package clases;

import com.mysql.cj.jdbc.Blob;
import java.io.IOException;
import java.io.InputStream;
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
    
    public String Codificacion(String decodificacion){

        byte[] bytes = decodificacion.getBytes();
        bytes = Base64.getEncoder().encode(bytes);
        return new String(bytes);
    }
    
    public Vector<String> sp_ObtenerEstados() throws SQLException{
        Vector<String> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerEstados()}")){
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Estado"));
                        response.add(codificacion);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerLocalidadesPorEstado(String estado) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerLocalidadesPorEstado(?)}")){
            ejecutor.setString("_Estado", estado);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdLocalidad"));
                        codificacion = Codificacion(resultado.getString("Localidad"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
        
    }
    
    public Vector<Object> sp_AgregarUsuario(String correo_Electronico,String contrasenia ,String nombre,
    String paterno,String materno, int id_Localidad, String genero,String fecha_Nacimiento, String tipo_Usuario,
    byte[] Imagen, double promedio_General,String descripcion) throws SQLException, IOException{
        
        Vector<Object> respuesta = new Vector<>();
        Blob blob = new Blob(Imagen,null);
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
            ejecutor.setBlob("_Imagen", blob);
            ejecutor.setDouble("_PromedioGeneral", promedio_General);
            ejecutor.setString("_Descripcion", descripcion);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public String sp_ObtenerCredenciales(String correo_Electronico) throws SQLException{
        String respuesta = new String();
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCredenciales(?)}")){
            ejecutor.setString("_CorreoElectronico", correo_Electronico);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        respuesta = resultado.getString("Contrasenia");
                        break;
                    }
                }
            }
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerUsuario(String correo_Electronico,String contrasenia){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerUsuario(?,?)}")){
            ejecutor.setString("_CorreoElectronico", correo_Electronico);
            ejecutor.setString("_Contrasenia", contrasenia);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_AgregarSesion(int id_Usuario, String dispositivo, String fabricante,
    String uuid, String ip){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarSesion(?,?,?,?,?)}")){
                ejecutor.setInt("_IdUsuario",id_Usuario);
                ejecutor.setString("_Dispositivo",dispositivo);
                ejecutor.setString("_Fabricante",fabricante);
                ejecutor.setString("_Uuid",uuid);
                ejecutor.setString("_DireccionIP",ip);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosPerfil(int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosPerfil(?)}")){
                ejecutor.setInt("_IdUsuario",id_Usuario);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Paterno"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Materno"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("CorreoElectronico"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Genero"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("TipoUsuario"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaNacimiento"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Localidad"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Estado"));
                        respuesta.add(codificacion);
                        
                        respuesta.add(resultado.getDouble("PromedioGeneral"));
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Contrasenia"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            
        }
        
        return respuesta;
    }
    
    public byte[] sp_ObtenerImagenPerfil(int id_Usuario){
        
        byte[] respuesta = new byte[]{};
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenPerfil(?)}")){
                ejecutor.setInt("_IdUsuario",id_Usuario);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        try(InputStream stream = resultado.getBlob("Imagen").getBinaryStream()){
                            respuesta = stream.readAllBytes();
                        } catch (IOException ex) {
                        }
                        break;
                    }
                }else{
                    
                }
            }
        } catch(SQLException ex){
        }
        
        return respuesta;
        
    }
    
    
    public Vector<Object> sp_ActualizarImagenPerfil(int id_Usuario, byte[] imagen){
        
        Vector<Object> respuesta = new Vector<>();
        Blob blob = new Blob(imagen,null);
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarImagenPerfil(?,?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setBlob("_Imagen", blob);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
        
    }
    
    
    public Vector<Object> sp_CerrarSesion(int id_Usuario, int id_Sesion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_CerrarSesion(?,?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setInt("_IdSesion", id_Sesion);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerSesiones(int id_Usuario){
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerSesiones(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdSesion"));
                        codificacion = Codificacion(resultado.getString("Dispositivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Fabricante"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Uuid"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("UltimaConexion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("DireccionIP"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } catch(SQLException ex){
            
        }
        
        return respuesta;
        
    }
    
    
    public Vector<Object> sp_ActualizarDatosPersonales(int id_Usuario, String nombre, String paterno, String materno, 
            String genero, String fecha_Nacimiento, int id_Localidad){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarDatosPersonales(?,?,?,?,?,?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setString("_Nombre",nombre);
            ejecutor.setString("_Paterno",paterno);
            ejecutor.setString("_Materno",materno);
            ejecutor.setString("_Genero",genero);
            ejecutor.setString("_FechaNacimiento",fecha_Nacimiento);
            ejecutor.setInt("_IdLocalidad",id_Localidad);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    
    public void Cerrar_Conexion(){
        try {
            db_CourseRoom_Conexion.close();
        } catch (SQLException ex) {
            
        }
    }
    
}
