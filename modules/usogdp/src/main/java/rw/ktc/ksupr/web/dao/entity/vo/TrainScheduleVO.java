package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User dima
 * Date 05.09.13
 * Time 1411
 * To change this template use File | Settings | File Templates.
 */
public class TrainScheduleVO {
    public String kso;
    public String staName;
    public String kop;
    @Temporal(TemporalType.TIMESTAMP)
    public Date datop;
    public Integer id_pr;
    public String kopDescr;
    public String kopMnemo;
    public String thread;

    @Override
    public String toString() {
        return "TrainScheduleVO{" +
                "kso='" + kso + '\'' +
                ", staName='" + staName + '\'' +
                ", kop='" + kop + '\'' +
                ", datop=" + datop +
                ", id_pr=" + id_pr +
                ", kopDescr='" + kopDescr + '\'' +
                ", kopMnemo='" + kopMnemo + '\'' +
                ", thread='" + thread + '\'' +
                '}';
    }
}
