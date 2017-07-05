package by.imix.games.monopoly;

import by.imix.games.monopoly.card.CardFirm;
import by.imix.games.monopoly.web.ActionUser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static by.imix.games.monopoly.ActionMonopolyE.*;

/**
 * Created by miha on 03.01.2015.
 */
public class Auction {
    private GameMonopoly gameMonopoly;
    private UserMonopoly auctionUser=null;
    private CardFirm auctionStartFirm=null;
    private List<UserMonopoly> userGoAuction=null;
    private int old_price;

    public Auction(GameMonopoly gameMonopoly) {
        this.gameMonopoly=gameMonopoly;
        auctionStartFirm=(CardFirm)gameMonopoly.getListCard().get(gameMonopoly.getCurentUser().getIndexPosition());
        old_price=auctionStartFirm.getPrice();
        //priceTop=Math.round(old_price/10);
        userGoAuction=new ArrayList<>();
        int indCU=gameMonopoly.getListUser().indexOf(gameMonopoly.getCurentUser());
        if(gameMonopoly.getListUser().size()-1!=indCU) {
            for (int i = indCU+1; i < gameMonopoly.getListUser().size(); i++) {
                if(gameMonopoly.getListUser().get(i).getMany()>=auctionStartFirm.getPrice()) {
                    userGoAuction.add(gameMonopoly.getListUser().get(i));
                }
            }
        }
        if(indCU!=0){
            for (int i = 0; i < indCU; i++) {
                if(gameMonopoly.getListUser().get(i).getMany()>auctionStartFirm.getPrice()) {
                    userGoAuction.add(gameMonopoly.getListUser().get(i));
                }
            }
        }
        ActionUser.createInstance(gameMonopoly,gameMonopoly.getCurentUser(), AUCTION_START, auctionStartFirm);
    }

    public void nextGamer() {
        if(userGoAuction.size()==0){
            ActionUser.createInstance(gameMonopoly,gameMonopoly.getCurentUser(), AUCTION_BRACK, null);
            gameMonopoly.stopAuction();
            return;
        }
        if(auctionUser==null){
            auctionUser=userGoAuction.get(0);
        }else{
            auctionUser.setActivGamer(false);
            if(userGoAuction.get(userGoAuction.size()-1)==auctionUser){
                auctionUser=userGoAuction.get(0);
            }else{
                auctionUser=userGoAuction.get(userGoAuction.indexOf(auctionUser)+1);
            }
        }
        if(userGoAuction.size()==1 && auctionStartFirm.getPrice() > old_price) {
            buyFirm();
            return;
        }
        auctionUser.setActivGamer(true);
        Set sdf= auctionUser.getAvailableAction();
        sdf.addAll(EnumSet.of(AUCTION_BUY,AUCTION_FOLD));
        ActionUser.createInstance(gameMonopoly,auctionUser, AUCTION_CHANGE_USER, auctionStartFirm);
    }

    public void auctionBuy(){
        if(auctionUser.getAvailableAction().contains(AUCTION_BUY)) {
            auctionUser.getAvailableAction().clear();
            if(userGoAuction.size()==1){
                buyFirm();
                return;
            }
            ActionUser.createInstance(gameMonopoly, auctionUser, AUCTION_BUY, auctionStartFirm);
            auctionStartFirm.setPrice(auctionStartFirm.getPrice() + Math.round(old_price / 10));
            //убираем тех, кто не может поставить такую цену
            List<UserMonopoly> lr = new ArrayList<>();
            for (int i = 0; i < userGoAuction.size(); i++) {
                if (userGoAuction.get(i) != auctionUser) {
                    if (userGoAuction.get(i).getMany() < auctionStartFirm.getPrice()) {
                        lr.add(userGoAuction.get(i));
                        ActionUser.createInstance(gameMonopoly, auctionUser, AUCTION_FOLD, auctionStartFirm);
                    }
                }
            }
            userGoAuction.removeAll(lr);
            nextGamer();
        }else{
            //штраф
            gameMonopoly.penaltyCheating(auctionUser);
        }
    }

    public void auctionFold(){
        if(auctionUser.getAvailableAction().contains(AUCTION_FOLD)) {
            auctionUser.getAvailableAction().clear();
            userGoAuction.remove(auctionUser);
            ActionUser.createInstance(gameMonopoly, auctionUser, AUCTION_FOLD, null);
            if(userGoAuction.size()==0){
                auctionUser.setActivGamer(false);
                ActionUser.createInstance(gameMonopoly, auctionUser, AUCTION_BRACK, null);
                auctionStartFirm.setPrice(old_price);
                gameMonopoly.stopAuction();
                return;
            }else {
                nextGamer();
            }
        }else{
            //штраф
            gameMonopoly.penaltyCheating(auctionUser);
        }
    }

    private void buyFirm(){
        if(auctionUser.getMany()>auctionStartFirm.getPrice()){
            //забираем у выигравшего деньги за фирму
            auctionUser.setMany(auctionUser.getMany()-auctionStartFirm.getPrice());
            ActionUser.createInstance(gameMonopoly,auctionUser, BUY_FIRM, auctionStartFirm);

            auctionStartFirm.setUserOwner(auctionUser);

            //объявившему аукцион отдаем заработанное на аукционе
            gameMonopoly.getCurentUser().setMany(gameMonopoly.getCurentUser().getMany()+(auctionStartFirm.getPrice()-old_price));
            ActionUser.createInstance(gameMonopoly,auctionUser, PAY_PENALTY, (auctionStartFirm.getPrice()-old_price));

            auctionUser.setActivGamer(false);
        }
        gameMonopoly.stopAuction();
    }

    public CardFirm getAuctionStartFirm() {
        return auctionStartFirm;
    }

    public UserMonopoly getAuctionUser() {
        return auctionUser;
    }
}
