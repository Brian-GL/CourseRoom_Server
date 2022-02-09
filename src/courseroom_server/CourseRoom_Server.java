/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseroom_server;

import com.formdev.flatlaf.FlatDarkLaf;
import frames.Principal_Frame;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author LENOVO
 */
public class CourseRoom_Server {
    
    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, SQLException, IOException {
        FlatDarkLaf ui = new FlatDarkLaf();
        UIManager.setLookAndFeel(ui);
        Principal_Frame principal_Frame = new Principal_Frame();
        principal_Frame.setVisible(true);
        
    }
    
}
