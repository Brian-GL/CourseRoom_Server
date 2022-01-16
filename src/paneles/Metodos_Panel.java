/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package paneles;

import clases.Celda_Renderer;
import courseroom_server.CourseRoom_Server;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author LENOVO
 */
public class Metodos_Panel extends javax.swing.JPanel {

    
    public Metodos_Panel() {
        initComponents();
        
        logo_JLabel.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/Course_Room_Brand_Blue.png")));
   
        metodos_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        metodos_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        Font gadugi = new Font("Gadugi", Font.BOLD, 16);
        metodos_JTable.getTableHeader().setFont(gadugi);
        metodos_JTable.getTableHeader().setBackground(logo_JLabel.getForeground());
        metodos_JTable.getTableHeader().setForeground(this.getBackground());
     
        DefaultTableModel modelo = (DefaultTableModel) metodos_JTable.getModel();

        String[] insercion = new String[3];
        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 35);i++){
            insercion[0] = CourseRoom_Server.Faker().artist().name();
            insercion[1] = String.valueOf(CourseRoom_Server.Faker().bool().bool());
            insercion[2] = String.valueOf(CourseRoom_Server.Faker().number().numberBetween(0, 100));
            
            modelo.addRow(insercion);
        }
           
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo_JLabel = new javax.swing.JLabel();
        metodos_JScrollPane = new javax.swing.JScrollPane();
        metodos_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        metodos_JTable.setDefaultRenderer(String.class, new Celda_Renderer());

        setBackground(new java.awt.Color(14, 30, 64));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        logo_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        logo_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_JLabel.setText("Métodos");
        logo_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

        metodos_JScrollPane.setBorder(null);

        metodos_JTable.setAutoCreateRowSorter(true);
        metodos_JTable.setBackground(new java.awt.Color(14, 30, 64));
        metodos_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        metodos_JTable.setForeground(new java.awt.Color(104, 194, 232));
        metodos_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Método", "Activo", "Utilizaciones"
            }
        ));
        metodos_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        metodos_JTable.setRowHeight(100);
        metodos_JTable.setRowMargin(10);
        metodos_JTable.setShowGrid(true);
        metodos_JTable.setRowSorter(new TableRowSorter(metodos_JTable.getModel()));
        metodos_JScrollPane.setViewportView(metodos_JTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(metodos_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addComponent(logo_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(metodos_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel logo_JLabel;
    private javax.swing.JScrollPane metodos_JScrollPane;
    private javax.swing.JTable metodos_JTable;
    // End of variables declaration//GEN-END:variables
}
