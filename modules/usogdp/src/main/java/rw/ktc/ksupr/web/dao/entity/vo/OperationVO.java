package rw.ktc.ksupr.web.dao.entity.vo;

import java.sql.Timestamp;

/**
 * Created by dima on 15.01.2015.
 */
public class OperationVO {
    public String trId;
    public String esr;
    public Timestamp datop;
    public String kop;
    public String ip;
    public String np;
    public Timestamp idtrainhistvag;

    @Override
    public String toString() {
        return "OperationVO{" +
                "trId='" + trId + '\'' +
                ", esr='" + esr + '\'' +
                ", datop=" + datop +
                ", kop='" + kop + '\'' +
                ", ip='" + ip + '\'' +
                ", np='" + np + '\'' +
                ", idtrainhistvag=" + idtrainhistvag +
                '}';
    }
}
