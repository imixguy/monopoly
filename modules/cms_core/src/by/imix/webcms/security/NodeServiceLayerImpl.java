package by.imix.webcms.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import by.imix.cms.nodedata.service.NodeService;
import by.imix.cms.usermanager.User;

/**
 * Created by miha on 17.09.2014.
 */
public abstract class NodeServiceLayerImpl implements NodeServiceLayer {
    private NodeService nodeService;

    public NodeServiceLayerImpl(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public User getWebUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user=auth.getPrincipal();
        if(user instanceof String){
            return null;
        }
        return ((UserWeb) auth.getPrincipal()).getUserw();
    }
}
