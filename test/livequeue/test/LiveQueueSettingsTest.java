package livequeue.test;

import junit.framework.TestCase;
import livequeue.gui.LiveQueueSettings;

/**
 *
 * @author Jayson Vaughn
 */
public class LiveQueueSettingsTest extends TestCase {
    
    public LiveQueueSettingsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        settings = new LiveQueueSettings();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWeCanSetUserName() {
        String userName = "foobar";
        settings.setUserName(userName);
        assertEquals(settings.getUserName(), userName);
    }

    public void testWeCanSetSecret() {
        String secret = "aBigSecret";
        settings.setSecret(secret);
        assertEquals(settings.getSecret(), secret);
    }

    public void testWeCanSetPort() {
        int port = 5038;
        settings.setPort(port);
        assertEquals(settings.getPort(), port);
    }

    public void testWeCanSetHost() {
        String hostName = "localhost";
        settings.setHost(hostName);
        assertEquals(settings.getHost(), hostName);
    }

    private LiveQueueSettings settings;
    
}
