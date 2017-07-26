package by.imix.games.gamecore.user;

import by.imix.games.gamecore.game.Room;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 * Room User
 */
public interface UserRoom extends UserGame {
    static final String CREATE_ROOM = "CreateRoom";

    //List room where user is
    List<Room> getActiveRooms();

    //player loose
    boolean isLoose();
    //set loose flag
    void setLoose(boolean key);

    //player win
    void setWin(boolean b);
    //set win flag
    boolean isWin();


}
