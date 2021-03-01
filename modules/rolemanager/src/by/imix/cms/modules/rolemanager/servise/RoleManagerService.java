package by.imix.cms.modules.rolemanager.servise;

import by.imix.cms.entity.Role;
import by.imix.cms.modules.rolemanager.rolejson.RoleVO;

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
