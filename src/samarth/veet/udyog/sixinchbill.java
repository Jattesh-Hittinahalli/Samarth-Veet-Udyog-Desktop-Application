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
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jatte
 */
public class sixinchbill extends javax.swing.JFrame {
    Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;
PreparedStatement pst;

    /**
     * Creates new form sixinchbill
     */
    public sixinchbill() throws ClassNotFoundException {
        initComponents();
        this.setResizable(false);
        Connect();
        Createcolum();
        loadData();
        party();
        
        sort();
        sixinchsum();
        
    }
    public void print() 
    {     float ppb =  (float) Double.parseDouble(jTextField3.getText());
       
        //Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String D = sdf.format(jDateChooser_BD.getDate());
        
        String font = "\t                   SAMARTH VEET UDYOG\n";
        jaria_print.setText(jaria_print.getText()+"\t                   SAMARTH VEET UDYOG");
        jaria_print.setText(jaria_print.getText()+"\n\t Chandak mala,Near S.R.P.Camp,Vijapur Rd Solapur,\t");
        jaria_print.setText(jaria_print.getText()+"\n\t\tCell:9011628663\t");
        jaria_print.setText(jaria_print.getText()+"\t              \n");
         jaria_print.setText(jaria_print.getText()+"To,                                              \t                             Date"+" - "+D+"\t\t");
        jaria_print.setText(jaria_print.getText()+"\n"+jTextArea1.getText()+"\t");
        jaria_print.setText(jaria_print.getText()+"\n");
        jaria_print.setText(jaria_print.getText()+"\n");
        
       jaria_print.setText(jaria_print.getText()+"\n=============================================================================================================================\n");
        jaria_print.setText(jaria_print.getText()+"          Date"+"                                   NOS"+"            Challan NO"+"\t"+"          Vehicle No"+"\t"); 
        loadforprint();
         jaria_print.setText(jaria_print.getText()+"\n"+"=============================================================================================================================\n");
        jaria_print.setText(jaria_print.getText()+"\n"+"Total 6 Inch Bricks"+" - "+jsum.getText()+"\nPrice per bricks - "+ppb+"\n"+"Total Amount"+" - "+jam.getText()+"\n"+"SGST(2.5%)"+" - "+jTextField4_sgst.getText()+"\n"+"CGST(2.5%)"+" - "+jTextField2_cgst.getText()+"\n");
        jaria_print.setText(jaria_print.getText()+"Total Bill"+" - "+jtotal.getText());
        jaria_print.setText(jaria_print.getText()+"\n");
        jaria_print.setText(jaria_print.getText()+"\n");
        jaria_print.setText(jaria_print.getText()+"\n");
        jaria_print.setText(jaria_print.getText()+"\nReceived"+"                                                                                    Samarth Veet Udyog");
        jaria_print.setText(jaria_print.getText()+"");
        
    };
    public void sixinchsum()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 2).toString());
        }
        jsum.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
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
          String sql = "select * from sixinchsale";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getString("sname"),rs.getString("sdate"),rs.getInt("snos"),rs.getInt("schallen"),rs.getString("vname")});
                 
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }
    
      public void Createcolum()
    {   dm = (DefaultTableModel) jTable1.getModel();
        dm.addColumn("Party Name");
        dm.addColumn("Date");
        dm.addColumn("NOS");
        dm.addColumn("Challen NO");
        dm.addColumn("Vehicle NO");
         jTable1.getTableHeader().setFont(new Font("segoe UI",Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.yellow);
        jTable1.getTableHeader().setForeground(new Color(32,136, 0xcb));
        jTable1.setRowHeight(25);
        
    }
     private void party()
    {
        try
        {
             String sql = "select * from party";
          rs = stmt.executeQuery(sql);
          
          
          while(rs.next())
          {
              String name = rs.getString("paname");
              jComboBox1.addItem(name);
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
         sixinchsum();

    }
       public void size_filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<>(dm);
        jTable1.setRowSorter(tr);
         tr.setRowFilter(RowFilter.regexFilter(query));
         sixinchsum();

    }
    public void loadDate()
     {
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String d = sdf.format(jDateChooser1.getDate());
             String e = sdf.format(jDateChooser2.getDate());
             
          String sql = "select * from sixinchsale where sdate between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                 model.addRow(new Object[] {rs.getString("sname"),rs.getString("sdate"),rs.getInt("snos"),rs.getInt("schallen"),rs.getString("vname")});
                 sixinchsum();
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }

    /**
     *
     */
    public void loadforprint()
    {
        try {
          for(int i=0; i< jTable1.getRowCount(); i++)
                {
                    String party_name =(String) jTable1.getValueAt(i, 0);
                    Object Date = jTable1.getValueAt(i, 1);
                    int NOS =(int) jTable1.getValueAt(i, 2);
                    int Callan_no =(int) jTable1.getValueAt(i, 3);
                   String vehicle_no =(String) jTable1.getValueAt(i, 4);
                    jaria_print.setText(jaria_print.getText()+"\n       "+Date+"\t      "                +NOS+"\t     "               +Callan_no+"\t         "          +vehicle_no);
                    
                }
               } catch (Exception e) {
                   JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void sort()
    {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm);
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
        side_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bill_4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bill_6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser_BD = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jtype = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        button1 = new java.awt.Button();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jaria_print = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        button2 = new java.awt.Button();
        jLabel10 = new javax.swing.JLabel();
        jTextField4_sgst = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField2_cgst = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jam = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jtotal = new javax.swing.JLabel();
        jsum = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_panel.setBackground(new java.awt.Color(23, 35, 51));
        side_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(41, 57, 80));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-brick-wall-20.png"))); // NOI18N
        jLabel7.setText("Production");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-land-sales-20.png"))); // NOI18N
        jLabel11.setText("4\" Sale");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-land-sales-20.png"))); // NOI18N
        jLabel13.setText("6\" Sale");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });

        bill_4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bill_4.setForeground(new java.awt.Color(255, 255, 255));
        bill_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-billing-machine-20.png"))); // NOI18N
        bill_4.setText("4\" Bill");
        bill_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bill_4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bill_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bill_4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bill_4MousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-truck-20.png"))); // NOI18N
        jLabel15.setText("Party");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });

        bill_6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bill_6.setForeground(new java.awt.Color(255, 255, 255));
        bill_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-billing-machine-20.png"))); // NOI18N
        bill_6.setText("6\" Bill");
        bill_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bill_6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bill_6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bill_6MouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cloud-backup-restore-20.png"))); // NOI18N
        jLabel16.setText("Bill Tracking");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cement-bag-20.png"))); // NOI18N
        jLabel17.setText("Vehicle");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 130, 280));

        jPanel1.add(side_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 630));

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SAMARTH VEET UDYOG");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 200, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Start Date");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Billing Party Name");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 30));

        jDateChooser_BD.setDateFormatString("yyyy-MM-d");
        jDateChooser_BD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser_BDMousePressed(evt);
            }
        });
        jPanel3.add(jDateChooser_BD, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 120, 32));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Billig Date");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 90, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 870, 50));

        jPanel4.setBackground(new java.awt.Color(84, 127, 206));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(120, 168, 252));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtype.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtype.setForeground(new java.awt.Color(255, 255, 255));
        jtype.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtype.setText("Sixinch Veet Bill");
        jPanel5.add(jtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Party Name");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, 30));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 10, 120, 30));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Start Date");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 30));

        jDateChooser1.setDateFormatString("yyyy-MM-d");
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDateChooser1MousePressed(evt);
            }
        });
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 60, 120, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("End Date");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 80, 32));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 105, 32));

        button1.setBackground(new java.awt.Color(84, 127, 206));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Select");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel4.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 100, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 120, 110, 30));

        jaria_print.setColumns(20);
        jaria_print.setRows(5);
        jScrollPane2.setViewportView(jaria_print);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 390, 330));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 410, 500));

        jPanel6.setBackground(new java.awt.Color(120, 168, 252));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Enter PRB");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 33));
        jPanel6.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 87, 30));

        button2.setBackground(new java.awt.Color(120, 168, 252));
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Calculate");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel6.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 90, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" SGST");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, 33));
        jPanel6.add(jTextField4_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 110, 33));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" CGST");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, 33));
        jPanel6.add(jTextField2_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, 33));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, 870, 80));

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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 460, 350));

        jPanel7.setBackground(new java.awt.Color(255, 255, 51));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Total Amount Wout TAX");

        jam.setBackground(new java.awt.Color(0, 0, 255));
        jam.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jam.setName("ilovee you"); // NOI18N
        jam.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jamComponentAdded(evt);
            }
        });
        jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jamKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jamKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Total Amount With TAX");

        jtotal.setBackground(new java.awt.Color(0, 0, 255));
        jtotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jtotal.setName("ilovee you"); // NOI18N

        jsum.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Total NOS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsum, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jsum, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 460, 150));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here
        Manufacturing obj = null;
        try {
            obj = new Manufacturing();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here
        jLabel7.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        // TODO add your handling code here:
        jLabel7.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        // TODO add your handling code here:
        jLabel11.setForeground(Color.ORANGE);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
        jLabel11.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
        jLabel12.setForeground(new Color(255,255,0));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
        jLabel12.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel13MouseExited

    private void bill_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseEntered
        // TODO add your handling code here
        bill_4.setForeground(Color.GREEN);
    }//GEN-LAST:event_bill_4MouseEntered

    private void bill_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseExited
        // TODO add your handling code here
        bill_4.setForeground(Color.WHITE);
    }//GEN-LAST:event_bill_4MouseExited

    private void bill_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bill_4MousePressed

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        // TODO add your handling code here:
        jLabel15.setForeground(new Color(46,43,95));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        // TODO add your handling code here
        jLabel15.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel15MouseExited

    private void bill_6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseEntered
        // TODO add your handling code here
        bill_6.setForeground(Color.BLUE);
    }//GEN-LAST:event_bill_6MouseEntered

    private void bill_6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseExited
        // TODO add your handling code here
        bill_6.setForeground(Color.WHITE);
    }//GEN-LAST:event_bill_6MouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here
        jLabel16.setForeground(new Color(255,0,0));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // TODO add your handling code here
        jLabel16.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here
        jLabel17.setForeground(new Color(139,0,255));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        jLabel17.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String query = jComboBox1.getSelectedItem().toString();
        filter(query);
        sixinchsum();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jDateChooser1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jDateChooser1MousePressed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try {
            loadDate();
            

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Select Date");
        }

    }//GEN-LAST:event_button1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            jaria_print.print();
        } catch (PrinterException ex) {
            Logger.getLogger(fourinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        try{
            float A =  (float) Double.parseDouble(jsum.getText());
            float B = (float) Double.parseDouble(jTextField3.getText());
            float c = (float) Double.parseDouble(jTextField4_sgst.getText());
            float d = (float) Double.parseDouble(jTextField2_cgst.getText());
            float per = d/100;
            float ger = c/100;

            float AB = A*B;
            this.jam.setText(AB+"");

            float a = (float) Double.parseDouble(jam.getText());
            float b = per;
            float ab= a*per;
            this.jTextField2_cgst.setText(ab+"");

            float val1 = (float) Double.parseDouble(jam.getText());
            float val2 = ger;
            float val3 = val1*val2;
            this.jTextField4_sgst.setText(val3+"");

            float total = AB+ab+val3;
            this.jtotal.setText(total+"");
            print();
        }
        catch (Exception e)
        {

            JOptionPane.showMessageDialog(this, "Enter all Details");

        }
                String partyname =jComboBox1.getSelectedItem().toString();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String s = sdf.format(jDateChooser1.getDate());
         
          String e = sdf.format(jDateChooser2.getDate());
          //Date date = new Date();
          String D = sdf.format(jDateChooser_BD.getDate());
       // String D = sdf.format(date);
        String name = jtype.getText();
         
         
        
         
         String nos = jsum.getText();
         double prb_6 = Double.parseDouble(jTextField3.getText());
         double sgst = Double.parseDouble(jTextField4_sgst.getText());
         double cgst = Double.parseDouble(jTextField2_cgst.getText());
           int amt_wotax =   (int) Double.parseDouble(jam.getText());
           int amt_wtax =   (int) Double.parseDouble(jtotal.getText());
         
         try
        {
            String sql ="insert into billtracking(partyname,date,startdate,enddate,totalnos,totalamt,totalamtwtax,prb,sgst,cgst,type)"
                    + "values('"+partyname+"','"+D+"','"+s+"','"+e+"','"+nos+"','"+amt_wotax+"','"+amt_wtax+"','"+prb_6+"','"+sgst+"','"+cgst+"','"+name+"')";
            stmt.executeUpdate(sql);
           JOptionPane.showMessageDialog(this, "system Tracked");
           
            
            
        }
        catch( SQLException ex)
        {
         JOptionPane.showMessageDialog(this,ex);
        }
         try
        {
            String sql ="insert into livebilltracking(partyname,date,startdate,enddate,totalnos,totalamt,totalamtwtax,prb,sgst,cgst,type)"
                    + "values('"+partyname+"','"+D+"','"+s+"','"+e+"','"+nos+"','"+amt_wotax+"','"+amt_wtax+"','"+prb_6+"','"+sgst+"','"+cgst+"','"+name+"')";
            stmt.executeUpdate(sql);
           
           jDateChooser1.setDate(null);
            jDateChooser2.setDate(null);
            
            
        }
        catch( SQLException ex)
        {
         JOptionPane.showMessageDialog(this,ex);
        }


    }//GEN-LAST:event_button2ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable1KeyReleased

    private void jamComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jamComponentAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_jamComponentAdded

    private void jamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jamKeyReleased

    private void jamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jamKeyTyped

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        sales obj = null;
        try {
            obj = new sales();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        sixinchsale obj = null;
        try {
            obj = new sixinchsale();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void bill_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseClicked
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
        }
        
    }//GEN-LAST:event_bill_4MouseClicked

    private void bill_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseClicked
        // TODO add your handling code here:
        sixinchbill obj = null;
        try {
            obj = new sixinchbill();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        
    }//GEN-LAST:event_bill_6MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        party obj = null;
        try {
            obj = new party();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchsale.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        vehicle obj = null;
        try {
            obj = new vehicle();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchsale.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jDateChooser_BDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser_BDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser_BDMousePressed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
         Bill_Tracking obj = null;
        try {
            obj = new Bill_Tracking();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchsale.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

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
            java.util.logging.Logger.getLogger(sixinchbill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sixinchbill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sixinchbill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sixinchbill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new sixinchbill().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bill_4;
    private javax.swing.JLabel bill_6;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser_BD;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2_cgst;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4_sgst;
    private javax.swing.JLabel jam;
    private javax.swing.JTextArea jaria_print;
    private javax.swing.JLabel jsum;
    private javax.swing.JLabel jtotal;
    private javax.swing.JLabel jtype;
    private javax.swing.JPanel side_panel;
    // End of variables declaration//GEN-END:variables
}
