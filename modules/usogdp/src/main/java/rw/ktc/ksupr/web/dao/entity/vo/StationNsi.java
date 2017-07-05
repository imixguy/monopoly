package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.*;

/**
 * User: dima
 * Date: 03.09.14
 * Time: 16:06
 */
@Entity
@Table(name = "STA", schema = "NSI")
public class StationNsi {
    @Id
    @Column(name = "ST#ID", insertable = false, unique = true)
    public Integer id;
    @Column(name = "STA_NO", columnDefinition = "char", length = 6)
    public String esr;
    @Column(name = "STA_NAME")
    public String name;
    @OneToOne
    @JoinColumn(name = "DIV#UN")
    public RailDivNsi railDiv;
    @Column(name = "SIGN")
    public Short sign;
    @Override
    public String toString() {
        return "StationNsi{" +
                "id=" + id +
                ", esr='" + esr + '\'' +
                ", name='" + name + '\'' +
                ", sign=" + sign +
                ", railDiv=" + railDiv +
                '}';
    }
}
