package by.imix.games.gamecore.implDefault;

import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;

/**
 * Created by miha on 07.01.2015.
 */
public abstract class Creator {
    public abstract Room createRoom();
    public abstract UserRoom createUser();
}
