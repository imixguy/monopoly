package by.nulay.changer.films;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by miha on 08.10.2014.
 */
@Entity
@Table(name = "films")
public class Film implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;//название
    @Column(name = "alternativeName", length = 100)
    private String alternativeName;//название
    @Column(name = "description", length = 1000)
    private String description;//описание
    @Column(name = "dateBegin", length = 10)
    private String dateBegin;//дата выхода
    @Column(name = "dateEnd", length = 10)
    private String dateEnd;//дата завершения или закрыт
    private boolean serial=false;//пометка сериал или полный фильм
    private int part;//часть
    @Column(name = "nameSeria", length = 100)
    private String nameSeria;//название серии
    private Long parentId;//ССылка на главную серию или начало выхода сериала
    private Integer countSezon;
    private String ganr;//жанр фильма
    @Column(name = "chanal", length = 50)
    private String chanal;//канал
    @Column(name = "timeEfir", length = 50)
    private String timeEfir;//время выхода в эфир
    @Column(name = "countTime", length = 50)
    private String countTime;//длина серии
    @Column(name = "raiting", length = 10)
    private String raiting;//оценка
    @Column(name = "writevk")
    private boolean writevk = false;//пометка о записи в контакте
    @Column(name = "status", length = 20)
    private String status;
    @Column(name = "transliterate", length = 100)
    private String transliterate;
    @Column(name = "nameFile", length = 25)
    private String nameFile;

    @Transient
    private List<List<Seria>> listSezon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //Название
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSerial() {
        return serial;
    }

    public void setSerial(boolean serial) {
        this.serial = serial;
    }

    public boolean isWritevk() {
        return writevk;
    }

    public void setWritevk(boolean writevk) {
        this.writevk = writevk;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getNameSeria() {
        return nameSeria;
    }

    public void setNameSeria(String nameSeria) {
        this.nameSeria = nameSeria;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getCountSezon() {
        return countSezon;
    }

    public void setCountSezon(Integer countSezon) {
        this.countSezon = countSezon;
    }

    public String getGanr() {
        return ganr;
    }

    public void setGanr(String ganr) {
        this.ganr = ganr;
    }

    public String getChanal() {
        return chanal;
    }

    public void setChanal(String chanal) {
        this.chanal = chanal;
    }

    public String getTimeEfir() {
        return timeEfir;
    }

    public void setTimeEfir(String timeEfir) {
        this.timeEfir = timeEfir;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<List<Seria>> getListSezon() {
        return listSezon;
    }

    public void setListSezon(List<List<Seria>> listSezon) {
        this.listSezon = listSezon;
    }

    public String getTransliterate() {
        return transliterate;
    }

    public void setTransliterate(String transliterate) {
        this.transliterate = transliterate;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}