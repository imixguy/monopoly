package by.imix.games.monopoly;

import by.imix.games.gamecore.card.Card;
import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.user.UserRoom;
import by.imix.games.monopoly.card.CardFirm;
import by.imix.games.monopoly.card.CardPrison;
import by.imix.games.monopoly.web.ActionUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

import static by.imix.games.monopoly.ActionMonopolyE.*;

/**
 * Created by miha on 15.12.2014.
 */

public class MonopolyGame implements GameMonopoly{
    //игровая комната
    private Room room;
    //Время начала игры
    private Long timeStartGame;
    private Long currentTime;
    //список ячеек по которым может передвигаться пользователь
    @JsonIgnore
    private List<Card> listCard;
    private boolean startGame=false;
    private String imageFolder;
    private String imageCenter;
    //штраф за обман
    @JsonIgnore
    private Integer penalty_cheating;
    //Текущий пользователь
    private UserMonopoly curentUser=null;
    //Деньги за круг
    private int circleMoney;
    //стартовые деньги
    private int startMoney;
    //возможный кредит
    private int credit;


    public MonopolyGame(List<Card> listCard) {
        this.timeStartGame=new Date().getTime();
        this.listCard = listCard;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public int getCircleMoney() {
        return circleMoney;
    }

    public void setCircleMoney(int circleMoney) {
        this.circleMoney = circleMoney;
    }

    public List<Card> getListCard() {
        return listCard;
    }

    public void setListCard(List<Card> listCard) {
        this.listCard = listCard;
    }

    public Long getTimeStartGame() {
        return timeStartGame;
    }

    public void setTimeStartGame(Long timeStartGame) {
        this.timeStartGame = timeStartGame;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public boolean addUser(UserRoom user) {
        UserMonopoly mUser= (UserMonopoly) user;
        if(room.addUser(user)){
            mUser.setAvailableAction(EnumSet.noneOf(ActionMonopolyE.class));
            if(isOpenRoom()){
                return true;
            }else{
                startGame();
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean removeUser(UserRoom user) {
        return room.removeUser(user);
    }

    @Override
    public long getNumberRoom() {
        return room.getNumberRoom();
    }

    @Override
    public void setNumberRoom(long numberRoom) {
        this.room.setNumberRoom(numberRoom);
    }

    @Override
    public boolean isPermission(UserRoom user) {
        return room.isPermission(user);
    }

    public boolean isOpenRoom() {
        return room.isOpenRoom();
    }

    @Override
    public int countPerson() {
        return room.countPerson();
    }

    @Override
    public List<UserMonopoly> getListUser() {
        return (List<UserMonopoly>) room.getListUser();
    }

    @Override
    public void setMaxCountUser(int count) {
        room.setMaxCountUser(count);
    }

    @Override
    public int getMaxCountUser() {
        return room.getMaxCountUser();
    }

    @Override
    public List<UserMonopoly> getListViewUser() {
        return (List<UserMonopoly>)room.getListViewUser();
    }

    public void startGame(){
        startGame=true;
        curentUser=getListUser().get(new SecureRandom().nextInt(getMaxCountUser()));
        nextGamer();
        for(UserMonopoly user:getListUser()) {
            user.setMoney(getStartMoney());
            ActionUser.createInstance(this,user, START_GAME, "Hello in GameRoom");
        }
    }

    @Override
    public void nextGamer(){
        curentUser.setActivGamer(false);
        //чистим список монополий в которых был куплен филиал на текущем шаге
        curentUser.getMonopByFilThisStep().clear();
        if(isWinSomebody()){
            return;
        }
        if(auction!=null){
            auction.nextGamer();
            return;
        }
        if(curentUser.getCountThrowDouble()==0){
            if(getListUser().get(getListUser().size()-1)==curentUser){
                curentUser=getListUser().get(0);
            }else{
                curentUser=getListUser().get(getListUser().indexOf(curentUser)+1);
            }
        }
        curentUser.setThrowCubs(false);
        curentUser.getAvailableAction().clear();

        getListCard().get(curentUser.getIndexPosition()).dropInToCard(this,curentUser);

        if(!canCheckPenalty(curentUser)){
            curentUser.getAvailableAction().add(THROW_CUBE);
        }
        giveTakeCredit(curentUser);
        firmFilialSell(curentUser);
        curentUser.setActivGamer(true);
        if(curentUser.getPrison()!=0){
            ActionUser.createInstance(this,curentUser, CHANGE_USER, curentUser);
            return;
        }
        canBuyFilial();
        ActionUser.createInstance(this,curentUser, CHANGE_USER, curentUser);
    }

    private boolean isWinSomebody() {
        //победа
        if(getListUser().size()==1){
            curentUser=getListUser().get(0);
            ActionUser.createInstance(this,curentUser, WIN, curentUser);
            curentUser.setActivGamer(true);
            curentUser.setWin(true);
            curentUser.getAvailableAction().clear();
            curentUser.getAvailableAction().add(GAME_CLOSE);
            return true;
        }
        return false;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getImageCenter() {
        return imageCenter;
    }

    public void setImageCenter(String imageCenter) {
        this.imageCenter = imageCenter;
    }

    @JsonIgnore
    private SecureRandom rand=new SecureRandom();

    //Бросить кубик
    public int[] throwCube() {
        if(curentUser.getAvailableAction().contains(THROW_CUBE)){
            curentUser.getAvailableAction().clear();
            int[] toValue={rand.nextInt(6)+1,rand.nextInt(6)+1};
            curentUser.throwDouble(toValue[0]==toValue[1]);
            ActionUser.createInstance(this,curentUser, THROW_CUBE, toValue);
            if(curentUser.getPrison()>0){
                if(curentUser.getCountThrowDouble()==1){
                    //выходит из тюрьмы
                    curentUser.setPrison(0);
                }else{
                    curentUser.setPrison(curentUser.getPrison()+1);
                    nextGamer();
                }
            }
            if(curentUser.getCountThrowDouble()==3){
                //устанавливаем выбрасывание кубика в 0 раз
                goPrison(curentUser);
                nextGamer();
                curentUser.throwDouble(false);
                return toValue;
            }
            goToCard(toValue[0]+toValue[1]);
            return toValue;
        }else{
            //штраф
            penaltyCheating(curentUser);
        }
        return null;
    }

    private void goPrison(UserMonopoly user) {
        user.setPrison(1);
        CardPrison cP=getCardPrison();
        if(cP!=null) {
            user.setPenalty(cP.getPenalty());
            user.setIndexPosition(listCard.indexOf(cP));
            ActionUser.createInstance(this, user, GO_PRISON, user);
        }
    }

    public CardPrison getCardPrison(){
        for(Card card:listCard){
            if(card instanceof CardPrison){
                return (CardPrison) card;
            }
        }
        return null;
    }

    //получение денег за круг
    private void getMoneybyCircle(UserMonopoly curentUser) {
        curentUser.setMoney(curentUser.getMoney() + circleMoney);
    }

    @Override
    public void buyFirm(){
        if(curentUser.getAvailableAction().contains(BUY_FIRM)){
            if(listCard.get(curentUser.getIndexPosition()) instanceof CardFirm) {
                CardFirm cardF = (CardFirm) listCard.get(curentUser.getIndexPosition());
                if(cardF.getUserOwner()==null && curentUser.getMoney()>=cardF.getPrice()) {
                    curentUser.setMoney(curentUser.getMoney() - cardF.getPrice());
                    cardF.setUserOwner(curentUser);
                    ActionUser.createInstance(this,curentUser, BUY_FIRM, cardF);
                    curentUser.getAvailableAction().clear();
                }else{
                    //штраф
                    penaltyCheating(curentUser);
                }
                nextGamer();
            }
        }
    }

    public void penaltyCheating(UserMonopoly user) {
        ActionUser.createInstance(this,user, PENALTY_CHEATING, getPenalty_cheating());
    }

    public void penaltyCheating(UserMonopoly user, Object obj) {
        ActionUser.createInstance(this,user, PENALTY_CHEATING, obj);
    }

    @Override
    public void payPenalty() {
        if(curentUser.getAvailableAction().contains(PAY_PENALTY)){
            if(curentUser.getPenalty()!=0 && curentUser.getMoney()+curentUser.getPenalty()>=0){
                Card c=getListCard().get(curentUser.getIndexPosition());
                if(c instanceof CardFirm){
                    CardFirm card=(CardFirm)c;
                    card.getUserOwner().setMoney(card.getUserOwner().getMoney()-curentUser.getPenalty());
                    ActionUser.createInstance(this,curentUser, RECEIVE_INCOME, card.getUserOwner());
                }
                curentUser.getAvailableAction().clear();
                curentUser.setMoney(curentUser.getMoney()-Math.abs(curentUser.getPenalty()));
                ActionUser.createInstance(this,curentUser, PAY_PENALTY, curentUser);
                curentUser.setPenalty(0);
                //если был в тюрьме выходит из тюрьмы
                curentUser.setPrison(0);
                nextGamer();
            }else{
                penaltyCheating(curentUser);
            }
        }
    }

    private Auction auction=null;

    @Override
    public void startAuction() {
        if(curentUser.getAvailableAction().contains(AUCTION_START)) {
            curentUser.getAvailableAction().clear();
            auction = new Auction(this);
            nextGamer();

        }else{
            penaltyCheating(curentUser);
        }
    }

    public void stopAuction() {
        auction=null;
        nextGamer();
    }

    @Override
    public void putFirm(int[] indFirm) {
        if(curentUser.getAvailableAction().contains(PUT_FIRM)) {
            for(int i=0;i<Array.getLength(indFirm);i++) {
                try {
                    CardFirm cF = ((CardFirm) getListCard().get(Array.getInt(indFirm, i)));
                    if(cF.putFirm(this, curentUser)) {
                        ActionUser.createInstance(this, curentUser, PUT_FIRM, cF);
                        firmFilialSell(curentUser);
                        canBuyFilial();
                    }
                } catch (Exception e) {
                    penaltyCheating(curentUser);
                }
            }
            if(!canCheckPenalty(curentUser)){
                Card card=listCard.get(getCurentUser().getIndexPosition());
                if(card instanceof CardFirm) {
                    if (((CardFirm)card).getUserOwner()==null && curentUser.getMoney() >= ((CardFirm)card).getPrice()) {
                        curentUser.getAvailableAction().add(BUY_FIRM);
                    }
                }
            }
        }else{
            penaltyCheating(curentUser);
        }
    }

    @Override
    public void redeemFirm(int[] indFirm) {
        if(curentUser.getAvailableAction().contains(REDEEM_FIRM)) {
            List<CardFirm> lCF=new ArrayList<>();
            int price=0;
            for(int i=0;i<Array.getLength(indFirm);i++) {
                try {
                    CardFirm cF = ((CardFirm) getListCard().get(Array.getInt(indFirm, i)));
                    lCF.add(cF);
                    price+=cF.getPrice();
                } catch (Exception e) {
                    penaltyCheating(curentUser);
                }
            }
            if(curentUser.getMoney()>price){
                for(CardFirm cF:lCF) {
                    if (cF.redeemFirm(this, curentUser)) {
                        ActionUser.createInstance(this, curentUser, REDEEM_FIRM, cF);
                        curentUser.getAvailableAction().remove(REDEEM_FIRM);
                        firmFilialSell(curentUser);
                        canBuyFilial();
                    }
                }
            }else{
                ActionUser.createInstance(this, curentUser, NOT_MONEY, price);
            }
        }else{
            penaltyCheating(curentUser);
        }
    }


    @Override
    public void buyFilial(int[] indFirm) {
        if(curentUser.getAvailableAction().contains(BUY_FILIAL)){
            try {
                Set<Integer> lICBF= canBuyFilial(curentUser);
                List<Integer> lMon=new ArrayList<>();
                for(int i=0;i<Array.getLength(indFirm);i++){
                    boolean canBuy=false;
                    for(int fN:lICBF){
                        if(Array.getInt(indFirm, i)==fN){
                            int numMon=((CardFirm)getListCard().get(fN)).getNumMonopoly();
                            for(int fNM:lMon){
                                if(numMon==fNM){
                                    //нельзя покупать 2 филиала в одной монополии за один ход
                                    //штраф
                                    penaltyCheating(curentUser);
                                    return;
                                }
                            }
                            lMon.add(numMon);
                            canBuy=true;
                            break;
                        }
                    }
                    if(!canBuy){
                        curentUser.getMonopByFilThisStep().remove(((CardFirm) getListCard().get(Array.getInt(indFirm, i))).getNumMonopoly());
                        //штраф
                        penaltyCheating(curentUser);
                        return;
                    }
                }
            } catch (Exception e) {
                //штраф
                penaltyCheating(curentUser);
                return;
            }
            for(int i=0;i<Array.getLength(indFirm);i++){
                try {
                    CardFirm cF=((CardFirm) getListCard().get(Array.getInt(indFirm, i)));
                    cF.buyFilial(this, curentUser);
                    curentUser.getMonopByFilThisStep().add(cF.getNumMonopoly());

                } catch (Exception e) {
                    //штраф
                    penaltyCheating(curentUser);
                }
            }
            canBuyFilial();
        }
    }


    @Override
    public void sellFilial(Set<Integer> indFirm) {
        if(curentUser.getAvailableAction().contains(SELL_FILIAL)){
            Set<Integer> lICBF=canSellFilial(curentUser);
            for(Integer indF:indFirm) {
                boolean canSell=false;
                for (Integer numFil : lICBF) {
                    if(indF.equals(numFil)){
                        canSell=true;
                        break;
                    }
                }
                if(!canSell){
                    penaltyCheating(curentUser);
                    return;
                }
            }
            for(Integer indF:indFirm){
                try {
                    CardFirm cF=((CardFirm) getListCard().get(indF));
                    cF.sellFilial(this, curentUser);
                    // curentUser.getMonopByFilThisStep().add(cF.getNumMonopoly());

                    ActionUser.createInstance(this,curentUser, SELL_FILIAL, cF);
                } catch (Exception e) {
                    //штраф
                    penaltyCheating(curentUser);
                }
            }
            curentUser.getAvailableAction().remove(SELL_FILIAL);
            canSellFilial();
            canCheckPenalty(curentUser);
        }
    }

    //проверка на возможность пользователя оплатить штраф
    public boolean canCheckPenalty(UserMonopoly userMonopoly){
        if(Math.abs(curentUser.getPenalty())>0 && curentUser.getMoney()>=Math.abs(curentUser.getPenalty())){
            curentUser.getAvailableAction().add(PAY_PENALTY);
            return true;
        }
        return false;
    }

    @Override
    public void gameClose(UserMonopoly user) {
        synchronized (room.getListViewUser()){
            room.getListViewUser().remove(user);
        }
    }

    //проверяем куплин ли хоть один филиал в монополии
    private boolean isBuyFilialInMonopoly(Integer numMonopoly) {
        for(Card c:getListCard()) {
            if(c instanceof CardFirm && ((CardFirm)c).getNumMonopoly()==numMonopoly && ((CardFirm)c).getFilialStay()>0) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    @Override
    public Collection<Integer> getPossibleFirmCh(String nameUser){
        Set<Integer> lC=new HashSet<>();
        UserMonopoly umFCH=getUserByName(nameUser);
        if(umFCH==null){
            return null;
        }
        for(Card card:listCard){
            if(card instanceof CardFirm){
                CardFirm cf= (CardFirm) card;
                if(cf.getUserOwner()!=null && umFCH.equals(cf.getUserOwner()) && !cf.isPut() && cf.getFilialStay()==0 && !isBuyFilialInMonopoly(cf.getNumMonopoly())){
                    lC.add(getListCard().indexOf(cf));
                }
            }
        }
        return lC;
    }

    @JsonIgnore
    @Override
    public Collection<Integer> getPossibleFirm(String type) {
        Set<Integer> lC=new HashSet<>();
        if((type.equals("PUT_FIRM") && curentUser.getAvailableAction().contains(PUT_FIRM)) || (type.equals("CHANGE_FIRM") && curentUser.getAvailableAction().contains(CHANGE_FIRM))) {
            for(Card card:listCard){
                if(card instanceof CardFirm){
                    CardFirm cf= (CardFirm) card;
                    if(cf.getUserOwner()!=null && curentUser.equals(cf.getUserOwner()) && !cf.isPut() && cf.getFilialStay()==0 && !isBuyFilialInMonopoly(cf.getNumMonopoly())){
                        lC.add(getListCard().indexOf(cf));
                    }
                }
            }
        }
        if(type.equals("REDEEM_FIRM") && curentUser.getAvailableAction().contains(REDEEM_FIRM)) {
            for(Card card:listCard){
                if(card instanceof CardFirm){
                    CardFirm cf= (CardFirm) card;
                    if(cf.getUserOwner()!=null && curentUser.equals(cf.getUserOwner()) && cf.isPut()){
                        lC.add(getListCard().indexOf(cf));
                    }
                }
            }
        }
        if(type.equals("BUY_FILIAL") && curentUser.getAvailableAction().contains(BUY_FILIAL)) {
            lC.addAll(canBuyFilial(curentUser));
        }
        if(type.equals("SELL_FILIAL") && curentUser.getAvailableAction().contains(SELL_FILIAL)) {
            lC.addAll(canSellFilial(curentUser));
        }
        return lC;
    }

    @JsonIgnore
    public Map<Integer, Set<CardFirm>> getAllMonopoly(){
        return getAllMonopoly(null);
    }

    @JsonIgnore
    public Map<Integer, Set<CardFirm>> getAllMonopoly(UserMonopoly user){
        Map<Integer,Set<CardFirm>> listAllMonopoly=new HashMap<>();
        Map<Integer,Set<CardFirm>> listUserMonopoly=new HashMap<>();
        for(Card card:listCard) {
            if (card instanceof CardFirm) {
                CardFirm cf = (CardFirm) card;
                if(cf.getUserOwner()!=null){
                    if(user!=null && !cf.getUserOwner().equals(user)){
                        continue;
                    }
                    Set<CardFirm> lCard=listAllMonopoly.get(cf.getNumMonopoly());
                    if(lCard==null){
                        lCard=new HashSet<>();
                        listAllMonopoly.put(cf.getNumMonopoly(),lCard);
                    }
                    lCard.add(cf);
                    if(lCard.size()==cf.getCountFirmInMonopoly()) {
                        listUserMonopoly.put(cf.getNumMonopoly(), lCard);
                    }
                }
            }
        }
        return listUserMonopoly;
    }

    //поиск пользователя по имени
    private UserMonopoly getUserByName(String userName){
        for(UserMonopoly um:getListUser()){
            if(um.getName().equals(userName)){
                return um;
            }
        }
        return null;
    }

    private ChangeFirm objectOffers;
    @Override
    public void changeFirm(Set<Integer> indFirm, Set<Integer> indFirm2, int money, int money2, String userName) {
        if(curentUser.getAvailableAction().contains(CHANGE_FIRM)){
            UserMonopoly umA=getUserByName(userName);
            if(umA==null){
                penaltyCheating(curentUser);
                return;
            }
            curentUser.doChangeFirm();
            curentUser.getAvailableAction().clear();
            umA.getAvailableAction().add(EXCHANGE_OFFERS);
            curentUser.setActivGamer(false);
            //todo сделать проверки на принадлежность фирм и необходимой суммы денег
            objectOffers=new ChangeFirm(indFirm, indFirm2, money, money2, curentUser,umA);
            curentUser=umA;
            ActionUser.createInstance(this,curentUser, CHANGE_USER, curentUser);
            curentUser.setActivGamer(true);
            ActionUser.createInstance(this,curentUser, EXCHANGE_OFFERS, objectOffers);
        }
    }

    public void changeFirm(ChangeFirm changeFirm) {
        this.changeFirm(changeFirm.getIndFirmUserChanger(),changeFirm.getIndFirm(),changeFirm.getMoneyUserChanger(),changeFirm.getMoney(),changeFirm.getUserName());
    }

    public void changeFirm(ActionMonopolyE type){
        if(curentUser.getAvailableAction().contains(EXCHANGE_OFFERS)){
            curentUser.getAvailableAction().clear();
            UserMonopoly usCH=objectOffers.getUserChanger();
            if(type==CHANGE_FIRM_OK){
                for (Integer indF:objectOffers.getIndFirmUserChanger()){
                    ((CardFirm)getListCard().get(indF)).setUserOwner(objectOffers.getUser());
                    ActionUser.createInstance(this,objectOffers.getUser(), BUY_FIRM, getListCard().get(indF));
                }
                objectOffers.getUser().setMoney(objectOffers.getUser().getMoney() + objectOffers.getMoneyUserChanger());
                usCH.setMoney(usCH.getMoney()-objectOffers.getMoneyUserChanger());
                for (Integer indF:objectOffers.getIndFirm()){
                    ((CardFirm)getListCard().get(indF)).setUserOwner(usCH);
                    ActionUser.createInstance(this,usCH, BUY_FIRM, getListCard().get(indF));
                }
                objectOffers.getUser().setMoney(objectOffers.getUser().getMoney() - objectOffers.getMoney());
                usCH.setMoney(usCH.getMoney()+objectOffers.getMoney());
                ActionUser.createInstance(this,curentUser, CHANGE_FIRM_OK, objectOffers);
            }else{
                ActionUser.createInstance(this,curentUser, CHANGE_FIRM_CANCAL, objectOffers);
            }
            curentUser.setActivGamer(false);
            curentUser=usCH;
            ActionUser.createInstance(this,curentUser, CHANGE_USER, curentUser);
            curentUser.setActivGamer(true);
            if(curentUser.isThrowCubs()) {
                Card card=getListCard().get(curentUser.getIndexPosition());
                if(card instanceof CardFirm) {
                    CardFirm cardF=(CardFirm)card;
                    if (cardF.getUserOwner() == null) {
                        if (curentUser.getMoney() >= cardF.getPrice()) {
                            curentUser.getAvailableAction().add(BUY_FIRM);
                        }
                        curentUser.getAvailableAction().add(AUCTION_START);
                    }
                }
                canCheckPenalty(curentUser);
                giveTakeCredit(curentUser);
                firmFilialSell(curentUser);
            }else{
                curentUser.getAvailableAction().clear();
                getListCard().get(curentUser.getIndexPosition()).transferCardForUser(this, curentUser);
                if(!canCheckPenalty(curentUser)){
                    curentUser.getAvailableAction().add(THROW_CUBE);
                }
                giveTakeCredit(curentUser);
                firmFilialSell(curentUser);
                canBuyFilial();
            }
            objectOffers=null;
        }
    }

    public void canSellFilial(){
        curentUser.getAvailableAction().remove(SELL_FILIAL);
        Set<Integer> list=canSellFilial(curentUser);
        if(list.size()>0){
            curentUser.getAvailableAction().add(SELL_FILIAL);
        }
    }

    //филиалы каких фирм может продать пользователь user.
    private Set<Integer> canSellFilial(UserMonopoly user){
        Set<Integer> lC=new HashSet<>();
        for(Set<CardFirm> cardList: getAllMonopoly(curentUser).values()){
            int maxM=0;
            int minM=cardList.iterator().next().getCountFilial();
            for(CardFirm cfs:cardList){
                if(maxM<cfs.getFilialStay()){
                    maxM=cfs.getFilialStay();
                }
                if(cfs.getFilialStay()<minM){
                    minM=cfs.getFilialStay();
                }
            }
            if(minM==maxM){
                if(minM>0){
                    minM-=1;
                }else{
                    continue;
                }
            }
            for(CardFirm cfs:cardList){
                if(cfs.getFilialStay()>minM){
                    lC.add(listCard.indexOf(cfs));
                }
            }
        }
        return lC;
    }

    public void canBuyFilial(){
        curentUser.getAvailableAction().remove(BUY_FILIAL);
        Set<Integer> list=canBuyFilial(curentUser);
        if(list.size()>0){
            curentUser.getAvailableAction().add(BUY_FILIAL);
        }
    }

    //филиалы каких фирм может купить пользователь user.
    private Set<Integer> canBuyFilial(UserMonopoly user){
        Set<Integer> lC=new HashSet<>();
        for(Set<CardFirm> cardList: getAllMonopoly(curentUser).values()){
            int maxM=0;
            int minM=cardList.iterator().next().getCountFilial();
            //ключ проверяющий заложенность филиала в монополи (если хоть 1 заложен то покупать филиалы нельзя)
            boolean keyPut=false;
            for(CardFirm cfs:cardList){
                if(maxM<cfs.getFilialStay()){
                    maxM=cfs.getFilialStay();
                }
                if(cfs.getFilialStay()<minM){
                    minM=cfs.getFilialStay();
                }
                if(cfs.isPut()){
                    keyPut=true;
                    break;
                }
            }
            if(keyPut){
                continue;
            }
            if(minM==maxM){
                if(maxM+1<=cardList.iterator().next().getCountFilial()){
                    maxM+=1;
                }else{
                    continue;
                }
            }
            for(CardFirm cfs:cardList){
                if(cfs.getFilialStay()<maxM){
                    //проверяем был ли куплен филиал пользователем user на этом шаге
                    boolean keyBuy=false;
                    for(Integer numMonop:user.getMonopByFilThisStep()){
                        if(cfs.getNumMonopoly()==numMonop.intValue()){
                            keyBuy=true;
                        }
                    }
                    if(!keyBuy) {
                        lC.add(listCard.indexOf(cfs));
                    }
                }
            }
        }
        return lC;
    }

    @Override
    public void gameEnd(UserMonopoly user) {
        synchronized (getListUser()){
            user.getAvailableAction().clear();
            for(Card card:getListCard()){
                if(card instanceof CardFirm){
                    CardFirm cardF=(CardFirm)card;
                    if(cardF.getUserOwner()!=null && cardF.getUserOwner().equals(user)) {
                        cardF.returnInBank(this);
                        ActionUser.createInstance(this,curentUser, RETURN_IN_BANK, cardF);
                    }
                }
            }
            //возврат денег тому на ячейки которого обанкротился , если такая есть
            if(user.getPenalty()<0){
                Card c = getListCard().get(user.getIndexPosition());
                if (c instanceof CardFirm) {
                    CardFirm card = (CardFirm) c;
                    int retM=Math.abs(user.getPenalty());
                    if(retM>user.getMoney()){
                        retM=user.getMoney();
                    }
                    card.getUserOwner().setMoney(card.getUserOwner().getMoney() + retM);
                    user.setPenalty(0);
                }
            }
            ActionUser.createInstance(this,user, LOOSE, null);
            getListUser().remove(user);
            getListViewUser().add(user);
            user.getAvailableAction().remove(GAME_END);
            user.getAvailableAction().add(GAME_CLOSE);
            if(user.equals(curentUser)){
                if(getListUser().size()>1) {
                    nextGamer();
                }
            }
            isWinSomebody();
        }
    }

    @Override
    public Auction getAuction() {
        return auction;
    }

    @Override
    public UserMonopoly getCurentUser() {
        return curentUser;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getPenalty_cheating() {
        return penalty_cheating;
    }

    public void setPenalty_cheating(Integer penalty_cheating) {
        this.penalty_cheating = penalty_cheating;
    }

    //проверка на возможность взять отдать кредит
    public void giveTakeCredit(UserMonopoly userMonopoly) {
        if(userMonopoly.getCredit()>0){
            userMonopoly.getAvailableAction().add(GIVE_CREDIT);
        }else{
            userMonopoly.getAvailableAction().add(TAKE_CREDIT);
        }
    }

    //проверка на возможность продать филиал или заложить или выкупить фирму
    public void firmFilialSell(UserMonopoly userMonopoly){
        for(Card card:getListCard()){
            if(card instanceof CardFirm && ((CardFirm)card).getUserOwner()==userMonopoly){
                //заложить фирму
                if(!((CardFirm)card).isPut() && ((CardFirm)card).getFilialStay()==0) {
                    userMonopoly.getAvailableAction().add(PUT_FIRM);
                    userMonopoly.getAvailableAction().add(CHANGE_FIRM);
                    continue;
                }
                //выкупить фирму
                if(curentUser.getPrison()==0) {
                    if (((CardFirm) card).isPut() && userMonopoly.getMoney() > ((CardFirm) card).getPrice()) {
                        userMonopoly.getAvailableAction().add(REDEEM_FIRM);
                        continue;
                    }
                }
                //продать филиал
                if(((CardFirm) card).getFilialStay()>0){
                    userMonopoly.getAvailableAction().add(SELL_FILIAL);
                }
            }
        }
    }

    public void goToCard(int countStep){
        int pos=0;
        if(curentUser.isGoForward()){
            pos=curentUser.getIndexPosition()+countStep;
            if(pos>=listCard.size()){
                pos=pos-listCard.size();
                //выдать деньги за круг
                getMoneybyCircle(curentUser);
                //увеличить кредит на 50%
                curentUser.setCredit((int)(curentUser.getCredit()*1.5));
            }
        }else{
            pos=curentUser.getIndexPosition()-(countStep);
            if(pos<0){
                pos=listCard.size()-1+pos;
            }
            //меняем направление на правильное
            curentUser.setGoForward(true);
        }
        curentUser.setThrowCubs(true);
        curentUser.setIndexPosition(pos);
        ActionUser.createInstance(this,curentUser, GO_SELL, countStep);
        listCard.get(pos).transferCardForUser(this,curentUser);
        if(curentUser.getPrison()>0){
            nextGamer();
            return;
        }
        canCheckPenalty(curentUser);
        giveTakeCredit(curentUser);
        firmFilialSell(curentUser);
    }
}
