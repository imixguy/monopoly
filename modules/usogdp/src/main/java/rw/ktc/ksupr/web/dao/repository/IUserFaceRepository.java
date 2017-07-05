package rw.ktc.ksupr.web.dao.repository;


import org.springframework.data.repository.CrudRepository;
import rw.ktc.ksupr.web.dao.entity.UserFace;

/**
 * Created by dima on 26.11.2014.
 */
public interface IUserFaceRepository extends CrudExtRepository<UserFace, Integer> {
    UserFace findByName(String name);

}
