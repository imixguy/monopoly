package by.imix.games.gamecore.game;

import by.imix.games.gamecore.card.Card;

import java.util.List;

/**
 * Created by miha on 26.07.2017.
 * Game with card
 */
public interface GameCard extends GameRoom {
    //get list cards
    List<Card> getListCard();
}
