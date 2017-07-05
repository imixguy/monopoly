package rw.gcktc.cms.nodedata.service.hib;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.cms.nodedata.service.GenericDAO;
import rw.gcktc.cms.nodedata.service.NodeService;
import rw.gcktc.cms.nodedata.state.State;
import rw.gcktc.cms.nodedata.state.StateDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.04.14
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
@Service("nodehibservice")
@Transactional(readOnly = true)
public class NodeServiceHib extends GenericHibernateDAO<Node,Long> implements NodeService,GenericDAO<Node,Long> {
    private static Logger log=Logger.getLogger(NodeServiceHib.class);

    @Autowired
    public NodeServiceHib(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<NodeProperty> getPropertysValue(Node node, String key) {
        List<NodeProperty> lnp=new ArrayList<NodeProperty>();
        if(!Hibernate.isInitialized(node.getNodeProperties())){
            getSession().buildLockRequest(LockOptions.NONE).lock(node);
//            getSession().refresh(node,LockOptions.READ );
            Hibernate.initialize(node.getNodeProperties());
        }
        if(node.getNodeProperties().size()!=0){
            for(NodeProperty np:node.getNodeProperties()){
                if(np.getKeyt().equals(key)){
                    lnp.add(np);
                }
            }
        }
        return lnp;
    }

    public List<NodeProperty> getAllPropertys(Node node) {
        getSession().refresh(node);
        if(!Hibernate.isInitialized(node.getNodeProperties())){
            Hibernate.initialize(node.getNodeProperties());
        }
        return node.getNodeProperties();
    }

    @Override
    public List<Node> getAllNodeFromType(String type){
        return getAllNodeFromType(Node.class,type);
    }

    @Override
    //Метод возвращает узлы по их динамическому типу (значит что класса не существует а обеъкт может быть)
    public List<Node> getAllNodeFromType(Class clazz,String type){
        String sql="SELECT n FROM "+clazz.getName()+" n LEFT JOIN n.nodeProperties np WHERE np.keyt='type' AND np.value=:type";
        return getSession().createQuery(sql).setString("type",type).list();
    }

    public State loadState(Long idState) {
        return (State)getSession().get(StateDefault.class, idState, LockOptions.NONE);
    }

    @Override
    public List<Node> getAllNodeFromPrKey(String key, String value) {
        return getSession().createQuery("SELECT n FROM "+Node.class.getName()+" n LEFT JOIN n.nodeProperties np WHERE np.keyt=:key AND np.value=:value").setString("key",key).setString("value",value).list();
    }

    @Override
    public List<Node> getAllNodeFromPrKey(Class clazz, String key, String value) {
        return getSession().createQuery("SELECT n FROM "+clazz.getName()+" n LEFT JOIN n.nodeProperties np WHERE np.keyt=:key AND np.value=:value").setString("key",key).setString("value",value).list();
    }

    //    public List<NodeProperty> getPropertysValue(Node ndh, String key) {
//        if(ndh.getNodeProperties().size()!=0){
//            //getSession().refresh(ndh);
//            List<NodeProperty> lnp=new ArrayList<NodeProperty>();
//            for(NodeProperty np:ndh.getNodeProperties()){
//                if(np.getKeyt().equals(key)){
//                    lnp.add(np);
//                }
//            }
//            return lnp;
//        }else{
//            NodeProperty np=new NodeProperty();
//            np.setValue(key);
//            np.setId_node(ndh.getId());
//            return ndhpgh.getByExample(np);
//        }
//    }

    public List<StateDefault> getStates(Node node) {
        try {
            return getSession().createCriteria(StateDefault.class).list();
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void removeNode(Node node) throws IllegalArgumentException {
        getSession().delete(node);
    }

    @Override
    @Transactional
    //todo разобрать 2 id пользователя  new Long(2)
    public Node saveNode(Node node, Node generatingNode) {
        if(node.getId()==null){
            node.setId_userCreator((generatingNode==null || generatingNode.getId()==null)?new Long(2):generatingNode.getId());
            node.setDateCreate(new Date());
        }
        node.setId_userCorrector(generatingNode.getId());
        node.setDateCorrect(new Date());
        getSession().saveOrUpdate(node);
        return node;
    }

    @Override
    public Node getNodeById(Class clazz,Long id){
        return (Node) getSession().get(clazz, id);
    }

    @Override
    public Node getFullNodeById(Long id) {
        Node node=getById(id, false);
        Hibernate.initialize(node.getNodeProperties());
        Hibernate.initialize(node.getNodeStates());
        Hibernate.initialize(node.getRules());
        return node;
    }

    @Override
    public Node getFullNodeById(Class clazz,Long id) {
        Node node= (Node) getSession().get(clazz,id);
        Hibernate.initialize(node.getNodeProperties());
        Hibernate.initialize(node.getNodeStates());
        Hibernate.initialize(node.getRules());
        return node;
    }

    @Override
    public Node loadFullObject(Node node){
        getSession().refresh(node);
        Hibernate.initialize(node.getNodeProperties());
        Hibernate.initialize(node.getNodeStates());
        Hibernate.initialize(node.getRules());
        return node;
    }

    @Override
    public List<Node> getAllNode(Class clazz){
         return getSession().createCriteria(clazz).list();
    }
}
