package by.imix.games.gamecore.user;

import by.imix.games.monopoly.web.ActionUser;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by miha on 27.07.2017.
 * User that can be participate auction
 */
public interface UserAuction {


    //Get list all available actions for user
    Set getAvailableAction();

    //set list all available actions
    void setAvailableAction(Set availableAction);



    //get ll auction user and clear
    List<ActionUser> getAndClearActionsAllUser();
    //add user to auction
    boolean addActionUser(ActionUser actionsAllUser);

     /*Remove action
    void removeAction(String action);
    add action
    void addAction(String action);
    add actions
    void addActions(String... actions);
    Check that user may to do action
    boolean hasAction(String action);*/
}
