package rw.ktc.ksupr.web.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rw.ktc.ksupr.web.dao.access.IProjectData;
import rw.ktc.ksupr.web.dao.entity.*;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.ksupr.web.dao.repository.*;

import java.util.*;

/**
 * Created by dima on 21.11.2014.
 */
public class TestHib {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test-context.xml");
//        testStationRepo(ac);
//        testStaModRepo(ac);
//        testMenu(ac);
//        testForecast(ac);
//        testDirection(ac);
//        createUserFace(ac);

        testUserFace(ac);
//        testFindTrain(ac);
//        testVisit(ac);
//         testDistrict(ac);
//        testProjectDataIface(ac);



//        http://www.concretepage.com/hibernate/example-inheritancetype_joined-hibernate
//        http://www.java2s.com/Code/Java/Hibernate/SpringDaoDemo.htm

    }
    private static void testProjectDataIface(ApplicationContext ac) {
        IProjectData projectData = (IProjectData) ac.getBean(IProjectData.class);
        List<String> list = projectData.getStaFilterVagon();
        System.out.println(list);
    }
    private static void testDistrict(ApplicationContext ac) {
        IDistrictRepository repository = (IDistrictRepository) ac.getBean(IDistrictRepository.class);
        List<DistrictVO> list = repository.findAll();
        System.out.println(list);
    }

    private static void testVisit(ApplicationContext ac) {
        IVisitInfoRepository repository = (IVisitInfoRepository) ac.getBean(IVisitInfoRepository.class);
        VisitInfo visitInfo = new VisitInfo();
        visitInfo.setIp("10.4.253.153");
        visitInfo = repository.save(visitInfo);
        System.out.println(visitInfo);
    }
    private static void createUserFace(ApplicationContext ac) {
        IUserFaceRepository userFaceRepository = (IUserFaceRepository) ac.getBean(IUserFaceRepository.class);
        IStaModRepository staModRepository = (IStaModRepository) ac.getBean(IStaModRepository.class);
        IMenuRepository menuRepository = (IMenuRepository) ac.getBean(IMenuRepository.class);
//        UserFace user = new UserFace();
        UserFace user = userFaceRepository.findOne(1);
        user.setName("Гость");
        System.out.println(user);
//
        user = userFaceRepository.save(user);
        List<StaMod> staMods= staModRepository.findAll();
        user.setStaModList(staMods);
        Menu menu = menuRepository.save(new Menu("home", "/"));
        user.setMenu(menu);

        user = userFaceRepository.save(user);
        System.out.println(user);

    }

    private static void testUserFace(ApplicationContext ac) {
        IUserFaceRepository userFaceRepository = (IUserFaceRepository) ac.getBean(IUserFaceRepository.class);
        UserFace user = userFaceRepository.findByName("Гость");
        System.out.println(user);
    }

    private static void testFindTrain(ApplicationContext ac) {
//        IForecastRepository forecastRepository = (IForecastRepository) ac.getBean(IForecastRepository.class);
        IStaModRepository staModRepository = (IStaModRepository) ac.getBean(IStaModRepository.class);
        IProjectData projectRepository = (IProjectData) ac.getBean(IProjectData.class);

        String esr = "138507";
        ForecastVO forecastVO =  projectRepository.getLastForecast();
        StaMod staMod = staModRepository.findByStationNsiEsr(esr);

        List<TrainVO> list = projectRepository.getTrainsOnStationForDirection(staMod.getStationNsi().esr, forecastVO);

        TrainVO train = list.get(0);
        System.out.println(train);
        List<TrainConsistVO> tc = projectRepository.getTrainConsistency(train.trId, train.idTrainHistVag.getTime(), forecastVO, staMod.getStationNsi().esr);



//        System.out.println(tc);
    }


    private static void testForecast(ApplicationContext ac) {
        IForecastRepository repository = (IForecastRepository) ac.getBean(IForecastRepository.class);
        List<ForecastVO> ll = repository.findByRealFalseOrderByStartDateAsc();
        System.out.println(ll);
        ForecastVO forecastVO ;
        forecastVO = repository.findOne(372790);
//        forecastVO = repository.findFirst();
//        System.out.println(repository.findFirst());
        System.out.println(forecastVO);
        System.out.println();
}
    private static StaMod getStaModRepo(ApplicationContext ac) {
        IStaModRepository repository = (IStaModRepository) ac.getBean(IStaModRepository.class);
        return  repository.findByStationNsiEsr("138507");
    }
    private static void testDirection(ApplicationContext ac) {
        StaMod staMod = getStaModRepo(ac);
        String esr = staMod.getStationNsi().esr;

        IProjectData dao = (IProjectData) ac.getBean(IProjectData.class);
        System.out.println(dao.getDirectionByESR(esr));

    }

    private static void testStaModRepo(ApplicationContext ac) {
        IStaModRepository repository = (IStaModRepository) ac.getBean(IStaModRepository.class);
        IStationRepository repositoryS = (IStationRepository) ac.getBean(IStationRepository.class);
        StationNsi s = repositoryS.findOne(1028581);
        StaMod sm = new
                StaMod();
        sm.setStationNsi(s);
//        repository.save(sm);

        System.out.println(repository.count());
        List<StaMod> list = repository.findAll();
        System.out.println(list);
//        System.out.println(repository.findOne(1028581));
    }

    private static void testStationRepo(ApplicationContext ac) {
        IStationRepository repository = (IStationRepository) ac.getBean(IStationRepository.class);
//        List<StationNsi> list = repository.findBySignBetweenOrSignIsNull( (short)10,(short)11);
        List<StationNsi> list = repository.findAll();
//        List<StationNsi> l2 = repository.
        System.out.println(list.size());
        System.out.println(repository.count());
        System.out.println(repository.findOne(1028581));
    }



    private static void testMenu(ApplicationContext ac) {
        IMenuRepository menuRepository = (IMenuRepository) ac.getBean(IMenuRepository.class);
        System.out.println(menuRepository.findOne(1));

        Menu menu = menuRepository.findOne(1);
        List<Menu> childList = new ArrayList<>();
        childList.add(new Menu("name1", "/api1", menu));
        childList.add(new Menu("name2", "/districts1", menu));
        childList.add(new Menu("name3", "/api2", menu));
        menu.setChildren(childList);

        menuRepository.save(menu);

        System.out.println(menuRepository.findAll());
    }

}
