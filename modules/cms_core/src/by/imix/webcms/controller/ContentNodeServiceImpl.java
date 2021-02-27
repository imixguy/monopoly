package by.imix.webcms.controller;

import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.service.NodeService;
import by.imix.webcms.security.NodeServiceLayerImpl;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 13:51
 * Класс реализующий методы для работы с классом ContentNode
 */
@Transactional(readOnly = true)
public class ContentNodeServiceImpl extends NodeServiceLayerImpl implements ContentNodeService {
    private static Logger log= Logger.getLogger(ContentNodeServiceImpl.class);

    public ContentNodeServiceImpl(NodeService nodeService) {
        super(nodeService);
    }

    @Transactional
    @Override
    public Node saveNode(Node node) {
        return getNodeService().saveNode(node, getWebUser());
    }

    @Transactional
    @Override
    public void removeNode(Node node) {
        getNodeService().removeNode(node);
    }

    @Transactional
    @Override
    public void removeNodeById(Long id) {
        getNodeService().deleteFromId(id);
    }

    @Override
    public List<Node> getAllContent() {
        return getNodeService().getAllObject();
    }

    @Override
    public List<Node> getAllNodeFromPrKey(String key, String value) {
        return getNodeService().getAllNodeFromPrKey(key, value);
    }

    @Override
    public Node loadFullObject(Node node) {
        return getNodeService().loadFullObject(node);
    }

    @Override
    public Node getNodeById(Long id) {
        Node node= getNodeService().getById(id, false);
        if(node!=null) {
            getNodeService().getAllPropertys(node);
        }
        return node;
    }

    @Override
    public Node getFullNodeById(Long id) {
        Node node=getNodeById(id);
        return loadFullObject(node);
    }
}
