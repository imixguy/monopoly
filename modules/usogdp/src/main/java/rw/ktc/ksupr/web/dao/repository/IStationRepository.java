package rw.ktc.ksupr.web.dao.repository;


import rw.ktc.ksupr.web.dao.entity.vo.StationNsi;

import java.util.List;

/**
 * Created by dima on 26.11.2014.
 */
public interface IStationRepository extends OnlyReadRepository<StationNsi, Integer> {

    StationNsi findByEsr(String esr);

    List<StationNsi> findBySignBetweenOrSignIsNull(Short sign1, Short sign2);

}
