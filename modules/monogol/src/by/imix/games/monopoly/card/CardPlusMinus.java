package by.imix.games.monopoly.card;

import by.imix.games.gamecore.card.CardDefault;
import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import java.security.SecureRandom;
import java.util.List;

import static by.imix.games.monopoly.ActionMonopolyE.GET_PENALTY;
import static by.imix.games.monopoly.ActionMonopolyE.RECEIVE_INCOME;

/**
 * Created by miha on 15.12.2014.
 * Карточка налоговой инспекции или лоттореи или казино
 */
public class CardPlusMinus extends CardDefault {
    private List<Integer> possibleShtraf;

    public CardPlusMinus(String name,List<Integer> possibleShtraf) {
        setName(name);
        this.possibleShtraf = possibleShtraf;
    }

    public List<Integer> getPossibleShtraf() {
        return possibleShtraf;
    }

    public void setPossibleShtraf(List<Integer> possibleShtraf) {
        this.possibleShtraf = possibleShtraf;
    }

    /**{@inheritDoc}*/
    @Override
    public void transferCardForUser(Room room, UserRoom userRoom) {
        GameMonopoly gameMonopoly = (GameMonopoly) room;
        UserMonopoly userMonopoly = (UserMonopoly) userRoom;
        Integer pm=userMonopoly.getPenalty();
        if(pm==0) {
            pm = possibleShtraf.get(new SecureRandom().nextInt(possibleShtraf.size()));
        }
        if(pm>0){
            userMonopoly.setMoney(userMonopoly.getMoney() + pm);
            ActionUser.createInstance(gameMonopoly,userMonopoly, RECEIVE_INCOME, userMonopoly);
            gameMonopoly.nextGamer();
        }else{
            ActionUser.createInstance(gameMonopoly,userMonopoly, GET_PENALTY, pm);
            userMonopoly.setPenalty(pm);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void dropInToCard(Room room, UserRoom userRoom) {
    }
}
