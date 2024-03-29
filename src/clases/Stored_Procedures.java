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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
                       
                        
                        respuesta.add(resultado.getInt("Codigo"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ActualizarDatosGeneralesCurso(int id_Curso,String nombre, String descripcion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarDatosGeneralesCurso(?,?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_ActualizarDatosGeneralesTarea(int id_Tarea,String nombre, String descripcion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ActualizarDatosGeneralesTarea(?,?,?)}")){
            
            ejecutor.setInt("_IdTarea",id_Tarea);
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_AgregarCurso(String nombre, String descripcion, int id_Profesor){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarCurso(?,?,?)}")){
            
            ejecutor.setString("_Nombre",nombre);
            ejecutor.setString("_Descripcion",descripcion);
            ejecutor.setInt("_IdProfesor",id_Profesor);

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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_AgregarGrupo(String nombre, String descripcion, int id_Curso){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarGrupo(?,?,?)}")){
            
            ejecutor.setString("_Nombre",nombre);
            ejecutor.setString("_Descripcion",descripcion);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_AgregarDesempenoUsuario(String tarea_Calificada, double calificacion, double promedio_Curso,
            double promedio_General, double prediccion_Promedio, String rumbo_Estatus_Promedio, double puntualidad,
            double promedio_Puntualidad, double prediccion_Puntualidad, String rumbo_Estatus_Puntualidad,
            boolean rezago, int id_Usuario, int id_Curso){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarDesempenoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?)}")){
            
            ejecutor.setString("_TareaCalificada",tarea_Calificada);
            ejecutor.setDouble("_Calificacion",calificacion);
            ejecutor.setDouble("_PromedioCurso",promedio_Curso);
            ejecutor.setDouble("_PromedioGeneral",promedio_General);
            ejecutor.setDouble("_PrediccionPromedio",prediccion_Promedio);
            ejecutor.setString("_RumboEstatusPromedio",rumbo_Estatus_Promedio);
            ejecutor.setDouble("_Puntualidad",puntualidad);
            ejecutor.setDouble("_PromedioPuntualidad",promedio_Puntualidad);
            ejecutor.setDouble("_PrediccionPuntualidad",prediccion_Puntualidad);
            ejecutor.setString("_RumboEstatusPuntualidad",rumbo_Estatus_Puntualidad);
            ejecutor.setBoolean("_Rezago",rezago);
            ejecutor.setInt("_IdUsuario",id_Usuario);
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_AgregarRespuestaCuestionario(int id_Curso,int id_Usuario, int id_Pregunta, String respuesta ){
        
        Vector<Object> response = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarRespuestaCuestionario(?,?,?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            ejecutor.setInt("_IdPregunta",id_Pregunta);
            ejecutor.setString("_Respuesta",respuesta);

            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        
                        response.add(resultado.getInt("Codigo"));
                        response.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            response.add(-1);
            response.add(ex.getMessage());
        }
        
        return response;
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_AgregarTarea(int id_Curso, String nombre,String descripcion, String fecha_Entrega){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarTarea(?,?,?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setString("_Nombre", nombre);
            ejecutor.setString("_Descripcion", descripcion);
            ejecutor.setString("_FechaEntrega", fecha_Entrega);
            
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_AgregarTareaUsuario(int id_Curso, int id_Tarea){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarTareaUsuario(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_AgregarTematica(int id_Curso, int id_Tematica){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarTematica(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdTematica", id_Tematica);
            
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_AgregarUsuario(String correo_Electronico,String contrasenia ,String nombre,
        String paterno,String materno, int id_Localidad, String genero,String fecha_Nacimiento, String tipo_Usuario,
        byte[] Imagen, double promedio_General,String descripcion){
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_AgregarUsuarioAGrupo(int id_Usuario, int id_Grupo){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_AgregarUsuarioAGrupo(?,?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Vector<Object>> sp_BuscarChatsPersonales(String busqueda, int id_Usuario){
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
                        fila.add(resultado.getInt("IdUsuario"));
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarCursos(String busqueda, int id_Usuario) {
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_BuscarCursos(?,?)}")){
            ejecutor.setString("_Busqueda", busqueda);
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
                        fila.add(Codificacion(resultado.getString("Estatus")));
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarGrupos(String busqueda, int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarPreguntas(String busqueda) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_BuscarTareas(String busqueda, int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_CalificarTarea(int id_Tarea, int id_Usuario, double calificacion){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_CalificarTarea(?,?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setDouble("_Calificacion", calificacion);
            
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
                        codificacion = resultado.getString("Mensaje");
                        codificacion = Codificacion(codificacion);
                        respuesta.add(resultado.getInt("Codigo"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
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
                        respuesta.add(resultado.getInt("Id"));
                        respuesta.add(resultado.getInt("Codigo"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_EnviarMensajeCurso(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Curso){
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_EnviarMensajeGrupo(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Grupo){
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_EnviarMensajePregunta(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Pregunta){
        
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
                        respuesta.add(resultado.getInt("Id"));
                        respuesta.add(resultado.getInt("Codigo"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_EnviarMensajeTarea(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor,
            int id_Tarea){
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_EnviarRetroalimentacionTarea(int id_Tarea, int id_Usuario, String retroalimentacion,
            String nombre_Archivo, byte[] archivo, String extension){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        Blob blob = new Blob(archivo,null);
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_EnviarRetroalimentacionTarea(?,?,?,?,?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setString("_Retroalimentacion", retroalimentacion);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_FinalizarCursoProfesor(int id_Curso,int id_Profesor){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_FinalizarCursoProfesor(?,?)}")){
            
            ejecutor.setInt("_IdCurso",id_Curso);
            ejecutor.setInt("_IdProfesor",id_Profesor);

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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_FinalizarCursoUsuario(int id_Curso,int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_FinalizarCursoUsuario(?,?)}")){
            
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosAdjuntosTarea(int id_Tarea) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosCompartidosGrupo(int id_Grupo) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerArchivosSubidosTarea(int id_Tarea, int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerAvisos(int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_ObtenerCalificacionTarea(int id_Tarea, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCalificacionTarea(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        respuesta.add(resultado.getDouble("Calificacion"));
                        codificacion = Codificacion(resultado.getString("FechaCalificacion"));
                        respuesta.add(codificacion);
                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Vector<Object>> sp_ObtenerChatsPersonales(int id_Usuario) {
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
                        fila.add(resultado.getInt("IdUsuario"));
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public String sp_ObtenerCredenciales(String correo_Electronico) {
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
        } catch (SQLException ex) {
            
            respuesta = "No se pudo obtener la credencial solicitada";
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Vector<Object>> sp_ObtenerCursosActuales(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosActuales(?)}")){
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
            System.err.println(ex.getMessage());
        }
        
        return response;
    }
    
    public Vector<Vector<Object>> sp_ObtenerCursosCreadosProfesor(int id_Profesor){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosCreadosProfesor(?)}")){
            ejecutor.setInt("_IdProfesor",id_Profesor);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Tematicas"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
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
    
    public Vector<Vector<Object>> sp_ObtenerCursosFinalizados(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosFinalizados(?)}")){
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
            System.err.println(ex.getMessage());
        }
        
        return response;
    }
    
    public Vector<Vector<Object>> sp_ObtenerCursosNuevos(int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerCursosNuevos(?)}")){
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
            System.err.println(ex.getMessage());
        }
        
        return response;
    }
    
    public Vector<Object> sp_ObtenerDatosEntregaTarea(int id_Tarea, int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosEntregaTarea(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        codificacion = Codificacion(resultado.getString("FechaSubida"));
                        respuesta.add(codificacion);
                        respuesta.add(resultado.getDouble("Calificacion"));
                        codificacion = Codificacion(resultado.getString("FechaCalificacion"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        respuesta.add(codificacion);
                        break;
                    }
                }
            }
        } catch (SQLException ex) { 
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    
    public Vector<Object> sp_ObtenerDatosGeneralesCurso(int id_Curso) {
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            
            try(ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        respuesta.add(resultado.getInt("IdProfesor"));
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesGrupo(int id_Grupo) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesPregunta(int id_Pregunta) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesTarea(int id_Tarea, int id_Usuario) {
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        respuesta.add(resultado.getInt("IdCurso"));
                        
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesTareaPendiente(int id_Tarea_Pendiente) {
        
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
        }  catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosGeneralesTareaProfesor(int id_Tarea, int id_Profesor) {
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDatosGeneralesTareaProfesor(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdProfesor",id_Profesor);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        
                        respuesta.add(resultado.getInt("IdCurso"));
                        
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Nombre"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Descripcion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaCreacion"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        respuesta.add(codificacion);
                        
                        codificacion = Codificacion(resultado.getString("Estatus"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        }  catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosPerfil(int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        
        return respuesta;
    }
    
    public Vector<Object> sp_ObtenerDatosPerfilChatPersonal(int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
    }
    
    public Vector<Vector<Object>> sp_ObtenerDesempenoCurso(int id_Curso) {
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDesempenoCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdDesempenoCurso"));
                        codificacion = Codificacion(resultado.getString("TareaCalificada"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Calificacion"));
                        fila.add(resultado.getDouble("PromedioCurso"));
                        fila.add(resultado.getDouble("PrediccionPromedio"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPromedio"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntualidad"));
                        fila.add(resultado.getDouble("PromedioPuntualidad"));
                        fila.add(resultado.getDouble("PrediccionPuntualidad"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPuntualidad"));
                        fila.add(codificacion);
                        fila.add(resultado.getBoolean("Rezago"));
                        codificacion = Codificacion(resultado.getString("FechaRegistro"));
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
    
    public Vector<Vector<Object>> sp_ObtenerDesempenoUsuario(int id_Usuario) {
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDesempenoUsuario(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdDesempenoCurso"));
                        fila.add(resultado.getInt("IdCurso"));
                        codificacion = Codificacion(resultado.getString("NombreCurso"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("PromedioCurso"));
                        fila.add(resultado.getDouble("PromedioGeneral"));
                        fila.add(resultado.getDouble("PrediccionPromedio"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPromedio"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntualidad"));
                        fila.add(resultado.getDouble("PromedioPuntualidad"));
                        fila.add(resultado.getDouble("PrediccionPuntualidad"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPuntualidad"));
                        fila.add(codificacion);
                        fila.add(resultado.getBoolean("Rezago"));
                        codificacion = Codificacion(resultado.getString("FechaRegistro"));
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
    
    public Vector<Vector<Object>> sp_ObtenerDesempenoUsuarioCurso(int id_Curso,int id_Usuario) {
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerDesempenoUsuarioCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    Vector<Object> fila;
                    while(resultado.next()){
                        fila = new Vector<>();
                        fila.add(resultado.getInt("IdDesempenoCurso"));
                        codificacion = Codificacion(resultado.getString("TareaCalificada"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Calificacion"));
                        fila.add(resultado.getDouble("PromedioCurso"));
                        fila.add(resultado.getDouble("PrediccionPromedio"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPromedio"));
                        fila.add(codificacion);
                        fila.add(resultado.getDouble("Puntualidad"));
                        fila.add(resultado.getDouble("PromedioPuntualidad"));
                        fila.add(resultado.getDouble("PrediccionPuntualidad"));
                        codificacion = Codificacion(resultado.getString("RumboEstatusPuntualidad"));
                        fila.add(codificacion);
                        fila.add(resultado.getBoolean("Rezago"));
                        codificacion = Codificacion(resultado.getString("FechaRegistro"));
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
    
    public Vector<Vector<Object>> sp_ObtenerEntregasTareaPorCalificar(int id_Tarea, int id_Profesor) {
        Vector<Vector<Object>> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerEntregasTareaPorCalificar(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdProfesor", id_Profesor);
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
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaSubida"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
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
    
    public Vector<String> sp_ObtenerEstados() {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public String sp_ObtenerFechaActualizacionTareaSubida(int id_Tarea,int id_Usuario) {
        String response = new String();
      
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerFechaActualizacionTareaSubida(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        response = Codificacion(resultado.getString("FechaSubida"));
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
    }
    
    public Vector<Vector<Object>> sp_ObtenerGrupos(int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerGruposCurso(int id_Curso) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
     
    public int sp_ObtenerIdProfesorTarea(int id_Tarea){
        int response = -1;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerIdProfesorTarea(?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        response = resultado.getInt("IdProfesor");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
        }
        
        return response;
    }
    
    public Vector<Integer> sp_ObtenerIDsUsuariosTarea(int id_Tarea, int id_Usuario){
        Vector<Integer> response = new Vector<>();
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerIDsUsuariosTarea(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        response.add(resultado.getInt("IdUsuario"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerImagenesEnviadasTarea(int id_Tarea, int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        Vector<Object> fila;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerImagenesEnviadasTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        fila = new Vector<>();
                       
                        fila.add(resultado.getInt("IdTarea"));
                        
                        fila.add(resultado.getString("NombreArchivo"));
                        
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerInfoGruposCurso(int id_Curso){
        Vector<Vector<Object>> response = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerInfoGruposCurso(?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        fila = new Vector<>();
                        
                        fila.add(resultado.getInt("IdUsuario"));
                        
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
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
    
    public Vector<Vector<Object>> sp_ObtenerLocalidadesPorEstado(String estado) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMaterialesSubidosCurso(int id_Curso) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesChat(int id_Chat) {
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
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesCurso(int id_Curso) {
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
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesGrupo(int id_Grupo) {
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
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesPregunta(int id_Pregunta) {
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
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMensajesTarea(int id_Tarea) {
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
                        response.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerMiembrosCurso(int id_Curso) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerPDFsEnviadosTarea(int id_Tarea, int id_Usuario){
        Vector<Vector<Object>> response = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        byte[] respuesta;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerPDFsEnviadosTarea(?,?)}")){
            ejecutor.setInt("_IdTarea",id_Tarea);
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        fila = new Vector<>();
                        
                        try(InputStream stream = resultado.getBlob("Archivo").getBinaryStream()){
                            respuesta = stream.readAllBytes();
                        } catch (IOException ex) {
                            respuesta = new byte[]{};
                        }
                        
                        fila.add(respuesta);
                        
                        codificacion = Codificacion(resultado.getString("Extension"));
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
    
    public Vector<Vector<Object>> sp_ObtenerMiembrosGrupo(int id_Grupo) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public double sp_ObtenerNuevoPromedioGeneral(int id_Usuario, int id_Curso, double nuevo_Promedio) {
        double response = -1;;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerNuevoPromedioGeneral(?,?,?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setDouble("_NuevoPromedio", nuevo_Promedio);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        response = resultado.getDouble("NuevoPromedio");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public int sp_ObtenerNumeroMiembrosCurso(int id_Curso) {
        int response = -1;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerNumeroMiembrosCurso(?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        response = resultado.getInt("NumeroMiembros");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerPreguntas(int id_Usuario) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public double sp_ObtenerPromedioCurso(int id_Curso, int id_Usuario) {
        double response = -1;;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerPromedioCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        response = resultado.getDouble("PromedioCurso");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public double sp_ObtenerPromedioPuntualidad(int id_Usuario) {
        double response = -1;;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerPromedioPuntualidad(?)}")){
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        response = resultado.getDouble("PromedioPuntualidad");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public double sp_ObtenerPuntualidad(int id_Tarea, int id_Usuario) {
        double response = -1;;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerPuntualidad(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdUsuario", id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    while(resultado.next()){
                        response = resultado.getDouble("Puntualidad");
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerRetroalimentacionesTarea(int id_Tarea,int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerSesiones(int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasCreadas(int id_Profesor) {
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasCreadas(?)}")){
            ejecutor.setInt("_IdProfesor", id_Profesor);

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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasCurso(int id_Curso, int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasCursoProfesor(int id_Curso, int id_Profesor) {
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasCursoProfesor(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdProfesor", id_Profesor);

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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasEstudiante(int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasMes(int mes, int id_Usuario) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasPendientesGrupo(int id_Grupo) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTareasPorCalificar(int id_Profesor) {
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTareasPorCalificar(?)}")){
            ejecutor.setInt("_IdProfesor", id_Profesor);

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
                        fila.add(resultado.getInt("IdUsuario"));
                        codificacion = Codificacion(resultado.getString("NombreCompleto"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaSubida"));
                        fila.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEntrega"));
                        fila.add(codificacion);
                        respuesta.add(fila);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTematicas() {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerTematicasCurso(int id_Curso) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Integer> sp_ObtenerTodosIDsUsuariosCurso(int id_Curso){
        Vector<Integer> response = new Vector<>();
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerTodosIDsUsuariosCurso(?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        response.add(resultado.getInt("IdUsuario"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
    
    public Vector<Object> sp_ObtenerUltimoAviso(int id_Usuario){
        Vector<Object> response = new Vector<>();
        String codificacion;
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerUltimoAviso(?)}")){
            ejecutor.setInt("_IdUsuario",id_Usuario);
            try (ResultSet resultado = ejecutor.executeQuery()){
                if(resultado != null){
                    
                    while(resultado.next()){
                        
                        response.add(resultado.getInt("IdAviso"));
                        codificacion = Codificacion(resultado.getString("TipoAviso"));
                        response.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Aviso"));
                        response.add(codificacion);
                        codificacion = Codificacion(resultado.getString("FechaEnvio"));
                        response.add(codificacion);
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    }
    
    public Vector<Vector<Object>> sp_ObtenerUsuariosParaChatear(String busqueda) {
        
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Vector<Object>> sp_ObtenerUsuariosSinGrupo(int id_Curso) {
        
        Vector<Vector<Object>> respuesta = new Vector<>();
        Vector<Object> fila;
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ObtenerUsuariosSinGrupo(?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);

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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return respuesta;
        
    }
    
    public Vector<Object> sp_RemoverArchivoAdjuntoTarea(int id_Archivo_Adjunto, int id_Tarea){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverArchivoAdjuntoTarea(?,?)}")){
            ejecutor.setInt("_IdArchivoAdjunto", id_Archivo_Adjunto);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
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
            System.err.println(ex.getMessage());
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
                        
                        respuesta.add(resultado.getInt("IdTarea"));
                        codificacion = Codificacion(resultado.getString("NombreArchivo"));
                        respuesta.add(codificacion);
                        codificacion = Codificacion(resultado.getString("Extension"));
                        respuesta.add(codificacion);
                        respuesta.add(resultado.getInt("Codigo"));
                        codificacion = Codificacion(resultado.getString("Mensaje"));
                        respuesta.add(codificacion);
                        
                        break;
                    }
                }
            }
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            codificacion = Codificacion("");
            respuesta.add(codificacion);
            codificacion = Codificacion("");
            respuesta.add(codificacion);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_RemoverCurso(int id_Curso, int id_Profesor){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
            ejecutor.setInt("_IdProfesor", id_Profesor);
            
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_RemoverGrupo(int id_Grupo, int id_Curso){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverGrupo(?,?)}")){
            ejecutor.setInt("_IdGrupo", id_Grupo);
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        
        return respuesta;
    } 
    
    public Vector<Object> sp_RemoverMiembroCurso(int id_Curso, int id_Usuario){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverMiembroCurso(?,?)}")){
            ejecutor.setInt("_IdCurso", id_Curso);
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        return respuesta;
    }
    
    public Vector<Object> sp_RemoverTarea(int id_Tarea, int id_Profesor){
        
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_RemoverTarea(?,?)}")){
            ejecutor.setInt("_IdTarea", id_Tarea);
            ejecutor.setInt("_IdProfesor", id_Profesor);
            
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
            System.err.println(ex.getMessage());
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        return respuesta;
    }
    
    public Vector<Object> sp_ValidacionContestarCuestionario(int id_Curso, int id_Usuario){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ValidacionContestarCuestionario(?,?)}")){
            ejecutor.setInt("_IdCurso",id_Curso);
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        return respuesta;
    }
    
    public Vector<Object> sp_ValidarGeneracionGrupos(int id_Curso){
        Vector<Object> respuesta = new Vector<>();
        String codificacion;
        
        try (CallableStatement ejecutor = db_CourseRoom_Conexion.prepareCall("{CALL sp_ValidarGeneracionGrupos(?)}")){
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
            System.err.println(ex.getMessage());
            respuesta.add(-1);
            respuesta.add(Codificacion(ex.getMessage()));
        }
        return respuesta;
    }
    
    public void Cerrar_Conexion(){
        try {
            db_CourseRoom_Conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
