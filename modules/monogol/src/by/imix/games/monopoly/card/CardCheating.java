package by.imix.games.monopoly.card;

import by.imix.games.gamecore.card.CardDefault;
import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import static by.imix.games.monopoly.ActionMonopolyE.GO_PRISON;

/**
 * Created by miha on 02.02.2015.
 * Class describe CardCheating
 */
public class CardCheating extends CardDefault {
    private int numPrison;

    /**
     * Constructor with name and count steps for skip
     * @param name - name
     * @param numPrison - count step need skip
     */
    public CardCheating(String name,int numPrison){
        this.numPrison=numPrison;
        setName(name);
    }

    /**{@inheritDoc}*/
    @Override
    public void transferCardForUser(Room room, UserRoom userRoom) {
        GameMonopoly gameMonopoly = (GameMonopoly) room;
        UserMonopoly userMonopoly = (UserMonopoly) userRoom;
        userMonopoly.setPrison(1);
        userMonopoly.setPenalty(((CardPrison)gameMonopoly.getListCard().get(numPrison)).getPenalty());
        userMonopoly.setIndexPosition(numPrison);
        ActionUser.createInstance(gameMonopoly, userMonopoly, GO_PRISON, userMonopoly);
    }

    /**{@inheritDoc}*/
    @Override
    public void dropInToCard(Room room, UserRoom userRoom) {

    }

    /**
     * Count step need skip
     * @return count step need skip
     */
    public int getNumPrison() {
        return numPrison;
    }

    /**
     * Set count step need skip
     * @param numPrison set count step need skip
     */
    public void setNumPrison(int numPrison) {
        this.numPrison = numPrison;
    }
}
