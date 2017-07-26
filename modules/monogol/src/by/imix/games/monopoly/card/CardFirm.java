package by.imix.games.monopoly.card;


import by.imix.games.gamecore.Room;
import by.imix.games.gamecore.UserRoom;
import by.imix.games.gamecore.card.CardDefault;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.MonopolyGame;
import by.imix.games.monopoly.UserMonopoly;
import by.imix.games.monopoly.web.ActionUser;

import javax.persistence.*;

import static by.imix.games.monopoly.ActionMonopolyE.*;

/**
 * Created by miha on 15.12.2014.
 */
@Entity
@Table(name = "m_card")
public class CardFirm extends CardDefault {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    //стоимость фирмы
    private int price;
    //владелец фирмы
    private UserMonopoly userOwner;
    //возможное количество филиалов
    private int countFilial;
    //размещенное количество филиалов
    private int filialStay;
    //стоимость филиала
    private int filialPrice;
    //номер монополии
    private int numMonopoly;
    //количество фирм в монополии
    private int countFirmInMonopoly;
    private int penalty;
    //фирма находится в залоге
    private boolean put=false;

    public CardFirm(String name, int price, int countFilial, int filialPrice, int numMonopoly, int countFirmInMonopoly) {
        this.price = price;
        setName(name);
        this.countFilial = countFilial;
        this.filialPrice = filialPrice;
        this.numMonopoly=numMonopoly;
        this.countFirmInMonopoly=countFirmInMonopoly;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UserMonopoly getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserMonopoly userOwner) {
        this.userOwner = userOwner;
    }

    public int getCountFilial() {
        return countFilial;
    }

    public void setCountFilial(int countFilial) {
        this.countFilial = countFilial;
    }

    public int getFilialStay() {
        return filialStay;
    }

    public void setFilialStay(int filialStay) {
        this.filialStay = filialStay;
    }

    public int getFilialPrice() {
        return filialPrice;
    }

    public void setFilialPrice(int filialPrice) {
        this.filialPrice = filialPrice;
    }

    public int getNumMonopoly() {
        return numMonopoly;
    }

    public void setNumMonopoly(int numMonopoly) {
        this.numMonopoly = numMonopoly;
    }

    public int getCountFirmInMonopoly() {
        return countFirmInMonopoly;
    }

    public void setCountFirmInMonopoly(int countFirmInMonopoly) {
        this.countFirmInMonopoly = countFirmInMonopoly;
    }

    public boolean isPut() {
        return put;
    }

    public void setPut(boolean put) {
        this.put = put;
    }

    @Override
    public void transferCardForUser(Room room, UserRoom userRoom) {
        GameMonopoly gameMonopoly = (GameMonopoly) room;
        UserMonopoly userMonopoly = (UserMonopoly) userRoom;
        //если фирма ничья то ее можно купить или выставить на аукцион
        if (getUserOwner() == null) {
            if (userMonopoly.getMany() >= this.getPrice()) {
                userMonopoly.getAvailableAction().add(BUY_FIRM);
            }
            userMonopoly.getAvailableAction().add(AUCTION_START);
        } else {
            if (userMonopoly != getUserOwner() && !isPut() ) {
                userMonopoly.setPenalty(0 - getPenalty());
                if(userMonopoly.getMany()>getPenalty()) {
                    userMonopoly.getAvailableAction().add(PAY_PENALTY);
                }
            }else{
                gameMonopoly.nextGamer();
                return;
            }
        }
    }

    @Override
    public void dropInToCard(Room room, UserRoom userRoom) {

    }

    //купить филиал
    public void buyFilial(MonopolyGame monopolyGame,UserMonopoly user){
        if(getFilialStay()< getCountFilial() && user.getMany()>=getFilialPrice()) {
            user.setMany(user.getMany() - getFilialPrice());
            setFilialStay(getFilialStay()+1);
            ActionUser.createInstance(monopolyGame, user, BUY_FILIAL, this);
        }else{
            // штраф
            monopolyGame.penaltyCheating(user);
        }
    }

    public void sellFilial(MonopolyGame monopolyGame,UserMonopoly user){
        if(getUserOwner()!=null && user==getUserOwner() && getFilialStay()>0) {
            user.setMany(user.getMany() + getFilialPrice());
            setFilialStay(getFilialStay()-1);
        }else{
            //штраф
            monopolyGame.penaltyCheating(user);
        }
    }

    //вернуть в банк
    public void returnInBank(GameMonopoly gameMonopoly) {
        //фирму в банк деньги пользователю
        userOwner.setMany(userOwner.getMany()+getFilialStay()*getFilialPrice()+getPrice());
        setUserOwner(null);
    }

    public int getPenalty() {
        return Math.round(getPrice()/5+(getPrice()*(filialStay*filialStay))/10);
    }


    public boolean putFirm(MonopolyGame monopolyGame, UserMonopoly curentUser) {
        if(getUserOwner()==curentUser & getFilialStay()==0){
            curentUser.setMany(curentUser.getMany()+Math.round(getPrice()/2));
            put=true;
            return true;
        }else{
            //штраф
            monopolyGame.penaltyCheating(curentUser);
            return false;
        }
    }

    public boolean redeemFirm(MonopolyGame monopolyGame, UserMonopoly curentUser) {
        if(getUserOwner()==curentUser && isPut() && curentUser.getMany()>getPrice()){
            curentUser.setMany(curentUser.getMany()-getPrice());
            put=false;
            return true;
        }else{
            //штраф
            monopolyGame.penaltyCheating(curentUser);
            return false;
        }
    }
}
