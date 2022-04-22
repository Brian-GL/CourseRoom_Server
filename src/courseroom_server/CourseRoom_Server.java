/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseroom_server;

//import clases.Algoritmos;
import com.formdev.flatlaf.FlatDarkLaf;
import frames.CourseRoom_Server_Frame;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 */
public class CourseRoom_Server {
    
    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, SQLException, IOException {
        FlatDarkLaf ui = new FlatDarkLaf();
        UIManager.setLookAndFeel(ui);
        //var al = new Algoritmos();
        CourseRoom_Server_Frame principal_Frame = new CourseRoom_Server_Frame();
        principal_Frame.setVisible(true);
        
    }
    
}
