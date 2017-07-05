package rw.ktc.ksupr.web.dao.entity.vo;

import javax.persistence.*;

/**
 * Created by dima on 09.01.2015.
 */
@Entity
@Table(name = "DISTR", schema="TEST")
public class DistrictVO {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "DISTR#UN")
    private Integer un;
    @Column(name = "DISTR#ID")
    private Integer id;
    @Column(name = "STA_NO")
    private String sta_no;
    @Column(name = "STA_NO_1")
    private String sta_no1;
    @Column(name = "NAME")
    private String name;

    public Integer getUn() {
        return un;
    }

    public void setUn(Integer un) {
        this.un = un;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSta_no() {
        return sta_no;
    }

    public void setSta_no(String sta_no) {
        this.sta_no = sta_no;
    }

    public String getSta_no1() {
        return sta_no1;
    }

    public void setSta_no1(String sta_no1) {
        this.sta_no1 = sta_no1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DistrictVO{" +
                "un=" + un +
                ", id=" + id +
                ", sta_no='" + sta_no + '\'' +
                ", sta_no1='" + sta_no1 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
