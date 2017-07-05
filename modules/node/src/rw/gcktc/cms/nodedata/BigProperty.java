package rw.gcktc.cms.nodedata;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 22.05.14
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 *
 * Класс предназначен для сохранения больших свойств значений NodeProperty создан для экономии места в таблице NodeProperty
 */
@Entity
@Table(name = "big_property")
public class BigProperty implements Cloneable,Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "value", length = Integer.MAX_VALUE) //для db2 clob or mysql text
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BigProperty bp = (BigProperty) super.clone();
        bp.value = value;
        return bp;
    }
}
