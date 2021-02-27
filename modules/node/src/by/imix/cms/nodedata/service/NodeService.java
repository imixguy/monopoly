package by.imix.cms.nodedata.service;

import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.nodedata.state.State;
import by.imix.cms.nodedata.state.StateDefault;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.04.14
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public interface NodeService extends GenericDAO<Node,Long>{
    //Метод возвращает все свойства узла с ключем key
    List<? extends NodeProperty> getPropertysValue(Node node, String key);
    //Метод возвращает все свойства узла
    List<NodeProperty> getAllPropertys(Node node);
    //Метод возвращает виртуальные(узлы классы которых различаются свойством type) узлы по типу
    List<Node> getAllNodeFromType(String type);
    List<Node> getAllNodeFromType(Class clazz,String type);
    //Метод возвращает состояние по идентификатору
    State loadState(Long idState);
    //Метод возвращает узел по идентификатору узла и классу
    Node getNodeById(Class clazz,Long id);
    //Метод возвращает узел по идентификатору узла со всеми подгруженными списками
    Node getFullNodeById(Long id);
    Node getFullNodeById(Class clazz,Long id);
    //Метод возвращает все возможные состояния, которые может принять узел
    List<StateDefault> getStates(Node node);
    //Метод удаляет узел
    void removeNode(Node node) throws IllegalArgumentException;
    //Метод сохраняет узел node - узел который нужно сохранить, generatingNode - узел который ответственнй за сохранение
    Node saveNode(Node node, Node generatingNode);
    //Метод возвращает все узлы
    List<Node> getAllNode(Class clazz);
    //Метод загружает все связанные объекты
    Node loadFullObject(Node node);
    //Метод возвращает все узлы у которых есть проперти с key и value
    List<Node> getAllNodeFromPrKey(String key, String value);
    List<Node> getAllNodeFromPrKey(Class clazz, String key, String value);
}