/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.asterisk.manager.queue;

import java.util.ArrayList;

/**
 *
 * @author Jayson Vaughn
 */
public class AsteriskQueue {

    private String name;
    private int max;
    private int calls;
    private int holdTime;
    private int completed;
    private int abandonded;
    private int serviceLevel;
    private double serviceLevelPerf;
    private double weight;
    private ArrayList<QueueMember> members;
    private ArrayList<QueueEntry> entries;

    public AsteriskQueue() {
        members = new ArrayList<QueueMember>();
        entries = new ArrayList<QueueEntry>();
    }

    public void addMember(QueueMember member) {
        members.add(member);
    }

    public ArrayList<QueueMember> getMembers() {
        return members;
    }

    public void addEntry(QueueEntry entry) {
        entries.add(entry);
    }

    public ArrayList<QueueEntry> getEntries() {
        return entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int m) {
        max = m;
    }

    public int getCalls() {
        return calls;
    }

    public void setCalls(int c) {
        calls = c;
    }

    public int getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(int ht) {
        holdTime = ht;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int c) {
        completed = c;
    }

    public int getAbandonded() {
        return abandonded;
    }

    public void setAbandonded(int a) {
        abandonded = a;
    }

    public int getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(int sl) {
        serviceLevel = sl;
    }

    public double getServiceLevelPerf() {
        return serviceLevelPerf;
    }

    public void setServiceLevelPerf(double slp) {
        serviceLevelPerf = slp;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }
}
