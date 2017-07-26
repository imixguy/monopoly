package by.imix.games.gamecore.card;

import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;

/**
 * Created by miha on 15.12.2014.
 * Class for any card to game, abstract class for minimum necessary functions
 */
public interface Card {
    /**
     * Method return path to image card
     * @return path to image card
     */
    String getImage();

    /**
     * Method for transfer one card to user (userRoom).
     * Room is needed for indicate specific room (user can play to fews rooms)
     */
    void transferCardForUser(Room room, UserRoom userRoom);

    /**
     * Method when user linked with card and need handle some action for user.
     * Room is needed for indicate specific room (user can play to fews rooms)
     */
    void dropInToCard(Room room, UserRoom userRoom);
}
