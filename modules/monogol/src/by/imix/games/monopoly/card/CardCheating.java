package by.imix.games.monopoly.card;

import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import static by.imix.games.monopoly.ActionMonopolyE.GO_PRISON;

/**
 * Created by miha on 02.02.2015.
 */
public class CardCheating extends CardDefault{
    private int numPrison;

    public CardCheating(String name,int numPrison){
        this.numPrison=numPrison;
        setName(name);
    }

    @Override
    public void gotOnCard(GameMonopoly gameMonopoly, UserMonopoly userMonopoly) {
        userMonopoly.setPrison(1);
        userMonopoly.setPenalty(((CardPrison)gameMonopoly.getListCard().get(numPrison)).getPenalty());
        userMonopoly.setIndexPosition(numPrison);
        ActionUser.createInstance(gameMonopoly, userMonopoly, GO_PRISON, userMonopoly);
    }

    @Override
    public void stayFirm(GameMonopoly gameMonopoly, UserMonopoly userMonopoly) {

    }

    public int getNumPrison() {
        return numPrison;
    }

    public void setNumPrison(int numPrison) {
        this.numPrison = numPrison;
    }
}
