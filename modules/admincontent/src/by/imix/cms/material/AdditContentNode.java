package by.imix.cms.material;

import by.imix.cms.nodedata.HistoryNode;
import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.NodeProperty;

import java.util.List;

/**
 * Created by miha on 04.09.2014.
 * Класс содержащий в себе информацию на основе проперти для дополнительного контента. Например блоков.
 */
public abstract class AdditContentNode extends ContentNode implements AdditContent{
    protected String listURL;//список URL в виде JSON

    public AdditContentNode() {
        this.node=new HistoryNode();
    }

    public AdditContentNode(Node node) {
        this.node = node;
        getType();
        getContent();
        getName();
        isPastRoot();
    }

    @Override
    public String getListURL() {
        if(listURL!=null && listURL.length()>0) return listURL;
        List<NodeProperty> lp=node.getPropertysValue(node, "listURL");
        if(lp!=null && lp.size()>0){
            listURL=lp.get(0).getValue();
        }
        return listURL;
    }

    @Override
    public void setListURL(String listURL) {
        node.addOnlyOneProperty("listURL", listURL);
        this.listURL = listURL;
    }
}
