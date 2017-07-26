package by.imix.games.gamecore.user;

/**
 * Created by miha on 27.07.2017.
 * User that can throw cube
 */
public interface UserCube {
    //Игрок выкинул дубль
    void throwDouble(boolean yes);
    //Узнать сколько раз игрок выкинул дублей за ход
    int getCountThrowDouble();

    //узнать кинул ли игрок кубик
    boolean isThrowCubs();
    //игрок кинул кубик
    void setThrowCubs(boolean throwCubs);
}
