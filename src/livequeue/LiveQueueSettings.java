package livequeue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author Jayson Vaughn
 */
public class LiveQueueSettings implements Serializable {

    private String host = "localhost";
    private int port = 5038;
    private String userName = "username";
    private String secret = "password";
    private String homeDir = System.getProperty("user.home");
    private File propertiesFile = new File(getHomeDir() + File.separator + "liveQueue.config");

    public LiveQueueSettings() {

        if (getPropertiesFile().exists()) {
            Properties props = new Properties();
            try {
                props.load(new FileInputStream(getPropertiesFile()));
                this.setUserName(props.getProperty("livequeue.username"));
                this.setSecret(props.getProperty("livequeue.secret"));
                this.setHost(props.getProperty("livequeue.host"));
                this.setPort(Integer.parseInt(props.getProperty("livequeue.port")));
            } catch (java.io.IOException e) {
                System.out.println(e.getStackTrace());
            }
        }

    }

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public File getPropertiesFile() {
        return propertiesFile;
    }

    public void setPropertiesFile(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param aHost the host to set
     */
    public void setHost(String aHost) {
        host = aHost;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public void setPort(int aPort) {
        port = aPort;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param aUserName the userName to set
     */
    public void setUserName(String aUserName) {
        userName = aUserName;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param aSecret the secret to set
     */
    public void setSecret(String aSecret) {
        secret = aSecret;
    }

    public void saveSettings() {
        Properties props = new Properties();
        props.setProperty("livequeue.username", getUserName());
        props.setProperty("livequeue.secret", getSecret());
        props.setProperty("livequeue.host", getHost());
        props.setProperty("livequeue.port", String.valueOf(getPort()));
        try {
            props.store(new FileOutputStream(getPropertiesFile()), "LiveQueue Properties");
        } catch (java.io.IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
