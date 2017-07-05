package rw.ktc.ksupr.web.dao.entity.vo;

/**
 * Created with IntelliJ IDEA.
 * User: dima
 * Date: 29.11.13
 * Time: 11:42
 */
public class DirectionVO {
    public String styk; // стыковочная станция
    public String name1; //
    public String name2; //
    public String parity; // 1 - нечетный , 0 - четный

    @Override
    public String toString() {
        return "DirectionVO{" +
                "styk='" + styk + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", parity='" + parity + '\'' +
                '}';
    }
}
