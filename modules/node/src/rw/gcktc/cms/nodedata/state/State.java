package rw.gcktc.cms.nodedata.state;

import rw.gcktc.cms.nodedata.Node;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.10.13
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public interface State extends Serializable, Cloneable {
    void addState(Node node);
    void removeState(Node node);
    Long getId();
    void setId(Long id);
    String getSimpleDescription();
    void setSimpleDescription(String simpleDescription);
}
