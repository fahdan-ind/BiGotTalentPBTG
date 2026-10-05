package Form;


import Koneksi.Koneksi;

import java.sql.*;

import javax.swing.JOptionPane;


public class FormSiswa extends javax.swing.JFrame {

    int idLoginSiswa;


    public FormSiswa(int idUser) {

        initComponents();

        setLocationRelativeTo(null);

        this.idLoginSiswa = idUser;

        loadPilihanLomba();

        cekStatusSiswa();

    }


    private void loadPilihanLomba() {

        cmbLomba.removeAllItems();

        try {

            Connection c = Koneksi.configDB();

            Statement s = c.createStatement();

            ResultSet r = s.executeQuery("SELECT * FROM lomba");

            while (r.next()) {

                cmbLomba.addItem(r.getString("id") + " - " + r.getString("nama_lomba"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Gagal memuat lomba: " + e.getMessage());

        }

    }


    private void cekStatusSiswa() {

        try {

            Connection c = Koneksi.configDB();

            Statement s = c.createStatement();

            ResultSet r = s.executeQuery("SELECT * FROM pendaftaran WHERE user_id='" + idLoginSiswa + "'");

            if (r.next()) {

                String status = r.getString("status");

                lblStatus.setText("Status Tahapan Anda: " + status);

            } else {

                lblStatus.setText("Status Tahapan Anda: Belum Mendaftarkan Diri");

            }

        } catch (Exception e) {

            lblStatus.setText("Status: Gagal memuat data");

        }

    }


    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {                                        

        try {

            String selectedItem = cmbLomba.getSelectedItem().toString();

            String idLomba = selectedItem.split(" - ")[0]; // Ambil ID lomba di depan

           

            Connection c = Koneksi.configDB();

            Statement s = c.createStatement();

           

            // Cek apakah sudah pernah daftar

            ResultSet r = s.executeQuery("SELECT * FROM pendaftaran WHERE user_id='" + idLoginSiswa + "'");

            if (r.next()) {

                JOptionPane.showMessageDialog(this, "Anda sudah terdaftar di lomba!");

                return;

            }

           

            String sql = "INSERT INTO pendaftaran (user_id, lomba_id, status) VALUES ('"

                    + idLoginSiswa + "', '" + idLomba + "', 'Dokumen dalam Tinjauan')";

            s.executeUpdate(sql);

            JOptionPane.showMessageDialog(this, "Pendaftaran Berhasil! Menunggu verifikasi admin.");

            cekStatusSiswa();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Gagal mendaftar: " + e.getMessage());

        }

    }  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cmbLomba = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DASHBOARD SISWA - BI GOT TALENT");

        cmbLomba.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Pilih Mata Lomba");

        jButton1.setText("Daftar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(lblStatus)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
            }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbLomba;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
