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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
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
public class Metodos {
    
    private Session sesion;
    private StringBuilder mensaje_HTML;
    private MimeBodyPart parte_Cuerpo_MIME_HTML;
    private MimeMessage mensaje_MIME;
    private InternetAddress direccion_Internet;
    private Multipart multiparte;
    private Stored_Procedures stored_Procedures;
    private Servidor_DB respuestas;
    
    private Metodos(){
       
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
    
    private void Enviar_Aviso(int id_Usuario){
        
        String simpleMessage = String.valueOf(id_Usuario);
        byte bandera = 0;
        
        byte[] buffer = new byte[16];
        
        //Usuario:
        buffer[0] = (byte) simpleMessage.length();
			
        //Creamos un valor auxiliar (copia) que nos obtendrá los bytes de la cadena.
        byte[] copia = simpleMessage.getBytes();

        //Creamos la copia del valor auxiliar hacia nuestro arreglo de bytes
        for(int i = 1; i <= simpleMessage.length();i++){
            buffer[i] = copia[i-1];
        }
        
        while(bandera < 60){
            try(DatagramSocket socketSender = new DatagramSocket()){

                DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length,
                        InetAddress.getByName("localhost")
                        ,9001);

                socketSender.send(datagramPacket);
                bandera = 100;
            } catch (SocketException ex) {
                System.err.println(ex.getMessage());
                bandera++;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                bandera++;
            }    
        }
    }
        
    public static Metodos getInstance() {
        return SolicitudesHolder.INSTANCE;
    }
    
    private static class SolicitudesHolder {

        private static final Metodos INSTANCE = new Metodos();
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
    
    public Vector<Object> Abandonar_Curso(int id_Curso, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Abandonar Curso ",String.valueOf(id_Curso)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_AbandonarCurso(id_Curso, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al profesor del curso:
            Enviar_Aviso((int)response.get(0));

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Curso Abandonado ",String.valueOf(id_Curso)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            
            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosGrupo(id_Grupo, id_Usuario);
            
            //Enviar aviso a los demás del grupo:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }
            
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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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

        
        response
                = stored_Procedures.sp_ActualizarDatosAutenticacion(id_Usuario, correo_Electronico, contrasenia);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Object> Actualizar_Datos_Generales_Curso(int id_Curso, String nombre, 
            String descripcion, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Datos Generales Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response = stored_Procedures.sp_ActualizarDatosGeneralesCurso(id_Curso, nombre, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Generales Actualizados Del Curso ", String.valueOf(id_Curso)), cliente, ip);

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

        
        response
                = stored_Procedures.sp_ActualizarDatosGeneralesGrupo(id_Grupo, nombre, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Object> Actualizar_Datos_Generales_Tarea(int id_Tarea, String nombre, 
            String descripcion, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Actualizar Datos Generales De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response = stored_Procedures.sp_ActualizarDatosGeneralesGrupo(id_Tarea, nombre, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Generales Actualizados De La Tarea ", String.valueOf(id_Tarea)), cliente, ip);

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

        
        response
                = stored_Procedures.sp_ActualizarDatosPersonales(id_Usuario,nombre, paterno, materno, genero, fecha_Nacimiento,id_Localidad);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);
            
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

        
        response
                = stored_Procedures.sp_ActualizarInformacionExtra(id_Usuario,tipo_Usuario, promedio_General, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Object> Agregar_Chat(int id_Usuario, int id_Usuario_Receptor, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Chat Entre Los Usuarios ",String.valueOf(id_Usuario), " & ",String.valueOf(id_Usuario_Receptor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_AgregarChat(id_Usuario, id_Usuario_Receptor);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           //Enviar aviso usuario receptor:
           Enviar_Aviso(id_Usuario_Receptor);

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Chat Agregado Con ID ", String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Curso(String nombre, String descripcion, int id_Profesor, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Curso Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response = stored_Procedures.sp_AgregarCurso(nombre, descripcion, id_Profesor);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Curso Agregado Con ID ", String.valueOf(response.elementAt(0))), cliente, ip);

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

        
        response
                = stored_Procedures.sp_AgregarInteres(id_Usuario, id_Tematica);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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

        response
                = stored_Procedures.sp_AgregarPregunta(id_Usuario, pregunta, descripcion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Object> Agregar_Respuesta_Cuestionario(int id_Curso,int id_Usuario, int id_Pregunta, String respuesta, String cliente, String ip){
        
        Vector<Object> response;
        
        respuesta = Decodificacion(respuesta);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> resultado = respuestas.Agregar_Solicitud(Concatenar("Agregar Respuesta Del Usuario ",String.valueOf(id_Usuario), " En El Cuestionario Del Curso ",String.valueOf(id_Curso), " De La Pregunta ", String.valueOf(id_Pregunta)), cliente, ip);

        if (resultado.first() == -1) {
            System.err.println(resultado.second());
        }

        response
                = stored_Procedures.sp_AgregarRespuestaCuestionario(id_Curso, id_Usuario, id_Pregunta, respuesta);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            resultado
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (resultado.first() == -1) {
                System.err.println(resultado.second());
            }

        } else {
            

            //Agregar respuesta:
            resultado
                    = respuestas.Agregar_Respuesta(Concatenar("Agregada Respuesta Del Usuario ",String.valueOf(id_Usuario), " En El Cuestionario Del Curso ",String.valueOf(id_Curso), " De La Pregunta ", String.valueOf(id_Pregunta)), cliente, ip);

            if (resultado.first() == -1) {
                System.err.println(resultado.second());
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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), uuid, ip);

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
    
    public Vector<Object> Agregar_Tarea(int id_Curso, String nombre, String descripcion, String fecha_Entrega, String cliente, String ip){
        
        Vector<Object> response;
        
        nombre = Decodificacion(nombre);
        descripcion = Decodificacion(descripcion);
        fecha_Entrega = Decodificacion(fecha_Entrega);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Tarea Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarTarea(id_Curso, nombre, descripcion, fecha_Entrega);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Tarea Agregada Del Curso ",String.valueOf(id_Curso)," Con ID ", ((Integer)response.get(0)).toString()), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            
            //Enviar aviso usuario a cargo:
           Enviar_Aviso(id_Usuario_Cargo);
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Tarea Pendiente Agregada Del Grupo ",String.valueOf(id_Grupo)," Con ID ", ((Integer)response.get(0)).toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Agregar_Tarea_Usuario(int id_Curso, int id_Tarea, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Tarea Usuario ", String.valueOf(id_Tarea), " Del Curso ", String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarTareaUsuario(id_Curso, id_Tarea);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Agregar Tarea Usuario ", String.valueOf(id_Tarea), " Del Curso ", String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Agregar_Tematica(int id_Curso, int id_Tematica, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Agregar Tematica Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_AgregarTematica(id_Curso, id_Tematica);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Tematica Agregada ",String.valueOf(id_Tematica)," Del Curso ",String.valueOf(id_Curso)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Vector<Object>> Buscar_Grupos(String busqueda, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        busqueda = Decodificacion(busqueda);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Buscar Grupos Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_BuscarGrupos(busqueda, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Vacía Grupos Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           

            //Agregar respuesta:
            respuesta =
                    respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Grupos Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Buscar_Preguntas(String busqueda, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        busqueda = Decodificacion(busqueda);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud("Buscar Preguntas ", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_BuscarPreguntas(busqueda);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Enviada Busqueda Vacía Preguntas", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta =
                    respuestas.Agregar_Respuesta("Enviada Busqueda Preguntas", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Buscar_Tareas(String busqueda, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        busqueda = Decodificacion(busqueda);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Buscar Tareas Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_BuscarTareas(busqueda, id_Usuario);

        if (response.isEmpty()) {
            
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Vacía Tareas Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta =
                    respuestas.Agregar_Respuesta(Concatenar("Enviada Busqueda Tareas Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Calificar_Tarea(int id_Tarea, int id_Usuario, double calificacion, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Calificar Tarea ", String.valueOf(id_Tarea), " Del Usuario ", String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_CalificarTarea(id_Tarea, id_Usuario, calificacion);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Calificar Tarea ", String.valueOf(id_Tarea), " Del Usuario ", String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Cambiar_Estatus_Tarea_Pendiente(int id_Tarea_Pendiente, String nuevo_Estatus, int id_Usuario, String cliente, String ip){
        
        Vector<Object> response;
        
        nuevo_Estatus = Decodificacion(nuevo_Estatus);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Cambiar Estatus Tarea Pendiente ",String.valueOf(id_Tarea_Pendiente)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_CambiarEstatusTareaPendienteGrupo(id_Tarea_Pendiente, nuevo_Estatus, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Estatus Cambiado De La Tarea Pendiente ", String.valueOf(id_Tarea_Pendiente)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Object> Enrolar_Usuario_Curso(int id_Curso, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enrolar Usuario ",String.valueOf(id_Usuario)," Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_EnrolarUsuarioCurso(id_Curso,id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al profesor del curso:
            Enviar_Aviso((int)response.get(0));
           

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Curso Enrolado ",String.valueOf(id_Curso)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Entregar_Tarea_Usuario(int id_Tarea, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Entregar Tarea ",String.valueOf(id_Tarea)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_EntregarTareaUsuario(id_Tarea,id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al profesor del curso:
            Enviar_Aviso((int)response.get(0));

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Tarea Entregada ",String.valueOf(id_Tarea)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Enviar_Archivo_Adjunto_Tarea(int id_Tarea, String nombre_Archivo, byte[] archivo, String extension, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        nombre_Archivo = Decodificacion(nombre_Archivo);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Archivo Adjunto De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_EnviarArchivoAdjuntoTarea(id_Tarea, nombre_Archivo, archivo, extension);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Archivo Adjunto Registrado Con ID ",String.valueOf((int)response.get(0))," De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Enviar_Archivo_Compartido_Grupo(int id_Grupo, int id_Usuario, String nombre_Archivo, byte[] archivo, String extension, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        nombre_Archivo = Decodificacion(nombre_Archivo);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Archivo Compartido Al Grupo ",String.valueOf(id_Grupo)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarArchivoCompartidoGrupo(id_Grupo, id_Usuario, nombre_Archivo, archivo, extension);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosGrupo(id_Grupo, id_Usuario);
            
            //Enviar aviso a los demás del grupo:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Archivo Compartido Del Grupo ",String.valueOf(id_Grupo)," Agregado Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Archivo_Subido_Tarea(int id_Tarea, int id_Usuario, String nombre_Archivo, byte[] archivo, String extension, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        nombre_Archivo = Decodificacion(nombre_Archivo);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Archivo Subido De La Tarea ",String.valueOf(id_Tarea)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_EnviarArchivoSubidoTarea(id_Tarea, id_Usuario, nombre_Archivo, archivo, extension);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Archivo Subido De La Tarea ",String.valueOf(id_Tarea)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Enviar_Material_Curso(int id_Curso, int id_Usuario, String nombre_Archivo, byte[] archivo, String extension, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        nombre_Archivo = Decodificacion(nombre_Archivo);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Material Del Usuario ",String.valueOf(id_Usuario)," Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_EnviarMaterialCurso(id_Curso, id_Usuario, nombre_Archivo, archivo, extension);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosCurso(id_Curso, id_Usuario);
            
            //Enviar aviso a los demás del curso:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Material Enviado Del Usuario ",String.valueOf(id_Usuario)," Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Enviar_Mensaje_Chat(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor, int id_Chat,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        mensaje = Decodificacion(mensaje);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Mensaje Al Chat ",String.valueOf(id_Chat)," Por El Usuario ",String.valueOf(id_Usuario_Emisor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarMensajeChat(mensaje, archivo, extension, id_Usuario_Emisor, id_Chat);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al usuario:
            Enviar_Aviso((int)response.get(0));

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Mensaje Enviado Al Chat ",String.valueOf(id_Chat)," Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Mensaje_Curso(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor, int id_Curso,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        mensaje = Decodificacion(mensaje);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Mensaje Al Curso ",String.valueOf(id_Curso)," Por El Usuario ",String.valueOf(id_Usuario_Emisor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarMensajeCurso(mensaje, archivo, extension, id_Usuario_Emisor, id_Curso);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosCurso(id_Curso, id_Usuario_Emisor);
            
            //Enviar aviso a los demás del curso:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }    
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Mensaje Enviado Al Curso ",String.valueOf(id_Curso)," Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Mensaje_Grupo(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor, int id_Grupo,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        mensaje = Decodificacion(mensaje);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Mensaje Al Grupo ",String.valueOf(id_Grupo)," Por El Usuario ",String.valueOf(id_Usuario_Emisor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarMensajeGrupo(mensaje, archivo, extension, id_Usuario_Emisor, id_Grupo);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosGrupo(id_Grupo, id_Usuario_Emisor);
            
            //Enviar aviso a los demás del grupo:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Mensaje Enviado Al Grupo ",String.valueOf(id_Grupo)," Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Mensaje_Pregunta(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor, int id_Pregunta,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        mensaje = Decodificacion(mensaje);
        extension= Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Mensaje A La Pregunta ",String.valueOf(id_Pregunta)," Por El Usuario ",String.valueOf(id_Usuario_Emisor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarMensajePregunta(mensaje, archivo, extension, id_Usuario_Emisor, id_Pregunta);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al usuario:
            Enviar_Aviso((int)response.get(0));

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Mensaje Enviado A La Pregunta ",String.valueOf(id_Pregunta)," Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Mensaje_Tarea(String mensaje, byte[] archivo, String extension, int id_Usuario_Emisor, int id_Tarea,String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        mensaje = Decodificacion(mensaje);
        extension = Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Mensaje A La Tarea ",String.valueOf(id_Tarea)," Por El Usuario ",String.valueOf(id_Usuario_Emisor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarMensajeTarea(mensaje, archivo, extension, id_Usuario_Emisor, id_Tarea);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            
            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosTarea(id_Tarea, id_Usuario_Emisor);
            
            //Enviar aviso a los demás de la tarea:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Mensaje Enviado A La Tarea ",String.valueOf(id_Tarea)," Con ID ",String.valueOf(response.elementAt(0))), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;

    }
    
    public Vector<Object> Enviar_Retroalimentacion_Tarea(int id_Tarea, int id_Usuario, String retroalimentacion,
            String nombre_Archivo, byte[] archivo, String extension,String cliente, String ip){
        Vector<Object> response;
        
        retroalimentacion = Decodificacion(retroalimentacion);
        nombre_Archivo = Decodificacion(nombre_Archivo);
        extension = Decodificacion(extension);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Enviar Retroalimentación De La Tarea ",String.valueOf(id_Tarea)," Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_EnviarRetroalimentacionTarea(id_Tarea, id_Usuario, retroalimentacion,
            nombre_Archivo, archivo, extension);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

                Enviar_Aviso(id_Usuario);
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviar Retroalimentación De La Tarea ",String.valueOf(id_Tarea)," Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

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

    public Vector<Object> Finalizar_Curso(int id_Curso, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Finalizar Curso ",String.valueOf(id_Curso)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_FinalizarCursoUsuario(id_Curso, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Enviar aviso al profesor del curso:
            Enviar_Aviso((int)response.get(0));

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Curso Finalizado ",String.valueOf(id_Curso)," Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;

    }
    
    public Vector<Object> Marcar_Pregunta_Solucionada(int id_Usuario,int id_Pregunta, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Marcar Pregunta ",String.valueOf(id_Pregunta), " Como Solucionada"), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_MarcarPreguntaSolucionada(id_Usuario, id_Pregunta);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Pregunta ", String.valueOf(id_Pregunta), " Marcada Como Solucionada"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Adjunto_Tarea(int id_Archivo_Adjunto, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Adjunto ",String.valueOf(id_Archivo_Adjunto)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoAdjuntoTarea(id_Archivo_Adjunto);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Adjunto Vacío ",String.valueOf(id_Archivo_Adjunto)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Adjunto ",String.valueOf(id_Archivo_Adjunto)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Compartido_Grupo(int id_Archivo_Compartido, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Compartido ",String.valueOf(id_Archivo_Compartido)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoCompartidoGrupo(id_Archivo_Compartido);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Compartido Vacío ",String.valueOf(id_Archivo_Compartido)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Compartido ",String.valueOf(id_Archivo_Compartido)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Mensaje_Chat(int id_Mensaje, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Mensaje Chat ",String.valueOf(id_Mensaje)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoMensajeChat(id_Mensaje);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Chat Vacío ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Chat ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Mensaje_Curso(int id_Mensaje, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Mensaje Curso ",String.valueOf(id_Mensaje)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoMensajeCurso(id_Mensaje);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Curso Vacío ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Curso ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Mensaje_Grupo(int id_Mensaje, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Mensaje Grupo ",String.valueOf(id_Mensaje)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoMensajeGrupo(id_Mensaje);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Grupo Vacío ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Grupo ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Mensaje_Pregunta(int id_Mensaje, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Mensaje Pregunta ",String.valueOf(id_Mensaje)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoMensajePregunta(id_Mensaje);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Pregunta Vacío ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Pregunta ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Mensaje_Tarea(int id_Mensaje, String cliente, String ip) throws SQLException{
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Mensaje Tarea ",String.valueOf(id_Mensaje)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoMensajeTarea(id_Mensaje);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Tarea Vacío ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Mensaje Tarea ",String.valueOf(id_Mensaje)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Retroalimentacion_Tarea(int id_Retroalimentacion, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Retroalimentación Tarea ",String.valueOf(id_Retroalimentacion)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoRetroalimentacionTarea(id_Retroalimentacion);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Retroalimentación Tarea Vacío ",String.valueOf(id_Retroalimentacion)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Retroalimentación Tarea ",String.valueOf(id_Retroalimentacion)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Object> Obtener_Archivo_Subido_Tarea(int id_Archivo_Subido, int id_Usuario, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivo Subido Tarea ",String.valueOf(id_Archivo_Subido), " Del Usuario", String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerArchivoSubidoTarea(id_Archivo_Subido, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Subido Tarea Vacío ",String.valueOf(id_Archivo_Subido), " Al Usuario", String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Archivo Subido Tarea ",String.valueOf(id_Archivo_Subido)," Al Usuario", String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }
    
    public Vector<Vector<Object>> Obtener_Archivos_Adjuntos_Tarea(int id_Tarea, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivos Adjuntos De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerArchivosAdjuntosTarea(id_Tarea);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Adjuntos Vacios De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Adjuntos De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Archivos_Compartidos_Grupo(int id_Grupo, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivos Compartidos DeL Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerArchivosCompartidosGrupo(id_Grupo);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Compartidos Vacios Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Compartidos Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Archivos_Subidos_Tarea(int id_Tarea,int id_Usuario,String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Archivos Subidos Del Usuario ",String.valueOf(id_Usuario)," En La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerArchivosSubidosTarea(id_Tarea, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Subidos Vacios Del Usuario ",String.valueOf(id_Usuario)," En La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Archivos Subidos Subidos Del Usuario ",String.valueOf(id_Usuario)," En La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Avisos(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Avisos Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerAvisos(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Avisos Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Avisos Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Chats_Personales(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Chats Personales Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerChatsPersonales(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Chats Personales Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Chats Personales Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Configuraciones(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Configuraciones Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerConfiguraciones(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Configuraciones Vacias Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Configuraciones Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Cursos_Actuales(int id_Usuario,String cliente, String ip){
        
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Cursos Actuales Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerCursosActuales(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Cursos Actuales Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Cursos Actuales Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;
        
    }
    
    public Vector<Vector<Object>> Obtener_Cursos_Creados_Profesor(int id_Profesor, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Cursos Creados Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerCursosCreadosProfesor(id_Profesor);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Cursos Creados Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Cursos Creados Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Cursos_Finalizados(int id_Usuario, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Cursos Finalizados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerCursosFinalizados(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviados Los Cursos Finalizados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Los Cursos Finalizados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Cursos_Nuevos(String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Cursos Nuevos Del Usuario ",cliente), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerCursosNuevos();

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviados Los Cursos Nuevos Del Usuario ",cliente), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviados Los Cursos Nuevos Del Usuario ",cliente), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;
    }
    
    public Vector<Object> Obtener_Datos_Generales_Chat_Personal(int id_Chat, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales Del Chat Personal ",String.valueOf(id_Chat)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesChatPersonal(id_Chat, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios Del Chat Personal ",String.valueOf(id_Chat)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Del Chat Personal ",String.valueOf(id_Chat)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Grupo(int id_Grupo, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesGrupo(id_Grupo);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Pregunta(int id_Pregunta, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesPregunta(id_Pregunta);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Tarea(int id_Tarea, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesTarea(id_Tarea, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Tarea_Pendiente(int id_Tarea_Pendiente, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales De La Tarea Pendiente ",String.valueOf(id_Tarea_Pendiente)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesTareaPendiente(id_Tarea_Pendiente);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales Vacios De La Tarea Pendiente ",String.valueOf(id_Tarea_Pendiente)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales De La Tarea Pendiente ",String.valueOf(id_Tarea_Pendiente)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Datos_Generales_Tarea_Profesor(int id_Tarea, int id_Profesor, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Generales De La Tarea ",String.valueOf(id_Tarea), "Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDatosGeneralesTareaProfesor(id_Tarea, id_Profesor);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales De La Tarea ",String.valueOf(id_Tarea), "Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Datos Generales De La Tarea ",String.valueOf(id_Tarea), "Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

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

        if(response.isEmpty()){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Vacios Enviados Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Enviados Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Obtener_Datos_Perfil_Chat_Personal(int id_Usuario,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Datos Perfil Chat Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<Object> response = stored_Procedures.sp_ObtenerDatosPerfilChatPersonal(id_Usuario);

        if(response.isEmpty()){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Chat Vacíos Enviados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Perfil Chat Enviados Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Desempeno_Curso(int id_Curso, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Desempeño Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDesempenoCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Vacio Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Desempeno_Usuario_Curso(int id_Curso, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Desempeño Del Usuario ",String.valueOf(id_Usuario), " En El Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDesempenoUsuarioCurso(id_Curso, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Vacio Del Usuario ",String.valueOf(id_Usuario)," En El Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Del Usuario ",String.valueOf(id_Usuario)," En El Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Desempeno_Usuario(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Desempeño Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerDesempenoUsuario(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Vacio Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Desempeño Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Entregas_Tareas_Por_Calificar(int id_Tarea, int id_Profesor, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Entregas De Tareas ",String.valueOf(id_Tarea), " Por Clificar El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerEntregasTareaPorCalificar(id_Tarea, id_Profesor);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Vacio De Entregas De Tareas ",String.valueOf(id_Tarea)," Por Clificar El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviada Entregas De Tareas ",String.valueOf(id_Tarea)," Por Clificar El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
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

        if(response.isEmpty()){
        
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

    public Vector<String> Obtener_Fecha_Actualizacion_Tarea_Subida(int id_Tarea, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<String> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Fecha De Actualizacion De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerFechaActualizacionTareaSubida(id_Tarea,id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Obtenida La Fecha De Actualizacion De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Obtenida La Fecha De Actualizacion De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Grupos(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Grupos Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerGrupos(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Grupos Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Grupos Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Grupos_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Grupos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerGruposCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Obtenidos Grupos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Obtenidos Grupos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }   
            
        return response;

    }
    
    public byte[] Obtener_Imagen_Chat_Personal(int id_Chat, int id_Usuario,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Imagen Del Chat ",String.valueOf(id_Chat)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        byte[] response = stored_Procedures.sp_ObtenerImagenChatPersonal(id_Chat, id_Usuario);

        if(response.length > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen Del Chat ",String.valueOf(id_Chat)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Vacíos De Imagen Del Chat ",String.valueOf(id_Chat)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public byte[] Obtener_Imagen_Curso(int id_Curso,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Imagen Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        byte[] response = stored_Procedures.sp_ObtenerImagenCurso(id_Curso);

        if(response.length > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen Del Curso ",String.valueOf(id_Curso)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Vacíos De Imagen Del Curso ",String.valueOf(id_Curso)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public byte[] Obtener_Imagen_Grupo(int id_Grupo,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Imagen Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        byte[] response = stored_Procedures.sp_ObtenerImagenGrupo(id_Grupo);

        if(response.length > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen Del Grupo ",String.valueOf(id_Grupo)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Vacíos De Imagen Del Grupo ",String.valueOf(id_Grupo)," Enviados"), cliente, ip);

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
    
    public byte[] Obtener_Imagen_Pregunta(int id_Pregunta,String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Imagen De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        byte[] response = stored_Procedures.sp_ObtenerImagenPregunta(id_Pregunta);

        if(response.length > 0){
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos De Imagen De La Pregunta ",String.valueOf(id_Pregunta)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }else{
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Datos Vacíos De La Imagen De La Pregunta ",String.valueOf(id_Pregunta)," Enviados"), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Intereses_Usuario(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Intereses Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerInteresesUsuario(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Intereses Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Intereses Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Localidades_Por_Estado(String estado, String cliente, String ip) throws SQLException {

        Vector<Vector<Object>> response;
        
        estado = Decodificacion(estado);
        ip = Decodificacion(ip);
        cliente = Decodificacion(cliente);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Localidades Del Estado De ", estado), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response = stored_Procedures.sp_ObtenerLocalidadesPorEstado(estado);

        if(response.isEmpty()){
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

    public Vector<Vector<Object>> Obtener_Materiales_Subidos_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Materiales Subidos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMaterialesSubidosCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Materiales Subidos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Materiales Subidos Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Object> Obtener_Material_Subido_Curso(int id_Material_Subido, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Material Subido Curso ",String.valueOf(id_Material_Subido)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_ObtenerMaterialSubidoCurso(id_Material_Subido);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviado Material Subido Curso Vacío ",String.valueOf(id_Material_Subido)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                  = respuestas.Agregar_Respuesta(Concatenar("Enviado Material Subido Curso ",String.valueOf(id_Material_Subido)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }


        return response;
        
    }

    public Vector<Vector<Object>> Obtener_Mensajes_Chat(int id_Chat, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Mensajes Del Chat ",String.valueOf(id_Chat)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMensajesChat(id_Chat);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Vacios Del Chat ",String.valueOf(id_Chat)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Del Chat ",String.valueOf(id_Chat)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Mensajes_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Mensajes Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMensajesCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Vacios Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Mensajes_Grupo(int id_Grupo, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Mensajes Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMensajesGrupo(id_Grupo);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Vacios Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Mensajes_Pregunta(int id_Pregunta, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Mensajes De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMensajesPregunta(id_Pregunta);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Vacios De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes De La Pregunta ",String.valueOf(id_Pregunta)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Mensajes_Tarea(int id_Tarea, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Mensajes De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMensajesTarea(id_Tarea);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes Vacios De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Mensajes De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Miembros_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Miembros Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerMiembrosCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviados Miembros Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviados Miembros Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Miembros_Grupo(int id_Grupo, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Miembros Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerMiembrosGrupo(id_Grupo);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Miembros Vacios Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Miembros Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Preguntas(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Preguntas Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerPreguntas(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Preguntas Vacios Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Preguntas Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Retroalimentaciones_Tarea(int id_Tarea, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Retroalimentaciones De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerRetroalimentacionesTarea(id_Tarea, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Retroalimentaciones Vacias De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Retroalimentaciones De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Sesiones(int id_Usuario, String cliente, String ip) throws SQLException {

        Vector<Vector<Object>> response;
        ip = Decodificacion(ip);
        cliente = Decodificacion(cliente);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Sesiones Del usuario ", String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response = stored_Procedures.sp_ObtenerSesiones(id_Usuario);

        if(response.isEmpty()){
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
    
    public Vector<Vector<Object>> Obtener_Tareas_Creadas(int id_Profesor, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Creadas Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTareasCreadas(id_Profesor);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Vacias Creadas Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Creadas Por El Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
             
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Curso(int id_Curso, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Del Curso ",String.valueOf(id_Curso), " Del Estudiante ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerTareasCurso(id_Curso, id_Usuario);
        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Estudiante ",String.valueOf(id_Usuario), " Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Estudiante ",String.valueOf(id_Usuario), " Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Curso_Profesor(int id_Curso, int id_Profesor, String cliente, String ip) throws SQLException, IOException {
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Del Curso ",String.valueOf(id_Curso), " Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerTareasCursoProfesor(id_Curso, id_Profesor);
        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Curso ",String.valueOf(id_Curso), " Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Curso ",String.valueOf(id_Curso), " Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Estudiante(int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Del Estudiante ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTareasEstudiante(id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Vacios Del Estudiante ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Estudiante ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Mes(int mes, int id_Usuario, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Del Usuario ",String.valueOf(id_Usuario), " Del Mes ", String.valueOf(mes)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTareasMes(mes, id_Usuario);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Vacias Del Usuario ",String.valueOf(id_Usuario), " Del Mes ", String.valueOf(mes)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Del Estudiante ",String.valueOf(id_Usuario), " Del Mes ", String.valueOf(mes)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Pendientes_Grupo(int id_Grupo, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Pendientes Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTareasPendientesGrupo(id_Grupo);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Pendientes Vacias Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Pendientes Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Tareas_Por_Calificar(int id_Profesor, String cliente, String ip){
        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tareas Por Calificar ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTareasPorCalificar(id_Profesor);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Por Calificar Vacias ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviadas Tareas Por Calificar ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }           
            
        return response;
    }
    
    public Vector<Vector<Object>> Obtener_Tematicas(String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud("Obtener Tematicas ", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerTematicas();

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta("Enviadas Temáticas Vacias", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta("Enviadas Temáticas ", cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public Vector<Vector<Object>> Obtener_Tematicas_Curso(int id_Curso, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Tematicas Del Curso",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response = stored_Procedures.sp_ObtenerTematicasCurso(id_Curso);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Enviadas Temáticas Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta  = respuestas.Agregar_Respuesta(Concatenar("Enviadas Temáticas Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }    
            
        return response;

    }
    
    public Vector<Object> Obtener_Ultimo_Aviso(int id_Usuario, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);
        
        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Último Aviso Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response = stored_Procedures.sp_ObtenerUltimoAviso(id_Usuario);
        
        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviando Último Aviso Vácio Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviando Último Aviso Al Usuario ",String.valueOf(id_Usuario)), cliente, ip);

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
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

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
    
    public Vector<Vector<Object>> Obtener_Usuarios_Chatear(String busqueda, String cliente, String ip) throws SQLException, IOException {

        Vector<Vector<Object>> response;
        
        busqueda = Decodificacion(busqueda);
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Obtener Usuarios Para Chatear De La Búsqueda ",busqueda), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Agregar Usuario:
        response
                = stored_Procedures.sp_ObtenerUsuariosParaChatear(busqueda);

        if (response.isEmpty()) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Usuarios Para Chatear Vacios De La Búsqueda ",busqueda), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta 
                    = respuestas.Agregar_Respuesta(Concatenar("Enviados Usuarios Para Chatear De La Búsqueda ",busqueda), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
            
        }
            
            
        return response;

    }
    
    public boolean Recuperar_Credenciales(String correo_Electronico, String cliente, String ip) throws SQLException {

        boolean response = false;
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
                
                response = true;

            } catch (MessagingException | UnsupportedEncodingException ex) {
                System.out.println("Recuperar_Credenciales(): " + ex.getMessage());
                
                //Agregar respuesta:
                respuesta
                        = respuestas.Agregar_Respuesta(Concatenar("Credenciales Enviadas Al Correo ", correo_Electronico), cliente, ip);

                if (respuesta.first() == -1) {
                    System.err.println(respuesta.second());
                }
            } 
            
            
        }
        
        return response;

    }
    
    public Vector<Object> Remover_Archivo_Adjunto_Tarea(int id_Archivo_Adjunto, int id_Tarea, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Archivo Adjunto ",String.valueOf(id_Archivo_Adjunto), " De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverArchivoAdjuntoTarea(id_Archivo_Adjunto, id_Tarea);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Archivo Adjunto ",String.valueOf(id_Archivo_Adjunto)," Removido De La Tarea ",String.valueOf(id_Tarea)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }
        
        return response;
    }

    public Vector<Object> Remover_Archivo_Compartido_Grupo(int id_Archivo_Compartido, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Archivo Compartido ",String.valueOf(id_Archivo_Compartido), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverArchivoCompartidoGrupo(id_Archivo_Compartido, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Archivo Compartido ",String.valueOf(id_Archivo_Compartido)," Removido Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Archivo_Subido_Tarea(int id_Archivo_Subido, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Archivo Subido ",String.valueOf(id_Archivo_Subido), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverArchivoSubidoTarea(id_Archivo_Subido, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Archivo Subido ",String.valueOf(id_Archivo_Subido)," Removido Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Chat_Personal(int id_Chat, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Chat Personal ",String.valueOf(id_Chat), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverChatPersonal(id_Chat, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Chat Personal ",String.valueOf(id_Chat)," Removido Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Curso(int id_Curso, int id_Profesor, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Curso ",String.valueOf(id_Curso), " Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response
                = stored_Procedures.sp_RemoverCurso(id_Curso, id_Profesor);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Curso ",String.valueOf(id_Curso)," Removido Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Remover_Grupo(int id_Grupo, int id_Curso, String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Grupo ",String.valueOf(id_Grupo), " Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response
                = stored_Procedures.sp_RemoverGrupo(id_Grupo, id_Curso);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Grupo ",String.valueOf(id_Grupo)," Removido Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Remover_Interes_Usuario(int id_Tematica, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Temática ",String.valueOf(id_Tematica), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverInteresUsuario(id_Tematica, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Temática ",String.valueOf(id_Tematica)," Removido Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Material_Curso(int id_Material_Subido, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Material ",String.valueOf(id_Material_Subido), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }
        
        response= stored_Procedures.sp_RemoverMaterialCurso(id_Material_Subido, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            

            //Agregar respuesta:
            respuesta = respuestas.Agregar_Respuesta(Concatenar("Removido El Material ",String.valueOf(id_Material_Subido), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Miembro_Curso(int id_Curso, int id_Usuario, String cliente, String ip){
        Vector<Object> response;

        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Miembro ", String.valueOf(id_Usuario), " Del Curso ", String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response
                = stored_Procedures.sp_RemoverMiembroCurso(id_Curso, id_Usuario);

        if ((Integer) response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String) response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Miembro ", String.valueOf(id_Usuario), " Removido Del Curso ", String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
    }
    
    public Vector<Object> Remover_Voto_Miembro_Grupo(int id_Grupo, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Miembro Por Voto ",String.valueOf(id_Usuario), " Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverPorVotoMiembroGrupo(id_Grupo, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
           
            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Agregado Voto Del Miembro ",String.valueOf(id_Usuario), " Del Grupo ",String.valueOf(id_Grupo)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Pregunta(int id_Pregunta, int id_Usuario , String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Pregunta ",String.valueOf(id_Pregunta), " Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        
        response
                = stored_Procedures.sp_RemoverPregunta(id_Pregunta, id_Usuario);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Pregunta ",String.valueOf(id_Pregunta)," Removida Del Usuario ",String.valueOf(id_Usuario)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
        
    }
    
    public Vector<Object> Remover_Tarea(int id_Tarea, int id_Profesor , String cliente, String ip){
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = respuestas.Agregar_Solicitud(Concatenar("Remover Tarea ",String.valueOf(id_Tarea), " Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);
        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response
                = stored_Procedures.sp_RemoverTarea(id_Tarea, id_Profesor);
        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {
            
            Vector<Integer> usuarios = stored_Procedures.sp_ObtenerIDsUsuariosTarea(id_Tarea, id_Profesor);
            
            //Enviar aviso a los alumnos sobre eliminación de la tarea:
            while(!usuarios.isEmpty()){
                Enviar_Aviso((int)usuarios.remove(0));
            }

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Tarea ",String.valueOf(id_Tarea)," Removida Del Profesor ",String.valueOf(id_Profesor)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        return response;
     }
    
    public Vector<Object> Remover_Tematica_Curso(int id_Tematica, int id_Curso, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = 
                respuestas.Agregar_Solicitud(Concatenar("Remover Temática ",
                        String.valueOf(id_Tematica), " Del Curso ",String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response
                = stored_Procedures.sp_RemoverTematicaCurso(id_Tematica, id_Curso);

        if ((Integer)response.get(0) == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Temática ",
                            String.valueOf(id_Tematica)," Removida Del Curso ",String.valueOf(id_Curso)), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }


        return response;
    
    }
    
    public Vector<Object> Validacion_Contestar_Cuestionario(int id_Curso, int id_Usuario, String cliente, String ip){
        
        Vector<Object> response;
        
        cliente = Decodificacion(cliente);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta = 
                respuestas.Agregar_Solicitud(Concatenar("El Usuario ",
                        String.valueOf(id_Usuario), " Pide Confirmación Para Contestar El Cuestionario Del Curso ",
                        String.valueOf(id_Curso)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        response
                = stored_Procedures.sp_ValidacionContestarCuestionario(id_Curso, id_Usuario);

       
        //Agregar respuesta:
        respuesta
                = respuestas.Agregar_Respuesta(Decodificacion((String)response.get(1)), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return response;
    }
    
    public void Cerrar_Conexion(){
        stored_Procedures.Cerrar_Conexion();
    }
    
}
