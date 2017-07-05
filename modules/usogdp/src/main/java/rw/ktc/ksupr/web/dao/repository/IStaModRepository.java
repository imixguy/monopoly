package rw.ktc.ksupr.web.dao.repository;


import org.springframework.data.repository.CrudRepository;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.vo.StationNsi;

import java.util.List;

/**
 * Created by dima on 26.11.2014.
 */
public interface IStaModRepository extends CrudExtRepository<StaMod, Integer> {
    StaMod findByStationNsiEsr(String esr);
}
