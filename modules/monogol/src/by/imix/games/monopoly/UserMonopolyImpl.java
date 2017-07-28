package by.imix.games.monopoly;

import by.imix.games.gamecore.ActionRoomI;
import by.imix.games.gamecore.card.Card;
import by.imix.games.gamecore.implDefault.web.DefaultUserRoom;
import by.imix.games.monopoly.web.ActionUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import rw.gcktc.cms.usermanager.User;

import java.security.SecureRandom;
import java.util.*;

/**
 * Created by miha on 15.12.2014.
 */

public class UserMonopolyImpl extends DefaultUserRoom implements UserMonopoly{
    //money
    private int money;
    //penalty
    private int penalty;
    @JsonIgnore
    private SecureRandom rand=new SecureRandom();
    //position of user
    private int indexPosition=0;
    //credit for user
    private int credit;
    //active user or not
    private boolean activGamer=false;
    //go Forward or back;
    private boolean goForward=true;
    //List each actions of user, after execute to cleanup
    @JsonIgnore
    private List<ActionUser> actionsAllUser;
    @JsonIgnore
    //list bought cards(firm)
    private List<Card> listBuyCard;
    //louse
    private boolean loose=false;
    //win
    private boolean win=false;
    @JsonIgnore
    //it is checked that user threw cube
    private boolean throwCubs=false;
    //количество выкинутых дублей за один ход
    private int throwDouble=0;
    //количество преложенных обменов за один ход
    private int changeFirmCount=0;
    //Список монополий в которых был куплен филиал на текущем шаге.
    private Set<Integer> monopByFilThisStep;
    //Находится ли пользователь в тюрьме и сколько ходов 0 - не находится.
    private int prison=0;
    //list available action
    @JsonIgnore
    //private Set<String> listAvailableActions;
    private Set availableAction;

    public UserMonopolyImpl(){
        this(null, 1);
    }

    public UserMonopolyImpl(User user){
        this(user, 1);
    }

    public UserMonopolyImpl(User user, int maxCountActiveRoom) {
        this(user, maxCountActiveRoom,0);
    }

    public UserMonopolyImpl(User user, int maxCountActiveRoom, Integer money) {
        super(user,maxCountActiveRoom);
        //setAvailableAction(new ActionMonopolyC());

        this.money = money;
        actionsAllUser=new ArrayList<>();
        monopByFilThisStep=new HashSet<>();

        listBuyCard=new ArrayList<>();
        availableAction=EnumSet.noneOf(ActionRoomI.class);
    }

    @Override
    public Set getAvailableAction(){return availableAction;}
    @Override
    public void setAvailableAction(Set availableAction) {
        this.availableAction = availableAction;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getIndexPosition() {
        return indexPosition;
    }

    public void setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isActivGamer() {
        return activGamer;
    }

    public void setActivGamer(boolean activGamer) {
        this.activGamer = activGamer;
    }
    @Override
    public boolean isGoForward() {
        return goForward;
    }
    @Override
    public void setGoForward(boolean goForward) {
        this.goForward = goForward;
    }


    @JsonIgnore
    @Override
    public List<ActionUser> getAndClearActionsAllUser() {
        synchronized (actionsAllUser) {
            List<ActionUser> oldAU = new ArrayList<>();
            oldAU.addAll(actionsAllUser);
            actionsAllUser.clear();
            return oldAU;
        }
    }

    @Override
    public boolean addActionUser(ActionUser actionUser) {
        return this.actionsAllUser.add(actionUser);
    }

    @Override
    public boolean isLoose() {
        return loose;
    }

    @Override
    public void setLoose(boolean key) {
        this.loose=key;
    }

    @Override
    public void setWin(boolean key) {
        this.win=key;
    }

    @Override
    public void throwDouble(boolean yes) {
        if(yes) {
            this.throwDouble += 1;
        }else {
            this.throwDouble = 0;
        }
    }

    @Override
    public int getCountThrowDouble() {
        return throwDouble;
    }

    public boolean isWin() {
        return win;
    }

    public Set<Integer> getMonopByFilThisStep() {
        return monopByFilThisStep;
    }

    @Override
    public void setMonopByFilThisStep(Collection<Integer> monopByFilThisStep) {
            this.monopByFilThisStep = (Set<Integer>) monopByFilThisStep;
    }


    @Override
    public boolean isMonopByFilThisStep(int numMonopoly) {
        for(Integer monopN:monopByFilThisStep){
            if(monopN.intValue()==numMonopoly){
                return true;
            }
        }
        return false;
    }

    public boolean isThrowCubs() {
        return throwCubs;
    }

    public void setThrowCubs(boolean throwCubs) {
        this.throwCubs = throwCubs;
    }

    @Override
    public void doChangeFirm() {
        this.changeFirmCount+=1;
    }

    public void setChangeFirmCount(int changeFirmCount) {
        this.changeFirmCount = changeFirmCount;
    }

    @Override
    public int getChangeFirmCount() {
        return changeFirmCount;
    }

    public int getPrison() {
        return prison;
    }

    public void setPrison(int prison) {
        this.prison = prison;
    }
}
