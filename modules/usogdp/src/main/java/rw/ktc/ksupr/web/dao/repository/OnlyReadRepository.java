package rw.ktc.ksupr.web.dao.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dima on 28.11.2014.
 */
@NoRepositoryBean
public interface OnlyReadRepository<T,ID extends Serializable> extends Repository<T, ID> {

    T findOne(ID var1);

    boolean exists(ID var1);

    List<T> findAll();

    long count();
}
