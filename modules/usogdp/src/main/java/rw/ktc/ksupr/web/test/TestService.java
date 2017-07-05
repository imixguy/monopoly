package rw.ktc.ksupr.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rw.ktc.ksupr.web.dao.access.IProjectData;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.ksupr.web.dao.repository.IStaModRepository;
import rw.ktc.ksupr.web.dao.repository.IStationRepository;
import rw.ktc.ksupr.web.service.IServiseData;
import rw.ktc.usogdp.filters.raspr.FiltrRasprPorVagon;
import rw.ktc.usogdp.filters.raspr.IFiltrRasprPorVagon;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dima on 19.11.2014.
 */
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("test-context.xml");
        IServiseData serviseData = (IServiseData) ac.getBean(IServiseData.class);

        IStationRepository starepo = (IStationRepository) ac.getBean(IStationRepository.class);

//        List<StationNsi> stavag = serviseData.getStaFilterVagon();
        StationNsi baran = starepo.findByEsr("138507");

        System.out.println(baran);
        if (true) return;



        Long l = System.currentTimeMillis();
        List<DirectionVO> directionVOs = serviseData.getDirectionByESR("138507");
        Long l2 = System.currentTimeMillis();
        System.out.println("ms:"+(l2-l));

        System.out.println(directionVOs);

        ForecastVO f = serviseData.getLastForecast();

        List<TrainVO> llt = serviseData.getTrainsOnStationForDirection("138507", f.id);

        System.out.println(llt.size());






        IFiltrRasprPorVagon iFiltrRasprPorVagon = (IFiltrRasprPorVagon) ac.getBean(IFiltrRasprPorVagon.class);;
        FiltrRasprPorVagon filtrRasprPorVagon1 = iFiltrRasprPorVagon.getFiltrForStation("138507");
        System.out.println(filtrRasprPorVagon1);


        FiltrRasprPorVagon filtrRasprPorVagon = serviseData.getFiltersRasp("138507");
        System.out.println(filtrRasprPorVagon);



        List<StaDistrict> staDistricts = serviseData.getStationsByDistrict(1);
        System.out.println(staDistricts);
        ForecastVO forecastVO = serviseData.getLastForecast();
        List<TrainProcessingVO> ltp = serviseData.getTrainProcessing(forecastVO.id, "138507");
        System.out.println(ltp);

        List<OperationVO> loper = serviseData.getOperationByForecast(forecastVO.id);
        System.out.println(loper);

        IStaModRepository staModRepository = (IStaModRepository) ac.getBean(IStaModRepository.class);
//        IProjectData projectData = (IProjectData) ac.getBean(IProjectData.class);

//        System.out.println(forecastVO);
        StaMod staMod = staModRepository.findByStationNsiEsr("138507");
        TrainVO trainVO = serviseData.getTrainsOnStationForDirection(staMod.getStationNsi().esr, forecastVO.id).get(0);
        List<TrainScheduleVO> list = serviseData.getTrainSchedule(trainVO.trId, forecastVO.id);
        System.out.println(list);
//        System.out.println(serviseData.findAllStaModByUser(null));

//        -Dfile.encoding=cp1251














        logger.info("Тест Информация Шишка");
        Locale locale = new Locale("ru", "RU");
        System.out.println(locale.getDisplayLanguage());
        System.out.println("system charset:" + Charset.defaultCharset());
        logger.info("Язык Информация Шишка -  {}", "русский");
        logger.debug("Язык Информация Шишка -  {}", "русский");
        logger.error("Язык Информация Шишка -  {}", "русский");
    }
}
