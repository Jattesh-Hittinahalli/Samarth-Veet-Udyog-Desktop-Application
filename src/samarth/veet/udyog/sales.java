/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samarth.veet.udyog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
public final class sales extends javax.swing.JFrame {
Connection con;
Statement  stmt;
ResultSet  rs;
DefaultTableModel dm;
java.sql.Date sqlDate;
       

    /**
     * Creates new form sales
     */
    public sales() throws ClassNotFoundException {
        initComponents();
        full_display();
        
        Connect();
        Createcolum();
        loadData();
        party();
        vehicle();
        fourinchsum();
        fourinchsumamt();
      remainig_stock();
        sort();
        
    }
    public sales(String para)
    {
        initComponents();
        jLabel1_fourstock.setText(para);
    }
    public void fourinchsum()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable_sales.getRowCount(); i++) 
        {
           sum = sum + Integer.parseInt(jTable_sales.getValueAt(i, 4).toString());
        }
        jLabel9.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
     public void exportable() throws IOException{
               TableModel model = jTable_sales.getModel();
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
          String sql = "select * from fourinchsales";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable_sales.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getInt("sid"),rs.getString("sname"),rs.getString("vname"),rs.getString("sdate"),rs.getInt("snos"),rs.getInt("schallen"),rs.getString("fourprb"),rs.getInt("fouramt")});
                 
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, "Hi I am There");
             
         }
     }
    
      public void Createcolum()
    {   dm = (DefaultTableModel) jTable_sales.getModel();
   
        dm.addColumn("id");
        dm.addColumn("Party Name");
        dm.addColumn("Vehicle No");
        dm.addColumn("Date");
        dm.addColumn("NOS");
        dm.addColumn("Challen");
        dm.addColumn("Four PRB");
        dm.addColumn("Total amount");
        jTable_sales.getTableHeader().setFont(new Font("segoe UI",Font.BOLD, 12));
        jTable_sales.getTableHeader().setOpaque(false);
        jTable_sales.getTableHeader().setBackground(Color.yellow);
        jTable_sales.getTableHeader().setForeground(new Color(32,136, 0xcb));
        jTable_sales.setRowHeight(25);
        
    }
     public void filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<>(dm);
        jTable_sales.setRowSorter(tr);
         tr.setRowFilter(RowFilter.regexFilter(query));

    }
    public void remainig_stock()
    {
       int m = Integer.parseInt(jTextField4_sum.getText());
        int s =Integer.parseInt(jLabel9.getText());
        int j = m-s;
        jLabel1_fourstock.setText(""+j);
        
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
       private void vehicle()
    {
        try
        {
             String sql = "select * from vehicle";
          rs = stmt.executeQuery(sql);
          
          
          while(rs.next())
          {
              String name = rs.getString("vname");
              jComboBox2.addItem(name);
          }
            
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
       public void fourinchsumamt()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable_sales.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable_sales.getValueAt(i, 7).toString());
        }
        jTextField4_sum1.setText(Integer.toString(sum));
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
             String d = sdf.format(jDateChooser3.getDate());
             String e = sdf.format(jDateChooser4.getDate());
             
          String sql = "select * from fourinchsales where sdate between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable_sales.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                model.addRow(new Object[] {rs.getInt("sid"),rs.getString("sname"),rs.getString("vname"),rs.getString("sdate"),rs.getInt("snos"),rs.getInt("schallen"),rs.getString("fourprb"),rs.getInt("fouramt")});
        fourinchsum();
         fourinchsumamt();
        remainig_stock();
         jDateChooser3.setDate(null);
         jDateChooser4.setDate(null);
             }
          
         } 
         catch (SQLException e)
         {
             
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }
        private void sort()
    {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm);
        jTable_sales.setRowSorter(sorter);
    }
         public void full_display()
    {
       
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int w = d.width;
        int h = d.height;
        jPanel1.setSize(w,h);
        
    }
      
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        side_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bill_4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bill_6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        button4 = new java.awt.Button();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        button2 = new java.awt.Button();
        button1 = new java.awt.Button();
        jTextField8 = new javax.swing.JTextField();
        PRB2 = new javax.swing.JLabel();
        button3 = new java.awt.Button();
        prb_8 = new javax.swing.JTextField();
        PRB1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_sales = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1_fourstock = new javax.swing.JLabel();
        jTextField4_sum1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_panel.setBackground(new java.awt.Color(23, 35, 51));
        side_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(41, 57, 80));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-brick-wall-20.png"))); // NOI18N
        jLabel11.setText("Production");
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-land-sales-20.png"))); // NOI18N
        jLabel12.setText("4\" Sale");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
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

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-truck-20.png"))); // NOI18N
        jLabel14.setText("party");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
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
        jLabel16.setText("Backup");
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
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 130, 280));

        jPanel1.add(side_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 730));

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SAMARTH VEET UDYOG");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 280, -1));

        button4.setBackground(new java.awt.Color(71, 120, 197));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setLabel("Select");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel3.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 70, 30));

        jDateChooser4.setDateFormatString("yyyy-MM-d");
        jPanel3.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 120, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("End Date");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 90, 30));

        jDateChooser3.setDateFormatString("yyyy-MM-d");
        jPanel3.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Start Date");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 1230, 50));

        jPanel4.setBackground(new java.awt.Color(84, 127, 206));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(120, 168, 252));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("4\" Sale");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-18 (1).png"))); // NOI18N

        jTextField5.setBackground(new java.awt.Color(71, 120, 197));
        jTextField5.setForeground(new java.awt.Color(255, 51, 51));
        jTextField5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTextField5.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField5.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 50));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Party Name");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 30));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 180, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vehicle No");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 100, 30));

        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 180, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No's of Unit");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 120, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Challen No");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 110, 30));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 180, 30));
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 180, 30));

        jPanel6.setBackground(new java.awt.Color(71, 120, 197));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 90, 31));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ENTER ID");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 90, 31));

        button2.setBackground(new java.awt.Color(71, 120, 197));
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Delete Details");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel6.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 90, 30));

        button1.setBackground(new java.awt.Color(71, 120, 197));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Add Detail");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel6.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 110, 30));

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 180, 30));

        PRB2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PRB2.setForeground(new java.awt.Color(255, 255, 255));
        PRB2.setText("4\" Amount");
        jPanel6.add(PRB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, 90, 20));

        button3.setBackground(new java.awt.Color(71, 120, 197));
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("Calculate Amount");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel6.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 130, 40));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 310, 270));

        prb_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prb_8ActionPerformed(evt);
            }
        });
        prb_8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prb_8KeyTyped(evt);
            }
        });
        jPanel4.add(prb_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 180, 30));

        PRB1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PRB1.setForeground(new java.awt.Color(255, 255, 255));
        PRB1.setText("4\"PRB");
        jPanel4.add(PRB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 90, 30));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 180, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 80, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 310, 600));

        jTable_sales.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable_sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_sales.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable_sales.setRowHeight(25);
        jTable_sales.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_salesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_sales);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 920, 600));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total Sales");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 31));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 51, 0));
        jLabel9.setText("i love you");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 100, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Stock");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 110, 31));

        jLabel1_fourstock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1_fourstock.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1_fourstock.setText("jLabel1");
        jPanel7.add(jLabel1_fourstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 120, 30));

        jTextField4_sum1.setBackground(new java.awt.Color(255, 255, 0));
        jTextField4_sum1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField4_sum1.setForeground(new java.awt.Color(255, 0, 51));
        jTextField4_sum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4_sum1ActionPerformed(evt);
            }
        });
        jTextField4_sum1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4_sum1KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField4_sum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 110, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total 4\" Amt");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 120, 30));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 640, 1230, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
         String query = jTextField5.getText().toLowerCase();
        filter(query);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTable_salesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_salesMouseClicked
        int row = jTable_sales.getSelectedRow();
        String selection = jTable_sales.getModel().getValueAt(row, 0).toString();
        jTextField2.setText(selection);
    
    }//GEN-LAST:event_jTable_salesMouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here
         Manufacturing obj = null;
    try {
        obj = new Manufacturing();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
    }
        obj.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        // TODO add your handling code here
        jLabel7.setForeground(Color.RED);

    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
        jLabel7.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        // TODO add your handling code here:
        jLabel11.setForeground(Color.ORANGE);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jLabel11.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel12MouseExited

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

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
        jLabel14.setForeground(new Color(46,43,95));
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here
        jLabel14.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel14MouseExited

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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        String partyname =jComboBox1.getSelectedItem().toString();
         String vehicle_no =jComboBox2.getSelectedItem().toString();
        
      
      
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String d = sdf.format(jDateChooser2.getDate());
         System.out.println(d);
         
        
         
         String nos = jTextField3.getText();
        
         String challen = jTextField4.getText();
         double prb_4 = Double.parseDouble(prb_8.getText());
           int amt_4 =   (int) Double.parseDouble(jTextField8.getText());
         
         try
        {
            String sql ="insert into fourinchsales(sname,vname,sdate,snos,schallen,fourprb,fouramt)"
                    + "values('"+partyname+"','"+vehicle_no+"','"+d+"','"+nos+"','"+challen+"','"+prb_4+"','"+amt_4+"')";
            stmt.executeUpdate(sql);
            
            
        }
        catch( SQLException ex)
        {
         JOptionPane.showMessageDialog(this,"Challen Number Must be unique");
        }
         loadData();
         fourinchsum();
         fourinchsumamt();
        remainig_stock();
         jTextField3.setText(null);
         jTextField4.setText(null);
         jTextField2.setText(null);
          jDateChooser2.setDate(null);
          prb_8.setText(null);
          jTextField8.setText(null);
         
         
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String id = jTextField2.getText();
        try 
        {
             String sql = "delete from fourinchsales where sid="+id+"";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Record Deleted");
        } 
        catch (Exception ex) 
        {
             JOptionPane.showMessageDialog(this,"Enter The ID ");
        }
        loadData();
        fourinchsum();
        remainig_stock();
        jTextField2.setText(null);
    }//GEN-LAST:event_button2ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        sales obj = null;
    try {
        obj = new sales();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
    }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        sixinchsale obj = null;
    try {
        obj = new sixinchsale();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
    }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void bill_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseClicked
        // TODO add your handling code here:
        fourinchbill obj;
        try {
            obj = new fourinchbill();
            obj.setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bill_4MouseClicked

    private void bill_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseClicked
        // TODO add your handling code here:
         sixinchbill obj = null;
    try {
        obj = new sixinchbill();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
    }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bill_6MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        party obj = null;
        try {
            obj = new party();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchsale.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

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

    private void prb_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prb_8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prb_8ActionPerformed

    private void prb_8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prb_8KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_prb_8KeyTyped

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        float A =  (float) Double.parseDouble(jTextField3.getText());
        float B =  (float) Double.parseDouble(prb_8.getText());
        float c = A*B;
        this.jTextField8.setText(""+c);
        

    }//GEN-LAST:event_button3ActionPerformed

    private void jTextField4_sum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4_sum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4_sum1ActionPerformed

    private void jTextField4_sum1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4_sum1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4_sum1KeyTyped

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
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
    }//GEN-LAST:event_button4ActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        try {
            exportable();
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new sales().setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PRB1;
    private javax.swing.JLabel PRB2;
    private javax.swing.JLabel bill_4;
    private javax.swing.JLabel bill_6;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
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
    public static javax.swing.JLabel jLabel1_fourstock;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable_sales;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField4_sum1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField prb_8;
    private javax.swing.JPanel side_panel;
    // End of variables declaration//GEN-END:variables

    void setvisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
