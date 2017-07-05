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
public class TrainChartVO {

    public String CODETHREAD;

    @Temporal(TemporalType.TIMESTAMP)
    public Date DATEREADY;
    @Temporal(TemporalType.TIMESTAMP)
    public Date DATOP;
    public Integer DLVS;
    public String DORFRM;
    public String DORNAZ;
    public String GIV;

    @Temporal(TemporalType.TIMESTAMP)
    public Date IDTRAINHISTVAG;

    public Integer ID_PR;
    public String IP;
    public Integer KLVP;
    public String KOP;
    public String KS_PR;
    public String KSO;
    public String MARS;
    public String NGBP;
    public String NGNP;
    public String NGVP;
    public String NGVRP;
    public String NP;
    public Integer OGS;
    public String POOV2;
    public Integer QBR;
    public String SFP;
    public String SNP;
    public String TR_ID;
    @Temporal(TemporalType.TIMESTAMP)
    public Date TR_OPER_ID;

    @Override
    public String toString() {
        return "\nTrainChartVO{" +
                "CODETHREAD='" + CODETHREAD + '\'' +
                ", DATEREADY=" + DATEREADY +
                ", DATOP=" + DATOP +
                ", DLVS=" + DLVS +
                ", DORFRM='" + DORFRM + '\'' +
                ", DORNAZ='" + DORNAZ + '\'' +
                ", GIV='" + GIV + '\'' +
                ", IDTRAINHISTVAG=" + IDTRAINHISTVAG +
                ", ID_PR=" + ID_PR +
                ", IP='" + IP + '\'' +
                ", KLVP=" + KLVP +
                ", KOP='" + KOP + '\'' +
                ", KS_PR='" + KS_PR + '\'' +
                ", KSO='" + KSO + '\'' +
                ", MARS='" + MARS + '\'' +
                ", NGBP='" + NGBP + '\'' +
                ", NGNP='" + NGNP + '\'' +
                ", NGVP='" + NGVP + '\'' +
                ", NGVRP='" + NGVRP + '\'' +
                ", NP='" + NP + '\'' +
                ", OGS=" + OGS +
                ", POOV2='" + POOV2 + '\'' +
                ", QBR=" + QBR +
                ", SFP='" + SFP + '\'' +
                ", SNP='" + SNP + '\'' +
                ", TR_ID='" + TR_ID + '\'' +
                ", TR_OPER_ID=" + TR_OPER_ID +
                '}';
    }
}
