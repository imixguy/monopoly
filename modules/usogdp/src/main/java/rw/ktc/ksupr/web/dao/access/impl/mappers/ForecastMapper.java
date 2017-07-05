package rw.ktc.ksupr.web.dao.access.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import rw.ktc.ksupr.web.dao.entity.vo.ForecastVO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 05.12.2014.
 */
public class ForecastMapper implements RowMapper<ForecastVO> {
    @Override
    public ForecastVO mapRow(ResultSet resultSet, int i) throws SQLException {
        ForecastVO obj = new ForecastVO();
        obj.id = resultSet.getInt("id");
        obj.idParent = resultSet.getInt("idParent");
        obj.startDate = resultSet.getTimestamp("startDate");
        obj.endDate = resultSet.getTimestamp("endDate");
        obj.real = resultSet.getInt("real") == 1 ? true:false;
        obj.complete = resultSet.getInt("complete") == 1 ? true:false;
        return obj;
    }
}
