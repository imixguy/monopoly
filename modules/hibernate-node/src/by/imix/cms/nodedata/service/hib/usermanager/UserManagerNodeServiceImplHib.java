package by.imix.cms.nodedata.service.hib.usermanager;

import by.imix.cms.nodedata.Node;
import by.imix.cms.usermanager.Role;
import by.imix.cms.usermanager.User;
import by.imix.cms.usermanager.service.UserManagerNodeService;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import by.imix.cms.nodedata.service.hib.HistNodeServiceHib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 30.10.13
 * Time: 9:54
 * To change this template use File | Settings | File Templates.
 */
@Service("userControlService")
@Transactional(readOnly = true)
public class UserManagerNodeServiceImplHib extends HistNodeServiceHib implements UserManagerNodeService {
    private static Logger log= Logger.getLogger(UserManagerNodeServiceImplHib.class);

    @Autowired
    public UserManagerNodeServiceImplHib(SessionFactory sessionFactory){
        super(sessionFactory);
    }


    @Override
    public List<User> getAllUser(){
        return castListNodeToListUser(getAllNode(User.class));
    }

    @Override
    public User getUserByNameAndLogin(String login,String password){
        Query q=getSession().createQuery("FROM "+User.class.getName()+" WHERE name=:name AND password=:password").setString("name",login).setString("password",password);
        return (User) q.uniqueResult();
    }

    @Override
    public User getUserById(Long id){
        return (User) getNodeById(User.class,id);
    }

    @Override
    public Role getRoleById(Long id){
        return (Role) getNodeById(Role.class, id);
    }

    @Override
    public List<User> getUserByExample(User user, String nameOrder, Boolean asc, String... excludeProperty){
        return castListNodeToListUser(getByExampleOrder(user, nameOrder, asc, excludeProperty));
    }

    //метод преобразует список нодов в список пользователей
    private List<User> castListNodeToListUser(List<Node> listN){
        List<User> lu=new ArrayList<User>();
        for(Node n:listN){
            lu.add((User) n);
        }
        return lu;
    }

    //метод преобразует список нодов в список ролей
    private List<Role> castListNodeToListRole(List<Node> listN){
        List<Role> lr=new ArrayList<Role>();
        for(Node n:listN){
            lr.add((Role) n);
        }
        return lr;
    }

    @Override
    public List<Role> getRoleByExample(Role role) {
        return getRoleByExample(role,null,null);
    }

    @Override
    public List<Role> getRoleByExample(Role role, String nameOrder, Boolean asc, String... excludeProperty){
        return castListNodeToListRole(getByExampleOrder(role, nameOrder, asc, excludeProperty));
    }

    private Role noRegisterRole;
    @Override
    public Role getUnRegisterRole() {
        if(noRegisterRole==null){
            noRegisterRole=getRoleByName("unregisteruser");
            if(noRegisterRole==null){
                Logger log = Logger.getLogger(Role.class);
                log.error("Невозможно получить неавторизационную роль");
            }
        }
        return noRegisterRole;
    }

    @Override
    @Transactional
    //todo new Long(2) - не корректно
    public User createUser(User us) {
        return (User) saveNode(us,getNodeById(User.class,new Long(2)));
    }

    @Override
    public Role getRoleByName(String name) {
        return noRegisterRole=(Role) getSession().createQuery("FROM "+Role.class.getName()+" WHERE name=:name").setString("name", name).uniqueResult();
    }

    @Override
    public User getUserByName(String name) {
        User us=(User) getSession().createQuery("FROM "+User.class.getName()+" u WHERE u.name=:name AND u.hystory=false AND u.removed=false").setString("name", name).uniqueResult();
        Hibernate.initialize(us.getRoles());
        Hibernate.initialize(us.getNodeProperties());
        return us;
    }

    //метод возвращает все роли
    @Override
    public List<Role> getAllRoles() {
        return castListNodeToListRole(getAllNode(Role.class));
    }

    @Override
    @Transactional
    public void deleteRoleFromId(Long id_role) {
        try {
            getSession().createSQLQuery("DELETE  FROM user_role WHERE roles_id=:id_role").setLong("id_role",id_role).executeUpdate();
            deleteFromId(id_role);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    //todo new Long(2)
    public void removeUser(User user) {
        removeNode(user, getNodeById(User.class,new Long(2)));
    }

    @Override
    @Transactional
    public Role saveRole(Role roleR, User generatingNode) {
        if(roleR.getId()==null){
            roleR.setId_userCreator((generatingNode==null || generatingNode.getId()==null)?new Long(2):generatingNode.getId());
            roleR.setDateCreate(new Date());
        }
        roleR.setId_userCorrector(generatingNode.getId());
        roleR.setDateCorrect(new Date());
//        for(NodeProperty np:roleR.getNodeProperties()){
//            ghdRole.getSession().saveOrUpdate(np);
//        }

        return (Role)saveOrUpdate(roleR);
    }

    @Override
    public User loadFullObject(User user) {
        super.loadFullObject(user);
        Hibernate.initialize(user.getRoles());
        for(Role role:user.getRoles()){
            loadFullObject(role);
        }
        return user;
    }
}
