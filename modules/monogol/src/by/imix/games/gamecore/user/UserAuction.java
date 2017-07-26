package by.imix.games.gamecore.user;

import java.util.Date;
import java.util.Set;

/**
 * Created by miha on 27.07.2017.
 * User that can be participate auction
 */
public interface UserAuction {
    //Date of last action
    Date getLastIn();

    //Get list all available actions for user
    Set getAvailableAction();

    //set list all available actions
    void setAvailableAction(Set availableAction);

    //Action was done
    void checkedTime();

     /*Remove action
    void removeAction(String action);
    add action
    void addAction(String action);
    add actions
    void addActions(String... actions);
    Check that user may to do action
    boolean hasAction(String action);*/
}
