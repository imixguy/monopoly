package rw.gcktc.cms.material;

import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.service.NodeService;
import rw.gcktc.webcms.controller.ContentNodeServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miha on 04.09.2014.
 */
@Transactional(readOnly = true)
public class TypeContentServiseImpl extends ContentNodeServiceImpl implements TypeContentService{

    public TypeContentServiseImpl(NodeService nodeService) {
        super(nodeService);
    }

    @Override
    public List<TypeContentNode> getAllTypeContent(String type){
        List<Node> ltcndh= getNodeService().getAllNodeFromType(type);
        List<TypeContentNode> ltc=new ArrayList<TypeContentNode>();
        if(ltcndh.size()>0){
            for(Node ndh:ltcndh){
                TypeContentNode m= new TypeContentNode(ndh);
                ltc.add(m);
            }
        }
        return ltc;
    }
}
