package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * User: dima
 * Date: 03.09.13
 * Time: 11:39
 */
@Entity
//@NamedQuery(name="ForecastVO.findFirstOnly",
//query = "SELECT f FROM ForecastVO f where f.real = 0 and f.complete = 1 order by endDate desc")
@Table(name = "PrognozReal", schema="KSUPR")
public class ForecastVO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATEBEGIN")
    public Date startDate;//дата начала прогноза или время загрузки данных
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETEDATE")
    public Date endDate;//дата начала прогноза или время загрузки данных
    public Boolean real=false;//реальные данные или прогнозные
    @Column(name = "ID_PARENT")
    public Integer idParent=null;//ссылка на данные по которым расчитывается прогноз
    @Column(name = "completeLoading")
    public Boolean complete=false;//расчет или загрузка данных завершена

    @Override
    public String toString() {
        return "ForecastVO{" +
                "id=" + id +
                ", idParent=" + idParent +
                ", startDate=" + startDate +
                '}';
    }
}
