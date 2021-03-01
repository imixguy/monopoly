package by.imix.cms.modules.rolemanager.servise;

import by.imix.cms.entity.Role;
import by.imix.cms.entity.User;
import by.imix.cms.modules.rolemanager.userjson.UserVO;


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
