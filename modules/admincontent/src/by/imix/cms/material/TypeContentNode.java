package by.imix.cms.material;

import by.imix.cms.nodedata.Node;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 22.05.14
 * Time: 10:55
 *
 * Данный класс отвечает за создание простого материала основанного на property и работающий через узловую структуру
 */
public class TypeContentNode extends ContentNode implements Cloneable {

    public TypeContentNode() {
        super();
    }

    public TypeContentNode(Node node) {
        super(node);
    }
}
