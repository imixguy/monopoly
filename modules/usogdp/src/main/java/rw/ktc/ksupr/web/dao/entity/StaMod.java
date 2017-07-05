package rw.ktc.ksupr.web.dao.entity;

import rw.ktc.ksupr.web.dao.entity.vo.DirectionVO;
import rw.ktc.ksupr.web.dao.entity.vo.StationNsi;

import javax.persistence.*;

/**
 * Created by dima on 28.11.2014.
 */

//@OneToMany(mappedBy="email", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//List<EmailError> emailErrors = new ArrayList<>();
//
//@ManyToOne()
//@JoinColumn(name = "email_id", nullable = false)
//Email email;
//
@Entity()
@Table(schema = "WEBFACE", name = "STAMOD")
public class StaMod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name="ST#ID",nullable = false)
    private StationNsi stationNsi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StationNsi getStationNsi() {
        return stationNsi;
    }

    public void setStationNsi(StationNsi stationNsi) {
        this.stationNsi = stationNsi;
    }

    @Override
    public String toString() {
        return "StaMod{" +
                "id=" + id +
                ", stationNsi=" + stationNsi +
                '}';
    }
}
