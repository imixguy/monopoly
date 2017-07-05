package rw.ktc.ksupr.web.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dima on 28.11.2014.
 */
@NoRepositoryBean
public interface CrudExtRepository<T,ID extends Serializable> extends CrudRepository<T, ID> {
    List<T> findAll();
}
