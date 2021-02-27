package by.imix.cms.nodedata.rules;

import by.imix.cms.nodedata.NodeStateDouble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 02.05.14
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
public class RuleFlags implements Serializable,Cloneable{
    private List<NodeStateDouble> add= new ArrayList<NodeStateDouble>();
    private List<NodeStateDouble> rem= new ArrayList<NodeStateDouble>();

    public List<NodeStateDouble> getAdd() {
        return add;
    }

    public void setAdd(List<NodeStateDouble> add) {
        this.add = add;
    }

    public List<NodeStateDouble> getRem() {
        return rem;
    }

    public void setRem(List<NodeStateDouble> rem) {
        this.rem = rem;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RuleFlags rf=(RuleFlags) super.clone();

        for(NodeStateDouble nsd:add){
            rf.add.add((NodeStateDouble)nsd.clone());
        }

        for(NodeStateDouble nsd:rem){
            rf.rem.add((NodeStateDouble)nsd.clone());
        }

        return rf;
    }
}
