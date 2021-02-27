package by.imix.cms.modules.rolemanager;

import by.imix.cms.modules.rolemanager.form.NewRoleForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import by.imix.cms.usermanager.Role;
import by.imix.webcms.security.CredentialBox;
import by.imix.cms.modules.rolemanager.rolejson.RoleVO;
import by.imix.cms.modules.rolemanager.servise.RoleManagerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sedler on 01.10.14.
 */
@Controller("roleManagerController")
//@Transactional(readOnly = true)
@SessionAttributes
public class RoleManagerController {
    private static Logger logger = Logger.getLogger(RoleManagerController.class);

    //объект содержащий сет допусков (credential)
    private CredentialBox credentialBox;

    //сервис управления ролями
    private RoleManagerService roleManagerServise;

    //----------конструктор, геттеры и сеттеры-------------------------
    @Autowired
    public RoleManagerController(CredentialBox credentialBox, RoleManagerService roleManagerServise) {
        this.credentialBox=credentialBox;
        this.roleManagerServise=roleManagerServise;
    }

    public CredentialBox getCredentialBox() {
        return credentialBox;
    }

    public void setCredentialBox(CredentialBox credentialBox) {
        this.credentialBox = credentialBox;
    }

    public RoleManagerService getRoleManagerService() {
        return roleManagerServise;
    }

    public void setRoleManagerService(RoleManagerService roleManagerServise) {
        this.roleManagerServise = roleManagerServise;
    }

    //---------------------------------------------------------------
    //--- меню настроек  -----
    @RequestMapping(value = "/admin/rolemanager/settings.html")
    public String settings(){
        return "rolemanager.settings";
    }

    //--- управление ролями  -----
    @RequestMapping(value = "/admin/rolemanager/credentialManager.html")
    public String editRoles(){
        return "rolemanager.credentialManager";
    }

    //--- создание/редактирование роли  -----
    @RequestMapping(value = "/admin/rolemanager/createRole.html")
    public String createRole(Model model){
        model.addAttribute("newRoleForm", new NewRoleForm());
        model.addAttribute("credentials", getCredentialBox().getListCredential());
        return "rolemanager.createRole";
    }

    //--- создание/редактрование роли (метод POST) -----
    @RequestMapping(value = "/admin/rolemanager/createRole", method = RequestMethod.POST)
    public String saveNewRole(@Valid NewRoleForm newRoleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "redirect:/admin/rolemanager/createRole.html";
        }
        if(newRoleForm.getId()==-1){
            getRoleManagerService().saveRole(newRoleForm.getInstance());
        }else{
            getRoleManagerService().saveRole(newRoleForm.changeRole(getRoleManagerService().getFullRoleById(newRoleForm.getId())));
        }
        return "rolemanager.settings";
    }

//    //--- редактирование роли  -----
//    @RequestMapping(value = "/admin/rolemanager/editRole.html")
//    public String editRole(){
//        return "rolemanager.editRole";
//    }

    //--- удаление ролей  -----
    @RequestMapping(value = "/admin/rolemanager/deleterole.html")
    public String deletRole(){
        return "rolemanager.deleteRole";
    }

    //----------------------------------------------------
    //--- список ролей и список допусков (JSON)----
    @RequestMapping(value = "/admin/rolemanager/getroles", method= RequestMethod.GET, produces="application/json")
    public  @ResponseBody List getRoles(){
        List<RoleVO> roles=getRoleManagerService().getAllRoles();
        Set<String> credentials=getCredentialBox().getListCredential();
        List data=new ArrayList();
        data.add(roles);
        data.add(credentials);
        return data;
    }

    //---------- управление ролью (JSON)-----------
    @RequestMapping(value = "/admin/rolemanager/editroles", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody Role editRoles(@RequestParam("roleName") String roleId) {
        String changeRole[]=roleId.split(";");
        return getRoleManagerService().changeRole(changeRole[0], changeRole[1]);
    }

    //--------удаление роли (JSON)------------------
    @RequestMapping(value = "/admin/rolemanager/deleterole", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody int deleteRole(@RequestBody Long[] rolesId) {
        getRoleManagerService().deleteRoleFromId(rolesId);
        return 0;
    }

//    //-------------редактирование роли (JSON)-----
//    @RequestMapping(value = "/admin/rolemanager/editrole", method= RequestMethod.POST, produces="application/json")
//    public @ResponseBody int editRole(@RequestBody RoleVO editRole) {
//        Role role = getRoleManagerService().getFullRoleById(editRole.getId());
//        role=editRole.getInstance(role);
//        getRoleManagerService().saveRole(role);
//        return 0;
//    }

    //-----проверка наличия роли с таким же именем (JSON)---
    @RequestMapping(value = "/admin/rolemanager/checkrole", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody boolean checkNameRole(@RequestParam("roleName") String roleName) {
        return getRoleManagerService().isRoleName(roleName);
    }
    //-------------------------------------------
}
