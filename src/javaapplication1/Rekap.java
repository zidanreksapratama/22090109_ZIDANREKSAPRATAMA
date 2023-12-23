/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaapplication1;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Locale;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author LENOVO
 */
public class Rekap extends javax.swing.JPanel {
    private String selectedName;
    private String selectedMonth;
    /**
     * Creates new form Rekap
     */
    public Rekap() {
        initComponents();
        show_combo();
        Tampil2();
        Totalkehadiran.setEditable(false);
    }
    private void show_combo() {
        try {
            String sql = "SELECT DISTINCT `Nama Karyawan` FROM tbabsen1";
            java.sql.Connection con = (java.sql.Connection) Koneksi.getConnection();
            java.sql.PreparedStatement pat = con.prepareStatement(sql);
            ResultSet rs = pat.executeQuery();
        while (rs.next()) {
            Nama.addItem(rs.getString("Nama Karyawan"));
        }
            String[] monthNames = new DateFormatSymbols(Locale.ENGLISH).getMonths();
            DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<>(monthNames);
            Bulan.setModel(monthModel);
    } catch (Exception e) {
        e.printStackTrace();
        }
    } 
    
   
    
    private void Tampil(String selectedName, String selectedMonth) {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID Karyawan");
        tabel.addColumn("Nama");
        tabel.addColumn("Tanggal");
        tabel.addColumn("Hadir");
        tabel.addColumn("Tidak Hadir");

      int totalHadir = 0;
      try {
        java.sql.Connection con = (java.sql.Connection) Koneksi.getConnection();
        System.out.println("Selected Name: " + selectedName);
        System.out.println("Selected Month: " + selectedMonth);
        
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
        java.util.Date date = inputFormat.parse(selectedMonth);
        String formattedMonth = outputFormat.format(date);
        
        String sql = "SELECT tbabsen1.`ID Karyawan`, tbabsen1.`Nama Karyawan`, tbabsen1.`Tanggal`, " +
                     "SUM(CASE WHEN tbabsen1.`Keterangan` = 'Hadir' THEN 1 ELSE 0 END) AS Hadir, " +
                     "SUM(CASE WHEN tbabsen1.`Keterangan` != 'Hadir' THEN 1 ELSE 0 END) AS Tidak_Hadir " +
                     "FROM tbcrew " +
                     "LEFT JOIN tbabsen1 ON tbcrew.`Nama Karyawan` = tbabsen1.`Nama Karyawan` " +
                     "WHERE tbcrew.`Nama Karyawan` = ? " +
                     "AND MONTH(tbabsen1.`Tanggal`) = ? " +
                     "GROUP BY tbabsen1.`ID Karyawan`, tbabsen1.`Nama Karyawan`, tbabsen1.`Tanggal`";
            java.sql.PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, selectedName);
            stm.setString(2, formattedMonth);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tabel.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                });
                totalHadir += Integer.parseInt(rs.getString(4));
            }
            Totalkehadiran.setText(String.valueOf(totalHadir));
            Tabelrekap.setModel(tabel);
        } catch (Exception e) {
             e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    private class MonthComboBoxModel extends DefaultComboBoxModel<String> {
        public MonthComboBoxModel() {
            String[] monthNames = new DateFormatSymbols(Locale.ENGLISH).getMonths();
            for (String monthName : monthNames) {
                if (!monthName.isEmpty()) {
                    this.addElement(monthName);
                }
            }
        }
    }
    
    private void fillMonthComboBox() {
        bulan1.setModel(new MonthComboBoxModel());
    }



    
    private void Tampil2() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("ID Karyawan");
        tabel.addColumn("Nama");
        tabel.addColumn("Tanggal");
        tabel.addColumn("Keterangan");

        try {
            java.sql.Connection con = (java.sql.Connection) Koneksi.getConnection();
            String sql = "SELECT * FROM tbabsen1";
            java.sql.PreparedStatement stm = con.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tabel.addRow(new Object[] {
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(5),
                    rs.getString(7),
                });
            }
            TabelRekap2.setModel(tabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabelrekap = new javax.swing.JTable();
        Bulan = new javax.swing.JComboBox<>();
        Nama = new javax.swing.JComboBox<>();
        Totalkehadiran = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelRekap2 = new javax.swing.JTable();
        bulan1 = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REKAP BULANAN");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, 0, 1520, 90));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1530, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1530, 90));

        Tabelrekap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Karyawan", "Nama ", "Tanggal", "Hadir", "Tiak Hadir"
            }
        ));
        jScrollPane1.setViewportView(Tabelrekap);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 1510, 100));

        Bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "====>Pilih Bulan<====", "Januari", "Februari", "Maret", "April ", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        add(Bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 180, -1));

        Nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "====>Pilih Nama<====" }));
        Nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaActionPerformed(evt);
            }
        });
        add(Nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 180, -1));

        Totalkehadiran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Totalkehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalkehadiranActionPerformed(evt);
            }
        });
        add(Totalkehadiran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 140, 170, -1));

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("NAMA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 110, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL KEHADIRAN");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 120, 170, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("BULAN");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 110, -1));

        TabelRekap2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TabelRekap2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1510, 140));

        bulan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(bulan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void TotalkehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalkehadiranActionPerformed
      
    }//GEN-LAST:event_TotalkehadiranActionPerformed

    private void NamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaActionPerformed
     
    }//GEN-LAST:event_NamaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selectedName = Nama.getSelectedItem().toString();
        selectedMonth = Bulan.getSelectedItem().toString();
        Tampil(selectedName, selectedMonth);
    }//GEN-LAST:event_jButton1ActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Bulan;
    private javax.swing.JComboBox<String> Nama;
    private javax.swing.JTable TabelRekap2;
    private javax.swing.JTable Tabelrekap;
    private javax.swing.JTextField Totalkehadiran;
    private javax.swing.JComboBox<String> bulan1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
