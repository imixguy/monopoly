package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by dima on 04.12.2014.
 */
public class TrainConsistVO {
    public int pnv;
    public String nv;
    public int vsgrvg;
    public String ksobt;
    public String stnz;
    public String nameSt;
    public String kdgr10;
    public String cargoName;
    public String kdpl;
    public String oov1;
    public String oov2;
    public String oov3;
    public String prim;
    public String srokd;
    public char vgDisl;//-
    public String ip;
    public String kop;//-
    public String kopdescr;
    public String rw_op_id;
    public String kso;
    public String ksostname;
    public Date datop;

    @Override
    public String toString() {
        return "TrainConsistVO{" +
                "pnv=" + pnv +
                ", nv='" + nv + '\'' +
                ", vsgrvg=" + vsgrvg +
                ", ksobt='" + ksobt + '\'' +
                ", stnz='" + stnz + '\'' +
                ", nameSt='" + nameSt + '\'' +
                ", kdgr10='" + kdgr10 + '\'' +
                ", cargoName='" + cargoName + '\'' +
                ", kdpl='" + kdpl + '\'' +
                ", oov1='" + oov1 + '\'' +
                ", oov2='" + oov2 + '\'' +
                ", oov3='" + oov3 + '\'' +
                ", prim='" + prim + '\'' +
                ", srokd='" + srokd + '\'' +
                ", vgDisl=" + vgDisl +
                ", ip='" + ip + '\'' +
                ", kop='" + kop + '\'' +
                ", kopdescr='" + kopdescr + '\'' +
                ", rw_op_id='" + rw_op_id + '\'' +
                ", kso='" + kso + '\'' +
                ", ksostname='" + ksostname + '\'' +
                ", datop=" + datop +
                '}';
    }
}
