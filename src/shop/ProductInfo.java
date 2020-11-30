
package shop;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Mahmudul Hasan
 */
public class ProductInfo extends javax.swing.JFrame {
    public ProductInfo() {
        initComponents();
        String query="SELECT `Name`,`Price`,`Details` FROM `productlist`";
        showData(query);
        getSingleData();
    }
    //Connection mysql server
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String url="jdbc:mysql://localhost:3306/javatest";
    String un="root";
    String pw="";  
    static int id=0;
    static int p=0;
    /*
    int autoId(){
        int a=0;
        try {
        dbConnect();
        
        String m="SELECT `pid` FROM `productlist`";
        rs=st.executeQuery(m);
        while(rs.next()){
            a=rs.getInt(1);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ++a;
    }
    */
    
    void dbConnect(){
        try {
            con=DriverManager.getConnection(url, un, pw);
            st=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //table data
    void getTableData(){
        DefaultTableModel dt=(DefaultTableModel)jTable1.getModel();
        String pName,pprice,pDetails;
        pName=dt.getValueAt(jTable1.getSelectedRow(),1).toString();
        pprice=dt.getValueAt(jTable1.getSelectedRow(), 2).toString();
        pDetails=dt.getValueAt(jTable1.getSelectedRow(), 3).toString();
        txtName.setText(pName);
        txtPrice.setText(pprice);
        txtDetails.setText(pDetails);
        String q="SELECT `pid` FROM `productlist` WHERE `Name`='"+pName+"'";
        id=showId(q);
        txtId.setText(""+id);
    }
    //database operation method
    void dataOperation(String query,String Operation){
        try {
            dbConnect();
            int queryResult=st.executeUpdate(query);
            if(queryResult>0){
                dataClear();
                JOptionPane.showMessageDialog(this, "Data "+Operation+" Success!");
                String q="SELECT `Name`,`Price`,`Details` FROM `productlist` order by 'pid'";
                showData(q);
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Data "+Operation+" Failed!");
            }
                    } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void dataClear(){
       txtName.setText("");
       txtPrice.setText("");
       txtDetails.setText("");
    }
    int dataCheck(){
        int a=0;
        if(txtName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter all Data!");
            txtName.requestFocus();
        }
        else if(txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter all Data!");
            txtPrice.requestFocus();
        }
        else if(txtDetails.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter all Data!");
            txtDetails.requestFocus();
        }
        else{
            a=1;
        }
        return a;
    }
    void showData(String sq){
        try {
            dbConnect();
            rs=null;
            DefaultTableModel td = (DefaultTableModel)jTable1.getModel();
            td.setNumRows(0);
            int i=0,a=0;
            
            rs=st.executeQuery(sq);
            while(rs.next()){i++;
                td.addRow(new Object[]{i,rs.getString(1),rs.getString(2),rs.getString(3)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int showId(String sq){
        int i=0;
        try { 
            dbConnect();
            rs=null;
            rs=st.executeQuery(sq);
            while(rs.next()){
                i=rs.getInt(1);
            }
            } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
void getSingleData(){
     try {
            // first data:
            dbConnect();
            rs=st.executeQuery("SELECT `Name`,`Price`,`Details` FROM `productlist`");
            if(rs.first()){
                txtName.setText(rs.getString(1));
                txtPrice.setText(rs.getString(2));
                txtDetails.setText(rs.getString(3)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
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
        txtId = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtDetails = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        autoId = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Product Information");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Details");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 260, 110, 30);

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Product information");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(110, 0, 360, 60);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Name");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 140, 80, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Price");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 200, 110, 30);

        txtId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setText("ID");
        txtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdMouseClicked(evt);
            }
        });
        getContentPane().add(txtId);
        txtId.setBounds(110, 380, 110, 40);

        txtPrice.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(txtPrice);
        txtPrice.setBounds(160, 190, 380, 50);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("CLEAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(330, 320, 100, 40);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(100, 320, 90, 40);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(200, 320, 120, 40);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(440, 320, 100, 40);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("FIND");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(230, 380, 140, 40);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("EXIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(380, 380, 90, 40);

        txtDetails.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(txtDetails);
        txtDetails.setBounds(160, 250, 380, 50);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 255));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(null);

        jTable1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl no.", "Name", "Price", "Details"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 490, 400);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Previous");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10);
        jButton10.setBounds(10, 420, 100, 30);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText("NEXT");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11);
        jButton11.setBounds(120, 420, 80, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(580, 0, 510, 560);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Next");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(300, 500, 120, 40);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Show All");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(310, 440, 120, 40);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("First");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(40, 500, 120, 40);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(200, -20, 10, 10);
        getContentPane().add(jPanel3);
        jPanel3.setBounds(-10, 30, 10, 10);

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("End");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12);
        jButton12.setBounds(430, 500, 120, 40);

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setText("Previous");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13);
        jButton13.setBounds(170, 500, 120, 40);

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setText("Show Less");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14);
        jButton14.setBounds(170, 440, 120, 40);

        txtName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(txtName);
        txtName.setBounds(160, 130, 380, 50);

        autoId.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        autoId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoId.setToolTipText("");
        getContentPane().add(autoId);
        autoId.setBounds(150, 80, 90, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Id");
        jLabel6.setToolTipText("");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(80, 80, 50, 40);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Go Back");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 0, 60, 30);

        setSize(new java.awt.Dimension(1094, 561));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // exit code:
        int a=JOptionPane.showConfirmDialog(this, "Are you sure?","Exit",JOptionPane.YES_NO_OPTION);
        if(a==0){
            try {
                this.dispose();
                con.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // clear codes are here:
        if(txtName.getText().isEmpty() && txtDetails.getText().isEmpty() && txtPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No data to clear!");
        }
        else{dataClear();}   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Next:
        try {
            // previous:
            if(rs.next()){
              txtName.setText(rs.getString(1));
              txtPrice.setText(rs.getString(2));
              txtDetails.setText(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Addin data into table:
    String pName=txtName.getText();
    String pPrice=txtPrice.getText();
    String pDetails=txtDetails.getText();
        if(dataCheck()==1){
        dataOperation("INSERT INTO `productlist`(`Name`,`Price`,`Details`) VALUES('"+pName+"','"+pPrice+"','"+pDetails+"')", "Add");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
        txtId.setText("");
    }//GEN-LAST:event_txtIdMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(dataCheck()==1){
        try {
            // Update:
    String pName=txtName.getText();
    String pPrice=txtPrice.getText();
    String pDetails=txtDetails.getText();
    String query="UPDATE `productlist` SET `Name`='"+pName+"',`Price`='"+pPrice+"',`Details`='"+pDetails+"' WHERE `pid`='"+id+"'";
            dbConnect();
                 int update= st.executeUpdate(query);
           if(update>0){
              dataClear();
               JOptionPane.showMessageDialog(this, "Data Update Success!");
               String q="SELECT `Name`,`Price`,`Details` FROM `productlist` order by 'pid'";
                showData(q);
           }
           else{
               JOptionPane.showMessageDialog(this, "Data Update Failed!");
           }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int a=JOptionPane.showConfirmDialog(this, "Delete this? Sure?","Delete",JOptionPane.YES_NO_OPTION);
        if (a==0){
        try {
            //Delete:
            dbConnect();
            String id=txtId.getText();
            int delete=st.executeUpdate("DELETE FROM `productlist` WHERE `pid`='"+id+"'");
            
            if(delete>0){
                JOptionPane.showMessageDialog(this, "Data Delete is Success!");
                String query="SELECT `Name`,`Price`,`Details` FROM `productlist` order by 'pid'";
                showData(query);
            }
           else {
                JOptionPane.showMessageDialog(this, "Data Delete is Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // Show all DAta:
        p=0;
        String query="SELECT `Name`,`Price`,`Details` FROM `productlist` order by 'pid'";
        showData(query);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Find Data:
        if(txtName.getText().isEmpty() && txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter Name or id to Find a Data!");
            txtName.requestFocus();
        }
        else{
            String name=txtName.getText();
            String q="SELECT `Name`,`Price`,`Details` FROM `productlist` WHERE `pid`='"+txtId.getText()+"' OR `Name`='"+name+"'";
            showData(q);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //getting value into text field:
        getTableData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       getSingleData();
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // next data:
        p=p+5;
            String q="SELECT `Name`,`Price`,`Details` FROM `productlist` order by `pid` limit "+p+",5";
            showData(q);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
      try{
        p=p-5;if(p>=0){
            String q="SELECT `Name`,`Price`,`Details` FROM `productlist` ORDER BY `pid` desc LIMIT "+p+",5";
            showData(q);
        }
          else if(p<0){
                JOptionPane.showMessageDialog(this, "This is an End!");    
            }
      }catch (HeadlessException ex) {
            
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //Last:
        try {
            // previous:
            if(rs.last()){
              txtName.setText(rs.getString(1));
              txtPrice.setText(rs.getString(2));
              txtDetails.setText(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            // previous:
            if(rs.previous()){
              txtName.setText(rs.getString(1));
              txtPrice.setText(rs.getString(2));
              txtDetails.setText(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         p=0;
        String query="SELECT `Name`,`Price`,`Details` FROM `productlist` order by 'pid' LIMIT 0,5";
        showData(query);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        new Dashboard().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(ProductInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        ProductInfo pi=new ProductInfo();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pi.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autoId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDetails;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
