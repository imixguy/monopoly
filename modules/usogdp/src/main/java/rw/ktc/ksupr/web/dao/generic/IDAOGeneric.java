package rw.ktc.ksupr.web.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface GenericDAO.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 * @author dima
 * http://www.ibm.com/developerworks/ru/library/j-genericdao/
 * The Interface GenericDAO.
 */
public interface IDAOGeneric<T, ID extends Serializable> {


    ID save(T entity) throws DaoException;

    void saveOrUpdate(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(T entity) throws DaoException;

    List<T> findAll() throws DaoException;

    T findByID(ID id) throws DaoException;

}