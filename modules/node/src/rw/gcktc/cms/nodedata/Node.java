package rw.gcktc.cms.nodedata;


import org.apache.log4j.Logger;
import rw.gcktc.cms.nodedata.rules.Rule;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.10.13
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 * Класс реализующий основную логику работы узлов. Все остальные классы наследуются от него.
 */

@Entity
@Table(name = "node")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)

public class Node implements Serializable,Cloneable {
    @Transient
    private static Logger log= Logger.getLogger(Node.class);
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="tbl-gen")
    @TableGenerator(name="tbl-gen",
            pkColumnName="entity_table_name", allocationSize=150,
            table="generators")
    private Long id;
    //    @Transient
//    private TypeNode typeNode;//????
    @Column(name = "id_usercreator")
    private Long id_userCreator;
    @Column(name = "id_usercorrector")
    private Long id_userCorrector;
    @Column(name = "datecreate")
    private Date dateCreate;
    @Column(name = "datecorrect")
    private Date dateCorrect;
    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval=true)
//    @Cascade( org.hibernate.annotations.CascadeType.MERGE )
    @JoinColumn(name="id_node", referencedColumnName="id",unique = true, nullable = false)
    private List<NodeProperty> nodeProperties=new ArrayList<NodeProperty>();

    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
//    @Cascade( org.hibernate.annotations.CascadeType.MERGE )
    @JoinColumn(name="id_node", referencedColumnName="id",unique = true, nullable = false)
    private List<NodeState> nodeStates=new ArrayList<NodeState>();

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval=true)
//    @Cascade( org.hibernate.annotations.CascadeType.MERGE )
    @JoinColumn(name="id_node", referencedColumnName="id",unique = true, nullable = false)
    private List<Rule> rules=new ArrayList<Rule>();

    public Node(){}

    public Node(Node nodechanger) {
        try {
            if(getId()==null){
                id_userCreator=nodechanger.getId();
                dateCreate=new Date();
            }
            id_userCorrector=nodechanger.getId();
        } catch (Exception e) {
            log.info("Создание узла без указания создающего ");
            dateCreate=new Date();
        }
        dateCorrect=new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public TypeNode getTypeNode() {
//        return typeNode;
//    }
//
//    public void setTypeNode(TypeNode typeNode) {
//        this.typeNode = typeNode;
//    }


    public Long getId_userCreator() {
        return id_userCreator;
    }

    public void setId_userCreator(Long id_userCreator) {
        this.id_userCreator = id_userCreator;
    }

    public Long getId_userCorrector() {
        return id_userCorrector;
    }

    public void setId_userCorrector(Long id_userCorrector) {
        this.id_userCorrector = id_userCorrector;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateCorrect() {
        return dateCorrect;
    }

    public void setDateCorrect(Date dateCorrect) {
        this.dateCorrect = dateCorrect;
    }

    public List<NodeProperty> getNodeProperties() {
        return nodeProperties;
    }

    public void setNodeProperties(List<NodeProperty> nodeProperties) {
        this.nodeProperties = nodeProperties;
    }

    public List<NodeState> getNodeStates() {
        return nodeStates;
    }

    public void setNodeStates(List<NodeState> nodeStates) {
        this.nodeStates = nodeStates;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Transient
    public List<NodeProperty> getPropertysValue(Node node, String key) {
        List<NodeProperty> lnp=new ArrayList<NodeProperty>();
        if(node.getNodeProperties().size()!=0){
            for(NodeProperty np:node.getNodeProperties()){
                if(np.getKeyt().equals(key)){
                    lnp.add(np);
                }
            }
        }
        return lnp;
    }

    @Transient
    //метод для добавления только одного свойства с одним ключем, при наличии нескольких проперти с одинаковым ключем
    //переопределит первое свойство.
    public void addOnlyOneProperty(String key,String value) {
        NodeProperty nP=null;
        List<NodeProperty> lnp= getPropertysValue(this,key);
        if(lnp!=null && lnp.size()>0){
            nP=getPropertysValue(this,key).get(0);
        }
        if(nP==null){
            getNodeProperties().add(new NodeProperty(key,value));
        }else{
            nP.setValue(value);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Node node = (Node) super.clone();
        node.id_userCreator = (id_userCreator==null)?null:new Long(id_userCreator);
        node.id_userCorrector = (id_userCorrector==null)?null:new Long(id_userCorrector);
        node.dateCreate= (dateCreate==null)?null:(Date)dateCreate.clone();
        node.dateCorrect=(dateCorrect==null)?null:(Date)dateCorrect.clone();
        if(nodeProperties!=null) {
            for (NodeProperty np : nodeProperties) {
                node.nodeProperties.add((NodeProperty) np.clone());
            }
        }
        if(nodeStates!=null) {
            for (NodeState ns : nodeStates) {
                node.nodeStates.add((NodeState) ns.clone());
            }
        }
        if(rules!=null) {
            for (Rule r : rules) {
                node.rules.add((Rule) r.clone());
            }
        }

        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (dateCorrect != null ? !dateCorrect.equals(node.dateCorrect) : node.dateCorrect != null) return false;
        if (dateCreate != null ? !dateCreate.equals(node.dateCreate) : node.dateCreate != null) return false;
        if (id != null ? !id.equals(node.id) : node.id != null) return false;
        if (id_userCorrector != null ? !id_userCorrector.equals(node.id_userCorrector) : node.id_userCorrector != null)
            return false;
        if (id_userCreator != null ? !id_userCreator.equals(node.id_userCreator) : node.id_userCreator != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
