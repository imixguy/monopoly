package rw.gcktc.cms.nodedata.service.hib.usermanager;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.cms.nodedata.service.GenericDAO;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;
import rw.gcktc.cms.nodedata.state.State;
import rw.gcktc.cms.nodedata.state.StateDefault;
import rw.gcktc.cms.usermanager.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 29.10.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */

@Service("testService")
@Transactional(readOnly = true)
public class TestService{

    private static Logger log= Logger.getLogger(TestService.class);

    @Autowired
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory;
    }

    public Session getSession(){
        return  sessionFactory.getCurrentSession();
    }

//    @Transactional(readOnly = true)
    public Node getNodeById(Class cl,Long id){
        return (Node) getSession().get(cl, id);
    }


//    @Transactional(readOnly = true)
    public User getUserById(Long id){
//        ghdUser.setSessionFactory(sessionFactory);
        return (User) getSession().get(User.class, id);
    }


//    @Transactional(readOnly = true)
    public List<Node> getNodes(int count, int cursor, String sortName, Boolean asc){
        GenericDAO<Node,Long> ghd=new GenericHibernateDAO<Node, Long>(Node.class,sessionFactory);
        try {
            return ghd.getAllObject(sortName,asc,cursor,count);
        } catch (Exception e) {
            log.error(e);
            return null;
        }

    }

//    @Transactional(readOnly = true)
    public List<Node> getNodes(Class cl){

        return getSession().createCriteria(cl).list();
    }

//    @Transactional(readOnly = true)
    public List<Node> getNodes2(){
        try {
            Query sql= getSession().createSQLQuery("SELECT * FROM node")
                    .addScalar("dateCorrect")
                    .setResultTransformer(Transformers.aliasToBean(Node.class));
            return sql.list();
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    public void saveNode(Node us) {
        getSession().saveOrUpdate(us);
    }

    @Transactional
    public void saveNode(NodeProperty np) {
        getSession().saveOrUpdate(np);
    }

    @Transactional
    public void saveState(State state) {
        getSession().saveOrUpdate(state);
    }

//    @Transactional
    public List<StateDefault> getStates() {
        GenericDAO<StateDefault,Integer> ghd=new GenericHibernateDAO<StateDefault, Integer>(StateDefault.class,sessionFactory);
        try {
            return ghd.getAllObject();
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            return null;
        }
    }

//    @Transactional
    public State loadState(Integer idState) {
        GenericDAO<StateDefault,Integer> ghd=new GenericHibernateDAO<StateDefault, Integer>(StateDefault.class,sessionFactory);
        return ghd.getById(idState,false);
    }
}
