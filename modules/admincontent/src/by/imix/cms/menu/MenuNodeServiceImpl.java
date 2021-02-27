package by.imix.cms.menu;

import by.imix.cms.nodedata.HistoryNode;
import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.NodeProperty;
import by.imix.webcms.security.UserWeb;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import by.imix.cms.nodedata.service.NodeService;
import by.imix.cms.usermanager.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 10.12.13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class MenuNodeServiceImpl implements MenuNodeService {
    private static Logger log= Logger.getLogger(MenuNodeServiceImpl.class);


    NodeService nodeService;

    public MenuNodeServiceImpl(NodeService nodeService){
        this.nodeService = nodeService;
    }

    @Override
    public List<Menu> getAllMenu(){
        List<Node> lmndh= nodeService.getAllNodeFromType("menusight");
        List<Menu> lm=new ArrayList<Menu>();
        if(lmndh.size()>0){
            for(Node ndh:lmndh){
                Menu m=new Menu();
                m.setId(ndh.getId());
                try {
                    m.setTitle(nodeService.getPropertysValue(ndh, "title").get(0).getValue());
                } catch (Exception e) {
                    log.error("not property nameMenu in HistoryNode ");
                }
                try {
                    m.setDescription(nodeService.getPropertysValue(ndh, "content").get(0).getValue());
                } catch (Exception e) {
                    //или проверку на null
                }
                lm.add(m);
            }
        }
        return lm;
    }

    @Override
    //todo дописать
    @Transactional
    public void createMenu(Node node, String name){
        Menu m=new Menu();
        HistoryNode ndh=new HistoryNode(node);
    }

    @Override
    @Transactional
    public void saveMenu(Node node, Menu menuForm) {
        HistoryNode ndh=null;
        if(menuForm.getId()!=null){
            ndh= (HistoryNode) nodeService.getNodeById(HistoryNode.class,menuForm.getId());
            if(ndh==null || nodeService.getPropertysValue(ndh, "title").size()==0){
                return;//это не меню пользователь кинуть нас хочет
            }
            nodeService.getPropertysValue(ndh, "title").get(0).setValue(menuForm.getTitle());
            nodeService.getPropertysValue(ndh, "content").get(0).setValue(menuForm.getDescription());
        }else{
            ndh=new HistoryNode(node);
            ndh.getNodeProperties().add(new NodeProperty("type","menusight"));
            ndh.getNodeProperties().add(new NodeProperty("title",menuForm.getTitle()));
            ndh.getNodeProperties().add(new NodeProperty("content",menuForm.getDescription()));
        }

        nodeService.saveNode(ndh,getWebUser());
    }


    @Override
    public Menu getMenuById(Long id) {
        Node ndh= nodeService.getById(id, false);
        Menu m=new Menu();
        if(ndh==null || nodeService.getPropertysValue(ndh, "title").size()==0){
            return m; //такого меню нету.
        }
        m.setId(ndh.getId());
        try {
            m.setTitle(nodeService.getPropertysValue(ndh, "title").get(0).getValue());
        } catch (Exception e) {
            log.error("not property nameMenu in NodeDefaultHist ");
        }
        try {
            m.setDescription(nodeService.getPropertysValue(ndh, "content").get(0).getValue());
        } catch (Exception e) {
            //или проверку на null
        }

        return m;
    }

    @Override
    @Transactional
    public boolean removeMenu(Long idMenu) {
        HistoryNode ndh=(HistoryNode) nodeService.getNodeById(HistoryNode.class, idMenu);
        if(ndh==null || nodeService.getPropertysValue(ndh, "type").size()==0 || !nodeService.getPropertysValue(ndh, "type").get(0).getValue().equals("menusight")){
            return false; //такого меню нету либо это не меню нас кидают.
        }
        nodeService.removeNode(ndh);
        return true;
    }

    //todo new Long(2)
    public User getWebUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user=auth.getPrincipal();
        if(user instanceof String){
            return (User) nodeService.getNodeById(HistoryNode.class, new Long(2));
        }
        return ((UserWeb) auth.getPrincipal()).getUserw();
    }
}
