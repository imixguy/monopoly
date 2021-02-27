package by.imix.cms.nodedata.state;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 01.11.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "state")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="classname",discriminatorType = DiscriminatorType.STRING, length=30)
public abstract class StateDefault implements State, Serializable,Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sdisc")
    private String simpleDescription;  //описание состояния

    public StateDefault() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public void setSimpleDescription(String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        StateDefault stD=(StateDefault) super.clone();
        stD.simpleDescription=simpleDescription;
        return stD;
    }
}
