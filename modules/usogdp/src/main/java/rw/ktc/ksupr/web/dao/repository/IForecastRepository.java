package rw.ktc.ksupr.web.dao.repository;


import rw.ktc.ksupr.web.dao.entity.vo.ForecastVO;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by dima on 26.11.2014.
 */
public interface IForecastRepository extends Repository<ForecastVO, Integer> {

    ForecastVO findOne(Integer var1);

    boolean exists(Integer var1);

    List<ForecastVO> findByRealFalse();

    List<ForecastVO> findByRealFalseOrderByStartDateAsc();

    long count();

}
