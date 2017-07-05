package rw.ktc.ksupr.web.service;

import rw.ktc.usogdp.filters.raspr.FiltrRasprPorVagon;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.UserFace;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.usogdp.filters.raspr.VagonNazn;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by dima on 02.12.2014.
 */
@Transactional
public interface IServiseData {

    List<StaMod> findAllStaMod();

    List<StaMod> findAllStaModByUser(UserFace userFace);

    List<DirectionVO> getDirectionByESR(String esr);

    List<TrainVO> getTrainsOnStationForDirection(String esr, Integer forecastId);

    List<ForecastVO> getForecasts();

    ForecastVO getLastForecast();

    ForecastVO getForecastById(Integer id);

    List<TrainConsistVO> getTrainConsistency(String trId, Long idTrainHistVag, Integer forecastId, String esr);

    List<TrainScheduleVO> getTrainSchedule(String trId, Integer forecastId);

    List<DistrictVO> getDistricts();

    DistrictVO getDistrictById(Integer id);

    List<StaDistrict> getStationsByDistrict(Integer districtId);

    List<OperationVO> getOperationByForecast(Integer forecastId);

    List<StationNsi> getStationsNsi();
    StationNsi getStationNsi(String esr);

    String deleteForecast(Integer forecastId);

    List<TrainProcessingVO> getTrainProcessing(Integer forecastId, String esr);

    FiltrRasprPorVagon getFiltersRasp(String esr);

    List<VagonNazn> getAllVagonNazn();

    boolean saveVagonNazn(List<VagonNazn> vagonNazns);

    List<StationNsi> getStaFilterVagon();

}
