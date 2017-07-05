package rw.ktc.cms.modules.rolemanager.rolejson;

import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.cms.usermanager.Role;
import rw.gcktc.webcms.security.CredentialBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by miha on 10.11.2014.
 */
//@JsonIgnoreProperties({"id_userCreator", "id_userCorrector", "nodeProperties"})
public class RoleVO{ //extends Role {
    private Long id;
    private String name;
    private List<String> permission;

    public RoleVO(){permission=new ArrayList<>();}

    public RoleVO(Role role) {
        this();
        this.id=role.getId();
        this.name=role.getName();
        for(NodeProperty pr: role.getNodeProperties()){
            if(pr.getKeyt().equals("credential")){
                permission.add(pr.getValue());
            }
        }
    }

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

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    //todo добавить permission
    public Role getInstance(Role role){
        role.setId(this.getId());
        role.setName(this.name);
        List<NodeProperty> props=role.getNodeProperties();
        List<NodeProperty> removeProps=new ArrayList<NodeProperty>();
        for(NodeProperty prop:props){
            if(prop.getKeyt().equals("credential")){
                removeProps.add(prop);
            }
        }
        props.removeAll(removeProps);
        for(String roleCredential:permission){
            props.add(new NodeProperty("credential", roleCredential));
        }
        return role;
    }
}
