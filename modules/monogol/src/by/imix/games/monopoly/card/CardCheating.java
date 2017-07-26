package by.imix.games.monopoly.card;

import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;
import by.imix.games.gamecore.card.CardDefault;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import static by.imix.games.monopoly.ActionMonopolyE.GO_PRISON;

/**
 * Created by miha on 02.02.2015.
 */
public class CardCheating extends CardDefault {
    private int numPrison;

    public CardCheating(String name,int numPrison){
        this.numPrison=numPrison;
        setName(name);
    }

    @Override
    public void transferCardForUser(Room room, UserRoom userRoom) {
        GameMonopoly gameMonopoly = (GameMonopoly) room;
        UserMonopoly userMonopoly = (UserMonopoly) userRoom;
        userMonopoly.setPrison(1);
        userMonopoly.setPenalty(((CardPrison)gameMonopoly.getListCard().get(numPrison)).getPenalty());
        userMonopoly.setIndexPosition(numPrison);
        ActionUser.createInstance(gameMonopoly, userMonopoly, GO_PRISON, userMonopoly);
    }

    @Override
    public void dropInToCard(Room room, UserRoom userRoom) {

    }

    public int getNumPrison() {
        return numPrison;
    }

    public void setNumPrison(int numPrison) {
        this.numPrison = numPrison;
    }
}
