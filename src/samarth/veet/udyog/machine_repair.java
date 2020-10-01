/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samarth.veet.udyog;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author jatte
 */
public class machine_repair extends javax.swing.JFrame {
     Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;

    /**
     * Creates new form machine_repair
     */
    public machine_repair() throws ClassNotFoundException {
        initComponents();
        Connect();
        Createcolum();
        loadData();
        
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
          String sql = "select * from machinerepair";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getInt("id"),rs.getString("date"),rs.getInt("Amount"),rs.getString("Resion"),rs.getString("Type")});
                 
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
        dm.addColumn("Amount");
        dm.addColumn("Resion");
        dm.addColumn("Type");
       
       
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
             
          String sql = "select * from machinerepair where date between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                model.addRow(new Object[] {rs.getInt("id"),rs.getString("date"),rs.getInt("Amount"),rs.getString("Resion"),rs.getString("Type")});
                 
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
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 2).toString());
        }
        jLabel11_amt.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
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
        jLabel13 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        button3 = new java.awt.Button();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
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
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        button2 = new java.awt.Button();
        jLabel16 = new javax.swing.JLabel();
        jLabel11_amt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(71, 120, 197));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Machine", "Extra" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 110, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Select Type");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 5)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, -1, 30));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 50));

        jPanel2.setBackground(new java.awt.Color(84, 127, 206));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(120, 168, 252));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Machine & Extra Cost");

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
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField_search))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        button1.setBackground(new java.awt.Color(84, 127, 206));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Add Detail");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 120, 40));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 180, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 80, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Resion");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 90, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 180, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Amount");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 90, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Machine", "Extra" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 180, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Select Type");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 420, 390));

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
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 150, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11_amt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 420, 130));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 570, 520));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        String query = jComboBox4.getSelectedItem().toString();
        filter(query);
      
        amount();
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        raw obj = null;
        obj = new raw();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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

        String type = jComboBox3.getSelectedItem().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(jDateChooser2.getDate());
        int amount = Integer.parseInt(jTextField4.getText());
        String resion = jTextArea1.getText();
       

        try
        {
            String sql ="insert into machinerepair(date,amount,Resion,type)"
            + "values('"+d+"','"+amount+"','"+resion+"','"+type+"')";
            stmt.executeUpdate(sql);
            loadData();
            jDateChooser2.setDate(null);
            
            jTextField3.setText(null);
            jTextField4.setText(null);
            jTextArea1.setText(null);
            
            amount();

        }
        catch( SQLException ex)
        {
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String id = jTextField3.getText();
        try
        {
            String sql = "delete from machinerepair where id="+id+"";
            stmt.executeUpdate(sql);
            loadData();

            JOptionPane.showMessageDialog(this, "Record Deleted");
            
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
            java.util.logging.Logger.getLogger(machine_repair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(machine_repair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(machine_repair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(machine_repair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new machine_repair().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(machine_repair.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel11_amt;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables
}
