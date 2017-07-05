package by.imix.games.monopoly.web;

import by.imix.games.monopoly.ActionMonopolyE;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;

import java.util.Date;

/**
 * Created by miha on 26.12.2014.
 */
public class ActionUser {
    private Date dateAction;
    private ActionMonopolyE action;
    private Object infoAction;
    private UserMonopoly user;
    //private Auction auction;

    public static void createInstance(GameMonopoly monopolyGame,UserMonopoly user, ActionMonopolyE action, Object infoAction){
        ActionUser au=new ActionUser(user,action,infoAction);
        for(UserMonopoly um:monopolyGame.getListUser()){
            um.addActionUser(au);
        }
        for(UserMonopoly um:monopolyGame.getListViewUser()){
            um.addActionUser(au);
        }
    }

    private ActionUser(UserMonopoly user, ActionMonopolyE action, Object infoAction) {
        this.user=user;
        this.action = action;
        this.infoAction = infoAction;
        this.dateAction=new Date();
    }

    public Date getDateAction() {
        return dateAction;
    }

    public UserMonopoly getUser() {
        return user;
    }

    public void setUser(UserMonopoly user) {
        this.user = user;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public ActionMonopolyE getAction() {
        return action;
    }

    public void setAction(ActionMonopolyE action) {
        this.action = action;
    }

    public Object getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(Object infoAction) {
        this.infoAction = infoAction;
    }

}
