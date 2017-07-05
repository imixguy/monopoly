package rw.gcktc.cms.nodedata.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 01.11.13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T extends Serializable, ID extends Serializable> {
    Class<T> getPersistentClass();

    @SuppressWarnings("unchecked")
    T getById(ID id, boolean lock);

    @SuppressWarnings("unchecked")
    List<T> getAllObject();

    @SuppressWarnings("unchecked")
    List<T> getAllObject(String sortName, Boolean asc);

    @SuppressWarnings("unchecked")
    List<T> getAllObject(String sortName, Boolean asc, Integer cursor, Integer count);

    @SuppressWarnings("unchecked")
    List<T> getByExample(T exampleInstance, String... excludeProperty);

    @SuppressWarnings("unchecked")
    List<T> getByExampleOrder(T exampleInstance, String nameOrder, Boolean asc, String... excludeProperty);

    @SuppressWarnings("unchecked")
    List<T> getByExampleCursorOrder(T exampleInstance, String nameOrder, Boolean asc, Integer cursor, Integer count, String... excludeProperty);

    @SuppressWarnings("unchecked")
    T saveOrUpdate(T entity);

    void deleteFromId(ID id);

    void delete(T entity);
}
