package by.imix.games.gamecore;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 * Room
 */
public interface Room {
    //add user
    boolean addUser(UserRoom user);
    //Remove user
    boolean removeUser(UserRoom user);
    //Get room number
    long getNumberRoom();
    //set room number
    void setNumberRoom(long numberRoom);
    //Check allow rom for specific user
    boolean isPermission(UserRoom user);
    //Check that room is opened for entrance
    boolean isOpenRoom();
    //count people in room
    int countPerson();
    //List user in room
    List<? extends UserRoom> getListUser();
    //Max count user that can be to room
    void setMaxCountUser(int count);
    //Max count active user that can be to room
    int getMaxCountUser();
    //List traced users for game
    List<? extends UserRoom> getListViewUser();
}