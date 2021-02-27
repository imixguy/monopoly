package by.imix.cms.nodedata.state;

import by.imix.cms.nodedata.Node;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 13.11.13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue(value = "CREATE_USER")
public class StateCreateUser extends StateCreateDefault implements State, Serializable {
    @Override
    public void addState(Node node) {
//        User user=new User();

        System.out.print("AddState");
    }

    @Override
    public void removeState(Node node) {
        System.out.print("RState");
    }
}
