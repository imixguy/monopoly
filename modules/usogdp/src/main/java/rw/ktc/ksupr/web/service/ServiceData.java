package rw.ktc.ksupr.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ktc.ksupr.web.dao.access.IProjectData;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.UserFace;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.ksupr.web.dao.repository.IDistrictRepository;
import rw.ktc.ksupr.web.dao.repository.IForecastRepository;
import rw.ktc.ksupr.web.dao.repository.IStaModRepository;
import rw.ktc.ksupr.web.dao.repository.IStationRepository;
import rw.ktc.usogdp.filters.raspr.FiltrRasprPorVagon;
import rw.ktc.usogdp.filters.raspr.IFiltrRasprPorVagon;
import rw.ktc.usogdp.filters.raspr.VagonNazn;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dima on 02.12.2014.
 */
@Service
public class ServiceData implements IServiseData {

    @Autowired
    private IProjectData projectData;
    @Autowired
    private IStaModRepository staModRepository;
    @Autowired
    private IStationRepository stationRepository;
    @Autowired
    private IForecastRepository forecastRepository;
    @Autowired
    private IDistrictRepository districtRepository;
    @Autowired
    private IFiltrRasprPorVagon filtrRasprPorVagon;

    @Override
    public List<StaMod> findAllStaMod() {
        List<StaMod> list = staModRepository.findAll();
        Comparator<StaMod> stc = new Comparator<StaMod>() {
            @Override
            public int compare(StaMod o1, StaMod o2) {
                return o1.getStationNsi().name.compareTo(o2.getStationNsi().name);
            }
        };
        Collections.sort(list, stc);
        return list;
    }

    @Override
    public List<StaMod> findAllStaModByUser(UserFace userFace) {
        return findAllStaMod();
    }

    @Override
    public List<DirectionVO> getDirectionByESR(String esr) {
        return projectData.getDirectionByESR(esr);
    }

    @Override
    public List<TrainVO> getTrainsOnStationForDirection(String esr, Integer forecastId) {
        ForecastVO forecastVO = getForecastById(forecastId);
        return projectData.getTrainsOnStationForDirection(esr, forecastVO);
    }

    @Override
    public List<ForecastVO> getForecasts() {
        return forecastRepository.findByRealFalseOrderByStartDateAsc();
    }

    @Override
    public ForecastVO getLastForecast() {
        return projectData.getLastForecast();
    }

    @Override
    public ForecastVO getForecastById(Integer id) {
        return forecastRepository.findOne(id);
    }

    @Override
    public List<TrainConsistVO> getTrainConsistency(String trId, Long idTrainHistVag, Integer forecastId, String esr) {
        ForecastVO forecastVO = getForecastById(forecastId);
        return projectData.getTrainConsistency(trId, idTrainHistVag, forecastVO, esr);
    }

    @Override
    public List<TrainScheduleVO> getTrainSchedule(String trId, Integer forecastId) {
        ForecastVO forecastVO = getForecastById(forecastId);
        return projectData.getTrainSchedule(trId, forecastVO);
    }

    @Override
    public List<DistrictVO> getDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public DistrictVO getDistrictById(Integer id) {
        return districtRepository.findById(id);
    }

    @Override
    public List<StaDistrict> getStationsByDistrict(Integer districtId) {
        return projectData.getStationsByDistrict(districtId);
    }

    @Override
    public List<OperationVO> getOperationByForecast(Integer forecastId) {
        ForecastVO forecastVO = getForecastById(forecastId);
        return projectData.getOperationByForecast(forecastVO);
    }

    @Override
    public List<StationNsi> getStationsNsi() {
        List<StationNsi> list = getStationRepository().findBySignBetweenOrSignIsNull((short)10,(short)11);
//        List<StationNsi> list = getStationRepository().findAll();
        return list;
    }

    @Override
    public StationNsi getStationNsi(String esr) {
        StationNsi st = getStationRepository().findByEsr(esr);
        return st;
    }

    @Override
    public String deleteForecast(Integer forecastId) {
        ForecastVO forecastVO = getForecastById(forecastId);
        if (null == forecastVO) return "forecast with id:"+forecastId+" does not exist";
        return getProjectData().removeDataForIdAndLess(forecastVO);
    }

    @Override
    public List<TrainProcessingVO> getTrainProcessing(Integer forecastId, String esr) {
        List<TrainProcessingVO> list = getProjectData().getTrainProcessing(forecastId, esr);
        return list;
    }

    @Override
    @Transactional
    public FiltrRasprPorVagon getFiltersRasp(String esr) {
        return filtrRasprPorVagon.getFiltrForStation(esr);
    }

    @Override
    @Transactional
    public List<VagonNazn> getAllVagonNazn() {
        return filtrRasprPorVagon.getAllVagonNazn();
    }

    @Override
    @Transactional
    public boolean saveVagonNazn(List<VagonNazn> vagonNazns) {
        return filtrRasprPorVagon.saveVagons( vagonNazns);
    }

    @Override
    @Transactional
    public List<StationNsi> getStaFilterVagon(){
        List<String> list = projectData.getStaFilterVagon();
        List<StationNsi> stations = new ArrayList<>();
        for( int i=0; i<list.size();i++){
            stations.add(getStationNsi(list.get(i).trim()));
        }
        return stations;
    }

    public IProjectData getProjectData() {
        return projectData;
    }

    public void setProjectData(IProjectData projectData) {
        this.projectData = projectData;
    }

    public IStaModRepository getStaModRepository() {
        return staModRepository;
    }

    public void setStaModRepository(IStaModRepository staModRepository) {
        this.staModRepository = staModRepository;
    }

    public IForecastRepository getForecastRepository() {
        return forecastRepository;
    }

    public void setForecastRepository(IForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public IStationRepository getStationRepository() {
        return stationRepository;
    }

    public void setStationRepository(IStationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public IDistrictRepository getDistrictRepository() {
        return districtRepository;
    }

    public void setDistrictRepository(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }
}
