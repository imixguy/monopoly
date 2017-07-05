package by.imix.games.gamecore.implDefault;

import by.imix.games.gamecore.ActionRoomI;
import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.RoomManager;
import by.imix.games.gamecore.UserRoom;
import by.imix.games.gamecore.implDefault.web.DefaultUserRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rw.gcktc.cms.usermanager.User;
import rw.gcktc.webcms.security.UserWeb;

import java.util.*;

/**
 * Created by miha on 16.12.2014.
 */
@Component("roomManager")
public class DefaultRoomManager implements RoomManager {
    private Logger log= LoggerFactory.getLogger(DefaultRoomManager.class);
    protected Map<Long,Room> listRoom;
    private static long NEXT_NUMBER_ROOM=0;
    @Autowired
    private Creator creator;

    public DefaultRoomManager() {
        this.listRoom = new HashMap<>();
    }


    @Override
    public Room createRoom(){
        Room room=creator.createRoom();
        room.setNumberRoom(getNextNumberRoom());
        listRoom.put(room.getNumberRoom(),room);
        return room;
    }

    @Override
    public List<Room> getAllRoom() {
        return new ArrayList<>(listRoom.values());
    }

    public Room getRoomByNumber(long numberRoom) {
        return listRoom.get(numberRoom);
    }

    @Override
    public List<Room> getAllPermissionRoom() {
        List<Room> listRoomFree=new ArrayList<>();
        UserRoom userRoom=getUser();
        for(Room room:listRoom.values()){
            if(room.isPermission(userRoom)){
                listRoomFree.add(room);
            }
        }
        return listRoomFree;
    }

    protected long getNextNumberRoom(){
        NEXT_NUMBER_ROOM+=1;
        return NEXT_NUMBER_ROOM;
    }


    @Autowired
    public void setCreator(Creator creator) {
        this.creator = creator;
    }



    public List<Room> getRoomByUser(){
        List<Room> lRoom=new ArrayList<>();
        UserRoom userRoom=getUser();
        for (Room room:listRoom.values()){
            for (UserRoom usR:room.getListUser()){
                if(userRoom.getName().equals(usR.getName())){
                    lRoom.add(room);
                    break;
                }
            }
        }
        return lRoom;
    }


    protected Map<String,UserRoom> listUserRoom=new HashMap<>();
    private int timeClean=1000*60*10;//10 минут

    @Override
    public UserRoom getUser() {
        User user=getWebUser();
        if(user!=null) {
            UserRoom userD = listUserRoom.get(user.getName());
            if (userD == null) {
                userD = creator.createUser();
                userD.setUser(getWebUser());
                ((DefaultUserRoom)userD).getAvailableAction().add(ActionRoomI.CREATE_ROOM);
                listUserRoom.put(user.getName(), userD);
            }
            userD.checkedTime();
            return userD;
        }
        return null;
    }

    public void cleanOldUser(){
        List<UserRoom> remListUserGame=new ArrayList<>();
        Long curDate= new Date().getTime();
        for(UserRoom user:listUserRoom.values()){
            if(user.getLastIn().getTime()+timeClean<curDate){
                remListUserGame.add(user);
            }
        }
        for(UserRoom user:remListUserGame){
            listUserRoom.remove(user);
        }
    }

    private User getWebUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user=auth.getPrincipal();
        if(user instanceof String){
            return null;
        }
        return ((UserWeb) user).getUserw();
    }
}

