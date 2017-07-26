package by.imix.games.gamecore;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by miha on 16.12.2014.
 * Room User
 */
public interface UserRoom {
    static final String CREATE_ROOM = "CreateRoom";

    //outside user
    void setUser(Object user);

    //Name User
    String getName();

    //Date of last action
    Date getLastIn();

    //Action was done
    void checkedTime();

    //List room where user is
    List<Room> getActiveRooms();

    /*Remove action
    void removeAction(String action);
    add action
    void addAction(String action);
    add actions
    void addActions(String... actions);
    Check that user may to do action
    boolean hasAction(String action);*/

    //Get list all available actions for user
    Set getAvailableAction();

    //set list all available actions
    void setAvailableAction(Set availableAction);

}
