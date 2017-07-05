package by.imix.games.gamecore;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 */
public interface Room {
    //Добавить пользователя
    boolean addUser(UserRoom user);
    //Удалить пользователя
    boolean removeUser(UserRoom user);
    //Получить номер комнаты
    long getNumberRoom();
    //Установить номер комнаты
    void setNumberRoom(long numberRoom);
    //доступна ли комната конкретному пользователю
    boolean isPermission(UserRoom user);
    //открыта ли комната для входа
    boolean isOpenRoom();
    //количество людей в комнате
    int countPerson();
    //список пользователей
    List<? extends UserRoom> getListUser();
    //макс число игроков
    void setMaxCountUser(int count);
    int getMaxCountUser();
    //список пользователей наблюдающих за игрой
    List<? extends UserRoom> getListViewUser();
}