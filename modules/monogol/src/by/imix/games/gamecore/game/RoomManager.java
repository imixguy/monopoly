package by.imix.games.gamecore.game;

import by.imix.games.gamecore.*;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 * Manager for room
 */
public interface RoomManager {
    //create room
    by.imix.games.gamecore.Room createRoom();
    //get list all room
    List<? extends by.imix.games.gamecore.Room> getAllRoom();
    //get list room for specific user
    List<? extends by.imix.games.gamecore.Room> getAllPermissionRoom();
    //get roum about number
    by.imix.games.gamecore.Room getRoomByNumber(long numberRoom);
    //get room about user
    List<? extends by.imix.games.gamecore.Room> getRoomByUser();
    //get user about room
    UserRoom getUser();
}
