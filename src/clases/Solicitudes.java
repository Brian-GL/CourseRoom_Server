/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package clases;

import datos.estructuras.Par;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author LENOVO
 */
public class Solicitudes {
    
    private Session sesion;
    private StringBuilder mensaje_HTML;
    private MimeBodyPart parte_Cuerpo_MIME_HTML;
    private MimeMessage mensaje_MIME;
    private InternetAddress direccion_Internet;
    private Multipart multiparte;
    private Stored_Procedures stored_Procedures;
    private Servidor_DB respuestas;
    
    private Solicitudes(){
       
        respuestas = Servidor_DB.getInstance();
        stored_Procedures = Stored_Procedures.getInstance();

        //Crear las propiedades para mandar el correo
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.outlook.com");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");

        // Obtener la sesion
        sesion = Session.getInstance(propiedades, null);
    }
    
    public static Solicitudes getInstance() {
        return SolicitudesHolder.INSTANCE;
    }
    
    private static class SolicitudesHolder {

        private static final Solicitudes INSTANCE = new Solicitudes();
    }
    
    private String Decodificacion(String codificacion){
        byte[] decoded = Base64.getDecoder().decode(codificacion);
        return new String(decoded);
    }
    
    private String Concatenar(String cadena, String... args) {
        StringBuilder constructor_Cadena = new StringBuilder(cadena);
        for (String arg : args) {
            constructor_Cadena.append(arg);
        }
        return constructor_Cadena.toString();
    }
    
    public Vector<Object> Abandonar_Grupo(int id_Grupo, int id_Usuario,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Abandonar Grupo ",String.valueOf(id_Grupo)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AbandonarGrupo(id_Grupo, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Grupo Abandonado ",String.valueOf(id_Grupo)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Actualizar_Configuracion(int id_Usuario, boolean chats_Conmigo, boolean avisos_Activo, boolean activo,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Configuración Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ActualizarConfiguracion(id_Usuario, chats_Conmigo, avisos_Activo, activo);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Actualizada Configuración Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Actualizar_Datos_Autenticacion(int id_Usuario, String correo_Electronico, String contrasenia,
            String cliente, String ip){
        
        Vector<Object> response;
        
        correo_Electronico = Decodificacion(correo_Electronico);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Datos De Autenticación Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_ActualizarDatosAutenticacion(id_Usuario, correo_Electronico, contrasenia);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Autenticación Actualizados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Actualizar_Datos_Generales_Grupo(int id_Grupo, String nombre, 
            String descripcion, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Datos Generales Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_ActualizarDatosGeneralesGrupo(id_Grupo, nombre, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Generales Actualizados Del Grupo ", String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Actualizar_Datos_Personales(int id_Usuario, String nombre, String paterno, String materno, 
            String genero, String fecha_Nacimiento, int id_Localidad, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        paterno = Decodificacion(paterno);
        materno = Decodificacion(materno);
        genero = Decodificacion(genero);
        fecha_Nacimiento = Decodificacion(fecha_Nacimiento);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Datos Personales Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_ActualizarDatosPersonales(id_Usuario,nombre, paterno, materno, genero, fecha_Nacimiento,id_Localidad);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Personales Actualizados Del Usuario ",String.valueOf(id_Usuario)," Con ID ", ((Integer)response.get(0)).toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Actualizar_Imagen_Curso(int id_Curso, byte[] imagen, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Imagen Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ActualizarImagenCurso(id_Curso,imagen);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Imagen Actualizada Del Curso ", String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Actualizar_Imagen_Grupo(int id_Grupo, byte[] imagen, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Imagen Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ActualizarImagenGrupo(id_Grupo,imagen);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Imagen Actualizada Del Grupo ", String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Actualizar_Imagen_Perfil(int id_Usuario, byte[] imagen, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Imagen Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ActualizarImagenPerfil(id_Usuario,imagen);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Imagen Actualizada Del Usuario ", String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Actualizar_Informacion_Extra(int id_Usuario, String tipo_Usuario, double promedio_General, 
            String descripcion, String cliente, String ip){
        
        Vector<Object> response;
        
        tipo_Usuario = Decodificacion(tipo_Usuario);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Información Extra Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_ActualizarInformacionExtra(id_Usuario,tipo_Usuario, promedio_General, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Información Extra Actualizada Del Usuario ", String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Chat(int id_Usuario, int id_Usuario_Receptor , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Chat Entre Los Usuarios ",String.valueOf(id_Usuario), " & ",String.valueOf(id_Usuario_Receptor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_AgregarChat(id_Usuario, id_Usuario_Receptor);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Chat Agregado Con ID ", String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Interes(int id_Usuario, int id_Tematica , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Interés ",String.valueOf(id_Tematica), " Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_AgregarInteres(id_Usuario, id_Tematica);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Interes Agregado Al Usuario Con ID De Relación ", String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Pregunta(int id_Usuario, String pregunta, String descripcion, String cliente, String ip){
        
        Vector<Object> response;
        
        pregunta = Decodificacion(pregunta);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Pregunta Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Actualizar Datos Usuario:
        response
                = stored_Procedures.sp_AgregarPregunta(id_Usuario, pregunta, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Agregada Pregunta Del Usuario ", String.valueOf(id_Usuario), " Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Sesion(int id_Usuario, String dispositivo, String fabricante,
        String uuid, String ip){
        
        Vector<Object> response;
        
        dispositivo = Decodificacion(dispositivo);
        fabricante = Decodificacion(fabricante);
        uuid = Decodificacion(uuid);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Sesión Del Usuario ",String.valueOf(id_Usuario)), uuid, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarSesion(id_Usuario, dispositivo, fabricante, uuid, ip);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), uuid, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Sesion Agregada Del Usuario ",String.valueOf(id_Usuario)," Con ID ", ((Integer)response.get(0)).toString()), uuid, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Tarea_Pendiente_Grupo(int id_Grupo, String nombre, String descripcion, String fecha_Finalizacion,
        int id_Usuario_Cargo, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        fecha_Finalizacion = Decodificacion(fecha_Finalizacion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Tarea Pendiente Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarTareaPendienteGrupo(id_Grupo, nombre, descripcion, fecha_Finalizacion, id_Usuario_Cargo);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Tarea Pendiente Agregada Del Grupo ",String.valueOf(id_Grupo)," Con ID ", ((Integer)response.get(0)).toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Usuario(String correo_Electronico, String contrasenia ,String nombre,
        String paterno, String materno, int id_Localidad, String genero, String fecha_Nacimiento, String tipo_Usuario,
        byte[] imagen, double promedio_General,String descripcion, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        correo_Electronico = Decodificacion(correo_Electronico);
        nombre = Decodificacion(nombre);
        paterno = Decodificacion(paterno);
        materno = Decodificacion(materno);
        genero = Decodificacion(genero);
        fecha_Nacimiento = Decodificacion(fecha_Nacimiento);
        tipo_Usuario = Decodificacion(tipo_Usuario);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud("Agregar Nuevo Usuario", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarUsuario(correo_Electronico, contrasenia, 
                        nombre, paterno, materno, id_Localidad, genero, fecha_Nacimiento, tipo_Usuario, 
                        imagen, promedio_General, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Usuario Agregado Con ID ", ((Integer)response.get(0)).toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
            //Enviar mensaje de bienvenida:
            
            try {
                
                // Leer la plantilla
                try (InputStream stream_Entrada = getClass().getResourceAsStream("/recursos/html/bienvenida.html")) {
                    try (BufferedReader lector_Buffered = new BufferedReader(new InputStreamReader(stream_Entrada))) {
                        // Almacenar el contenido de la plantilla en un StringBuffer
                        String linea;
                        mensaje_HTML = new StringBuilder();

                        while ((linea = lector_Buffered.readLine()) != null) {
                            mensaje_HTML.append(linea);
                        }

                    }
                } catch (IOException ex) { 
                    
                }

                // Crear la parte del mensaje HTML
                parte_Cuerpo_MIME_HTML = new MimeBodyPart();
                parte_Cuerpo_MIME_HTML.setContent(mensaje_HTML.toString(), "text/html");

                // Crear el cuerpo del mensaje
                mensaje_MIME = new MimeMessage(sesion);

                // Agregar el asunto al correo
                mensaje_MIME.setSubject("CourseRoom -  Mensaje De Bienvenida.");

                // Agregar quien envía el correo
                mensaje_MIME.setFrom(new InternetAddress("Course_Room@outlook.com", "CourseRoom Mensaje De Bienvenida"));

                // Crear el multiparte para agregar la parte del mensaje anterior
                multiparte = new MimeMultipart();

                // Agregar la parte del mensaje  de recuperación HTML al multiPart
                multiparte.addBodyPart(parte_Cuerpo_MIME_HTML);

                direccion_Internet = new InternetAddress();
                
                contrasenia = Decodificacion(contrasenia);
                
                // Destinatario
                direccion_Internet.setAddress(correo_Electronico);

                // Agregar destinatario al mensaje
                mensaje_MIME.setRecipient(Message.RecipientType.TO, direccion_Internet);

                // Agregar el multiparte al cuerpo del mensaje
                mensaje_MIME.setContent(multiparte);

                // Enviar el mensaje
                try (Transport transporte = sesion.getTransport("smtp")) {

                    byte[] decoded_Correo = Base64.getDecoder().decode("Q291cnNlX1Jvb21Ab3V0bG9vay5jb20=");
                    byte[] decoded_Password = Base64.getDecoder().decode("Y3VjZWlVREc=");
                    String decodificacion_Correo = new String(decoded_Correo);
                    String decodificacion_Password = new String(decoded_Password);

                    String servidor = sesion.getProperty("mail.smtp.host");
                    int puerto = Integer.parseInt(sesion.getProperty("mail.smtp.port"));
                    transporte.connect(servidor, puerto, decodificacion_Correo, decodificacion_Password);

                    transporte.sendMessage(mensaje_MIME, mensaje_MIME.getAllRecipients());

                }
                
            } catch (MessagingException ex) {
                System.out.println("Mensaje De Bienvenida(): " + ex.getMessage());
                
            }
            
        }


        return response;

    }
    
    public Vector<Vector<Object>> Buscar_Chats_Personales(String busqueda, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        busqueda = Decodificacion(busqueda);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Buscar Chats Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_BuscarChatsPersonales(busqueda, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Vacía Chats Personales Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta =
                    respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Chats Personales Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    
    public Vector<Integer> Fecha_Hora_Servidor(String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        Vector<Integer> response = new Vector<>();

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud("Obtener Fecha Y Hora Del Servidor", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        LocalDateTime fecha_Hora_Actual = LocalDateTime.now();

        response.add(fecha_Hora_Actual.getYear());
        response.add(fecha_Hora_Actual.getMonthValue());
        response.add(fecha_Hora_Actual.getDayOfMonth());
        response.add(fecha_Hora_Actual.getHour());
        response.add(fecha_Hora_Actual.getMinute());
        response.add(fecha_Hora_Actual.getSecond());

        //Agregar respuesta:
        respuesta = respuestas.Agregar_Respuesta("Fecha & Hora Entregadas", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return response;
    }

    public byte[] Imagen_Inicio_Sesion(String cliente, String ip) throws MalformedURLException, IOException, SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        byte[] response;

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud("Obtener Imagen Inicio Sesión", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        URL url_Imagen = new URL("https://picsum.photos/600/600");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] chunk = new byte[512];
            int bytesRead;
            try (InputStream stream = url_Imagen.openStream()) {

                while ((bytesRead = stream.read(chunk)) > 0) {
                    outputStream.write(chunk, 0, bytesRead);
                }

                response = outputStream.toByteArray();
            }
            
            
        } catch (IOException e) {
            response = new byte[]{};
        }

        if(response.length > 0){
           //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Imagen Enviada Y Obtenida Desde: https://picsum.photos/600/600", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

       }else{
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Imagen Vacía Enviada", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
       }

        return response;

    }

    public Boolean Recuperar_Credenciales(String correo_Electronico, String cliente, String ip) throws SQLException {

        Boolean response = Boolean.FALSE;
        cliente = Decodificacion(cliente);
        correo_Electronico = Decodificacion(correo_Electronico);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Recuperar Credenciales Del Correo " ,correo_Electronico), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Buscar la contraseña en la base de datos:
        String contrasena = stored_Procedures.sp_ObtenerCredenciales(correo_Electronico);

        if(!contrasena.isEmpty() && !contrasena.isBlank()){
            try {
                
                // Leer la plantilla
                try (InputStream stream_Entrada = getClass().getResourceAsStream("/recursos/html/mensaje.html")) {
                    try (BufferedReader lector_Buffered = new BufferedReader(new InputStreamReader(stream_Entrada))) {
                        // Almacenar el contenido de la plantilla en un StringBuffer
                        String linea;
                        mensaje_HTML = new StringBuilder();

                        while ((linea = lector_Buffered.readLine()) != null) {
                            mensaje_HTML.append(linea);
                        }

                    }
                } catch (IOException ex) { 
                    
                }

                // Crear la parte del mensaje HTML
                parte_Cuerpo_MIME_HTML = new MimeBodyPart();
                parte_Cuerpo_MIME_HTML.setContent(mensaje_HTML.toString(), "text/html");

                // Crear el cuerpo del mensaje
                mensaje_MIME = new MimeMessage(sesion);

                // Agregar el asunto al correo
                mensaje_MIME.setSubject("CourseRoom -  Mensaje De Recuperación.");

                // Agregar quien envía el correo
                mensaje_MIME.setFrom(new InternetAddress("Course_Room@outlook.com", "CourseRoom Mensaje De Recuperación"));

                // Crear el multiparte para agregar la parte del mensaje anterior
                multiparte = new MimeMultipart();

                // Agregar la parte del mensaje  de recuperación HTML al multiPart
                multiparte.addBodyPart(parte_Cuerpo_MIME_HTML);

                direccion_Internet = new InternetAddress();
                
                contrasena = Decodificacion(contrasena);
                
                // Destinatario
                direccion_Internet.setAddress(correo_Electronico);

                // Agregar destinatario al mensaje
                mensaje_MIME.setRecipient(Message.RecipientType.TO, direccion_Internet);

                // Crear la parte del mensaje HTML
                MimeBodyPart mimeBodyPartMensaje = new MimeBodyPart();
                mimeBodyPartMensaje.setFileName("Credenciales.txt");
                mimeBodyPartMensaje.setText("Contraseña: " + contrasena);

                // Agregar la parte del mensaje HTML al multiPart
                multiparte.addBodyPart(mimeBodyPartMensaje);

                // Agregar el multiparte al cuerpo del mensaje
                mensaje_MIME.setContent(multiparte);

                // Enviar el mensaje
                try (Transport transporte = sesion.getTransport("smtp")) {

                    byte[] decoded_Correo = Base64.getDecoder().decode("Q291cnNlX1Jvb21Ab3V0bG9vay5jb20=");
                    byte[] decoded_Password = Base64.getDecoder().decode("Y3VjZWlVREc=");
                    String decodificacion_Correo = new String(decoded_Correo);
                    String decodificacion_Password = new String(decoded_Password);

                    String servidor = sesion.getProperty("mail.smtp.host");
                    int puerto = Integer.parseInt(sesion.getProperty("mail.smtp.port"));
                    transporte.connect(servidor, puerto, decodificacion_Correo, decodificacion_Password);

                    transporte.sendMessage(mensaje_MIME, mensaje_MIME.getAllRecipients());

                }
                
                response = Boolean.TRUE;

            } catch (MessagingException | UnsupportedEncodingException ex) {
                System.out.println("Recuperar_Credenciales(): " + ex.getMessage());
                
                //Agregar respuesta:
                respuesta
                        = respuestas.Agregar_Respuesta(Concatenar("Credenciales Enviadas Al Correo ", correo_Electronico), cliente, ip);

                if (respuesta.first() == -1) {
                    System.err.println(respuesta.second());
                }
            }
            //Agregar respuesta:
            
        }
        
        return response;

    }

    public Vector<String> Obtener_Estados(String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud("Obtener Estados", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<String> response = stored_Procedures.sp_ObtenerEstados();

        if(response.size() > 0){
        
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Estados Enviados", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }else{
            respuesta
                    = respuestas.Agregar_Respuesta("Estados Vacíos Enviados", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }

    public Vector<Vector<Object>> Obtener_Localidades_Por_Estado(String estado, String cliente, String ip) throws SQLException {

        estado = Decodificacion(estado);
        ip = Decodificacion(ip);
        cliente = Decodificacion(cliente);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Localidades Del Estado De ", estado), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<Vector<Object>> response = stored_Procedures.sp_ObtenerLocalidadesPorEstado(estado);

        if(response.size() > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Localidades Enviadas Del Estado De ",estado), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Localidades Vacías Enviadas", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }

    
    public Vector<Object> Obtener_Usuario(String correo_Electronico, String contrasenia, String cliente, String ip){
        
        Vector<Object> response;
        
        correo_Electronico = Decodificacion(correo_Electronico);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);
        
        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Inicio De Sesión Del Correo ",correo_Electronico), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response = stored_Procedures.sp_ObtenerUsuario(correo_Electronico, contrasenia);
        
        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Sesión Iniciada Del Usuario", ((Integer)response.get(0)).toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }
        
        return response;
        
    }
    
    
    public Vector<Object> Obtener_Datos_Perfil(int id_Usuario,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Perfil Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<Object> response = stored_Procedures.sp_ObtenerDatosPerfil(id_Usuario);

        if(response.size() > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Enviados Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Vacíos Enviados Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public byte[] Obtener_Imagen_Perfil(int id_Usuario,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Imagen Perfil Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        byte[] response = stored_Procedures.sp_ObtenerImagenPerfil(id_Usuario);

        if(response.length > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen Perfil Enviados Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen Perfil Vacíos Enviados Al Usuario ",String.valueOf(id_Usuario)),  cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    
    public Vector<Object> Cerrar_Sesion(int id_Usuario, int id_Sesion, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Cerrar Sesión ",String.valueOf(id_Sesion)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_CerrarSesion(id_Usuario,id_Sesion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta((String)response.get(1), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Sesión Cerrada ",String.valueOf(id_Sesion)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Sesiones_Usuario(int id_Usuario, String cliente, String ip) throws SQLException {

        ip = Decodificacion(ip);
        cliente = Decodificacion(cliente);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Sesiones Del usuario ", String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<Vector<Object>> response = stored_Procedures.sp_ObtenerSesiones(id_Usuario);

        if(response.size() > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Sesiones Enviadas Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Sesiones Vacías Enviadas Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    
    public void Cerrar_Conexion(){
        stored_Procedures.Cerrar_Conexion();
    }
    
}
