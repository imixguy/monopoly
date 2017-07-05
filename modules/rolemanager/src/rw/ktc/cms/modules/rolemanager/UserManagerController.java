package rw.ktc.cms.modules.rolemanager;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rw.gcktc.cms.usermanager.User;
import rw.gcktc.webcms.form.NewUserForm;
import rw.ktc.cms.modules.rolemanager.form.NewUserFullForm;
import rw.ktc.cms.modules.rolemanager.servise.UserAdminService;
import rw.ktc.cms.modules.rolemanager.userjson.UserVO;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sedler on 18.11.14.
 */

@Controller("UserManagerController")
@SessionAttributes
public class UserManagerController {
    private static Logger logger = Logger.getLogger(UserManagerController.class);

    //сервис управления пользователями
    UserAdminService userAdminService;
    //----------конструктор, геттеры и сеттеры-------------------------

    @Autowired
    public UserManagerController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    public UserAdminService getUserAdminService() {
        return userAdminService;
    }

    public void setUserAdminService(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    //---------------------------------------------------------------
    //--- назначение ролей пользователю  -----
    @RequestMapping(value = "admin/usermanager/user-role.html")
    public String userRole(){
        return "usermanager.userRole";
    }

    //--- список пользователей  -----
    @RequestMapping(value = "admin/usermanager/listusers.html")
    public String listUsers(){
        return "usermanager.listUsers";
    }

    //-----создание/редактирование пользователя----------
    @RequestMapping(value = "admin/usermanager/createnewuser.html")
    public String createNewUser(Model model){
        model.addAttribute("newUserForm", new NewUserFullForm());
        return "usermanager.createNewUser";
    }

    //-----создание/редактирование (POST запрос)
    @RequestMapping(value = "admin/usermanager/createnewuser.html", method = RequestMethod.POST)
    public String saveNewUser(@Valid NewUserFullForm newUserForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "usermanager.createNewUser";
        }
        User user=null;
        if(newUserForm.getId()==-1){
            user=newUserForm.createUserFromThis(getUserAdminService().getRoleByName("registeruser"));
        }else{
            user=newUserForm.editUser(getUserAdminService().getUserById(newUserForm.getId()));
        }
        User us=getUserAdminService().createUser(user);
        return "redirect:/admin/rolemanager/settings.html";
    }

    //--- удалить пользователя  -----
    @RequestMapping(value = "admin/usermanager/deleteuser.html")
    public String deleteUser(){
        return "usermanager.deleteUser";
    }
//------------------------------------------------------------------
    //-------список пользователей (JSON)----------
    @RequestMapping(value = "admin/usermanager/listusers", method= RequestMethod.GET, produces="application/json")
    public @ResponseBody List<UserVO> editRoles() {
        List<UserVO> users=getUserAdminService().getAllUser();
        return users;
    }

    //------- изменение активности юзера (JSON)----------
    @RequestMapping(value = "admin/usermanager/changeuser", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody Long changeActivityUser(@RequestParam("userId") Long userId) {
        User user=getUserAdminService().getUserById(userId);
        if(user.getActive()){
            user.setActive(false);
        }else{
            user.setActive(true);
        }
        return getUserAdminService().createUser(user).getId();
    }

    //-------- удаление пользователя (JSON)-------
    @RequestMapping(value = "admin/usermanager/deleteuser", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody int deleteUser(@RequestBody  Long[] usersId) {
        for(Long id: usersId){
            User us=getUserAdminService().getUserById(id);
            getUserAdminService().deleteUser(us);
        }
        return 0;
    }

    //-------- изменение ролей у пользователя (JSON)-------
    @RequestMapping(value = "admin/usermanager/user-role", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody Long changeUserRole(@RequestParam("userIdRoleName")  String userIdRoleName) {
        String[] s=userIdRoleName.split(";");
        Long userId=Long.parseLong(s[1]);
        String roleName=s[0];
        User us=getUserAdminService().getUserById(userId);
        return getUserAdminService().changeUserRole(us, roleName).getId();
    }

    //-------- проверка имени пользователя (JSON)-------
    @RequestMapping(value = "admin/usermanager/checkuserlogin", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody boolean checkUserLogin(@RequestParam("userLogin") String userLogin) {
        try{
            User user=getUserAdminService().getUserByName(userLogin);
            return false;
        }catch (Exception e){
            return true;
        }
    }
}