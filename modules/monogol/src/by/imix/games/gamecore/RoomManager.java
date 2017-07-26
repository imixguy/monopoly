package by.imix.games.gamecore;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 * Manager for room
 */
public interface RoomManager {
    //create room
    Room createRoom();
    //get list all room
    List<? extends Room> getAllRoom();
    //get list room for specific user
    List<? extends Room> getAllPermissionRoom();
    //get roum about number
    Room getRoomByNumber(long numberRoom);
    //get room about user
    List<? extends Room> getRoomByUser();
    //get user about room
    UserRoom getUser();
}
