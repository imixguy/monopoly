package rw.ktc.ksupr.web.dao.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dima on 21.11.2014.
 */
@Entity
@Table(schema = "WEBFACE", name = "USERF")
public class UserFace {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(schema = "WEBFACE", name = "STAMODUSER")
    private List<StaMod> staModList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Menu menu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaMod> getStaModList() {
        return staModList;
    }

    public void setStaModList(List<StaMod> staModList) {
        this.staModList = staModList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "UserFace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", staModList=" + staModList +
                ", menu=" + menu +
                '}';
    }
}
