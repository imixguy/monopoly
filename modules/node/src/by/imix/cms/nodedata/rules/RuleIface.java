package by.imix.cms.nodedata.rules;

import by.imix.cms.nodedata.Node;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 22.01.14
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
public interface RuleIface {
    void doRule(Node nodePerformer,Node node);
}
