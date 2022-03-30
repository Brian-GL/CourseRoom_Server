/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Celda_Renderer;
import clases.Servidor_DB;
import clases.Solicitudes;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
public class CourseRoom_Server_Frame extends javax.swing.JFrame {

    private byte carta_Visible;
    private final WebServer webServer;
    private Solicitudes solicitudes;
    private Servidor_DB respuestas;
    
    @SuppressWarnings({"OverridableMethodCallInConstructor", "null"})
    public CourseRoom_Server_Frame() throws ClassNotFoundException, SQLException, IOException {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        solicitudes = Solicitudes.getInstance() ;
        respuestas = Servidor_DB.getInstance();
        
        webServer = new WebServer(9000);
        webServer.addHandler("CourseRoom_Server", solicitudes);
        webServer.start();
        System.out.println("WebServer Starting At Port 9000");
       
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
        
        solicitudes_JButton.setBackground(contenido_JPanel.getForeground());
        respuestas_JButton.setBackground(contenido_JPanel.getBackground());
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
        actualizar_JButton = new javax.swing.JButton();
        principal_JLayeredPane = new javax.swing.JLayeredPane();
        solicitudes_JScrollPane = new javax.swing.JScrollPane();
        solicitudes_JTable = new javax.swing.JTable();
        solicitudes_JTable.setDefaultRenderer(Celda_Renderer.class, new Celda_Renderer());
        respuestas_JScrollPane = new javax.swing.JScrollPane();
        respuestas_JTable = new javax.swing.JTable();
        respuestas_JTable.setDefaultRenderer(Celda_Renderer.class, new Celda_Renderer());

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

        contenido_Titulo_JPanel.setMaximumSize(new java.awt.Dimension(32767, 68));
        contenido_Titulo_JPanel.setOpaque(false);
        contenido_Titulo_JPanel.setPreferredSize(new java.awt.Dimension(276, 68));

        titulo_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        titulo_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_JLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        titulo_JLabel.setMaximumSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setMinimumSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setOpaque(true);
        titulo_JLabel.setPreferredSize(new java.awt.Dimension(449, 68));
        titulo_JLabel.setRequestFocusEnabled(false);

        respuestas_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/responsibility.png"))); // NOI18N
        respuestas_JButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ((ImageIcon)respuestas_JButton.getIcon()).getImage().flush();
        respuestas_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                respuestas_JButtonMouseClicked(evt);
            }
        });

        solicitudes_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/request.png"))); // NOI18N
        solicitudes_JButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ((ImageIcon)solicitudes_JButton.getIcon()).getImage().flush();
        solicitudes_JButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solicitudes_JButtonMouseClicked(evt);
            }
        });

        actualizar_JButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/updated.png"))); // NOI18N
        actualizar_JButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        javax.swing.GroupLayout contenido_Titulo_JPanelLayout = new javax.swing.GroupLayout(contenido_Titulo_JPanel);
        contenido_Titulo_JPanel.setLayout(contenido_Titulo_JPanelLayout);
        contenido_Titulo_JPanelLayout.setHorizontalGroup(
            contenido_Titulo_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenido_Titulo_JPanelLayout.createSequentialGroup()
                .addComponent(titulo_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addComponent(solicitudes_JButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respuestas_JButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actualizar_JButton))
        );
        contenido_Titulo_JPanelLayout.setVerticalGroup(
            contenido_Titulo_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(solicitudes_JButton)
            .addComponent(respuestas_JButton)
            .addComponent(actualizar_JButton)
        );

        principal_JLayeredPane.setLayout(new java.awt.CardLayout());

        solicitudes_JScrollPane.setBorder(null);

        solicitudes_JTable.setAutoCreateRowSorter(true);
        solicitudes_JTable.setBackground(new java.awt.Color(14, 30, 64));
        solicitudes_JTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        solicitudes_JTable.setForeground(new java.awt.Color(104, 194, 232));
        solicitudes_JTable.setModel(

            new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Id", "Solicitud", "Cliente","Dirección IP", "Fecha"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }

                @Override
                public Class getColumnClass(int column)
                {
                    for(int i = 0; i < solicitudes_JTable.getRowCount(); i++)
                    {
                        //The first valid value of a cell of given column is retrieved.
                        if(getValueAt(i,column) != null)
                        {
                            return getValueAt(i, column).getClass();
                        }
                    }
                    //if no valid value is found, default renderer is returned.
                    return super.getColumnClass(column);
                }
            });
            solicitudes_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
            solicitudes_JTable.setGridColor(new java.awt.Color(104, 194, 232));
            solicitudes_JTable.setRowHeight(100);
            solicitudes_JTable.setRowMargin(5);
            solicitudes_JTable.setShowGrid(true);
            solicitudes_JTable.setRowSorter(new TableRowSorter(solicitudes_JTable.getModel()));
            solicitudes_JScrollPane.setViewportView(solicitudes_JTable);

            principal_JLayeredPane.add(solicitudes_JScrollPane, "Solicitudes");

            respuestas_JScrollPane.setBorder(null);

            respuestas_JTable.setAutoCreateRowSorter(true);
            respuestas_JTable.setBackground(new java.awt.Color(14, 30, 64));
            respuestas_JTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            respuestas_JTable.setForeground(new java.awt.Color(104, 194, 232));
            respuestas_JTable.setModel(

                new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Id", "Respuesta", "Cliente","Dirección IP", "Fecha"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }

                    @Override
                    public Class getColumnClass(int column)
                    {
                        for(int i = 0; i < respuestas_JTable.getRowCount(); i++)
                        {
                            //The first valid value of a cell of given column is retrieved.
                            if(getValueAt(i,column) != null)
                            {
                                return getValueAt(i, column).getClass();
                            }
                        }
                        //if no valid value is found, default renderer is returned.
                        return super.getColumnClass(column);
                    }
                });
                respuestas_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                respuestas_JTable.setGridColor(new java.awt.Color(104, 194, 232));
                respuestas_JTable.setRowHeight(100);
                respuestas_JTable.setRowMargin(5);
                respuestas_JTable.setShowGrid(true);
                respuestas_JTable.setRowSorter(new TableRowSorter(respuestas_JTable.getModel()));
                respuestas_JScrollPane.setViewportView(respuestas_JTable);

                principal_JLayeredPane.add(respuestas_JScrollPane, "Respuestas");

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
       
        // TODO add your handling code here:
        webServer.shutdown();
        respuestas.Cerrar_Conexion();
        solicitudes.Cerrar_Conexion();
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void respuestas_JButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_respuestas_JButtonMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Respuestas");
            titulo_JLabel.setText("Respuestas");
            carta_Visible = 1;
            solicitudes_JButton.setBackground(contenido_JPanel.getBackground());
            respuestas_JButton.setBackground(contenido_JPanel.getForeground());
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

        }
    }//GEN-LAST:event_solicitudes_JButtonMouseClicked

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

    
    public void Obtener_Solicitudes() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) solicitudes_JTable.getModel();
        modelo.setRowCount(0);
        try(ResultSet resultados = respuestas.Obtener_Solicitudes()){
            if (resultados != null) {
                Celda_Renderer[] celdas = new Celda_Renderer[5];
                Celda_Renderer celda;
                String solicitud;
                while (resultados.next()) {

                    celda = new Celda_Renderer(String.valueOf(resultados.getInt("IdSolicitud")));
                    celdas[0] = celda;
                    solicitud = resultados.getString("Solicitud");
                    celda = new Celda_Renderer(solicitud);
                    celdas[1] = celda;
                    celda = new Celda_Renderer(resultados.getString("Cliente"));
                    celdas[2] = celda;
                    celda = new Celda_Renderer(resultados.getString("DireccionIP"));
                    celdas[3] = celda;
                    celda = new Celda_Renderer(resultados.getString("Fecha"));
                    celdas[4] = celda;

                    modelo.addRow(celdas);
                    solicitudes_JTable.setRowHeight(modelo.getRowCount()-1, 
                            Altura_Fila_Tabla(solicitud != null ? solicitud.length() : 96));

                }
            }
        }
    }
    
    public void Obtener_Respuestas() throws SQLException {
        
        DefaultTableModel modelo = (DefaultTableModel) respuestas_JTable.getModel();
        modelo.setRowCount(0);
        try(ResultSet resultados = respuestas.Obtener_Respuestas()){
            if (resultados != null) {
                Celda_Renderer[] celdas = new Celda_Renderer[5];
                Celda_Renderer celda;
                String respuesta;
                while (resultados.next()) {

                    celda = new Celda_Renderer(String.valueOf(resultados.getInt("IdRespuesta")));
                    celdas[0] = celda;
                    respuesta = resultados.getString("Respuesta");
                    celda = new Celda_Renderer(respuesta);
                    celdas[1] = celda;
                    celda = new Celda_Renderer(resultados.getString("Cliente"));
                    celdas[2] = celda;
                    celda = new Celda_Renderer(resultados.getString("DireccionIP"));
                    celdas[3] = celda;
                    celda = new Celda_Renderer(resultados.getString("Fecha"));
                    celdas[4] = celda;

                    modelo.addRow(celdas);
                    respuestas_JTable.setRowHeight(modelo.getRowCount()-1, 
                            Altura_Fila_Tabla(respuesta != null ? respuesta.length() : 96));
                }
            }
        }
    }
    
    public static void Agregar_Solicitud(String IdSolicitud,String Solicitud, String Cliente,String ip){
        DefaultTableModel modelo = (DefaultTableModel) solicitudes_JTable.getModel();
        Celda_Renderer[] celdas = new Celda_Renderer[5];
        Celda_Renderer celda;
        celda = new Celda_Renderer(IdSolicitud);
        celdas[0] = celda;
        celda = new Celda_Renderer(Solicitud);
        celdas[1] = celda;
        celda = new Celda_Renderer(Cliente);
        celdas[2] = celda;
        celda = new Celda_Renderer(ip);
        celdas[3] = celda;
        DateTimeFormatter formato_Fecha = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy hh:mm:ss");
        celda = new Celda_Renderer(LocalDateTime.now().format(formato_Fecha));
        celdas[4] = celda;
        
        modelo.insertRow(0, celdas);
        solicitudes_JTable.setRowHeight(0, Altura_Fila_Tabla(Solicitud != null ? Solicitud.length() : 96));
        
        if(modelo.getRowCount() >= 250){
            modelo.removeRow(modelo.getRowCount()-1);
        }
        
    }
    
    public static void Agregar_Respuesta(String IdRespuesta,String Respuesta, String Cliente,String ip){
        DefaultTableModel modelo = (DefaultTableModel) respuestas_JTable.getModel();
        Celda_Renderer[] celdas = new Celda_Renderer[5];
        Celda_Renderer celda;
        celda = new Celda_Renderer(IdRespuesta);
        celdas[0] = celda;
        celda = new Celda_Renderer(Respuesta);
        celdas[1] = celda;
        celda = new Celda_Renderer(Cliente);
        celdas[2] = celda;
        celda = new Celda_Renderer(ip);
        celdas[3] = celda;
        DateTimeFormatter formato_Fecha = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy hh:mm:ss");
        celda = new Celda_Renderer(LocalDateTime.now().format(formato_Fecha));
        celdas[4] = celda;
        
        modelo.insertRow(0, celdas);
        respuestas_JTable.setRowHeight(0, Altura_Fila_Tabla(Respuesta != null ? Respuesta.length() : 96));
        
        if(modelo.getRowCount() >= 250){
            modelo.removeRow(modelo.getRowCount()-1);
        }
    }
    
    private static Integer Altura_Fila_Tabla(Integer numero_Letras){
        return (Integer)((numero_Letras/60) * 20) + 44;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar_JButton;
    private javax.swing.JPanel contenido_JPanel;
    private javax.swing.JPanel contenido_Titulo_JPanel;
    private javax.swing.JLayeredPane principal_JLayeredPane;
    private javax.swing.JButton respuestas_JButton;
    private javax.swing.JScrollPane respuestas_JScrollPane;
    private static javax.swing.JTable respuestas_JTable;
    private javax.swing.JButton solicitudes_JButton;
    private javax.swing.JScrollPane solicitudes_JScrollPane;
    private static javax.swing.JTable solicitudes_JTable;
    private javax.swing.JLabel titulo_JLabel;
    // End of variables declaration//GEN-END:variables

}
