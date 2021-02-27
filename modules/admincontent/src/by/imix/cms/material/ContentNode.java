package by.imix.cms.material;

import by.imix.cms.material.parsetonode.NodeExt;
import by.imix.cms.nodedata.Node;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 13:56
 * Класс для работы с контентом через сущность узлов.
 */

public class ContentNode extends NodeExt implements Content, Cloneable {

    protected String type;//тип материала, если он динамический то берется из   typeContent.getTypeContent(); статический прописывается в классе
    protected String name;//Название
    protected String content;//Описание
    protected boolean pastRoot=false;//разместить на главной

    public ContentNode() {
        super();
    }

    public ContentNode(Node node) {
        super(node);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPastRoot() {
        return pastRoot;
    }

    public void setPastRoot(boolean pastRoot) {
        this.pastRoot = pastRoot;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        ContentNode nodeDH = (ContentNode) super.clone();
        return nodeDH;
    }
}
