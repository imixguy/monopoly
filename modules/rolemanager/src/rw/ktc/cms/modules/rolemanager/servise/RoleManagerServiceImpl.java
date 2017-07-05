package rw.ktc.cms.modules.rolemanager.servise;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.cms.usermanager.Role;
import rw.gcktc.cms.usermanager.service.UserManagerNodeService;
import rw.gcktc.webcms.security.NodeServiceLayerImpl;
import rw.ktc.cms.modules.rolemanager.rolejson.RoleVO;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import rw.gcktc.webcms.form.NewRoleForm;

/**
 * Created by sedler on 29.09.14.
 */
@Service("RoleManagerService")
@Transactional(readOnly = true)
public class RoleManagerServiceImpl extends NodeServiceLayerImpl implements RoleManagerService {
    private static Logger logger = Logger.getLogger(RoleManagerService.class);

    private UserManagerNodeService userService;

    @Autowired
    public RoleManagerServiceImpl(UserManagerNodeService userService) {
        super(userService);
        this.userService=userService;
    }

    public UserManagerNodeService getUserService() {
        return userService;
    }

    public void setUserService(UserManagerNodeService userService) {
        this.userService = userService;
    }

    //---------------------------------
    @Override
    @Transactional
    public Role saveRole(Role role) {
        return getUserService().saveRole(role, getWebUser());
    }


    //получить все роли (объекты RoleVO)
    @Override
    public List<RoleVO> getAllRoles() {
        List<Role> roles=getUserService().getAllRoles();
        List<RoleVO> rolesVO=new ArrayList<RoleVO>();
        for(Role role: roles){
            rolesVO.add(new RoleVO((Role)getUserService().loadFullObject(role)));
        }
        return rolesVO;
    }

    //получить роль по name (full object)
    @Override
    public Role getFullRoleByName(String roleName) {
        Role role= getUserService().getRoleByName(roleName);
        return (Role)getUserService().loadFullObject(role);
    }

    //получить роль по id (full object)
    @Override
    public Role getFullRoleById(Long roleId) {
        Role role=getUserService().getRoleById(roleId);
        return (Role)getUserService().loadFullObject(role);
    }

    //изменение роли
    @Override
    @Transactional
    public Role changeRole(String roleName, String roleCredential) {
        Role changeRole=getFullRoleByName(roleName);
        List<NodeProperty> props=changeRole.getNodeProperties();
        NodeProperty removeProp=null;
        for(NodeProperty prop:props){
            if(prop.getKeyt().equals("credential") && prop.getValue().equals(roleCredential)){
                removeProp=prop;
            }
        }
        if(removeProp!=null){
            props.remove(removeProp);
        }else{
            props.add(new NodeProperty("credential", roleCredential));
        }
        return saveRole(changeRole);
    }

    //удалить роль
    @Override
    @Transactional
    public void deleteRoleFromId(Long[] rolesId) {
        for(Long roleId:rolesId){
            getUserService().deleteRoleFromId(roleId);
        }
    }

    @Override
    public boolean isRoleName(String roleName) {
        Role role=getUserService().getRoleByName(roleName);
        if(role!=null){
            return true;
        }
        return false;
    }
}
