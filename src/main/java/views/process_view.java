package views;

import controller.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ProcessModel;
import utils.EnumProcess;

public class process_view extends javax.swing.JFrame {

    private final DefaultTableModel ModT;
    private final Controller controller;

    public process_view() {

        initComponents();
        loadComobobox();
        this.controller = new Controller();
        ModT = (DefaultTableModel) tab.getModel();

    }

    private void loadComobobox() {

        cb1.setModel(new DefaultComboBoxModel(EnumProcess.values()));
    }

    private void processFilter() {
        ArrayList<ProcessModel> result = new ArrayList<>();
        ArrayList<ProcessModel> result2 = new ArrayList<>();
        if (tf1.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Indica el número de procesos a listar");
            return;
        }

        result = this.controller.processBuilder.getProcess();
        System.out.println(cb1.getSelectedItem().toString());
        String selectedIndex = cb1.getSelectedItem().toString();

        result2 = this.controller.processManager.filterProcess(result, EnumProcess.valueOf(selectedIndex));
        int n = Integer.parseInt(tf1.getText().trim());
        ModT.setRowCount(n);

        for (int i = 0; i < n; i++) {
            ModT.setValueAt(i + 1 + "", i, 0);//Indice
            ModT.setValueAt(result2.get(i).getPid(), i, 1);//Pid
            ModT.setValueAt(result2.get(i).getProcessName(), i, 2);//Nombre
            ModT.setValueAt(result2.get(i).getUserName(), i, 3);//Usuario
            ModT.setValueAt(result2.get(i).getProcessName(), i, 4);//Descripcion
            ModT.setValueAt(result2.get(i).getPid(), i, 5);//Prioridad
            ModT.setValueAt(result2.get(i).getMemoryUse(), i, 6);//Prioridad
            ModT.setValueAt(result2.get(i).getCpuTime(), i, 7);//Prioridad
            ModT.setValueAt(result2.get(i).getArrivalTime(), i, 8);//Prioridad

        }

//        this.controller.dataBaseController.addData(selectedIndex, result2, n);
        result.clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cb1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        bt1 = new javax.swing.JButton();
        tf1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cb1.setBackground(new java.awt.Color(255, 255, 255));
        cb1.setForeground(new java.awt.Color(255, 255, 255));
        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mayo uso CPU", "Menor uso CPU", "Mayor uso RAM", "Menos uso RAM", "Procesos del sistema", "Procesos de usuario" }));
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        tab.setForeground(new java.awt.Color(0, 0, 102));
        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "PID", "NOMBRE", "USUARIO", "DESCRIPCION", "PRIORIDAD", "MEMORIA", "CPU", "Tiempo llegada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tab);

        bt1.setText("Buscar");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(688, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt1)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
//        ArrayList<ProcessModel> result = new ArrayList<>();
//        result = this.controller.processBuilder.getProcess();
//     
        processFilter();
        // TODO add your handling code here:
    }//GEN-LAST:event_bt1ActionPerformed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb1ActionPerformed

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
            java.util.logging.Logger.getLogger(process_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(process_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(process_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(process_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new process_view().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tab;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
