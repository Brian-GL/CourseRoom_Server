/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseroom_server;

import clases.ConexionMySQL;
import com.formdev.flatlaf.FlatDarkLaf;
import com.github.javafaker.Faker;
import frames.Principal_Frame;
import java.sql.ResultSet;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author LENOVO
 */
public class CourseRoom_Server {
    
    private static Faker _faker;
    private static ConexionMySQL conexionMySQL;

   
    

    public CourseRoom_Server(){
        conexionMySQL = new ConexionMySQL();
        _faker = new Faker(new Locale("es", "MX"));
        Principal_Frame principal_Frame = new Principal_Frame();
        principal_Frame.setVisible(true);
        
    }
    
    public static ResultSet sp_ObtenerSolicitudes() {
        return ConexionMySQL.sp_ObtenerSolicitudes();
    }

    public static ResultSet sp_ObtenerRespuestas() {
        return ConexionMySQL.sp_ObtenerRespuestas();
    }

    public static int sp_AgregarSolicitud(String solicitud, String cliente, String fecha_Solicitud) {
        return ConexionMySQL.sp_AgregarSolicitud(solicitud, cliente, fecha_Solicitud);
    }

    public static void Cerrar_Conexion() {
        ConexionMySQL.Cerrar_Conexion();
    }

    public static void main(String args[]) {
        try {
            FlatDarkLaf ui = new FlatDarkLaf();
            UIManager.setLookAndFeel(ui);
            CourseRoom_Server courseRoom_Server = new CourseRoom_Server();
        } catch (UnsupportedLookAndFeelException ex) {

        }
    }
    
    public static String Concatenar(String cadena, String... args) {
        StringBuilder constructor_Cadena = new StringBuilder(cadena);
        String argumento;
        for (int i = 0; i < args.length; i++) {
            argumento = args[i];
            constructor_Cadena.append(argumento);
        }
        return constructor_Cadena.toString();
    }
    
    public static String Formato_HTML_Central(String text) {
        return Concatenar("<html><div style='text-align:center; align-items: center; justify-content: center;'><p>",
                text,"</p></div></html>");
    }
    
    public static Faker Faker() {
        return _faker;
    }
    
}
