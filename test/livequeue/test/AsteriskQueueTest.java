/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.test;

import java.util.ArrayList;
import junit.framework.TestCase;
import livequeue.asterisk.manager.queue.AsteriskQueue;
import livequeue.asterisk.manager.queue.QueueEntry;
import livequeue.asterisk.manager.queue.QueueMember;

/**
 *
 * @author jayson
 */
public class AsteriskQueueTest extends TestCase {

    private AsteriskQueue astQueue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        astQueue = new AsteriskQueue();

    }

    @Override
    protected void tearDown() throws Exception {
        super.setUp();
        astQueue = null;

    }

    public void testWeHaveAnAsteriskQueue() {
        assertEquals(astQueue.getClass().toString(), "class livequeue.asterisk.manager.queue.AsteriskQueue");
    }

    public void testJustOneSetterAndGetter() {
        astQueue.setName("Jayson");
        assertEquals("Jayson", astQueue.getName());
    }

    public void testCanAddAQueueMember() {
        QueueMember member = new QueueMember();
        member.setName("Yoyo");
        astQueue.addMember(member);
    }

    public void testCanGetAQueueMember() {
        QueueMember member1 = new QueueMember();
        member1.setName("Yoyo");
        astQueue.addMember(member1);
        ArrayList<QueueMember> members = astQueue.getMembers();
        QueueMember member2 = members.get(0);
        assertEquals(member1, member2);
    }

    public void testCanAddQueueEntry() {
        QueueEntry entry = new QueueEntry();
        entry.setCallerId("8675309");
        astQueue.addEntry(entry);
    }

    public void testCanGetAQueueEntry() {
        QueueEntry entry1 = new QueueEntry();
        entry1.setCallerId("8675309");
        astQueue.addEntry(entry1);
        ArrayList<QueueEntry> entries = astQueue.getEntries();
        QueueEntry entry2 = entries.get(0);
        assertEquals(entry1, entry2);
    }
}
