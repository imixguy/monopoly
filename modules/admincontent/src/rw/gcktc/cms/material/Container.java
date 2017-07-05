package rw.gcktc.cms.material;

import rw.gcktc.cms.nodedata.Node;

/**
 * Created by miha on 04.09.2014.
 * Класс отвечающий за размещение информации в к
 */
public class Container extends AdditContentNode {
    private String type = "container";

    public Container() {
        super();
    }

    public Container(Node node) {
        super(node);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    //запрещаем переопределять тип какой бы не указали все равно останется page
    public void setType(String type) {
        node.addOnlyOneProperty("type", "container");
        this.type="container";
    }

    public Object clone() throws CloneNotSupportedException{
        Container container = (Container) super.clone();
        return container;
    }
}