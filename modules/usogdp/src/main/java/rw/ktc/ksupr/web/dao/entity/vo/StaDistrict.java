package rw.ktc.ksupr.web.dao.entity.vo;

/**
 * Created by dima on 14.01.2015.
 */
public class StaDistrict {
    public Integer distr;
    public String esr;
    public String name;
    public Integer seq;
    public Integer st_pr;

    @Override
    public String toString() {
        return "\nStaDistrict{" +
                "distr=" + distr +
                ", esr='" + esr + '\'' +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", st_pr=" + st_pr +
                '}';
    }
}
