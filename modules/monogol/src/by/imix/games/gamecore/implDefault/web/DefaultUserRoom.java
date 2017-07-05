package by.imix.games.gamecore.implDefault.web;

import by.imix.games.gamecore.ActionRoomI;
import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import rw.gcktc.cms.usermanager.User;

import java.util.*;

/**
 * Created by miha on 16.12.2014.
 */
public abstract class DefaultUserRoom implements UserRoom {
    //Внешний класс пользователя
    @JsonIgnore
    private User user;
    //список комнат в которые вошел пользователь
    @JsonIgnore
    private List<Room> activeRooms;
    //максимальное количество комнат в которые может войти пользователь
    private int maxCountActiveRoom;
    @JsonIgnore
    private Date lastIn;
    //список доступных действий
    @JsonIgnore
    //private Set<String> listAvailableActions;
    private Set availableAction;

    public  DefaultUserRoom(){this(null,1);}

    public DefaultUserRoom(User user){
        this(user,1);
    }

    public DefaultUserRoom(User user, int maxCountActiveRoom) {
        this.user = user;
        this.activeRooms=new ArrayList<>();
        this.maxCountActiveRoom=maxCountActiveRoom;
        availableAction=EnumSet.noneOf(ActionRoomI.class);
    }

    @Override
    public Set getAvailableAction(){return availableAction;}
    @Override
    public void setAvailableAction(Set availableAction) {
        this.availableAction = availableAction;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public void checkedTime() {
        lastIn=new Date();
    }

    public Date getLastIn() {
        return lastIn;
    }

    public List<Room> getActiveRooms(){
        return activeRooms;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void setUser(Object user) {
        this.user = (User)user;
    }
}