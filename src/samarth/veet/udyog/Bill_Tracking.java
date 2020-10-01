/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samarth.veet.udyog;

import java.awt.Color;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static samarth.veet.udyog.Manufacturing.jTextField4_sum;

/**
 *
 * @author jatte
 */
public class Bill_Tracking extends javax.swing.JFrame {
    Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;

    /**
     * Creates new form Bill_Tracking
     */
    public Bill_Tracking() throws ClassNotFoundException {
        initComponents();
        Connect();
        Createcolum();
        loadData();
        total_nos();
        sumamt();
        sumamtwt();
        cgst();
        sgst();
        sort();
        
        
        
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
     public void loadData()
     {
         try
         {
          String sql = "select * from billtracking";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getString("id"),rs.getString("partyname"),rs.getString("Date"),rs.getString("startdate"),rs.getString("enddate"),rs.getInt("totalnos"),rs.getString("totalamt"),rs.getString("totalamtwtax"),rs.getString("prb"),rs.getString("sgst"),rs.getString("cgst"),rs.getString("type")});
                 
             }
             total_nos();
        sumamt();
        sumamtwt();
        cgst();
        sgst();
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, "Hi I am There");
             
         }
     }
        public void Createcolum()
    {   dm = (DefaultTableModel) jTable1.getModel();
   
        dm.addColumn("id");
        dm.addColumn("Party Name");
        dm.addColumn("Billing date");
        dm.addColumn("Start Date");
        dm.addColumn("End Date");
        dm.addColumn("Total NOS");
        dm.addColumn("Amt");
        dm.addColumn("TAWT ");
        dm.addColumn("PRB");
        dm.addColumn("SGST");
        dm.addColumn("CGST");
        dm.addColumn("Type");
        jTable1.getTableHeader().setFont(new Font("segoe UI",Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.yellow);
        jTable1.getTableHeader().setForeground(new Color(32,136, 0xcb));
        jTable1.setRowHeight(25);
        
    }
           public void filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<>(dm);
        jTable1.setRowSorter(tr);
         tr.setRowFilter(RowFilter.regexFilter(query));
         total_nos();
        sumamt();
        sumamtwt();
        cgst();
        sgst();
         

    }
           public void total_nos()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 5).toString());
        }
        jLabel1_nos.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
           public void sumamt()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 6).toString());
        }
        jLabel6_amt.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
      
           public void sumamtwt()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 7).toString());
        }
        jLabel11_tawt.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
           public void cgst()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            double cgst = Double.parseDouble((String) jTable1.getValueAt(i, 10));
            sum =(int) ((double) sum + cgst);
        }
        jLabel21_cgst.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
           public void sgst()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            double sgst = Double.parseDouble((String) jTable1.getValueAt(i, 9));
            sum = (int) (sum + sgst);
        }
        jLabel17_sgst.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
            public void loadDate()
     {
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String d = sdf.format(jDateChooser1.getDate());
             String e = sdf.format(jDateChooser2.getDate());
             
          String sql = "select * from billtracking where date between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                 model.addRow(new Object[] {rs.getString("id"),rs.getString("partyname"),rs.getString("Date"),rs.getString("startdate"),rs.getString("enddate"),rs.getInt("totalnos"),rs.getString("totalamt"),rs.getString("totalamtwtax"),rs.getString("prb"),rs.getString("sgst"),rs.getString("cgst"),rs.getString("type")});
                 
             }
             total_nos();
        sumamt();
        sumamtwt();
        cgst();
        sgst();
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }
            public void sort()
            {
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);
                jTable1.setRowSorter(sorter);
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
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        button2 = new java.awt.Button();
        jButton1 = new javax.swing.JButton();
        button3 = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1_nos = new javax.swing.JLabel();
        jLabel6_amt = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11_tawt = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17_sgst = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21_cgst = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(71, 120, 197));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Fourinch Veet Bill", "Sixinch Veet Bill", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Select Type");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

        jDateChooser1.setDateFormatString("yyyy-MM-d");
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser1MousePressed(evt);
            }
        });
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 120, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Start Date");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("End Date");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 80, 32));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 105, 32));

        button2.setBackground(new java.awt.Color(71, 120, 197));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Delete");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel4.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 80, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 5)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, -1, 30));

        button3.setBackground(new java.awt.Color(71, 120, 197));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("Select");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel4.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 70, 30));

        jLabel1.setText("jLabel1");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 70, 30));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 80));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1370, 540));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total NOS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 30));

        jLabel1_nos.setText("jLabel1");
        jPanel2.add(jLabel1_nos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 70, 30));

        jLabel6_amt.setText("jLabel1");
        jPanel2.add(jLabel6_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("AMT");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 90, 30));

        jLabel11_tawt.setText("jLabel1");
        jPanel2.add(jLabel11_tawt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 70, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TAWT");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 90, 30));

        jLabel17_sgst.setText("jLabel1");
        jPanel2.add(jLabel17_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 70, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SGST");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 90, 30));

        jLabel21_cgst.setText("jLabel1");
        jPanel2.add(jLabel21_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 20, 70, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CGST");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, 90, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 1420, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String selection = jTable1.getModel().getValueAt(row, 0).toString();
        jLabel1.setText(selection);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String query = jComboBox1.getSelectedItem().toString();
        filter(query);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jDateChooser1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1MousePressed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String id = jLabel1.getText();
        try
        {
            String sql = "delete from billtracking where id="+id+"";
            stmt.executeUpdate(sql);
            loadData();
            JOptionPane.showMessageDialog(this, "Record Deleted");
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Enter The ID ");
        }
        
        
        
    }//GEN-LAST:event_button2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fourinchbill obj = null;
        try {
            try {
                obj = new fourinchbill();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        try {
              loadDate();
        
        
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Select Date");
        }
    }//GEN-LAST:event_button3ActionPerformed

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
            java.util.logging.Logger.getLogger(Bill_Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill_Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill_Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill_Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Bill_Tracking().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Bill_Tracking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button2;
    private java.awt.Button button3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11_tawt;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17_sgst;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel1_nos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21_cgst;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6_amt;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
