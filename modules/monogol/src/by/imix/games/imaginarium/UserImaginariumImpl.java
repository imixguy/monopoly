package by.imix.games.imaginarium;

import by.imix.games.gamecore.game.Room;

import java.util.Date;
import java.util.List;

/**
 * Created by Mikhail_Kachanouski on 7/26/2017.
 */
public class UserImaginariumImpl implements UserImaginarium{
    @Override
    public void setPenalty(int penalty) {

    }

    @Override
    public int getPenalty() {
        return 0;
    }

    @Override
    public int getIndexPosition() {
        return 0;
    }

    @Override
    public void setIndexPosition(int indexPosition) {

    }

    @Override
    public boolean isGoForward() {
        return false;
    }

    @Override
    public void setGoForward(boolean goForward) {

    }

    @Override
    public List<Room> getActiveRooms() {
        return null;
    }

    @Override
    public boolean isLoose() {
        return false;
    }

    @Override
    public void setLoose(boolean key) {

    }

    @Override
    public void setWin(boolean b) {

    }

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    public Date getLastIn() {
        return null;
    }

    @Override
    public void checkedTime() {

    }

    @Override
    public void setUser(Object user) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isActivGamer() {
        return false;
    }

    @Override
    public void setActivGamer(boolean activGamer) {

    }
}
