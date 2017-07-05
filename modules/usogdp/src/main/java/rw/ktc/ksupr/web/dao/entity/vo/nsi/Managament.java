package rw.ktc.ksupr.web.dao.entity.vo.nsi;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 21.11.2014.
 */
//@Entity
//@Table(name = "MANAGAMENT", schema="NSI")
//@Inheritance(strategy= InheritanceType.JOINED)
public class Managament implements Serializable{
    @Id
    @Column (name="MANAG#ID", insertable = false)
    public Integer id;
    @Column (name="MANAG_NO")
    public String no;
    @Column (name="MANAG_NAME")
    public String name;
    @Column (name="M_NAME_RUS")
    public String mrus;
    @Column (name="M_NAME_LAT")
    public String mlat;

    @Override
    public String toString() {
        return "Managament{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", mrus='" + mrus + '\'' +
                ", mlat='" + mlat + '\'' +
                '}';
    }
}
