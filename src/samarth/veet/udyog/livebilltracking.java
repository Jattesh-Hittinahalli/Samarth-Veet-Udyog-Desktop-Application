/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samarth.veet.udyog;
import java.awt.Color;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static samarth.veet.udyog.Manufacturing.jTextField4_sum;
/**
 *
 * @author jatte
 */
public class livebilltracking extends javax.swing.JFrame {
    Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;

    /**
     * Creates new form livebilltracking
     */
    public livebilltracking() throws ClassNotFoundException {
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
          String sql = "select * from livebilltracking";
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
             
          String sql = "select * from livebilltracking where date between '"+d+"' and '"+e+"'";
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
        button1 = new java.awt.Button();
        label1 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        button4 = new java.awt.Button();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, 30));

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
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 120, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Start Date");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("End Date");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 80, 32));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 105, 32));

        button2.setBackground(new java.awt.Color(71, 120, 197));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Delete");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel4.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 80, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 5)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, -1, 30));

        button3.setBackground(new java.awt.Color(71, 120, 197));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("Select");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel4.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 70, 30));

        jLabel1.setText("jLabel1");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 50, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 50));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 710, 470));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total NOS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jLabel1_nos.setText("jLabel1");
        jPanel2.add(jLabel1_nos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 30));

        jLabel6_amt.setText("jLabel1");
        jPanel2.add(jLabel6_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("AMT");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 90, 30));

        jLabel11_tawt.setText("jLabel1");
        jPanel2.add(jLabel11_tawt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 70, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TAWT");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 90, 30));

        jLabel17_sgst.setText("jLabel1");
        jPanel2.add(jLabel17_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 70, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SGST");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 90, 30));

        jLabel21_cgst.setText("jLabel1");
        jPanel2.add(jLabel21_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 70, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CGST");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 90, 30));

        button1.setBackground(new java.awt.Color(51, 51, 255));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Update amount");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 140, 30));

        label1.setText("label1");
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 70, 90, 30));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 140, 30));

        button4.setBackground(new java.awt.Color(51, 51, 255));
        button4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setLabel("search amount");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel2.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 140, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 910, 130));

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField10.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        jTextField11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            String sql = "delete from livebilltracking where id="+id+"";
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String selection = jTable1.getModel().getValueAt(row, 0).toString();
        jLabel1.setText(selection);
        label1.setText(selection);
        String selectio = jTable1.getModel().getValueAt(row, 1).toString();
        jTextField2.setText(selectio);
        String selecti = jTable1.getModel().getValueAt(row, 2).toString();
        jTextField3.setText(selecti);
        String select = jTable1.getModel().getValueAt(row, 3).toString();
        jTextField4.setText(select);
        String selec = jTable1.getModel().getValueAt(row, 4).toString();
        jTextField5.setText(selec);
        String sele = jTable1.getModel().getValueAt(row, 5).toString();
        jTextField6.setText(sele);
        String sel = jTable1.getModel().getValueAt(row, 6).toString();
        jTextField7.setText(sel);
        String se = jTable1.getModel().getValueAt(row, 8).toString();
        jTextField8.setText(se);
        String s = jTable1.getModel().getValueAt(row, 9).toString();
        jTextField9.setText(s);
        String g = jTable1.getModel().getValueAt(row, 10).toString();
        jTextField10.setText(g);
        String h = jTable1.getModel().getValueAt(row, 11).toString();
        jTextField11.setText(h);
       
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        String id =label1.getText();
        String update= jTextField1.getText();
        String updat= jTextField2.getText();
        String upda= jTextField3.getText();
        String upd= jTextField4.getText();
        String up= jTextField5.getText();
        String u= jTextField6.getText();
        String aupdate= jTextField7.getText();
        String bupdate= jTextField8.getText();
        String cupdate= jTextField9.getText();
        String dupdate= jTextField10.getText();
        String eupdate= jTextField11.getText();
        
        
    
    }//GEN-LAST:event_button1ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        String id = label1.getText();
        try {
            String sql = "select totalamtwtax from livebilltracking where id ="+id+"";
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                jTextField1.setText(rs.getString("totalamtwtax")); 
            }
            
        } catch (Exception e) {
        }
            
    }//GEN-LAST:event_button4ActionPerformed

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
            java.util.logging.Logger.getLogger(livebilltracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(livebilltracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(livebilltracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(livebilltracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new livebilltracking().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(livebilltracking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
