package by.imix.games.monopoly.web;

import by.imix.games.gamecore.UserRoom;
import by.imix.games.monopoly.Auction;

import java.util.List;
import java.util.Set;

/**
 * Created by miha on 26.12.2014.
 */
public class DataForGame {
    private UserRoom userRoom;
    private List<ActionUser> listAction;
    private Set availAction;
    private Auction auction;

    public DataForGame(UserRoom userRoom, Set availAction,List<ActionUser> listAction,Auction auction) {
        this.userRoom = userRoom;
        this.listAction = listAction;
        this.availAction=availAction;
        this.auction=auction;
    }

    public Set getAvailAction() {
        return availAction;
    }

    public void setAvailAction(Set availAction) {
        this.availAction = availAction;
    }

    public UserRoom getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(UserRoom userRoom) {
        this.userRoom = userRoom;
    }

    public List<ActionUser> getListAction() {
        return listAction;
    }

    public void setListAction(List<ActionUser> listAction) {
        this.listAction = listAction;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
