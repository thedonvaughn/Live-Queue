package livequeue.asterisk.manager.action;

import livequeue.asterisk.manager.ManagerConnection;

/**
 *
 * @author Jayson Vaughn
 */
public class LoginAction implements SendAction {

    private ManagerConnection managerConnection;

    public LoginAction() {
    }

    public LoginAction(ManagerConnection managerConnection) {
        this.managerConnection = managerConnection;
    }

    public ManagerConnection getManagerConnection() {
        return managerConnection;
    }

    public void setManagerConnection(ManagerConnection managerConnection) {
        this.managerConnection = managerConnection;
    }

    public void sendAction() {
        managerConnection.getWriter().print("action: login\r\n");
        managerConnection.getWriter().print("username: " + managerConnection.getUsername() + "\r\n");
        managerConnection.getWriter().print("secret: " + managerConnection.getPassword() + "\r\n");
        managerConnection.getWriter().print("events: " + managerConnection.getEvents() + "\r\n");
        managerConnection.getWriter().print("\r\n");
        managerConnection.getWriter().flush();
    }
}
