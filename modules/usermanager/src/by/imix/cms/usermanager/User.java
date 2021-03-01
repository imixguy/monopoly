package by.imix.cms.usermanager;

import by.imix.cms.nodedata.HistoryNode;
import by.imix.cms.nodedata.HistoryNodeImpl;
import by.imix.cms.nodedata.Node;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
@AttributeOverrides({
        @AttributeOverride(name="id_usercreator", column=@Column(name="id_usercreator")),
        @AttributeOverride(name="id_usercorrector", column=@Column(name="id_usercorrector")),
        @AttributeOverride(name="datecreate", column=@Column(name="datecreate")),
        @AttributeOverride(name="datecorrect", column=@Column(name="datecorrect")),
        @AttributeOverride(name="id_hystPremParent", column=@Column(name="id_hystPremParent")),
        @AttributeOverride(name="id_hystParent", column=@Column(name="id_hystParent")),
        @AttributeOverride(name="hystory", column=@Column(name="hystory"))
})

//@DiscriminatorColumn(name="User",discriminatorType = DiscriminatorType.STRING)
public class User extends HistoryNodeImpl implements Serializable {
    private String name;
    private String password;

    //    @Column(name = "active",columnDefinition = "SMALLINT default 0 not null")
    private Boolean active=false;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Role.class)
    @JoinTable(name = "user_role")
    private Set<Role> roles=new HashSet<Role>();

    public User(){}

    public User(String name,String password) {
        super(null);
        this.name=name;
        this.password=password;
    }

    public User(Node nodechanger, HistoryNode historyNode, String name, String password) {
        super(nodechanger,historyNode);
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(password, user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
