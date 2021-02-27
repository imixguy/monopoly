package by.imix.cms.nodedata.rules;

import com.google.gson.Gson;
import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.NodeStateDouble;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.10.13
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 * Класс содержащий правило работы с состояниями
 */
@Entity
@Table(name = "rule")
public class Rule implements Serializable,Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String discription;
    private String fullPr;
    @Transient
    private RuleFlags ruleFlags;
    @Transient
    private Gson gson = new Gson();

    public Rule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getFullPr() {
        return fullPr;
    }

    public void setFullPr(String fullPr) {
        this.fullPr = fullPr;
    }

    public boolean addAddedState(NodeStateDouble stN){
        boolean k=getRuleFlags().getAdd().add(stN);
        parseToJSON();
        return k;
    }

    private void parseToJSON() {
//        if(addListNodeSt.size()==0 && remListNodeSt.size()==0) return;
//        StringBuilder sb=new StringBuilder();
//        sb.append("{");
//        if(addListNodeSt.size()>0){
//            sb.append("'add':[");
//            for(NodeStateDouble nsd:addListNodeSt){
//                sb.append(nsd.parseToObj()).append(",");
//            }
//            sb.delete(sb.length() - 1, sb.length()).append("]");
//            if(remListNodeSt.size()>0){
//                sb.append(", ");
//            }
//        }
//        if(remListNodeSt.size()>0){
//            sb.append("'rem':[");
//            for(NodeStateDouble nsd:remListNodeSt){
//                sb.append(nsd.parseToObj()).append(",");
//            }
//            sb.delete(sb.length() - 1, sb.length()).append("]");
//        }
//        sb.append("}");

        fullPr=gson.toJson(ruleFlags);
    }

    public boolean addRemovedState(NodeStateDouble stN){
        boolean k=getRuleFlags().getRem().add(stN);
        parseToJSON();
        return k;
    }

    public boolean delAddedState(NodeStateDouble stN){
        boolean k=getRuleFlags().getAdd().remove(stN);
        parseToJSON();
        return k;
    }

    public boolean delRemovedState(NodeStateDouble stN){
        boolean k=getRuleFlags().getRem().add(stN);
        parseToJSON();
        return k;
    }

    public RuleFlags getRuleFlags() {
        return ruleFlags;
    }

    public void setRuleFlags(RuleFlags ruleFlags) {
        this.ruleFlags = ruleFlags;
    }

    /**
     *
     * @param node - узел который запрашивает выполнение правила
     */
    public void rulePerform(Node node){
        //загружаем Правило (Rule) для конкретного узла (Node).
        //если такого правила для узла нету ничего не делаем

        //если правило есть, то по имени состояния загружаем соответствующий класс и вызываем у него метод addState либо removeState в зависимости от правила

        for(NodeStateDouble nsd:getRuleFlags().getAdd()){
            nsd.getState().addState(nsd.getNode());
        }
        for(NodeStateDouble nsd:getRuleFlags().getRem()){
            nsd.getState().removeState(nsd.getNode());
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Rule rule = (Rule) super.clone();
        rule.discription = discription;
        rule.fullPr = fullPr;
        rule.ruleFlags=(RuleFlags)ruleFlags.clone();
        return rule;
    }
}
