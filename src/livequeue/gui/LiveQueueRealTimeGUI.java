/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LiveQueueRealTimeGUI.java
 *
 * Created on Apr 27, 2009, 8:02:45 AM
 */
package livequeue.gui;

import livequeue.LiveQueueSettings;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import livequeue.asterisk.manager.ManagerConnection;
import livequeue.asterisk.manager.queue.AsteriskQueue;
import livequeue.asterisk.manager.queue.QueueEntry;
import livequeue.asterisk.manager.queue.QueueMember;

/**
 *
 * @author Jayson Vaughn
 */
public class LiveQueueRealTimeGUI extends javax.swing.JFrame {

    public final class ClientHandler implements Runnable {

        public void run() {
            try {
                while (true) {
                    Thread.sleep(2000);
                    EventQueue.invokeLater(new updateQueueStatus());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public final class updateQueueStatus implements Runnable {

        public void run() {
            try {
                updateRealTimeView();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /** Creates new form LiveQueueRealTimeGUI */
    public LiveQueueRealTimeGUI() {
        initComponents();
        Thread t = new Thread(new ClientHandler());
        t.start();
    }

    public void initRealTimeViewText() {
        // !!!!!!!!!!!!!!!!!
        // DEPRECATED
        // !!!!!!!!!!!!!!!!!

        LiveQueueSettings settings = new LiveQueueSettings();
        ManagerConnection conn = new ManagerConnection(settings.getHost(), settings.getUserName(), settings.getSecret(), settings.getPort());
        if (conn.login()) {
            jTabbedPane1.removeAll();
            ArrayList<AsteriskQueue> astQueues = conn.queueStatus();
            for (AsteriskQueue q : astQueues) {
                javax.swing.JTextArea queueText = new javax.swing.JTextArea();
                queueText.setEditable(false);
                queueText.append("Calls Waiting: " + q.getCalls() +
                        " Completed: " + q.getCompleted() + " Abandoned: " + q.getAbandonded() +
                        " HoldTime: " + q.getHoldTime() + " ServiceLvl: " + q.getServiceLevel() +
                        " Max: " + q.getMax() + " ServiceLvlPerf: " + q.getServiceLevelPerf() +
                        " Weight: " + q.getWeight() + "\n\n");

                for (QueueMember m : q.getMembers()) {
                    queueText.append("\n" + m.getName() +
                            " - Location: " + m.getLocation() + " Status: " + m.getStatus() +
                            " CallsTaken: " + m.getCallsTaken() + " LastCall: " + m.getLastCall() +
                            " Membership: " + m.getMembership() + " Penalty: " + m.getPenalty() +
                            " Paused: " + m.getPaused() + "\n");

                }

                for (QueueEntry e : q.getEntries()) {
                    queueText.append("\n" + e.getPosition() + " - " + e.getCallerId() +
                            " " + e.getCallerIdName() + " Wait: " + e.getWait() + " Channel: " + e.getChannel() + "\n");
                }


                jTabbedPane1.add(q.getName(), queueText);
            }
            conn.logoff();
        }

    }

    public void initRealTimeViewTable() {
        LiveQueueSettings settings = new LiveQueueSettings();
        ManagerConnection conn = new ManagerConnection(settings.getHost(), settings.getUserName(), settings.getSecret(), settings.getPort());
        if (conn.login()) {
            jTabbedPane1.removeAll();
            ArrayList<AsteriskQueue> astQueues = (ArrayList<AsteriskQueue>) conn.queueStatus();
            for (AsteriskQueue q : astQueues) {
               // Set the Table Columns
                String[] queueColumn = {"Calls Waiting", "Completed", "Abandoned", "HoldTime", "ServiceLvl", "Max", "ServiceLvlPerf", "Weight"};
                String[] memberColumn = {"Name", "Location", "Status", "CallsTaken", "LastCall", "Membership", "Penalty", "Paused"};
                String[] callerColumn = {"Position", "Caller ID", "Name", "Wait", "Channel"};
                Vector columnNamesV = new Vector(Arrays.asList(queueColumn));
                Vector queueRow = new Vector();
                Vector queueColumnData = new Vector();
                Vector space = new Vector();

                // Get Queue Stats
                queueColumnData.add(q.getCalls());
                queueColumnData.add(q.getCompleted());
                queueColumnData.add(q.getAbandonded());
                queueColumnData.add(q.getHoldTime());
                queueColumnData.add(q.getServiceLevel());
                queueColumnData.add(q.getMax());
                queueColumnData.add(q.getServiceLevelPerf());
                queueColumnData.add(q.getWeight());
                queueRow.add(queueColumnData);

                // Add Space and next Column Headers
                queueRow.add(space);
                queueRow.add(new Vector(Arrays.asList(memberColumn)));

                // Get Queue Member Stats
                for (QueueMember m : q.getMembers()) {
                    Vector memberColumnData = new Vector();
                    memberColumnData.add(m.getName());
                    memberColumnData.add(m.getLocation());
                    memberColumnData.add(m.getStatus());
                    memberColumnData.add(m.getCallsTaken());
                    memberColumnData.add(m.getLastCall());
                    memberColumnData.add(m.getMembership());
                    memberColumnData.add(m.getPenalty());
                    memberColumnData.add(m.getPaused());
                    queueRow.add(memberColumnData);
                }

                // Add Space and Next Column headers
                queueRow.add(space);
                queueRow.add(new Vector(Arrays.asList(callerColumn)));

                // Get Queue Entry Stats
                for (QueueEntry e : q.getEntries()) {
                    Vector callerColumnData = new Vector();
                    callerColumnData.add(e.getPosition());
                    callerColumnData.add(e.getCallerId());
                    callerColumnData.add(e.getCallerIdName());
                    callerColumnData.add(e.getWait());
                    callerColumnData.add(e.getChannel());
                    queueRow.add(callerColumnData);
                }

                TableModel model = new DefaultTableModel(queueRow, columnNamesV);
                JTable table = new JTable(model);
                javax.swing.JScrollPane queueScrollPane = new javax.swing.JScrollPane(table);
                jTabbedPane1.add(q.getName(), queueScrollPane);
            }
            conn.logoff();
        }

    }

    public void updateRealTimeView() {
        int currentIndex = jTabbedPane1.getSelectedIndex();
        initRealTimeViewTable();
        jTabbedPane1.setSelectedIndex(currentIndex);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Live Queue - Live Monitor");

        fileMenu.setText("File");

        fileExit.setText("Close");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileExit);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        initRealTimeViewTable();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_fileExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LiveQueueRealTimeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
