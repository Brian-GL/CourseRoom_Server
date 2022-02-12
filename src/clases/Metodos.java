/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import frames.Principal_Frame;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
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
    
    private final DB_CourseRoom_Server db_CourseRoom_Server;
    
    
    private Session sesion;
    private StringBuilder mensaje_HTML;
    private MimeBodyPart parte_Cuerpo_MIME_HTML;
    private MimeMessage mensaje_MIME;
    private InternetAddress direccion_Internet;
    private Multipart multiparte;
    
    
    public Metodos() throws ClassNotFoundException, SQLException{
        db_CourseRoom_Server = new DB_CourseRoom_Server();
        
        try{
        
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
                    String linea = "";
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
   
    // <editor-fold defaultstate="collapsed" desc="Metodos base de datos">
    
    public ResultSet Sp_ObtenerSolicitudes() throws SQLException{
        return db_CourseRoom_Server.Sp_ObtenerSolicitudes();
    }

    public ResultSet Sp_ObtenerRespuestas() throws SQLException {
        return db_CourseRoom_Server.Sp_ObtenerRespuestas();
    }

    public ResultSet Sp_ObtenerMetodos() throws SQLException {
        return db_CourseRoom_Server.Sp_ObtenerMetodos();
    }

    public ResultSet Sp_ObtenerTablasCourseRoom() throws SQLException {
        return db_CourseRoom_Server.Sp_ObtenerTablasCourseRoom();
    }

    public void Cerrar_Conexion() throws SQLException {
        db_CourseRoom_Server.Cerrar_Conexion();
    }
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para rpc">
    
    
    public Vector<Integer> Fecha_Hora_Servidor(String cliente){
        
        LocalDateTime fecha_Hora_Actual = LocalDateTime.now();
        
        Vector<Integer> vector = new Vector<>();
        
        vector.add(fecha_Hora_Actual.getYear());
        vector.add(fecha_Hora_Actual.getMonthValue());
        vector.add(fecha_Hora_Actual.getDayOfMonth());
        vector.add(fecha_Hora_Actual.getHour());
        vector.add(fecha_Hora_Actual.getMinute());
        vector.add(fecha_Hora_Actual.getSecond());
        
        Principal_Frame.Agregar_Conexion(cliente);
        
        return vector;
    }
    
    public byte[] Imagen_Inicio_Sesion(String cliente) throws MalformedURLException, IOException{
        
        URL url_Imagen = new URL("https://picsum.photos/500/700");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            try(InputStream stream = url_Imagen.openStream()){

                while ((bytesRead = stream.read(chunk)) > 0) {
                    outputStream.write(chunk, 0, bytesRead);
                }
            
            }

        } catch (IOException e) {
            return null;
        }

        Principal_Frame.Agregar_Conexion(cliente);
        return outputStream.toByteArray();  
       
    }
    
    
    public Boolean Recuperar_Credenciales(String correo_Electronico, String cliente) {

        // brianlomeli097@outlook.com
       //Buscar la contraseña en la base de datos:
       String contrasena = "contraseña De Prueba";

       try {

           // Destinatario
           direccion_Internet.setAddress(correo_Electronico);

           // Agregar destinatario al mensaje
           mensaje_MIME.setRecipient(Message.RecipientType.TO,direccion_Internet); 

           // Crear la parte del mensaje HTML
           MimeBodyPart mimeBodyPartMensaje = new MimeBodyPart();
           mimeBodyPartMensaje.setFileName("Credenciales.txt");
           mimeBodyPartMensaje.setText("Contraseña: "+contrasena);

           // Agregar la parte del mensaje HTML al multiPart
           multiparte.addBodyPart(mimeBodyPartMensaje);

           // Agregar el multiparte al cuerpo del mensaje
           mensaje_MIME.setContent(multiparte);

           // Enviar el mensaje
           try ( Transport transporte = sesion.getTransport("smtp")) {
               
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
           System.out.println("clases.Metodos.Recuperar_Credenciales(): "+ex.getLocalizedMessage());
           System.out.println("clases.Metodos.Recuperar_Credenciales(): "+ex.getMessage());
           return Boolean.FALSE;
       }

       Principal_Frame.Agregar_Conexion(cliente);
       return Boolean.TRUE;
    }
    
//    public Vector<Vector<String>> ObtenerRespuestas(){
//        
//        Vector<Vector<String>> vector = new Vector<>();
//     
//        ResultSet resultados;
//        try {
//            resultados = Sp_ObtenerRespuestas();
//            
//            if (resultados != null) {
//
//                Vector<String> idTickets = new Vector<>();
//                Vector<String> respuestas = new Vector<>();
//                Vector<String> clientes = new Vector<>();
//                Vector<String> fechasRespuesta = new Vector<>();
//                
//                while (resultados.next()) {
//
//                    idTickets.add(String.valueOf(resultados.getInt("IdTicket")));
//                    respuestas.add(resultados.getString("Respuesta"));
//                    clientes.add(resultados.getString("Cliente"));
//                    fechasRespuesta.add(resultados.getString("FechaRespuesta"));
//
//                }
//
//                resultados.close();
//                
//                vector.add(idTickets);
//                vector.add(respuestas);
//                vector.add(clientes);
//                vector.add(fechasRespuesta);
//                
//                return vector;
//               
//            }
//            
//        } catch (SQLException ex) {
//            
//        }
//        
//        return vector;
//    } 

    // </editor-fold >
    
    
    
    
}
