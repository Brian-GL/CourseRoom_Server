/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import paneles.Celda_Panel;

/**
 *
 * @author LENOVO
 */
public class Celda_Renderer extends DefaultTableCellRenderer{
   
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
       
        Celda_Panel celda = new Celda_Panel(value.toString(),
                table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue().toString());
        
       
        return celda;
    }
}
