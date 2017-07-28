package by.imix.games.monopoly;

import java.util.Set;

/**
 * Created by miha on 28.01.2015.
 */
public class ChangeFirm {
    private Set<Integer> indFirmUserChanger;
    private Set<Integer> indFirm;
    private int moneyUserChanger;
    private int money;
    private String userName;
    private UserMonopoly userChanger;
    private UserMonopoly user;

    public ChangeFirm(){}

    public ChangeFirm(Set<Integer> indFirmUserChanger, Set<Integer> indFirm, int moneyUserChanger, int money, String userName) {
        this.indFirmUserChanger = indFirmUserChanger;
        this.indFirm = indFirm;
        this.moneyUserChanger = moneyUserChanger;
        this.money = money;
        this.userName=userName;
    }

    public ChangeFirm(Set<Integer> indFirmUserChanger, Set<Integer> indFirm, int moneyUserChanger, int money, UserMonopoly userChanger, UserMonopoly user) {
        this.indFirmUserChanger = indFirmUserChanger;
        this.indFirm = indFirm;
        this.moneyUserChanger = moneyUserChanger;
        this.money = money;
        this.userChanger = userChanger;
        this.user = user;
    }

    public Set<Integer> getIndFirmUserChanger() {
        return indFirmUserChanger;
    }

    public void setIndFirmUserChanger(Set<Integer> indFirmUserChanger) {
        this.indFirmUserChanger = indFirmUserChanger;
    }

    public Set<Integer> getIndFirm() {
        return indFirm;
    }

    public void setIndFirm(Set<Integer> indFirm) {
        this.indFirm = indFirm;
    }

    public int getMoneyUserChanger() {
        return moneyUserChanger;
    }

    public void setMoneyUserChanger(int moneyUserChanger) {
        this.moneyUserChanger = moneyUserChanger;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserMonopoly getUserChanger() {
        return userChanger;
    }

    public void setUserChanger(UserMonopoly userChanger) {
        this.userChanger = userChanger;
    }

    public UserMonopoly getUser() {
        return user;
    }

    public void setUser(UserMonopoly user) {
        this.user = user;
    }
}
