package livequeue.asterisk.manager.action;

import java.util.ArrayList;

/**
 *
 * @author Jayson Vaughn
 */
public interface GetAction extends SendAction {

    public ArrayList getActionResponse();
}
