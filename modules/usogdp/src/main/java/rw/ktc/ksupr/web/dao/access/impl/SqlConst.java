package rw.ktc.ksupr.web.dao.access.impl;

/**
 * Created with IntelliJ IDEA.
 * User: dima
 * Date: 27.12.13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public final class SqlConst {

    public final static String GET_DIRECTION_BY_ESR = "with distr as (select distr from test.station_distr where sta_no = :esr and st_distr_end > current timestamp)\n" +
            ", exc as (select distinct sta_no_1 from test.station_distr where sta_no =  :esr)\n" +
            "select distinct q1.sta_no_1 styk\n" +
            ", case when q3.seq < q4.seq then q2.name_c1 else q2.name_c end name1\n" +
            ", case when q3.seq < q4.seq then q2.name_na1 else q2.name_na end name2\n" +
            ", case when q3.seq < q4.seq then '1' else '0' end parity\n" +
            "from test.station_distr q1\n" +
            "left join test.distr q2 on q2.\"DISTR#ID\" = q1.distr\n" +
            "left join test.station_distr q3 on q3.distr = q1.distr and q3.sta_no = :esr\n" +
            "left join test.station_distr q4 on q4.distr = q1.distr and q4.sta_no = q1.sta_no_1\n" +
            "where q1.distr in (select distr from distr) and q1.sta_no_1 not in (select sta_no_1 from exc) and q1.st_pr in (1,3,5)";

    public final static String GET_LIST_TRAIN_ON_STATION_BY_DIRECTIONS = "with p1 as (\n" +
            "select distinct tr_id, ip, np, sfp, snp\n" +
            "from ksupr.op_ipo_p\n" +
            "where id_pr in (:real,:forecast) and kso = :esr\n" +
            ")\n" +
            ", p2 as (\n" +
            "select distinct q1.tr_id, q1.kso, q2.sta_no_1 ksoJ, q1.datop, q1.klvp, q1.dlvs, q1.qbr, q1.id_pr\n" +
            ", case when q1.idtrainhistvag is not null then q1.idtrainhistvag else q1.datop end idtrainhistvag\n" +
            "from ksupr.op_ipo_p q1\n" +
            "left join test.station_distr q2 on q2.sta_no = q1.kso\n" +
            "where q1.id_pr in (:real,:forecast)\n" +
            "and q1.kop in ('P0001','P0003','P0011','P0013','P0023','P0031','P0033','P0043','P0054','P0101','P0201','P1003','P1020')\n" +
            ")\n" +
            ", p3 as (\n" +
            "select distinct q1.tr_id, q1.kso, q2.sta_no_1 ksoJ, q1.datop, q1.klvp, q1.dlvs, q1.qbr, q1.id_pr\n" +
            ", case when q1.idtrainhistvag is not null then q1.idtrainhistvag else q1.datop end idtrainhistvag\n" +
            "from ksupr.op_ipo_p q1\n" +
            "left join test.station_distr q2 on q2.sta_no = q1.kso\n" +
            "where q1.id_pr in (:real,:forecast)\n" +
            "and q1.kop in ('P0002','P0003','P0013','P0022','P0023','P0033','P0042','P0043','P0102','P1003','P1010')\n" +
            ")\n" +
            ", p4 as (\n" +
            "select distinct tr_id, kso, datop, id_pr\n" +
            "from ksupr.op_ipo_p\n" +
            "where id_pr in (:real,:forecast) and kop = 'P0009'\n" +
            ")\n" +
            "select distinct \n" +
            "        q1.np, q1.ip\n" +
            "    , q2.datop prib, q2.id_pr idPrPrib, q4.datop got, q4.id_pr idPrGot, q3.datop otpr, q3.id_pr idPrOtpr\n" +
            "    , q2.klvp klvpPrib, q3.klvp klvpOtpr, q2.dlvs dlvsPrib, q3.dlvs dlvsOtpr, q2.qbr qbrPrib, q3.qbr qbrOtpr\n" +
            "    , q1.tr_id trId, case when q3.idTrainHistVag is not null then q3.idTrainHistVag else q2.idTrainHistVag end idTrainHistVag\n" +
            "    , q1.sfp, q1.snp\n" +
            "    , q9.sta_no_1 iz, q11.sta_no_1 na\n" +
            "from p1 q1\n" +
            "left join p2 q2 on q2.tr_id = q1.tr_id and q2.kso = :esr\n" +
            "left join p3 q3 on q3.tr_id = q1.tr_id and q3.kso = :esr\n" +
            "left join p4 q4 on q4.tr_id = q1.tr_id and q4.kso = :esr\n" +
            "left join test.station_distr q5 on q5.sta_no = :esr\n" +
            "left join p3 q6 on q6.tr_id = q1.tr_id and q6.datop < q2.datop and q6.ksoJ <> q5.sta_no_1\n" +
            "left join p2 q7 on q7.tr_id = q1.tr_id and q7.datop > q3.datop and q7.ksoJ <> q5.sta_no_1\n" +
            "left join test.station_distr q8 on q8.distr = q5.distr and q8.sta_no = q6.kso\n" +
            "left join test.station_distr q9 on q9.distr = q8.distr and q9.st_pr in (1,3,5) and q9.sta_no_1 <> q5.sta_no_1\n" +
            "left join test.station_distr q10 on q10.distr = q5.distr and q10.sta_no = q7.kso\n" +
            "left join test.station_distr q11 on q11.distr = q10.distr and q11.st_pr in (1,3,5) and q11.sta_no_1 <> q5.sta_no_1\n" +
            "left join p3 q12 on q12.tr_id = q1.tr_id and q12.datop < q2.datop and q12.ksoJ = q5.sta_no_1\n" +
            "left join p2 q13 on q13.tr_id = q1.tr_id and q13.datop > q3.datop and q13.ksoJ = q5.sta_no_1\n" +
            "where (q9.sta_no_1 is not null or q11.sta_no_1 is not null) or (q12.ksoJ is not null or q13.ksoJ is not null)\n" +
            "order by q2.datop";

    public final static String TRAIN_CONSIST = "select case when q1.pnv is not null then q1.pnv else cast(999 as integer) end as pnv, q1.nv as nv, q1.vsgrvg as vsgrvg, q1.ksobt as ksobt, q1.stnz as stnz, q2.sta_name as nameSt, q1.kdgr10 as kdgr10, q3.cargo_fullname as cargoName, q1.kdpl as kdpl, q1.oov1 as oov1, q1.oov2 as oov2, q1.oov3 as oov3, q1.prim as prim, q1.srokd as srokd, q1.vg_disl as vgDisl, q5.ip as ip, q4.kop as kop,q8.rw_op_name as kopDescr, case when q6.op_id_113 is not null then q6.op_id_113 else q8.RW_OP_ID end as rw_op_id, q4.kso as kso, q7.sta_name as ksoStName, q4.datop as datop\n" +
            "from ksupr.op_vgo_p q1\n" +
            "left join nsi.sta q2 on q2.sta_no = q1.stnz and q2.\"ST#END\" > current timestamp\n" +
            "left join nsi.cargo q3 on q3.cargo = q1.kdgr10 and \"CAR#END\" > current timestamp\n" +
            "left join KSUPR.OP_VGO_P q4 on q4.nv = q1.nv and q4.id_pr = :real\n" +
            "left join ksupr.op_ipo_p q5 on q5.tr_id = q4.tr_id and q5.id_pr = :real\n" +
            "left join nsiview.rw_oper_pr q6 on q6.rw_oper_no = q4.kop and q6.\"RW_OP_PR#END\" > current timestamp\n" +
            "left join nsi.sta q7 on q7.sta_no = q4.kso and q7.\"ST#END\" > current timestamp\n" +
            "left join nsi.rw_oper q8 on q8.rw_oper_no = q4.kop and q8.\"RW_OP#END\" > current timestamp\n" +
            "where q1.id_pr in (:real,:forecast) and q1.tr_id = :trId and q1.datop = :idTrainHistVag\n" +
            "and (q1.vg_disl = '1' or q1.vg_disl = '0' and q1.kso = :esr) \n" +
            "order by q1.pnv";

    public final static String FORECAST_LAST = "SELECT f.id as id, f.ID_PARENT as idParent ,f.datebegin as startDate,f.COMPLETEDATE as endDate,f.REAL as real,  f.completeLoading as complete FROM KSUPR.PrognozReal f where real = 0 and COMPLETELOADING = 1 order by startDate DESC fetch first 1 row only";

    public final static String TRAIN_SCHEDULE = "select kso, bv.sta_name as staName, kop, datop, id_pr, vc.rw_op_name kopDescr, fd.op_id_113 kopMnemo, codethread thread\n" +
            "from ksupr.op_ipo_p lp\n" +
            "  left join nsi.sta bv on bv.sta_no = lp.kso and \"ST#END\" > current timestamp\n" +
            "  left join nsi.rw_oper vc on vc.rw_oper_no = lp.kop and \"RW_OP#END\" > current timestamp\n" +
            "  left join nsiview.rw_oper_pr fd on fd.rw_oper_no = lp.kop and fd.\"RW_OP_PR#END\" > current timestamp\n" +
            "where id_pr in (:idReal,:idForecast) and tr_id = :trId\n" +
            "union\n" +
            "select kso, bv.sta_name as staName, kop, datop, 0 as id_pr, vc.rw_op_name kopDescr, fd.op_id_113 kopMnemo, '' thread\n" +
            "from ksupr.op_ipo_view lp\n" +
            "  left join nsi.sta bv on bv.sta_no = lp.kso and \"ST#END\" > current timestamp\n" +
            "  left join nsi.rw_oper vc on vc.rw_oper_no = lp.kop and \"RW_OP#END\" > current timestamp\n" +
            "  left join nsiview.rw_oper_pr fd on fd.rw_oper_no = lp.kop and fd.\"RW_OP_PR#END\" > current timestamp\n" +
            "where tr_id = :trId and (kop in ('B0096','P0001','P0002','P0003','P0009','P0011','P0013','P0031','P0033','P0034','P0035','P0042','P0043','P0044','P0054','P0070','P0071','P0072','P0101','P0102','P1003','P1010','P1030') or kop = 'P0005' and kso = sfp)\n" +
            "      and datop < (select datop from ksupr.op_ipo_p where id_pr = :idReal and tr_id = :trId)\n" +
            "order by datop";

    public static final String TEST_FIND_TRAIN = "SELECT * FROM KSUPR.OP_IPO_P q1 where q1.ID_PR = (SELECT id FROM KSUPR.PrognozReal f where real = 0 order by f.DATEBEGIN desc fetch first 1 row only) \n" +
            "and q1.kso = :esr";

    public static final String STATION_DISTRICT_FIND_BY_ID = "select distinct distr, sta_no_1 as esr, q2.sta_name as name, seq, st_pr from test.station_distr q1 join nsi.sta q2 on q2.sta_no = sta_no_1 where distr in (:distr) order by seq";

    public static final String OPERATION_BY_FORECAST = "select distinct q1.tr_id trId, q2.sta_no_1 esr, q1.datop,q1.kop kop, q1.ip ip, q1.np np,q1.IDTRAINHISTVAG idtrainhistvag\n" +
            "from ksupr.op_ipo_p q1\n" +
            "join test.station_distr q2 on q2.sta_no = q1.kso\n" +
            "where q1.id_pr in (:id,:idParent)\n" +
            "and q1.kop in ('P0001','P0002','P0003','P0011','P0013','P0022','P0023','P0101','P0102','P0201','P1003','P1010','P1020','P0042','P0043')\n" +
            "order by datop";

        public static final String TRAIN_PROCESSING_BY_FORECAST_AND_ESR = "select distinct q2.ip ip, q1.brigade brigade, q1.begin begin, q1.end end, q1.norm norm\n" +
                "from temp.train_processing q1\n" +
                "join ksupr.op_ipo_p q2 on q2.id_pr = :forecastId and q2.tr_id = q1.tr_id and q2.kso = :esr\n" +
                "where q1.id_pr = :forecastId and q1.esr = :esr";

}
