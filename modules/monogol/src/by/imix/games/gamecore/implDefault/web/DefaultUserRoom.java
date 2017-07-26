package by.imix.games.gamecore.implDefault.web;

import by.imix.games.gamecore.ActionRoomI;
import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import rw.gcktc.cms.usermanager.User;

import java.util.*;

/**
 * Created by miha on 16.12.2014.
 * Class with basis functionality for user room with imix node user
 */
public abstract class DefaultUserRoom implements UserRoom {
    //Outside user
    @JsonIgnore
    private User user;
    //list rooms what user was entrance
    @JsonIgnore
    private List<Room> activeRooms;
    //max count rooms is available for user can entrance
    private int maxCountActiveRoom;
    @JsonIgnore
    //date last entrance
    private Date lastIn;
    //list available action
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

    /**{@inheritDoc}*/
    @Override
    public Set getAvailableAction(){return availableAction;}

    /**{@inheritDoc}*/
    @Override
    public void setAvailableAction(Set availableAction) {
        this.availableAction = availableAction;
    }

    /**{@inheritDoc}*/
    @Override
    public String getName() {
        return user.getName();
    }

    /**{@inheritDoc}*/
    @Override
    public void checkedTime() {
        lastIn=new Date();
    }

    /**{@inheritDoc}*/
    @Override
    public Date getLastIn() {
        return lastIn;
    }

    /**{@inheritDoc}*/
    @Override
    public List<Room> getActiveRooms(){
        return activeRooms;
    }

    //Method return user
    public User getUser() {
        return user;
    }

    /**{@inheritDoc}*/
    @Override
    public void setUser(Object user) {
        this.user = (User)user;
    }
}