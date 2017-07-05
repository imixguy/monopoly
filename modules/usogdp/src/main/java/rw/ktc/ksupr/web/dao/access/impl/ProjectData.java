package rw.ktc.ksupr.web.dao.access.impl;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rw.ktc.ksupr.web.dao.access.IProjectData;
import rw.ktc.ksupr.web.dao.access.impl.mappers.DirectionVOMapper;
import rw.ktc.ksupr.web.dao.access.impl.mappers.ForecastMapper;
import rw.ktc.ksupr.web.dao.access.impl.mappers.TrainVOMapper;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.ksupr.web.dao.repository.IForecastRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by dima on 02.12.2014.
 */
@Component
public class ProjectData implements IProjectData {
    private static final Logger logger = LoggerFactory.getLogger(ProjectData.class);

    @Autowired
    private SessionFactory sessionFactory;


    private DataSource dataSource;
    @Autowired
    private IForecastRepository forecastRepository;

    private JdbcTemplate jdbcTemplate;

//    @Override
//    @Deprecated
//    @Transactional(readOnly = true)
//    public List<DirectionVO> getDirectionByESR2(String esr) {
//        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.GET_DIRECTION_BY_ESR).
//                addScalar("styk").addScalar("name1").addScalar("name2").addScalar("parity").setString("esr", esr).
//                setResultTransformer(Transformers.aliasToBean(DirectionVO.class)).setCacheMode(CacheMode.GET);
//        List<DirectionVO> list = query.list();
//        return list;
//    }

    @Override
    public List<DirectionVO> getDirectionByESR(String esr) {
        String sql = SqlConst.GET_DIRECTION_BY_ESR;
        sql = sql.replace(":esr", "'"+esr+"'");
        List<DirectionVO> directions = getJdbcTemplate().query(sql, new DirectionVOMapper());
        return directions;
    }

//    @Override
//    @Transactional(readOnly = false)
//    public List<TrainVO> getTrainsOnStationForDirection2(String esr, ForecastVO forecastVO) {
//        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.GET_LIST_TRAIN_ON_STATION_BY_DIRECTIONS);
////        .
////                addScalar("np").
////                addScalar("ip").
////                addScalar("prib").
////                addScalar("idPrPrib").
////                addScalar("got").
////                addScalar("idPrGot").
////                addScalar("otpr").
////                addScalar("idPrOtpr").
////                addScalar("klvpPrib").
////                addScalar("klvpOtpr").
////                addScalar("dlvsPrib").
////                addScalar("dlvsOtpr").
////                addScalar("qbrPrib").
////                addScalar("qbrOtpr").
////                addScalar("trId").
////                addScalar("idTrainHistVag").
////                addScalar("sfp").
////                addScalar("snp").
////                addScalar("iz").
////                addScalar("na");
////        .
////                setResultTransformer(Transformers.aliasToBean(TrainVO.class)).
////                setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).
////                setCacheMode(CacheMode.GET);
//        query.setString("esr", esr);
//        query.setInteger("forecast", forecastVO.id);
//        query.setInteger("real", forecastVO.idParent);
////        List<TrainVO> list = query.list();
//        List list = query.list();
////        int i = 0;
////        while (i < list.size()) {
////            TrainVO vo = list.get(i);
////            if (null == vo.iz && null == vo.na) { // выкинуть все строки если iz или na пустые
////                list.remove(i);
////            } else {
////                i++;
////            }
////        }
//        return list;
//    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainVO> getTrainsOnStationForDirection(String esr, ForecastVO forecastVO) {
        String sql = SqlConst.GET_LIST_TRAIN_ON_STATION_BY_DIRECTIONS;
        sql = sql.replace(":esr", "'"+esr+"'");
        sql = sql.replace(":forecast", forecastVO.id+"");
        sql = sql.replace(":real", forecastVO.idParent+"");
        List<TrainVO> rows = getJdbcTemplate().query(sql, new TrainVOMapper());
        return rows;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainConsistVO> getTrainConsistency(String trId, Long idTrainHistVag, ForecastVO forecastVO, String esr) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.TRAIN_CONSIST).
                addScalar("pnv").
                addScalar("nv").
                addScalar("vsgrvg").
                addScalar("ksobt").
                addScalar("stnz").
                addScalar("nameSt").
                addScalar("kdgr10").
                addScalar("cargoName").
                addScalar("kdpl").
                addScalar("oov1").
                addScalar("oov2").
                addScalar("oov3").
                addScalar("prim").
                addScalar("srokd").
                addScalar("vgDisl").
                addScalar("ip").
                addScalar("kop").
                addScalar("kopdescr").
                addScalar("rw_op_id").
                addScalar("kso").
                addScalar("ksostname").
                addScalar("datop").
                setResultTransformer(Transformers.aliasToBean(TrainConsistVO.class)).
                setCacheMode(CacheMode.GET);
        query.setInteger("forecast", forecastVO.id);
        query.setInteger("real", forecastVO.idParent);
        query.setString("trId", trId);
        query.setTimestamp("idTrainHistVag", new Date(idTrainHistVag));
        query.setString("esr", esr);
        List<TrainConsistVO> trainConsist = query.list();
        return trainConsist;
    }

    @Override
    public ForecastVO getLastForecast() {
        ForecastVO forecastVO = null;
        JdbcTemplate template = new JdbcTemplate(dataSource);
        forecastVO = template.queryForObject(SqlConst.FORECAST_LAST, new ForecastMapper());
        return forecastVO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainScheduleVO> getTrainSchedule(String trId, ForecastVO forecastVO) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.TRAIN_SCHEDULE).
                addScalar("kso").
                addScalar("staName").
                addScalar("kop").
                addScalar("datop").
                addScalar("id_pr").
                addScalar("kopDescr").
                addScalar("kopMnemo").
                addScalar("thread").
                setResultTransformer(Transformers.aliasToBean(TrainScheduleVO.class));
        query.setString("trId", trId);
        query.setInteger("idForecast", forecastVO.id);
        query.setInteger("idReal", forecastVO.idParent);
        List list = query.list();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getStaFilterVagon() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select distinct q1.ESR esr from KSUPR.FILTRRASPRPORVAGON q1");
//        .setResultTransformer(Transformers.aliasToBean(String.class));
        List list = query.list();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaDistrict> getStationsByDistrict(Integer districtId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.STATION_DISTRICT_FIND_BY_ID).
                addScalar("distr").
                addScalar("esr").
                addScalar("name").
                addScalar("seq").
                addScalar("st_pr").
                setResultTransformer(Transformers.aliasToBean(StaDistrict.class));
        query.setInteger("distr", districtId);
        List list = query.list();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationVO> getOperationByForecast(ForecastVO forecast) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.OPERATION_BY_FORECAST).
                addScalar("trId").
                addScalar("esr").
                addScalar("datop").
                addScalar("kop").
                addScalar("ip").
                addScalar("np").
                addScalar("idtrainhistvag").
                setResultTransformer(Transformers.aliasToBean(OperationVO.class));
        query.setInteger("id", forecast.id);
        query.setInteger("idParent", forecast.idParent);
        List list = query.list();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainProcessingVO> getTrainProcessing(Integer forecastId, String esr) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(SqlConst.TRAIN_PROCESSING_BY_FORECAST_AND_ESR).
                addScalar("ip").
                addScalar("brigade").
                addScalar("begin").
                addScalar("end").
                addScalar("norm").
                setResultTransformer(Transformers.aliasToBean(TrainProcessingVO.class));
        query.setInteger("forecastId", forecastId);
        query.setString("esr", esr);
        List list = query.list();
        return list;
    }

    private Object lockRemoveData = new Object();

    @Override
    public String removeDataForIdAndLess(ForecastVO forecastVO) {
        synchronized (lockRemoveData){
            logger.info("команда на удаление данных ниже прогноза:"+ forecastVO);
            List<ForecastVO> list  = forecastRepository.findByRealFalse();
            Collections.sort(list, new Comparator<ForecastVO>() {
                @Override
                public int compare(ForecastVO o1, ForecastVO o2) {
                    if (o1.id > o2.id) return 1;
                    if (o1.id < o2.id) return -1;
                    return 0;
                }
            });
            try (Connection connection = dataSource.getConnection();
                 Statement deleteRows=connection.createStatement();){
                connection.setAutoCommit(false);
                String sql ;
                int count = 0;
                for (ForecastVO firstForecast :list ){
                    if (firstForecast.id <= forecastVO.id ){ // удаление по одному
                        sql = String.format("delete from ksupr.op_ipo_p where id_pr in (select id from ksupr.prognozreal where id <= %d or id_parent <= %d)", firstForecast.id,firstForecast.id);
                        deleteRows.execute(sql);
                        sql = String.format("delete from ksupr.op_vgo_p where id_pr in (select id from ksupr.prognozreal where id <= %d or id_parent <= %d)", firstForecast.id,firstForecast.id);
                        deleteRows.execute(sql);
                        sql = String.format("delete from ksupr.op_lko_p where id_pr in (select id from ksupr.prognozreal where id <= %d or id_parent <= %d)", firstForecast.id,firstForecast.id);
                        deleteRows.execute(sql);
                        sql = String.format("delete from temp.train_processing where id_pr in (select id from ksupr.prognozreal where id <= %d or id_parent <= %d)", firstForecast.id,firstForecast.id);
                        deleteRows.execute(sql);
                        sql = String.format("delete from ksupr.prognozreal where id <= %d or id_parent <= %d", firstForecast.id,firstForecast.id);
                        deleteRows.execute(sql);
                        connection.commit();
                        count++;
                        logger.info(String.format("удалено всего %d, текущий удаленный прогноз:%s", count, firstForecast.toString()));
                    }
                }
                connection.setAutoCommit(true);
                logger.info(" удаление завершено!");
                return " remove complete for id:"+forecastVO.id;
            } catch (SQLException e){
                logger.info(" ошибка при удалении прогноза:%s", e);
                return " ошибка при удалении прогноза:%s"+ e.getMessage();
            }
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public IForecastRepository getForecastRepository() {
        return forecastRepository;
    }

    public void setForecastRepository(IForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
