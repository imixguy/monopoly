package rw.gcktc.cms.menu;

import rw.gcktc.cms.nodedata.Node;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.04.14
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public interface MenuNodeService {
    List<Menu> getAllMenu();
    void createMenu(Node node, String name);
    void saveMenu(Node node, Menu menuForm);
    Menu getMenuById(Long id);
    boolean removeMenu(Long idMenu);
}
