package rw.ktc.ksupr.web.dao.access.impl.mappers;

import org.springframework.jdbc.core.RowMapper;
import rw.ktc.ksupr.web.dao.entity.vo.DirectionVO;
import rw.ktc.ksupr.web.dao.entity.vo.TrainVO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 05.12.2014.
 */
public class DirectionVOMapper implements RowMapper<DirectionVO> {
    @Override
    public DirectionVO mapRow(ResultSet resultSet, int i) throws SQLException {
        DirectionVO obj = new DirectionVO();
        obj.styk = resultSet.getString("styk");
        obj.name1 = resultSet.getString("name1");
        obj.name2 = resultSet.getString("name2");
        obj.parity = resultSet.getString("parity");
        return obj;
    }

}
