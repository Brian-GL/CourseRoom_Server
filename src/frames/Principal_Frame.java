/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Celda_Renderer;
import courseroom_server.CourseRoom_Server;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.Array;
import java.sql.SQLException;


/**
 *
 * @author LENOVO
 */
public class Principal_Frame extends javax.swing.JFrame {

    @SuppressWarnings({"OverridableMethodCallInConstructor", "null"})
    public Principal_Frame() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        Image logo = null;
        try {
            this.setIconImage(ImageIO.read(getClass().getResource("/recursos/iconos/Course_Room_Brand.png")));
            logo = ImageIO.read(getClass().getResource("/recursos/imagenes/Course_Room_Brand_Blue.png"));
        } catch (IOException ex) {
            
        }
        
        logo = logo.getScaledInstance(125, 100, Image.SCALE_AREA_AVERAGING);
        ImageIcon icono = new ImageIcon(logo);
        
        titulo_JLabel.setIcon(icono);
        
        logo.flush();
        ((ImageIcon)titulo_JLabel.getIcon()).getImage().flush();
        
        icono.getImage().flush();
   
        respuestas_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        respuestas_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        Font gadugi = new Font("Gadugi", Font.BOLD, 16);
        respuestas_JTable.getTableHeader().setFont(gadugi);
        respuestas_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        respuestas_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
     
        DefaultTableModel modelo = (DefaultTableModel) respuestas_JTable.getModel();

        ResultSet resultados = CourseRoom_Server.sp_ObtenerRespuestas();
        if(resultados != null){
            String[] celdas = new String[4];
            try {
                while(resultados.next()){
                    
                    celdas[0] = String.valueOf(resultados.getInt("IdTicket"));
                    celdas[1] = resultados.getString("Respuesta");
                    celdas[2] = resultados.getString("Cliente");
                    celdas[3] =  resultados.getString("FechaRespuesta");
                    
                    modelo.addRow(celdas);
                }
            } catch (SQLException ex) {
                
            }
             
        }
        
        try {
            resultados.close();
        } catch (SQLException ex) {
            
        }
        
        //Solicitudes:
        solicitudes_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        solicitudes_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        solicitudes_JTable.getTableHeader().setFont(gadugi);
        solicitudes_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        solicitudes_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
     
        modelo = (DefaultTableModel) solicitudes_JTable.getModel();
        
        resultados = CourseRoom_Server.sp_ObtenerSolicitudes();
        if(resultados != null){
            String[] celdas = new String[4];
            try {
               
                while(resultados.next()){
                    
                   
                    celdas[0] = String.valueOf(resultados.getInt("IdTicket"));
                    celdas[1] = resultados.getString("Solicitud");
                    celdas[2] = resultados.getString("Cliente");
                    celdas[3] =  resultados.getString("FechaSolicitud");
              
                    
                    modelo.addRow(celdas);
                }
            } catch (SQLException ex) {
                
            }
             
        }

        try {
            resultados.close();
        } catch (SQLException ex) {

        }
       
        //Metodos:
        metodos_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        metodos_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        metodos_JTable.getTableHeader().setFont(gadugi);
        metodos_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        metodos_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
     
        modelo = (DefaultTableModel) metodos_JTable.getModel();

        String[] insercion = new String[2];
        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 35);i++){
            insercion[0] = CourseRoom_Server.Faker().artist().name();
            insercion[1] = String.valueOf(CourseRoom_Server.Faker().bool().bool());
            
            modelo.addRow(insercion);
        }
        
        //Info General:
        courseroom_DB_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        courseroom_DB_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        courseroom_Server_DB_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        courseroom_Server_DB_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        courseroom_DB_JTable.getTableHeader().setFont(gadugi);
        courseroom_DB_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        courseroom_DB_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
        courseroom_Server_DB_JTable.getTableHeader().setFont(gadugi);
        courseroom_Server_DB_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        courseroom_Server_DB_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());

        modelo = (DefaultTableModel) courseroom_DB_JTable.getModel();

        
        insercion = new String[3];
        for (int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 15); i++) {
            insercion[0] = CourseRoom_Server.Faker().witcher().character();
            insercion[1] = String.valueOf(CourseRoom_Server.Faker().number().numberBetween(1, 100));
            insercion[2] = CourseRoom_Server.Faker().date().birthday(21, 22).toString();

            modelo.addRow(insercion);
        }
        
        modelo = (DefaultTableModel) courseroom_Server_DB_JTable.getModel();

        for (int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 15); i++) {
            insercion[0] = CourseRoom_Server.Faker().ancient().god();
            insercion[1] = String.valueOf(CourseRoom_Server.Faker().number().numberBetween(1, 100));
            insercion[2] = CourseRoom_Server.Faker().date().birthday(21, 22).toString();

            modelo.addRow(insercion);
        }

        titulo_JLabel.setText("Solicitudes");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo_JLabel = new javax.swing.JLabel();
        principal_JLayeredPane = new javax.swing.JLayeredPane();
        solicitudes_JScrollPane = new javax.swing.JScrollPane();
        solicitudes_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        solicitudes_JTable.setDefaultRenderer(String.class, new Celda_Renderer());
        respuestas_JScrollPane = new javax.swing.JScrollPane();
        respuestas_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        respuestas_JTable.setDefaultRenderer(String.class, new Celda_Renderer());
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
        info_General_JPanel = new javax.swing.JPanel();
        courseroom_DB_JPanel = new javax.swing.JPanel();
        courseroom_DB_JScrollPane = new javax.swing.JScrollPane();
        courseroom_DB_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        courseroom_DB_JTable.setDefaultRenderer(String.class, new Celda_Renderer());
        courseroom_Server_DB_JPanel = new javax.swing.JPanel();
        courseroom_Server_DB_JScrollPane = new javax.swing.JScrollPane();
        courseroom_Server_DB_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        courseroom_DB_JTable.setDefaultRenderer(String.class, new Celda_Renderer());
        barra_Menu_JMenuBar = new javax.swing.JMenuBar();
        solicitudes_JMenu = new javax.swing.JMenu();
        respuestas_JMenu = new javax.swing.JMenu();
        metodos_JMenu = new javax.swing.JMenu();
        info_General_JMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Servidor De CourseRoom");
        setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        titulo_JLabel.setBackground(new java.awt.Color(14, 30, 64));
        titulo_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        titulo_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        titulo_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_JLabel.setText("Peticiones");
        titulo_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        titulo_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        titulo_JLabel.setOpaque(true);
        titulo_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

        principal_JLayeredPane.setLayout(new java.awt.CardLayout());

        solicitudes_JScrollPane.setBorder(null);

        solicitudes_JTable.setAutoCreateRowSorter(true);
        solicitudes_JTable.setBackground(new java.awt.Color(14, 30, 64));
        solicitudes_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        solicitudes_JTable.setForeground(new java.awt.Color(104, 194, 232));
        solicitudes_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ticket", "Solicitud", "Cliente", "Fecha Solicitud"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        solicitudes_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        solicitudes_JTable.setRowHeight(100);
        solicitudes_JTable.setRowMargin(10);
        solicitudes_JTable.setShowGrid(true);
        solicitudes_JTable.setRowSorter(new TableRowSorter(solicitudes_JTable.getModel()));
        solicitudes_JScrollPane.setViewportView(solicitudes_JTable);

        principal_JLayeredPane.add(solicitudes_JScrollPane, "Solicitudes");

        respuestas_JScrollPane.setBorder(null);

        respuestas_JTable.setAutoCreateRowSorter(true);
        respuestas_JTable.setBackground(new java.awt.Color(14, 30, 64));
        respuestas_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        respuestas_JTable.setForeground(new java.awt.Color(104, 194, 232));
        respuestas_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ticket", "Respuesta", "Cliente", "Fecha Respuesta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        respuestas_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        respuestas_JTable.setRowHeight(100);
        respuestas_JTable.setRowMargin(10);
        respuestas_JTable.setShowGrid(true);
        respuestas_JTable.setRowSorter(new TableRowSorter(respuestas_JTable.getModel()));
        respuestas_JScrollPane.setViewportView(respuestas_JTable);

        principal_JLayeredPane.add(respuestas_JScrollPane, "Respuestas");

        metodos_JScrollPane.setBorder(null);

        metodos_JTable.setAutoCreateRowSorter(true);
        metodos_JTable.setBackground(new java.awt.Color(14, 30, 64));
        metodos_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        metodos_JTable.setForeground(new java.awt.Color(104, 194, 232));
        metodos_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Método", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        metodos_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        metodos_JTable.setRowHeight(100);
        metodos_JTable.setRowMargin(10);
        metodos_JTable.setShowGrid(true);
        metodos_JTable.setRowSorter(new TableRowSorter(metodos_JTable.getModel()));
        metodos_JScrollPane.setViewportView(metodos_JTable);

        principal_JLayeredPane.add(metodos_JScrollPane, "Metodos");

        info_General_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        info_General_JPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        info_General_JPanel.setLayout(new java.awt.GridLayout(1, 2));

        courseroom_DB_JPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CourseRoom DB", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gadugi", 1, 18), new java.awt.Color(104, 194, 232))); // NOI18N
        courseroom_DB_JPanel.setForeground(new java.awt.Color(104, 194, 232));
        courseroom_DB_JPanel.setOpaque(false);
        courseroom_DB_JPanel.setPreferredSize(new java.awt.Dimension(800, 609));
        courseroom_DB_JPanel.setLayout(new java.awt.CardLayout());

        courseroom_DB_JScrollPane.setBorder(null);

        courseroom_DB_JTable.setAutoCreateRowSorter(true);
        courseroom_DB_JTable.setBackground(new java.awt.Color(14, 30, 64));
        courseroom_DB_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        courseroom_DB_JTable.setForeground(new java.awt.Color(104, 194, 232));
        courseroom_DB_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tabla", "# Registros", "Actualizado"
            }
        ));
        courseroom_DB_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        courseroom_DB_JTable.setRowHeight(100);
        courseroom_DB_JTable.setRowMargin(10);
        courseroom_DB_JTable.setShowGrid(true);
        courseroom_DB_JTable.setRowSorter(new TableRowSorter(courseroom_DB_JTable.getModel()));
        courseroom_DB_JScrollPane.setViewportView(courseroom_DB_JTable);

        courseroom_DB_JPanel.add(courseroom_DB_JScrollPane, "card2");

        info_General_JPanel.add(courseroom_DB_JPanel);

        courseroom_Server_DB_JPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CourseRoom Server DB", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gadugi", 1, 18), new java.awt.Color(104, 194, 232))); // NOI18N
        courseroom_Server_DB_JPanel.setForeground(new java.awt.Color(104, 194, 232));
        courseroom_Server_DB_JPanel.setOpaque(false);
        courseroom_Server_DB_JPanel.setPreferredSize(new java.awt.Dimension(800, 609));
        courseroom_Server_DB_JPanel.setLayout(new java.awt.CardLayout());

        courseroom_Server_DB_JScrollPane.setBorder(null);

        courseroom_Server_DB_JTable.setAutoCreateRowSorter(true);
        courseroom_Server_DB_JTable.setBackground(new java.awt.Color(14, 30, 64));
        courseroom_Server_DB_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        courseroom_Server_DB_JTable.setForeground(new java.awt.Color(104, 194, 232));
        courseroom_Server_DB_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tabla", "# Registros", "Actualizado"
            }
        ));
        courseroom_Server_DB_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        courseroom_Server_DB_JTable.setRowHeight(100);
        courseroom_Server_DB_JTable.setRowMargin(10);
        courseroom_Server_DB_JTable.setShowGrid(true);
        courseroom_DB_JTable.setRowSorter(new TableRowSorter(courseroom_DB_JTable.getModel()));
        courseroom_Server_DB_JScrollPane.setViewportView(courseroom_Server_DB_JTable);

        courseroom_Server_DB_JPanel.add(courseroom_Server_DB_JScrollPane, "card2");

        info_General_JPanel.add(courseroom_Server_DB_JPanel);

        principal_JLayeredPane.add(info_General_JPanel, "Info_General");

        solicitudes_JMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/request.png"))); // NOI18N
        solicitudes_JMenu.setText("Solicitudes");
        solicitudes_JMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        solicitudes_JMenu.setFocusCycleRoot(true);
        solicitudes_JMenu.setFocusPainted(true);
        solicitudes_JMenu.setFocusTraversalPolicyProvider(true);
        solicitudes_JMenu.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        solicitudes_JMenu.setOpaque(true);
        solicitudes_JMenu.setRolloverEnabled(false);
        solicitudes_JMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solicitudes_JMenuMouseClicked(evt);
            }
        });
        barra_Menu_JMenuBar.add(solicitudes_JMenu);

        respuestas_JMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/responsibility.png"))); // NOI18N
        respuestas_JMenu.setText("Respuestas");
        respuestas_JMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        respuestas_JMenu.setFocusCycleRoot(true);
        respuestas_JMenu.setFocusPainted(true);
        respuestas_JMenu.setFocusTraversalPolicyProvider(true);
        respuestas_JMenu.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        respuestas_JMenu.setOpaque(true);
        respuestas_JMenu.setRolloverEnabled(false);
        respuestas_JMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                respuestas_JMenuMouseClicked(evt);
            }
        });
        barra_Menu_JMenuBar.add(respuestas_JMenu);

        metodos_JMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/programming.png"))); // NOI18N
        metodos_JMenu.setText("Métodos");
        metodos_JMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        metodos_JMenu.setFocusCycleRoot(true);
        metodos_JMenu.setFocusPainted(true);
        metodos_JMenu.setFocusTraversalPolicyProvider(true);
        metodos_JMenu.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        metodos_JMenu.setOpaque(true);
        metodos_JMenu.setRolloverEnabled(false);
        metodos_JMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                metodos_JMenuMouseClicked(evt);
            }
        });
        barra_Menu_JMenuBar.add(metodos_JMenu);

        info_General_JMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/information.png"))); // NOI18N
        info_General_JMenu.setText("Info General");
        info_General_JMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info_General_JMenu.setFocusCycleRoot(true);
        info_General_JMenu.setFocusPainted(true);
        info_General_JMenu.setFocusTraversalPolicyProvider(true);
        info_General_JMenu.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        info_General_JMenu.setOpaque(true);
        info_General_JMenu.setRolloverEnabled(false);
        info_General_JMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                info_General_JMenuMouseClicked(evt);
            }
        });
        barra_Menu_JMenuBar.add(info_General_JMenu);

        setJMenuBar(barra_Menu_JMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal_JLayeredPane)
            .addComponent(titulo_JLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(titulo_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(principal_JLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solicitudes_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solicitudes_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            titulo_JLabel.setText("Solicitudes");
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Solicitudes");
        }
    }//GEN-LAST:event_solicitudes_JMenuMouseClicked

    private void respuestas_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_respuestas_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            titulo_JLabel.setText("Respuestas");
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Respuestas");
        }
    }//GEN-LAST:event_respuestas_JMenuMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        CourseRoom_Server.Cerrar_Conexion();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void metodos_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metodos_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            titulo_JLabel.setText("Métodos");
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Metodos");
        }
    }//GEN-LAST:event_metodos_JMenuMouseClicked

    private void info_General_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_info_General_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            titulo_JLabel.setText("Info General");
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Info_General");
        }
    }//GEN-LAST:event_info_General_JMenuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barra_Menu_JMenuBar;
    private javax.swing.JPanel courseroom_DB_JPanel;
    private javax.swing.JScrollPane courseroom_DB_JScrollPane;
    private javax.swing.JTable courseroom_DB_JTable;
    private javax.swing.JPanel courseroom_Server_DB_JPanel;
    private javax.swing.JScrollPane courseroom_Server_DB_JScrollPane;
    private javax.swing.JTable courseroom_Server_DB_JTable;
    private javax.swing.JMenu info_General_JMenu;
    private javax.swing.JPanel info_General_JPanel;
    private javax.swing.JMenu metodos_JMenu;
    private javax.swing.JScrollPane metodos_JScrollPane;
    private javax.swing.JTable metodos_JTable;
    private javax.swing.JLayeredPane principal_JLayeredPane;
    private javax.swing.JMenu respuestas_JMenu;
    private javax.swing.JScrollPane respuestas_JScrollPane;
    private javax.swing.JTable respuestas_JTable;
    private javax.swing.JMenu solicitudes_JMenu;
    private javax.swing.JScrollPane solicitudes_JScrollPane;
    private javax.swing.JTable solicitudes_JTable;
    private javax.swing.JLabel titulo_JLabel;
    // End of variables declaration//GEN-END:variables

}
