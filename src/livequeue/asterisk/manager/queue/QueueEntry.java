/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.asterisk.manager.queue;

/**
 *
 * @author Jayson Vaughn
 */
public class QueueEntry {

    private String queue;
    private int position;
    private String channel;
    private String callerId;
    private String callerIdName;
    private int wait;

    public void setQueue(String q) {
        queue = q;
    }

    public String getQueue() {
        return queue;
    }

    public void setPosition(int p) {
        position = p;
    }

    public int getPosition() {
        return position;
    }

    public void setChannel(String c) {
        channel = c;
    }

    public String getChannel() {
        return channel;
    }

    public void setCallerId(String cid) {
        callerId = cid;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerIdName(String cidName) {
        callerIdName = cidName;
    }

    public String getCallerIdName() {
        return callerIdName;
    }

    public void setWait(int w) {
        wait = w;
    }

    public int getWait() {
        return wait;
    }
}
