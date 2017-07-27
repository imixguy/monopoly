package by.imix.games.monopoly;

import by.imix.games.gamecore.user.UserAuction;
import by.imix.games.gamecore.user.UserCredit;
import by.imix.games.gamecore.user.UserCube;
import by.imix.games.gamecore.user.UserRoom;

import java.util.Collection;

/**
 * Created by miha on 24.12.2014.
 * User for monopoly game
 */
public interface UserMonopoly extends UserRoom, UserAuction, UserCube, UserCredit {

    //player money
    int getMoney();
    //set player money
    void setMoney(int many);

    //Penalty
    void setPenalty(int penalty);
    //Get Penalty count
    int getPenalty();

    //IndexPosition
    int getIndexPosition();
    //set IndexPosition
    void setIndexPosition(int indexPosition);

    //direction where go player forward or back
    boolean isGoForward();
    //set direction where go player forward or back
    void setGoForward(boolean goForward);

    //It is list monopoly where was bought filial on current step
    Collection<Integer> getMonopByFilThisStep();
    //Set list monopoly where was bought filial on current step
    void setMonopByFilThisStep(Collection<Integer> monopByFilThisStep);
    //Check that this monopoly contain filial which was buy on current step
    boolean isMonopByFilThisStep(int numMonopoly);

    //Player proposes change firm
    void doChangeFirm();
    //set count changed of was proposed by player for step, need for clearing result
    void setChangeFirmCount(int changeFirmCount);
    //check how many time player propose of changed
    int getChangeFirmCount();

    //set prison
    void setPrison(int i);
    //get prison
    int getPrison();
}
