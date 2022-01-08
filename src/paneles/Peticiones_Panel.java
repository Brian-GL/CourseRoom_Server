/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package paneles;

import clases.Celda_Renderer;
import courseroom_server.CourseRoom_Server;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author LENOVO
 */
public class Peticiones_Panel extends javax.swing.JPanel {

    
    public Peticiones_Panel() {
        initComponents();
        
        logo_JLabel.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/Course_Room_Brand_Blue.png")));
   
        peticiones_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        peticiones_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
     
        DefaultTableModel modelo = (DefaultTableModel) peticiones_JTable.getModel();

        String[] insercion = new String[4];
        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 15);i++){
            insercion[0] = CourseRoom_Server.Faker().job().field();
            insercion[1] = CourseRoom_Server.Faker().internet().macAddress();
            insercion[2] = CourseRoom_Server.Faker().date().birthday(21, 22).toString();
            insercion[3] = CourseRoom_Server.Faker().lorem().paragraph();
            
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
        peticiones_JScrollPane = new javax.swing.JScrollPane();
        peticiones_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        peticiones_JTable.setDefaultRenderer(String.class, new Celda_Renderer());

        setBackground(new java.awt.Color(14, 30, 64));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

        peticiones_JScrollPane.setBorder(null);

        peticiones_JTable.setAutoCreateRowSorter(true);
        peticiones_JTable.setBackground(new java.awt.Color(14, 30, 64));
        peticiones_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        peticiones_JTable.setForeground(new java.awt.Color(104, 194, 232));
        peticiones_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Petición", "Cliente", "Fecha", "Descripción"
            }
        ));
        peticiones_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        peticiones_JTable.setRowHeight(100);
        peticiones_JTable.setRowMargin(10);
        peticiones_JTable.setShowGrid(true);
        peticiones_JScrollPane.setViewportView(peticiones_JTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(peticiones_JScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(peticiones_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel logo_JLabel;
    private javax.swing.JScrollPane peticiones_JScrollPane;
    private javax.swing.JTable peticiones_JTable;
    // End of variables declaration//GEN-END:variables
}
