package by.imix.games.monopoly.card;

import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import java.security.SecureRandom;
import java.util.List;

import static by.imix.games.monopoly.ActionMonopolyE.*;

/**
 * Created by miha on 15.12.2014.
 * Карточка налоговой инспекции или лоттореи или казино
 */
public class CardPlusMinus extends CardDefault{
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


    @Override
    public void gotOnCard(GameMonopoly gameMonopoly,UserMonopoly userMonopoly) {
        Integer pm=userMonopoly.getPenalty();
        if(pm==0) {
            pm = possibleShtraf.get(new SecureRandom().nextInt(possibleShtraf.size()));
        }
        if(pm>0){
            userMonopoly.setMany(userMonopoly.getMany() + pm);
            ActionUser.createInstance(gameMonopoly,userMonopoly, RECEIVE_INCOME, userMonopoly);
            gameMonopoly.nextGamer();
        }else{
            ActionUser.createInstance(gameMonopoly,userMonopoly, GET_PENALTY, pm);
            userMonopoly.setPenalty(pm);
        }
    }

    @Override
    public void stayFirm(GameMonopoly gameMonopoly,UserMonopoly userMonopoly) {
    }
}
