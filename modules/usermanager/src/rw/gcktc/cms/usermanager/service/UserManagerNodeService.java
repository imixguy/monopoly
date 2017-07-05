package rw.gcktc.cms.usermanager.service;


import rw.gcktc.cms.nodedata.service.HistoryNodeService;
import rw.gcktc.cms.usermanager.Role;
import rw.gcktc.cms.usermanager.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 19.11.13
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public interface UserManagerNodeService extends HistoryNodeService {

    User getUserById(Long id);

    Role getRoleById(Long id);

    List<User> getUserByExample(User user, String nameOrder, Boolean asc, String... excludeProperty);

    List<Role> getRoleByExample(Role role);

    List<Role> getRoleByExample(Role role, String nameOrder, Boolean asc, String... excludeProperty);

    List<User> getAllUser();

    User getUserByNameAndLogin(String name,String login);

    Role getUnRegisterRole();

    User createUser(User us);

    Role getRoleByName(String name);

    User getUserByName(String name);

    List<Role> getAllRoles();

    void deleteRoleFromId(Long id_role);

    Role saveRole(Role roleR, User webUser);

    User loadFullObject(User user);

    void removeUser(User user);
}