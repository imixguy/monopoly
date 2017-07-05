package rw.gcktc.cms.nodedata;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.10.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 * Узел который позволяет хранить историю изменений этого узла
 */
@Entity
@Table(name = "history_node")
@AttributeOverrides({
        @AttributeOverride(name="id_usercreator", column=@Column(name="id_usercreator")),
        @AttributeOverride(name="id_usercorrector", column=@Column(name="id_usercorrector")),
        @AttributeOverride(name="datecreate", column=@Column(name="datecreate")),
        @AttributeOverride(name="datecorrect", column=@Column(name="datecorrect")),
        @AttributeOverride(name="id_hystPremParent", column=@Column(name="id_hystPremParent")),
        @AttributeOverride(name="id_hystParent", column=@Column(name="id_hystParent")),
        @AttributeOverride(name="hystory", column=@Column(name="hystory"))
})
public class HistoryNode extends Node implements Serializable,Cloneable {
    private Long id_hystPremParent;//идентификатор самого первого узла
    private Long id_hystParent;//идентификатор предыдущего узла
//    @Column(name = "hystory",columnDefinition = "SMALLINT default 0 not null")
    @Column(name= "historical",nullable = false)
    private Boolean hystory=false; //является ли данный узел историческим
    @Column(nullable = false)
    private Boolean removed=false; //если true узел удален

    public HistoryNode(){}

    public HistoryNode(Node nodechanger) {
        super(nodechanger);
//        hystory=false;
    }

    public HistoryNode(Node nodechanger, HistoryNode historyNode) {
        super(nodechanger);
        historyNode.hystory=true;
        this.id_hystParent=historyNode.getId();
        this.id_hystPremParent=historyNode.getId_hystPremParent();
        hystory=false;

    }

    public Long getId_hystPremParent() {
        return id_hystPremParent;
    }

    public void setId_hystPremParent(Long id_hystPremParent) {
        this.id_hystPremParent = id_hystPremParent;
    }

    public Long getId_hystParent() {
        return id_hystParent;
    }

    public void setId_hystParent(Long id_hystParent) {
        this.id_hystParent = id_hystParent;
    }

    public Boolean getHystory() {
        return hystory;
    }

    public void setHystory(Boolean hystory) {
        this.hystory = (hystory==null)?false:hystory;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = (removed==null)?false:removed;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HistoryNode node = (HistoryNode) super.clone();
        node.id_hystPremParent =(id_hystPremParent==null)?null:new Long(id_hystPremParent);
        node.id_hystParent = (id_hystParent==null)?null:new Long(id_hystParent);
        node.hystory=new Boolean(hystory);
        return node;
    }
}