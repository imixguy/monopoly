package by.imix.cms.usermanager;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 17.12.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
//@Entity
//@DiscriminatorColumn(name="UserASKBD",discriminatorType = DiscriminatorType.STRING)
public class UserASKBD extends User {
    String askbd;

    public String getAskbd() {
        return askbd;
    }

    public void setAskbd(String askbd) {
        this.askbd = askbd;
    }
}
