package by.imix.games.gamecore.implDefault;

import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miha on 16.12.2014.
 */
public class SpringRoom implements Room {
    @JsonIgnore //иначе json уходит в цикл
    //список играков
    private List<UserRoom> listUser;
    //список наблюдателей
    private List<UserRoom> listViewUser;
    //максимальное количество играков
    private int maxCountUser=0;
    //Номер комнаты
    private long numberRoom;
    //Откыта для входа
    private volatile Boolean openRoom=true;

    public SpringRoom() {
        this.listUser = new ArrayList<UserRoom>();
        this.listViewUser = new ArrayList<UserRoom>();
    }

    @Override
    public boolean addUser(UserRoom user) {
        if (isOpenRoom()) {
            synchronized (openRoom) {
                //если комната закрыта не добавлять
                if (isOpenRoom()) {
                    //если юзер присутствует не добавлять
                    for (UserRoom userR : listUser) {
                        if (userR.getName().equals(user.getName())) {
                            return false;
                        }
                    }
                    //если пользователей больше чем макс количество не добавлять
                    if (listUser.size() <= maxCountUser) {
                        listUser.add(user);
                        if (listUser.size() == maxCountUser) {
                            openRoom = false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeUser(UserRoom user) {
        synchronized (openRoom) {
            return listUser.remove(user);
        }
    }

    @Override
    //Получить номер комнаты
    public long getNumberRoom(){
        return numberRoom;
    }

    @Override
    public void setNumberRoom(long numberRoom) {
        this.numberRoom=numberRoom;
    }

    @Override
    public boolean isPermission(UserRoom user) {
        if(this.isOpenRoom()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOpenRoom() {
        return openRoom;
    }

    @Override
    public int countPerson() {
        return listUser.size();
    }

    @Override
    public List<? extends UserRoom> getListUser() {
        return listUser;
    }

    public int getMaxCountUser() {
        return maxCountUser;
    }

    public void setMaxCountUser(int maxCountUser) {
        this.maxCountUser = maxCountUser;
    }

    @Override
    public List<? extends UserRoom> getListViewUser() {
        return listViewUser;
    }
}
