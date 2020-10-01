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
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jdk.nashorn.internal.objects.NativeString;
import static samarth.veet.udyog.sixinchsale.jLabel1_sixstock;


/**
 *
 * @author jatte
 */
public final class Manufacturing extends javax.swing.JFrame {
    Connection con;
    Statement  stmt;
    ResultSet  rs;
    DefaultTableModel dm;
       

    /**
     * Creates new form Manufacturing
     */
    public Manufacturing() throws ClassNotFoundException {
        initComponents();
        
       
        Connect();
        Createcolum();
        loadData();
        fourinchsum();
        sixinchsum();
        sort();
        
       
    }
    public void Connect() throws ClassNotFoundException
    {
       try
        {
           //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/samarth","root","");
            Class.forName("org.sqlite.JDBC");
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
          String sql = "select * from production";
          rs = stmt.executeQuery(sql);
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 model.addRow(new Object[] {rs.getString("pid"),rs.getString("pdate"),rs.getString("fourinchproduction"),rs.getString("sixinchproduction")});
                 
             }
          
         } 
         catch (SQLException e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
     }
     public void loadDate()
     {
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String d = sdf.format(jDateChooser2.getDate());
             String e = sdf.format(jDateChooser3.getDate());
             
          String sql = "select * from production where pdate between '"+d+"' and '"+e+"'";
          rs = stmt.executeQuery(sql);
          
          DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             while(rs.next())
             {
                 
                 model.addRow(new Object[] {rs.getString("pid"),rs.getString("pdate"),rs.getString("fourinchproduction"),rs.getString("sixinchproduction")});
                 
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
     public void sixinchsum()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 3).toString());
        }
        sixsum.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
      }
      private void sort()
    {
        TableRowSorter<DefaultTableModel> acending = new TableRowSorter<>(dm);
        jTable1.setRowSorter(acending);
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
     *
     */
    public void fourinchsum()
      {
          try
          {
          int sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) 
        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 2).toString());
        }
        jTextField4_sum.setText(Integer.toString(sum));
          }
          catch (Exception e)
         {
          JOptionPane.showMessageDialog(this, e.getMessage());
             
         }
          
      }
    public void Createcolum()
    {
        dm = (DefaultTableModel) jTable1.getModel();
        
        dm.addColumn("ID");
        dm.addColumn("Date");
        dm.addColumn("4 Veet");
        dm.addColumn("6 veet");
        
        
        jTable1.getTableHeader().setFont(new Font("segoe UI",Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.yellow);
        jTable1.getTableHeader().setForeground(new Color(32,136, 0xcb));
        jTable1.setRowHeight(25);
        
    }
    public void full_display()
    {
       
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int w = d.width;
        int h = d.height;
        this.setSize(w,h);
        this.setLocationByPlatform(true);
    }
     
      
    
  
       
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel1688 = new javax.swing.JPanel();
        jPanel1689 = new javax.swing.JPanel();
        jPanel1690 = new javax.swing.JPanel();
        jPanel1691 = new javax.swing.JPanel();
        jPanel1692 = new javax.swing.JPanel();
        jPanel1693 = new javax.swing.JPanel();
        jPanel1694 = new javax.swing.JPanel();
        jPanel1695 = new javax.swing.JPanel();
        jPanel1696 = new javax.swing.JPanel();
        jPanel1697 = new javax.swing.JPanel();
        jPanel1698 = new javax.swing.JPanel();
        jPanel1699 = new javax.swing.JPanel();
        jPanel1700 = new javax.swing.JPanel();
        jPanel1701 = new javax.swing.JPanel();
        jPanel1702 = new javax.swing.JPanel();
        jPanel1703 = new javax.swing.JPanel();
        jPanel1704 = new javax.swing.JPanel();
        jPanel1705 = new javax.swing.JPanel();
        jPanel1706 = new javax.swing.JPanel();
        jPanel1707 = new javax.swing.JPanel();
        jPanel1708 = new javax.swing.JPanel();
        jPanel1709 = new javax.swing.JPanel();
        jPanel1710 = new javax.swing.JPanel();
        jPanel1711 = new javax.swing.JPanel();
        jPanel1712 = new javax.swing.JPanel();
        jPanel1713 = new javax.swing.JPanel();
        jPanel1714 = new javax.swing.JPanel();
        jPanel1715 = new javax.swing.JPanel();
        jPanel1716 = new javax.swing.JPanel();
        jPanel1717 = new javax.swing.JPanel();
        jPanel1718 = new javax.swing.JPanel();
        jPanel1719 = new javax.swing.JPanel();
        jPanel1720 = new javax.swing.JPanel();
        jPanel1721 = new javax.swing.JPanel();
        jPanel1722 = new javax.swing.JPanel();
        jPanel1723 = new javax.swing.JPanel();
        jPanel1724 = new javax.swing.JPanel();
        jPanel1725 = new javax.swing.JPanel();
        jPanel1726 = new javax.swing.JPanel();
        jPanel1727 = new javax.swing.JPanel();
        jPanel1728 = new javax.swing.JPanel();
        jPanel1729 = new javax.swing.JPanel();
        jPanel1730 = new javax.swing.JPanel();
        jPanel1731 = new javax.swing.JPanel();
        jPanel1732 = new javax.swing.JPanel();
        jPanel1733 = new javax.swing.JPanel();
        jPanel1734 = new javax.swing.JPanel();
        jPanel1735 = new javax.swing.JPanel();
        jPanel1736 = new javax.swing.JPanel();
        jPanel1737 = new javax.swing.JPanel();
        jPanel1738 = new javax.swing.JPanel();
        jPanel1739 = new javax.swing.JPanel();
        jPanel1740 = new javax.swing.JPanel();
        jPanel1741 = new javax.swing.JPanel();
        jPanel1742 = new javax.swing.JPanel();
        jPanel1743 = new javax.swing.JPanel();
        jPanel1744 = new javax.swing.JPanel();
        jPanel1745 = new javax.swing.JPanel();
        jPanel1746 = new javax.swing.JPanel();
        jPanel1747 = new javax.swing.JPanel();
        jPanel1748 = new javax.swing.JPanel();
        jPanel1749 = new javax.swing.JPanel();
        jPanel1750 = new javax.swing.JPanel();
        jPanel1751 = new javax.swing.JPanel();
        jPanel1752 = new javax.swing.JPanel();
        jPanel1753 = new javax.swing.JPanel();
        jPanel1754 = new javax.swing.JPanel();
        jPanel1755 = new javax.swing.JPanel();
        jPanel1756 = new javax.swing.JPanel();
        jPanel1757 = new javax.swing.JPanel();
        jPanel1758 = new javax.swing.JPanel();
        jPanel1759 = new javax.swing.JPanel();
        jPanel1760 = new javax.swing.JPanel();
        jPanel1761 = new javax.swing.JPanel();
        jPanel1762 = new javax.swing.JPanel();
        jPanel1763 = new javax.swing.JPanel();
        jPanel1764 = new javax.swing.JPanel();
        jPanel1765 = new javax.swing.JPanel();
        jPanel1766 = new javax.swing.JPanel();
        jPanel1767 = new javax.swing.JPanel();
        jPanel1768 = new javax.swing.JPanel();
        jPanel1769 = new javax.swing.JPanel();
        jPanel1770 = new javax.swing.JPanel();
        jPanel1771 = new javax.swing.JPanel();
        jPanel1772 = new javax.swing.JPanel();
        jPanel1773 = new javax.swing.JPanel();
        jPanel1774 = new javax.swing.JPanel();
        jPanel1775 = new javax.swing.JPanel();
        jPanel1776 = new javax.swing.JPanel();
        jPanel1777 = new javax.swing.JPanel();
        jPanel1778 = new javax.swing.JPanel();
        jPanel1779 = new javax.swing.JPanel();
        jPanel1780 = new javax.swing.JPanel();
        jPanel1781 = new javax.swing.JPanel();
        jPanel1782 = new javax.swing.JPanel();
        jPanel1783 = new javax.swing.JPanel();
        jPanel1784 = new javax.swing.JPanel();
        jPanel1785 = new javax.swing.JPanel();
        jPanel1786 = new javax.swing.JPanel();
        jPanel1787 = new javax.swing.JPanel();
        jPanel1788 = new javax.swing.JPanel();
        jPanel1789 = new javax.swing.JPanel();
        jPanel1790 = new javax.swing.JPanel();
        jPanel1791 = new javax.swing.JPanel();
        jPanel1792 = new javax.swing.JPanel();
        jPanel1793 = new javax.swing.JPanel();
        jPanel1794 = new javax.swing.JPanel();
        jPanel1795 = new javax.swing.JPanel();
        jPanel1796 = new javax.swing.JPanel();
        jPanel1797 = new javax.swing.JPanel();
        jPanel1798 = new javax.swing.JPanel();
        jPanel1799 = new javax.swing.JPanel();
        jPanel1800 = new javax.swing.JPanel();
        jPanel1801 = new javax.swing.JPanel();
        jPanel1802 = new javax.swing.JPanel();
        jPanel1803 = new javax.swing.JPanel();
        jPanel1804 = new javax.swing.JPanel();
        jPanel1805 = new javax.swing.JPanel();
        jPanel1806 = new javax.swing.JPanel();
        jPanel1807 = new javax.swing.JPanel();
        jPanel1808 = new javax.swing.JPanel();
        jPanel1809 = new javax.swing.JPanel();
        jPanel1810 = new javax.swing.JPanel();
        jPanel1811 = new javax.swing.JPanel();
        jPanel1812 = new javax.swing.JPanel();
        jPanel1813 = new javax.swing.JPanel();
        jPanel1814 = new javax.swing.JPanel();
        jPanel1815 = new javax.swing.JPanel();
        jPanel1816 = new javax.swing.JPanel();
        jPanel1817 = new javax.swing.JPanel();
        jPanel1818 = new javax.swing.JPanel();
        jPanel1819 = new javax.swing.JPanel();
        jPanel1820 = new javax.swing.JPanel();
        jPanel1821 = new javax.swing.JPanel();
        jPanel1822 = new javax.swing.JPanel();
        jPanel1823 = new javax.swing.JPanel();
        jPanel1824 = new javax.swing.JPanel();
        jPanel1825 = new javax.swing.JPanel();
        jPanel1826 = new javax.swing.JPanel();
        jPanel1827 = new javax.swing.JPanel();
        jPanel1828 = new javax.swing.JPanel();
        jPanel1829 = new javax.swing.JPanel();
        jPanel1830 = new javax.swing.JPanel();
        jPanel1831 = new javax.swing.JPanel();
        jPanel1832 = new javax.swing.JPanel();
        jPanel1833 = new javax.swing.JPanel();
        jPanel1834 = new javax.swing.JPanel();
        jPanel1835 = new javax.swing.JPanel();
        jPanel1836 = new javax.swing.JPanel();
        jPanel1837 = new javax.swing.JPanel();
        jPanel1838 = new javax.swing.JPanel();
        jPanel1839 = new javax.swing.JPanel();
        jPanel1840 = new javax.swing.JPanel();
        jPanel1841 = new javax.swing.JPanel();
        jPanel1842 = new javax.swing.JPanel();
        jPanel1843 = new javax.swing.JPanel();
        jPanel1844 = new javax.swing.JPanel();
        jPanel1845 = new javax.swing.JPanel();
        jPanel1846 = new javax.swing.JPanel();
        jPanel1847 = new javax.swing.JPanel();
        jPanel1848 = new javax.swing.JPanel();
        jPanel1849 = new javax.swing.JPanel();
        jPanel1850 = new javax.swing.JPanel();
        jPanel1851 = new javax.swing.JPanel();
        jPanel1852 = new javax.swing.JPanel();
        jPanel1853 = new javax.swing.JPanel();
        jPanel1854 = new javax.swing.JPanel();
        jPanel1855 = new javax.swing.JPanel();
        jPanel1856 = new javax.swing.JPanel();
        jPanel1857 = new javax.swing.JPanel();
        jPanel1858 = new javax.swing.JPanel();
        jPanel1859 = new javax.swing.JPanel();
        jPanel1860 = new javax.swing.JPanel();
        jPanel1861 = new javax.swing.JPanel();
        jPanel1862 = new javax.swing.JPanel();
        jPanel1863 = new javax.swing.JPanel();
        jPanel1864 = new javax.swing.JPanel();
        jPanel1865 = new javax.swing.JPanel();
        jPanel1866 = new javax.swing.JPanel();
        jPanel1867 = new javax.swing.JPanel();
        jPanel1868 = new javax.swing.JPanel();
        jPanel1869 = new javax.swing.JPanel();
        jPanel1870 = new javax.swing.JPanel();
        jPanel1871 = new javax.swing.JPanel();
        jPanel1872 = new javax.swing.JPanel();
        jPanel1873 = new javax.swing.JPanel();
        jPanel1874 = new javax.swing.JPanel();
        jPanel1875 = new javax.swing.JPanel();
        jPanel1876 = new javax.swing.JPanel();
        jPanel1877 = new javax.swing.JPanel();
        jPanel1878 = new javax.swing.JPanel();
        jPanel1879 = new javax.swing.JPanel();
        jPanel1880 = new javax.swing.JPanel();
        jPanel1881 = new javax.swing.JPanel();
        jPanel1882 = new javax.swing.JPanel();
        jPanel1883 = new javax.swing.JPanel();
        jPanel1884 = new javax.swing.JPanel();
        jPanel1885 = new javax.swing.JPanel();
        jPanel1886 = new javax.swing.JPanel();
        jPanel1887 = new javax.swing.JPanel();
        jPanel1888 = new javax.swing.JPanel();
        jPanel1889 = new javax.swing.JPanel();
        jPanel1890 = new javax.swing.JPanel();
        jPanel1891 = new javax.swing.JPanel();
        jPanel1892 = new javax.swing.JPanel();
        jPanel1893 = new javax.swing.JPanel();
        jPanel1894 = new javax.swing.JPanel();
        jPanel1895 = new javax.swing.JPanel();
        jPanel1896 = new javax.swing.JPanel();
        jPanel1897 = new javax.swing.JPanel();
        jPanel1898 = new javax.swing.JPanel();
        jPanel1899 = new javax.swing.JPanel();
        jPanel1900 = new javax.swing.JPanel();
        jPanel1901 = new javax.swing.JPanel();
        jPanel1902 = new javax.swing.JPanel();
        jPanel1903 = new javax.swing.JPanel();
        jPanel1904 = new javax.swing.JPanel();
        jPanel1905 = new javax.swing.JPanel();
        jPanel1906 = new javax.swing.JPanel();
        jPanel1907 = new javax.swing.JPanel();
        jPanel1908 = new javax.swing.JPanel();
        jPanel1909 = new javax.swing.JPanel();
        jPanel1910 = new javax.swing.JPanel();
        jPanel1911 = new javax.swing.JPanel();
        jPanel1912 = new javax.swing.JPanel();
        jPanel1913 = new javax.swing.JPanel();
        jPanel1914 = new javax.swing.JPanel();
        jPanel1915 = new javax.swing.JPanel();
        jPanel1916 = new javax.swing.JPanel();
        jPanel1917 = new javax.swing.JPanel();
        jPanel1918 = new javax.swing.JPanel();
        jPanel1919 = new javax.swing.JPanel();
        jPanel1920 = new javax.swing.JPanel();
        jPanel1921 = new javax.swing.JPanel();
        jPanel1922 = new javax.swing.JPanel();
        jPanel1923 = new javax.swing.JPanel();
        jPanel1924 = new javax.swing.JPanel();
        jPanel1925 = new javax.swing.JPanel();
        jPanel1926 = new javax.swing.JPanel();
        jPanel1927 = new javax.swing.JPanel();
        jPanel1928 = new javax.swing.JPanel();
        jPanel1929 = new javax.swing.JPanel();
        jPanel1930 = new javax.swing.JPanel();
        jPanel1931 = new javax.swing.JPanel();
        jPanel1932 = new javax.swing.JPanel();
        jPanel1933 = new javax.swing.JPanel();
        jPanel1934 = new javax.swing.JPanel();
        jPanel1935 = new javax.swing.JPanel();
        jPanel1936 = new javax.swing.JPanel();
        jPanel1937 = new javax.swing.JPanel();
        jPanel1938 = new javax.swing.JPanel();
        jPanel1939 = new javax.swing.JPanel();
        jPanel1940 = new javax.swing.JPanel();
        jPanel1941 = new javax.swing.JPanel();
        jPanel1942 = new javax.swing.JPanel();
        jPanel1943 = new javax.swing.JPanel();
        jPanel1944 = new javax.swing.JPanel();
        jPanel1945 = new javax.swing.JPanel();
        jPanel1946 = new javax.swing.JPanel();
        jPanel1947 = new javax.swing.JPanel();
        jPanel1948 = new javax.swing.JPanel();
        jPanel1949 = new javax.swing.JPanel();
        jPanel1950 = new javax.swing.JPanel();
        jPanel1951 = new javax.swing.JPanel();
        jPanel1952 = new javax.swing.JPanel();
        jPanel1953 = new javax.swing.JPanel();
        jPanel1954 = new javax.swing.JPanel();
        jPanel1955 = new javax.swing.JPanel();
        jPanel1956 = new javax.swing.JPanel();
        jPanel1957 = new javax.swing.JPanel();
        jPanel1958 = new javax.swing.JPanel();
        jPanel1959 = new javax.swing.JPanel();
        jPanel1960 = new javax.swing.JPanel();
        jPanel1961 = new javax.swing.JPanel();
        jPanel1962 = new javax.swing.JPanel();
        jPanel1963 = new javax.swing.JPanel();
        jPanel1964 = new javax.swing.JPanel();
        jPanel1965 = new javax.swing.JPanel();
        jPanel1966 = new javax.swing.JPanel();
        jPanel1967 = new javax.swing.JPanel();
        jPanel1968 = new javax.swing.JPanel();
        jPanel1969 = new javax.swing.JPanel();
        jPanel1970 = new javax.swing.JPanel();
        jPanel1971 = new javax.swing.JPanel();
        jPanel1972 = new javax.swing.JPanel();
        jPanel1973 = new javax.swing.JPanel();
        jPanel1974 = new javax.swing.JPanel();
        jPanel1975 = new javax.swing.JPanel();
        jPanel1976 = new javax.swing.JPanel();
        jPanel1977 = new javax.swing.JPanel();
        jPanel1978 = new javax.swing.JPanel();
        jPanel1979 = new javax.swing.JPanel();
        jPanel1980 = new javax.swing.JPanel();
        jPanel1981 = new javax.swing.JPanel();
        jPanel1982 = new javax.swing.JPanel();
        jPanel1983 = new javax.swing.JPanel();
        jPanel1984 = new javax.swing.JPanel();
        jPanel1985 = new javax.swing.JPanel();
        jPanel1986 = new javax.swing.JPanel();
        jPanel1987 = new javax.swing.JPanel();
        jPanel1988 = new javax.swing.JPanel();
        jPanel1989 = new javax.swing.JPanel();
        jPanel1990 = new javax.swing.JPanel();
        jPanel1991 = new javax.swing.JPanel();
        jPanel1992 = new javax.swing.JPanel();
        jPanel1993 = new javax.swing.JPanel();
        jPanel1994 = new javax.swing.JPanel();
        jPanel1995 = new javax.swing.JPanel();
        jPanel1996 = new javax.swing.JPanel();
        jPanel1997 = new javax.swing.JPanel();
        jPanel1998 = new javax.swing.JPanel();
        jPanel1999 = new javax.swing.JPanel();
        jPanel2000 = new javax.swing.JPanel();
        jPanel2001 = new javax.swing.JPanel();
        jPanel2002 = new javax.swing.JPanel();
        jPanel2003 = new javax.swing.JPanel();
        jPanel2004 = new javax.swing.JPanel();
        jPanel2005 = new javax.swing.JPanel();
        jPanel2006 = new javax.swing.JPanel();
        jPanel2007 = new javax.swing.JPanel();
        jPanel2008 = new javax.swing.JPanel();
        jPanel2009 = new javax.swing.JPanel();
        jPanel2010 = new javax.swing.JPanel();
        jPanel2011 = new javax.swing.JPanel();
        jPanel2012 = new javax.swing.JPanel();
        jPanel2013 = new javax.swing.JPanel();
        jPanel2014 = new javax.swing.JPanel();
        jPanel2015 = new javax.swing.JPanel();
        jPanel2016 = new javax.swing.JPanel();
        jPanel2017 = new javax.swing.JPanel();
        jPanel2018 = new javax.swing.JPanel();
        jPanel2019 = new javax.swing.JPanel();
        jPanel2020 = new javax.swing.JPanel();
        jPanel2021 = new javax.swing.JPanel();
        jPanel2022 = new javax.swing.JPanel();
        jPanel2023 = new javax.swing.JPanel();
        jPanel2024 = new javax.swing.JPanel();
        jPanel2025 = new javax.swing.JPanel();
        jPanel2026 = new javax.swing.JPanel();
        jPanel2027 = new javax.swing.JPanel();
        jPanel2028 = new javax.swing.JPanel();
        jPanel2029 = new javax.swing.JPanel();
        jPanel2030 = new javax.swing.JPanel();
        jPanel2031 = new javax.swing.JPanel();
        jPanel2032 = new javax.swing.JPanel();
        jPanel2033 = new javax.swing.JPanel();
        jPanel2034 = new javax.swing.JPanel();
        jPanel2035 = new javax.swing.JPanel();
        jPanel2036 = new javax.swing.JPanel();
        jPanel2037 = new javax.swing.JPanel();
        jPanel2038 = new javax.swing.JPanel();
        jPanel2039 = new javax.swing.JPanel();
        jPanel2040 = new javax.swing.JPanel();
        jPanel2041 = new javax.swing.JPanel();
        jPanel2042 = new javax.swing.JPanel();
        jPanel2043 = new javax.swing.JPanel();
        jPanel2044 = new javax.swing.JPanel();
        jPanel2045 = new javax.swing.JPanel();
        jPanel2046 = new javax.swing.JPanel();
        jPanel2047 = new javax.swing.JPanel();
        jPanel2048 = new javax.swing.JPanel();
        jPanel2049 = new javax.swing.JPanel();
        jPanel2050 = new javax.swing.JPanel();
        jPanel2051 = new javax.swing.JPanel();
        jPanel2052 = new javax.swing.JPanel();
        jPanel2053 = new javax.swing.JPanel();
        jPanel2054 = new javax.swing.JPanel();
        jPanel2055 = new javax.swing.JPanel();
        jPanel2056 = new javax.swing.JPanel();
        jPanel2057 = new javax.swing.JPanel();
        jPanel2058 = new javax.swing.JPanel();
        jPanel2059 = new javax.swing.JPanel();
        jPanel2060 = new javax.swing.JPanel();
        jPanel2061 = new javax.swing.JPanel();
        jPanel2062 = new javax.swing.JPanel();
        jPanel2063 = new javax.swing.JPanel();
        jPanel2064 = new javax.swing.JPanel();
        jPanel2065 = new javax.swing.JPanel();
        jPanel2066 = new javax.swing.JPanel();
        jPanel2067 = new javax.swing.JPanel();
        jPanel2068 = new javax.swing.JPanel();
        jPanel2069 = new javax.swing.JPanel();
        jPanel2070 = new javax.swing.JPanel();
        jPanel2071 = new javax.swing.JPanel();
        jPanel2072 = new javax.swing.JPanel();
        jPanel2073 = new javax.swing.JPanel();
        jPanel2074 = new javax.swing.JPanel();
        jPanel2075 = new javax.swing.JPanel();
        jPanel2076 = new javax.swing.JPanel();
        jPanel2077 = new javax.swing.JPanel();
        jPanel2078 = new javax.swing.JPanel();
        jPanel2079 = new javax.swing.JPanel();
        jPanel2080 = new javax.swing.JPanel();
        jPanel2081 = new javax.swing.JPanel();
        jPanel2082 = new javax.swing.JPanel();
        jPanel2083 = new javax.swing.JPanel();
        jPanel2084 = new javax.swing.JPanel();
        jPanel2085 = new javax.swing.JPanel();
        jPanel2086 = new javax.swing.JPanel();
        jPanel2087 = new javax.swing.JPanel();
        jPanel2088 = new javax.swing.JPanel();
        jPanel2089 = new javax.swing.JPanel();
        jPanel2090 = new javax.swing.JPanel();
        jPanel2091 = new javax.swing.JPanel();
        jPanel2092 = new javax.swing.JPanel();
        jPanel2093 = new javax.swing.JPanel();
        jPanel2094 = new javax.swing.JPanel();
        jPanel2095 = new javax.swing.JPanel();
        jPanel2096 = new javax.swing.JPanel();
        jPanel2097 = new javax.swing.JPanel();
        jPanel2098 = new javax.swing.JPanel();
        jPanel2099 = new javax.swing.JPanel();
        jPanel2100 = new javax.swing.JPanel();
        jPanel2101 = new javax.swing.JPanel();
        jPanel2102 = new javax.swing.JPanel();
        jPanel2103 = new javax.swing.JPanel();
        jPanel2104 = new javax.swing.JPanel();
        jPanel2105 = new javax.swing.JPanel();
        jPanel2106 = new javax.swing.JPanel();
        jPanel2107 = new javax.swing.JPanel();
        jPanel2108 = new javax.swing.JPanel();
        jPanel2109 = new javax.swing.JPanel();
        jPanel2110 = new javax.swing.JPanel();
        jPanel2111 = new javax.swing.JPanel();
        jPanel2112 = new javax.swing.JPanel();
        jPanel2113 = new javax.swing.JPanel();
        jPanel2114 = new javax.swing.JPanel();
        jPanel2115 = new javax.swing.JPanel();
        jPanel2116 = new javax.swing.JPanel();
        jPanel2117 = new javax.swing.JPanel();
        jPanel2118 = new javax.swing.JPanel();
        jPanel2119 = new javax.swing.JPanel();
        jPanel2120 = new javax.swing.JPanel();
        jPanel2121 = new javax.swing.JPanel();
        jPanel2122 = new javax.swing.JPanel();
        jPanel2123 = new javax.swing.JPanel();
        jPanel2124 = new javax.swing.JPanel();
        jPanel2125 = new javax.swing.JPanel();
        jPanel2126 = new javax.swing.JPanel();
        jPanel2127 = new javax.swing.JPanel();
        jPanel2128 = new javax.swing.JPanel();
        jPanel2129 = new javax.swing.JPanel();
        jPanel2130 = new javax.swing.JPanel();
        jPanel2131 = new javax.swing.JPanel();
        jPanel2132 = new javax.swing.JPanel();
        jPanel2133 = new javax.swing.JPanel();
        jPanel2134 = new javax.swing.JPanel();
        jPanel2135 = new javax.swing.JPanel();
        jPanel2136 = new javax.swing.JPanel();
        jPanel2137 = new javax.swing.JPanel();
        jPanel2138 = new javax.swing.JPanel();
        jPanel2139 = new javax.swing.JPanel();
        jPanel2140 = new javax.swing.JPanel();
        jPanel2141 = new javax.swing.JPanel();
        jPanel2142 = new javax.swing.JPanel();
        jPanel2143 = new javax.swing.JPanel();
        jPanel2144 = new javax.swing.JPanel();
        jPanel2145 = new javax.swing.JPanel();
        jPanel2146 = new javax.swing.JPanel();
        jPanel2147 = new javax.swing.JPanel();
        jPanel2148 = new javax.swing.JPanel();
        jPanel2149 = new javax.swing.JPanel();
        jPanel2150 = new javax.swing.JPanel();
        jPanel2151 = new javax.swing.JPanel();
        jPanel2152 = new javax.swing.JPanel();
        jPanel2153 = new javax.swing.JPanel();
        jPanel2154 = new javax.swing.JPanel();
        jPanel2155 = new javax.swing.JPanel();
        jPanel2156 = new javax.swing.JPanel();
        jPanel2157 = new javax.swing.JPanel();
        jPanel2158 = new javax.swing.JPanel();
        jPanel2159 = new javax.swing.JPanel();
        jPanel2160 = new javax.swing.JPanel();
        jPanel2161 = new javax.swing.JPanel();
        jPanel2162 = new javax.swing.JPanel();
        jPanel2163 = new javax.swing.JPanel();
        jPanel2164 = new javax.swing.JPanel();
        jPanel2165 = new javax.swing.JPanel();
        jPanel2166 = new javax.swing.JPanel();
        jPanel2167 = new javax.swing.JPanel();
        jPanel2168 = new javax.swing.JPanel();
        jPanel2169 = new javax.swing.JPanel();
        jPanel2170 = new javax.swing.JPanel();
        jPanel2171 = new javax.swing.JPanel();
        jPanel2172 = new javax.swing.JPanel();
        jPanel2173 = new javax.swing.JPanel();
        jPanel2174 = new javax.swing.JPanel();
        jPanel2175 = new javax.swing.JPanel();
        jPanel2176 = new javax.swing.JPanel();
        jPanel2177 = new javax.swing.JPanel();
        jPanel2178 = new javax.swing.JPanel();
        jPanel2179 = new javax.swing.JPanel();
        jPanel2180 = new javax.swing.JPanel();
        jPanel2181 = new javax.swing.JPanel();
        jPanel2182 = new javax.swing.JPanel();
        jPanel2183 = new javax.swing.JPanel();
        jPanel2184 = new javax.swing.JPanel();
        jPanel2185 = new javax.swing.JPanel();
        jPanel2186 = new javax.swing.JPanel();
        jPanel2187 = new javax.swing.JPanel();
        jPanel2188 = new javax.swing.JPanel();
        jPanel2189 = new javax.swing.JPanel();
        jPanel2190 = new javax.swing.JPanel();
        jPanel2191 = new javax.swing.JPanel();
        jPanel2192 = new javax.swing.JPanel();
        jPanel2193 = new javax.swing.JPanel();
        jPanel2194 = new javax.swing.JPanel();
        jPanel2195 = new javax.swing.JPanel();
        jPanel2196 = new javax.swing.JPanel();
        jPanel2197 = new javax.swing.JPanel();
        jPanel2198 = new javax.swing.JPanel();
        jPanel2199 = new javax.swing.JPanel();
        side_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bill_4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bill_6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        button1 = new java.awt.Button();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        button3 = new java.awt.Button();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        button2 = new java.awt.Button();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jTextField4_sum = new javax.swing.JTextField();
        sixsum = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1688.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1688.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1689Layout = new javax.swing.GroupLayout(jPanel1689);
        jPanel1689.setLayout(jPanel1689Layout);
        jPanel1689Layout.setHorizontalGroup(
            jPanel1689Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1689Layout.setVerticalGroup(
            jPanel1689Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1688.add(jPanel1689, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1690.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1690.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1691Layout = new javax.swing.GroupLayout(jPanel1691);
        jPanel1691.setLayout(jPanel1691Layout);
        jPanel1691Layout.setHorizontalGroup(
            jPanel1691Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1691Layout.setVerticalGroup(
            jPanel1691Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1690.add(jPanel1691, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1688.add(jPanel1690, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1692.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1692.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1693Layout = new javax.swing.GroupLayout(jPanel1693);
        jPanel1693.setLayout(jPanel1693Layout);
        jPanel1693Layout.setHorizontalGroup(
            jPanel1693Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1693Layout.setVerticalGroup(
            jPanel1693Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1692.add(jPanel1693, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1694.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1694.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1695Layout = new javax.swing.GroupLayout(jPanel1695);
        jPanel1695.setLayout(jPanel1695Layout);
        jPanel1695Layout.setHorizontalGroup(
            jPanel1695Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1695Layout.setVerticalGroup(
            jPanel1695Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1694.add(jPanel1695, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1692.add(jPanel1694, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1688.add(jPanel1692, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1696.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1696.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1697Layout = new javax.swing.GroupLayout(jPanel1697);
        jPanel1697.setLayout(jPanel1697Layout);
        jPanel1697Layout.setHorizontalGroup(
            jPanel1697Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1697Layout.setVerticalGroup(
            jPanel1697Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1696.add(jPanel1697, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1698.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1698.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1699Layout = new javax.swing.GroupLayout(jPanel1699);
        jPanel1699.setLayout(jPanel1699Layout);
        jPanel1699Layout.setHorizontalGroup(
            jPanel1699Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1699Layout.setVerticalGroup(
            jPanel1699Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1698.add(jPanel1699, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1696.add(jPanel1698, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1700.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1700.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1701Layout = new javax.swing.GroupLayout(jPanel1701);
        jPanel1701.setLayout(jPanel1701Layout);
        jPanel1701Layout.setHorizontalGroup(
            jPanel1701Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1701Layout.setVerticalGroup(
            jPanel1701Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1700.add(jPanel1701, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1702.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1702.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1703Layout = new javax.swing.GroupLayout(jPanel1703);
        jPanel1703.setLayout(jPanel1703Layout);
        jPanel1703Layout.setHorizontalGroup(
            jPanel1703Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1703Layout.setVerticalGroup(
            jPanel1703Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1702.add(jPanel1703, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1700.add(jPanel1702, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1696.add(jPanel1700, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1688.add(jPanel1696, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1704.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1704.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1705Layout = new javax.swing.GroupLayout(jPanel1705);
        jPanel1705.setLayout(jPanel1705Layout);
        jPanel1705Layout.setHorizontalGroup(
            jPanel1705Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1705Layout.setVerticalGroup(
            jPanel1705Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1704.add(jPanel1705, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1706.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1706.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1707Layout = new javax.swing.GroupLayout(jPanel1707);
        jPanel1707.setLayout(jPanel1707Layout);
        jPanel1707Layout.setHorizontalGroup(
            jPanel1707Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1707Layout.setVerticalGroup(
            jPanel1707Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1706.add(jPanel1707, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1704.add(jPanel1706, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1708.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1708.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1709Layout = new javax.swing.GroupLayout(jPanel1709);
        jPanel1709.setLayout(jPanel1709Layout);
        jPanel1709Layout.setHorizontalGroup(
            jPanel1709Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1709Layout.setVerticalGroup(
            jPanel1709Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1708.add(jPanel1709, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1710.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1710.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1711Layout = new javax.swing.GroupLayout(jPanel1711);
        jPanel1711.setLayout(jPanel1711Layout);
        jPanel1711Layout.setHorizontalGroup(
            jPanel1711Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1711Layout.setVerticalGroup(
            jPanel1711Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1710.add(jPanel1711, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1708.add(jPanel1710, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1704.add(jPanel1708, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1712.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1712.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1713Layout = new javax.swing.GroupLayout(jPanel1713);
        jPanel1713.setLayout(jPanel1713Layout);
        jPanel1713Layout.setHorizontalGroup(
            jPanel1713Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1713Layout.setVerticalGroup(
            jPanel1713Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1712.add(jPanel1713, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1714.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1714.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1715Layout = new javax.swing.GroupLayout(jPanel1715);
        jPanel1715.setLayout(jPanel1715Layout);
        jPanel1715Layout.setHorizontalGroup(
            jPanel1715Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1715Layout.setVerticalGroup(
            jPanel1715Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1714.add(jPanel1715, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1712.add(jPanel1714, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1716.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1716.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1717Layout = new javax.swing.GroupLayout(jPanel1717);
        jPanel1717.setLayout(jPanel1717Layout);
        jPanel1717Layout.setHorizontalGroup(
            jPanel1717Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1717Layout.setVerticalGroup(
            jPanel1717Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1716.add(jPanel1717, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1718.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1718.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1719Layout = new javax.swing.GroupLayout(jPanel1719);
        jPanel1719.setLayout(jPanel1719Layout);
        jPanel1719Layout.setHorizontalGroup(
            jPanel1719Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1719Layout.setVerticalGroup(
            jPanel1719Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1718.add(jPanel1719, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1716.add(jPanel1718, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1712.add(jPanel1716, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1704.add(jPanel1712, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1688.add(jPanel1704, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1720.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1720.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1721Layout = new javax.swing.GroupLayout(jPanel1721);
        jPanel1721.setLayout(jPanel1721Layout);
        jPanel1721Layout.setHorizontalGroup(
            jPanel1721Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1721Layout.setVerticalGroup(
            jPanel1721Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1720.add(jPanel1721, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1722.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1722.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1723Layout = new javax.swing.GroupLayout(jPanel1723);
        jPanel1723.setLayout(jPanel1723Layout);
        jPanel1723Layout.setHorizontalGroup(
            jPanel1723Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1723Layout.setVerticalGroup(
            jPanel1723Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1722.add(jPanel1723, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1720.add(jPanel1722, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1724.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1724.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1725Layout = new javax.swing.GroupLayout(jPanel1725);
        jPanel1725.setLayout(jPanel1725Layout);
        jPanel1725Layout.setHorizontalGroup(
            jPanel1725Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1725Layout.setVerticalGroup(
            jPanel1725Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1724.add(jPanel1725, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1726.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1726.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1727Layout = new javax.swing.GroupLayout(jPanel1727);
        jPanel1727.setLayout(jPanel1727Layout);
        jPanel1727Layout.setHorizontalGroup(
            jPanel1727Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1727Layout.setVerticalGroup(
            jPanel1727Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1726.add(jPanel1727, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1724.add(jPanel1726, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1720.add(jPanel1724, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1728.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1728.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1729Layout = new javax.swing.GroupLayout(jPanel1729);
        jPanel1729.setLayout(jPanel1729Layout);
        jPanel1729Layout.setHorizontalGroup(
            jPanel1729Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1729Layout.setVerticalGroup(
            jPanel1729Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1728.add(jPanel1729, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1730.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1730.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1731Layout = new javax.swing.GroupLayout(jPanel1731);
        jPanel1731.setLayout(jPanel1731Layout);
        jPanel1731Layout.setHorizontalGroup(
            jPanel1731Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1731Layout.setVerticalGroup(
            jPanel1731Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1730.add(jPanel1731, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1728.add(jPanel1730, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1732.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1732.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1733Layout = new javax.swing.GroupLayout(jPanel1733);
        jPanel1733.setLayout(jPanel1733Layout);
        jPanel1733Layout.setHorizontalGroup(
            jPanel1733Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1733Layout.setVerticalGroup(
            jPanel1733Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1732.add(jPanel1733, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1734.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1734.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1735Layout = new javax.swing.GroupLayout(jPanel1735);
        jPanel1735.setLayout(jPanel1735Layout);
        jPanel1735Layout.setHorizontalGroup(
            jPanel1735Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1735Layout.setVerticalGroup(
            jPanel1735Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1734.add(jPanel1735, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1732.add(jPanel1734, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1728.add(jPanel1732, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1720.add(jPanel1728, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1736.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1736.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1737Layout = new javax.swing.GroupLayout(jPanel1737);
        jPanel1737.setLayout(jPanel1737Layout);
        jPanel1737Layout.setHorizontalGroup(
            jPanel1737Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1737Layout.setVerticalGroup(
            jPanel1737Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1736.add(jPanel1737, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1738.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1738.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1739Layout = new javax.swing.GroupLayout(jPanel1739);
        jPanel1739.setLayout(jPanel1739Layout);
        jPanel1739Layout.setHorizontalGroup(
            jPanel1739Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1739Layout.setVerticalGroup(
            jPanel1739Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1738.add(jPanel1739, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1736.add(jPanel1738, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1740.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1740.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1741Layout = new javax.swing.GroupLayout(jPanel1741);
        jPanel1741.setLayout(jPanel1741Layout);
        jPanel1741Layout.setHorizontalGroup(
            jPanel1741Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1741Layout.setVerticalGroup(
            jPanel1741Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1740.add(jPanel1741, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1742.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1742.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1743Layout = new javax.swing.GroupLayout(jPanel1743);
        jPanel1743.setLayout(jPanel1743Layout);
        jPanel1743Layout.setHorizontalGroup(
            jPanel1743Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1743Layout.setVerticalGroup(
            jPanel1743Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1742.add(jPanel1743, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1740.add(jPanel1742, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1736.add(jPanel1740, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1744.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1744.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1745Layout = new javax.swing.GroupLayout(jPanel1745);
        jPanel1745.setLayout(jPanel1745Layout);
        jPanel1745Layout.setHorizontalGroup(
            jPanel1745Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1745Layout.setVerticalGroup(
            jPanel1745Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1744.add(jPanel1745, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1746.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1746.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1747Layout = new javax.swing.GroupLayout(jPanel1747);
        jPanel1747.setLayout(jPanel1747Layout);
        jPanel1747Layout.setHorizontalGroup(
            jPanel1747Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1747Layout.setVerticalGroup(
            jPanel1747Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1746.add(jPanel1747, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1744.add(jPanel1746, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1748.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1748.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1749Layout = new javax.swing.GroupLayout(jPanel1749);
        jPanel1749.setLayout(jPanel1749Layout);
        jPanel1749Layout.setHorizontalGroup(
            jPanel1749Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1749Layout.setVerticalGroup(
            jPanel1749Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1748.add(jPanel1749, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1750.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1750.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1751Layout = new javax.swing.GroupLayout(jPanel1751);
        jPanel1751.setLayout(jPanel1751Layout);
        jPanel1751Layout.setHorizontalGroup(
            jPanel1751Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1751Layout.setVerticalGroup(
            jPanel1751Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1750.add(jPanel1751, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1748.add(jPanel1750, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1744.add(jPanel1748, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1736.add(jPanel1744, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1720.add(jPanel1736, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1688.add(jPanel1720, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1752.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1752.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1753Layout = new javax.swing.GroupLayout(jPanel1753);
        jPanel1753.setLayout(jPanel1753Layout);
        jPanel1753Layout.setHorizontalGroup(
            jPanel1753Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1753Layout.setVerticalGroup(
            jPanel1753Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1752.add(jPanel1753, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1754.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1754.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1755Layout = new javax.swing.GroupLayout(jPanel1755);
        jPanel1755.setLayout(jPanel1755Layout);
        jPanel1755Layout.setHorizontalGroup(
            jPanel1755Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1755Layout.setVerticalGroup(
            jPanel1755Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1754.add(jPanel1755, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1752.add(jPanel1754, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1756.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1756.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1757Layout = new javax.swing.GroupLayout(jPanel1757);
        jPanel1757.setLayout(jPanel1757Layout);
        jPanel1757Layout.setHorizontalGroup(
            jPanel1757Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1757Layout.setVerticalGroup(
            jPanel1757Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1756.add(jPanel1757, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1758.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1758.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1759Layout = new javax.swing.GroupLayout(jPanel1759);
        jPanel1759.setLayout(jPanel1759Layout);
        jPanel1759Layout.setHorizontalGroup(
            jPanel1759Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1759Layout.setVerticalGroup(
            jPanel1759Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1758.add(jPanel1759, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1756.add(jPanel1758, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1752.add(jPanel1756, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1760.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1760.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1761Layout = new javax.swing.GroupLayout(jPanel1761);
        jPanel1761.setLayout(jPanel1761Layout);
        jPanel1761Layout.setHorizontalGroup(
            jPanel1761Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1761Layout.setVerticalGroup(
            jPanel1761Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1760.add(jPanel1761, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1762.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1762.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1763Layout = new javax.swing.GroupLayout(jPanel1763);
        jPanel1763.setLayout(jPanel1763Layout);
        jPanel1763Layout.setHorizontalGroup(
            jPanel1763Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1763Layout.setVerticalGroup(
            jPanel1763Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1762.add(jPanel1763, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1760.add(jPanel1762, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1764.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1764.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1765Layout = new javax.swing.GroupLayout(jPanel1765);
        jPanel1765.setLayout(jPanel1765Layout);
        jPanel1765Layout.setHorizontalGroup(
            jPanel1765Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1765Layout.setVerticalGroup(
            jPanel1765Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1764.add(jPanel1765, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1766.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1766.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1767Layout = new javax.swing.GroupLayout(jPanel1767);
        jPanel1767.setLayout(jPanel1767Layout);
        jPanel1767Layout.setHorizontalGroup(
            jPanel1767Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1767Layout.setVerticalGroup(
            jPanel1767Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1766.add(jPanel1767, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1764.add(jPanel1766, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1760.add(jPanel1764, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1752.add(jPanel1760, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1768.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1768.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1769Layout = new javax.swing.GroupLayout(jPanel1769);
        jPanel1769.setLayout(jPanel1769Layout);
        jPanel1769Layout.setHorizontalGroup(
            jPanel1769Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1769Layout.setVerticalGroup(
            jPanel1769Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1768.add(jPanel1769, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1770.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1770.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1771Layout = new javax.swing.GroupLayout(jPanel1771);
        jPanel1771.setLayout(jPanel1771Layout);
        jPanel1771Layout.setHorizontalGroup(
            jPanel1771Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1771Layout.setVerticalGroup(
            jPanel1771Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1770.add(jPanel1771, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1768.add(jPanel1770, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1772.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1772.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1773Layout = new javax.swing.GroupLayout(jPanel1773);
        jPanel1773.setLayout(jPanel1773Layout);
        jPanel1773Layout.setHorizontalGroup(
            jPanel1773Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1773Layout.setVerticalGroup(
            jPanel1773Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1772.add(jPanel1773, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1774.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1774.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1775Layout = new javax.swing.GroupLayout(jPanel1775);
        jPanel1775.setLayout(jPanel1775Layout);
        jPanel1775Layout.setHorizontalGroup(
            jPanel1775Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1775Layout.setVerticalGroup(
            jPanel1775Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1774.add(jPanel1775, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1772.add(jPanel1774, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1768.add(jPanel1772, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1776.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1776.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1777Layout = new javax.swing.GroupLayout(jPanel1777);
        jPanel1777.setLayout(jPanel1777Layout);
        jPanel1777Layout.setHorizontalGroup(
            jPanel1777Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1777Layout.setVerticalGroup(
            jPanel1777Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1776.add(jPanel1777, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1778.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1778.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1779Layout = new javax.swing.GroupLayout(jPanel1779);
        jPanel1779.setLayout(jPanel1779Layout);
        jPanel1779Layout.setHorizontalGroup(
            jPanel1779Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1779Layout.setVerticalGroup(
            jPanel1779Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1778.add(jPanel1779, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1776.add(jPanel1778, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1780.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1780.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1781Layout = new javax.swing.GroupLayout(jPanel1781);
        jPanel1781.setLayout(jPanel1781Layout);
        jPanel1781Layout.setHorizontalGroup(
            jPanel1781Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1781Layout.setVerticalGroup(
            jPanel1781Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1780.add(jPanel1781, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1782.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1782.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1783Layout = new javax.swing.GroupLayout(jPanel1783);
        jPanel1783.setLayout(jPanel1783Layout);
        jPanel1783Layout.setHorizontalGroup(
            jPanel1783Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1783Layout.setVerticalGroup(
            jPanel1783Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1782.add(jPanel1783, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1780.add(jPanel1782, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1776.add(jPanel1780, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1768.add(jPanel1776, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1752.add(jPanel1768, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1784.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1784.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1785Layout = new javax.swing.GroupLayout(jPanel1785);
        jPanel1785.setLayout(jPanel1785Layout);
        jPanel1785Layout.setHorizontalGroup(
            jPanel1785Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1785Layout.setVerticalGroup(
            jPanel1785Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1784.add(jPanel1785, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1786.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1786.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1787Layout = new javax.swing.GroupLayout(jPanel1787);
        jPanel1787.setLayout(jPanel1787Layout);
        jPanel1787Layout.setHorizontalGroup(
            jPanel1787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1787Layout.setVerticalGroup(
            jPanel1787Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1786.add(jPanel1787, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1784.add(jPanel1786, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1788.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1788.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1789Layout = new javax.swing.GroupLayout(jPanel1789);
        jPanel1789.setLayout(jPanel1789Layout);
        jPanel1789Layout.setHorizontalGroup(
            jPanel1789Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1789Layout.setVerticalGroup(
            jPanel1789Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1788.add(jPanel1789, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1790.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1790.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1791Layout = new javax.swing.GroupLayout(jPanel1791);
        jPanel1791.setLayout(jPanel1791Layout);
        jPanel1791Layout.setHorizontalGroup(
            jPanel1791Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1791Layout.setVerticalGroup(
            jPanel1791Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1790.add(jPanel1791, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1788.add(jPanel1790, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1784.add(jPanel1788, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1792.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1792.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1793Layout = new javax.swing.GroupLayout(jPanel1793);
        jPanel1793.setLayout(jPanel1793Layout);
        jPanel1793Layout.setHorizontalGroup(
            jPanel1793Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1793Layout.setVerticalGroup(
            jPanel1793Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1792.add(jPanel1793, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1794.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1794.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1795Layout = new javax.swing.GroupLayout(jPanel1795);
        jPanel1795.setLayout(jPanel1795Layout);
        jPanel1795Layout.setHorizontalGroup(
            jPanel1795Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1795Layout.setVerticalGroup(
            jPanel1795Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1794.add(jPanel1795, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1792.add(jPanel1794, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1796.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1796.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1797Layout = new javax.swing.GroupLayout(jPanel1797);
        jPanel1797.setLayout(jPanel1797Layout);
        jPanel1797Layout.setHorizontalGroup(
            jPanel1797Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1797Layout.setVerticalGroup(
            jPanel1797Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1796.add(jPanel1797, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1798.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1798.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1799Layout = new javax.swing.GroupLayout(jPanel1799);
        jPanel1799.setLayout(jPanel1799Layout);
        jPanel1799Layout.setHorizontalGroup(
            jPanel1799Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1799Layout.setVerticalGroup(
            jPanel1799Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1798.add(jPanel1799, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1796.add(jPanel1798, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1792.add(jPanel1796, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1784.add(jPanel1792, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1800.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1800.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1801Layout = new javax.swing.GroupLayout(jPanel1801);
        jPanel1801.setLayout(jPanel1801Layout);
        jPanel1801Layout.setHorizontalGroup(
            jPanel1801Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1801Layout.setVerticalGroup(
            jPanel1801Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1800.add(jPanel1801, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1802.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1802.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1803Layout = new javax.swing.GroupLayout(jPanel1803);
        jPanel1803.setLayout(jPanel1803Layout);
        jPanel1803Layout.setHorizontalGroup(
            jPanel1803Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1803Layout.setVerticalGroup(
            jPanel1803Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1802.add(jPanel1803, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1800.add(jPanel1802, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1804.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1804.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1805Layout = new javax.swing.GroupLayout(jPanel1805);
        jPanel1805.setLayout(jPanel1805Layout);
        jPanel1805Layout.setHorizontalGroup(
            jPanel1805Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1805Layout.setVerticalGroup(
            jPanel1805Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1804.add(jPanel1805, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1806.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1806.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1807Layout = new javax.swing.GroupLayout(jPanel1807);
        jPanel1807.setLayout(jPanel1807Layout);
        jPanel1807Layout.setHorizontalGroup(
            jPanel1807Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1807Layout.setVerticalGroup(
            jPanel1807Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1806.add(jPanel1807, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1804.add(jPanel1806, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1800.add(jPanel1804, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1808.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1808.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1809Layout = new javax.swing.GroupLayout(jPanel1809);
        jPanel1809.setLayout(jPanel1809Layout);
        jPanel1809Layout.setHorizontalGroup(
            jPanel1809Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1809Layout.setVerticalGroup(
            jPanel1809Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1808.add(jPanel1809, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1810.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1810.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1811Layout = new javax.swing.GroupLayout(jPanel1811);
        jPanel1811.setLayout(jPanel1811Layout);
        jPanel1811Layout.setHorizontalGroup(
            jPanel1811Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1811Layout.setVerticalGroup(
            jPanel1811Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1810.add(jPanel1811, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1808.add(jPanel1810, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1812.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1812.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1813Layout = new javax.swing.GroupLayout(jPanel1813);
        jPanel1813.setLayout(jPanel1813Layout);
        jPanel1813Layout.setHorizontalGroup(
            jPanel1813Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1813Layout.setVerticalGroup(
            jPanel1813Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1812.add(jPanel1813, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1814.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1814.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1815Layout = new javax.swing.GroupLayout(jPanel1815);
        jPanel1815.setLayout(jPanel1815Layout);
        jPanel1815Layout.setHorizontalGroup(
            jPanel1815Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1815Layout.setVerticalGroup(
            jPanel1815Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1814.add(jPanel1815, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1812.add(jPanel1814, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1808.add(jPanel1812, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1800.add(jPanel1808, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1784.add(jPanel1800, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1752.add(jPanel1784, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1688.add(jPanel1752, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel1816.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1816.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1817Layout = new javax.swing.GroupLayout(jPanel1817);
        jPanel1817.setLayout(jPanel1817Layout);
        jPanel1817Layout.setHorizontalGroup(
            jPanel1817Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1817Layout.setVerticalGroup(
            jPanel1817Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1816.add(jPanel1817, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1818.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1818.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1819Layout = new javax.swing.GroupLayout(jPanel1819);
        jPanel1819.setLayout(jPanel1819Layout);
        jPanel1819Layout.setHorizontalGroup(
            jPanel1819Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1819Layout.setVerticalGroup(
            jPanel1819Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1818.add(jPanel1819, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1816.add(jPanel1818, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1820.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1820.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1821Layout = new javax.swing.GroupLayout(jPanel1821);
        jPanel1821.setLayout(jPanel1821Layout);
        jPanel1821Layout.setHorizontalGroup(
            jPanel1821Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1821Layout.setVerticalGroup(
            jPanel1821Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1820.add(jPanel1821, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1822.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1822.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1823Layout = new javax.swing.GroupLayout(jPanel1823);
        jPanel1823.setLayout(jPanel1823Layout);
        jPanel1823Layout.setHorizontalGroup(
            jPanel1823Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1823Layout.setVerticalGroup(
            jPanel1823Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1822.add(jPanel1823, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1820.add(jPanel1822, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1816.add(jPanel1820, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1824.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1824.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1825Layout = new javax.swing.GroupLayout(jPanel1825);
        jPanel1825.setLayout(jPanel1825Layout);
        jPanel1825Layout.setHorizontalGroup(
            jPanel1825Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1825Layout.setVerticalGroup(
            jPanel1825Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1824.add(jPanel1825, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1826.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1826.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1827Layout = new javax.swing.GroupLayout(jPanel1827);
        jPanel1827.setLayout(jPanel1827Layout);
        jPanel1827Layout.setHorizontalGroup(
            jPanel1827Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1827Layout.setVerticalGroup(
            jPanel1827Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1826.add(jPanel1827, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1824.add(jPanel1826, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1828.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1828.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1829Layout = new javax.swing.GroupLayout(jPanel1829);
        jPanel1829.setLayout(jPanel1829Layout);
        jPanel1829Layout.setHorizontalGroup(
            jPanel1829Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1829Layout.setVerticalGroup(
            jPanel1829Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1828.add(jPanel1829, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1830.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1830.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1831Layout = new javax.swing.GroupLayout(jPanel1831);
        jPanel1831.setLayout(jPanel1831Layout);
        jPanel1831Layout.setHorizontalGroup(
            jPanel1831Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1831Layout.setVerticalGroup(
            jPanel1831Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1830.add(jPanel1831, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1828.add(jPanel1830, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1824.add(jPanel1828, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1816.add(jPanel1824, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1832.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1832.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1833Layout = new javax.swing.GroupLayout(jPanel1833);
        jPanel1833.setLayout(jPanel1833Layout);
        jPanel1833Layout.setHorizontalGroup(
            jPanel1833Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1833Layout.setVerticalGroup(
            jPanel1833Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1832.add(jPanel1833, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1834.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1834.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1835Layout = new javax.swing.GroupLayout(jPanel1835);
        jPanel1835.setLayout(jPanel1835Layout);
        jPanel1835Layout.setHorizontalGroup(
            jPanel1835Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1835Layout.setVerticalGroup(
            jPanel1835Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1834.add(jPanel1835, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1832.add(jPanel1834, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1836.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1836.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1837Layout = new javax.swing.GroupLayout(jPanel1837);
        jPanel1837.setLayout(jPanel1837Layout);
        jPanel1837Layout.setHorizontalGroup(
            jPanel1837Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1837Layout.setVerticalGroup(
            jPanel1837Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1836.add(jPanel1837, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1838.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1838.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1839Layout = new javax.swing.GroupLayout(jPanel1839);
        jPanel1839.setLayout(jPanel1839Layout);
        jPanel1839Layout.setHorizontalGroup(
            jPanel1839Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1839Layout.setVerticalGroup(
            jPanel1839Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1838.add(jPanel1839, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1836.add(jPanel1838, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1832.add(jPanel1836, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1840.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1840.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1841Layout = new javax.swing.GroupLayout(jPanel1841);
        jPanel1841.setLayout(jPanel1841Layout);
        jPanel1841Layout.setHorizontalGroup(
            jPanel1841Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1841Layout.setVerticalGroup(
            jPanel1841Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1840.add(jPanel1841, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1842.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1842.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1843Layout = new javax.swing.GroupLayout(jPanel1843);
        jPanel1843.setLayout(jPanel1843Layout);
        jPanel1843Layout.setHorizontalGroup(
            jPanel1843Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1843Layout.setVerticalGroup(
            jPanel1843Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1842.add(jPanel1843, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1840.add(jPanel1842, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1844.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1844.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1845Layout = new javax.swing.GroupLayout(jPanel1845);
        jPanel1845.setLayout(jPanel1845Layout);
        jPanel1845Layout.setHorizontalGroup(
            jPanel1845Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1845Layout.setVerticalGroup(
            jPanel1845Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1844.add(jPanel1845, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1846.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1846.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1847Layout = new javax.swing.GroupLayout(jPanel1847);
        jPanel1847.setLayout(jPanel1847Layout);
        jPanel1847Layout.setHorizontalGroup(
            jPanel1847Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1847Layout.setVerticalGroup(
            jPanel1847Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1846.add(jPanel1847, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1844.add(jPanel1846, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1840.add(jPanel1844, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1832.add(jPanel1840, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1816.add(jPanel1832, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1848.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1848.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1849Layout = new javax.swing.GroupLayout(jPanel1849);
        jPanel1849.setLayout(jPanel1849Layout);
        jPanel1849Layout.setHorizontalGroup(
            jPanel1849Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1849Layout.setVerticalGroup(
            jPanel1849Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1848.add(jPanel1849, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1850.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1850.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1851Layout = new javax.swing.GroupLayout(jPanel1851);
        jPanel1851.setLayout(jPanel1851Layout);
        jPanel1851Layout.setHorizontalGroup(
            jPanel1851Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1851Layout.setVerticalGroup(
            jPanel1851Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1850.add(jPanel1851, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1848.add(jPanel1850, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1852.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1852.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1853Layout = new javax.swing.GroupLayout(jPanel1853);
        jPanel1853.setLayout(jPanel1853Layout);
        jPanel1853Layout.setHorizontalGroup(
            jPanel1853Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1853Layout.setVerticalGroup(
            jPanel1853Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1852.add(jPanel1853, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1854.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1854.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1855Layout = new javax.swing.GroupLayout(jPanel1855);
        jPanel1855.setLayout(jPanel1855Layout);
        jPanel1855Layout.setHorizontalGroup(
            jPanel1855Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1855Layout.setVerticalGroup(
            jPanel1855Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1854.add(jPanel1855, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1852.add(jPanel1854, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1848.add(jPanel1852, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1856.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1856.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1857Layout = new javax.swing.GroupLayout(jPanel1857);
        jPanel1857.setLayout(jPanel1857Layout);
        jPanel1857Layout.setHorizontalGroup(
            jPanel1857Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1857Layout.setVerticalGroup(
            jPanel1857Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1856.add(jPanel1857, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1858.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1858.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1859Layout = new javax.swing.GroupLayout(jPanel1859);
        jPanel1859.setLayout(jPanel1859Layout);
        jPanel1859Layout.setHorizontalGroup(
            jPanel1859Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1859Layout.setVerticalGroup(
            jPanel1859Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1858.add(jPanel1859, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1856.add(jPanel1858, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1860.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1860.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1861Layout = new javax.swing.GroupLayout(jPanel1861);
        jPanel1861.setLayout(jPanel1861Layout);
        jPanel1861Layout.setHorizontalGroup(
            jPanel1861Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1861Layout.setVerticalGroup(
            jPanel1861Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1860.add(jPanel1861, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1862.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1862.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1863Layout = new javax.swing.GroupLayout(jPanel1863);
        jPanel1863.setLayout(jPanel1863Layout);
        jPanel1863Layout.setHorizontalGroup(
            jPanel1863Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1863Layout.setVerticalGroup(
            jPanel1863Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1862.add(jPanel1863, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1860.add(jPanel1862, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1856.add(jPanel1860, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1848.add(jPanel1856, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1864.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1864.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1865Layout = new javax.swing.GroupLayout(jPanel1865);
        jPanel1865.setLayout(jPanel1865Layout);
        jPanel1865Layout.setHorizontalGroup(
            jPanel1865Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1865Layout.setVerticalGroup(
            jPanel1865Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1864.add(jPanel1865, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1866.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1866.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1867Layout = new javax.swing.GroupLayout(jPanel1867);
        jPanel1867.setLayout(jPanel1867Layout);
        jPanel1867Layout.setHorizontalGroup(
            jPanel1867Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1867Layout.setVerticalGroup(
            jPanel1867Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1866.add(jPanel1867, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1864.add(jPanel1866, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1868.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1868.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1869Layout = new javax.swing.GroupLayout(jPanel1869);
        jPanel1869.setLayout(jPanel1869Layout);
        jPanel1869Layout.setHorizontalGroup(
            jPanel1869Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1869Layout.setVerticalGroup(
            jPanel1869Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1868.add(jPanel1869, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1870.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1870.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1871Layout = new javax.swing.GroupLayout(jPanel1871);
        jPanel1871.setLayout(jPanel1871Layout);
        jPanel1871Layout.setHorizontalGroup(
            jPanel1871Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1871Layout.setVerticalGroup(
            jPanel1871Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1870.add(jPanel1871, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1868.add(jPanel1870, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1864.add(jPanel1868, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1872.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1872.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1873Layout = new javax.swing.GroupLayout(jPanel1873);
        jPanel1873.setLayout(jPanel1873Layout);
        jPanel1873Layout.setHorizontalGroup(
            jPanel1873Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1873Layout.setVerticalGroup(
            jPanel1873Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1872.add(jPanel1873, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1874.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1874.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1875Layout = new javax.swing.GroupLayout(jPanel1875);
        jPanel1875.setLayout(jPanel1875Layout);
        jPanel1875Layout.setHorizontalGroup(
            jPanel1875Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1875Layout.setVerticalGroup(
            jPanel1875Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1874.add(jPanel1875, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1872.add(jPanel1874, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1876.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1876.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1877Layout = new javax.swing.GroupLayout(jPanel1877);
        jPanel1877.setLayout(jPanel1877Layout);
        jPanel1877Layout.setHorizontalGroup(
            jPanel1877Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1877Layout.setVerticalGroup(
            jPanel1877Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1876.add(jPanel1877, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1878.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1878.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1879Layout = new javax.swing.GroupLayout(jPanel1879);
        jPanel1879.setLayout(jPanel1879Layout);
        jPanel1879Layout.setHorizontalGroup(
            jPanel1879Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1879Layout.setVerticalGroup(
            jPanel1879Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1878.add(jPanel1879, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1876.add(jPanel1878, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1872.add(jPanel1876, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1864.add(jPanel1872, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1848.add(jPanel1864, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1816.add(jPanel1848, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1880.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1880.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1881Layout = new javax.swing.GroupLayout(jPanel1881);
        jPanel1881.setLayout(jPanel1881Layout);
        jPanel1881Layout.setHorizontalGroup(
            jPanel1881Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1881Layout.setVerticalGroup(
            jPanel1881Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1880.add(jPanel1881, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1882.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1882.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1883Layout = new javax.swing.GroupLayout(jPanel1883);
        jPanel1883.setLayout(jPanel1883Layout);
        jPanel1883Layout.setHorizontalGroup(
            jPanel1883Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1883Layout.setVerticalGroup(
            jPanel1883Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1882.add(jPanel1883, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1880.add(jPanel1882, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1884.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1884.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1885Layout = new javax.swing.GroupLayout(jPanel1885);
        jPanel1885.setLayout(jPanel1885Layout);
        jPanel1885Layout.setHorizontalGroup(
            jPanel1885Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1885Layout.setVerticalGroup(
            jPanel1885Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1884.add(jPanel1885, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1886.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1886.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1887Layout = new javax.swing.GroupLayout(jPanel1887);
        jPanel1887.setLayout(jPanel1887Layout);
        jPanel1887Layout.setHorizontalGroup(
            jPanel1887Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1887Layout.setVerticalGroup(
            jPanel1887Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1886.add(jPanel1887, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1884.add(jPanel1886, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1880.add(jPanel1884, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1888.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1888.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1889Layout = new javax.swing.GroupLayout(jPanel1889);
        jPanel1889.setLayout(jPanel1889Layout);
        jPanel1889Layout.setHorizontalGroup(
            jPanel1889Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1889Layout.setVerticalGroup(
            jPanel1889Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1888.add(jPanel1889, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1890.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1890.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1891Layout = new javax.swing.GroupLayout(jPanel1891);
        jPanel1891.setLayout(jPanel1891Layout);
        jPanel1891Layout.setHorizontalGroup(
            jPanel1891Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1891Layout.setVerticalGroup(
            jPanel1891Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1890.add(jPanel1891, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1888.add(jPanel1890, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1892.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1892.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1893Layout = new javax.swing.GroupLayout(jPanel1893);
        jPanel1893.setLayout(jPanel1893Layout);
        jPanel1893Layout.setHorizontalGroup(
            jPanel1893Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1893Layout.setVerticalGroup(
            jPanel1893Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1892.add(jPanel1893, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1894.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1894.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1895Layout = new javax.swing.GroupLayout(jPanel1895);
        jPanel1895.setLayout(jPanel1895Layout);
        jPanel1895Layout.setHorizontalGroup(
            jPanel1895Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1895Layout.setVerticalGroup(
            jPanel1895Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1894.add(jPanel1895, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1892.add(jPanel1894, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1888.add(jPanel1892, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1880.add(jPanel1888, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1896.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1896.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1897Layout = new javax.swing.GroupLayout(jPanel1897);
        jPanel1897.setLayout(jPanel1897Layout);
        jPanel1897Layout.setHorizontalGroup(
            jPanel1897Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1897Layout.setVerticalGroup(
            jPanel1897Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1896.add(jPanel1897, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1898.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1898.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1899Layout = new javax.swing.GroupLayout(jPanel1899);
        jPanel1899.setLayout(jPanel1899Layout);
        jPanel1899Layout.setHorizontalGroup(
            jPanel1899Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1899Layout.setVerticalGroup(
            jPanel1899Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1898.add(jPanel1899, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1896.add(jPanel1898, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1900.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1900.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1901Layout = new javax.swing.GroupLayout(jPanel1901);
        jPanel1901.setLayout(jPanel1901Layout);
        jPanel1901Layout.setHorizontalGroup(
            jPanel1901Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1901Layout.setVerticalGroup(
            jPanel1901Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1900.add(jPanel1901, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1902.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1902.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1903Layout = new javax.swing.GroupLayout(jPanel1903);
        jPanel1903.setLayout(jPanel1903Layout);
        jPanel1903Layout.setHorizontalGroup(
            jPanel1903Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1903Layout.setVerticalGroup(
            jPanel1903Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1902.add(jPanel1903, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1900.add(jPanel1902, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1896.add(jPanel1900, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1904.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1904.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1905Layout = new javax.swing.GroupLayout(jPanel1905);
        jPanel1905.setLayout(jPanel1905Layout);
        jPanel1905Layout.setHorizontalGroup(
            jPanel1905Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1905Layout.setVerticalGroup(
            jPanel1905Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1904.add(jPanel1905, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1906.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1906.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1907Layout = new javax.swing.GroupLayout(jPanel1907);
        jPanel1907.setLayout(jPanel1907Layout);
        jPanel1907Layout.setHorizontalGroup(
            jPanel1907Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1907Layout.setVerticalGroup(
            jPanel1907Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1906.add(jPanel1907, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1904.add(jPanel1906, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1908.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1908.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1909Layout = new javax.swing.GroupLayout(jPanel1909);
        jPanel1909.setLayout(jPanel1909Layout);
        jPanel1909Layout.setHorizontalGroup(
            jPanel1909Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1909Layout.setVerticalGroup(
            jPanel1909Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1908.add(jPanel1909, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1910.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1910.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1911Layout = new javax.swing.GroupLayout(jPanel1911);
        jPanel1911.setLayout(jPanel1911Layout);
        jPanel1911Layout.setHorizontalGroup(
            jPanel1911Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1911Layout.setVerticalGroup(
            jPanel1911Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1910.add(jPanel1911, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1908.add(jPanel1910, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1904.add(jPanel1908, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1896.add(jPanel1904, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1880.add(jPanel1896, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1912.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1912.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1913Layout = new javax.swing.GroupLayout(jPanel1913);
        jPanel1913.setLayout(jPanel1913Layout);
        jPanel1913Layout.setHorizontalGroup(
            jPanel1913Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1913Layout.setVerticalGroup(
            jPanel1913Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1912.add(jPanel1913, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1914.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1914.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1915Layout = new javax.swing.GroupLayout(jPanel1915);
        jPanel1915.setLayout(jPanel1915Layout);
        jPanel1915Layout.setHorizontalGroup(
            jPanel1915Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1915Layout.setVerticalGroup(
            jPanel1915Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1914.add(jPanel1915, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1912.add(jPanel1914, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1916.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1916.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1917Layout = new javax.swing.GroupLayout(jPanel1917);
        jPanel1917.setLayout(jPanel1917Layout);
        jPanel1917Layout.setHorizontalGroup(
            jPanel1917Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1917Layout.setVerticalGroup(
            jPanel1917Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1916.add(jPanel1917, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1918.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1918.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1919Layout = new javax.swing.GroupLayout(jPanel1919);
        jPanel1919.setLayout(jPanel1919Layout);
        jPanel1919Layout.setHorizontalGroup(
            jPanel1919Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1919Layout.setVerticalGroup(
            jPanel1919Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1918.add(jPanel1919, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1916.add(jPanel1918, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1912.add(jPanel1916, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1920.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1920.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1921Layout = new javax.swing.GroupLayout(jPanel1921);
        jPanel1921.setLayout(jPanel1921Layout);
        jPanel1921Layout.setHorizontalGroup(
            jPanel1921Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1921Layout.setVerticalGroup(
            jPanel1921Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1920.add(jPanel1921, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1922.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1922.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1923Layout = new javax.swing.GroupLayout(jPanel1923);
        jPanel1923.setLayout(jPanel1923Layout);
        jPanel1923Layout.setHorizontalGroup(
            jPanel1923Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1923Layout.setVerticalGroup(
            jPanel1923Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1922.add(jPanel1923, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1920.add(jPanel1922, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1924.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1924.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1925Layout = new javax.swing.GroupLayout(jPanel1925);
        jPanel1925.setLayout(jPanel1925Layout);
        jPanel1925Layout.setHorizontalGroup(
            jPanel1925Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1925Layout.setVerticalGroup(
            jPanel1925Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1924.add(jPanel1925, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1926.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1926.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1927Layout = new javax.swing.GroupLayout(jPanel1927);
        jPanel1927.setLayout(jPanel1927Layout);
        jPanel1927Layout.setHorizontalGroup(
            jPanel1927Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1927Layout.setVerticalGroup(
            jPanel1927Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1926.add(jPanel1927, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1924.add(jPanel1926, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1920.add(jPanel1924, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1912.add(jPanel1920, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1928.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1928.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1929Layout = new javax.swing.GroupLayout(jPanel1929);
        jPanel1929.setLayout(jPanel1929Layout);
        jPanel1929Layout.setHorizontalGroup(
            jPanel1929Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1929Layout.setVerticalGroup(
            jPanel1929Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1928.add(jPanel1929, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1930.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1930.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1931Layout = new javax.swing.GroupLayout(jPanel1931);
        jPanel1931.setLayout(jPanel1931Layout);
        jPanel1931Layout.setHorizontalGroup(
            jPanel1931Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1931Layout.setVerticalGroup(
            jPanel1931Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1930.add(jPanel1931, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1928.add(jPanel1930, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1932.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1932.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1933Layout = new javax.swing.GroupLayout(jPanel1933);
        jPanel1933.setLayout(jPanel1933Layout);
        jPanel1933Layout.setHorizontalGroup(
            jPanel1933Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1933Layout.setVerticalGroup(
            jPanel1933Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1932.add(jPanel1933, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1934.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1934.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1935Layout = new javax.swing.GroupLayout(jPanel1935);
        jPanel1935.setLayout(jPanel1935Layout);
        jPanel1935Layout.setHorizontalGroup(
            jPanel1935Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1935Layout.setVerticalGroup(
            jPanel1935Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1934.add(jPanel1935, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1932.add(jPanel1934, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1928.add(jPanel1932, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1936.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1936.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1937Layout = new javax.swing.GroupLayout(jPanel1937);
        jPanel1937.setLayout(jPanel1937Layout);
        jPanel1937Layout.setHorizontalGroup(
            jPanel1937Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1937Layout.setVerticalGroup(
            jPanel1937Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1936.add(jPanel1937, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1938.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1938.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1939Layout = new javax.swing.GroupLayout(jPanel1939);
        jPanel1939.setLayout(jPanel1939Layout);
        jPanel1939Layout.setHorizontalGroup(
            jPanel1939Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1939Layout.setVerticalGroup(
            jPanel1939Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1938.add(jPanel1939, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1936.add(jPanel1938, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1940.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1940.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1941Layout = new javax.swing.GroupLayout(jPanel1941);
        jPanel1941.setLayout(jPanel1941Layout);
        jPanel1941Layout.setHorizontalGroup(
            jPanel1941Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1941Layout.setVerticalGroup(
            jPanel1941Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1940.add(jPanel1941, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1942.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1942.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1943Layout = new javax.swing.GroupLayout(jPanel1943);
        jPanel1943.setLayout(jPanel1943Layout);
        jPanel1943Layout.setHorizontalGroup(
            jPanel1943Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1943Layout.setVerticalGroup(
            jPanel1943Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1942.add(jPanel1943, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1940.add(jPanel1942, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1936.add(jPanel1940, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1928.add(jPanel1936, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1912.add(jPanel1928, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1880.add(jPanel1912, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1816.add(jPanel1880, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel1688.add(jPanel1816, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel1944.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1944.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1945Layout = new javax.swing.GroupLayout(jPanel1945);
        jPanel1945.setLayout(jPanel1945Layout);
        jPanel1945Layout.setHorizontalGroup(
            jPanel1945Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1945Layout.setVerticalGroup(
            jPanel1945Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1944.add(jPanel1945, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1946.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1946.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1947Layout = new javax.swing.GroupLayout(jPanel1947);
        jPanel1947.setLayout(jPanel1947Layout);
        jPanel1947Layout.setHorizontalGroup(
            jPanel1947Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1947Layout.setVerticalGroup(
            jPanel1947Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1946.add(jPanel1947, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1944.add(jPanel1946, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1948.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1948.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1949Layout = new javax.swing.GroupLayout(jPanel1949);
        jPanel1949.setLayout(jPanel1949Layout);
        jPanel1949Layout.setHorizontalGroup(
            jPanel1949Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1949Layout.setVerticalGroup(
            jPanel1949Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1948.add(jPanel1949, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1950.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1950.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1951Layout = new javax.swing.GroupLayout(jPanel1951);
        jPanel1951.setLayout(jPanel1951Layout);
        jPanel1951Layout.setHorizontalGroup(
            jPanel1951Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1951Layout.setVerticalGroup(
            jPanel1951Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1950.add(jPanel1951, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1948.add(jPanel1950, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1944.add(jPanel1948, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1952.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1952.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1953Layout = new javax.swing.GroupLayout(jPanel1953);
        jPanel1953.setLayout(jPanel1953Layout);
        jPanel1953Layout.setHorizontalGroup(
            jPanel1953Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1953Layout.setVerticalGroup(
            jPanel1953Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1952.add(jPanel1953, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1954.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1954.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1955Layout = new javax.swing.GroupLayout(jPanel1955);
        jPanel1955.setLayout(jPanel1955Layout);
        jPanel1955Layout.setHorizontalGroup(
            jPanel1955Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1955Layout.setVerticalGroup(
            jPanel1955Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1954.add(jPanel1955, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1952.add(jPanel1954, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1956.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1956.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1957Layout = new javax.swing.GroupLayout(jPanel1957);
        jPanel1957.setLayout(jPanel1957Layout);
        jPanel1957Layout.setHorizontalGroup(
            jPanel1957Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1957Layout.setVerticalGroup(
            jPanel1957Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1956.add(jPanel1957, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1958.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1958.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1959Layout = new javax.swing.GroupLayout(jPanel1959);
        jPanel1959.setLayout(jPanel1959Layout);
        jPanel1959Layout.setHorizontalGroup(
            jPanel1959Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1959Layout.setVerticalGroup(
            jPanel1959Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1958.add(jPanel1959, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1956.add(jPanel1958, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1952.add(jPanel1956, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1944.add(jPanel1952, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1960.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1960.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1961Layout = new javax.swing.GroupLayout(jPanel1961);
        jPanel1961.setLayout(jPanel1961Layout);
        jPanel1961Layout.setHorizontalGroup(
            jPanel1961Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1961Layout.setVerticalGroup(
            jPanel1961Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1960.add(jPanel1961, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1962.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1962.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1963Layout = new javax.swing.GroupLayout(jPanel1963);
        jPanel1963.setLayout(jPanel1963Layout);
        jPanel1963Layout.setHorizontalGroup(
            jPanel1963Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1963Layout.setVerticalGroup(
            jPanel1963Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1962.add(jPanel1963, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1960.add(jPanel1962, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1964.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1964.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1965Layout = new javax.swing.GroupLayout(jPanel1965);
        jPanel1965.setLayout(jPanel1965Layout);
        jPanel1965Layout.setHorizontalGroup(
            jPanel1965Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1965Layout.setVerticalGroup(
            jPanel1965Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1964.add(jPanel1965, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1966.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1966.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1967Layout = new javax.swing.GroupLayout(jPanel1967);
        jPanel1967.setLayout(jPanel1967Layout);
        jPanel1967Layout.setHorizontalGroup(
            jPanel1967Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1967Layout.setVerticalGroup(
            jPanel1967Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1966.add(jPanel1967, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1964.add(jPanel1966, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1960.add(jPanel1964, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1968.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1968.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1969Layout = new javax.swing.GroupLayout(jPanel1969);
        jPanel1969.setLayout(jPanel1969Layout);
        jPanel1969Layout.setHorizontalGroup(
            jPanel1969Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1969Layout.setVerticalGroup(
            jPanel1969Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1968.add(jPanel1969, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1970.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1970.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1971Layout = new javax.swing.GroupLayout(jPanel1971);
        jPanel1971.setLayout(jPanel1971Layout);
        jPanel1971Layout.setHorizontalGroup(
            jPanel1971Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1971Layout.setVerticalGroup(
            jPanel1971Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1970.add(jPanel1971, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1968.add(jPanel1970, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1972.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1972.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1973Layout = new javax.swing.GroupLayout(jPanel1973);
        jPanel1973.setLayout(jPanel1973Layout);
        jPanel1973Layout.setHorizontalGroup(
            jPanel1973Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1973Layout.setVerticalGroup(
            jPanel1973Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1972.add(jPanel1973, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1974.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1974.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1975Layout = new javax.swing.GroupLayout(jPanel1975);
        jPanel1975.setLayout(jPanel1975Layout);
        jPanel1975Layout.setHorizontalGroup(
            jPanel1975Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1975Layout.setVerticalGroup(
            jPanel1975Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1974.add(jPanel1975, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1972.add(jPanel1974, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1968.add(jPanel1972, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1960.add(jPanel1968, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1944.add(jPanel1960, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1976.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1976.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1977Layout = new javax.swing.GroupLayout(jPanel1977);
        jPanel1977.setLayout(jPanel1977Layout);
        jPanel1977Layout.setHorizontalGroup(
            jPanel1977Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1977Layout.setVerticalGroup(
            jPanel1977Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1976.add(jPanel1977, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1978.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1978.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1979Layout = new javax.swing.GroupLayout(jPanel1979);
        jPanel1979.setLayout(jPanel1979Layout);
        jPanel1979Layout.setHorizontalGroup(
            jPanel1979Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1979Layout.setVerticalGroup(
            jPanel1979Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1978.add(jPanel1979, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1976.add(jPanel1978, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1980.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1980.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1981Layout = new javax.swing.GroupLayout(jPanel1981);
        jPanel1981.setLayout(jPanel1981Layout);
        jPanel1981Layout.setHorizontalGroup(
            jPanel1981Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1981Layout.setVerticalGroup(
            jPanel1981Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1980.add(jPanel1981, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1982.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1982.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1983Layout = new javax.swing.GroupLayout(jPanel1983);
        jPanel1983.setLayout(jPanel1983Layout);
        jPanel1983Layout.setHorizontalGroup(
            jPanel1983Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1983Layout.setVerticalGroup(
            jPanel1983Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1982.add(jPanel1983, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1980.add(jPanel1982, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1976.add(jPanel1980, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1984.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1984.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1985Layout = new javax.swing.GroupLayout(jPanel1985);
        jPanel1985.setLayout(jPanel1985Layout);
        jPanel1985Layout.setHorizontalGroup(
            jPanel1985Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1985Layout.setVerticalGroup(
            jPanel1985Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1984.add(jPanel1985, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1986.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1986.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1987Layout = new javax.swing.GroupLayout(jPanel1987);
        jPanel1987.setLayout(jPanel1987Layout);
        jPanel1987Layout.setHorizontalGroup(
            jPanel1987Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1987Layout.setVerticalGroup(
            jPanel1987Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1986.add(jPanel1987, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1984.add(jPanel1986, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1988.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1988.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1989Layout = new javax.swing.GroupLayout(jPanel1989);
        jPanel1989.setLayout(jPanel1989Layout);
        jPanel1989Layout.setHorizontalGroup(
            jPanel1989Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1989Layout.setVerticalGroup(
            jPanel1989Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1988.add(jPanel1989, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1990.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1990.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1991Layout = new javax.swing.GroupLayout(jPanel1991);
        jPanel1991.setLayout(jPanel1991Layout);
        jPanel1991Layout.setHorizontalGroup(
            jPanel1991Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1991Layout.setVerticalGroup(
            jPanel1991Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1990.add(jPanel1991, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1988.add(jPanel1990, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1984.add(jPanel1988, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1976.add(jPanel1984, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1992.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1992.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1993Layout = new javax.swing.GroupLayout(jPanel1993);
        jPanel1993.setLayout(jPanel1993Layout);
        jPanel1993Layout.setHorizontalGroup(
            jPanel1993Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1993Layout.setVerticalGroup(
            jPanel1993Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1992.add(jPanel1993, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1994.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1994.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1995Layout = new javax.swing.GroupLayout(jPanel1995);
        jPanel1995.setLayout(jPanel1995Layout);
        jPanel1995Layout.setHorizontalGroup(
            jPanel1995Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1995Layout.setVerticalGroup(
            jPanel1995Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1994.add(jPanel1995, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1992.add(jPanel1994, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1996.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1996.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1997Layout = new javax.swing.GroupLayout(jPanel1997);
        jPanel1997.setLayout(jPanel1997Layout);
        jPanel1997Layout.setHorizontalGroup(
            jPanel1997Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1997Layout.setVerticalGroup(
            jPanel1997Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1996.add(jPanel1997, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1998.setBackground(new java.awt.Color(41, 57, 80));
        jPanel1998.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1999Layout = new javax.swing.GroupLayout(jPanel1999);
        jPanel1999.setLayout(jPanel1999Layout);
        jPanel1999Layout.setHorizontalGroup(
            jPanel1999Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel1999Layout.setVerticalGroup(
            jPanel1999Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1998.add(jPanel1999, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1996.add(jPanel1998, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel1992.add(jPanel1996, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2000.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2000.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2001Layout = new javax.swing.GroupLayout(jPanel2001);
        jPanel2001.setLayout(jPanel2001Layout);
        jPanel2001Layout.setHorizontalGroup(
            jPanel2001Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2001Layout.setVerticalGroup(
            jPanel2001Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2000.add(jPanel2001, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2002.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2002.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2003Layout = new javax.swing.GroupLayout(jPanel2003);
        jPanel2003.setLayout(jPanel2003Layout);
        jPanel2003Layout.setHorizontalGroup(
            jPanel2003Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2003Layout.setVerticalGroup(
            jPanel2003Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2002.add(jPanel2003, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2000.add(jPanel2002, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2004.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2004.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2005Layout = new javax.swing.GroupLayout(jPanel2005);
        jPanel2005.setLayout(jPanel2005Layout);
        jPanel2005Layout.setHorizontalGroup(
            jPanel2005Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2005Layout.setVerticalGroup(
            jPanel2005Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2004.add(jPanel2005, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2006.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2006.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2007Layout = new javax.swing.GroupLayout(jPanel2007);
        jPanel2007.setLayout(jPanel2007Layout);
        jPanel2007Layout.setHorizontalGroup(
            jPanel2007Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2007Layout.setVerticalGroup(
            jPanel2007Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2006.add(jPanel2007, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2004.add(jPanel2006, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2000.add(jPanel2004, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1992.add(jPanel2000, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1976.add(jPanel1992, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1944.add(jPanel1976, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2008.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2008.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2009Layout = new javax.swing.GroupLayout(jPanel2009);
        jPanel2009.setLayout(jPanel2009Layout);
        jPanel2009Layout.setHorizontalGroup(
            jPanel2009Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2009Layout.setVerticalGroup(
            jPanel2009Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2008.add(jPanel2009, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2010.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2010.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2011Layout = new javax.swing.GroupLayout(jPanel2011);
        jPanel2011.setLayout(jPanel2011Layout);
        jPanel2011Layout.setHorizontalGroup(
            jPanel2011Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2011Layout.setVerticalGroup(
            jPanel2011Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2010.add(jPanel2011, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2008.add(jPanel2010, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2012.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2012.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2013Layout = new javax.swing.GroupLayout(jPanel2013);
        jPanel2013.setLayout(jPanel2013Layout);
        jPanel2013Layout.setHorizontalGroup(
            jPanel2013Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2013Layout.setVerticalGroup(
            jPanel2013Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2012.add(jPanel2013, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2014.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2014.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2015Layout = new javax.swing.GroupLayout(jPanel2015);
        jPanel2015.setLayout(jPanel2015Layout);
        jPanel2015Layout.setHorizontalGroup(
            jPanel2015Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2015Layout.setVerticalGroup(
            jPanel2015Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2014.add(jPanel2015, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2012.add(jPanel2014, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2008.add(jPanel2012, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2016.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2016.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2017Layout = new javax.swing.GroupLayout(jPanel2017);
        jPanel2017.setLayout(jPanel2017Layout);
        jPanel2017Layout.setHorizontalGroup(
            jPanel2017Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2017Layout.setVerticalGroup(
            jPanel2017Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2016.add(jPanel2017, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2018.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2018.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2019Layout = new javax.swing.GroupLayout(jPanel2019);
        jPanel2019.setLayout(jPanel2019Layout);
        jPanel2019Layout.setHorizontalGroup(
            jPanel2019Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2019Layout.setVerticalGroup(
            jPanel2019Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2018.add(jPanel2019, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2016.add(jPanel2018, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2020.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2020.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2021Layout = new javax.swing.GroupLayout(jPanel2021);
        jPanel2021.setLayout(jPanel2021Layout);
        jPanel2021Layout.setHorizontalGroup(
            jPanel2021Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2021Layout.setVerticalGroup(
            jPanel2021Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2020.add(jPanel2021, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2022.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2022.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2023Layout = new javax.swing.GroupLayout(jPanel2023);
        jPanel2023.setLayout(jPanel2023Layout);
        jPanel2023Layout.setHorizontalGroup(
            jPanel2023Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2023Layout.setVerticalGroup(
            jPanel2023Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2022.add(jPanel2023, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2020.add(jPanel2022, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2016.add(jPanel2020, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2008.add(jPanel2016, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2024.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2024.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2025Layout = new javax.swing.GroupLayout(jPanel2025);
        jPanel2025.setLayout(jPanel2025Layout);
        jPanel2025Layout.setHorizontalGroup(
            jPanel2025Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2025Layout.setVerticalGroup(
            jPanel2025Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2024.add(jPanel2025, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2026.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2026.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2027Layout = new javax.swing.GroupLayout(jPanel2027);
        jPanel2027.setLayout(jPanel2027Layout);
        jPanel2027Layout.setHorizontalGroup(
            jPanel2027Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2027Layout.setVerticalGroup(
            jPanel2027Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2026.add(jPanel2027, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2024.add(jPanel2026, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2028.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2028.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2029Layout = new javax.swing.GroupLayout(jPanel2029);
        jPanel2029.setLayout(jPanel2029Layout);
        jPanel2029Layout.setHorizontalGroup(
            jPanel2029Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2029Layout.setVerticalGroup(
            jPanel2029Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2028.add(jPanel2029, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2030.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2030.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2031Layout = new javax.swing.GroupLayout(jPanel2031);
        jPanel2031.setLayout(jPanel2031Layout);
        jPanel2031Layout.setHorizontalGroup(
            jPanel2031Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2031Layout.setVerticalGroup(
            jPanel2031Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2030.add(jPanel2031, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2028.add(jPanel2030, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2024.add(jPanel2028, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2032.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2032.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2033Layout = new javax.swing.GroupLayout(jPanel2033);
        jPanel2033.setLayout(jPanel2033Layout);
        jPanel2033Layout.setHorizontalGroup(
            jPanel2033Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2033Layout.setVerticalGroup(
            jPanel2033Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2032.add(jPanel2033, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2034.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2034.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2035Layout = new javax.swing.GroupLayout(jPanel2035);
        jPanel2035.setLayout(jPanel2035Layout);
        jPanel2035Layout.setHorizontalGroup(
            jPanel2035Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2035Layout.setVerticalGroup(
            jPanel2035Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2034.add(jPanel2035, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2032.add(jPanel2034, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2036.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2036.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2037Layout = new javax.swing.GroupLayout(jPanel2037);
        jPanel2037.setLayout(jPanel2037Layout);
        jPanel2037Layout.setHorizontalGroup(
            jPanel2037Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2037Layout.setVerticalGroup(
            jPanel2037Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2036.add(jPanel2037, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2038.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2038.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2039Layout = new javax.swing.GroupLayout(jPanel2039);
        jPanel2039.setLayout(jPanel2039Layout);
        jPanel2039Layout.setHorizontalGroup(
            jPanel2039Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2039Layout.setVerticalGroup(
            jPanel2039Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2038.add(jPanel2039, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2036.add(jPanel2038, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2032.add(jPanel2036, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2024.add(jPanel2032, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2008.add(jPanel2024, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2040.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2040.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2041Layout = new javax.swing.GroupLayout(jPanel2041);
        jPanel2041.setLayout(jPanel2041Layout);
        jPanel2041Layout.setHorizontalGroup(
            jPanel2041Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2041Layout.setVerticalGroup(
            jPanel2041Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2040.add(jPanel2041, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2042.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2042.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2043Layout = new javax.swing.GroupLayout(jPanel2043);
        jPanel2043.setLayout(jPanel2043Layout);
        jPanel2043Layout.setHorizontalGroup(
            jPanel2043Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2043Layout.setVerticalGroup(
            jPanel2043Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2042.add(jPanel2043, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2040.add(jPanel2042, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2044.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2044.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2045Layout = new javax.swing.GroupLayout(jPanel2045);
        jPanel2045.setLayout(jPanel2045Layout);
        jPanel2045Layout.setHorizontalGroup(
            jPanel2045Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2045Layout.setVerticalGroup(
            jPanel2045Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2044.add(jPanel2045, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2046.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2046.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2047Layout = new javax.swing.GroupLayout(jPanel2047);
        jPanel2047.setLayout(jPanel2047Layout);
        jPanel2047Layout.setHorizontalGroup(
            jPanel2047Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2047Layout.setVerticalGroup(
            jPanel2047Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2046.add(jPanel2047, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2044.add(jPanel2046, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2040.add(jPanel2044, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2048.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2048.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2049Layout = new javax.swing.GroupLayout(jPanel2049);
        jPanel2049.setLayout(jPanel2049Layout);
        jPanel2049Layout.setHorizontalGroup(
            jPanel2049Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2049Layout.setVerticalGroup(
            jPanel2049Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2048.add(jPanel2049, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2050.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2050.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2051Layout = new javax.swing.GroupLayout(jPanel2051);
        jPanel2051.setLayout(jPanel2051Layout);
        jPanel2051Layout.setHorizontalGroup(
            jPanel2051Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2051Layout.setVerticalGroup(
            jPanel2051Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2050.add(jPanel2051, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2048.add(jPanel2050, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2052.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2052.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2053Layout = new javax.swing.GroupLayout(jPanel2053);
        jPanel2053.setLayout(jPanel2053Layout);
        jPanel2053Layout.setHorizontalGroup(
            jPanel2053Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2053Layout.setVerticalGroup(
            jPanel2053Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2052.add(jPanel2053, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2054.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2054.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2055Layout = new javax.swing.GroupLayout(jPanel2055);
        jPanel2055.setLayout(jPanel2055Layout);
        jPanel2055Layout.setHorizontalGroup(
            jPanel2055Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2055Layout.setVerticalGroup(
            jPanel2055Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2054.add(jPanel2055, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2052.add(jPanel2054, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2048.add(jPanel2052, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2040.add(jPanel2048, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2056.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2056.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2057Layout = new javax.swing.GroupLayout(jPanel2057);
        jPanel2057.setLayout(jPanel2057Layout);
        jPanel2057Layout.setHorizontalGroup(
            jPanel2057Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2057Layout.setVerticalGroup(
            jPanel2057Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2056.add(jPanel2057, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2058.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2058.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2059Layout = new javax.swing.GroupLayout(jPanel2059);
        jPanel2059.setLayout(jPanel2059Layout);
        jPanel2059Layout.setHorizontalGroup(
            jPanel2059Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2059Layout.setVerticalGroup(
            jPanel2059Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2058.add(jPanel2059, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2056.add(jPanel2058, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2060.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2060.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2061Layout = new javax.swing.GroupLayout(jPanel2061);
        jPanel2061.setLayout(jPanel2061Layout);
        jPanel2061Layout.setHorizontalGroup(
            jPanel2061Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2061Layout.setVerticalGroup(
            jPanel2061Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2060.add(jPanel2061, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2062.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2062.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2063Layout = new javax.swing.GroupLayout(jPanel2063);
        jPanel2063.setLayout(jPanel2063Layout);
        jPanel2063Layout.setHorizontalGroup(
            jPanel2063Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2063Layout.setVerticalGroup(
            jPanel2063Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2062.add(jPanel2063, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2060.add(jPanel2062, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2056.add(jPanel2060, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2064.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2064.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2065Layout = new javax.swing.GroupLayout(jPanel2065);
        jPanel2065.setLayout(jPanel2065Layout);
        jPanel2065Layout.setHorizontalGroup(
            jPanel2065Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2065Layout.setVerticalGroup(
            jPanel2065Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2064.add(jPanel2065, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2066.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2066.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2067Layout = new javax.swing.GroupLayout(jPanel2067);
        jPanel2067.setLayout(jPanel2067Layout);
        jPanel2067Layout.setHorizontalGroup(
            jPanel2067Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2067Layout.setVerticalGroup(
            jPanel2067Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2066.add(jPanel2067, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2064.add(jPanel2066, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2068.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2068.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2069Layout = new javax.swing.GroupLayout(jPanel2069);
        jPanel2069.setLayout(jPanel2069Layout);
        jPanel2069Layout.setHorizontalGroup(
            jPanel2069Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2069Layout.setVerticalGroup(
            jPanel2069Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2068.add(jPanel2069, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2070.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2070.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2071Layout = new javax.swing.GroupLayout(jPanel2071);
        jPanel2071.setLayout(jPanel2071Layout);
        jPanel2071Layout.setHorizontalGroup(
            jPanel2071Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2071Layout.setVerticalGroup(
            jPanel2071Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2070.add(jPanel2071, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2068.add(jPanel2070, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2064.add(jPanel2068, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2056.add(jPanel2064, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2040.add(jPanel2056, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2008.add(jPanel2040, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel1944.add(jPanel2008, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel2072.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2072.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2073Layout = new javax.swing.GroupLayout(jPanel2073);
        jPanel2073.setLayout(jPanel2073Layout);
        jPanel2073Layout.setHorizontalGroup(
            jPanel2073Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2073Layout.setVerticalGroup(
            jPanel2073Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2072.add(jPanel2073, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2074.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2074.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2075Layout = new javax.swing.GroupLayout(jPanel2075);
        jPanel2075.setLayout(jPanel2075Layout);
        jPanel2075Layout.setHorizontalGroup(
            jPanel2075Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2075Layout.setVerticalGroup(
            jPanel2075Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2074.add(jPanel2075, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2072.add(jPanel2074, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2076.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2076.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2077Layout = new javax.swing.GroupLayout(jPanel2077);
        jPanel2077.setLayout(jPanel2077Layout);
        jPanel2077Layout.setHorizontalGroup(
            jPanel2077Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2077Layout.setVerticalGroup(
            jPanel2077Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2076.add(jPanel2077, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2078.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2078.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2079Layout = new javax.swing.GroupLayout(jPanel2079);
        jPanel2079.setLayout(jPanel2079Layout);
        jPanel2079Layout.setHorizontalGroup(
            jPanel2079Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2079Layout.setVerticalGroup(
            jPanel2079Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2078.add(jPanel2079, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2076.add(jPanel2078, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2072.add(jPanel2076, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2080.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2080.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2081Layout = new javax.swing.GroupLayout(jPanel2081);
        jPanel2081.setLayout(jPanel2081Layout);
        jPanel2081Layout.setHorizontalGroup(
            jPanel2081Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2081Layout.setVerticalGroup(
            jPanel2081Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2080.add(jPanel2081, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2082.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2082.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2083Layout = new javax.swing.GroupLayout(jPanel2083);
        jPanel2083.setLayout(jPanel2083Layout);
        jPanel2083Layout.setHorizontalGroup(
            jPanel2083Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2083Layout.setVerticalGroup(
            jPanel2083Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2082.add(jPanel2083, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2080.add(jPanel2082, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2084.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2084.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2085Layout = new javax.swing.GroupLayout(jPanel2085);
        jPanel2085.setLayout(jPanel2085Layout);
        jPanel2085Layout.setHorizontalGroup(
            jPanel2085Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2085Layout.setVerticalGroup(
            jPanel2085Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2084.add(jPanel2085, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2086.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2086.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2087Layout = new javax.swing.GroupLayout(jPanel2087);
        jPanel2087.setLayout(jPanel2087Layout);
        jPanel2087Layout.setHorizontalGroup(
            jPanel2087Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2087Layout.setVerticalGroup(
            jPanel2087Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2086.add(jPanel2087, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2084.add(jPanel2086, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2080.add(jPanel2084, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2072.add(jPanel2080, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2088.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2088.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2089Layout = new javax.swing.GroupLayout(jPanel2089);
        jPanel2089.setLayout(jPanel2089Layout);
        jPanel2089Layout.setHorizontalGroup(
            jPanel2089Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2089Layout.setVerticalGroup(
            jPanel2089Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2088.add(jPanel2089, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2090.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2090.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2091Layout = new javax.swing.GroupLayout(jPanel2091);
        jPanel2091.setLayout(jPanel2091Layout);
        jPanel2091Layout.setHorizontalGroup(
            jPanel2091Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2091Layout.setVerticalGroup(
            jPanel2091Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2090.add(jPanel2091, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2088.add(jPanel2090, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2092.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2092.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2093Layout = new javax.swing.GroupLayout(jPanel2093);
        jPanel2093.setLayout(jPanel2093Layout);
        jPanel2093Layout.setHorizontalGroup(
            jPanel2093Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2093Layout.setVerticalGroup(
            jPanel2093Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2092.add(jPanel2093, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2094.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2094.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2095Layout = new javax.swing.GroupLayout(jPanel2095);
        jPanel2095.setLayout(jPanel2095Layout);
        jPanel2095Layout.setHorizontalGroup(
            jPanel2095Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2095Layout.setVerticalGroup(
            jPanel2095Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2094.add(jPanel2095, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2092.add(jPanel2094, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2088.add(jPanel2092, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2096.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2096.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2097Layout = new javax.swing.GroupLayout(jPanel2097);
        jPanel2097.setLayout(jPanel2097Layout);
        jPanel2097Layout.setHorizontalGroup(
            jPanel2097Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2097Layout.setVerticalGroup(
            jPanel2097Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2096.add(jPanel2097, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2098.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2098.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2099Layout = new javax.swing.GroupLayout(jPanel2099);
        jPanel2099.setLayout(jPanel2099Layout);
        jPanel2099Layout.setHorizontalGroup(
            jPanel2099Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2099Layout.setVerticalGroup(
            jPanel2099Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2098.add(jPanel2099, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2096.add(jPanel2098, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2100.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2100.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2101Layout = new javax.swing.GroupLayout(jPanel2101);
        jPanel2101.setLayout(jPanel2101Layout);
        jPanel2101Layout.setHorizontalGroup(
            jPanel2101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2101Layout.setVerticalGroup(
            jPanel2101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2100.add(jPanel2101, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2102.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2102.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2103Layout = new javax.swing.GroupLayout(jPanel2103);
        jPanel2103.setLayout(jPanel2103Layout);
        jPanel2103Layout.setHorizontalGroup(
            jPanel2103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2103Layout.setVerticalGroup(
            jPanel2103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2102.add(jPanel2103, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2100.add(jPanel2102, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2096.add(jPanel2100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2088.add(jPanel2096, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2072.add(jPanel2088, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2104.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2104.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2105Layout = new javax.swing.GroupLayout(jPanel2105);
        jPanel2105.setLayout(jPanel2105Layout);
        jPanel2105Layout.setHorizontalGroup(
            jPanel2105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2105Layout.setVerticalGroup(
            jPanel2105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2104.add(jPanel2105, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2106.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2106.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2107Layout = new javax.swing.GroupLayout(jPanel2107);
        jPanel2107.setLayout(jPanel2107Layout);
        jPanel2107Layout.setHorizontalGroup(
            jPanel2107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2107Layout.setVerticalGroup(
            jPanel2107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2106.add(jPanel2107, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2104.add(jPanel2106, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2108.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2108.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2109Layout = new javax.swing.GroupLayout(jPanel2109);
        jPanel2109.setLayout(jPanel2109Layout);
        jPanel2109Layout.setHorizontalGroup(
            jPanel2109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2109Layout.setVerticalGroup(
            jPanel2109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2108.add(jPanel2109, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2110.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2110.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2111Layout = new javax.swing.GroupLayout(jPanel2111);
        jPanel2111.setLayout(jPanel2111Layout);
        jPanel2111Layout.setHorizontalGroup(
            jPanel2111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2111Layout.setVerticalGroup(
            jPanel2111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2110.add(jPanel2111, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2108.add(jPanel2110, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2104.add(jPanel2108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2112.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2112.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2113Layout = new javax.swing.GroupLayout(jPanel2113);
        jPanel2113.setLayout(jPanel2113Layout);
        jPanel2113Layout.setHorizontalGroup(
            jPanel2113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2113Layout.setVerticalGroup(
            jPanel2113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2112.add(jPanel2113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2114.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2114.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2115Layout = new javax.swing.GroupLayout(jPanel2115);
        jPanel2115.setLayout(jPanel2115Layout);
        jPanel2115Layout.setHorizontalGroup(
            jPanel2115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2115Layout.setVerticalGroup(
            jPanel2115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2114.add(jPanel2115, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2112.add(jPanel2114, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2116.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2116.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2117Layout = new javax.swing.GroupLayout(jPanel2117);
        jPanel2117.setLayout(jPanel2117Layout);
        jPanel2117Layout.setHorizontalGroup(
            jPanel2117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2117Layout.setVerticalGroup(
            jPanel2117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2116.add(jPanel2117, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2118.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2118.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2119Layout = new javax.swing.GroupLayout(jPanel2119);
        jPanel2119.setLayout(jPanel2119Layout);
        jPanel2119Layout.setHorizontalGroup(
            jPanel2119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2119Layout.setVerticalGroup(
            jPanel2119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2118.add(jPanel2119, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2116.add(jPanel2118, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2112.add(jPanel2116, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2104.add(jPanel2112, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2120.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2120.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2121Layout = new javax.swing.GroupLayout(jPanel2121);
        jPanel2121.setLayout(jPanel2121Layout);
        jPanel2121Layout.setHorizontalGroup(
            jPanel2121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2121Layout.setVerticalGroup(
            jPanel2121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2120.add(jPanel2121, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2122.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2122.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2123Layout = new javax.swing.GroupLayout(jPanel2123);
        jPanel2123.setLayout(jPanel2123Layout);
        jPanel2123Layout.setHorizontalGroup(
            jPanel2123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2123Layout.setVerticalGroup(
            jPanel2123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2122.add(jPanel2123, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2120.add(jPanel2122, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2124.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2124.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2125Layout = new javax.swing.GroupLayout(jPanel2125);
        jPanel2125.setLayout(jPanel2125Layout);
        jPanel2125Layout.setHorizontalGroup(
            jPanel2125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2125Layout.setVerticalGroup(
            jPanel2125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2124.add(jPanel2125, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2126.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2126.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2127Layout = new javax.swing.GroupLayout(jPanel2127);
        jPanel2127.setLayout(jPanel2127Layout);
        jPanel2127Layout.setHorizontalGroup(
            jPanel2127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2127Layout.setVerticalGroup(
            jPanel2127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2126.add(jPanel2127, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2124.add(jPanel2126, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2120.add(jPanel2124, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2128.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2128.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2129Layout = new javax.swing.GroupLayout(jPanel2129);
        jPanel2129.setLayout(jPanel2129Layout);
        jPanel2129Layout.setHorizontalGroup(
            jPanel2129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2129Layout.setVerticalGroup(
            jPanel2129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2128.add(jPanel2129, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2130.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2130.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2131Layout = new javax.swing.GroupLayout(jPanel2131);
        jPanel2131.setLayout(jPanel2131Layout);
        jPanel2131Layout.setHorizontalGroup(
            jPanel2131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2131Layout.setVerticalGroup(
            jPanel2131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2130.add(jPanel2131, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2128.add(jPanel2130, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2132.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2132.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2133Layout = new javax.swing.GroupLayout(jPanel2133);
        jPanel2133.setLayout(jPanel2133Layout);
        jPanel2133Layout.setHorizontalGroup(
            jPanel2133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2133Layout.setVerticalGroup(
            jPanel2133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2132.add(jPanel2133, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2134.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2134.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2135Layout = new javax.swing.GroupLayout(jPanel2135);
        jPanel2135.setLayout(jPanel2135Layout);
        jPanel2135Layout.setHorizontalGroup(
            jPanel2135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2135Layout.setVerticalGroup(
            jPanel2135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2134.add(jPanel2135, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2132.add(jPanel2134, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2128.add(jPanel2132, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2120.add(jPanel2128, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2104.add(jPanel2120, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2072.add(jPanel2104, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2136.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2136.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2137Layout = new javax.swing.GroupLayout(jPanel2137);
        jPanel2137.setLayout(jPanel2137Layout);
        jPanel2137Layout.setHorizontalGroup(
            jPanel2137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2137Layout.setVerticalGroup(
            jPanel2137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2136.add(jPanel2137, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2138.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2138.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2139Layout = new javax.swing.GroupLayout(jPanel2139);
        jPanel2139.setLayout(jPanel2139Layout);
        jPanel2139Layout.setHorizontalGroup(
            jPanel2139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2139Layout.setVerticalGroup(
            jPanel2139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2138.add(jPanel2139, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2136.add(jPanel2138, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2140.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2140.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2141Layout = new javax.swing.GroupLayout(jPanel2141);
        jPanel2141.setLayout(jPanel2141Layout);
        jPanel2141Layout.setHorizontalGroup(
            jPanel2141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2141Layout.setVerticalGroup(
            jPanel2141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2140.add(jPanel2141, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2142.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2142.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2143Layout = new javax.swing.GroupLayout(jPanel2143);
        jPanel2143.setLayout(jPanel2143Layout);
        jPanel2143Layout.setHorizontalGroup(
            jPanel2143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2143Layout.setVerticalGroup(
            jPanel2143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2142.add(jPanel2143, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2140.add(jPanel2142, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2136.add(jPanel2140, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2144.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2144.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2145Layout = new javax.swing.GroupLayout(jPanel2145);
        jPanel2145.setLayout(jPanel2145Layout);
        jPanel2145Layout.setHorizontalGroup(
            jPanel2145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2145Layout.setVerticalGroup(
            jPanel2145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2144.add(jPanel2145, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2146.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2146.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2147Layout = new javax.swing.GroupLayout(jPanel2147);
        jPanel2147.setLayout(jPanel2147Layout);
        jPanel2147Layout.setHorizontalGroup(
            jPanel2147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2147Layout.setVerticalGroup(
            jPanel2147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2146.add(jPanel2147, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2144.add(jPanel2146, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2148.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2148.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2149Layout = new javax.swing.GroupLayout(jPanel2149);
        jPanel2149.setLayout(jPanel2149Layout);
        jPanel2149Layout.setHorizontalGroup(
            jPanel2149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2149Layout.setVerticalGroup(
            jPanel2149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2148.add(jPanel2149, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2150.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2150.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2151Layout = new javax.swing.GroupLayout(jPanel2151);
        jPanel2151.setLayout(jPanel2151Layout);
        jPanel2151Layout.setHorizontalGroup(
            jPanel2151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2151Layout.setVerticalGroup(
            jPanel2151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2150.add(jPanel2151, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2148.add(jPanel2150, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2144.add(jPanel2148, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2136.add(jPanel2144, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2152.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2152.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2153Layout = new javax.swing.GroupLayout(jPanel2153);
        jPanel2153.setLayout(jPanel2153Layout);
        jPanel2153Layout.setHorizontalGroup(
            jPanel2153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2153Layout.setVerticalGroup(
            jPanel2153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2152.add(jPanel2153, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2154.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2154.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2155Layout = new javax.swing.GroupLayout(jPanel2155);
        jPanel2155.setLayout(jPanel2155Layout);
        jPanel2155Layout.setHorizontalGroup(
            jPanel2155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2155Layout.setVerticalGroup(
            jPanel2155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2154.add(jPanel2155, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2152.add(jPanel2154, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2156.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2156.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2157Layout = new javax.swing.GroupLayout(jPanel2157);
        jPanel2157.setLayout(jPanel2157Layout);
        jPanel2157Layout.setHorizontalGroup(
            jPanel2157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2157Layout.setVerticalGroup(
            jPanel2157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2156.add(jPanel2157, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2158.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2158.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2159Layout = new javax.swing.GroupLayout(jPanel2159);
        jPanel2159.setLayout(jPanel2159Layout);
        jPanel2159Layout.setHorizontalGroup(
            jPanel2159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2159Layout.setVerticalGroup(
            jPanel2159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2158.add(jPanel2159, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2156.add(jPanel2158, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2152.add(jPanel2156, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2160.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2160.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2161Layout = new javax.swing.GroupLayout(jPanel2161);
        jPanel2161.setLayout(jPanel2161Layout);
        jPanel2161Layout.setHorizontalGroup(
            jPanel2161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2161Layout.setVerticalGroup(
            jPanel2161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2160.add(jPanel2161, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2162.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2162.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2163Layout = new javax.swing.GroupLayout(jPanel2163);
        jPanel2163.setLayout(jPanel2163Layout);
        jPanel2163Layout.setHorizontalGroup(
            jPanel2163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2163Layout.setVerticalGroup(
            jPanel2163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2162.add(jPanel2163, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2160.add(jPanel2162, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2164.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2164.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2165Layout = new javax.swing.GroupLayout(jPanel2165);
        jPanel2165.setLayout(jPanel2165Layout);
        jPanel2165Layout.setHorizontalGroup(
            jPanel2165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2165Layout.setVerticalGroup(
            jPanel2165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2164.add(jPanel2165, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2166.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2166.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2167Layout = new javax.swing.GroupLayout(jPanel2167);
        jPanel2167.setLayout(jPanel2167Layout);
        jPanel2167Layout.setHorizontalGroup(
            jPanel2167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2167Layout.setVerticalGroup(
            jPanel2167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2166.add(jPanel2167, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2164.add(jPanel2166, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2160.add(jPanel2164, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2152.add(jPanel2160, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2136.add(jPanel2152, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2168.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2168.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2169Layout = new javax.swing.GroupLayout(jPanel2169);
        jPanel2169.setLayout(jPanel2169Layout);
        jPanel2169Layout.setHorizontalGroup(
            jPanel2169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2169Layout.setVerticalGroup(
            jPanel2169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2168.add(jPanel2169, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2170.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2170.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2171Layout = new javax.swing.GroupLayout(jPanel2171);
        jPanel2171.setLayout(jPanel2171Layout);
        jPanel2171Layout.setHorizontalGroup(
            jPanel2171Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2171Layout.setVerticalGroup(
            jPanel2171Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2170.add(jPanel2171, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2168.add(jPanel2170, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2172.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2172.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2173Layout = new javax.swing.GroupLayout(jPanel2173);
        jPanel2173.setLayout(jPanel2173Layout);
        jPanel2173Layout.setHorizontalGroup(
            jPanel2173Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2173Layout.setVerticalGroup(
            jPanel2173Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2172.add(jPanel2173, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2174.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2174.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2175Layout = new javax.swing.GroupLayout(jPanel2175);
        jPanel2175.setLayout(jPanel2175Layout);
        jPanel2175Layout.setHorizontalGroup(
            jPanel2175Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2175Layout.setVerticalGroup(
            jPanel2175Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2174.add(jPanel2175, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2172.add(jPanel2174, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2168.add(jPanel2172, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2176.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2176.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2177Layout = new javax.swing.GroupLayout(jPanel2177);
        jPanel2177.setLayout(jPanel2177Layout);
        jPanel2177Layout.setHorizontalGroup(
            jPanel2177Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2177Layout.setVerticalGroup(
            jPanel2177Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2176.add(jPanel2177, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2178.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2178.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2179Layout = new javax.swing.GroupLayout(jPanel2179);
        jPanel2179.setLayout(jPanel2179Layout);
        jPanel2179Layout.setHorizontalGroup(
            jPanel2179Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2179Layout.setVerticalGroup(
            jPanel2179Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2178.add(jPanel2179, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2176.add(jPanel2178, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2180.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2180.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2181Layout = new javax.swing.GroupLayout(jPanel2181);
        jPanel2181.setLayout(jPanel2181Layout);
        jPanel2181Layout.setHorizontalGroup(
            jPanel2181Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2181Layout.setVerticalGroup(
            jPanel2181Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2180.add(jPanel2181, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2182.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2182.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2183Layout = new javax.swing.GroupLayout(jPanel2183);
        jPanel2183.setLayout(jPanel2183Layout);
        jPanel2183Layout.setHorizontalGroup(
            jPanel2183Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2183Layout.setVerticalGroup(
            jPanel2183Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2182.add(jPanel2183, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2180.add(jPanel2182, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2176.add(jPanel2180, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2168.add(jPanel2176, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2184.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2184.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2185Layout = new javax.swing.GroupLayout(jPanel2185);
        jPanel2185.setLayout(jPanel2185Layout);
        jPanel2185Layout.setHorizontalGroup(
            jPanel2185Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2185Layout.setVerticalGroup(
            jPanel2185Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2184.add(jPanel2185, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2186.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2186.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2187Layout = new javax.swing.GroupLayout(jPanel2187);
        jPanel2187.setLayout(jPanel2187Layout);
        jPanel2187Layout.setHorizontalGroup(
            jPanel2187Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2187Layout.setVerticalGroup(
            jPanel2187Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2186.add(jPanel2187, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2184.add(jPanel2186, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2188.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2188.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2189Layout = new javax.swing.GroupLayout(jPanel2189);
        jPanel2189.setLayout(jPanel2189Layout);
        jPanel2189Layout.setHorizontalGroup(
            jPanel2189Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2189Layout.setVerticalGroup(
            jPanel2189Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2188.add(jPanel2189, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2190.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2190.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2191Layout = new javax.swing.GroupLayout(jPanel2191);
        jPanel2191.setLayout(jPanel2191Layout);
        jPanel2191Layout.setHorizontalGroup(
            jPanel2191Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2191Layout.setVerticalGroup(
            jPanel2191Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2190.add(jPanel2191, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2188.add(jPanel2190, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2184.add(jPanel2188, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2192.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2192.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2193Layout = new javax.swing.GroupLayout(jPanel2193);
        jPanel2193.setLayout(jPanel2193Layout);
        jPanel2193Layout.setHorizontalGroup(
            jPanel2193Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2193Layout.setVerticalGroup(
            jPanel2193Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2192.add(jPanel2193, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2194.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2194.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2195Layout = new javax.swing.GroupLayout(jPanel2195);
        jPanel2195.setLayout(jPanel2195Layout);
        jPanel2195Layout.setHorizontalGroup(
            jPanel2195Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2195Layout.setVerticalGroup(
            jPanel2195Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2194.add(jPanel2195, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2192.add(jPanel2194, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2196.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2196.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2197Layout = new javax.swing.GroupLayout(jPanel2197);
        jPanel2197.setLayout(jPanel2197Layout);
        jPanel2197Layout.setHorizontalGroup(
            jPanel2197Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2197Layout.setVerticalGroup(
            jPanel2197Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2196.add(jPanel2197, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2198.setBackground(new java.awt.Color(41, 57, 80));
        jPanel2198.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2199Layout = new javax.swing.GroupLayout(jPanel2199);
        jPanel2199.setLayout(jPanel2199Layout);
        jPanel2199Layout.setHorizontalGroup(
            jPanel2199Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel2199Layout.setVerticalGroup(
            jPanel2199Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2198.add(jPanel2199, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2196.add(jPanel2198, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        jPanel2192.add(jPanel2196, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2184.add(jPanel2192, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2168.add(jPanel2184, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2136.add(jPanel2168, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 130, 30));

        jPanel2072.add(jPanel2136, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel1944.add(jPanel2072, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        jPanel1688.add(jPanel1944, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 130, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-land-sales-20.png"))); // NOI18N
        jLabel12.setText("6\" Sale");
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
        jLabel14.setText("Party");
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
        jLabel16.setText("Raw Matirial");
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

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cloud-backup-restore-20.png"))); // NOI18N
        jLabel20.setText("Backup");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(bill_6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        side_panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 130, 310));

        getContentPane().add(side_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 700));

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SAMARTH VEET UDYOG");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Start Date");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 20));

        jDateChooser2.setDateFormatString("yyyy-MM-d");
        jPanel3.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("End Date");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 90, 20));

        jDateChooser3.setDateFormatString("yyyy-MM-d");
        jPanel3.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 120, 30));

        button1.setBackground(new java.awt.Color(71, 120, 197));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Select");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel3.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 70, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -10, -1, 60));

        jPanel4.setBackground(new java.awt.Color(71, 120, 197));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(120, 168, 252));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Production");

        jTextField6.setBackground(new java.awt.Color(71, 120, 197));
        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTextField6.setPreferredSize(new java.awt.Dimension(2, 20));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-18 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        jPanel6.setBackground(new java.awt.Color(84, 127, 206));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Date");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 60, 20));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 140, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("4\" VEET");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 90, 20));

        jDateChooser1.setDateFormatString("yyyy-MM-d");
        jPanel6.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 140, -1));
        jPanel6.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 140, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("6\" VEET");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 90, 20));

        button3.setBackground(new java.awt.Color(71, 120, 197));
        button3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("Save details\n");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel6.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 120, 40));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 400, 290));

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 150, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ENTER ID ");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 110, -1));

        button2.setBackground(new java.awt.Color(84, 127, 206));
        button2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Delete details");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel4.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 140, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 400, 650));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
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
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(51, 51, 51));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel8.setBackground(new java.awt.Color(120, 168, 252));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField4_sum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField4_sum.setForeground(new java.awt.Color(51, 204, 0));
        jTextField4_sum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4_sumActionPerformed(evt);
            }
        });
        jTextField4_sum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4_sumKeyTyped(evt);
            }
        });
        jPanel8.add(jTextField4_sum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, 40));

        sixsum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sixsum.setForeground(new java.awt.Color(255, 51, 0));
        sixsum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixsumActionPerformed(evt);
            }
        });
        jPanel8.add(sixsum, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 110, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Total 4\" Veet");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total 6\" Veet");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 120, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 830, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
       String query = jTextField6.getText().toLowerCase();
        filter(query);
        fourinchsum();
        sixinchsum();
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        String selection = jTable1.getModel().getValueAt(row, 0).toString();
        jTextField7.setText(selection);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4_sumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4_sumActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField4_sumActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void sixsumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixsumActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_sixsumActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String id = jTextField7.getText();
        try 
        {
             String sql = "delete from production where pid="+id+"";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Record Deleted");
        } 
        catch (Exception ex) 
        {
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        loadData();
        fourinchsum();
        sixinchsum();
        
        
        jTextField7.setText(null);
        
    }//GEN-LAST:event_button2ActionPerformed

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here
        jLabel7.setForeground(Color.RED);
        
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here
         Manufacturing obj = null;
        try {
            obj = new Manufacturing();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manufacturing.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel7MouseClicked

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

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        // TODO add your handling code here:
        jLabel12.setForeground(new Color(255,255,0));
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jLabel12.setForeground(Color.WHITE);
    }//GEN-LAST:event_jLabel12MouseExited

    private void bill_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseEntered
        // TODO add your handling code here
        bill_4.setForeground(Color.GREEN);
    }//GEN-LAST:event_bill_4MouseEntered

    private void bill_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseExited
        // TODO add your handling code here
          bill_4.setForeground(Color.WHITE);
    }//GEN-LAST:event_bill_4MouseExited

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
                jLabel14.setForeground(new Color(46,43,95));

    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here
                jLabel14.setForeground(new Color(255,255,255));

    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here
                jLabel17.setForeground(new Color(139,0,255));

    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
                jLabel17.setForeground(new Color(255,255,255));

    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here
                jLabel16.setForeground(new Color(255,0,0));

    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // TODO add your handling code here
                jLabel16.setForeground(new Color(255,255,255));

    }//GEN-LAST:event_jLabel16MouseExited

    private void bill_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bill_4MousePressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
         sales obj = null;
        try {
            obj = new sales();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manufacturing.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
         sixinchsale obj = null;
        try {
            obj = new sixinchsale();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manufacturing.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel12MouseClicked

    private void bill_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_4MouseClicked
        // TODO add your handling code here:
         fourinchbill obj;
        try {
            obj = new fourinchbill();
            obj.setVisible(true);
            this.dispose();
        } catch (PrinterException ex) {
            Logger.getLogger(sixinchbill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manufacturing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bill_4MouseClicked

    private void jTextField4_sumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4_sumKeyTyped
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jTextField4_sumKeyTyped

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

    private void bill_6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseExited
        // TODO add your handling code here
        bill_6.setForeground(Color.WHITE);
    }//GEN-LAST:event_bill_6MouseExited

    private void bill_6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseEntered
        // TODO add your handling code here
        bill_6.setForeground(Color.BLUE);
    }//GEN-LAST:event_bill_6MouseEntered

    private void bill_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_6MouseClicked
        // TODO add your handling code here:
         sixinchbill obj = null;
        try {
            obj = new sixinchbill();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sixinchsale.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_bill_6MouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
       // float A =  (float) Double.parseDouble(jTextField2.getText());
        //float B =  (float) Double.parseDouble(prb_8.getText());
        //float c = A*B;
       // this.jTextField8.setText(""+c);
       // float f =  (float) Double.parseDouble(jTextField4.getText());
        //float g =  (float) Double.parseDouble(jTextField3.getText());
        //float h = g*f;
        //this.jTextField9.setText(""+h);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(jDateChooser1.getDate());
        String Lastname =jTextField2.getText();
        String Class =jTextField4.getText();
        //String prb_4 =prb_8.getText();
        // String prb_6 =jTextField3.getText();
         // String amt_4 =jTextField8.getText();
           //String amt_6 =jTextField9.getText();
        //double prb_4 = Double.parseDouble(prb_8.getText());
       // double prb_6 =  Double.parseDouble(jTextField3.getText());
       // int amt_4 =   (int) Double.parseDouble(jTextField8.getText());
       // int amt_6 =   (int)Double.parseDouble(jTextField9.getText());
       //double amt_4 =   Double.parseDouble(jTextField8.getText());
       // double amt_6 =   Double.parseDouble(jTextField9.getText());
        try
        {
            String sql ="insert into production(pdate,fourinchproduction,sixinchproduction)"
            + "values('"+d+"','"+Lastname+"','"+Class+"')";
            stmt.executeUpdate(sql);
            

        }
        catch( SQLException ex)
        {
            JOptionPane.showMessageDialog(this, ex);
        }
        loadData();
        fourinchsum();
        sixinchsum();
        
        jDateChooser1.setDate(null);
        jTextField2.setText(null);
        
        jTextField4.setText(null);
        jTextField7.setText(null);
        

       
    }//GEN-LAST:event_button3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
         try {
              loadDate();
              fourinchsum();
              sixinchsum();
        jDateChooser2.setDate(null);
         jDateChooser3.setDate(null);
        
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Select Date");
        }
      
    
    }//GEN-LAST:event_button1ActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
         raw obj = null;
         obj = new raw();
        obj.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        try {
            exportable();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here
        jLabel16.setForeground(new Color(255,0,0));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here
        jLabel16.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel20MouseExited

    /**
     * @param args the command line arguments// TODO add your handling code here:
    }                                    
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
            java.util.logging.Logger.getLogger(Manufacturing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manufacturing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manufacturing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manufacturing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Manufacturing().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Manufacturing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bill_4;
    private javax.swing.JLabel bill_6;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel1688;
    private javax.swing.JPanel jPanel1689;
    private javax.swing.JPanel jPanel1690;
    private javax.swing.JPanel jPanel1691;
    private javax.swing.JPanel jPanel1692;
    private javax.swing.JPanel jPanel1693;
    private javax.swing.JPanel jPanel1694;
    private javax.swing.JPanel jPanel1695;
    private javax.swing.JPanel jPanel1696;
    private javax.swing.JPanel jPanel1697;
    private javax.swing.JPanel jPanel1698;
    private javax.swing.JPanel jPanel1699;
    private javax.swing.JPanel jPanel1700;
    private javax.swing.JPanel jPanel1701;
    private javax.swing.JPanel jPanel1702;
    private javax.swing.JPanel jPanel1703;
    private javax.swing.JPanel jPanel1704;
    private javax.swing.JPanel jPanel1705;
    private javax.swing.JPanel jPanel1706;
    private javax.swing.JPanel jPanel1707;
    private javax.swing.JPanel jPanel1708;
    private javax.swing.JPanel jPanel1709;
    private javax.swing.JPanel jPanel1710;
    private javax.swing.JPanel jPanel1711;
    private javax.swing.JPanel jPanel1712;
    private javax.swing.JPanel jPanel1713;
    private javax.swing.JPanel jPanel1714;
    private javax.swing.JPanel jPanel1715;
    private javax.swing.JPanel jPanel1716;
    private javax.swing.JPanel jPanel1717;
    private javax.swing.JPanel jPanel1718;
    private javax.swing.JPanel jPanel1719;
    private javax.swing.JPanel jPanel1720;
    private javax.swing.JPanel jPanel1721;
    private javax.swing.JPanel jPanel1722;
    private javax.swing.JPanel jPanel1723;
    private javax.swing.JPanel jPanel1724;
    private javax.swing.JPanel jPanel1725;
    private javax.swing.JPanel jPanel1726;
    private javax.swing.JPanel jPanel1727;
    private javax.swing.JPanel jPanel1728;
    private javax.swing.JPanel jPanel1729;
    private javax.swing.JPanel jPanel1730;
    private javax.swing.JPanel jPanel1731;
    private javax.swing.JPanel jPanel1732;
    private javax.swing.JPanel jPanel1733;
    private javax.swing.JPanel jPanel1734;
    private javax.swing.JPanel jPanel1735;
    private javax.swing.JPanel jPanel1736;
    private javax.swing.JPanel jPanel1737;
    private javax.swing.JPanel jPanel1738;
    private javax.swing.JPanel jPanel1739;
    private javax.swing.JPanel jPanel1740;
    private javax.swing.JPanel jPanel1741;
    private javax.swing.JPanel jPanel1742;
    private javax.swing.JPanel jPanel1743;
    private javax.swing.JPanel jPanel1744;
    private javax.swing.JPanel jPanel1745;
    private javax.swing.JPanel jPanel1746;
    private javax.swing.JPanel jPanel1747;
    private javax.swing.JPanel jPanel1748;
    private javax.swing.JPanel jPanel1749;
    private javax.swing.JPanel jPanel1750;
    private javax.swing.JPanel jPanel1751;
    private javax.swing.JPanel jPanel1752;
    private javax.swing.JPanel jPanel1753;
    private javax.swing.JPanel jPanel1754;
    private javax.swing.JPanel jPanel1755;
    private javax.swing.JPanel jPanel1756;
    private javax.swing.JPanel jPanel1757;
    private javax.swing.JPanel jPanel1758;
    private javax.swing.JPanel jPanel1759;
    private javax.swing.JPanel jPanel1760;
    private javax.swing.JPanel jPanel1761;
    private javax.swing.JPanel jPanel1762;
    private javax.swing.JPanel jPanel1763;
    private javax.swing.JPanel jPanel1764;
    private javax.swing.JPanel jPanel1765;
    private javax.swing.JPanel jPanel1766;
    private javax.swing.JPanel jPanel1767;
    private javax.swing.JPanel jPanel1768;
    private javax.swing.JPanel jPanel1769;
    private javax.swing.JPanel jPanel1770;
    private javax.swing.JPanel jPanel1771;
    private javax.swing.JPanel jPanel1772;
    private javax.swing.JPanel jPanel1773;
    private javax.swing.JPanel jPanel1774;
    private javax.swing.JPanel jPanel1775;
    private javax.swing.JPanel jPanel1776;
    private javax.swing.JPanel jPanel1777;
    private javax.swing.JPanel jPanel1778;
    private javax.swing.JPanel jPanel1779;
    private javax.swing.JPanel jPanel1780;
    private javax.swing.JPanel jPanel1781;
    private javax.swing.JPanel jPanel1782;
    private javax.swing.JPanel jPanel1783;
    private javax.swing.JPanel jPanel1784;
    private javax.swing.JPanel jPanel1785;
    private javax.swing.JPanel jPanel1786;
    private javax.swing.JPanel jPanel1787;
    private javax.swing.JPanel jPanel1788;
    private javax.swing.JPanel jPanel1789;
    private javax.swing.JPanel jPanel1790;
    private javax.swing.JPanel jPanel1791;
    private javax.swing.JPanel jPanel1792;
    private javax.swing.JPanel jPanel1793;
    private javax.swing.JPanel jPanel1794;
    private javax.swing.JPanel jPanel1795;
    private javax.swing.JPanel jPanel1796;
    private javax.swing.JPanel jPanel1797;
    private javax.swing.JPanel jPanel1798;
    private javax.swing.JPanel jPanel1799;
    private javax.swing.JPanel jPanel1800;
    private javax.swing.JPanel jPanel1801;
    private javax.swing.JPanel jPanel1802;
    private javax.swing.JPanel jPanel1803;
    private javax.swing.JPanel jPanel1804;
    private javax.swing.JPanel jPanel1805;
    private javax.swing.JPanel jPanel1806;
    private javax.swing.JPanel jPanel1807;
    private javax.swing.JPanel jPanel1808;
    private javax.swing.JPanel jPanel1809;
    private javax.swing.JPanel jPanel1810;
    private javax.swing.JPanel jPanel1811;
    private javax.swing.JPanel jPanel1812;
    private javax.swing.JPanel jPanel1813;
    private javax.swing.JPanel jPanel1814;
    private javax.swing.JPanel jPanel1815;
    private javax.swing.JPanel jPanel1816;
    private javax.swing.JPanel jPanel1817;
    private javax.swing.JPanel jPanel1818;
    private javax.swing.JPanel jPanel1819;
    private javax.swing.JPanel jPanel1820;
    private javax.swing.JPanel jPanel1821;
    private javax.swing.JPanel jPanel1822;
    private javax.swing.JPanel jPanel1823;
    private javax.swing.JPanel jPanel1824;
    private javax.swing.JPanel jPanel1825;
    private javax.swing.JPanel jPanel1826;
    private javax.swing.JPanel jPanel1827;
    private javax.swing.JPanel jPanel1828;
    private javax.swing.JPanel jPanel1829;
    private javax.swing.JPanel jPanel1830;
    private javax.swing.JPanel jPanel1831;
    private javax.swing.JPanel jPanel1832;
    private javax.swing.JPanel jPanel1833;
    private javax.swing.JPanel jPanel1834;
    private javax.swing.JPanel jPanel1835;
    private javax.swing.JPanel jPanel1836;
    private javax.swing.JPanel jPanel1837;
    private javax.swing.JPanel jPanel1838;
    private javax.swing.JPanel jPanel1839;
    private javax.swing.JPanel jPanel1840;
    private javax.swing.JPanel jPanel1841;
    private javax.swing.JPanel jPanel1842;
    private javax.swing.JPanel jPanel1843;
    private javax.swing.JPanel jPanel1844;
    private javax.swing.JPanel jPanel1845;
    private javax.swing.JPanel jPanel1846;
    private javax.swing.JPanel jPanel1847;
    private javax.swing.JPanel jPanel1848;
    private javax.swing.JPanel jPanel1849;
    private javax.swing.JPanel jPanel1850;
    private javax.swing.JPanel jPanel1851;
    private javax.swing.JPanel jPanel1852;
    private javax.swing.JPanel jPanel1853;
    private javax.swing.JPanel jPanel1854;
    private javax.swing.JPanel jPanel1855;
    private javax.swing.JPanel jPanel1856;
    private javax.swing.JPanel jPanel1857;
    private javax.swing.JPanel jPanel1858;
    private javax.swing.JPanel jPanel1859;
    private javax.swing.JPanel jPanel1860;
    private javax.swing.JPanel jPanel1861;
    private javax.swing.JPanel jPanel1862;
    private javax.swing.JPanel jPanel1863;
    private javax.swing.JPanel jPanel1864;
    private javax.swing.JPanel jPanel1865;
    private javax.swing.JPanel jPanel1866;
    private javax.swing.JPanel jPanel1867;
    private javax.swing.JPanel jPanel1868;
    private javax.swing.JPanel jPanel1869;
    private javax.swing.JPanel jPanel1870;
    private javax.swing.JPanel jPanel1871;
    private javax.swing.JPanel jPanel1872;
    private javax.swing.JPanel jPanel1873;
    private javax.swing.JPanel jPanel1874;
    private javax.swing.JPanel jPanel1875;
    private javax.swing.JPanel jPanel1876;
    private javax.swing.JPanel jPanel1877;
    private javax.swing.JPanel jPanel1878;
    private javax.swing.JPanel jPanel1879;
    private javax.swing.JPanel jPanel1880;
    private javax.swing.JPanel jPanel1881;
    private javax.swing.JPanel jPanel1882;
    private javax.swing.JPanel jPanel1883;
    private javax.swing.JPanel jPanel1884;
    private javax.swing.JPanel jPanel1885;
    private javax.swing.JPanel jPanel1886;
    private javax.swing.JPanel jPanel1887;
    private javax.swing.JPanel jPanel1888;
    private javax.swing.JPanel jPanel1889;
    private javax.swing.JPanel jPanel1890;
    private javax.swing.JPanel jPanel1891;
    private javax.swing.JPanel jPanel1892;
    private javax.swing.JPanel jPanel1893;
    private javax.swing.JPanel jPanel1894;
    private javax.swing.JPanel jPanel1895;
    private javax.swing.JPanel jPanel1896;
    private javax.swing.JPanel jPanel1897;
    private javax.swing.JPanel jPanel1898;
    private javax.swing.JPanel jPanel1899;
    private javax.swing.JPanel jPanel1900;
    private javax.swing.JPanel jPanel1901;
    private javax.swing.JPanel jPanel1902;
    private javax.swing.JPanel jPanel1903;
    private javax.swing.JPanel jPanel1904;
    private javax.swing.JPanel jPanel1905;
    private javax.swing.JPanel jPanel1906;
    private javax.swing.JPanel jPanel1907;
    private javax.swing.JPanel jPanel1908;
    private javax.swing.JPanel jPanel1909;
    private javax.swing.JPanel jPanel1910;
    private javax.swing.JPanel jPanel1911;
    private javax.swing.JPanel jPanel1912;
    private javax.swing.JPanel jPanel1913;
    private javax.swing.JPanel jPanel1914;
    private javax.swing.JPanel jPanel1915;
    private javax.swing.JPanel jPanel1916;
    private javax.swing.JPanel jPanel1917;
    private javax.swing.JPanel jPanel1918;
    private javax.swing.JPanel jPanel1919;
    private javax.swing.JPanel jPanel1920;
    private javax.swing.JPanel jPanel1921;
    private javax.swing.JPanel jPanel1922;
    private javax.swing.JPanel jPanel1923;
    private javax.swing.JPanel jPanel1924;
    private javax.swing.JPanel jPanel1925;
    private javax.swing.JPanel jPanel1926;
    private javax.swing.JPanel jPanel1927;
    private javax.swing.JPanel jPanel1928;
    private javax.swing.JPanel jPanel1929;
    private javax.swing.JPanel jPanel1930;
    private javax.swing.JPanel jPanel1931;
    private javax.swing.JPanel jPanel1932;
    private javax.swing.JPanel jPanel1933;
    private javax.swing.JPanel jPanel1934;
    private javax.swing.JPanel jPanel1935;
    private javax.swing.JPanel jPanel1936;
    private javax.swing.JPanel jPanel1937;
    private javax.swing.JPanel jPanel1938;
    private javax.swing.JPanel jPanel1939;
    private javax.swing.JPanel jPanel1940;
    private javax.swing.JPanel jPanel1941;
    private javax.swing.JPanel jPanel1942;
    private javax.swing.JPanel jPanel1943;
    private javax.swing.JPanel jPanel1944;
    private javax.swing.JPanel jPanel1945;
    private javax.swing.JPanel jPanel1946;
    private javax.swing.JPanel jPanel1947;
    private javax.swing.JPanel jPanel1948;
    private javax.swing.JPanel jPanel1949;
    private javax.swing.JPanel jPanel1950;
    private javax.swing.JPanel jPanel1951;
    private javax.swing.JPanel jPanel1952;
    private javax.swing.JPanel jPanel1953;
    private javax.swing.JPanel jPanel1954;
    private javax.swing.JPanel jPanel1955;
    private javax.swing.JPanel jPanel1956;
    private javax.swing.JPanel jPanel1957;
    private javax.swing.JPanel jPanel1958;
    private javax.swing.JPanel jPanel1959;
    private javax.swing.JPanel jPanel1960;
    private javax.swing.JPanel jPanel1961;
    private javax.swing.JPanel jPanel1962;
    private javax.swing.JPanel jPanel1963;
    private javax.swing.JPanel jPanel1964;
    private javax.swing.JPanel jPanel1965;
    private javax.swing.JPanel jPanel1966;
    private javax.swing.JPanel jPanel1967;
    private javax.swing.JPanel jPanel1968;
    private javax.swing.JPanel jPanel1969;
    private javax.swing.JPanel jPanel1970;
    private javax.swing.JPanel jPanel1971;
    private javax.swing.JPanel jPanel1972;
    private javax.swing.JPanel jPanel1973;
    private javax.swing.JPanel jPanel1974;
    private javax.swing.JPanel jPanel1975;
    private javax.swing.JPanel jPanel1976;
    private javax.swing.JPanel jPanel1977;
    private javax.swing.JPanel jPanel1978;
    private javax.swing.JPanel jPanel1979;
    private javax.swing.JPanel jPanel1980;
    private javax.swing.JPanel jPanel1981;
    private javax.swing.JPanel jPanel1982;
    private javax.swing.JPanel jPanel1983;
    private javax.swing.JPanel jPanel1984;
    private javax.swing.JPanel jPanel1985;
    private javax.swing.JPanel jPanel1986;
    private javax.swing.JPanel jPanel1987;
    private javax.swing.JPanel jPanel1988;
    private javax.swing.JPanel jPanel1989;
    private javax.swing.JPanel jPanel1990;
    private javax.swing.JPanel jPanel1991;
    private javax.swing.JPanel jPanel1992;
    private javax.swing.JPanel jPanel1993;
    private javax.swing.JPanel jPanel1994;
    private javax.swing.JPanel jPanel1995;
    private javax.swing.JPanel jPanel1996;
    private javax.swing.JPanel jPanel1997;
    private javax.swing.JPanel jPanel1998;
    private javax.swing.JPanel jPanel1999;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2000;
    private javax.swing.JPanel jPanel2001;
    private javax.swing.JPanel jPanel2002;
    private javax.swing.JPanel jPanel2003;
    private javax.swing.JPanel jPanel2004;
    private javax.swing.JPanel jPanel2005;
    private javax.swing.JPanel jPanel2006;
    private javax.swing.JPanel jPanel2007;
    private javax.swing.JPanel jPanel2008;
    private javax.swing.JPanel jPanel2009;
    private javax.swing.JPanel jPanel2010;
    private javax.swing.JPanel jPanel2011;
    private javax.swing.JPanel jPanel2012;
    private javax.swing.JPanel jPanel2013;
    private javax.swing.JPanel jPanel2014;
    private javax.swing.JPanel jPanel2015;
    private javax.swing.JPanel jPanel2016;
    private javax.swing.JPanel jPanel2017;
    private javax.swing.JPanel jPanel2018;
    private javax.swing.JPanel jPanel2019;
    private javax.swing.JPanel jPanel2020;
    private javax.swing.JPanel jPanel2021;
    private javax.swing.JPanel jPanel2022;
    private javax.swing.JPanel jPanel2023;
    private javax.swing.JPanel jPanel2024;
    private javax.swing.JPanel jPanel2025;
    private javax.swing.JPanel jPanel2026;
    private javax.swing.JPanel jPanel2027;
    private javax.swing.JPanel jPanel2028;
    private javax.swing.JPanel jPanel2029;
    private javax.swing.JPanel jPanel2030;
    private javax.swing.JPanel jPanel2031;
    private javax.swing.JPanel jPanel2032;
    private javax.swing.JPanel jPanel2033;
    private javax.swing.JPanel jPanel2034;
    private javax.swing.JPanel jPanel2035;
    private javax.swing.JPanel jPanel2036;
    private javax.swing.JPanel jPanel2037;
    private javax.swing.JPanel jPanel2038;
    private javax.swing.JPanel jPanel2039;
    private javax.swing.JPanel jPanel2040;
    private javax.swing.JPanel jPanel2041;
    private javax.swing.JPanel jPanel2042;
    private javax.swing.JPanel jPanel2043;
    private javax.swing.JPanel jPanel2044;
    private javax.swing.JPanel jPanel2045;
    private javax.swing.JPanel jPanel2046;
    private javax.swing.JPanel jPanel2047;
    private javax.swing.JPanel jPanel2048;
    private javax.swing.JPanel jPanel2049;
    private javax.swing.JPanel jPanel2050;
    private javax.swing.JPanel jPanel2051;
    private javax.swing.JPanel jPanel2052;
    private javax.swing.JPanel jPanel2053;
    private javax.swing.JPanel jPanel2054;
    private javax.swing.JPanel jPanel2055;
    private javax.swing.JPanel jPanel2056;
    private javax.swing.JPanel jPanel2057;
    private javax.swing.JPanel jPanel2058;
    private javax.swing.JPanel jPanel2059;
    private javax.swing.JPanel jPanel2060;
    private javax.swing.JPanel jPanel2061;
    private javax.swing.JPanel jPanel2062;
    private javax.swing.JPanel jPanel2063;
    private javax.swing.JPanel jPanel2064;
    private javax.swing.JPanel jPanel2065;
    private javax.swing.JPanel jPanel2066;
    private javax.swing.JPanel jPanel2067;
    private javax.swing.JPanel jPanel2068;
    private javax.swing.JPanel jPanel2069;
    private javax.swing.JPanel jPanel2070;
    private javax.swing.JPanel jPanel2071;
    private javax.swing.JPanel jPanel2072;
    private javax.swing.JPanel jPanel2073;
    private javax.swing.JPanel jPanel2074;
    private javax.swing.JPanel jPanel2075;
    private javax.swing.JPanel jPanel2076;
    private javax.swing.JPanel jPanel2077;
    private javax.swing.JPanel jPanel2078;
    private javax.swing.JPanel jPanel2079;
    private javax.swing.JPanel jPanel2080;
    private javax.swing.JPanel jPanel2081;
    private javax.swing.JPanel jPanel2082;
    private javax.swing.JPanel jPanel2083;
    private javax.swing.JPanel jPanel2084;
    private javax.swing.JPanel jPanel2085;
    private javax.swing.JPanel jPanel2086;
    private javax.swing.JPanel jPanel2087;
    private javax.swing.JPanel jPanel2088;
    private javax.swing.JPanel jPanel2089;
    private javax.swing.JPanel jPanel2090;
    private javax.swing.JPanel jPanel2091;
    private javax.swing.JPanel jPanel2092;
    private javax.swing.JPanel jPanel2093;
    private javax.swing.JPanel jPanel2094;
    private javax.swing.JPanel jPanel2095;
    private javax.swing.JPanel jPanel2096;
    private javax.swing.JPanel jPanel2097;
    private javax.swing.JPanel jPanel2098;
    private javax.swing.JPanel jPanel2099;
    private javax.swing.JPanel jPanel2100;
    private javax.swing.JPanel jPanel2101;
    private javax.swing.JPanel jPanel2102;
    private javax.swing.JPanel jPanel2103;
    private javax.swing.JPanel jPanel2104;
    private javax.swing.JPanel jPanel2105;
    private javax.swing.JPanel jPanel2106;
    private javax.swing.JPanel jPanel2107;
    private javax.swing.JPanel jPanel2108;
    private javax.swing.JPanel jPanel2109;
    private javax.swing.JPanel jPanel2110;
    private javax.swing.JPanel jPanel2111;
    private javax.swing.JPanel jPanel2112;
    private javax.swing.JPanel jPanel2113;
    private javax.swing.JPanel jPanel2114;
    private javax.swing.JPanel jPanel2115;
    private javax.swing.JPanel jPanel2116;
    private javax.swing.JPanel jPanel2117;
    private javax.swing.JPanel jPanel2118;
    private javax.swing.JPanel jPanel2119;
    private javax.swing.JPanel jPanel2120;
    private javax.swing.JPanel jPanel2121;
    private javax.swing.JPanel jPanel2122;
    private javax.swing.JPanel jPanel2123;
    private javax.swing.JPanel jPanel2124;
    private javax.swing.JPanel jPanel2125;
    private javax.swing.JPanel jPanel2126;
    private javax.swing.JPanel jPanel2127;
    private javax.swing.JPanel jPanel2128;
    private javax.swing.JPanel jPanel2129;
    private javax.swing.JPanel jPanel2130;
    private javax.swing.JPanel jPanel2131;
    private javax.swing.JPanel jPanel2132;
    private javax.swing.JPanel jPanel2133;
    private javax.swing.JPanel jPanel2134;
    private javax.swing.JPanel jPanel2135;
    private javax.swing.JPanel jPanel2136;
    private javax.swing.JPanel jPanel2137;
    private javax.swing.JPanel jPanel2138;
    private javax.swing.JPanel jPanel2139;
    private javax.swing.JPanel jPanel2140;
    private javax.swing.JPanel jPanel2141;
    private javax.swing.JPanel jPanel2142;
    private javax.swing.JPanel jPanel2143;
    private javax.swing.JPanel jPanel2144;
    private javax.swing.JPanel jPanel2145;
    private javax.swing.JPanel jPanel2146;
    private javax.swing.JPanel jPanel2147;
    private javax.swing.JPanel jPanel2148;
    private javax.swing.JPanel jPanel2149;
    private javax.swing.JPanel jPanel2150;
    private javax.swing.JPanel jPanel2151;
    private javax.swing.JPanel jPanel2152;
    private javax.swing.JPanel jPanel2153;
    private javax.swing.JPanel jPanel2154;
    private javax.swing.JPanel jPanel2155;
    private javax.swing.JPanel jPanel2156;
    private javax.swing.JPanel jPanel2157;
    private javax.swing.JPanel jPanel2158;
    private javax.swing.JPanel jPanel2159;
    private javax.swing.JPanel jPanel2160;
    private javax.swing.JPanel jPanel2161;
    private javax.swing.JPanel jPanel2162;
    private javax.swing.JPanel jPanel2163;
    private javax.swing.JPanel jPanel2164;
    private javax.swing.JPanel jPanel2165;
    private javax.swing.JPanel jPanel2166;
    private javax.swing.JPanel jPanel2167;
    private javax.swing.JPanel jPanel2168;
    private javax.swing.JPanel jPanel2169;
    private javax.swing.JPanel jPanel2170;
    private javax.swing.JPanel jPanel2171;
    private javax.swing.JPanel jPanel2172;
    private javax.swing.JPanel jPanel2173;
    private javax.swing.JPanel jPanel2174;
    private javax.swing.JPanel jPanel2175;
    private javax.swing.JPanel jPanel2176;
    private javax.swing.JPanel jPanel2177;
    private javax.swing.JPanel jPanel2178;
    private javax.swing.JPanel jPanel2179;
    private javax.swing.JPanel jPanel2180;
    private javax.swing.JPanel jPanel2181;
    private javax.swing.JPanel jPanel2182;
    private javax.swing.JPanel jPanel2183;
    private javax.swing.JPanel jPanel2184;
    private javax.swing.JPanel jPanel2185;
    private javax.swing.JPanel jPanel2186;
    private javax.swing.JPanel jPanel2187;
    private javax.swing.JPanel jPanel2188;
    private javax.swing.JPanel jPanel2189;
    private javax.swing.JPanel jPanel2190;
    private javax.swing.JPanel jPanel2191;
    private javax.swing.JPanel jPanel2192;
    private javax.swing.JPanel jPanel2193;
    private javax.swing.JPanel jPanel2194;
    private javax.swing.JPanel jPanel2195;
    private javax.swing.JPanel jPanel2196;
    private javax.swing.JPanel jPanel2197;
    private javax.swing.JPanel jPanel2198;
    private javax.swing.JPanel jPanel2199;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField4_sum;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPanel side_panel;
    public static javax.swing.JTextField sixsum;
    // End of variables declaration//GEN-END:variables
}
