package rw.ktc.ksupr.web.dao.access;

import org.springframework.transaction.annotation.Transactional;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.vo.*;

import java.util.List;

/**
 * Created by dima on 02.12.2014.
 */
public interface IProjectData {

    List<DirectionVO> getDirectionByESR(String esr);

    List<TrainVO> getTrainsOnStationForDirection(String esr, ForecastVO forecastVO);

    List<TrainConsistVO> getTrainConsistency(String trId, Long idTrainHistVag,ForecastVO forecastVO, String esr);

    ForecastVO getLastForecast();

    List<TrainScheduleVO> getTrainSchedule(String trId, ForecastVO forecastVO);

    List<String> getStaFilterVagon();

    List<StaDistrict> getStationsByDistrict(Integer districtId);

    List<OperationVO> getOperationByForecast(ForecastVO forecast);

    List<TrainProcessingVO> getTrainProcessing(Integer forecastId, String esr);

    String removeDataForIdAndLess(ForecastVO forecastVO);
}