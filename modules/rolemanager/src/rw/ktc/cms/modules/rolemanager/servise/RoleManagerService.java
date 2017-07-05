package rw.ktc.cms.modules.rolemanager.servise;

import rw.gcktc.cms.usermanager.Role;
import rw.ktc.cms.modules.rolemanager.rolejson.RoleVO;

import java.util.List;

/**
 * Created by sedler on 29.09.14.
 */
public interface RoleManagerService {

    Role saveRole(Role role);

    List<RoleVO> getAllRoles();

    Role getFullRoleByName(String roleName);

    Role getFullRoleById(Long roleId);

    Role changeRole(String roleName, String roleCredential);

    void deleteRoleFromId(Long[] rolesId);

    boolean isRoleName(String roleName);
}
