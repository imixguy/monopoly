package by.imix.games.monopoly.web;

import by.imix.games.gamecore.RoomManager;
import by.imix.games.gamecore.UserRoom;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by miha on 05.01.2015.
 */
@Component("gameManager")
public class GameManagerImpl implements GameManager{
    @Autowired
    RoomManager roomManager;
    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }
    public RoomManager getRoomManager() {
        return roomManager;
    }

    private GameCreator gameCreator;
    private Map<Long,GameMonopoly> listGame=new HashMap<>();

    private static int numberRoom=0;

    @Autowired
    public void setGameCreator(GameCreator gameCreator) {
        this.gameCreator = gameCreator;
    }

    @Override
    public UserMonopoly getUserMonopoly() {
        return null;
    }

    @Override
    public GameMonopoly createGame() {
        GameMonopoly gameMonopoly=gameCreator.createGame();
        gameMonopoly.setRoom(roomManager.createRoom());
        listGame.put(gameMonopoly.getNumberRoom(),gameMonopoly);
        return gameMonopoly;
    }

    @Override
    public List<GameMonopoly> getAllGame()  {
        return new ArrayList<>(listGame.values());
    }

    @Override
    public List<GameMonopoly> getAllPermissionRoom(UserMonopoly user) {
        List<GameMonopoly> listGameFree=new ArrayList<>();
        for(GameMonopoly room:listGame.values()){
            if(room.isPermission(user)){
                listGameFree.add(room);
            }
        }
        return listGameFree;
    }

    @Override
    public GameMonopoly getGameByNumberRoom(long numberRoom) {
        return listGame.get(numberRoom);
    }

    @Override
    public List<? extends GameMonopoly> getGameByUser(UserRoom user) {
        List<GameMonopoly> lGame=new ArrayList<>();
        for (GameMonopoly room:listGame.values()){
            for (UserMonopoly usR:room.getListUser()){
                if(user.getName().equals(usR.getName())){
                    lGame.add(room);
                    break;
                }
            }
        }
        return lGame;
    }

    @Override
    public UserMonopoly getUser() {
        return (UserMonopoly)roomManager.getUser();
    }

    @Override
    public boolean gameEnd(GameMonopoly gameMonopoly) {
        return getAllGame().remove(gameMonopoly);
    }
}
