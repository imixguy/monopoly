package by.imix.cms.modules.rolemanager.form;

import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.usermanager.User;
import by.imix.webcms.form.NewUserForm;

import java.util.List;

/**
 * Created by sedler on 15.12.14.
 */
public class NewUserFullForm extends NewUserForm{
    public NewUserFullForm() {
        super();
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User editUser(User user){
        user.setId(this.getId());
        user.setName(this.getLogin());
        user.setPassword(this.getPassword());
        List<NodeProperty> props=user.getNodeProperties();
        for(NodeProperty prop:props){
            switch (prop.getKeyt()){
                case "fname":
                    prop.setValue(this.getFname());
                break;

                case "lname":
                    prop.setValue(this.getLname());
                break;

                case "pname":
                    prop.setValue(this.getPname());
                break;

                case "workPhone":
                    prop.setValue(this.getWorkPhone());
                break;

                case "mobilePhone":
                    prop.setValue(this.getMobilePhone());
                break;

                case "email":
                    prop.setValue(this.getEmail());
                break;
            }
        }
        return user;
    }
}
