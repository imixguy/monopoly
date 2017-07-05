package by.imix.games.gamecore;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by miha on 16.12.2014.
 * Пользователь комнаты
 */
public interface UserRoom {
    static final String CREATE_ROOM="CreateRoom";
    //Внешний пользователь  -  с логинки
    void setUser(Object user);
    //имя пользовавателя
    String getName();
    //дата последнего действия
    Date getLastIn();
    //действие произошло
    void checkedTime();
    //Список комнат в которых находится пользоватьль
    List<Room> getActiveRooms();
    //Удалить событие
//    void removeAction(String action);
    //Добавить событие
//    void addAction(String action);
    //Добавить событие
//    void addActions(String... actions);
    //Имеет ли пользователь право совершать действие
//    boolean hasAction(String action);
    //Получить все доступные действия , которые может совершать пользователь
    Set getAvailableAction();
    void setAvailableAction(Set availableAction);

}
