package by.imix.games.monopoly;

import by.imix.games.gamecore.card.Card;
import by.imix.games.gamecore.game.Room;
import by.imix.games.monopoly.card.CardFirm;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by miha on 25.12.2014.
 */
public interface GameMonopoly extends Room {
    Room getRoom();
    void setRoom(Room room);

    //стартовала ли игра
    boolean isStartGame();
    //получить список карт
    List<Card> getListCard();
    //следующий игрок
    void nextGamer();
    //получить текущего пльзователя
    UserMonopoly getCurentUser();

    //получить действующий аукцион
    Auction getAuction();

    //стартовые деньги
    void setStartMany(int credit);
    //деньги за круг
    void setCircleMany(int circleMany);
    //максимальный кредит
    void setCredit(int credit);

    @Override
    List<UserMonopoly> getListUser();

    @Override
    List<UserMonopoly> getListViewUser();

    //Бросить кубик
    int[] throwCube();
    //купить фирму
    void buyFirm();
    //купить филиал
    void buyFilial(int[] indFirm);
    //оплатить штраф
    void payPenalty();
    //объявить аукцион
    void startAuction();
    //Остановить аукцион
    void stopAuction();
    //заложить фирму
    void putFirm(int[] indFirm);
    //продать филиал
    void sellFilial(Set<Integer> indFirm);
    //выкупить фирму
    void redeemFirm(int[] indFirm);
    //Закончить игру(сдаться)
    void gameEnd(UserMonopoly user);
    //Закрыть окно и выйти окончательно из игры
    void gameClose(UserMonopoly user);

    //возвращает индексы фирм которые можно заложить
    Collection<Integer> getPossibleFirm(String type);
    //возвращает индексы фирм которые может обменять пользователь с именем nameUser
    Collection<Integer> getPossibleFirmCh(String nameUser);

    void penaltyCheating(UserMonopoly user);
    void penaltyCheating(UserMonopoly user, Object obj);

    void canBuyFilial();

    //получить список монополий
    Map<Integer, Set<CardFirm>> getAllMonopoly();
    Map<Integer, Set<CardFirm>> getAllMonopoly(UserMonopoly user);

    //предложение обмена текущим игроком (Предлагает фирмы indFirm и денег many) игроку с именем userName
    //запрашивает фирмы indFirm2 и денег many2
    void changeFirm(Set<Integer> indFirm, Set<Integer> indFirm2, int many, int many2, String userName);
    void changeFirm(ChangeFirm changeFirm);
    //согласие с предложенным обменом либо не согласие зависит от типа type CHANGE_FIRM_CANCAL - не согласен, иначе CHANGE_FIRM_OK
    void changeFirm(ActionMonopolyE type);

    //проверка на возможность пользователя оплатить штраф
    boolean canCheckPenalty(UserMonopoly userMonopoly);

    //проверка на возможность взять отдать кредит
    void giveTakeCredit(UserMonopoly userMonopoly);

    //проверка на возможность продать филиал или заложить или выкупить фирму
    public void firmFilialSell(UserMonopoly userMonopoly);

    void goToCard(int countStep);
}
