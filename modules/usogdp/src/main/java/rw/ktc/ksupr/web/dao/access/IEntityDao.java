package rw.ktc.ksupr.web.dao.access;

import org.springframework.beans.factory.annotation.Autowired;
import rw.ktc.ksupr.web.dao.repository.IForecastRepository;
import rw.ktc.ksupr.web.dao.repository.IMenuRepository;
import rw.ktc.ksupr.web.dao.repository.IStaModRepository;
import rw.ktc.ksupr.web.dao.repository.IStationRepository;

/**
 * Created by dima on 02.12.2014.
 */
public class IEntityDao {
    @Autowired
    IMenuRepository menuRepository;
    @Autowired
    IForecastRepository forecastRepository;
    @Autowired
    IStaModRepository staModRepository;
    @Autowired
    IStationRepository stationRepository;
}
