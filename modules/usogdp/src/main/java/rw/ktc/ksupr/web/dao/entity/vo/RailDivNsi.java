package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dima on 21.11.2014.
 */
@Entity
@Table(name = "RAIL_DIV", schema="NSI")
//@Inheritance(strategy= InheritanceType.JOINED)
public class RailDivNsi implements Serializable{
    @Id
    @Column (name="DIV#UN", insertable = false)
    public int un;
    @Column (name="DIV_NAME")
    public String divName;
    @Column (name="DIV_ID")
    public String divId;

    @Override
    public String toString() {
        return "RailDivNsi{" +
                "un=" + un +
                ", divName='" + divName + '\'' +
                ", divId='" + divId + '\'' +
                '}';
    }
}
