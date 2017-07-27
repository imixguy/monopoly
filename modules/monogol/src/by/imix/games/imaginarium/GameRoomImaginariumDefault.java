package by.imix.games.imaginarium;

import by.imix.games.gamecore.card.Card;
import by.imix.games.gamecore.game.GameRoomDefault;
import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;

import java.util.List;

/**
 * Created by miha on 26.07.2017.
 */
public class GameRoomImaginariumDefault extends GameRoomDefault implements GameRoomImaginarium {
    /**{@inheritDoc}*/
    @Override
    public Room getRoom() {
        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public void setRoom(Room room) {

    }

    /**{@inheritDoc}*/
    @Override
    public boolean isStartGame() {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public List<Card> getListCard() {
        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public void nextGamer() {

    }

    /**{@inheritDoc}*/
    @Override
    public UserRoom getCurentUser() {
        return null;
    }

    @Override
    public void gameEnd(UserRoom user) {

    }

    @Override
    public void gameClose(UserRoom user) {

    }
}
