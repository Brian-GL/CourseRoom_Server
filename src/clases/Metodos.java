/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author LENOVO
 */
public class Metodos {
    
    private final DB_CourseRoom_Server db_CourseRoom_Server;
    
    public Metodos() throws ClassNotFoundException, SQLException{
        db_CourseRoom_Server = new DB_CourseRoom_Server();
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="Metodos para frame">
    
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
    
    
    public Vector<Integer> Fecha_Hora_Servidor(){
        
        LocalDateTime fecha_Hora_Actual = LocalDateTime.now();
        
        Vector<Integer> vector = new Vector<>();
        
        vector.add(fecha_Hora_Actual.getYear());
        vector.add(fecha_Hora_Actual.getMonthValue());
        vector.add(fecha_Hora_Actual.getDayOfMonth());
        vector.add(fecha_Hora_Actual.getHour());
        vector.add(fecha_Hora_Actual.getMinute());
        vector.add(fecha_Hora_Actual.getSecond());
        
        return vector;
    }
    
    public byte[] Imagen_Inicio_Sesion() throws MalformedURLException, IOException{
        
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

        return outputStream.toByteArray();  
       
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
