/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LiveQueueMainGUI.java
 *
 * Created on Apr 26, 2009, 2:27:18 PM
 */
package livequeue.gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jayson Vaughn
 */
public class LiveQueueMainGUI extends javax.swing.JFrame {

    /** Creates new form LiveQueueMainGUI */
    public LiveQueueMainGUI() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        queueButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileExit = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editProperties = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Live Queue - Asterisk Queue Monitoring");

        queueButton.setText("Monitor Queues");
        queueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queueButtonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("LiveQueue - Live Monitoring of Asterisk Queues\n(c) 2009 - Jayson Vaughn\n(vaughn.jayson@gmail.com)\n\nDo not forget to set your Asterisk Manager Interface.\nEdit -> Properties");
        jScrollPane1.setViewportView(jTextArea1);

        fileMenu.setText("File");

        fileExit.setText("Exit");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileExit);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");

        editProperties.setText("Properties");
        editProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPropertiesActionPerformed(evt);
            }
        });
        editMenu.add(editProperties);

        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addComponent(queueButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queueButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queueButtonActionPerformed
// TODO add your handling code here:
        LiveQueueRealTimeGUI realTimeView = new LiveQueueRealTimeGUI();
        realTimeView.setVisible(true);
    }//GEN-LAST:event_queueButtonActionPerformed

    private void fileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitActionPerformed
// TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_fileExitActionPerformed

    private void editPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPropertiesActionPerformed
        // TODO add your handling code here:
        LiveQueuePropertyWindow properties = new LiveQueuePropertyWindow();
        properties.setVisible(true);
    }//GEN-LAST:event_editPropertiesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LiveQueueMainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editProperties;
    private javax.swing.JMenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton queueButton;
    // End of variables declaration//GEN-END:variables
}
