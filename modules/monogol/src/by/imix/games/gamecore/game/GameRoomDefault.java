package by.imix.games.gamecore.game;


import by.imix.games.gamecore.user.UserRoom;

import java.util.List;

/**
 * Created by miha on 26.07.2017.
 */
public class GameRoomDefault implements GameRoom {
     /**{@inheritDoc}*/
     @Override
    public Room getRoom() {
        return null;
    }

     /**{@inheritDoc}*/
     @Override
    public void setRoom(Room room) {

    }

     /**{@inheritDoc}*/
     @Override
    public boolean isStartGame() {
        return false;
    }

     /**{@inheritDoc}*/
     @Override
    public void nextGamer() {

    }

     /**{@inheritDoc}*/
     @Override
    public UserRoom getCurentUser() {
        return null;
    }

     /**{@inheritDoc}*/
     @Override
    public List<UserRoom> getListUser() {
        return null;
    }

     /**{@inheritDoc}*/
     @Override
    public List<UserRoom> getListViewUser() {
        return null;
    }

     /**{@inheritDoc}*/
     @Override
    public void gameEnd(UserRoom user) {

    }

     /**{@inheritDoc}*/
     @Override
    public void gameClose(UserRoom user) {

    }
}
