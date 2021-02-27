package by.imix.cms.modules.rolemanager.form;

import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.usermanager.Role;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sedler on 18.11.14.
 */
public class NewRoleForm {
    private Long id;

    @Size(min=5, max=20, message = "неверно введено имя роли")
    private String name;

    private List<String> permissions=new ArrayList<String>();

    public NewRoleForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getInstance(){
        Role newRole=new Role();
        newRole.setName(this.name);
        if(permissions==null){
            return newRole;
        }
        for(String permission:permissions){
            newRole.getNodeProperties().add(new NodeProperty("credential", permission));
        }
        return newRole;
    }

    public Role changeRole(Role role){
        role.setName(this.getName());
        List<NodeProperty> props=role.getNodeProperties();
        List<NodeProperty> removeProps=new ArrayList<NodeProperty>();
        for(NodeProperty prop:props){
            if(prop.getKeyt().equals("credential")){
                removeProps.add(prop);
            }
        }
        props.removeAll(removeProps);
        for(String cred:this.getPermissions()){
            props.add(new NodeProperty("credential", cred));
        }
        return role;
    }
}
