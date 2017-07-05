package by.imix.games.monopoly.card;

import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;

/**
 * Created by miha on 15.12.2014.
 */
public interface Card {
    String getImage();
    void gotOnCard(GameMonopoly gameMonopoly,UserMonopoly userMonopoly);
    void stayFirm(GameMonopoly gameMonopoly,UserMonopoly userMonopoly);
}
