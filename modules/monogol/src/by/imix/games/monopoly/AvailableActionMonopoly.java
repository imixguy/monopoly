package by.imix.games.monopoly;

import by.imix.games.gamecore.AvailableAction;

/**
 * Created by miha on 02.01.2015.
 */
public interface AvailableActionMonopoly extends AvailableAction{
    boolean isThrowCube();
    void setThrowCube(boolean throwCube);
    boolean isBuyFirm();
    void setBuyFirm(boolean buyFirm);
    boolean isBuyFilial();
    void setBuyFilial(boolean buyFilial);
    boolean isTakeCredit();
    void setTakeCredit(boolean takeCredit);
    boolean isGiveCredit();
    void setGiveCredit(boolean giveCredit);
    boolean isPutFirm();
    void setPutFirm(boolean putFirm);
    boolean isRedeemFirm();
    void setRedeemFirm(boolean redeemFirm);
    boolean isSellFilial();
    void setSellFilial(boolean sellFilial);
    boolean isPayPenalty();
    void setPayPenalty(boolean payPenalty);
    boolean isStartAuction();
    void setStartAuction(boolean startAuction);
    boolean isAuctionFold();
    void setAuctionFold(boolean auctionFold);
    boolean isAuctionBuy();
    void setAuctionBuy(boolean auctionBuy);
    boolean isGameEnd();
    void setGameEnd(boolean gameEnd);
    boolean isGameClose();
    void setGameClose(boolean gameClose);
}
