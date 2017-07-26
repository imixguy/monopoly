package by.imix.games.gamecore.user;

/**
 * Created by miha on 27.07.2017.
 * User Game
 */
public interface UserGame {
    //outside user
    void setUser(Object user);
    //Name User
    String getName();
    //user is active user
    boolean isActivGamer();
    //set active user
    void setActivGamer(boolean activGamer);
}
