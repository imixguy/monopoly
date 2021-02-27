package by.imix.cms.nodedata.service;

import by.imix.cms.nodedata.HistoryNode;
import by.imix.cms.nodedata.Node;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 25.04.14
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public interface HistoryNodeService extends NodeService{
    //Метод возвращает все исторические узлы
    List<HistoryNode> getAllNDH();
    //Метод возвращает все исторические узлы конкретного узла
    //todo дописать
    List<HistoryNode> getAllNDHForConcreteNode(HistoryNode node);
    void removeNode(HistoryNode node, Node generatingNode);
}