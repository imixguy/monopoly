package by.nulay.changer.vk;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by miha on 24.07.2014.
 */
@Entity
@Table(name = "frandsservice")
public class Frands  implements Serializable,Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;//идентификатор пользователя от которого сделано предложение
    private String frandid;//идентификатор пользователя кому сделано
    private String servid;//идентификатор сервиса например группа imix или предложение дружбы
    private boolean ogr;//пометка о том что пользователь ограничил тех кто может добавлять его в группу

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFrandid() {
        return frandid;
    }

    public void setFrandid(String frandid) {
        this.frandid = frandid;
    }

    public String getServid() {
        return servid;
    }

    public void setServid(String servid) {
        this.servid = servid;
    }

    public boolean isOgr() {
        return ogr;
    }

    public void setOgr(boolean ogr) {
        this.ogr = ogr;
    }
}
