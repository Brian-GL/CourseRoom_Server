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
        try{
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

        }
        catch (IOException | MessagingException ex) {

        }
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

        byte[] response = null;

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud("Obtener Imagen Inicio Sesión", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        URL url_Imagen = new URL("https://picsum.photos/500/700");

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

        }

        //Agregar respuesta:
        respuesta
                = respuestas.Agregar_Respuesta("Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return response;

    }

    public Boolean Recuperar_Credenciales(String correo_Electronico, String cliente, String ip) throws SQLException {

        cliente = Decodificacion(cliente);
        correo_Electronico = Decodificacion(correo_Electronico);
        ip = Decodificacion(ip);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud("Recuperar Credenciales Por Correo Electrónico", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        //Buscar la contraseña en la base de datos:
        String contrasena = "contraseña De Prueba";

        try {

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

        } catch (MessagingException ex) {
            System.out.println("clases.Metodos.Recuperar_Credenciales(): " + ex.getLocalizedMessage());
            System.out.println("clases.Metodos.Recuperar_Credenciales(): " + ex.getMessage());
            return Boolean.FALSE;
        }

        //Agregar respuesta:
        respuesta
                = respuestas.Agregar_Respuesta("Credenciales Enviadas Al Correo: " + correo_Electronico, cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return Boolean.TRUE;
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

        //Agregar respuesta:
        respuesta
                = respuestas.Agregar_Respuesta("Estados Enviados", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return response;
    }

    public Vector<Vector<Object>> Obtener_Localidades_Por_Estado(String estado, String cliente, String ip) throws SQLException {

        estado = Decodificacion(estado);
        ip = Decodificacion(ip);
        cliente = Decodificacion(cliente);

        //Agregar solicitud:
        Par<Integer, String> respuesta
                = respuestas.Agregar_Solicitud(Concatenar("Obtener Localidades Del Estado ", estado), cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        Vector<Vector<Object>> response = stored_Procedures.sp_ObtenerLocalidadesPorEstado(estado);

        //Agregar respuesta:
        respuesta
                = respuestas.Agregar_Respuesta("Localidades Enviadas", cliente, ip);

        if (respuesta.first() == -1) {
            System.err.println(respuesta.second());
        }

        return response;
    }

    public Vector<Object> Agregar_Nuevo_Usuario(String correo_Electronico, String contrasenia ,String nombre,
        String paterno, String materno, int id_Localidad, String genero, String fecha_Nacimiento, String tipo_Usuario,
        byte[] imagen, double promedio_General,String descripcion, String cliente, String ip) throws SQLException, IOException {

        Vector<Object> response = new Vector<>();
        
        correo_Electronico = Decodificacion(correo_Electronico);
        contrasenia = Decodificacion(contrasenia);
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
        respuesta
                = stored_Procedures.sp_AgregarUsuario(correo_Electronico, contrasenia, nombre, paterno, materno, id_Localidad, genero, fecha_Nacimiento, tipo_Usuario, imagen, promedio_General, descripcion);

        if (respuesta.first() == -1) {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(respuesta.second(), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }

        } else {

            //Agregar respuesta:
            respuesta
                    = respuestas.Agregar_Respuesta(Concatenar("Usuario Agregado Con ID ", respuesta.first().toString()), cliente, ip);

            if (respuesta.first() == -1) {
                System.err.println(respuesta.second());
            }
        }

        response.add(respuesta.first());
        response.add(respuesta.second());

        return response;

    }
    
    public void Cerrar_Conexion(){
        stored_Procedures.Cerrar_Conexion();
    }
    
}
