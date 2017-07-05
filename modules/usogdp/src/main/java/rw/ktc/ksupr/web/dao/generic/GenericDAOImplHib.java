package rw.ktc.ksupr.web.dao.generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Dmitry 4ernookiy - 4ernookiy@gmail.com on 08.05.14.
 * http://www.ibm.com/developerworks/ru/library/j-genericdao/
 */
@Transactional
public class GenericDAOImplHib<T, ID extends Serializable> implements IDAOGeneric<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    private Class<T> klass;

    public GenericDAOImplHib() {
//        this.klass = klass;
        this.klass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public ID save(T entity) {
        ID id = (ID) getSession().save(entity);
        return id;
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void update(T entity) throws DaoException {
        getSession().update(entity);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return getSession().createQuery("from " + klass.getName()).list();
    }

    @Override
    public T findByID(ID id) throws DaoException {
        return (T) getSession().get(klass, id);
    }

}
