package by.imix.games.monopoly;

import by.imix.games.gamecore.ActionRoom;

/**
 * Created by miha on 06.01.2015.
 */
public enum ActionMonopolyE  implements ActionRoom {
    START_GAME ("startGame"),
    //бросает кубик
    THROW_CUBE ("throwCube"),
    //идет на клетку
    GO_SELL ("goSell"),
    //идти в тюрьму
    GO_PRISON ("goPrison"),
    BUY_FIRM ("buyFirm"),
    BUY_FILIAL ("buyFilial"),
    //взял кредит
    TAKE_CREDIT ("takeCredit"),
    //вернул кредит
    GIVE_CREDIT ("giveCredit"),
    //заложить фирму
    PUT_FIRM ("putFirm"),
    //выкупить фирму
    REDEEM_FIRM ("redeemFirm"),
    //Обмен фирмами (предложить обмен)
    CHANGE_FIRM ("changeFirm"),
    //Обмен фирмами согласится
    CHANGE_FIRM_OK ("changeFirmOk"),
    //Обмен фирмами отказать
    CHANGE_FIRM_CANCAL ("changeFirmCancal"),
    //продал филиал
    SELL_FILIAL ("sellFilial"),
    //получил штраф
    GET_PENALTY ("getPenalty"),
    //заплатил штраф
    PAY_PENALTY ("payPenalty"),
    //получил доход
    RECEIVE_INCOME ("receiveIncome"),
    //штраф за обман
    PENALTY_CHEATING ("penaltyCheating"),
    //смена пользователя
    CHANGE_USER ("changeUser"),
    //предлагает обмен
    EXCHANGE_OFFERS ("exchange_offers"),
    //Объявлен аукцион
    AUCTION_START ("auctionStart"),
    //принял цену для покупки
    AUCTION_BUY ("auctionBuy"),
    //выходит из аукциона
    AUCTION_FOLD ("aucionFold"),
    //меняем пользователя учавствующего в аукционе
    AUCTION_CHANGE_USER ("auctionChangeUser"),
    //аукцион не состоялся
    AUCTION_BRACK ("auctionBrack"),
    //не хватает денег
    NOT_MONEY ("notMoney"),
    //игрок выиграл
    WIN ("win"),
    //игрок проиграл
    LOOSE ("loose"),
    //Вернуть в банк
    RETURN_IN_BANK ("returnInBank"),
    //Cдаться
    GAME_END ("gameEnd"),
    //Выйти из игры
    GAME_CLOSE ("gameClose"),
    //Отправить сообщение
    SEND_MESSAGE ("sendMessage");

    private String key="";

    private ActionMonopolyE(String key) {
        this.key=key;
    }

    public String getKey() {
        return key;
    }
}