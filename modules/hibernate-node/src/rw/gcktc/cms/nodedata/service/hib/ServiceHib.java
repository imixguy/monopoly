package rw.gcktc.cms.nodedata.service.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.04.14
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class ServiceHib {
    protected SessionFactory sessionFactory;

    public ServiceHib() {
        super();
    }

    @Autowired
    public ServiceHib(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

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
}
