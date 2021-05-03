/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personal_Schedule_Management;


import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author admin
 */
public class Menu_Form extends javax.swing.JFrame {

    /**
     * Creates new form Menu_Form
     */
    String status, image_path1, image_path2, image_path3, image_path4, image_path5, image_path6;
    int MouseX, MouseY;
    
    String full_name, user_name, path, id, phone, fsaying;
    Date date;
    
    public Menu_Form() {
        initComponents();
        
    }
    public Menu_Form(String fname, String username, String path, String id, String phone, Date date, String fsaying) throws IOException{
        initComponents();
        
        
        
        jLabel_iblEmpname.setText("Hello, "+fname.toLowerCase());
        jLabel_IblEmpDesignation.setText(username.toLowerCase());
        if(path == null){
            
        }
        else{
            try {
            BufferedImage image = ImageIO.read(new File(path));
            if(image == null){
                
            }
            else{
                int x = jLabel1.getSize().width;
                int y = jLabel1.getSize().height;
                ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                jLabel1.setIcon(icon);
            }
            
        }
        catch (IOException ex) {
            Logger.getLogger(Login_Form.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        }
        
        this.full_name = fname;
        this.user_name = username;
        this.path = path;
        this.id = id;
        this.phone = phone;
        this.date = date;
        this.fsaying = fsaying;
        jTextArea_describle.setLineWrap(true);
        jTextArea_comment.setLineWrap(true);
        
        jPanel_schedule_and_calender.setVisible(true);
        jPanel_viewCalen.setVisible(false);
        jPanel_viewSche.setVisible(false);
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        jLabel_doc1.setVisible(false);
        jLabel_doc2.setVisible(false);
        
    }
    void reset(){
        jTextField_workname.setText("");
        jTextArea_describle.setText("");
        jDateChooser_startday.setDate(null);
        jDateChooser_startday.setDate(null);
        jComboBox_workprocess.setSelectedIndex(1);
    }
    void resetMusic(){
        jTextField_namesong.setText("");
        jTextField_author.setText("");
        jTextField_link.setText("");
        jComboBox_evaluate.setSelectedIndex(0);
        jTextArea_comment.setText("");
    }
    void resetDiary(){
        jDateChooser_date.setDate(null);
        jTextArea_write.setText("");
    }
    // schdule Form
    public ArrayList<User> userList(){
        ArrayList<User> userList = new ArrayList<>();
        
         PreparedStatement ps;
         ResultSet rs;
         
         String query = "SELECT * FROM `schedule_list`";
         
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            User user;
            while(rs.next()){
                user = new User(rs.getString(1), rs.getDate(2), rs.getDate(3),rs.getString(4), rs.getString(5));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
         return userList;
        
    }
    
    public void show_user(){
        ArrayList<User> list = userList();
        DefaultTableModel model = (DefaultTableModel) jTable_viewSchdule.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getWorkname();
            row[1] = list.get(i).getStartday();
            row[2] = list.get(i).getEndday();
            row[3] = list.get(i).getWorkprogress();
            row[4] = list.get(i).getDiscrible();
            model.addRow(row);
            
        }
        
    }
    
    
    public void show_add_user(){
        ArrayList<User> list = userList();
        User user = userList().get(userList().size()-1);
        DefaultTableModel model = (DefaultTableModel)jTable_viewSchdule.getModel();
        model.addRow(new Object[]{
            user.getWorkname(), user.getStartday(),
            user.getEndday(), user.getWorkprogress(),
            user.getDiscrible()
        
        });
    }
     //music Form
     public ArrayList<Music> musicList(){
        ArrayList<Music> musicList = new ArrayList<>();
        
         PreparedStatement ps;
         ResultSet rs;
         
         String query = "SELECT * FROM `music_list`";
         
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            Music music;
            while(rs.next()){
                music = new Music(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));
                musicList.add(music);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
         return musicList;
        
    }
     
     public void show_music(){
        ArrayList<Music> list = musicList();
        DefaultTableModel model = (DefaultTableModel) jTable_viewfavamusic.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getName();
            row[1] = list.get(i).getAuthor();
            row[2] = list.get(i).getLink();
            row[3] = list.get(i).getEvaluate();
            row[4] = list.get(i).getComment();
            model.addRow(row);
            
        }
        
    }
     
     public void show_add_music(){
        ArrayList<Music> list = musicList();
        Music music = musicList().get(musicList().size()-1);
        DefaultTableModel model = (DefaultTableModel)jTable_viewfavamusic.getModel();
        model.addRow(new Object[]{
            music.getName(), music.getAuthor(),
            music.getLink(), music.getEvaluate(),
            music.getComment()
        
        });
    }
    
     
     //Diary
     public void show_diary(){
         if(jDateChooser_date.getDate()==null){
             jDateChooser_date.setDate(null);
             jTextArea_write.setText("");
         }
         else{
            
            PreparedStatement ps;
            ResultSet rs;
         
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser_date.getDate());
            System.out.println(date);
            String query = "SELECT * FROM `diary_list` WHERE `date`=?";
         
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            
            ps.setString(1, date);
            rs = ps.executeQuery();
            if(rs.next()){
                //jDateChooser_date.setDate(rs.getDate("date"));
                jTextArea_write.setText(rs.getString("writediary"));
            }
            else{
                jTextArea_write.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jFrame1 = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jFileChooser2 = new javax.swing.JFileChooser();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jButton_addMusic1 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jPanel_side = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_IblEmpDesignation = new javax.swing.JLabel();
        jPanel_viewProfile = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel_schedule = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel_favariteImage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel_favoriteMusic = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel_diary = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel_bnt6 = new javax.swing.JLabel();
        jPanel_HeaderPanel = new javax.swing.JPanel();
        jLabel_cancel = new javax.swing.JLabel();
        jLabel_iblEmpname = new javax.swing.JLabel();
        jPanel_center = new javax.swing.JPanel();
        jPanel_favoImg = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel_imageBac1 = new javax.swing.JPanel();
        jLabel_image1 = new javax.swing.JLabel();
        jButton_viewImd1 = new javax.swing.JButton();
        jPanel_imageBac2 = new javax.swing.JPanel();
        jLabel_image2 = new javax.swing.JLabel();
        jButton_viewImd2 = new javax.swing.JButton();
        jPanel_imageBac3 = new javax.swing.JPanel();
        jLabel_image3 = new javax.swing.JLabel();
        jButton_viewImd3 = new javax.swing.JButton();
        jPanel_imageBac4 = new javax.swing.JPanel();
        jLabel_image4 = new javax.swing.JLabel();
        jButton_viewImd4 = new javax.swing.JButton();
        jPanel_imageBac5 = new javax.swing.JPanel();
        jLabel_image5 = new javax.swing.JLabel();
        jButton_viewImd5 = new javax.swing.JButton();
        jPanel_imageBac6 = new javax.swing.JPanel();
        jLabel_image6 = new javax.swing.JLabel();
        jButton_viewImd6 = new javax.swing.JButton();
        jPanel_favoMuc = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField_namesong = new javax.swing.JTextField();
        jTextField_author = new javax.swing.JTextField();
        jComboBox_evaluate = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jTextField_link = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_comment = new javax.swing.JTextArea();
        jButton_addMusic = new javax.swing.JButton();
        jButton_deleteMusic = new javax.swing.JButton();
        jButton_updateMusic = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_viewfavamusic = new javax.swing.JTable();
        jPanel_pdiary = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jDateChooser_date = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_write = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        jButton_viewWrite = new javax.swing.JButton();
        jButton_UpadteDiary = new javax.swing.JButton();
        jPanel_p5 = new javax.swing.JPanel();
        jPanel_viewSche = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox_workprocess = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_describle = new javax.swing.JTextArea();
        jTextField_workname = new javax.swing.JTextField();
        jDateChooser_endday = new com.toedter.calendar.JDateChooser();
        jDateChooser_startday = new com.toedter.calendar.JDateChooser();
        jButton_add = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_viewSchdule = new javax.swing.JTable();
        jPanel_viewCalen = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jPanel_schedule_and_calender = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel_viewSchedule = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel_viewCalender = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel_doc2 = new javax.swing.JLabel();
        jLabel_doc1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setText("jLabel6");

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 3, new java.awt.Color(104, 104, 104)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_user_icon.png"))); // NOI18N
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 70));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Add Employee");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 3, new java.awt.Color(104, 104, 104)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_user_icon.png"))); // NOI18N
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 70));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Add Employee");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenuItem3.setText("jMenuItem3");

        jButton_addMusic1.setBackground(new java.awt.Color(153, 153, 255));
        jButton_addMusic1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_addMusic1.setForeground(new java.awt.Color(51, 0, 51));
        jButton_addMusic1.setText("Add");
        jButton_addMusic1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_addMusic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addMusic1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(873, 630));
        setUndecorated(true);

        mainPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 255)));
        mainPanel.setMinimumSize(new java.awt.Dimension(867, 650));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_side.setBackground(new java.awt.Color(0, 80, 204));
        jPanel_side.setMinimumSize(new java.awt.Dimension(130, 641));
        jPanel_side.setPreferredSize(new java.awt.Dimension(130, 600));
        jPanel_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user_menu.png"))); // NOI18N
        jPanel_side.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 100, 90));

        jLabel_IblEmpDesignation.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_IblEmpDesignation.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_IblEmpDesignation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_IblEmpDesignation.setText("username");
        jPanel_side.add(jLabel_IblEmpDesignation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 130, -1));

        jPanel_viewProfile.setBackground(new java.awt.Color(0, 153, 204));
        jPanel_viewProfile.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_viewProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_viewProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_viewProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel_viewProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel_viewProfileMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("View Profile");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel_viewProfileLayout = new javax.swing.GroupLayout(jPanel_viewProfile);
        jPanel_viewProfile.setLayout(jPanel_viewProfileLayout);
        jPanel_viewProfileLayout.setHorizontalGroup(
            jPanel_viewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );
        jPanel_viewProfileLayout.setVerticalGroup(
            jPanel_viewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel_side.add(jPanel_viewProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 110, 40));

        jPanel_schedule.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_schedule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_scheduleMouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/schedule.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 102, 153));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Schedule");

        javax.swing.GroupLayout jPanel_scheduleLayout = new javax.swing.GroupLayout(jPanel_schedule);
        jPanel_schedule.setLayout(jPanel_scheduleLayout);
        jPanel_scheduleLayout.setHorizontalGroup(
            jPanel_scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel_scheduleLayout.setVerticalGroup(
            jPanel_scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_scheduleLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_side.add(jPanel_schedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 70));

        jPanel_favariteImage.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_favariteImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_favariteImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_favariteImageMouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/picture.png"))); // NOI18N

        jLabel8.setBackground(new java.awt.Color(0, 102, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Favorite Image");

        javax.swing.GroupLayout jPanel_favariteImageLayout = new javax.swing.GroupLayout(jPanel_favariteImage);
        jPanel_favariteImage.setLayout(jPanel_favariteImageLayout);
        jPanel_favariteImageLayout.setHorizontalGroup(
            jPanel_favariteImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel_favariteImageLayout.setVerticalGroup(
            jPanel_favariteImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_favariteImageLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_side.add(jPanel_favariteImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 130, 70));

        jPanel_favoriteMusic.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_favoriteMusic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_favoriteMusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_favoriteMusicMouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/music-note.png"))); // NOI18N

        jLabel10.setBackground(new java.awt.Color(0, 102, 153));
        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Favorite Music");

        javax.swing.GroupLayout jPanel_favoriteMusicLayout = new javax.swing.GroupLayout(jPanel_favoriteMusic);
        jPanel_favoriteMusic.setLayout(jPanel_favoriteMusicLayout);
        jPanel_favoriteMusicLayout.setHorizontalGroup(
            jPanel_favoriteMusicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel_favoriteMusicLayout.setVerticalGroup(
            jPanel_favoriteMusicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_favoriteMusicLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_side.add(jPanel_favoriteMusic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 130, 70));

        jPanel_diary.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_diary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_diary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_diaryMouseClicked(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/diary.png"))); // NOI18N

        jLabel14.setBackground(new java.awt.Color(0, 102, 153));
        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Diary");

        javax.swing.GroupLayout jPanel_diaryLayout = new javax.swing.GroupLayout(jPanel_diary);
        jPanel_diary.setLayout(jPanel_diaryLayout);
        jPanel_diaryLayout.setHorizontalGroup(
            jPanel_diaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel_diaryLayout.setVerticalGroup(
            jPanel_diaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_diaryLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jPanel_side.add(jPanel_diary, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 130, 80));

        jLabel_bnt6.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_bnt6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_bnt6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_bnt6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bnt6.setText("Log out");
        jLabel_bnt6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(255, 255, 255)));
        jLabel_bnt6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_bnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_bnt6MouseClicked(evt);
            }
        });
        jPanel_side.add(jLabel_bnt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 110, 41));

        mainPanel.add(jPanel_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        jPanel_HeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HeaderPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_HeaderPanelMouseDragged(evt);
            }
        });
        jPanel_HeaderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_HeaderPanelMousePressed(evt);
            }
        });

        jLabel_cancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel_icon.png"))); // NOI18N
        jLabel_cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_cancelMouseClicked(evt);
            }
        });

        jLabel_iblEmpname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_iblEmpname.setForeground(new java.awt.Color(102, 51, 255));
        jLabel_iblEmpname.setText("FullName");
        jLabel_iblEmpname.setMaximumSize(new java.awt.Dimension(45, 25));
        jLabel_iblEmpname.setMinimumSize(new java.awt.Dimension(45, 25));

        javax.swing.GroupLayout jPanel_HeaderPanelLayout = new javax.swing.GroupLayout(jPanel_HeaderPanel);
        jPanel_HeaderPanel.setLayout(jPanel_HeaderPanelLayout);
        jPanel_HeaderPanelLayout.setHorizontalGroup(
            jPanel_HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HeaderPanelLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel_iblEmpname, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel_HeaderPanelLayout.setVerticalGroup(
            jPanel_HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_iblEmpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(jPanel_HeaderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 870, 35));

        jPanel_center.setBackground(new java.awt.Color(255, 153, 153));
        jPanel_center.setLayout(new java.awt.CardLayout());

        jPanel_favoImg.setBackground(new java.awt.Color(255, 204, 255));

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));

        jLabel47.setBackground(new java.awt.Color(0, 0, 0));
        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Favorite Image");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_imageBac1.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image1.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac1Layout = new javax.swing.GroupLayout(jPanel_imageBac1);
        jPanel_imageBac1.setLayout(jPanel_imageBac1Layout);
        jPanel_imageBac1Layout.setHorizontalGroup(
            jPanel_imageBac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_imageBac1Layout.setVerticalGroup(
            jPanel_imageBac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        jButton_viewImd1.setText("VIEW");
        jButton_viewImd1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd1ActionPerformed(evt);
            }
        });

        jPanel_imageBac2.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image2.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac2Layout = new javax.swing.GroupLayout(jPanel_imageBac2);
        jPanel_imageBac2.setLayout(jPanel_imageBac2Layout);
        jPanel_imageBac2Layout.setHorizontalGroup(
            jPanel_imageBac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
            .addGroup(jPanel_imageBac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel_image2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );
        jPanel_imageBac2Layout.setVerticalGroup(
            jPanel_imageBac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
            .addGroup(jPanel_imageBac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_imageBac2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel_image2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton_viewImd2.setText("VIEW");
        jButton_viewImd2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd2ActionPerformed(evt);
            }
        });

        jPanel_imageBac3.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image3.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac3Layout = new javax.swing.GroupLayout(jPanel_imageBac3);
        jPanel_imageBac3.setLayout(jPanel_imageBac3Layout);
        jPanel_imageBac3Layout.setHorizontalGroup(
            jPanel_imageBac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
            .addGroup(jPanel_imageBac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel_image3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );
        jPanel_imageBac3Layout.setVerticalGroup(
            jPanel_imageBac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel_imageBac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel_image3, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
        );

        jButton_viewImd3.setText("VIEW");
        jButton_viewImd3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd3ActionPerformed(evt);
            }
        });

        jPanel_imageBac4.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image4.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac4Layout = new javax.swing.GroupLayout(jPanel_imageBac4);
        jPanel_imageBac4.setLayout(jPanel_imageBac4Layout);
        jPanel_imageBac4Layout.setHorizontalGroup(
            jPanel_imageBac4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_imageBac4Layout.setVerticalGroup(
            jPanel_imageBac4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        jButton_viewImd4.setText("VIEW");
        jButton_viewImd4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd4ActionPerformed(evt);
            }
        });

        jPanel_imageBac5.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image5.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac5Layout = new javax.swing.GroupLayout(jPanel_imageBac5);
        jPanel_imageBac5.setLayout(jPanel_imageBac5Layout);
        jPanel_imageBac5Layout.setHorizontalGroup(
            jPanel_imageBac5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_imageBac5Layout.setVerticalGroup(
            jPanel_imageBac5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        jButton_viewImd5.setText("VIEW");
        jButton_viewImd5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd5ActionPerformed(evt);
            }
        });

        jPanel_imageBac6.setBackground(new java.awt.Color(255, 204, 255));

        jLabel_image6.setBackground(new java.awt.Color(255, 204, 255));
        jLabel_image6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_image6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel_image6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 255)));
        jLabel_image6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_image6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_image6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_image6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_image6MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_imageBac6Layout = new javax.swing.GroupLayout(jPanel_imageBac6);
        jPanel_imageBac6.setLayout(jPanel_imageBac6Layout);
        jPanel_imageBac6Layout.setHorizontalGroup(
            jPanel_imageBac6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_imageBac6Layout.createSequentialGroup()
                .addComponent(jLabel_image6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_imageBac6Layout.setVerticalGroup(
            jPanel_imageBac6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_image6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        jButton_viewImd6.setText("VIEW");
        jButton_viewImd6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 102, 102), java.awt.Color.lightGray));
        jButton_viewImd6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewImd6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewImd6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_favoImgLayout = new javax.swing.GroupLayout(jPanel_favoImg);
        jPanel_favoImg.setLayout(jPanel_favoImgLayout);
        jPanel_favoImgLayout.setHorizontalGroup(
            jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_imageBac4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton_viewImd4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_imageBac1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton_viewImd1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jButton_viewImd2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_viewImd3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel_imageBac5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addComponent(jButton_viewImd5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel_imageBac2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel_imageBac3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel_imageBac6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addComponent(jButton_viewImd6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(60, 60, 60))))))
        );
        jPanel_favoImgLayout.setVerticalGroup(
            jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_imageBac2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_imageBac3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_viewImd2)
                            .addComponent(jButton_viewImd3)))
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addComponent(jPanel_imageBac1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_viewImd1)))
                .addGap(29, 29, 29)
                .addGroup(jPanel_favoImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addComponent(jPanel_imageBac4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_viewImd4))
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addComponent(jPanel_imageBac5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_viewImd5))
                    .addGroup(jPanel_favoImgLayout.createSequentialGroup()
                        .addComponent(jPanel_imageBac6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_viewImd6)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel_center.add(jPanel_favoImg, "card3");

        jPanel_favoMuc.setBackground(new java.awt.Color(255, 220, 255));

        jPanel5.setBackground(new java.awt.Color(102, 255, 255));

        jLabel48.setBackground(new java.awt.Color(0, 0, 0));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Favorite Music");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 51));
        jLabel30.setText("Name:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 51, 51));
        jLabel31.setText("Author:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 51));
        jLabel34.setText("Evaluation:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 51));
        jLabel35.setText("Link:");

        jTextField_namesong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_namesong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField_author.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_author.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jComboBox_evaluate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_evaluate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excellent", "Pretty good", "Normal" }));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 51));
        jLabel38.setText("Comment:");

        jTextField_link.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_link.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextArea_comment.setColumns(20);
        jTextArea_comment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextArea_comment.setRows(5);
        jScrollPane1.setViewportView(jTextArea_comment);

        jButton_addMusic.setBackground(new java.awt.Color(153, 153, 255));
        jButton_addMusic.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_addMusic.setForeground(new java.awt.Color(51, 0, 51));
        jButton_addMusic.setText("Add");
        jButton_addMusic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_addMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addMusicActionPerformed(evt);
            }
        });

        jButton_deleteMusic.setBackground(new java.awt.Color(153, 153, 255));
        jButton_deleteMusic.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_deleteMusic.setForeground(new java.awt.Color(51, 0, 51));
        jButton_deleteMusic.setText("Delete");
        jButton_deleteMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteMusicActionPerformed(evt);
            }
        });

        jButton_updateMusic.setBackground(new java.awt.Color(153, 153, 255));
        jButton_updateMusic.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_updateMusic.setForeground(new java.awt.Color(51, 0, 51));
        jButton_updateMusic.setText("Update");
        jButton_updateMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateMusicActionPerformed(evt);
            }
        });

        jTable_viewfavamusic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Author", "Evaluate", "Comment", "Link"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_viewfavamusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_viewfavamusicMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_viewfavamusic);

        javax.swing.GroupLayout jPanel_favoMucLayout = new javax.swing.GroupLayout(jPanel_favoMuc);
        jPanel_favoMuc.setLayout(jPanel_favoMucLayout);
        jPanel_favoMucLayout.setHorizontalGroup(
            jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_favoMucLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                        .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_namesong, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_author, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_link, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_evaluate, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jButton_addMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(jButton_deleteMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_updateMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)))
                .addGap(26, 26, 26))
            .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_favoMucLayout.setVerticalGroup(
            jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_namesong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_evaluate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_favoMucLayout.createSequentialGroup()
                        .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_author, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_link, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel_favoMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_addMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_deleteMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_updateMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel_center.add(jPanel_favoMuc, "card4");

        jPanel_pdiary.setBackground(new java.awt.Color(255, 204, 255));

        jPanel6.setBackground(new java.awt.Color(102, 255, 255));

        jLabel49.setBackground(new java.awt.Color(0, 0, 0));
        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Diary");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/time.png"))); // NOI18N
        jLabel39.setText("Date:");

        jDateChooser_date.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 255)));
        jDateChooser_date.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser_date.setDateFormatString("dd-MM-yyyy");
        jDateChooser_date.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/writing.png"))); // NOI18N
        jLabel40.setText("Write:");

        jTextArea_write.setColumns(20);
        jTextArea_write.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea_write.setRows(5);
        jScrollPane5.setViewportView(jTextArea_write);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("where you write the things you experienced during the day ");

        jButton_viewWrite.setBackground(new java.awt.Color(153, 153, 255));
        jButton_viewWrite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_viewWrite.setForeground(new java.awt.Color(51, 0, 51));
        jButton_viewWrite.setText("View");
        jButton_viewWrite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_viewWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewWriteActionPerformed(evt);
            }
        });

        jButton_UpadteDiary.setBackground(new java.awt.Color(255, 153, 153));
        jButton_UpadteDiary.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_UpadteDiary.setForeground(new java.awt.Color(51, 0, 51));
        jButton_UpadteDiary.setText("Update");
        jButton_UpadteDiary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_UpadteDiary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpadteDiaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_pdiaryLayout = new javax.swing.GroupLayout(jPanel_pdiary);
        jPanel_pdiary.setLayout(jPanel_pdiaryLayout);
        jPanel_pdiaryLayout.setHorizontalGroup(
            jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_pdiaryLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_UpadteDiary, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                .addGroup(jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser_date, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jButton_viewWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel_pdiaryLayout.setVerticalGroup(
            jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_pdiaryLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel41)
                .addGap(32, 32, 32)
                .addGroup(jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_viewWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser_date, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel_pdiaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton_UpadteDiary, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel_center.add(jPanel_pdiary, "card5");

        javax.swing.GroupLayout jPanel_p5Layout = new javax.swing.GroupLayout(jPanel_p5);
        jPanel_p5.setLayout(jPanel_p5Layout);
        jPanel_p5Layout.setHorizontalGroup(
            jPanel_p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel_p5Layout.setVerticalGroup(
            jPanel_p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jPanel_center.add(jPanel_p5, "card6");

        jPanel_viewSche.setBackground(new java.awt.Color(255, 204, 255));

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        jLabel46.setBackground(new java.awt.Color(0, 0, 0));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("View Schedule");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 51));
        jLabel17.setText("Work name:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 51));
        jLabel18.setText("End day:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 51));
        jLabel19.setText("Start day:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 51));
        jLabel26.setText("Work progress:");

        jComboBox_workprocess.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Incomplete", "Complete" }));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 51, 51));
        jLabel29.setText("Describe:");

        jTextArea_describle.setColumns(20);
        jTextArea_describle.setRows(5);
        jScrollPane2.setViewportView(jTextArea_describle);

        jDateChooser_endday.setDateFormatString("dd-MM-yyyy");

        jDateChooser_startday.setDateFormatString("dd-MM-yyyy");

        jButton_add.setBackground(new java.awt.Color(153, 153, 255));
        jButton_add.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(51, 0, 51));
        jButton_add.setText("Add");
        jButton_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(153, 153, 255));
        jButton_delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(51, 0, 51));
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_update.setBackground(new java.awt.Color(153, 153, 255));
        jButton_update.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(51, 0, 51));
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jTable_viewSchdule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Work name", "Start day", "End day", "Work progress", "Describe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_viewSchdule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_viewSchduleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_viewSchdule);

        javax.swing.GroupLayout jPanel_viewScheLayout = new javax.swing.GroupLayout(jPanel_viewSche);
        jPanel_viewSche.setLayout(jPanel_viewScheLayout);
        jPanel_viewScheLayout.setHorizontalGroup(
            jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(8, 8, 8))
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_workname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser_endday, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_workprocess, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_viewScheLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                    .addGap(130, 130, 130)
                    .addComponent(jDateChooser_startday, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(426, Short.MAX_VALUE)))
        );
        jPanel_viewScheLayout.setVerticalGroup(
            jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_workprocess, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_workname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_endday, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel_viewScheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_viewScheLayout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(jDateChooser_startday, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(374, Short.MAX_VALUE)))
        );

        jPanel_center.add(jPanel_viewSche, "card7");

        jPanel_viewCalen.setBackground(new java.awt.Color(255, 204, 255));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI Emoji", 1, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Calender");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Here is the app for you to view: date, month, year, in the past, present and future ");

        javax.swing.GroupLayout jPanel_viewCalenLayout = new javax.swing.GroupLayout(jPanel_viewCalen);
        jPanel_viewCalen.setLayout(jPanel_viewCalenLayout);
        jPanel_viewCalenLayout.setHorizontalGroup(
            jPanel_viewCalenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_viewCalenLayout.createSequentialGroup()
                .addGroup(jPanel_viewCalenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_viewCalenLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_viewCalenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_viewCalenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_viewCalenLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel28)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel_viewCalenLayout.setVerticalGroup(
            jPanel_viewCalenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_viewCalenLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel_center.add(jPanel_viewCalen, "card3");

        jPanel_schedule_and_calender.setBackground(new java.awt.Color(255, 220, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Welcome to System");

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Schedule Section");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

        jPanel_viewSchedule.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 3, new java.awt.Color(104, 104, 104)));
        jPanel_viewSchedule.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_viewSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_viewScheduleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel_viewScheduleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel_viewScheduleMouseExited(evt);
            }
        });
        jPanel_viewSchedule.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/scheduleadd.png"))); // NOI18N
        jPanel_viewSchedule.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 70));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("View Schedule");
        jPanel_viewSchedule.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jPanel_viewCalender.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 3, new java.awt.Color(104, 104, 104)));
        jPanel_viewCalender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_viewCalender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_viewCalenderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel_viewCalenderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel_viewCalenderMouseExited(evt);
            }
        });
        jPanel_viewCalender.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/fathers-day.png"))); // NOI18N
        jPanel_viewCalender.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 70));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("View Calender");
        jPanel_viewCalender.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Calender Section");

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N

        jLabel_doc2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_doc2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_doc2.setText("See the calendar to plan for the next day");

        jLabel_doc1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_doc1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_doc1.setText("Help manage tasks, view schedule yourself");

        javax.swing.GroupLayout jPanel_schedule_and_calenderLayout = new javax.swing.GroupLayout(jPanel_schedule_and_calender);
        jPanel_schedule_and_calender.setLayout(jPanel_schedule_and_calenderLayout);
        jPanel_schedule_and_calenderLayout.setHorizontalGroup(
            jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                        .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_viewSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                        .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37))
                            .addComponent(jPanel_viewCalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jLabel_doc2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
            .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_schedule_and_calenderLayout.createSequentialGroup()
                    .addContainerGap(266, Short.MAX_VALUE)
                    .addComponent(jLabel_doc1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(77, 77, 77)))
        );
        jPanel_schedule_and_calenderLayout.setVerticalGroup(
            jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel_viewSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_viewCalender, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_doc2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
            .addGroup(jPanel_schedule_and_calenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_schedule_and_calenderLayout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(jLabel_doc1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(282, Short.MAX_VALUE)))
        );

        jPanel_center.add(jPanel_schedule_and_calender, "card2");

        mainPanel.add(jPanel_center, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 740, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_scheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_scheduleMouseClicked
        // TODO add your handling code here:
        jPanel_schedule.setBackground(new Color(204,153,0));
        jPanel_favariteImage.setBackground(new Color(255,255,255));
        jPanel_favoriteMusic.setBackground(new Color(255,255,255));
        jPanel_diary.setBackground(new Color(255,255,255));
        jPanel_viewProfile.setBackground(new Color(0,153,204));
        
        
        jPanel_schedule_and_calender.setVisible(true);
        jPanel_viewCalen.setVisible(false);
        jPanel_viewSche.setVisible(false);
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        
        jLabel_doc1.setVisible(false);
        jLabel_doc2.setVisible(false);
    }//GEN-LAST:event_jPanel_scheduleMouseClicked

    private void jPanel_favariteImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_favariteImageMouseClicked
        // TODO add your handling code here:
        jPanel_schedule.setBackground(new Color(255,255,255));
        jPanel_favariteImage.setBackground(new Color(204,153,0));
        jPanel_favoriteMusic.setBackground(new Color(255,255,255));
        jPanel_diary.setBackground(new Color(255,255,255));
        jPanel_viewProfile.setBackground(new Color(0,153,204));
        
        
        jPanel_schedule_and_calender.setVisible(false);
        jPanel_viewCalen.setVisible(false);
        jPanel_viewSche.setVisible(false);
        jPanel_favoImg.setVisible(true);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        
        
            
            
        
    }//GEN-LAST:event_jPanel_favariteImageMouseClicked

    private void jPanel_favoriteMusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_favoriteMusicMouseClicked
        // TODO add your handling code here:
        jPanel_schedule.setBackground(new Color(255,255,255));
        jPanel_favariteImage.setBackground(new Color(255,255,255));
        jPanel_favoriteMusic.setBackground(new Color(204,153,0));
        jPanel_diary.setBackground(new Color(255,255,255));
        jPanel_viewProfile.setBackground(new Color(0,153,204));
        
        
        jPanel_schedule_and_calender.setVisible(false);
        //jPanel_schedule.setVisible(false);
        jPanel_viewCalen.setVisible(false);
        jPanel_viewSche.setVisible(false);
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(true);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        
        resetMusic();
        show_music();
    }//GEN-LAST:event_jPanel_favoriteMusicMouseClicked

    private void jPanel_diaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_diaryMouseClicked
        // TODO add your handling code here:
        jPanel_schedule.setBackground(new Color(255,255,255));
        jPanel_favariteImage.setBackground(new Color(255,255,255));
        jPanel_favoriteMusic.setBackground(new Color(255,255,255));
        jPanel_diary.setBackground(new Color(204,153,0));
        jPanel_viewProfile.setBackground(new Color(0,153,204));
       
        
        jPanel_schedule_and_calender.setVisible(false);
        jPanel_viewCalen.setVisible(false);
        jPanel_viewSche.setVisible(false);
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(true);
        jPanel_p5.setVisible(false);
        
        jTextArea_write.setLineWrap(true);
        resetDiary();
        show_diary();
        
          
    }//GEN-LAST:event_jPanel_diaryMouseClicked

    private void jLabel_bnt6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bnt6MouseClicked
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you want to log out?", "Confill", JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION){
            Login_Form lf = new Login_Form();
            lf.setVisible(true);
            lf.pack();
            lf.setLocationRelativeTo(null);
            this.dispose();
        }
        
    }//GEN-LAST:event_jLabel_bnt6MouseClicked

    private void jPanel_viewProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewProfileMouseClicked
        // TODO add your handling code here:
        
        jPanel_viewProfile.setBackground(new Color(0,153,204));
        jPanel_schedule.setBackground(Color.white);
        jPanel_favariteImage.setBackground(Color.white);
        jPanel_favoriteMusic.setBackground(Color.white);
        jPanel_diary.setBackground(Color.white);
        
        
        Profile_Form pf;
        
            if(user_name == null)
            {
                pf = new Profile_Form();
                pf.setVisible(true);
                System.out.println(fsaying);
            }
            else{
        
                try {
                    pf = new Profile_Form(user_name, path, full_name, id, date, phone, fsaying);
                    pf.setVisible(true);
                    pf.pack();
                    pf.setLocationRelativeTo(this);
                    this.dispose();
                } catch (IOException ex) {
                        Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        
        
        
    }//GEN-LAST:event_jPanel_viewProfileMouseClicked

    private void jPanel_viewScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewScheduleMouseClicked
        // TODO add your handling code here:
        jPanel_viewSche.setVisible(true);
        jPanel_schedule_and_calender.setVisible(false);
        
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        
        reset();
        show_user();
        
    }//GEN-LAST:event_jPanel_viewScheduleMouseClicked

    private void jPanel_HeaderPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_HeaderPanelMousePressed
        // TODO add your handling code here:
        MouseX=evt.getX();
        MouseY=evt.getY();
    }//GEN-LAST:event_jPanel_HeaderPanelMousePressed

    private void jPanel_HeaderPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_HeaderPanelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - MouseX,y - MouseY);
    }//GEN-LAST:event_jPanel_HeaderPanelMouseDragged

    private void jLabel_cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_cancelMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel_cancelMouseClicked

    private void jPanel_viewProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewProfileMouseEntered
        // TODO add your handling code here:
        jPanel_viewProfile.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jPanel_viewProfileMouseEntered

    private void jPanel_viewProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewProfileMouseExited
        // TODO add your handling code here:
        jPanel_viewProfile.setBackground(new Color(0,153,204));
    }//GEN-LAST:event_jPanel_viewProfileMouseExited

    private void jPanel_viewCalenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewCalenderMouseClicked
        // TODO add your handling code here:
        jPanel_viewCalen.setVisible(true);
        jPanel_schedule_and_calender.setVisible(false);
        jPanel_favoImg.setVisible(false);
        jPanel_favoMuc.setVisible(false);
        jPanel_pdiary.setVisible(false);
        jPanel_p5.setVisible(false);
        
        
    }//GEN-LAST:event_jPanel_viewCalenderMouseClicked

    private void jPanel_viewScheduleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewScheduleMouseEntered
        // TODO add your handling code here:
        jLabel_doc1.setVisible(true);
    }//GEN-LAST:event_jPanel_viewScheduleMouseEntered

    private void jPanel_viewScheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewScheduleMouseExited
        // TODO add your handling code here:
        jLabel_doc1.setVisible(false);
    }//GEN-LAST:event_jPanel_viewScheduleMouseExited

    private void jPanel_viewCalenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewCalenderMouseEntered
        // TODO add your handling code here:
        jLabel_doc2.setVisible(true);
    }//GEN-LAST:event_jPanel_viewCalenderMouseEntered

    private void jPanel_viewCalenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_viewCalenderMouseExited
        // TODO add your handling code here:
        jLabel_doc2.setVisible(false);
    }//GEN-LAST:event_jPanel_viewCalenderMouseExited

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "INSERT INTO `schedule_list`(`work_name`, `start_day`, `end_day`, `work_progress`, `describle`) VALUES (?,?,?,?,?)";
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, jTextField_workname.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser_startday.getDate());
            ps.setString(2, date);
            String dateend = sdf.format(jDateChooser_endday.getDate());
            ps.setString(3, dateend);
            
            
            String progress = jComboBox_workprocess.getSelectedItem().toString();
            ps.setString(4, progress);
            ps.setString(5, jTextArea_describle.getText());
            
            ps.executeUpdate();
            //DefaultTableModel model = (DefaultTableModel)jTable_Display_User.getModel();
            //model.setRowCount(0);
            show_add_user();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jTable_viewSchduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_viewSchduleMouseClicked
        // TODO add your handling code here:
        int i = jTable_viewSchdule.getSelectedRow();
        TableModel model = jTable_viewSchdule.getModel();
        jTextField_workname.setText(model.getValueAt(i, 0).toString());
        jDateChooser_startday.setDate((Date) model.getValueAt(i, 1));
        jDateChooser_endday.setDate((Date) model.getValueAt(i, 2));
        
        
        

        
        String subject = model.getValueAt(i, 3).toString();
        switch(subject){
            case "Incomplete":
                jComboBox_workprocess.setSelectedIndex(0);
                break;
            case "Complete":
                jComboBox_workprocess.setSelectedIndex(1);
                break;
            default:
                break;

        };
        jTextArea_describle.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_jTable_viewSchduleMouseClicked

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // TODO add your handling code here:
        int row;
        row = jTable_viewSchdule.getSelectedRow();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Select object to remove");
        }
        else{
            PreparedStatement ps;
            ResultSet rs;
            String cell = jTable_viewSchdule.getModel().getValueAt(row, 4).toString();
            System.out.println(cell);
            String query = "DELETE FROM `schedule_list` WHERE `describle`= '"+cell+"'";
            
            try {
                ps = My_CNX.getConnection().prepareStatement(query);
                ps.execute();
                JOptionPane.showMessageDialog(this, "delete successfully");
                
                DefaultTableModel model = (DefaultTableModel) jTable_viewSchdule.getModel();
                model.setRowCount(0);
                show_user();
                
            } catch (SQLException ex) {
                Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // TODO add your handling code here:
       String value1 = jTextField_workname.getText();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String value2 = sdf.format(jDateChooser_startday.getDate());
       String value3 = sdf.format(jDateChooser_endday.getDate());
        
        
        
        
        
        String value4 = jComboBox_workprocess.getSelectedItem().toString();
        String value5 = jTextArea_describle.getText();
        int p = JOptionPane.showConfirmDialog(this, "Are you sure to update your work","Update Record", JOptionPane.YES_NO_OPTION);
        if(p == JOptionPane.YES_OPTION){
          
            PreparedStatement ps;
            ResultSet rs;
            
            String query = "UPDATE `schedule_list` SET `work_name`='"+value1+"',`start_day`='"+value2+"',`end_day`='"+value3+"',`work_progress`='"+value4+"',`describle`='"+value5+"' WHERE `describle` = '"+value5+"'";
     
            try {
                ps = My_CNX.getConnection().prepareStatement(query);
                ps.execute();
                
                DefaultTableModel model = (DefaultTableModel) jTable_viewSchdule.getModel();
                model.setRowCount(0);
                
                show_user();
                JOptionPane.showMessageDialog(this, "Upadate successfull");
            } catch (SQLException ex) {
                Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jLabel_image1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image1MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac1.setBackground(new Color(255,255,255,250));
        
        
    }//GEN-LAST:event_jLabel_image1MouseEntered

    private void jLabel_image1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image1MouseExited
        // TODO add your handling code here:
        jPanel_imageBac1.setBackground(new Color(255,204,255));
    }//GEN-LAST:event_jLabel_image1MouseExited

    private void jLabel_image1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image1MouseClicked
        // TODO add your handling code here:
        String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path1 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path1 = null;
                }
                else{
                    int x = jLabel_image1.getSize().width;
                    int y = jLabel_image1.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image1.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
    }//GEN-LAST:event_jLabel_image1MouseClicked

    private void jButton_viewImd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd1ActionPerformed
        // TODO add your handling code here:
        System.out.println(image_path1);
        if(image_path1 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path1);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd1ActionPerformed

    private void jLabel_image2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image2MouseClicked
        // TODO add your handling code here:
        String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path2 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path2 = null;
                }
                else{
                    int x = jLabel_image1.getSize().width;
                    int y = jLabel_image1.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image2.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
    }//GEN-LAST:event_jLabel_image2MouseClicked

    private void jLabel_image2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image2MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac2.setBackground(new Color(255,255,255,250));
    }//GEN-LAST:event_jLabel_image2MouseEntered

    private void jLabel_image2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image2MouseExited
        // TODO add your handling code here:
        jPanel_imageBac2.setBackground(new Color(255,204,255));
    }//GEN-LAST:event_jLabel_image2MouseExited

    private void jButton_viewImd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd2ActionPerformed
        // TODO add your handling code here:
        
        if(image_path2 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path2);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd2ActionPerformed

    private void jButton_viewImd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd3ActionPerformed
        // TODO add your handling code here:
        
        if(image_path3 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path3);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd3ActionPerformed

    private void jLabel_image4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image4MouseClicked
        // TODO add your handling code here:
        String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path4 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path4 = null;
                }
                else{
                    int x = jLabel_image4.getSize().width;
                    int y = jLabel_image4.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image4.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
    }//GEN-LAST:event_jLabel_image4MouseClicked

    private void jLabel_image4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image4MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac4.setBackground(new Color(255,255,255,250));
    }//GEN-LAST:event_jLabel_image4MouseEntered

    private void jLabel_image4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image4MouseExited
        // TODO add your handling code here:
        jPanel_imageBac4.setBackground(new Color(255,204,255));
    }//GEN-LAST:event_jLabel_image4MouseExited

    private void jButton_viewImd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd4ActionPerformed
        // TODO add your handling code here:
        if(image_path4 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path4);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd4ActionPerformed

    private void jLabel_image5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image5MouseClicked
        // TODO add your handling code here:
         String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path5 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path5 = null;
                }
                else{
                    int x = jLabel_image5.getSize().width;
                    int y = jLabel_image5.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image5.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
    }//GEN-LAST:event_jLabel_image5MouseClicked

    private void jLabel_image5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image5MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac5.setBackground(new Color(255,255,255,250));
    }//GEN-LAST:event_jLabel_image5MouseEntered

    private void jLabel_image5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image5MouseExited
        // TODO add your handling code here:
        jPanel_imageBac5.setBackground(new Color(255,204,255));
    }//GEN-LAST:event_jLabel_image5MouseExited

    private void jButton_viewImd5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd5ActionPerformed
        // TODO add your handling code here:
        if(image_path5 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path5);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd5ActionPerformed

    private void jLabel_image6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image6MouseClicked
        // TODO add your handling code here:
        String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path6 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path6 = null;
                }
                else{
                    int x = jLabel_image6.getSize().width;
                    int y = jLabel_image6.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image6.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
    }//GEN-LAST:event_jLabel_image6MouseClicked

    private void jLabel_image6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image6MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac6.setBackground(new Color(255,255,255,250));
    }//GEN-LAST:event_jLabel_image6MouseEntered

    private void jLabel_image6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image6MouseExited
        // TODO add your handling code here:
        jPanel_imageBac6.setBackground(new Color(255,204,255));
    }//GEN-LAST:event_jLabel_image6MouseExited

    private void jButton_viewImd6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewImd6ActionPerformed
        // TODO add your handling code here:
        if(image_path6 == null){
            JOptionPane.showMessageDialog(this, "Please choose image to view");
        }
        else{
            ViewImage vi = new ViewImage(image_path6);
            vi.setVisible(true);
            vi.pack();
            vi.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButton_viewImd6ActionPerformed

    private void jLabel_image3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image3MouseEntered
        // TODO add your handling code here:
        jPanel_imageBac3.setBackground(new Color(255,255,255,250));
    }//GEN-LAST:event_jLabel_image3MouseEntered

    private void jLabel_image3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image3MouseClicked
        // TODO add your handling code here:
        String path = null;
        //tao file lua chon
        JFileChooser chooser = new JFileChooser();
        //thiet lap thu muc lua chon
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        //set phan mo rong file de them vao phan loc
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*Image","jpg","png","jpef");
        chooser.addChoosableFileFilter(extension);
        
        //hient hi hop thoai luu
        int filestate = chooser.showSaveDialog(null);
        
        //kiem tra link anh
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            
            image_path3 = path;
            //set anh cho label va doi kich thuoc
            try {
                BufferedImage image = ImageIO.read(new File(path));
                if(image==null){
                    JOptionPane.showMessageDialog(this, "Error loading image", "Error!", JOptionPane.ERROR_MESSAGE);
                    image_path3 = null;
                }
                else{
                    int x = jLabel_image1.getSize().width;
                    int y = jLabel_image1.getSize().height;
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, image.SCALE_SMOOTH));
                    jLabel_image3.setIcon(icon);
                }   
            } catch (IOException ex) {
                Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
        
    }//GEN-LAST:event_jLabel_image3MouseClicked

    private void jLabel_image3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_image3MouseExited
        // TODO add your handling code here:
        jPanel_imageBac3.setBackground(new Color(255,204,255));
        
    }//GEN-LAST:event_jLabel_image3MouseExited

    private void jButton_addMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addMusicActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "INSERT INTO `music_list`(`name`, `author`, `evaluate`, `comment`, `link`) VALUES (?,?,?,?,?)";
        try {
            ps = My_CNX.getConnection().prepareStatement(query);
            ps.setString(1, jTextField_namesong.getText());
            ps.setString(2, jTextField_author.getText());
            
            
            
            String evaluate = jComboBox_evaluate.getSelectedItem().toString();
            ps.setString(3, evaluate);
            ps.setString(4, jTextArea_comment.getText());
            ps.setString(5, jTextField_link.getText());
            ps.executeUpdate();
            //DefaultTableModel model = (DefaultTableModel)jTable_Display_User.getModel();
            //model.setRowCount(0);
            show_add_music();
                    
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_addMusicActionPerformed

    private void jButton_deleteMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteMusicActionPerformed
        // TODO add your handling code here:
        int row;
        row = jTable_viewfavamusic.getSelectedRow();
        if(row<0){
            JOptionPane.showMessageDialog(this, "Select music to remove");
        }
        else{
            PreparedStatement ps;
            ResultSet rs;
            String cell = jTable_viewfavamusic.getModel().getValueAt(row, 0).toString();
            
            String query = "DELETE FROM `music_list` WHERE `name`= '"+cell+"'";
            
            try {
                ps = My_CNX.getConnection().prepareStatement(query);
                ps.execute();
                JOptionPane.showMessageDialog(this, "delete successfully");
                
                DefaultTableModel model = (DefaultTableModel) jTable_viewfavamusic.getModel();
                model.setRowCount(0);
                show_music();
                
            } catch (SQLException ex) {
                Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_deleteMusicActionPerformed

    private void jButton_updateMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateMusicActionPerformed
        // TODO add your handling code here:
       String value1 = jTextField_namesong.getText();
       
       String value2 = jTextField_author.getText();
       String value3 = jTextField_link.getText();
        
        
        
        
        
        String value4 = jComboBox_evaluate.getSelectedItem().toString();
        String value5 = jTextArea_comment.getText();
        int p = JOptionPane.showConfirmDialog(this, "Are you sure to update your work","Update Record", JOptionPane.YES_NO_OPTION);
        if(p == JOptionPane.YES_OPTION){
          
            PreparedStatement ps;
            ResultSet rs;
            
            String query = "UPDATE `music_list` SET `name`='"+value1+"',`author`='"+value2+"',`evaluate`='"+value3+"',`comment`='"+value4+"',`link`='"+value5+"' WHERE `name` = '"+value5+"'";
                
            try {
                ps = My_CNX.getConnection().prepareStatement(query);
                ps.execute();
                
                DefaultTableModel model = (DefaultTableModel) jTable_viewfavamusic.getModel();
                model.setRowCount(0);
                
                show_music();
                JOptionPane.showMessageDialog(this, "Upadate successfull");
            } catch (SQLException ex) {
                Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_updateMusicActionPerformed

    private void jTable_viewfavamusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_viewfavamusicMouseClicked
        // TODO add your handling code here:
        int i = jTable_viewfavamusic.getSelectedRow();
        TableModel model = jTable_viewfavamusic.getModel();
        jTextField_namesong.setText(model.getValueAt(i, 0).toString());
        jTextField_author.setText(model.getValueAt(i, 1).toString());
        jTextField_link.setText(model.getValueAt(i, 2).toString());
        
        
        



        
        String evaluate = model.getValueAt(i, 3).toString();
        switch(evaluate){
            case "Excellent":
                jComboBox_evaluate.setSelectedIndex(0);
                break;
            case "Pretty good":
                jComboBox_evaluate.setSelectedIndex(1);
                break;
            case "Normal":
                jComboBox_evaluate.setSelectedIndex(3);
                break;
            default:
                break;

        };
        jTextArea_comment.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_jTable_viewfavamusicMouseClicked

    private void jButton_addMusic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addMusic1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_addMusic1ActionPerformed

    private void jButton_viewWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewWriteActionPerformed
        // TODO add your handling code here:
        show_diary();
    }//GEN-LAST:event_jButton_viewWriteActionPerformed

    private void jButton_UpadteDiaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpadteDiaryActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String value1 = sdf.format(jDateChooser_date.getDate());
        String value2 = jTextArea_write.getText();
        int p = JOptionPane.showConfirmDialog(this, "Are you sure to update your work","Update Record", JOptionPane.YES_NO_OPTION);
        
        if(p == JOptionPane.YES_OPTION){
          
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * FROM `diary_list` WHERE `date`=?";
         
            try {
                ps = My_CNX.getConnection().prepareStatement(query);
            
                ps.setString(1, value1);
                rs = ps.executeQuery();
                if(rs.next()){
                    String queryUpadte = "UPDATE `diary_list` SET `date`='"+value1+"',`writediary`='"+value2+"' WHERE `date` = '"+value1+"'";
                    try {
                        ps = My_CNX.getConnection().prepareStatement(queryUpadte);
                        ps.execute();
                        show_diary();
                        JOptionPane.showMessageDialog(this, "Upadate successfull");
                    } catch (SQLException ex) {
                        Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                }
                else{
                    String queryInsert = "INSERT INTO `diary_list`(`date`, `writediary`) VALUES (?,?)";
                    
                    try {
                        ps = My_CNX.getConnection().prepareStatement(queryInsert);
                        
                        ps.setString(1, value1);
                        ps.setString(2, value2);
                        ps.executeUpdate();
                        show_diary();
                        JOptionPane.showMessageDialog(this, "Upadate successfull");
                    
                    } catch (SQLException ex) {
                        Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(Menu_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_UpadteDiaryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton_UpadteDiary;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_addMusic;
    private javax.swing.JButton jButton_addMusic1;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_deleteMusic;
    private javax.swing.JButton jButton_update;
    private javax.swing.JButton jButton_updateMusic;
    private javax.swing.JButton jButton_viewImd1;
    private javax.swing.JButton jButton_viewImd2;
    private javax.swing.JButton jButton_viewImd3;
    private javax.swing.JButton jButton_viewImd4;
    private javax.swing.JButton jButton_viewImd5;
    private javax.swing.JButton jButton_viewImd6;
    private javax.swing.JButton jButton_viewWrite;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox_evaluate;
    private javax.swing.JComboBox<String> jComboBox_workprocess;
    private com.toedter.calendar.JDateChooser jDateChooser_date;
    private com.toedter.calendar.JDateChooser jDateChooser_endday;
    private com.toedter.calendar.JDateChooser jDateChooser_startday;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_IblEmpDesignation;
    private javax.swing.JLabel jLabel_bnt6;
    private javax.swing.JLabel jLabel_cancel;
    private javax.swing.JLabel jLabel_doc1;
    private javax.swing.JLabel jLabel_doc2;
    private javax.swing.JLabel jLabel_iblEmpname;
    private javax.swing.JLabel jLabel_image1;
    private javax.swing.JLabel jLabel_image2;
    private javax.swing.JLabel jLabel_image3;
    private javax.swing.JLabel jLabel_image4;
    private javax.swing.JLabel jLabel_image5;
    private javax.swing.JLabel jLabel_image6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_HeaderPanel;
    private javax.swing.JPanel jPanel_center;
    private javax.swing.JPanel jPanel_diary;
    private javax.swing.JPanel jPanel_favariteImage;
    private javax.swing.JPanel jPanel_favoImg;
    private javax.swing.JPanel jPanel_favoMuc;
    private javax.swing.JPanel jPanel_favoriteMusic;
    private javax.swing.JPanel jPanel_imageBac1;
    private javax.swing.JPanel jPanel_imageBac2;
    private javax.swing.JPanel jPanel_imageBac3;
    private javax.swing.JPanel jPanel_imageBac4;
    private javax.swing.JPanel jPanel_imageBac5;
    private javax.swing.JPanel jPanel_imageBac6;
    private javax.swing.JPanel jPanel_p5;
    private javax.swing.JPanel jPanel_pdiary;
    private javax.swing.JPanel jPanel_schedule;
    private javax.swing.JPanel jPanel_schedule_and_calender;
    private javax.swing.JPanel jPanel_side;
    private javax.swing.JPanel jPanel_viewCalen;
    private javax.swing.JPanel jPanel_viewCalender;
    private javax.swing.JPanel jPanel_viewProfile;
    private javax.swing.JPanel jPanel_viewSche;
    private javax.swing.JPanel jPanel_viewSchedule;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable_viewSchdule;
    private javax.swing.JTable jTable_viewfavamusic;
    private javax.swing.JTextArea jTextArea_comment;
    private javax.swing.JTextArea jTextArea_describle;
    private javax.swing.JTextArea jTextArea_write;
    private javax.swing.JTextField jTextField_author;
    private javax.swing.JTextField jTextField_link;
    private javax.swing.JTextField jTextField_namesong;
    private javax.swing.JTextField jTextField_workname;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
