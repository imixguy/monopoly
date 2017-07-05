package rw.gcktc.cms.material.dynamiccontent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rw.gcktc.cms.material.ContentNode;
import rw.gcktc.cms.material.parsetonode.IncludeSuperclass;
import rw.gcktc.cms.material.parsetonode.Transient;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by miha on 24.09.2014.
 */
@IncludeSuperclass
public class DynamicContent  extends ContentNode {
    private String type = "dynamicContent";
    private String typeDynCont;
    private String shablonName;
    @Transient
    private List<String> redirectUrl=new ArrayList<String>();
    @Transient
    private boolean clone=false;
    @Transient
    private List<Container> cont;
    @Transient
    @JsonIgnore
    private LinkedHashMap<String, String> typeAtribute= new LinkedHashMap<String, String>();
    @Transient
    @JsonIgnore
    private List<DynamicContent> childContainer=new ArrayList<DynamicContent>();
    {
        typeAtribute.put("jsp","jsp"); //ссылка на чистый jsp
        typeAtribute.put("tiles","tiles");  //ссылка на tiles который хочется вставить внутрь своего
        typeAtribute.put("text","text");  //текст в формате html или просто текста
        typeAtribute.put("container","container"); //другой контейнер
        typeAtribute.put("page","page"); //блок страница - к которой есть адрес
    }

    public DynamicContent() {
        super();
    }

    public DynamicContent(Node node) {
        super(node);
        for(NodeProperty np:node.getNodeProperties()){
            if(np.getKeyt().startsWith("redirectUrl[")){
                redirectUrl.add(np.getValue());
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDynCont() {
        return typeDynCont;
    }

    public void setTypeDynCont(String typeDynCont) {
        this.typeDynCont = typeDynCont;
    }

    public String getShablonName() {
        return shablonName;
    }

    public void setShablonName(String shablonName) {
        this.shablonName = shablonName;
    }

    public List<Container> getCont() {
        return cont;
    }

    public void setCont(List<Container> cont) {
        this.cont = cont;
    }

    public LinkedHashMap<String, String> getTypeAtribute() {
        return typeAtribute;
    }

    public void setTypeAtribute(LinkedHashMap<String, String> typeAtribute) {
        this.typeAtribute = typeAtribute;
    }

    public List<DynamicContent> getChildContainer() {
        return childContainer;
    }

    public void setChildContainer(List<DynamicContent> childContainer) {
        this.childContainer = childContainer;
    }

    public void addDynamicContent(DynamicContent dynamicContent) {
        getChildContainer().add(dynamicContent);
    }

    public void addContainer(Container container) {
        getCont().add(container);
    }

    public boolean isClone() {
        return clone;
    }

    public void setClone(boolean clone) {
        this.clone = clone;
    }

    public List<String> getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(List<String> redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setTypeDef(String clone){}
    @JsonIgnore
    public String getTypeDef(){return "";}

    @Transient
    @JsonIgnore
    //Метод возвращает контейнер по имени
    public Container getContainerByName(String name){
        for(Container cont: getCont()){
            if(cont.getName().equals(name)){
                return cont;
            }
        }
        return null;
    }

    @Transient
    @JsonIgnore
    //Метод возвращает DynCont по ид
    public DynamicContent getDynamicContent(Long idDC){
        for(DynamicContent dC:getChildContainer()){
            if(dC.getId().equals(idDC)){
                return dC;
            }
        }
        return null;
    }
}
