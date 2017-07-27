package by.imix.games.monopoly.web;

import by.imix.games.gamecore.ActionRoomI;
import by.imix.games.gamecore.card.Card;
import by.imix.games.gamecore.game.Room;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 */
@Controller("monopolyController")
public class MonopolyController {
    private Logger log= LoggerFactory.getLogger(MonopolyController.class);
    @Autowired
    GameManager gameManager;
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }

    @RequestMapping(value = "/games/monopoly/game.html", method = RequestMethod.GET)
    public ModelAndView startGame() {
        log.info("games/monopoly/game.html");
        ModelAndView mav=new ModelAndView();
        try {
            GameMonopoly room = getGameManager().getGameByUser(getGameManager().getUser()).get(0);
            mav.setViewName("games/monopoly/polejs");
        } catch (Exception e) {
            mav.setViewName("redirect:/games/room/rooms.html");
        }
        return mav;
    }

    @RequestMapping(value = "/games/monopoly/getCards", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Card> getCards() {
        try {
            return getGameManager().getGameByUser(getGameManager().getUser()).get(0).getListCard();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/games/monopoly/gameinfo", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Room getGameInfo() {
        try {
            return  getGameManager().getGameByUser(getGameManager().getUser()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/games/monopoly/createroom.html", method = RequestMethod.GET)
    public String createRoomMonopoly(@RequestParam("countgameuser") int countGameUser) {
        UserMonopoly userRoom=getGameManager().getUser();
        if(userRoom==null){
            return "redirect:/games/room/rooms.html";
        }
        GameMonopoly room=getGameManager().createGame();
        room.addUser(userRoom);
        room.setMaxCountUser(countGameUser);
        return "redirect:/games/monopoly/game.html";
    }



    private GameMonopoly getGame(){
        return getGameManager().getGameByUser(getGameManager().getUser()).get(0);
    }
    private UserMonopoly getUserMonopoly(){
        return getGameManager().getUser();
    }

    @RequestMapping(value = "/games/monopoly/loadgamedata", method = RequestMethod.GET)
    @ResponseBody
    public DataForGame loadGameData() {
        try {
            DataForGame dataForGame=new DataForGame(getGameManager().getUser(), getGameManager().getUser().getAvailableAction(), getUserMonopoly().getAndClearActionsAllUser(),getGame().getAuction());
            return dataForGame;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/games/monopoly/getStartGamers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMonopoly> getStartGamers() {
        try {
            GameMonopoly gM=getGameManager().getGameByUser(getGameManager().getUser()).get(0);
            if(gM.isStartGame()){
                 return gM.getListUser();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "games/monopoly/offRoom/{numberRoom}", method = RequestMethod.GET, produces="application/json")
    public boolean offRoom(@PathVariable("numberRoom")long numberRoom) {
        GameMonopoly room=getGameManager().getGameByNumberRoom(numberRoom);
        return room.removeUser(getGameManager().getUser());
    }

    @RequestMapping(value = "/games/monopoly/{numberRoom}/joinRoom.html", method = RequestMethod.GET)
    public String joinRoom(@PathVariable("numberRoom")long numberRoom) {
        UserMonopoly userRoom=getGameManager().getUser();
        if(userRoom==null){
            return "redirect:/games/room/rooms.html";
        }
        userRoom.getAvailableAction().remove(ActionRoomI.CREATE_ROOM);
        GameMonopoly room=getGameManager().getGameByNumberRoom(numberRoom);
        if(room==null){
            return "redirect:/games/room/rooms.html";
        }
        room.addUser(userRoom);
        return "redirect:/games/monopoly/game.html";
    }
}
