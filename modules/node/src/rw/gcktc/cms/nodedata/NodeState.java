package rw.gcktc.cms.nodedata;

import rw.gcktc.cms.nodedata.state.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.10.13
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 * Класс состояния узла
 */
@Entity
@Table(name = "node_state")
public class NodeState implements Serializable,Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //Состояние узла
    @Column(name = "stateandnode",length=500)
    private String stateAndNode;
    @Transient
    private Set<NodeStateDouble> listStates=new HashSet<NodeStateDouble>();

    public NodeState() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateAndNode() {
        return stateAndNode;
    }

    public void setStateAndNode(String stateAndNode) {
        this.stateAndNode = stateAndNode;
    }

    public boolean addState(State state, Node node){
        boolean d=listStates.add(new NodeStateDouble(state,node));
        if(d){
            parseListToObj(listStates);
        }
        return d;
    }

    public boolean removeState(State state, Node node){
        NodeStateDouble nsd=new NodeStateDouble(state,node);
        boolean d=false;
        for(NodeStateDouble nsdl:listStates){
            if(nsdl.equals(nsd)){
                d=listStates.remove(nsdl);
            }
        }
        if(d){
            parseListToObj(listStates);
        }
        return d;
    }

    private void parseListToObj(Set<NodeStateDouble> listStates) {
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(NodeStateDouble nsd:listStates){
            sb.append(nsd.parseToObj()).append(",");
        }
        stateAndNode=sb.substring(0,sb.length()-1)+"]";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        NodeState nodeS = (NodeState) super.clone();
        nodeS.stateAndNode=stateAndNode;
        for(NodeStateDouble nsd:listStates){
            nodeS.listStates.add((NodeStateDouble)nsd.clone());
        }


        return nodeS;
    }
}
