package rw.gcktc.cms.nodedata;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.10.13
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "node_property")
public class NodeProperty implements Serializable,Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
//    @ManyToOne
//    @JoinColumn(name="id_node", nullable=false)
//    private Node node;

    private String value;
    private String keyt;
    @Column(name = "id_node",insertable = false, updatable = false)
    private Long id_node;
    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
//    @Cascade( org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    private BigProperty bigProperty;

    public NodeProperty() {
    }

    public NodeProperty(String keyt,String value) {
        setKeyt(keyt);
        setValue(value);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        if(bigProperty!=null){
            return bigProperty.getValue();
        }
        return value;
    }

    public void setValue(String value) {
        if(value!=null && value.length()>254){
            this.value = "";
            bigProperty=new BigProperty();
            bigProperty.setValue(value);
        }else{
            //todo удалить bigProperty из БД
            bigProperty=null;
            this.value = value;
        }
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Long getId_node() {
        return id_node;
    }

    public void setId_node(Long id_node) {
        this.id_node = id_node;
    }

    private BigProperty getBigProperty() {
        return bigProperty;
    }

    private void setBigProperty(BigProperty bigProperty) {
        this.bigProperty = bigProperty;
    }

    //    public Node getNode() {
//        return node;
//    }
//
//    public void setNode(Node node) {
//        this.node = node;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeProperty that = (NodeProperty) o;

        if (bigProperty != null ? !bigProperty.equals(that.bigProperty) : that.bigProperty != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (id_node != null ? !id_node.equals(that.id_node) : that.id_node != null) return false;
        if (keyt != null ? !keyt.equals(that.keyt) : that.keyt != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (keyt != null ? keyt.hashCode() : 0);
        result = 31 * result + (id_node != null ? id_node.hashCode() : 0);
        result = 31 * result + (bigProperty != null ? bigProperty.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        NodeProperty nodeP = (NodeProperty) super.clone();
        nodeP.value = value;
        nodeP.keyt = keyt;
        nodeP.id_node=new Long(id_node);
        nodeP.bigProperty=(bigProperty==null)?null:(BigProperty) bigProperty.clone();
        return nodeP;
    }
}
