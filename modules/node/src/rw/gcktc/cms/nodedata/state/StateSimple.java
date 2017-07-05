package rw.gcktc.cms.nodedata.state;

import rw.gcktc.cms.nodedata.Node;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 01.11.13
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue(value = "SIMPLE")
public class StateSimple extends StateDefault implements State, Serializable {

    @Override
    public void addState(Node node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeState(Node node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
