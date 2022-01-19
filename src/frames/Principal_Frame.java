/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import clases.Celda_Renderer;
import courseroom_server.CourseRoom_Server;
import java.awt.CardLayout;
import java.awt.Font;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author LENOVO
 */
public class Principal_Frame extends javax.swing.JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Principal_Frame() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        try {
            this.setIconImage(ImageIO.read(getClass().getResource("/recursos/iconos/Course_Room_Brand.png")));
        } catch (IOException ex) {
            
        }
        
        var icono = new ImageIcon(getClass().getResource("/recursos/imagenes/Course_Room_Brand_Blue.png"));
        
        //Respuestas: 
        logo_Respuestas_JLabel.setIcon(icono);
   
        respuestas_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        respuestas_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        Font gadugi = new Font("Gadugi", Font.BOLD, 16);
        respuestas_JTable.getTableHeader().setFont(gadugi);
        respuestas_JTable.getTableHeader().setBackground(logo_Respuestas_JLabel.getForeground());
        respuestas_JTable.getTableHeader().setForeground(this.getBackground());
     
        DefaultTableModel modelo = (DefaultTableModel) respuestas_JTable.getModel();

        String[] insercion = new String[3];
        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 15);i++){
            insercion[0] = CourseRoom_Server.Faker().university().name();
            insercion[1] = CourseRoom_Server.Faker().internet().macAddress();
            insercion[2] = CourseRoom_Server.Faker().date().birthday(21, 22).toString();
            
            modelo.addRow(insercion);
        }
        
        //Peticiones:
        logo_Peticiones_JLabel.setIcon(icono);
   
        peticiones_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        peticiones_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        peticiones_JTable.getTableHeader().setFont(gadugi);
        peticiones_JTable.getTableHeader().setBackground(logo_Peticiones_JLabel.getForeground());
        peticiones_JTable.getTableHeader().setForeground(this.getBackground());
     
        modelo = (DefaultTableModel) peticiones_JTable.getModel();

        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 15);i++){
            insercion[0] = CourseRoom_Server.Faker().job().field();
            insercion[1] = CourseRoom_Server.Faker().internet().macAddress();
            insercion[2] = CourseRoom_Server.Faker().date().birthday(21, 22).toString();
            
            modelo.addRow(insercion);
        }
        
        
        //Metodos:
         logo_Metodos_JLabel.setIcon(icono);
   
        metodos_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        metodos_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        metodos_JTable.getTableHeader().setFont(gadugi);
        metodos_JTable.getTableHeader().setBackground(logo_Metodos_JLabel.getForeground());
        metodos_JTable.getTableHeader().setForeground(this.getBackground());
     
        modelo = (DefaultTableModel) metodos_JTable.getModel();

        insercion = new String[2];
        for(int i = 0; i < CourseRoom_Server.Faker().number().numberBetween(5, 35);i++){
            insercion[0] = CourseRoom_Server.Faker().artist().name();
            insercion[1] = String.valueOf(CourseRoom_Server.Faker().bool().bool());
            
            modelo.addRow(insercion);
        }
        
        //Info General:
        logo_Info_General_JLabel.setIcon(icono);

        courseroom_DB_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        courseroom_DB_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        courseroom_Server_DB_JScrollPane.getVerticalScrollBar().setUnitIncrement(40);
        courseroom_Server_DB_JScrollPane.getHorizontalScrollBar().setUnitIncrement(40);
        
        courseroom_DB_JTable.getTableHeader().setFont(gadugi);
        courseroom_DB_JTable.getTableHeader().setBackground(logo_Info_General_JLabel.getForeground());
        courseroom_DB_JTable.getTableHeader().setForeground(this.getBackground());
        courseroom_Server_DB_JTable.getTableHeader().setFont(gadugi);
        courseroom_Server_DB_JTable.getTableHeader().setBackground(logo_Info_General_JLabel.getForeground());
        courseroom_Server_DB_JTable.getTableHeader().setForeground(this.getBackground());

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

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principal_JLayeredPane = new javax.swing.JLayeredPane();
        peticiones_JPanel = new javax.swing.JPanel();
        logo_Peticiones_JLabel = new javax.swing.JLabel();
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
        respuestas_JPanel = new javax.swing.JPanel();
        logo_Respuestas_JLabel = new javax.swing.JLabel();
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
        metodos_JPanel = new javax.swing.JPanel();
        logo_Metodos_JLabel = new javax.swing.JLabel();
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
        logo_Info_General_JLabel = new javax.swing.JLabel();
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
        peticiones_JMenu = new javax.swing.JMenu();
        respuestas_JMenu = new javax.swing.JMenu();
        metodos_JMenu = new javax.swing.JMenu();
        info_General_JMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Servidor De CourseRoom");
        setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        principal_JLayeredPane.setLayout(new java.awt.CardLayout());

        peticiones_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        peticiones_JPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_Peticiones_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        logo_Peticiones_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        logo_Peticiones_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_Peticiones_JLabel.setText("Peticiones");
        logo_Peticiones_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_Peticiones_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_Peticiones_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

        peticiones_JScrollPane.setBorder(null);

        peticiones_JTable.setAutoCreateRowSorter(true);
        peticiones_JTable.setBackground(new java.awt.Color(14, 30, 64));
        peticiones_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        peticiones_JTable.setForeground(new java.awt.Color(104, 194, 232));
        peticiones_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Petición", "Cliente", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        peticiones_JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        peticiones_JTable.setRowHeight(100);
        peticiones_JTable.setRowMargin(10);
        peticiones_JTable.setShowGrid(true);
        peticiones_JTable.setRowSorter(new TableRowSorter(peticiones_JTable.getModel()));
        peticiones_JScrollPane.setViewportView(peticiones_JTable);

        javax.swing.GroupLayout peticiones_JPanelLayout = new javax.swing.GroupLayout(peticiones_JPanel);
        peticiones_JPanel.setLayout(peticiones_JPanelLayout);
        peticiones_JPanelLayout.setHorizontalGroup(
            peticiones_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peticiones_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(peticiones_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(peticiones_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addComponent(logo_Peticiones_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        peticiones_JPanelLayout.setVerticalGroup(
            peticiones_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peticiones_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_Peticiones_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(peticiones_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        principal_JLayeredPane.add(peticiones_JPanel, "Peticiones");

        respuestas_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        respuestas_JPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_Respuestas_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        logo_Respuestas_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        logo_Respuestas_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_Respuestas_JLabel.setText("Respuestas");
        logo_Respuestas_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_Respuestas_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_Respuestas_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

        respuestas_JScrollPane.setBorder(null);

        respuestas_JTable.setAutoCreateRowSorter(true);
        respuestas_JTable.setBackground(new java.awt.Color(14, 30, 64));
        respuestas_JTable.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        respuestas_JTable.setForeground(new java.awt.Color(104, 194, 232));
        respuestas_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Respuesta", "Cliente", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        javax.swing.GroupLayout respuestas_JPanelLayout = new javax.swing.GroupLayout(respuestas_JPanel);
        respuestas_JPanel.setLayout(respuestas_JPanelLayout);
        respuestas_JPanelLayout.setHorizontalGroup(
            respuestas_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(respuestas_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(respuestas_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(respuestas_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addComponent(logo_Respuestas_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        respuestas_JPanelLayout.setVerticalGroup(
            respuestas_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(respuestas_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_Respuestas_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(respuestas_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        principal_JLayeredPane.add(respuestas_JPanel, "Respuestas");

        metodos_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        metodos_JPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_Metodos_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        logo_Metodos_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        logo_Metodos_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_Metodos_JLabel.setText("Métodos");
        logo_Metodos_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_Metodos_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_Metodos_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

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

        javax.swing.GroupLayout metodos_JPanelLayout = new javax.swing.GroupLayout(metodos_JPanel);
        metodos_JPanel.setLayout(metodos_JPanelLayout);
        metodos_JPanelLayout.setHorizontalGroup(
            metodos_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metodos_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(metodos_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(metodos_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addComponent(logo_Metodos_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        metodos_JPanelLayout.setVerticalGroup(
            metodos_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metodos_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_Metodos_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(metodos_JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        principal_JLayeredPane.add(metodos_JPanel, "Metodos");

        info_General_JPanel.setBackground(new java.awt.Color(14, 30, 64));
        info_General_JPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        logo_Info_General_JLabel.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        logo_Info_General_JLabel.setForeground(new java.awt.Color(104, 194, 232));
        logo_Info_General_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_Info_General_JLabel.setText("Info General");
        logo_Info_General_JLabel.setMaximumSize(new java.awt.Dimension(175, 125));
        logo_Info_General_JLabel.setMinimumSize(new java.awt.Dimension(175, 125));
        logo_Info_General_JLabel.setPreferredSize(new java.awt.Dimension(175, 125));

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

        javax.swing.GroupLayout info_General_JPanelLayout = new javax.swing.GroupLayout(info_General_JPanel);
        info_General_JPanel.setLayout(info_General_JPanelLayout);
        info_General_JPanelLayout.setHorizontalGroup(
            info_General_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_General_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info_General_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logo_Info_General_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addGroup(info_General_JPanelLayout.createSequentialGroup()
                        .addComponent(courseroom_DB_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(courseroom_Server_DB_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        info_General_JPanelLayout.setVerticalGroup(
            info_General_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_General_JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo_Info_General_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(info_General_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(courseroom_Server_DB_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addComponent(courseroom_DB_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        principal_JLayeredPane.add(info_General_JPanel, "Info_General");

        peticiones_JMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/request.png"))); // NOI18N
        peticiones_JMenu.setText("Peticiones");
        peticiones_JMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        peticiones_JMenu.setFocusCycleRoot(true);
        peticiones_JMenu.setFocusPainted(true);
        peticiones_JMenu.setFocusTraversalPolicyProvider(true);
        peticiones_JMenu.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        peticiones_JMenu.setOpaque(true);
        peticiones_JMenu.setRolloverEnabled(false);
        peticiones_JMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                peticiones_JMenuMouseClicked(evt);
            }
        });
        barra_Menu_JMenuBar.add(peticiones_JMenu);

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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(principal_JLayeredPane)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(principal_JLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void peticiones_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peticiones_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Peticiones");
        }
    }//GEN-LAST:event_peticiones_JMenuMouseClicked

    private void respuestas_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_respuestas_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Respuestas");
        }
    }//GEN-LAST:event_respuestas_JMenuMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void metodos_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metodos_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            
            ((CardLayout)principal_JLayeredPane.getLayout()).show(principal_JLayeredPane, "Metodos");
        }
    }//GEN-LAST:event_metodos_JMenuMouseClicked

    private void info_General_JMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_info_General_JMenuMouseClicked
        // TODO add your handling code here:
        if(SwingUtilities.isLeftMouseButton(evt)){
            
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
    private javax.swing.JLabel logo_Info_General_JLabel;
    private javax.swing.JLabel logo_Metodos_JLabel;
    private javax.swing.JLabel logo_Peticiones_JLabel;
    private javax.swing.JLabel logo_Respuestas_JLabel;
    private javax.swing.JMenu metodos_JMenu;
    private javax.swing.JPanel metodos_JPanel;
    private javax.swing.JScrollPane metodos_JScrollPane;
    private javax.swing.JTable metodos_JTable;
    private javax.swing.JMenu peticiones_JMenu;
    private javax.swing.JPanel peticiones_JPanel;
    private javax.swing.JScrollPane peticiones_JScrollPane;
    private javax.swing.JTable peticiones_JTable;
    private javax.swing.JLayeredPane principal_JLayeredPane;
    private javax.swing.JMenu respuestas_JMenu;
    private javax.swing.JPanel respuestas_JPanel;
    private javax.swing.JScrollPane respuestas_JScrollPane;
    private javax.swing.JTable respuestas_JTable;
    // End of variables declaration//GEN-END:variables

}
