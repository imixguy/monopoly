package by.nulay.changer.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by miha on 17.11.2015.
 */
@Entity
@Table(name = "datasite")
public class DataSite implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dataSite", length = 10000)
    private String dataSite;//текст
    @Column(name = "name", length = 255)
    private String name;//текст
    @Column(name = "imgs", length = 255)
    private String imgs;//текст json ["img1","img2"]
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

    public String getDataSite() {
        return dataSite;
    }

    public void setDataSite(String dataSite) {
        this.dataSite = dataSite;
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
