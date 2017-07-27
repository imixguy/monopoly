package by.imix.games.monopoly.card;

import by.imix.games.gamecore.card.CardDefault;
import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;

import java.security.SecureRandom;

import static by.imix.games.monopoly.ActionMonopolyE.THROW_CUBE;

/**
 * Created by miha on 15.12.2014.
 */
public class CardPrison extends CardDefault {
    private int penalty;

    public CardPrison(String name,int penalty){
        super();
        setName(name);
        this.penalty=penalty;
    }

    @Override
    public void transferCardForUser(Room room, UserRoom userRoom) {
        GameMonopoly gameMonopoly = (GameMonopoly) room;
        //счатаем что количество дней отдыха равно - количеству карт
        gameMonopoly.goToCard(new SecureRandom().nextInt(gameMonopoly.getListCard().size()));
    }

    @Override
    public void dropInToCard(Room room, UserRoom userRoom) {
        UserMonopoly userMonopoly = (UserMonopoly) userRoom;
        if(userMonopoly.getPrison()!=0){
            if(userMonopoly.getPrison()<3){
                userMonopoly.getAvailableAction().add(THROW_CUBE);
            }
        }
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}