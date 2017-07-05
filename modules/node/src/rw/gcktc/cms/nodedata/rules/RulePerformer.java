package rw.gcktc.cms.nodedata.rules;

import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeStateDouble;
import rw.gcktc.cms.nodedata.service.NodeService;
/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 13.11.13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
//todo добавить транзакционность
public class RulePerformer {
    private NodeService nodeService;

    public RulePerformer(NodeService nodeService) {
        this.nodeService=nodeService;
    }

    /**
     *
     * @param node - узел который запрашивает выполнение правила
     * @param id_rule - id правила которое нужно выполнить
     */
    public void rulePerform(Node node, Integer id_rule){
        //загружаем Правило (Rule) для конкретного узла (Node).
        //если такого правила для узла нету ничего не делаем

        //если правило есть, то по имени состояния загружаем соответствующий класс и вызываем у него метод addState либо removeState в зависимости от правила
        Rule r=loadRule(1);
        rulePerform(node,r);
    }

    /**
     *
     * @param node - узел который запрашивает выполнение правила
     * @param rule - правило которое нужно выполнить
     */
    public void rulePerform(Node node, Rule rule){
        //загружаем Правило (Rule) для конкретного узла (Node).
        //если такого правила для узла нету ничего не делаем

        //если правило есть, то по имени состояния загружаем соответствующий класс и вызываем у него метод addState либо removeState в зависимости от правила
//        Rule r=loadRule(1);
        for(NodeStateDouble nsd:rule.getRuleFlags().getAdd()){
            nsd.getState().addState(nsd.getNode());
        }
        for(NodeStateDouble nsd:rule.getRuleFlags().getRem()){
            nsd.getState().removeState(nsd.getNode());
        }
    }

    //todo посмотреть смысл
    private Rule loadRule(Integer id) {
        Rule r=new Rule();

        NodeStateDouble nsd= null;

        nsd = new NodeStateDouble(nodeService.loadState(new Long(1)), nodeService.getNodeById(Node.class,new Long(1)));

        r.addAddedState(nsd);
        return r;
    }
}
