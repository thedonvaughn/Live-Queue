package livequeue.asterisk.manager.action;

import livequeue.asterisk.manager.queue.*;
import java.util.ArrayList;
import livequeue.asterisk.manager.ManagerConnection;
import java.io.*;

/**
 *
 * @author Jayson Vaughn
 */
public class QueueStatusAction implements GetAction {

    private ManagerConnection managerConnection;

    public QueueStatusAction() {
    }

    public QueueStatusAction(ManagerConnection managerConnection) {
        this.managerConnection = managerConnection;
    }

    public ArrayList getActionResponse() {
        String queueStatusResponse;
        ArrayList astQueues = new ArrayList();
        this.sendAction();
        try {
            while ((queueStatusResponse = managerConnection.getReader().readLine()) != null) {
                // System.out.println(queueStatusResponse);
                if (queueStatusResponse.contains("QueueStatusComplete")) {
                    break;
                }
                if (queueStatusResponse.contains("QueueParams")) {
                    AsteriskQueue astQueue = buildQueue();
                    astQueues.add(astQueue);
                }

                if (queueStatusResponse.contains("QueueMember")) {
                    QueueMember astMember = buildQueueMember();
                    for (AsteriskQueue q : (ArrayList<AsteriskQueue>) astQueues) {
                        if (q.getName().equals(astMember.getQueue())) {
                            q.addMember(astMember);
                        }
                    }
                }

                if (queueStatusResponse.contains("QueueEntry")) {
                    QueueEntry astEntry = buildQueueEntry();
                    for (AsteriskQueue q : (ArrayList<AsteriskQueue>) astQueues) {
                        if (q.getName().equals(astEntry.getQueue())) {
                            q.addEntry(astEntry);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return astQueues;
    }

    public void sendAction() {
        managerConnection.getWriter().print("action: QueueStatus\r\n");
        managerConnection.getWriter().print("\r\n");
        managerConnection.getWriter().flush();
    }

    private AsteriskQueue buildQueue() throws IOException {
        AsteriskQueue astQueue = new AsteriskQueue();
        astQueue.setName(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        astQueue.setCalls(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setHoldTime(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setCompleted(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setAbandonded(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setServiceLevel(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setServiceLevelPerf(Double.parseDouble(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        astQueue.setWeight(Double.parseDouble(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        return astQueue;
    }

    private QueueMember buildQueueMember() throws IOException {
        QueueMember member = new QueueMember();
        member.setQueue(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        member.setName(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        member.setLocation(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        member.setMembership(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        member.setPenalty(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        member.setCallsTaken(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        member.setLastCall(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        member.setStatus(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        member.setPaused(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        return member;
    }

    private QueueEntry buildQueueEntry() throws IOException {
        QueueEntry entry = new QueueEntry();
        entry.setQueue(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        entry.setPosition(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        entry.setChannel(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        entry.setCallerId(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        entry.setCallerIdName(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", ""));
        entry.setWait(Integer.parseInt(managerConnection.getReader().readLine().trim().split(":")[1].replaceAll(" ", "")));
        return entry;
    }
}
