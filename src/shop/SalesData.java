/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.toedter.calendar.JCalendar;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalesData extends javax.swing.JFrame {

    
    public SalesData() {
        initComponents();
        bill=myAutoId()+1;
        txtBill.setText(""+bill);
        setProductName();
    }
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/javatest";
    String un = "root";
    String pw = "";    
    static int bill=0,id=0;
    String customerName, billNumber,product,price,date,quantity;
    //this method is for connecting mysql database to java
    void dbConnect() {

        try {
            con = DriverManager.getConnection(url, un, pw);
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //This method is for generating auto Bill
    int myAutoId(){
        int a=0;
        try {
            dbConnect();
            String q="SELECT `Bill no.` FROM `salestable`";
            rs=st.executeQuery(q);
            while(rs.next()){
                a=Integer.parseInt(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }
    //This method is for clearing the text fields
    void clearText(){ 
        txtPrice.setText("");
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
        txtQuantity.setText("");
        comProduct.setSelectedIndex(0);
    }
    void setProductName(){
        try {
            dbConnect();
            comProduct.removeAllItems();
            String s="SELECT `Name` FROM `productlist`";
            comProduct.addItem("Select Product");
            rs=st.executeQuery(s);
            while(rs.next()){
             comProduct.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void getData(){
         customerName=txtCustomer.getText();
         billNumber=txtBill.getText();
         product=comProduct.getSelectedItem().toString();
         price=txtPrice.getText();
         SimpleDateFormat smp=new SimpleDateFormat("yyyy-MM-dd");
         date=jLabel12.getText();
         quantity=txtQuantity.getText();
        
    }
    void showData(String query){
        try {
            int total=0;
            dbConnect();
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            rs=st.executeQuery(query);
            for (int i = 0, p=0, q=0,t=0; rs.next(); ) {i++;
            p=Integer.parseInt(rs.getString(5));
            q=Integer.parseInt(rs.getString(6));
            t=p*q;
            dtm.addRow(new Object[]{i,rs.getString(4),p,q,t});
            total=total+t;
           jLabel10.setText(rs.getString(1));
           jLabel9.setText(rs.getString(3));
           jLabel12.setText(rs.getString(2));
            }
            jLabel14.setText(""+total);
            jLabel17.setText(""+total);
            jTextField2.setText("0");
            jTextField1.setText("0");
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int dataEmptyCheck(){
        int check=0;
         if(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Select Date!");
            jDateChooser1.requestFocus();
        }
         else if(txtCustomer.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter Customer Name!");
            txtCustomer.requestFocus();
        }
        else if(comProduct.getSelectedIndex()==0 || txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Select Product!");
            txtPrice.requestFocus();
        }
        else if(txtQuantity.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter Quantity!");
            txtQuantity.requestFocus();
        }
        else{
            check=1;
        }
        
        return check;
    }
    void clear1(){
        DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        txtCustomer.setText("");
        jLabel14.setText("");
        jTextField2.setText("");
        jTextField1.setText("");
        jLabel17.setText("");
        jLabel10.setText("Bill no.");
        jLabel9.setText("Customer Name");
        jLabel12.setText("Date");
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
    }
    void showPaidDiscount(){
        try {String cu=jLabel9.getText();
            String q="SELECT * FROM `salescollection` WHERE`Customer Name`='"+cu+"'";
            dbConnect();
            rs=st.executeQuery(q);
            while(rs.next()){
                jTextField1.setText(rs.getString(7));
                jTextField2.setText(rs.getString(6));
                jLabel17.setText(rs.getString(8));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        txtCustomer = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        comProduct = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtBill = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales Information");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer's Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 190, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sales Information");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 330, 60));

        jLabel3.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bill No.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 120, 40));

        jLabel4.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Date:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 120, 40));

        jLabel5.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quantity");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 130, 40));

        jLabel6.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Product ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 130, 40));

        jLabel7.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Price");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 130, 40));

        txtFind.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtFind.setForeground(new java.awt.Color(0, 51, 51));
        txtFind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFindMouseClicked(evt);
            }
        });
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindKeyPressed(evt);
            }
        });
        getContentPane().add(txtFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 210, 40));

        txtCustomer.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCustomer.setForeground(new java.awt.Color(0, 51, 51));
        txtCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustomerMouseClicked(evt);
            }
        });
        txtCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerActionPerformed(evt);
            }
        });
        getContentPane().add(txtCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 270, 50));

        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(0, 51, 51));
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 270, 50));

        txtQuantity.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtQuantity.setForeground(new java.awt.Color(0, 51, 51));
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 270, 50));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 270, 50));

        comProduct.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        comProduct.setForeground(new java.awt.Color(0, 51, 51));
        comProduct.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", " " }));
        comProduct.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comProductPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        comProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comProductActionPerformed(evt);
            }
        });
        getContentPane().add(comProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 270, 50));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl No.", "Name", "Price", "Quantity", "Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 590, 370));

        jLabel9.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Customer Name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 200, 30));

        jLabel10.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Bill no.");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Discount");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 80, 30));

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 51, 51));
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 120, 30));

        jLabel12.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Date");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 130, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Total");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 80, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 100, 30));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Paid");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 80, 30));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 540, 100, 30));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Due");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 540, 80, 30));

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 51, 51));
        jButton9.setText("Submit Bill");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 120, 30));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 510, 100, 30));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 600, 570));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 100, 40));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 90, 40));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 51));
        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 100, 40));

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 51));
        jButton4.setText("Find");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 90, 40));

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 51));
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 100, 40));

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 51, 51));
        jButton6.setText("Show");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 90, 40));

        txtBill.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtBill.setForeground(new java.awt.Color(0, 51, 51));
        getContentPane().add(txtBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 270, 50));

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 51, 51));
        jButton8.setText("Exit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 90, 40));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Go Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 30));

        setSize(new java.awt.Dimension(1063, 574));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
clearText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      if(dataEmptyCheck()==1){
        int a=JOptionPane.showConfirmDialog(this, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION);
            if(a==0){  try {
            // Delete:
            
            dbConnect();
            String dQuery="DELETE FROM `salestable` WHERE `Bill no.`='"+jLabel10.getText()+"' AND `p_Name`='"+comProduct.getSelectedItem().toString()+"'";
            int delete=st.executeUpdate(dQuery);
            if(delete>0){
                JOptionPane.showMessageDialog(this, "Data Deleted!");
                String sql="SELECT * FROM `salestable` WHERE `cus_Name`='"+txtCustomer.getText()+"'AND `cus_Name`='"+txtCustomer.getText()+"'";
        showData(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }}}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
int a=JOptionPane.showConfirmDialog(this, "Are you sure?","Exit",JOptionPane.YES_NO_OPTION);
if(a==0){
    this.dispose();
}

    }//GEN-LAST:event_jButton8ActionPerformed

    private void comProductPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comProductPopupMenuWillBecomeInvisible
        try {
            // ivisible items:
            dbConnect();
            String name=comProduct.getSelectedItem().toString();
            String s="SELECT SUM(`Price`+(`Price`*5)/100) \n" +
"FROM `productlist` \n" +
"WHERE `Name`='"+name+"' ";
            rs=st.executeQuery(s);
            if(rs.next()){
                txtPrice.setText(""+rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_comProductPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(dataEmptyCheck()==1){
        try {
            // Add:
            dbConnect();
            getData();
            SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
            date=simple.format(jDateChooser1.getDate());
            String sq="INSERT INTO `salestable`(`Bill no.`,`s_Date`,`cus_Name`,`p_Name`,`s_Quantity`,`s_Price`)\n" +
                    "VALUES('"+billNumber+"','"+date+"','"+customerName+"','"+product+"','"+quantity+"','"+price+"')";
            int a=st.executeUpdate(sq);
            if(a>0){
                JOptionPane.showMessageDialog(this, "Added!");
                String squery="SELECT * FROM `salestable` WHERE `Bill no.`='"+billNumber+"' AND `cus_Name`='"+customerName+"'";
                showData(squery);
                txtPrice.setText("");
                txtQuantity.setText("");
                comProduct.setSelectedIndex(0);
        
            }
            else{
                JOptionPane.showMessageDialog(this, "Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(dataEmptyCheck()==1){ try {
            //Update:
            getData();
            String sUpdate="UPDATE `salestable` SET `s_Date`='"+date+"',`cus_Name`='"+customerName+"',`p_Name`='"+product+"',`s_Price`='"+price+"',`s_Quantity`= '"+quantity+"'WHERE `cus_Name`='"+jLabel9.getText()+"' and `Bill no.`='"+jLabel10.getText()+"'AND `p_Name`='"+product+"'";
            dbConnect();
            rs=null;
            int check=st.executeUpdate(sUpdate);
            if(check>0){
                DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
               String Items=""+dtm.getRowCount();
                String up="UPDATE `salescollection` SET `Total`='"+jLabel14.getText()+"',`Items`='"+Items+"' WHERE `Bill no.`='"+jLabel10.getText()+"'";
                int u=st.executeUpdate(up);
                if(u>0){
                JOptionPane.showMessageDialog(this, "Updated!");
                String sq="SELECT * FROM `salestable` WHERE `Bill no.`='"+jLabel10.getText()+"'";
                showData(sq); } 
            }
            else{
                JOptionPane.showMessageDialog(this, "Failed to Update!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
String showD="SELECT * FROM `salestable` WHERE `Bill no.`=1 AND `cus_Name`=\"Solim\"";
        showData(showD);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // setting back value to table
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        String cusName=jLabel9.getText();
        String pName=dtm.getValueAt(jTable1.getSelectedRow(),1).toString();
        String productPrice=dtm.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String productQuantity=dtm.getValueAt(jTable1.getSelectedRow(), 3).toString();
        String Sdate=jLabel12.getText();
        
        txtCustomer.setText(cusName);
        txtPrice.setText(productPrice);
        txtQuantity.setText(productQuantity);
        comProduct.setSelectedItem(pName);
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(Sdate);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //clear tabledata:
        clear1();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustomerMouseClicked
        // TODO add your handling code here:
        txtCustomer.setText("");
    }//GEN-LAST:event_txtCustomerMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String find=txtFind.getText();
        String sql="SELECT * FROM `salestable` WHERE `Bill no.`='"+find+"'";
        showData(sql);
        showPaidDiscount();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // Enter:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        if(!jTextField1.getText().isEmpty()){   
            double y=Double.parseDouble(jTextField1.getText());
            double t=Double.parseDouble(jLabel14.getText());
            double due=t-y;
            if(!jTextField2.getText().isEmpty()){
            double disc=Double.parseDouble(jTextField2.getText());
            due=due-disc;}
            jLabel17.setText(""+due);
            
        }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // Submit to SalesCollection:
       try {
        String sBill,sDate,sItems,sTotal,sDiscount,sPaid,sDue,sCustomer;
        dbConnect();
        DefaultTableModel dtm= (DefaultTableModel)jTable1.getModel();
        sBill=jLabel10.getText();
        sCustomer=jLabel9.getText();
        sDate=jLabel12.getText();
        sItems=""+dtm.getRowCount();
        sTotal=jLabel14.getText();
        sDiscount=jTextField2.getText();
        sPaid=jTextField1.getText();
        sDue=jLabel17.getText();
        String SalesIn="INSERT INTO `SalesCollection` VALUES('"+sBill+"','"+sCustomer+"','"+sDate+"','"+sItems+"','"+sTotal+"','"+sDiscount+"','"+sPaid+"','"+sDue+"')";
            int c=st.executeUpdate(SalesIn);
            if(c>0){
                bill++;
                JOptionPane.showMessageDialog(this, "Submitted.....");
                clear1();
                txtBill.setText(""+bill);
            }
            else{
                JOptionPane.showMessageDialog(this, "Failed to Submit.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        jTextField1.requestFocus();
        double dis1=Double.parseDouble(jTextField2.getText());
        double total1=Double.parseDouble(jLabel14.getText());
        double d=total1-dis1;
        jLabel17.setText(""+d);
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    private void txtFindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFindMouseClicked
        // TODO add your handling code here:
        txtFind.setText("");
    }//GEN-LAST:event_txtFindMouseClicked

    private void txtFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        String find=txtFind.getText();
        String sql="SELECT * FROM `salestable` WHERE `Bill no.`='"+find+"'";
        showData(sql);
        showPaidDiscount();
        }
    }//GEN-LAST:event_txtFindKeyPressed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        new Dashboard().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void comProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comProductActionPerformed

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
            java.util.logging.Logger.getLogger(SalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtBill;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
