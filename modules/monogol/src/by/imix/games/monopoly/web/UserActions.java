package by.imix.games.monopoly.web;

import by.imix.games.monopoly.ChangeFirm;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Mikhail_Kachanouski on 7/28/2017.
 * Class every possible action from user for monopoly game
 */
public interface UserActions {
    /**
     * Throw cube
     */
    void throwCube();

    /**
     * Buy firm
     */
    void buyFirm();

    /**
     * Buy filial for firm
     * @param indFirm number firm
     */
    void buyFilial(@RequestParam("indFirm[]") int[] indFirm);

    /**
     * take credit
     */
    void takeCredit();

    /**
     * give credit
     */
    void giveCredit();

    /**
     * put firm by firm
     * @param indFirm number firm
     */
    void putFirm(@RequestParam("indFirm[]") int[] indFirm);

    /**
     * Firms for some type of manipulation (put buy sell redeem)
     * @param type type action for witch sked firms
     * @return list firm for specific type
     */
    Collection<Integer> getPossibleFirm(@PathVariable String type);

    /**
     * Firms for specific user {@nameUser}
     * @param nameUser name User
     * @return list firm for specific user {@nameUser}
     */
    Collection<Integer> getPossibleFirmCh(@PathVariable String nameUser);

    /**
     * Set filial on defined firms {@indFirm}
     * @param indFirm list firm for witch need set filial
     */
    void sellFilial(@RequestParam("indFirm[]") Set<Integer> indFirm);

    /**
     * Set redeem firms {@indFirm}
     * @param indFirm list firm for redeem
     */
    void redeemFirm(@RequestParam("indFirm[]") int[] indFirm);

    /**
     * Change firm
     * @param changeFirm offer firm
     */
    void changeFirm(ChangeFirm changeFirm);

    void changeFirmOk();

    void changeFirmCancal();

    void outPrison();

    void auctionStart();

    void payPenalty();

    void auctionBuy();

    void auctionFold();

    void gameEnd();

    String gameClose();

    void sendMessage(@RequestParam("message") String message);
}
