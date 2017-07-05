package by.nulay.changer.vk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by miha on 17.11.2015.
 */
@Entity
@Table(name = "newfilm")
public class FilmTake implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "film", length = 10000)
    private String film;//текст
    @Column(name = "name", length = 255)
    private String name;//текст
    @Column(name = "img", length = 255)
    private String img;//текст
    @Column(name = "discription", length = 10000)
    private String discription;//текст
    private Date dateCreate;//дата создания
    @Column(name = "writea")
    private boolean writea=false;//пометка о записаном анекдоте
    @Column(name = "writeok")
    private boolean writeok=false;//пометка о записаном анекдоте в ok
    @Column(name = "sight")
    private String sight;//сайт с которой взят фильм


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
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

    public String getSight() {
        return sight;
    }

    public void setSight(String sight) {
        this.sight = sight;
    }

    public boolean isWriteok() {
        return writeok;
    }

    public void setWriteok(boolean writeok) {
        this.writeok = writeok;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
