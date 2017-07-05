package rw.ktc.ksupr.web.dao.access.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import rw.ktc.ksupr.web.dao.entity.vo.ForecastVO;
import rw.ktc.ksupr.web.dao.entity.vo.TrainVO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 05.12.2014.
 */
public class TrainVOMapper implements RowMapper<TrainVO> {
    @Override
    public TrainVO mapRow(ResultSet resultSet, int i) throws SQLException {
        TrainVO obj = new TrainVO();
        obj.np = resultSet.getString("np");
        obj.ip = resultSet.getString("ip");
        obj.prib = resultSet.getTimestamp("prib");
        obj.idPrPrib = resultSet.getInt("idPrPrib");
        obj.got = resultSet.getTimestamp("got");
        obj.idPrGot = resultSet.getInt("idPrGot");
        obj.otpr = resultSet.getTimestamp("otpr");
        obj.idPrOtpr = resultSet.getInt("idPrOtpr");
        obj.klvpPrib = resultSet.getInt("klvpPrib");
        obj.klvpOtpr = resultSet.getInt("klvpOtpr");
        obj.dlvsPrib = resultSet.getInt("dlvsPrib");
        obj.dlvsOtpr = resultSet.getInt("dlvsOtpr");
        obj.qbrPrib = resultSet.getInt("qbrPrib");
        obj.qbrOtpr = resultSet.getInt("qbrOtpr");
        obj.trId = resultSet.getString("trId").trim();
        obj.idTrainHistVag = resultSet.getTimestamp("idTrainHistVag");
        obj.sfp = resultSet.getString("sfp");
        obj.snp = resultSet.getString("snp");
        obj.iz = resultSet.getString("iz");
        obj.na = resultSet.getString("na");
        return obj;
    }

}
