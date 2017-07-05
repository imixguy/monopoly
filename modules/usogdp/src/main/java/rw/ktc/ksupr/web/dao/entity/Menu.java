package rw.ktc.ksupr.web.dao.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 26.11.2014.
 */
@Entity
@Table(schema = "WEBFACE", name = "MENU")
public class Menu implements Serializable{
    @Id
    @GeneratedValue
    @OrderColumn
    @Column(unique = true, nullable = false)
    private Integer id;

    private String name;

//  url length ->  http://stackoverflow.com/questions/417142/what-is-the-maximum-length-of-a-url-in-different-browsers
    @Column(length = 2000)
    private String url; // relative

    @OneToMany( mappedBy = "parent",cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Menu> children;

    @ManyToOne(fetch=FetchType.LAZY)
    private Menu parent;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Menu() {
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Menu(String name, String url, Menu parent) {
        this.name = name;
        this.url = url;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
//                "   , parent=" + parent +
                ", children=" + children +
                "}\n";
    }

//    @Override
//    public int compareTo(Object o) {
//        if ( !(o instanceof Menu) ) return -1;
//        if (null == ((Menu)o).getId()) return -1;
//        if ( (getId() - ((Menu)o).getId()) > 0){
//            return 1;
//        }
//        if ( (getId() - ((Menu)o).getId()) < 0){
//            return -1;
//        }
//        return 0;
//    }
}
