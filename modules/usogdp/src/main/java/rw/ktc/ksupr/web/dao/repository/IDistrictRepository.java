package rw.ktc.ksupr.web.dao.repository;

import rw.ktc.ksupr.web.dao.entity.vo.DistrictVO;

/**
 * Created by dima on 09.01.2015.
 */
public interface IDistrictRepository extends OnlyReadRepository<DistrictVO,Integer> {
    DistrictVO findById(Integer id);

}
