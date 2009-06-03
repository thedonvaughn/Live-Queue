/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.asterisk.manager.action;

import livequeue.asterisk.manager.ManagerConnection;

/**
 *
 * @author jayson
 */
public class LogoffAction implements SendAction {

    private ManagerConnection managerConnection;

    public LogoffAction() {
    }

    public LogoffAction(ManagerConnection managerConnection) {
        this.managerConnection = managerConnection;
    }

    public ManagerConnection getManagerConnection() {
        return managerConnection;
    }

    public void setManagerConnection(ManagerConnection managerConnection) {
        this.managerConnection = managerConnection;
    }

    public void sendAction() {
        managerConnection.getWriter().print("action: logoff\r\n");
        managerConnection.getWriter().print("\r\n");
        managerConnection.getWriter().flush();
    }
}
