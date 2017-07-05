package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User dima
 * Date 05.09.13
 * Time 1411
 * To change this template use File | Settings | File Templates.
 */
public class TrainVO {
    public String np; // номер поездва
    public String ip; // ип поезда
    @Temporal(TemporalType.TIMESTAMP)
    public Date prib;
    public Integer idPrPrib;
    @Temporal(TemporalType.TIMESTAMP)
    public Date got;
    public Integer idPrGot;
    @Temporal(TemporalType.TIMESTAMP)
    public Date otpr;
    public Integer idPrOtpr;

    public Integer klvpPrib;
    public Integer klvpOtpr;
    public Integer dlvsPrib;
    public Integer dlvsOtpr;
    public Integer qbrPrib;
    public Integer qbrOtpr;

    public String trId;
    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp idTrainHistVag;
    public String sfp;
    public String snp;
    public String iz;
    public String na;

    @Override
    public String toString() {
        return "\nTrainVO{" +
                "np='" + np + '\'' +
                ", ip='" + ip + '\'' +
                ", trId='" + trId + '\'' +
                ", idTrainHistVag=" + idTrainHistVag +
                '}';
    }
}
