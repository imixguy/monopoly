package by.imix.games.gamecore.game;

import by.imix.games.gamecore.Room;

import java.util.List;

/**
 * Created by miha on 26.07.2017.
 * Game in room
 */
public interface GameRoom {
    //get room
    Room getRoom();

    //set room
    void setRoom(Room room);

    //Have started game?
    boolean isStartGame();

    //next players
    void nextGamer();

    //get next active user
    UserRoom getCurentUser();

    //get list active user
    List<UserRoom> getListUser();

    //get list trace user
    List<UserRoom> getListViewUser();

    //Stop game(lose)
    void gameEnd(UserRoom user);

    //Close window and exit from game
    void gameClose(UserRoom user);
}
