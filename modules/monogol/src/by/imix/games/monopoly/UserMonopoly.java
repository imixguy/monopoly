package by.imix.games.monopoly;

import by.imix.games.gamecore.UserRoom;
import by.imix.games.monopoly.web.ActionUser;

import java.util.Collection;
import java.util.List;

/**
 * Created by miha on 24.12.2014.
 */
public interface UserMonopoly extends UserRoom{

    int getMany();
    void setMany(int many);

    void setPenalty(int penalty);
    int getPenalty();

    int getIndexPosition();
    void setIndexPosition(int indexPosition);

    boolean isActivGamer();
    void setActivGamer(boolean activGamer);

    //установить кредит
    void setCredit(int credit);
    //узнать сумму кредита
    int getCredit();

    boolean isGoForward();
    void setGoForward(boolean goForward);

    List<ActionUser> getAndClearActionsAllUser();
    boolean addActionUser(ActionUser actionsAllUser);

    //игрок проиграл
    boolean isLoose();
    void setLoose(boolean key);

    void setWin(boolean b);
    boolean isWin();

    //Игрок выкинул дубль
    void throwDouble(boolean yes);
    //Узнать сколько раз игрок выкинул дублей за ход
    int getCountThrowDouble();

    Collection<Integer> getMonopByFilThisStep();
    void setMonopByFilThisStep(Collection<Integer> monopByFilThisStep);
    boolean isMonopByFilThisStep(int numMonopoly);

    //узнать кинул ли игрок кубик
    boolean isThrowCubs();
    //игрок кинул кубик
    void setThrowCubs(boolean throwCubs);

    //игрок предлагает обмен
    void doChangeFirm();
    //установить количество обменов предложенных играком за ход, метод предназначен для обнуления результата
    void setChangeFirmCount(int changeFirmCount);
    //узнать сколько раз игрок предлогал обмен
    int getChangeFirmCount();

    void setPrison(int i);
    int getPrison();
}
