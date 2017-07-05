package rw.ktc.cms.modules.rolemanager.servise;

import rw.gcktc.cms.usermanager.Role;
import rw.gcktc.cms.usermanager.User;
import rw.ktc.cms.modules.rolemanager.userjson.UserVO;

import java.util.List;

/**
 * Created by sedler on 19.11.14.
 */
public interface UserAdminService {

    List<UserVO> getAllUser();

    User createUser(User user);

    Role getRoleByName(String roleName);

    void deleteUser(User user);

    User changeUserRole(User user, String roleName);

    User getUserById(Long userId);

    User getUserByName(String name);
}
