package by.nulay.changer.vk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by miha on 24.07.2014.
 */
@Entity
@Table(name = "anekdot")
public class Anekdot   implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "anekdot", length = 10000)
    private String anekdot;//текст
    private Date dateCreate;//дата создания
    @Column(name = "writea")
    private boolean writea=false;//пометка о записаном анекдоте
    @Column(name = "writeok")
    private boolean writeok=false;//пометка о записаном анекдоте в ok
    @Column(name = "groupn")
    private String group;//группа с которой взят анекдот
    private Integer countLike;//колличество лайков
    private Integer countAdded;//количество поделившихся


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnekdot() {
        return anekdot;
    }

    public void setAnekdot(String anekdot) {
        this.anekdot = anekdot;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public boolean isWritea() {
        return writea;
    }

    public void setWritea(boolean writea) {
        this.writea = writea;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public Integer getCountAdded() {
        return countAdded;
    }

    public void setCountAdded(Integer countAdded) {
        this.countAdded = countAdded;
    }

    public boolean isWriteok() {
        return writeok;
    }

    public void setWriteok(boolean writeok) {
        this.writeok = writeok;
    }
}
