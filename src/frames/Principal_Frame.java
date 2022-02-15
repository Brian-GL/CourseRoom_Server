/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Celda_Renderer;
import clases.Metodos;
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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.xmlrpc.WebServer;


/**
 *
 * @author LENOVO
 */
public class Principal_Frame extends javax.swing.JFrame {

    private byte carta_Visible;
    private static Metodos metodos;
    private final WebServer webServer;
    
    @SuppressWarnings({"OverridableMethodCallInConstructor", "null"})
    public Principal_Frame() throws ClassNotFoundException, SQLException, IOException {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        Principal_Frame.metodos = new Metodos();
        
        webServer = new WebServer(3030);
        webServer.addHandler("CourseRoom_Server", metodos);
        webServer.start();
        System.out.println("WebServer Starting At Port 3030");
       
        carta_Visible = 0;
        
        titulo_JLabel.setForeground(contenido_JPanel.getBackground());
        titulo_JLabel.setBackground(contenido_JPanel.getForeground());
        
        Image logo = ImageIO.read(getClass().getResource("/recursos/iconos/Course_Room_Brand.png"));
        this.setIconImage(logo);
        logo = ImageIO.read(getClass().getResource("/recursos/imagenes/Course_Room_Brand_Blue.png"));
        
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
     
        Obtener_Respuestas();
        
        //Solicitudes:
        solicitudes_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        solicitudes_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        solicitudes_JTable.getTableHeader().setFont(gadugi);
        solicitudes_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        solicitudes_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
     
        Obtener_Solicitudes();
        
        //Metodos:
        metodos_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        metodos_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        metodos_JTable.getTableHeader().setFont(gadugi);
        metodos_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        metodos_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
     
        Obtener_Metodos();
        
        //Tablas CourseRoom:
        tablas_Courseroom_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        tablas_Courseroom_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        tablas_CourseRoom_JTable.getTableHeader().setFont(gadugi);
        tablas_CourseRoom_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        tablas_CourseRoom_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
        
        Obtener_Tablas_CourseRoom();
        
        //Tabla Conexiones:
        conexiones_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        tablas_Courseroom_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        conexiones_JTable.getTableHeader().setFont(gadugi);
        conexiones_JTable.getTableHeader().setBackground(titulo_JLabel.getForeground());
        conexiones_JTable.getTableHeader().setForeground(titulo_JLabel.getBackground());
        
        solicitudes_JButton.setBackground(contenido_JPanel.getForeground());
        respuestas_JButton.setBackground(contenido_JPanel.getBackground());
        metodos_JButton.setBackground(contenido_JPanel.getBackground());
        tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getBackground());
        conexiones_JButton.setBackground(contenido_JPanel.getBackground());
        actualizar_JButton.setBackground(contenido_JPanel.getBackground());
        
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

        contenido_JPanel = new javax.swing.JPanel();
        contenido_Titulo_JPanel = new javax.swing.JPanel();
        titulo_JLabel = new javax.swing.JLabel();
        respuestas_JButton = new javax.swing.JButton();
        solicitudes_JButton = new javax.swing.JButton();
        metodos_JButton = new javax.swing.JButton();
        tablas_CourseRoom_JButton = new javax.swing.JButton();
        actualizar_JButton = new javax.swing.JButton();
        conexiones_JButton = new javax.swing.JButton();
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
        tablas_Courseroom_JScrollPane = new javax.swing.JScrollPane();
        tablas_CourseRoom_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        tablas_CourseRoom_JTable.setDefaultRenderer(String.class, new Celda_Renderer());
        conexiones_JScrollPane = new javax.swing.JScrollPane();
        conexiones_JTable = new JTable(){
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
        conexiones_JTable.setDefaultRenderer(String.class, new Celda_Renderer());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Servidor De CourseRoom");
        setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        contenido_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        contenido_JPanel.setForeground(new java.awt.Color(104, 194, 232));

        contenido_Titulo_JPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido_Titulo_JPanel.setMaximumSize(new java.awt.Dimension(32767, 68));
        contenido_Titulo_JPanel.setOpaque(false);
        contenido_Titulo_JPanel.setPreferredSize(new java.awt.Dimension(276, 68));

        titulo_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        titulo_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_JLabel.setMaximumSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setMinimumSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setOpaque(true);
        titulo_JLabel.setPreferredSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setRequestFocusEnabled(false);

        respuestas_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/responsibility.png"))); // NOI18N
        respuestas_JButton.setBorder(null);
        ((ImageIcon)respuestas_JButton.getIcon()).getImage().flush();
        respuestas_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                respuestas_JButtonMouseClicked(evt);
            }
        });

        solicitudes_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/request.png"))); // NOI18N
        solicitudes_JButton.setBorder(null);
        ((ImageIcon)solicitudes_JButton.getIcon()).getImage().flush();
        solicitudes_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solicitudes_JButtonMouseClicked(evt);
            }
        });

        metodos_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/programming.png"))); // NOI18N
        metodos_JButton.setBorder(null);
        ((ImageIcon)metodos_JButton.getIcon()).getImage().flush();
        metodos_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                metodos_JButtonMouseClicked(evt);
            }
        });

        tablas_CourseRoom_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/information.png"))); // NOI18N
        tablas_CourseRoom_JButton.setBorder(null);
        ((ImageIcon)tablas_CourseRoom_JButton.getIcon()).getImage().flush();
        tablas_CourseRoom_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablas_CourseRoom_JButtonMouseClicked(evt);
            }
        });

        actualizar_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/updated.png"))); // NOI18N
        actualizar_JButton.setBorder(null);
        ((ImageIcon)actualizar_JButton.getIcon()).getImage().flush();
        actualizar_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizar_JButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actualizar_JButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actualizar_JButtonMouseExited(evt);
            }
        });

        conexiones_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/verified-user.png"))); // NOI18N
        conexiones_JButton.setBorder(null);
        ((ImageIcon)conexiones_JButton.getIcon()).getImage().flush();
        conexiones_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conexiones_JButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout contenido_Titulo_JPanelLayout = new javax.swing.GroupLayout(contenido_Titulo_JPanel);
        contenido_Titulo_JPanel.setLayout(contenido_Titulo_JPanelLayout);
        contenido_Titulo_JPanelLayout.setHorizontalGroup(
            contenido_Titulo_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenido_Titulo_JPanelLayout.createSequentialGroup()
                .addComponent(titulo_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(solicitudes_JButton)
                .addGap(18, 18, 18)
                .addComponent(respuestas_JButton)
                .addGap(18, 18, 18)
                .addComponent(metodos_JButton)
                .addGap(18, 18, 18)
                .addComponent(tablas_CourseRoom_JButton)
                .addGap(18, 18, 18)
                .addComponent(conexiones_JButton)
                .addGap(18, 18, 18)
                .addComponent(actualizar_JButton))
        );
        contenido_Titulo_JPanelLayout.setVerticalGroup(
            contenido_Titulo_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(solicitudes_JButton)
            .addComponent(respuestas_JButton)
            .addComponent(metodos_JButton)
            .addComponent(tablas_CourseRoom_JButton)
            .addComponent(actualizar_JButton)
            .addComponent(conexiones_JButton)
        );

        principal_JLayeredPane.setLayout(new java.awt.CardLayout());

        solicitudes_JScrollPane.setBorder(null);

        solicitudes_JTable.setAutoCreateRowSorter(true);
        solicitudes_JTable.setBackground(new java.awt.Color(14, 30, 64));
        solicitudes_JTable.setFont(new java.awt.Font("Gadugi", 0, 13)); // NOI18N
        solicitudes_JTable.setForeground(new java.awt.Color(104, 194, 232));
        solicitudes_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Solicitud", "Solicitud", "Cliente", "Fecha Solicitud"
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
        respuestas_JTable.setFont(new java.awt.Font("Gadugi", 0, 13)); // NOI18N
        respuestas_JTable.setForeground(new java.awt.Color(104, 194, 232));
        respuestas_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Respuesta", "Respuesta", "Cliente", "Fecha Respuesta"
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
                "Método"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        metodos_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        metodos_JTable.setRowHeight(50);
        metodos_JTable.setRowMargin(15);
        metodos_JTable.setShowGrid(true);
        metodos_JTable.setRowSorter(new TableRowSorter(metodos_JTable.getModel()));
        metodos_JScrollPane.setViewportView(metodos_JTable);

        principal_JLayeredPane.add(metodos_JScrollPane, "Metodos");

        tablas_Courseroom_JScrollPane.setBorder(null);

        tablas_CourseRoom_JTable.setAutoCreateRowSorter(true);
        tablas_CourseRoom_JTable.setBackground(new java.awt.Color(14, 30, 64));
        tablas_CourseRoom_JTable.setFont(new java.awt.Font("Gadugi", 0, 13)); // NOI18N
        tablas_CourseRoom_JTable.setForeground(new java.awt.Color(104, 194, 232));
        tablas_CourseRoom_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tabla", "# Registros", "Actualizado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablas_CourseRoom_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablas_CourseRoom_JTable.setRowHeight(100);
        tablas_CourseRoom_JTable.setRowMargin(10);
        tablas_CourseRoom_JTable.setShowGrid(true);
        tablas_CourseRoom_JTable.setRowSorter(new TableRowSorter(tablas_CourseRoom_JTable.getModel()));
        tablas_Courseroom_JScrollPane.setViewportView(tablas_CourseRoom_JTable);

        principal_JLayeredPane.add(tablas_Courseroom_JScrollPane, "Tablas_CourseRoom");

        conexiones_JScrollPane.setBorder(null);

        conexiones_JTable.setAutoCreateRowSorter(true);
        conexiones_JTable.setBackground(new java.awt.Color(14, 30, 64));
        conexiones_JTable.setFont(new java.awt.Font("Gadugi", 0, 13)); // NOI18N
        conexiones_JTable.setForeground(new java.awt.Color(104, 194, 232));
        conexiones_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Conectado El"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        conexiones_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        conexiones_JTable.setRowHeight(50);
        conexiones_JTable.setRowMargin(15);
        conexiones_JTable.setShowGrid(true);
        conexiones_JTable.setRowSorter(new TableRowSorter(conexiones_JTable.getModel()));
        conexiones_JScrollPane.setViewportView(conexiones_JTable);

        principal_JLayeredPane.add(conexiones_JScrollPane, "Conexiones");

        javax.swing.GroupLayout contenido_JPanelLayout = new javax.swing.GroupLayout(contenido_JPanel);
        contenido_JPanel.setLayout(contenido_JPanelLayout);
        contenido_JPanelLayout.setHorizontalGroup(
            contenido_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenido_JPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(contenido_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(principal_JLayeredPane)
                    .addComponent(contenido_Titulo_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        contenido_JPanelLayout.setVerticalGroup(
            contenido_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenido_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenido_Titulo_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(principal_JLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(contenido_JPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            // TODO add your handling code here:
            metodos.Cerrar_Conexion();
            webServer.shutdown();
            this.dispose();
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_formWindowClosing

    private void respuestas_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_respuestas_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Respuestas");
            titulo_JLabel.setText("Respuestas");
            carta_Visible = 1;
            solicitudes_JButton.setBackground(contenido_JPanel.getBackground());
            respuestas_JButton.setBackground(contenido_JPanel.getForeground());
            metodos_JButton.setBackground(contenido_JPanel.getBackground());
            tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getBackground());
            conexiones_JButton.setBackground(contenido_JPanel.getBackground());
        }
    }//GEN-LAST:event_respuestas_JButtonMouseClicked

    private void solicitudes_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solicitudes_JButtonMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isLeftMouseButton(evt)) {
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Solicitudes");
            titulo_JLabel.setText("Solicitudes");
            carta_Visible = 0;
            solicitudes_JButton.setBackground(contenido_JPanel.getForeground());
            respuestas_JButton.setBackground(contenido_JPanel.getBackground());
            metodos_JButton.setBackground(contenido_JPanel.getBackground());
            tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getBackground());
            conexiones_JButton.setBackground(contenido_JPanel.getBackground());

        }
    }//GEN-LAST:event_solicitudes_JButtonMouseClicked

    private void metodos_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metodos_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Metodos");
            titulo_JLabel.setText("Metodos");
            carta_Visible = 2;
            solicitudes_JButton.setBackground(contenido_JPanel.getBackground());
            respuestas_JButton.setBackground(contenido_JPanel.getBackground());
            metodos_JButton.setBackground(contenido_JPanel.getForeground());
            tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getBackground());
            conexiones_JButton.setBackground(contenido_JPanel.getBackground());
            
        }
    }//GEN-LAST:event_metodos_JButtonMouseClicked

    private void tablas_CourseRoom_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablas_CourseRoom_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Tablas_CourseRoom");
            titulo_JLabel.setText("Tablas CourseRoom");
            carta_Visible = 3;
            solicitudes_JButton.setBackground(contenido_JPanel.getBackground());
            respuestas_JButton.setBackground(contenido_JPanel.getBackground());
            metodos_JButton.setBackground(contenido_JPanel.getBackground());
            tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getForeground());
            conexiones_JButton.setBackground(contenido_JPanel.getBackground());
            
        }
    }//GEN-LAST:event_tablas_CourseRoom_JButtonMouseClicked

    private void actualizar_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            switch (carta_Visible) {
                case 0:
                {
                    try {
                        Obtener_Solicitudes();
                    } catch (SQLException ex) {
                        
                    }
                }
                    break;

                case 1:
                {
                    try {
                        Obtener_Respuestas();
                    } catch (SQLException ex) {
                        
                    }
                }
                    break;

                case 2:
                {
                    try {
                        Obtener_Metodos();
                    } catch (SQLException ex) {
                        
                    }
                }
                    break;

                case 3:
                {
                    try {
                        Obtener_Tablas_CourseRoom();
                    } catch (SQLException ex) {
                        
                    }
                }
                    break;

            }
        }
    }//GEN-LAST:event_actualizar_JButtonMouseClicked

    private void actualizar_JButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar_JButtonMouseEntered
        // TODO add your handling code here:
        actualizar_JButton.setBackground(contenido_JPanel.getForeground());
    }//GEN-LAST:event_actualizar_JButtonMouseEntered

    private void actualizar_JButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizar_JButtonMouseExited
        // TODO add your handling code here:
        actualizar_JButton.setBackground(contenido_JPanel.getBackground());
    }//GEN-LAST:event_actualizar_JButtonMouseExited

    private void conexiones_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conexiones_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Conexiones");
            titulo_JLabel.setText("Conexiones");
            carta_Visible = 4;
            solicitudes_JButton.setBackground(contenido_JPanel.getBackground());
            respuestas_JButton.setBackground(contenido_JPanel.getBackground());
            metodos_JButton.setBackground(contenido_JPanel.getBackground());
            tablas_CourseRoom_JButton.setBackground(contenido_JPanel.getBackground());
            conexiones_JButton.setBackground(contenido_JPanel.getForeground());
            
        }
    }//GEN-LAST:event_conexiones_JButtonMouseClicked

    
    public static void Obtener_Solicitudes() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) solicitudes_JTable.getModel();
        modelo.setRowCount(0);
        ResultSet resultados = metodos.Sp_ObtenerSolicitudes();
        if (resultados != null) {
            String[] celdas = new String[4];
            while (resultados.next()) {

                celdas[0] = String.valueOf(resultados.getInt("IdSolicitud"));
                celdas[1] = resultados.getString("Solicitud");
                celdas[2] = resultados.getString("Cliente");
                celdas[3] = resultados.getString("FechaSolicitud");

                modelo.addRow(celdas);
            }

            resultados.close();
        }

    }
    
    public static void Obtener_Respuestas() throws SQLException {
        
        DefaultTableModel modelo = (DefaultTableModel) respuestas_JTable.getModel();
        modelo.setRowCount(0);
        ResultSet resultados = Metodos.Sp_ObtenerRespuestas();
        if (resultados != null) {
            String[] celdas = new String[4];
            
            while (resultados.next()) {

                celdas[0] = String.valueOf(resultados.getInt("IdRespuesta"));
                celdas[1] = resultados.getString("Respuesta");
                celdas[2] = resultados.getString("Cliente");
                celdas[3] = resultados.getString("FechaRespuesta");

                modelo.addRow(celdas);
            }

            resultados.close();
        }
    }
    
    private void Obtener_Metodos() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) metodos_JTable.getModel();
        modelo.setRowCount(0);
        ResultSet resultados = Metodos.Sp_ObtenerMetodos();
        if (resultados != null) {
            String[] celdas = new String[1];
            
            while (resultados.next()) {

                celdas[0] = resultados.getString("Metodo");

                modelo.addRow(celdas);
            }
            
            resultados.close();
        }

    }
    
    private static void Obtener_Tablas_CourseRoom() throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) tablas_CourseRoom_JTable.getModel();
        modelo.setRowCount(0);
        ResultSet resultados = Metodos.Sp_ObtenerTablasCourseRoom();
        if (resultados != null) {
            String[] celdas = new String[3];
            
            while (resultados.next()) {

                celdas[0] = resultados.getString("Tabla");
                celdas[1] = String.valueOf(resultados.getInt("CantidadRegistros"));
                celdas[2] = resultados.getString("FechaActualizacion");

                modelo.addRow(celdas);
            }

            resultados.close();
        }
    }
    
    
    public static void Agregar_Conexion(String cliente){
        
        DateTimeFormatter formato_Fecha = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy hh:mm:ss a");
        LocalDateTime fecha_Hora_Servidor = LocalDateTime.now();
        String tiempo = fecha_Hora_Servidor.format(formato_Fecha);
        DefaultTableModel modelo = (DefaultTableModel) conexiones_JTable.getModel();
        
        for(int i = 0; i < modelo.getRowCount();i++){
            if(modelo.getValueAt(i, 0).equals(cliente)){
                modelo.setValueAt(tiempo,i, 1);
                return;
            }
        }
        String[] celdas = new String[2];
        celdas[0] = cliente;
        celdas[1] = tiempo;
        modelo.addRow(celdas);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar_JButton;
    private javax.swing.JButton conexiones_JButton;
    private javax.swing.JScrollPane conexiones_JScrollPane;
    private static javax.swing.JTable conexiones_JTable;
    private javax.swing.JPanel contenido_JPanel;
    private javax.swing.JPanel contenido_Titulo_JPanel;
    private javax.swing.JButton metodos_JButton;
    private javax.swing.JScrollPane metodos_JScrollPane;
    private javax.swing.JTable metodos_JTable;
    private javax.swing.JLayeredPane principal_JLayeredPane;
    private javax.swing.JButton respuestas_JButton;
    private javax.swing.JScrollPane respuestas_JScrollPane;
    private static javax.swing.JTable respuestas_JTable;
    private javax.swing.JButton solicitudes_JButton;
    private javax.swing.JScrollPane solicitudes_JScrollPane;
    private static javax.swing.JTable solicitudes_JTable;
    private javax.swing.JButton tablas_CourseRoom_JButton;
    private static javax.swing.JTable tablas_CourseRoom_JTable;
    private javax.swing.JScrollPane tablas_Courseroom_JScrollPane;
    private javax.swing.JLabel titulo_JLabel;
    // End of variables declaration//GEN-END:variables

}
