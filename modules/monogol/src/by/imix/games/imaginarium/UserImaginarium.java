package by.imix.games.imaginarium;

import by.imix.games.gamecore.UserRoom;

/**
 * Created by Mikhail_Kachanouski on 7/26/2017.
 */
public interface UserImaginarium extends UserRoom,{

    void setPenalty(int penalty);
    int getPenalty();

    int getIndexPosition();
    void setIndexPosition(int indexPosition);

    boolean isGoForward();
    void setGoForward(boolean goForward);
}
