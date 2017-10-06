package by.imix.games.monopoly.web;

import by.imix.games.monopoly.ActionMonopolyE;
import by.imix.games.monopoly.ChangeFirm;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

/**
 * Created by miha on 23.12.2014.
 */

@Controller("userActionsController")
public class UserActionsController implements UserActions {
    private Logger log= LoggerFactory.getLogger(UserActionsController.class);

    @Autowired
    GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/throw_cube", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void throwCube() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.throwCube();
        }
    }

    private GameMonopoly getGame(){
        return gameManager.getGameByUser(gameManager.getUser()).get(0);
    }

    private UserMonopoly getUserMonopoly(){
        return gameManager.getUser();
    }


    @Override
    @RequestMapping(value = "/games/monopoly/actions/buy_firm", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void buyFirm() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.buyFirm();
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/buy_filial", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void buyFilial(@RequestParam("indFirm[]") int[] indFirm) {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.buyFilial(indFirm);
        }
    }

    @Override
    public void takeCredit() {

    }

    @Override
    public void giveCredit() {

    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/put_firm", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void putFirm(@RequestParam("indFirm[]") int[] indFirm) {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.putFirm(indFirm);
        }else{
            //штраф
            game.penaltyCheating(getUserMonopoly());
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/getPossibleFirm/{type}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Collection<Integer> getPossibleFirm(@PathVariable String type){
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            return game.getPossibleFirm(type);
        }else{
            //штраф
            game.penaltyCheating(getUserMonopoly());
            return null;
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/getPossibleFirmCh/{nameUser}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Collection<Integer> getPossibleFirmCh(@PathVariable String nameUser){
        GameMonopoly game=getGame();
        return game.getPossibleFirmCh(nameUser);

    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/sell_filial", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void sellFilial(@RequestParam("indFirm[]") Set<Integer> indFirm) {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.sellFilial(indFirm);
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/redeem_firm", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void redeemFirm(@RequestParam("indFirm[]") int[] indFirm) {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.redeemFirm(indFirm);
        }
    }

//    @RequestMapping(value = "/games/monopoly/actions/getChange_firm", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public ChangeFirm getChangeFirm() {
//        return new ChangeFirm(new HashSet<Integer>(Arrays.asList(1,2)),new HashSet<Integer>(Arrays.asList(2,3)),0,0,"");
//    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/change_firm", method = RequestMethod.POST)
    @ResponseBody
    public void changeFirm(ChangeFirm changeFirm) {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.changeFirm(changeFirm);//indFirm,indFirm2,money,money2,userName);
        }
    }



    @Override
    @RequestMapping(value = "/games/monopoly/actions/change_firm_ok", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void changeFirmApprove() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.changeFirm(ActionMonopolyE.CHANGE_FIRM_OK);
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/change_firm_cancal", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void changeFirmReject() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.changeFirm(ActionMonopolyE.CHANGE_FIRM_CANCAL);
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/out_prison", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void outPrison() {

    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/auction_start", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void auctionStart() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.startAuction();
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/pay_penalty", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void payPenalty() {
        GameMonopoly game=getGame();
        if(getUserMonopoly()==game.getCurentUser()){
            game.payPenalty();
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/auction_buy", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void auctionBuy() {
        GameMonopoly game=getGame();
        if(game.getAuction()!=null && getUserMonopoly()==game.getAuction().getAuctionUser()){
            game.getAuction().auctionBuy();
        }else{
            //штраф
            game.penaltyCheating(getUserMonopoly());
        }
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/auction_fold", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void auctionFold() {
        GameMonopoly game=getGame();
        if(game.getAuction()!=null && getUserMonopoly()==game.getAuction().getAuctionUser()){
            game.getAuction().auctionFold();
        }else{
            //штраф
            game.penaltyCheating(getUserMonopoly());
        }

    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/game_end", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public void gameEnd() {
        getGame().gameEnd(getUserMonopoly());
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/game_close", method = RequestMethod.GET)
    public String gameClose() {
        GameMonopoly game=getGame();
        game.gameClose(getUserMonopoly());
        if(game.getListUser().size()==0){
            gameManager.gameEnd(game);
        }
        return "redirect:/games/room/rooms.html";
    }

    @Override
    @RequestMapping(value = "/games/monopoly/actions/send_message", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void sendMessage(@RequestParam("message") String message) {
        GameMonopoly game=getGame();
        ActionUser.createInstance(game,getUserMonopoly(), ActionMonopolyE.SEND_MESSAGE, message);
    }
}
