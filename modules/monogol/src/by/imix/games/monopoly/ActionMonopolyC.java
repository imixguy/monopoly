package by.imix.games.monopoly;

import by.imix.games.gamecore.ActionRoomC;

/**
 * Created by miha on 25.12.2014.
 */
public class ActionMonopolyC extends ActionRoomC implements AvailableActionMonopoly{
    private boolean throwCube=false;
    private boolean buyFirm=false;
    private boolean buyFilial=false;
    private boolean takeCredit=false;
    private boolean giveCredit=false;
    //заложить фирму
    private boolean putFirm=false;
    //выкупить фирму
    private boolean redeemFirm=false;
    private boolean sellFilial=false;
    private boolean payPenalty=false;
    private boolean startAuction=false;
    private boolean auctionBuy=false;
    private boolean auctionFold=false;
    private boolean gameClose=false;
    private boolean gameEnd=true;


    @Override
    public void clearAction(){
        super.clearAction();
        throwCube=false;
        buyFirm=false;
        buyFilial=false;
        takeCredit=false;
        giveCredit=false;
        putFirm=false;
        redeemFirm=false;
        sellFilial=false;
        payPenalty=false;
        startAuction=false;
        auctionBuy=false;
        auctionFold=false;
        gameClose=false;
    }

    public boolean isThrowCube() {
        return throwCube;
    }

    public void setThrowCube(boolean throwCube) {
        this.throwCube = throwCube;
    }

    public boolean isBuyFirm() {
        return buyFirm;
    }

    public void setBuyFirm(boolean buyFirm) {
        this.buyFirm = buyFirm;
    }

    public boolean isBuyFilial() {
        return buyFilial;
    }

    public void setBuyFilial(boolean buyFilial) {
        this.buyFilial = buyFilial;
    }

    public boolean isTakeCredit() {
        return takeCredit;
    }

    public void setTakeCredit(boolean takeCredit) {
        this.takeCredit = takeCredit;
    }

    public boolean isGiveCredit() {
        return giveCredit;
    }

    public void setGiveCredit(boolean giveCredit) {
        this.giveCredit = giveCredit;
    }

    public boolean isPutFirm() {
        return putFirm;
    }

    public void setPutFirm(boolean putFirm) {
        this.putFirm = putFirm;
    }

    public boolean isRedeemFirm() {
        return redeemFirm;
    }

    public void setRedeemFirm(boolean redeemFirm) {
        this.redeemFirm = redeemFirm;
    }

    public boolean isSellFilial() {
        return sellFilial;
    }

    public void setSellFilial(boolean sellFilial) {
        this.sellFilial = sellFilial;
    }

    public boolean isPayPenalty() {
        return payPenalty;
    }

    public void setPayPenalty(boolean payPenalty) {
        this.payPenalty = payPenalty;
    }

    public boolean isStartAuction() {
        return startAuction;
    }

    public void setStartAuction(boolean startAuction) {
        this.startAuction = startAuction;
    }

    public boolean isAuctionFold() {
        return auctionFold;
    }

    public void setAuctionFold(boolean auctionFold) {
        this.auctionFold = auctionFold;
    }

    public boolean isAuctionBuy() {
        return auctionBuy;
    }

    public void setAuctionBuy(boolean auctionBuy) {
        this.auctionBuy = auctionBuy;
    }

    @Override
    public boolean isGameClose() {
        return gameClose;
    }

    @Override
    public void setGameClose(boolean gameClose) {
        this.gameClose=gameClose;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
}
