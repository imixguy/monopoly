package by.imix.games.gamecore.game;


import by.imix.games.gamecore.user.UserRoom;

import java.util.List;

/**
 * Created by miha on 26.07.2017.
 */
public class GameRoomDefault implements GameRoom {
    @Override
    public Room getRoom() {
        return null;
    }

    @Override
    public void setRoom(Room room) {

    }

    @Override
    public boolean isStartGame() {
        return false;
    }

    @Override
    public void nextGamer() {

    }

    @Override
    public UserRoom getCurentUser() {
        return null;
    }

    @Override
    public List<UserRoom> getListUser() {
        return null;
    }

    @Override
    public List<UserRoom> getListViewUser() {
        return null;
    }

    @Override
    public void gameEnd(UserRoom user) {

    }

    @Override
    public void gameClose(UserRoom user) {

    }
}
