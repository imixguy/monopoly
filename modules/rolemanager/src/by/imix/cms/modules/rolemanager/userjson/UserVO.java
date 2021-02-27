package by.imix.cms.modules.rolemanager.userjson;

import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.usermanager.Role;
import by.imix.cms.usermanager.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sedler on 19.11.14.
 */
public class UserVO {

    private Long id;

    private String dateCreate;

    private boolean active;

    private List<NodeProperty> userProperties=new ArrayList<NodeProperty>();

    private Set<Role> roles=null;

    private String login;

    private String newPassword;

    public UserVO(){

    }

    public UserVO(User user){
        this.id=user.getId();
        this.dateCreate=user.getDateCreate().toString();
        this.userProperties=user.getNodeProperties();
        this.active=user.getActive();
        this.roles=user.getRoles();
        this.login=user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<NodeProperty> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(List<NodeProperty> userProperties) {
        this.userProperties = userProperties;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
