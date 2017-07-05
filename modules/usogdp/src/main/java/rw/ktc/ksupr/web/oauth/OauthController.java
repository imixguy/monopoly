package rw.ktc.ksupr.web.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sedler on 09.02.15.
 */
@Controller
@RequestMapping("/")
public class OauthController {

    OauthServise oauthServise;

    public OauthServise getOauthServise() {
        return oauthServise;
    }

    @Autowired
    public void setOauthServise(OauthServise oauthServise) {
        this.oauthServise = oauthServise;
    }

    @RequestMapping(value="/permission", method = RequestMethod.GET)
    public String getPermission(Model model) {
        String permission = oauthServise.getUserPermission();
        model.addAttribute("permission", permission);
        return "oauth";

    }
}
