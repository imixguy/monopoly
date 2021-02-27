package by.imix.cms.usermanager;


import by.imix.cms.nodedata.Node;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "role")
//@PrimaryKeyJoinColumn(name = "id_node", referencedColumnName = "id")

@AttributeOverrides({
        @AttributeOverride(name="id_usercreator", column=@Column(name="id_usercreator")),
        @AttributeOverride(name="id_usercorrector", column=@Column(name="id_usercorrector")),
        @AttributeOverride(name="datecreate", column=@Column(name="datecreate")),
        @AttributeOverride(name="datecorrect", column=@Column(name="datecorrect"))
})

public class Role extends Node implements Serializable {
    @Transient
    private static Logger log = Logger.getLogger(Role.class);

    private String name;

    public Role(){}

    public Role(String name) {
        this(null,name);
    }

    protected Role(Node nodechanger){
        super(nodechanger);
    }

    public Role(Node nodechanger,String name) {
        super(nodechanger);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
