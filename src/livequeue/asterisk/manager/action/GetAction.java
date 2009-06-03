/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package livequeue.asterisk.manager.action;

import java.util.ArrayList;

/**
 *
 * @author Jayson Vaughn
 */
public interface GetAction extends SendAction {

    public ArrayList getActionResponse();
}
