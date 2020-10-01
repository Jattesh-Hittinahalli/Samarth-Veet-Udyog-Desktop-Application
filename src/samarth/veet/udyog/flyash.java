/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samarth.veet.udyog;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jatte
 */
public class flyash extends javax.swing.JFrame {
      Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;

    /**
     * Creates new form flyash
     */
    public flyash() throws ClassNotFoundException {
        initComponents();
         Connect();
        Createcolum();
        loadData();
         
        no_brass();
        amount();
        sort();
    }
      private void sort()
    {
        TableRowSorter<DefaultTableModel> acending = new TableRowSorter<>(dm);
        jTable1.setRowSorter(acending);
        
    }
    public void Connect() throws ClassNotFoundException
    {
       try
        {   Class.forName("org.sqlite.JDBC");
           con = DriverManager.getConnection("jdbc:sqlite:prathu.db");
            stmt = con.createStatement();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void loadData()
     {
         try
         {
          String sql = "select * from flyash";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getInt("id"),rs.getString("date"),rs.getFloat("ton"),rs.getString("rpb"),rs.getInt("amount")});
                 
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e);
             
         }
     }
    
      public void Createcolum()
    {   dm = (DefaultTableModel) jTable1.getModel();
   
        
        dm.addColumn("id");
        dm.addColumn("Date");
        dm.addColumn("TON");
        dm.addColumn("RPB");
        
        dm.addColumn("Amount");
        jTable1.getTableHeader().setFont(new Font("segoe UI",Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.yellow);
        jTable1.getTableHeader().setForeground(new Color(32,136, 0xcb));
        jTable1.setRowHeight(25);
        
    }
       public void loadDate()
     {
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String d = sdf.format(jDateChooser3.getDate());
             String e = sdf.format(jDateChooser4.getDate());
             
          String sql = "select * from flyash where date between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                  model.addRow(new Object[] {rs.getInt("id"),rs.getString("date"),rs.getFloat("ton"),rs.getString("rpb"),rs.getInt("amount")});
                 no_brass();
        amount();
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }
        
         public void filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<>(dm);
        jTable1.setRowSorter(tr);
         tr.setRowFilter(RowFilter.regexFilter(query));

    }
          public void amount()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 4).toString());
        }
        jLabel11_amt.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
           public void no_brass()
      {
          try
          {
          float sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {  //float ton = (float) Double.parseDouble(jTextField4.getText());
            sum =  (float) (sum + Double.parseDouble(jTable1.getValueAt( i, 2).toString()));
        }
        jLabel11_amt1.setText(sum+"");
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }

    /**
     *
     * @throws Exception
     */
    public void exportable() throws IOException{
               TableModel model = jTable1.getModel();
               String response = JOptionPane.showInputDialog(".............Write A File Name...............");
                String filename= response;
               
               FileWriter out = new FileWriter("D:\\SoftwareExcel\\"+filename+".xls");
              for (int i = 0; i < model.getColumnCount(); i++) {
                  out.write(model.getColumnName(i)+"\t");
                  
              }
              out.write("\n");
              for (int a = 0; a < model.getRowCount(); a++){
                  for (int j = 0; j < model.getColumnCount(); j++){
                     
                      
                      
                      out.write(model.getValueAt(a, j)+"\t"); 
           
                    
                      
                  }
                  out.write("\n");
                  
                   
                  
                  
              }
                    
                 out.close();
                 
                 JOptionPane.showMessageDialog(this, "File has been Saved");
                
                          
                        
           }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        button3 = new java.awt.Button();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_search = new javax.swing.JTextField();
        button1 = new java.awt.Button();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        button2 = new java.awt.Button();
        jLabel16 = new javax.swing.JLabel();
        jLabel11_amt = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11_amt1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(71, 120, 197));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SAMARTH VEET UDYOG");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Start Date");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 30));

        jDateChooser3.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("End Date");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 90, 30));

        jDateChooser4.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 120, 30));

        button3.setBackground(new java.awt.Color(71, 120, 197));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("Select");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel4.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 70, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 5)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, 30));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 50));

        jPanel2.setBackground(new java.awt.Color(84, 127, 206));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(120, 168, 252));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Flyash");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-18 (1).png"))); // NOI18N

        jTextField_search.setBackground(new java.awt.Color(71, 120, 197));
        jTextField_search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchActionPerformed(evt);
            }
        });
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_search)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        button1.setBackground(new java.awt.Color(84, 127, 206));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Add Detail");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 120, 40));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 180, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 80, 30));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 180, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("RPT");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 90, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 180, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ton");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 90, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Amount");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 90, -1));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 180, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 310, 390));

        jPanel6.setBackground(new java.awt.Color(71, 120, 197));

        button2.setBackground(new java.awt.Color(71, 120, 197));
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Delete ");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Amount");

        jLabel11_amt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11_amt.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11_amt.setText("jLabel1");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total Ton");

        jLabel11_amt1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11_amt1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11_amt1.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 310, 130));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable1.setShowHorizontalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 680, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        try {
            loadDate();
            //fourinchsum();
            //sixinchsum();
            jDateChooser4.setDate(null);
            jDateChooser3.setDate(null);

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Select Date");
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        // TODO add your handling code here:
        String query = jTextField_search.getText().toLowerCase();
        filter(query);
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(jDateChooser2.getDate());
      
        
         float ton = (float) Double.parseDouble(jTextField4.getText());
        int rpb = Integer.parseInt(jTextField2.getText());
        int amt = (int) (ton*rpb);
        jLabel1.setText(amt+"");

        try
        {
            String sql ="insert into flyash(date,ton,rpb,amount)"
            + "values('"+d+"','"+ton+"','"+rpb+"','"+amt+"')";
            stmt.executeUpdate(sql);
            loadData();
            jDateChooser2.setDate(null);
            jTextField2.setText(null);
            jTextField3.setText(null);
            jTextField4.setText(null);
            no_brass();
            amount();

        }
        catch( SQLException ex)
        {
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String id = jTextField3.getText();
        try
        {
            String sql = "delete from flyash where id="+id+"";
            stmt.executeUpdate(sql);
            loadData();

            JOptionPane.showMessageDialog(this, "Record Deleted");
            no_brass();
            amount();

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Enter The ID ");
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        String selection = jTable1.getModel().getValueAt(row, 0).toString();
        jTextField3.setText(selection);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        raw obj = null;
        obj = new raw();
        obj.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code h
        
        
       
          
              
              
        try {
            exportable();
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
        
        
                
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(flyash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(flyash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(flyash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(flyash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new flyash().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(flyash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11_amt;
    private javax.swing.JLabel jLabel11_amt1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables

    
}
