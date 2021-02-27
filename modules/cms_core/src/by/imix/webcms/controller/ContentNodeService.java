package by.imix.webcms.controller;

import by.imix.cms.nodedata.Node;
import by.imix.webcms.security.NodeServiceLayer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 16:25
 * Интерфейс реализующий методы для работы с классом ContentNode
 */
public interface ContentNodeService extends NodeServiceLayer {
    Node getNodeById(Long id);
    Node getFullNodeById(Long id);
    Node saveNode(Node node);
    void removeNode(Node node);
    void removeNodeById(Long id);
    List<Node> getAllContent();
    List<Node> getAllNodeFromPrKey(String key,String value);
    Node loadFullObject(Node node);
}
