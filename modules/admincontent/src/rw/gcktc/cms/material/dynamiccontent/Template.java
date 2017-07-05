package rw.gcktc.cms.material.dynamiccontent;

import rw.gcktc.cms.material.ContentNode;
import rw.gcktc.cms.material.parsetonode.IncludeSuperclass;
import rw.gcktc.cms.nodedata.Node;

import java.util.List;

/**
 * Created by miha on 24.09.2014.
 */
@IncludeSuperclass
public class Template  extends ContentNode {
    private String type = "template";
    private String file;
    private String typeTemplate;
    private List<String> containers;

    public Template() {
        super();
    }

    public Template(Node node) {
        super(node);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTypeTemplate() {
        return typeTemplate;
    }

    public void setTypeTemplate(String typeTemplate) {
        this.typeTemplate = typeTemplate;
    }

    public List<String> getContainers() {
        return containers;
    }

    public void setContainers(List<String> containers) {
        this.containers = containers;
    }
}
