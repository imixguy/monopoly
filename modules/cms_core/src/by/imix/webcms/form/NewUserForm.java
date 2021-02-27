package by.imix.webcms.form;


import com.seostella.constraints.FieldEquals;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
//import rw.gcktc.cms.Role;
//import rw.gcktc.cms.User;
import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.usermanager.Role;
import by.imix.cms.usermanager.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.11.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */

@FieldEquals( field="password", equalsTo="confirmPassword" )
public class NewUserForm{
    @Pattern(regexp="^[a-zA-Z0-9]+$",message = "{registrationUser.login.symbol}")
    @Size(min=5, max=20, message = "{registrationUser.login.size}")
    private String login;
    @Pattern(regexp="^[a-zA-Z0-9]+$",message = "{registrationUser.pass.symbol}")
    @Size(min=5, max=20, message = "{registrationUser.login.size}")
    private String password;
    @NotBlank(message = "{registrationUser.confirmPassword.error}")
    private String confirmPassword;
    @NotBlank(message = "{registrationUser.fname.empty}")
    private String fname;
    @NotBlank(message = "{registrationUser.lname.empty}")
    private String lname;
    @NotBlank(message = "{registrationUser.pname.empty}")
    private String pname;
    @NotBlank(message = "{registrationUser.workphone.empty}")
    private String workPhone;
    private String mobilePhone;
    @Email(message = "{registrationUser.email.nocorrect}")
    private String email;
    //@NotEmpty()
    private String id_dep;//принадлежность(отделение или управление)
    //@NotEmpty()
    private String id_ecUn;//предприятие
    //@NotEmpty()
    private String id_proff;//должность

    private String id_proffShort;//сокращенная должность

    public NewUserForm() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_dep() {
        return id_dep;
    }

    public void setId_dep(String id_dep) {
        this.id_dep = id_dep;
    }

    public String getId_ecUn() {
        return id_ecUn;
    }

    public void setId_ecUn(String id_ecUn) {
        this.id_ecUn = id_ecUn;
    }

    public String getId_proff() {
        return id_proff;
    }

    public void setId_proff(String id_proff) {
        this.id_proff = id_proff;
    }

    public String getId_proffShort() { return id_proffShort; }

    public void setId_proffShort(String id_proffShort) { this.id_proffShort = id_proffShort; }

    public User createUserFromThis(Role role){
        User us = new User(getLogin(),getPassword());
        us.getRoles().add(role);
        us.getNodeProperties().add(new NodeProperty("fname", getFname()));
        us.getNodeProperties().add(new NodeProperty("lname",getLname()));
        us.getNodeProperties().add(new NodeProperty("pname",getPname()));
        us.getNodeProperties().add(new NodeProperty("wphone",getWorkPhone()));
        us.getNodeProperties().add(new NodeProperty("mphone",getMobilePhone()));
        us.getNodeProperties().add(new NodeProperty("email",getEmail()));
        us.getNodeProperties().add(new NodeProperty("id_dep",getId_dep()));
        us.getNodeProperties().add(new NodeProperty("ecUn",getId_ecUn()));
        us.getNodeProperties().add(new NodeProperty("id_proff",getId_proff()));
        us.getNodeProperties().add(new NodeProperty("id_proffShort",getId_proffShort()));
        us.setActive(true);
        return us;
    }
}
