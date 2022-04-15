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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Vector<Object> sp_AbandonarCurso(int id_Curso,int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AbandonarCurso(?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_AbandonarGrupo(int id_Grupo,int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AbandonarGrupo(?,?)}")){
            
            ejecutor.setInt("_IdGrupo",id_Grupo);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_ActualizarConfiguracion(int id_Usuario, boolean chats_Conmigo, boolean avisos_Activos,
        boolean activo){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarConfiguracion(?,?,?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setBoolean("_ChatsConmigo",chats_Conmigo);
            ejecutor.setBoolean("_AvisosActivos",avisos_Activos);
            ejecutor.setBoolean("_Activo",activo);

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
    
    public Vector<Object> sp_ActualizarDatosAutenticacion(int id_Usuario, String correo_Electronico, 
            String contrasenia){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarDatosAutenticacion(?,?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setString("_CorreoElectronico",correo_Electronico);
            ejecutor.setString("_Contrasenia",contrasenia);

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
    
    public Vector<Object> sp_ActualizarDatosGeneralesGrupo(int id_Grupo,String nombre, String descripcion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarDatosGeneralesGrupo(?,?,?)}")){
            
            ejecutor.setInt("_IdGrupo",id_Grupo);
            ejecutor.setString("_Nombre",nombre);
            ejecutor.setString("_Descripcion",descripcion);

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
    
    public Vector<Object> sp_ActualizarImagenCurso(int id_Curso, byte[] imagen){
        
        Vector<Object> respuesta = new Vector<>();
        Blob blob = new Blob(imagen,null);
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarImagenCurso(?,?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);
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
    
    public Vector<Object> sp_ActualizarImagenGrupo(int id_Grupo, byte[] imagen){
        
        Vector<Object> respuesta = new Vector<>();
        Blob blob = new Blob(imagen,null);
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarImagenGrupo(?,?)}")){
            ejecutor.setInt("_IdGrupo",id_Grupo);
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
    
    public Vector<Object> sp_ActualizarInformacionExtra(int id_Usuario, String tipo_Usuario, double promedio_General, 
            String descripcion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarInformacionExtra(?,?,?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setString("_TipoUsuario",tipo_Usuario);
            ejecutor.setDouble("_PromedioGeneral",promedio_General);
            ejecutor.setString("_Descripcion",descripcion);

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
    
    public Vector<Object> sp_AgregarChat(int id_Usuario, int id_Usuario_Receptor){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarChat(?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setInt("_IdUsuarioReceptor",id_Usuario_Receptor);

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
    
    public Vector<Object> sp_AgregarInteres(int id_Usuario, int id_Tematica){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarInteres(?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setInt("_IdTematica",id_Tematica);

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
    
    public Vector<Object> sp_AgregarPregunta(int id_Usuario, String pregunta,String descripcion ){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarPregunta(?,?,?)}")){
            
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setString("_Pregunta",pregunta);
            ejecutor.setString("_Descripcion",descripcion);

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
    
    public Vector<Object> sp_AgregarTareaPendienteGrupo(int id_Grupo, String nombre,String descripcion, 
            String fecha_Finalizacion, int id_Usuario_Cargo){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarTareaPendienteGrupo(?,?,?,?,?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            ejecutor.setString("_Nombre", nombre);
            ejecutor.setString("_Descripcion", descripcion);
            ejecutor.setString("_FechaFinalizacion", fecha_Finalizacion);
            ejecutor.setInt("_IdUsuarioACargo", id_Usuario_Cargo);
            
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
    
    public Vector<Vector<Object>> sp_BuscarChatsPersonales(String busqueda, int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_BuscarChatsPersonales(?,?)}")){
            ejecutor.setString("_Busqueda", busqueda);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdChat"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("UltimoMensaje"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarGrupos(String busqueda, int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_BuscarGrupos(?,?)}")){
            ejecutor.setString("_Busqueda", busqueda);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdGrupo"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarPreguntas(String busqueda) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_BuscarPreguntas(?)}")){
            ejecutor.setString("_Busqueda", busqueda);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdPregunta"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Pregunta"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarTareas(String busqueda, int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_BuscarTareas(?,?)}")){
            ejecutor.setString("_Busqueda", busqueda);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTarea"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_CambiarEstatusTareaPendienteGrupo(int id_Tarea_Pendiente,String nuevo_Estatus, int id_Usuario ){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_CambiarEstatusTareaPendienteGrupo(?,?,?)}")){
            
            ejecutor.setInt("_IdTareaPendiente",id_Tarea_Pendiente);
            ejecutor.setString("_NuevoEstatus",nuevo_Estatus);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            

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
    
    public Vector<Object> sp_EnrolarUsuarioCurso(int id_Curso,int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnrolarUsuarioCurso(?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_EntregarTareaUsuario(int id_Tarea,int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EntregarTareaUsuario(?,?)}")){
            
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_EnviarArchivoAdjuntoTarea(int id_Tarea,String nombre_Archivo,byte[] archivo,String extension){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarArchivoAdjuntoTarea(?,?,?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setString("_NombreArchivo", nombre_Archivo);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            
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
    
    public Vector<Object> sp_EnviarArchivoCompartidoGrupo(int id_Grupo, int id_Usuario, String nombre_Archivo, byte[] archivo, 
            String extension){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarArchivoCompartidoGrupo(?,?,?,?,?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setString("_NombreArchivo", nombre_Archivo);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            
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
    
    public Vector<Object> sp_EnviarArchivoSubidoTarea(int id_Tarea, int id_Usuario, String nombre_Archivo, byte[] archivo, 
            String extension){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarArchivoSubidoTarea(?,?,?,?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setString("_NombreArchivo", nombre_Archivo);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            
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
    
    public Vector<Object> sp_EnviarMaterialCurso(int id_Curso, int id_Usuario, String nombre_Archivo, byte[] archivo, 
            String extension){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMaterialCurso(?,?,?,?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setString("_NombreArchivo", nombre_Archivo);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            
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
    
    public Vector<Object> sp_EnviarMensajeChat(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Chat){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMensajeChat(?,?,?,?,?)}")){
            ejecutor.setString("_Mensaje", mensaje);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            ejecutor.setInt("_IdUsuarioEmisor", id_Usuario_Emisor);
            ejecutor.setInt("_IdChat", id_Chat);
            
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
    
    public Vector<Object> sp_EnviarMensajeCurso(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Curso) throws SQLException, IOException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMensajeCurso(?,?,?,?,?)}")){
            ejecutor.setString("_Mensaje", mensaje);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            ejecutor.setInt("_IdUsuarioEmisor", id_Usuario_Emisor);
            ejecutor.setInt("_IdCurso", id_Curso);
            
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
    
    public Vector<Object> sp_EnviarMensajeGrupo(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Grupo) throws SQLException, IOException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMensajeGrupo(?,?,?,?,?)}")){
            ejecutor.setString("_Mensaje", mensaje);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            ejecutor.setInt("_IdUsuarioEmisor", id_Usuario_Emisor);
            ejecutor.setInt("_IdGrupo", id_Grupo);
            
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
    
    public Vector<Object> sp_EnviarMensajePregunta(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Pregunta) throws SQLException, IOException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMensajePregunta(?,?,?,?,?)}")){
            ejecutor.setString("_Mensaje", mensaje);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            ejecutor.setInt("_IdUsuarioEmisor", id_Usuario_Emisor);
            ejecutor.setInt("_IdPregunta", id_Pregunta);
            
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
    
    public Vector<Object> sp_EnviarMensajeTarea(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Tarea) throws SQLException, IOException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarMensajeTarea(?,?,?,?,?)}")){
            ejecutor.setString("_Mensaje", mensaje);
            ejecutor.setBlob("_Archivo", blob);
            ejecutor.setString("_Extension", extension);
            ejecutor.setInt("_IdUsuarioEmisor", id_Usuario_Emisor);
            ejecutor.setInt("_IdTarea", id_Tarea);
            
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
    
    public Vector<Object> sp_FinalizarCurso(int id_Curso,int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_FinalizarCurso(?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_MarcarPreguntaSolucionada(int id_Usuario, int id_Pregunta){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_MarcarPreguntaSolucionada(?,?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setInt("_IdPregunta", id_Pregunta);

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
    
    public Vector<Object> sp_ObtenerArchivoAdjuntoTarea(int id_Archivo_Adjunto){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoAdjuntoTarea(?)}")){
            ejecutor.setInt("_IdArchivoAdjunto", id_Archivo_Adjunto);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoCompartidoGrupo(int id_Archivo_Compartido){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoCompartidoGrupo(?)}")){
            ejecutor.setInt("_IdArchivoCompartido", id_Archivo_Compartido);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoMensajeChat(int id_Mensaje){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoMensajeChat(?)}")){
            ejecutor.setInt("_IdMensaje", id_Mensaje);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoMensajeCurso(int id_Mensaje){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoMensajeCurso(?)}")){
            ejecutor.setInt("_IdMensaje", id_Mensaje);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoMensajeGrupo(int id_Mensaje){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoMensajeGrupo(?)}")){
            ejecutor.setInt("_IdMensaje", id_Mensaje);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
           
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoMensajePregunta(int id_Mensaje) {
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoMensajePregunta(?)}")){
            ejecutor.setInt("_IdMensaje", id_Mensaje);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoMensajeTarea(int id_Mensaje){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoMensajeTarea(?)}")){
            ejecutor.setInt("_IdMensaje", id_Mensaje);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoRetroalimentacionTarea(int id_Retroalimentacion){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoRetroalimentacionTarea(?)}")){
            ejecutor.setInt("_IdRetroalimentacion",id_Retroalimentacion);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ObtenerArchivoSubidoTarea(int id_Archivo_Subido, int id_Usuario) {
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivoSubidoTarea(?,?)}")){
            ejecutor.setInt("_IdArchivoSubido", id_Archivo_Subido);
            ejecutor.setInt("_IdUsuario", id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosAdjuntosTarea(int id_Tarea) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivosAdjuntosTarea(?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdArchivoAdjunto"));
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnviado"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosCompartidosGrupo(int id_Grupo) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivosCompartidosGrupo(?)}")){
            ejecutor.setInt("_IdGrupo",id_Grupo);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdArchivoCompartido"));
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnviado"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosSubidosTarea(int id_Tarea, int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerArchivosSubidosTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdArchivoSubido"));
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnviado"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerAvisos(int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerAvisos(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdAviso"));
                        codificacion = Codificacion(resultado.getString("TipoAviso"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Aviso"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerChatsPersonales(int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerChatsPersonales(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdChat"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("UltimoMensaje"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_ObtenerConfiguraciones(int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerConfiguraciones(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        respuesta.add(resultado.getBoolean("ChatsConmigo"));
                        respuesta.add(resultado.getBoolean("AvisosActivos"));
                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
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
    
    public Vector<Vector<Object>> sp_ObtenerCursosActuales(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosActuales()}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("ListaTematicas"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntuacion"));
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return response;
    }
    
    public Vector<Vector<Object>> sp_ObtenerCursosFinalizados(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosFinalizados()}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("ListaTematicas"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntuacion"));
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return response;
    }
    
    public Vector<Vector<Object>> sp_ObtenerCursosNuevos(){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosNuevos()}")){
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("ListaTematicas"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntuacion"));
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return response;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesChatPersonal(int id_Chat, int id_Usuario) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesChatPersonal(?,?)}")){
            ejecutor.setInt("_IdChat", id_Chat);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        respuesta.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        respuesta.add(codificacion);
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesCurso(int id_Curso) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("DescripcionProfesor"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesGrupo(int id_Grupo) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesGrupo(?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        respuesta.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesPregunta(int id_Pregunta) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesPregunta(?)}")){
            ejecutor.setInt("_IdPregunta",id_Pregunta);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        respuesta.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Pregunta"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        respuesta.add(codificacion);
                        
                        
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesTarea(int id_Tarea, int id_Usuario) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesTareaPendiente(int id_Tarea_Pendiente) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesTareaPendiente(?)}")){
            ejecutor.setInt("_IdTareaPendiente",id_Tarea_Pendiente);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaFinalizacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        respuesta.add(codificacion);
                        
                        respuesta.add(resultado.getInt("IdUsuario"));
                        
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } 
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosPerfil(int id_Usuario) throws SQLException{
        
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
        } 
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosPerfilChatPersonal(int id_Usuario) throws SQLException{
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosPerfilChatPersonal(?)}")){
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
                        
                        break;
                    }
                }
            }
        } 
        
        return respuesta;
    }
    
    public Vector<Vector<Object>> sp_ObtenerDesempenoUsuario(int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDesempenoUsuario(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdDesempeno"));
                        fila.add(resultado.getDouble("Prediccion"));
                        fila.add(resultado.getInt("NumeroTareasCalificadas"));
                        codificacion = Codificacion(resultado.getString("RumboEstatus"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("PromedioGeneral"));
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
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
    
    public Vector<String> sp_ObtenerFechaActualizacionTareaSubida(int id_Tarea,int id_Usuario) throws SQLException{
        Vector<String> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerFechaActualizacionTareaSubida(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("FechaSubida"));
                        response.add(codificacion);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerGrupos(int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerGrupos(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdGrupo"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdCurso"));
                         codificacion = Codificacion(resultado.getString("NombreCurso"));
                        fila.add(codificacion);
                         codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerGruposCurso(int id_Curso) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerGruposCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdGrupo"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("NumeroIntegrantes"));
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Integer> sp_ObtenerIDsUsuariosCurso(int id_Curso, int id_Usuario){
        Vector<Integer> response = new Vector<>();
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerIDsUsuariosCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        response.add(resultado.getInt("IdUsuario"));
                    }
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return response;
    }
    
    public Vector<Integer> sp_ObtenerIDsUsuariosGrupo(int id_Grupo, int id_Usuario){
        Vector<Integer> response = new Vector<>();
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerIDsUsuariosGrupo(?,?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        response.add(resultado.getInt("IdUsuario"));
                    }
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return response;
    }
    
    public byte[] sp_ObtenerImagenChatPersonal(int id_Chat, int id_Usuario){
        
        byte[] respuesta = new byte[]{};
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenChatPersonal(?,?)}")){
            ejecutor.setInt("_IdChat",id_Chat);
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
    
    public byte[] sp_ObtenerImagenCurso(int id_Curso){
        
        byte[] respuesta = new byte[]{};
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenCurso(?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);
            
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
    
    public byte[] sp_ObtenerImagenGrupo(int id_Grupo){
        
        byte[] respuesta = new byte[]{};
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenGrupo(?)}")){
            ejecutor.setInt("_IdGrupo",id_Grupo);
            
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
    
    public byte[] sp_ObtenerImagenPregunta(int id_Pregunta){
        
        byte[] respuesta = new byte[]{};
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenPregunta(?)}")){
            ejecutor.setInt("_IdPregunta",id_Pregunta);
            
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
    
    public Vector<Vector<Object>> sp_ObtenerInteresesUsuario(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerInteresesUsuario(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        fila = new Vector<>();
                        
                        fila.add(resultado.getInt("IdTematica"));
                        
                        codificacion = Codificacion(resultado.getString("Tematica"));
                        fila.add(codificacion);
                        
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
    
    public Vector<Vector<Object>> sp_ObtenerMaterialesSubidosCurso(int id_Curso) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMaterialesSubidosCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMaterialSubido"));
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnviado"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_ObtenerMaterialSubidoCurso( int id_Material_Subido){
        
        Vector<Object> respuesta = new Vector<>();
        byte[] archivo;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMaterialSubidoCurso(?)}")){
            ejecutor.setInt("_IdMaterialSubido", id_Material_Subido);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            archivo = stream.readAllBytes();
                        } catch (IOException ex) {
                            archivo = new byte[]{};
                        }
                        
                        respuesta.add(archivo);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);

                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesChat(int id_Chat) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMensajesChat(?)}")){
            ejecutor.setInt("_IdChat", id_Chat);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMensaje"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesCurso(int id_Curso) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMensajesCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMensaje"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesGrupo(int id_Grupo) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMensajesGrupo(?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMensaje"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesPregunta(int id_Pregunta) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMensajesPregunta(?)}")){
            ejecutor.setInt("_IdPregunta", id_Pregunta);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMensaje"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesTarea(int id_Tarea) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMensajesTarea(?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdMensaje"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMiembrosCurso(int id_Curso) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMiembrosCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaIngreso"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMiembrosGrupo(int id_Grupo) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerMiembrosGrupo(?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaIngreso"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerPreguntas(int id_Usuario) throws SQLException{
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerPreguntas(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdPregunta"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Pregunta"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        response.add(fila);
                    }
                }
            }
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerRetroalimentacionesTarea(int id_Tarea,int id_Usuario) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerRetroalimentacionesTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdRetroalimentacion"));
                        codificacion = Codificacion(resultado.getString("Retroalimentacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerSesiones(int id_Usuario) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerSesiones(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
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
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasCurso(int id_Curso, int id_Usuario) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdUsuario", id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTarea"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasEstudiante(int id_Usuario) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasEstudiante(?,?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTarea"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasMes(int mes, int id_Usuario) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasMes(?,?)}")){
            ejecutor.setInt("_Mes", mes);
            ejecutor.setInt("_IdUsuario", id_Usuario);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTarea"));
                        fila.add(resultado.getInt("Dia"));
                        codificacion = Codificacion(resultado.getString("Horario"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasPendientesGrupo(int id_Grupo) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasPendientesGrupo(?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTareaPendiente"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTematicas() throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTematicas()}")){

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTematica"));
                        codificacion = Codificacion(resultado.getString("Tematica"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTematicasCurso(int id_Curso) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTematicasCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdTematica"));
                        codificacion = Codificacion(resultado.getString("Tematica"));
                        fila.add(codificacion);
                        respuesta.add(fila);
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
    
    public Vector<Vector<Object>> sp_ObtenerUsuariosParaChatear(String busqueda) throws SQLException{
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerUsuariosParaChatear(?)}")){
            ejecutor.setString("_Busqueda",busqueda);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } 
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_RemoverArchivoCompartidoGrupo(int id_Archivo_Compartido, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverArchivoCompartidoGrupo(?,?)}")){
            ejecutor.setInt("_IdArchivoCompartido", id_Archivo_Compartido);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverArchivoSubidoTarea(int id_Archivo_Subido,int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverArchivoSubidoTarea(?,?)}")){
            ejecutor.setInt("_IdArchivoSubido", id_Archivo_Subido);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverChatPersonal(int id_Chat, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverChatPersonal(?,?)}")){
            ejecutor.setInt("_IdChat", id_Chat);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverInteresUsuario(int id_Tematica, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverInteresUsuario(?,?)}")){
            ejecutor.setInt("_IdTematica", id_Tematica);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverMaterialCurso(int id_Material_Subido, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverMaterialCurso(?,?)}")){
            ejecutor.setInt("_IdMaterialSubido", id_Material_Subido);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverPorVotoMiembroGrupo(int id_Grupo, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverPorVotoMiembroGrupo(?,?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
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
    
    public Vector<Object> sp_RemoverPregunta(int id_Pregunta, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverPregunta(?,?)}")){
            ejecutor.setInt("_IdPregunta", id_Pregunta);
            ejecutor.setInt("_IdUsuario",id_Usuario);

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
    
    public Vector<Object> sp_RemoverTematicaCurso(int id_Tematica, int id_Curso){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverTematicaCurso(?,?)}")){
            ejecutor.setInt("_IdTematica", id_Tematica);
            ejecutor.setInt("_IdCurso",id_Curso);

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
    
    public void Cerrar_Conexion(){
        try {
            db_CourseRoom_Conexion.close();
        } catch (SQLException ex) {
            
        }
    }
    
}
