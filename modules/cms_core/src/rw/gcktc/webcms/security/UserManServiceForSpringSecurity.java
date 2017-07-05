package rw.gcktc.webcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.cms.usermanager.Role;
import rw.gcktc.cms.usermanager.User;
import rw.gcktc.cms.usermanager.service.UserManagerNodeService;
import rw.gcktc.image.FileUploaderIface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 16.12.13
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */

@Service("userManServiceForSpringSecurity")
@Transactional(readOnly = true)
public class UserManServiceForSpringSecurity implements UserDetailsManager {
    @Autowired
    private UserManagerNodeService userManagerService;

    private FileUploaderIface fileUploader;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userManagerService.getUserByName(username);
        return new UserWeb(username, user.getPassword(), true,  user.getActive(), true, true, getGrantedAuthority(user), user);
    }

    public List<SimpleGrantedAuthority> getGrantedAuthority(User user){
        List<NodeProperty> lGr=new ArrayList<NodeProperty>();
        for (Role r:user.getRoles()){
            lGr.addAll(userManagerService.getPropertysValue(r, "credential"));
        }

        List<SimpleGrantedAuthority> gal=new ArrayList<SimpleGrantedAuthority>();
        for(NodeProperty np:lGr){
            gal.add(new SimpleGrantedAuthority(np.getValue()));
        }

        return gal;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteUser(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void changePassword(String s, String s2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean userExists(String name) {
        User user = userManagerService.getUserByName(name);
        return (user!=null);
    }

    public FileUploaderIface getFileUploader() {
        return fileUploader;
    }

    @Autowired
    public void setFileUploader(@Qualifier("imageUploadForAvatar") FileUploaderIface fileUploader) {
        this.fileUploader = fileUploader;
    }

    public UserManagerNodeService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService(UserManagerNodeService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
