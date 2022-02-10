/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
    
    public Vector<Vector<String>> ObtenerRespuestas(){
        
        Vector<Vector<String>> vector = new Vector<>();
     
        ResultSet resultados;
        try {
            resultados = Sp_ObtenerRespuestas();
            
            if (resultados != null) {

                Vector<String> idTickets = new Vector<>();
                Vector<String> respuestas = new Vector<>();
                Vector<String> clientes = new Vector<>();
                Vector<String> fechasRespuesta = new Vector<>();
                
                while (resultados.next()) {

                    idTickets.add(String.valueOf(resultados.getInt("IdTicket")));
                    respuestas.add(resultados.getString("Respuesta"));
                    clientes.add(resultados.getString("Cliente"));
                    fechasRespuesta.add(resultados.getString("FechaRespuesta"));

                }

                resultados.close();
                
                vector.add(idTickets);
                vector.add(respuestas);
                vector.add(clientes);
                vector.add(fechasRespuesta);
                
                return vector;
               
            }
            
        } catch (SQLException ex) {
            
        }
        
        return vector;
    } 
//    
//    public Object[] ObtenerRespuestas(){
//     
//        ResultSet resultados;
//        try {
//            resultados = Sp_ObtenerRespuestas();
//            
//            if (resultados != null) {
//
//                resultados.last();
//                int numero_Registros = resultados.getRow();
//                
//                int i = 0;
//                Object[] respuesta = new Object[4];
//                String[] idTickets = new String[numero_Registros];
//                String[] respuestas = new String[numero_Registros];
//                String[] clientes = new String[numero_Registros];
//                String[] fechasRespuesta = new String[numero_Registros];
//                
//                while (resultados.previous()) {
//
//                    idTickets[i] = String.valueOf(resultados.getInt("IdTicket"));
//                    respuestas[i] = resultados.getString("Respuesta");
//                    clientes[i] = resultados.getString("Cliente");
//                    fechasRespuesta[i] = resultados.getString("FechaRespuesta");
//
//                    i++;
//                }
//
//                resultados.close();
//                
//                respuesta[0] = idTickets;
//                respuesta[1] = respuestas;
//                respuesta[2] = clientes;
//                respuesta[3] = fechasRespuesta;
//                
//                return respuesta;
//               
//            }
//            
//        } catch (SQLException ex) {
//            
//        }
//        
//        return new Object[]{new Object[]{""},new Object[]{""},new Object[]{""},new Object[]{""}};
//    } 
//    
    // </editor-fold >
    
    
    
    
}
