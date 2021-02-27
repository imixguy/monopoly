package by.imix.cms.material;

import by.imix.cms.material.parsetonode.IncludeSuperclass;
import by.imix.cms.nodedata.Node;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 14:05
 * Стандартный тип материала реализованный на нодах
 */
@IncludeSuperclass
public class Page extends ContentNode {
    private String type = "page";

    public Page() {
        super();
    }

    public Page(Node node) {
        super(node);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    //запрещаем переопределять тип какой бы не указали все равно останется page
    public void setType(String type) {
        this.type="page";
    }

    public Object clone() throws CloneNotSupportedException{
        Page page = (Page) super.clone();
        return page;
    }
}
