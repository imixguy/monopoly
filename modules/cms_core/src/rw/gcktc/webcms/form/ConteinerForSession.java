package rw.gcktc.webcms.form;

import rw.gcktc.cms.usermanager.User;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 27.11.13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class ConteinerForSession {
    private User user;
    private String avatarPath;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
