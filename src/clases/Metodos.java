/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import datos.estructuras.Par;
import frames.Principal_Frame;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    private static Connection db_CourseRoom_Server_Conexion;
    
    
    private Session sesion;
    private StringBuilder mensaje_HTML;
    private MimeBodyPart parte_Cuerpo_MIME_HTML;
    private MimeMessage mensaje_MIME;
    private InternetAddress direccion_Internet;
    private Multipart multiparte;
    private DateTimeFormatter formato_Fecha;
    
    public Metodos() throws ClassNotFoundException, SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        byte[] decoded = Base64.getDecoder().decode("QmgrMzMxMDcxMjAyMA==");
        String decodificacion = new String(decoded);
        db_CourseRoom_Server_Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseroom_server", "root", decodificacion);
        
        formato_Fecha = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy hh:mm:ss a");
        
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
   
    // <editor-fold defaultstate="collapsed" desc="Metodos base de datos para frame principal">
    
     public ResultSet Sp_ObtenerSolicitudes() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerSolicitudes()}");
        return ejecutor.executeQuery();
    }
    
    public static ResultSet Sp_ObtenerRespuestas() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerRespuestas()}");
        return ejecutor.executeQuery();
    }
    
    public static ResultSet Sp_ObtenerMetodos() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerMetodos()}");
        return ejecutor.executeQuery();
    }
    
    public static ResultSet Sp_ObtenerTablasCourseRoom() throws SQLException{
        CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_ObtenerTablasCourseRoom()}");
        return ejecutor.executeQuery();
    }
    
    public Par<Boolean, String> sp_AgregarSolicitud(String solicitud, String cliente, String fecha_Solicitud){
        
        Boolean codigo = Boolean.FALSE;
        String mensaje = "";
        try {
            
            try (CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_AgregarSolicitud(?,?,?)}")) {
                ejecutor.setString("_Solicitud", solicitud);
                ejecutor.setString("_Cliente", cliente);
                ejecutor.setString("_FechaSolicitud", fecha_Solicitud);
                try (ResultSet resultado = ejecutor.executeQuery()) {
                    if(resultado != null){
                        while(resultado.next()){
                            codigo = resultado.getBoolean("Codigo");
                            mensaje = resultado.getString("Mensaje");
                            break;
                        }
                    }
                }
                
            }
            
              
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
        
        return new Par<>(codigo,mensaje);
    }
    
    public Par<Boolean, String> sp_AgregarRespuesta(String respuesta, String cliente, String fecha_Respuesta){
        
        Boolean codigo = Boolean.FALSE;
        String mensaje = "";
        
        try {
            
            try (CallableStatement ejecutor = db_CourseRoom_Server_Conexion.prepareCall("{CALL sp_AgregarRespuesta(?,?,?)}")) {
                ejecutor.setString("_Respuesta", respuesta);
                ejecutor.setString("_Cliente", cliente);
                ejecutor.setString("_FechaRespuesta", fecha_Respuesta);
                try (ResultSet resultado = ejecutor.executeQuery()) {
                    if(resultado != null){
                        while(resultado.next()){
                            codigo = resultado.getBoolean("Codigo");
                            mensaje = resultado.getString("Mensaje");
                            break;
                        }
                    }
                }
            }
            
              
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
        
        return new Par<>(codigo, mensaje);
    }
    
    public void Cerrar_Conexion() throws SQLException{
        db_CourseRoom_Server_Conexion.close();
    }
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para rpc">
    
    
    public Vector<Integer> Fecha_Hora_Servidor(String cliente) throws SQLException{
        
        //Agregar solicitud:
        Par<Boolean, String> respuesta = 
                sp_AgregarSolicitud("Obtener Fecha & Hora Del Servidor", cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
        LocalDateTime fecha_Hora_Actual = LocalDateTime.now();
        
        Vector<Integer> vector = new Vector<>();
        
        vector.add(fecha_Hora_Actual.getYear());
        vector.add(fecha_Hora_Actual.getMonthValue());
        vector.add(fecha_Hora_Actual.getDayOfMonth());
        vector.add(fecha_Hora_Actual.getHour());
        vector.add(fecha_Hora_Actual.getMinute());
        vector.add(fecha_Hora_Actual.getSecond());
        
        Principal_Frame.Agregar_Conexion(cliente);
        
        //Agregar respuesta:
        respuesta = 
                sp_AgregarRespuesta("Fecha & Hora: "+vector.toString(), cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
        Principal_Frame.Obtener_Solicitudes();
        Principal_Frame.Obtener_Respuestas();
        
        return vector;
    }
    
    public byte[] Imagen_Inicio_Sesion(String cliente) throws MalformedURLException, IOException, SQLException{
        
        //Agregar solicitud:
        Par<Boolean, String> respuesta = 
                sp_AgregarSolicitud("Obtener Imagen Inicio Sesión", cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
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
        
        //Agregar respuesta:
        respuesta = 
                sp_AgregarRespuesta("Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700", cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
        Principal_Frame.Obtener_Solicitudes();
        Principal_Frame.Obtener_Respuestas();
        
        return outputStream.toByteArray();  
       
    }
    
    
    public Boolean Recuperar_Credenciales(String correo_Electronico, String cliente) throws SQLException {

        //Agregar solicitud:
        Par<Boolean, String> respuesta = 
                sp_AgregarSolicitud("Recuperar Credenciales Por Correo Electrónico", cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
        
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
       
       //Agregar respuesta:
        respuesta = 
                sp_AgregarRespuesta("Credenciales Enviadas Al Correo: "+correo_Electronico, cliente, LocalDateTime.now().format(formato_Fecha));
        
        if(!respuesta.first()){
            System.err.println(respuesta.second());
        }
        
        Principal_Frame.Obtener_Solicitudes();
        Principal_Frame.Obtener_Respuestas();
       
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
