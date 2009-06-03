/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package livequeue.asterisk.queue;

/**
 *
 * @author Jayson Vaughn
 */
public class QueueMember {
    private String queue;
    private String name;
    private String location;
    private String membership;
    private int penalty;
    private int callsTaken;
    private String lastCall;
    private int status;
    private int paused;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String q) {
        queue = q;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String l) {
        location = l;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String m) {
        membership = m;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int p) {
        penalty = p;
    }

    public int getCallsTaken() {
        return callsTaken;
    }

    public void setCallsTaken(int c) {
        callsTaken = c;
    }

    public String getLastCall() {
        return lastCall;
    }

    public void setLastCall(String l) {
        lastCall = l;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int s) {
        status = s;
    }

    public int getPaused() {
        return paused;
    }

    public void setPaused(int p) {
        paused = p;
    }

}
