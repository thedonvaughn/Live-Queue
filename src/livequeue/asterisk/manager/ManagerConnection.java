/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.asterisk.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import livequeue.asterisk.manager.action.GetAction;
import livequeue.asterisk.manager.action.LoginAction;
import livequeue.asterisk.manager.action.LogoffAction;
import livequeue.asterisk.manager.action.QueueStatusAction;
import livequeue.asterisk.manager.action.SendAction;

/**
 *
 * @author Jayson Vaughn
 */
public class ManagerConnection {

    private String host;
    private String username;
    private String password;
    private int port;
    private Socket amiSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean isLoggedIn = false;
    private String events = "off";

    public ManagerConnection(String aHost, String aUser, String aPass) {
        this.host = aHost;
        this.username = aUser;
        this.password = aPass;
        this.port = 5038;
    }

    public ManagerConnection(String aHost, String aUser, String aPass, int aPort) {
        this(aHost, aUser, aPass);
        this.port = aPort;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }

    public void setPassword(String aPassword) {
        password = aPassword;
    }

    public void setHost(String aHost) {
        host = aHost;
    }

    public void setPort(int aPort) {
        port = aPort;
    }

    public void setEvents(String anEventSetting) {
        events = anEventSetting;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getEvents() {
        return events;
    }

    public boolean login() {
        String loginResponse;
        establishConnection();
        sendLoginAction();
        try {
            while ((loginResponse = reader.readLine()) != null) {
                if (loginResponse.contains("accepted")) {
                    isLoggedIn = true;
                    break;
                } else if (loginResponse.contains("failed")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return isLoggedIn;
    }

    public void logoff() {
        sendLogoffAction();
        try {
            amiSocket.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void establishConnection() {
        try {
            amiSocket = new Socket(host, port);
            InputStreamReader streamReader = new InputStreamReader(amiSocket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(amiSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendLoginAction() {
        SendAction loginAction = new LoginAction(this);
        loginAction.sendAction();

    }

    private void sendLogoffAction() {
        SendAction logoffAction = new LogoffAction(this);
        logoffAction.sendAction();
    }

    public ArrayList queueStatus() {
        GetAction queueAction = new QueueStatusAction(this);
        return queueAction.getActionResponse();
    }
}