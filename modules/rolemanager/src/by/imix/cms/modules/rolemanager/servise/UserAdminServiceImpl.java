package by.imix.cms.modules.rolemanager.servise;

import by.imix.cms.modules.rolemanager.userjson.UserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.nodedata.NodeState;
import by.imix.cms.nodedata.rules.Rule;
import by.imix.cms.usermanager.Role;
import by.imix.cms.usermanager.User;
import by.imix.cms.usermanager.service.UserManagerNodeService;
import by.imix.webcms.security.NodeServiceLayerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sedler on 19.11.14.
 */
@Service("UserAdminService")
@Transactional(readOnly = true)
public class UserAdminServiceImpl extends NodeServiceLayerImpl implements UserAdminService {
    private static Logger logger = Logger.getLogger(RoleManagerService.class);

    private UserManagerNodeService userService;
    //--------конструктор, геттеры и сеттеры----
    @Autowired
    public UserAdminServiceImpl(UserManagerNodeService userService) {
        super(userService);
        this.userService=userService;
    }

    public UserManagerNodeService getUserService() {
        return userService;
    }

    public void setUserService(UserManagerNodeService userService) {
        this.userService = userService;
    }
    //-----------------------------------------------

    //Return All users (List UserVO)
    @Override
    public List<UserVO> getAllUser() {
        List<User> users=getUserService().getAllUser();
        List<UserVO> userVO= new ArrayList<UserVO>();
        for(User user:users){
            getUserService().loadFullObject(user);
            if(!user.getHystory() && !user.getRemoved()){
                userVO.add(new UserVO(user));
            }
        }
        return userVO;
    }

    //Return full object (User)
    @Override
    public User getUserById(Long userId) {
        return getUserService().loadFullObject(getUserService().getUserById(userId));
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user= properties(user);
        return getUserService().createUser(user);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return getUserService().getRoleByName(roleName);
    }

    @Override
    @Transactional
    public void deleteUser(User us) {
            us= properties(us);
            getUserService().removeUser(us);
    }

    @Override
    @Transactional
    public User changeUserRole(User user, String roleName) {
        boolean roleKey=false;
        Role removeRole=null;
        for(Role role:user.getRoles()){
            if(role.getName().equals(roleName)){
                roleKey=true;
                removeRole=role;
            }
        }
        if(roleKey){
            user.getRoles().remove(removeRole);
        }else{
            Role newRole=getUserService().getRoleByName(roleName);
            getUserService().loadFullObject(newRole);
            user.getRoles().add(newRole);
        }
        return createUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return getUserService().getUserByName(name);
    }

    //todo сделать копию объекта!
    private User properties(User user){
        List<NodeProperty> oldProp=user.getNodeProperties();
        List<NodeProperty> newNodeProp=new ArrayList<NodeProperty>();
        for(NodeProperty prop:oldProp){
            newNodeProp.add(new NodeProperty(prop.getKeyt(),prop.getValue()));
        }

        List<NodeState> oldState=user.getNodeStates();
        List<NodeState> newNodeState=new ArrayList<NodeState>();
        for(NodeState state:oldState){
            newNodeState.add(new NodeState());
        }

        List<Rule> oldRule=user.getRules();
        List<Rule> newNodeRule=new ArrayList<Rule>();
        for(Rule rule:oldRule){
            newNodeRule.add(new Rule());
        }

        user.setNodeProperties(newNodeProp);
        user.setNodeStates(newNodeState);
        user.setRules(newNodeRule);
        return user;
    }
}
