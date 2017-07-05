package rw.gcktc.webcms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.File;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 17.12.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class UserWeb extends User{
    private rw.gcktc.cms.usermanager.User userw;
    private static String webRootPath="";
    //webRootPath=httpSession.getServletContext().getRealPath("");
    private String avatarPath= File.separator+"resources"+File.separator+"avataruser";
    private String avatarName= "";
    //webRootPath+File.separator+"resources"+File.separator+"avataruser";

    public UserWeb(String username, String password, Collection<? extends GrantedAuthority> authorities, rw.gcktc.cms.usermanager.User userw) {
        super(username,password,authorities);
        this.userw=userw;
    }

    public UserWeb(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, rw.gcktc.cms.usermanager.User userw) {
        super(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
        this.userw=userw;
    }

    public rw.gcktc.cms.usermanager.User getUserw() {
        return userw;
    }

    public void setUserw(rw.gcktc.cms.usermanager.User userw) {
        this.userw = userw;
    }
}
