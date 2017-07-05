package by.imix.games.monopoly;

import java.util.Set;

/**
 * Created by miha on 28.01.2015.
 */
public class ChangeFirm {
    private Set<Integer> indFirmUserChanger;
    private Set<Integer> indFirm;
    private int manyUserChanger;
    private int many;
    private String userName;
    private UserMonopoly userChanger;
    private UserMonopoly user;

    public ChangeFirm(){}

    public ChangeFirm(Set<Integer> indFirmUserChanger, Set<Integer> indFirm, int manyUserChanger, int many, String userName) {
        this.indFirmUserChanger = indFirmUserChanger;
        this.indFirm = indFirm;
        this.manyUserChanger = manyUserChanger;
        this.many = many;
        this.userName=userName;
    }

    public ChangeFirm(Set<Integer> indFirmUserChanger, Set<Integer> indFirm, int manyUserChanger, int many, UserMonopoly userChanger, UserMonopoly user) {
        this.indFirmUserChanger = indFirmUserChanger;
        this.indFirm = indFirm;
        this.manyUserChanger = manyUserChanger;
        this.many = many;
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

    public int getManyUserChanger() {
        return manyUserChanger;
    }

    public void setManyUserChanger(int manyUserChanger) {
        this.manyUserChanger = manyUserChanger;
    }

    public int getMany() {
        return many;
    }

    public void setMany(int many) {
        this.many = many;
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
