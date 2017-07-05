package by.imix.games.gamecore;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 */
public interface RoomManager {
    //создать комнату
    Room createRoom();
    //получить список всех комнат
    List<? extends Room> getAllRoom();
    //получить список комнат в которые можно войти конкретному пользователю
    List<? extends Room> getAllPermissionRoom();
    //получить комнату по номеру
    Room getRoomByNumber(long numberRoom);
    //получить комнаты по пользователю
    List<? extends Room> getRoomByUser();
    //получить пользователя для комнаты
    UserRoom getUser();
}
