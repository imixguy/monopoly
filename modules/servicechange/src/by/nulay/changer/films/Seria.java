package by.nulay.changer.films;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 19.10.14
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "serias")
public class Seria implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "alternativeName", length = 100)
    private String alternativeName;//альтернативное название
    @Column(name = "dateEntered", length = 50)
    private String dateEntered;//дата выхода;
    private Long id_film;//ссылка на сериал
    private Integer sezon;
    private Integer seria;
    private Boolean writevk;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Long getId_film() {
        return id_film;
    }

    public void setId_film(Long id_film) {
        this.id_film = id_film;
    }

    public Integer getSezon() {
        return sezon;
    }

    public void setSezon(Integer sezon) {
        this.sezon = sezon;
    }

    public Integer getSeria() {
        return seria;
    }

    public void setSeria(Integer seria) {
        this.seria = seria;
    }

    public Boolean getWritevk() {
        return writevk;
    }

    public void setWritevk(Boolean writevk) {
        this.writevk = writevk;
    }
}
