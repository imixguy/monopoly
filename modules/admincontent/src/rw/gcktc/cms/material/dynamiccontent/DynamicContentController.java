package rw.gcktc.cms.material.dynamiccontent;

import by.imix.cms.redirect.RedirectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeProperty;
import rw.gcktc.webcms.controller.ContentNodeService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by miha on 24.09.2014.
 */
@Controller("dynamicContentController")
public class DynamicContentController {

    private ContentNodeService contentService;

    @Autowired
    private RedirectViewService redirectViewService;

    public RedirectViewService getRedirectViewService() {
        return redirectViewService;
    }

    public void setRedirectViewService(RedirectViewService redirectViewService) {
        this.redirectViewService = redirectViewService;
    }

    @Autowired
    public DynamicContentController(ContentNodeService contentService) {
        this.contentService=contentService;
    }

    public ContentNodeService getContentService() {
        return contentService;
    }

    public void setContentService(ContentNodeService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/alldynamiccontents.html")
    public String allTemplates() throws IOException {
        return "manager/dynamicpage/allDynamicContents";
    }

    @RequestMapping(value = "/managercms/dynamiccontent/savedcontent.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveDynamicContent(DynamicContent dcForm, HttpServletRequest request) throws IOException {
        try {
            if(!dcForm.isClone()) {
                if(dcForm.getId()!=null) {
                    Node node = contentService.getNodeById(dcForm.getId());
                    dcForm.setNode(node);
                }
                dcForm.fillObject();//заполняем ноду новыми данными


                //TODO перенести в сервисес
                //Удаляем все свойства начинающиеся на  cont[
                List<NodeProperty> lnpRem = new ArrayList<NodeProperty>();
                for (NodeProperty np : dcForm.getNode().getNodeProperties()) {
                    if (np.getKeyt().indexOf("cont[") == 0) {
                        lnpRem.add(np);
                    }
                    if (np.getKeyt().indexOf("redirectUrl[") == 0) {
                        lnpRem.add(np);
                    }
                }
                List<NodeProperty> lnpNew = dcForm.getNode().getNodeProperties();
                lnpNew.removeAll(lnpRem);
                dcForm.getNode().setNodeProperties(lnpNew);
            }else{
                dcForm.getNode().setId(null);
                dcForm.fillObject();//заполняем ноду новыми данными
            }
            //Добавляем новые свойства контейнеров
            if(dcForm.getCont()!=null) {
                for (int i = 0; i < dcForm.getCont().size(); i++) {
                    Container cont = dcForm.getCont().get(i);
                    String namecont = "cont[" + i + "].";
                    dcForm.getNode().addOnlyOneProperty(namecont + "name", cont.getName());
                    dcForm.getNode().addOnlyOneProperty(namecont + "type", cont.getType());
                    dcForm.getNode().addOnlyOneProperty(namecont + "content", cont.getContent());
                }
            }
            if(dcForm.getRedirectUrl()!=null) {
                for (int i = 0; i < dcForm.getRedirectUrl().size(); i++) {
                    String url=dcForm.getRedirectUrl().get(i);
                    String nameRU = "redirectUrl[" + i + "]";
                    dcForm.getNode().addOnlyOneProperty(nameRU, url);
                }
            }
            contentService.saveNode(dcForm.getNode());
            redirectViewService.updateRedirectList();
//            File file = new File(request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+
//                    "views"+File.separator+"templates"+File.separator+"dynamictemplates"+File.separator+"dyncont_id_"+
//                    dcForm.getId()+".jsp");


            //FileUtils.writeStringToFile(file, dcForm.getFile(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/managercms/dynamiccontent/alldynamiccontents.html");
    }

    @RequestMapping(value = "/managercms/dynamiccontent/removedc/{id_cont}", method = RequestMethod.DELETE, produces="application/json")
    public @ResponseBody boolean removedc(@PathVariable("id_cont") Long id_cont) throws IOException {
        try {
            contentService.removeNodeById(id_cont);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/newdc.html")
    public ModelAndView newdc() throws IOException {
        ModelAndView mav=new ModelAndView("redactDynamicContent");
        mav.addObject("dynamicContent", new DynamicContent());
        return mav;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/{idPage}/editdc.html")
    public ModelAndView editdc(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("redactDynamicContent");
        try {
            Node node=contentService.getNodeById(idPage);
            DynamicContent page=new DynamicContent(node);
            mav.addObject("dynamicContent", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/{idPage}/clonedc.html")
    public ModelAndView cloneDC(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("redactDynamicContent");
        try {
            Node node=contentService.getNodeById(idPage);
            DynamicContent page=new DynamicContent(node);
            page.setClone(true);
            mav.addObject("dynamicContent", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/content/dynamic/{idPage}/view.html")
    public ModelAndView viewDynamicContent(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView();
        try {
            Node node=contentService.getNodeById(idPage);
            DynamicContent dynContent=new DynamicContent(node);
            addConteiners(dynContent);
            mav.addObject("dynContent", dynContent);
            mav.setViewName("templates/dynamictemplates/template_id_" + dynContent.getShablonName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    public ModelAndView viewDynamicContent(ModelAndView mav,Long idSh) throws IOException {
        mav.getViewName();
        try {
            Node node=contentService.getNodeById(idSh);
            DynamicContent dynContent=new DynamicContent(node);
            addConteiners(dynContent,mav);
            if(mav!=null) {
                Container contC=new Container();
                contC.setName("centerPage");
                if(mav.getViewName().contains("/")){
                    contC.setType("jsp");
                }else {
                    contC.setType("tiles");
                }
                contC.setContent(mav.getViewName());
                mav.addObject("centerPage", contC);
            }
            mav.addObject("dynContent", dynContent);
            mav.setViewName("templates/dynamictemplates/template_id_" + dynContent.getShablonName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    private Map<String,Container> getMapNodeName(Node node){
        Map<String,Container> lNP=new HashMap<String, Container>();
        for(NodeProperty np:node.getNodeProperties()){
            if(np.getKeyt().startsWith("cont[") && np.getKeyt().endsWith(".name")) {
                Container c=new Container();
                c.setName(np.getValue());
                String num=np.getKeyt().substring(np.getKeyt().indexOf("[")+1,np.getKeyt().indexOf("]"));
                lNP.put(num,c);
            }
        }
        return lNP;
    }

    //метод для добавления иерархии контейнеров
    private void addConteiners(DynamicContent page){
        addConteiners(page, null);
    }

    //метод для добавления иерархии контейнеров
    private void addConteiners(DynamicContent page,ModelAndView mav){
        invertNodePToCont(page,mav);
        addPodContainers(page);
    }

    //todo Node заменить на Container List
    //метод для добавления рекурсивного добавления контейнеров в контейнеры
    private void addPodContainers(DynamicContent page){
        for(Container c:page.getCont()){
            if(c.getType().equals("container")){
                Node nodeB=contentService.getNodeById(new Long(c.getContent()));
                DynamicContent pageCh=new DynamicContent(nodeB);
                pageCh.setContent("templates/dynamictemplates/template_id_" + pageCh.getShablonName());
                addConteiners(pageCh);
                page.addDynamicContent(pageCh);
            }
        }
    }

    //метод для преобразования node в контейнер
    private void invertNodePToCont(DynamicContent page){
        invertNodePToCont(page,null);
    }
    private void invertNodePToCont(DynamicContent page, ModelAndView mav){
        Map<String,Container> mapCont=getMapNodeName(page.getNode());
        for(NodeProperty np:page.getNode().getNodeProperties()){
            if(np.getKeyt().startsWith("cont[")){
                String numCont=np.getKeyt().substring(np.getKeyt().indexOf("[")+1,np.getKeyt().indexOf("]"));
                Container cont=mapCont.get(numCont);
                String propN=np.getKeyt().substring(np.getKeyt().lastIndexOf(".")+1,np.getKeyt().length());
                if(propN.equals("name")){
                    cont.setName(np.getValue());
                }else {
                    if(propN.equals("type")){
                        cont.setType(np.getValue());
                    }else{
                        if(propN.equals("content")){
                            cont.setContent(np.getValue());
                        }
                    }
                }
            }
        }
        List<Container> containers=new ArrayList<Container>(mapCont.values());

        page.setCont(containers);
    }

    @RequestMapping(value = "/managercms/dynamiccontent/getTemplates", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<NodeProperty> getTemplates(@RequestParam("typet") String typet) throws IOException {
        List<Node> listNode=contentService.getAllNodeFromPrKey("typeTemplate",typet);
        List<NodeProperty> listTName=new ArrayList<NodeProperty>();
        for(Node n:listNode){
            n=contentService.loadFullObject(n);
            listTName.addAll(n.getPropertysValue(n, "name"));
        }

//        <!--# javascript #-->
        return listTName;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/getContainers", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<String> getContainers(@RequestParam("id_cont") Long id_cont) throws IOException {
        Node node=contentService.getNodeById(id_cont);
        NodeProperty np=node.getPropertysValue(node,"file").get(0);
        return getConteinerFromString(np.getValue());
    }

    private  List<String> getConteinerFromString(String strTextFile){
        Pattern p = Pattern.compile("<\\!--#begin.+#-->",Pattern.MULTILINE);
        Matcher m =  p.matcher(strTextFile);
        List<String> str=new ArrayList<String>();
        while (m.find()) {
            String stl=m.group();
            str.add(stl.substring(10,stl.length()-4).trim());
        }
        return str;
    }

    @RequestMapping(value = "/managercms/dynamiccontent/getDataContainers", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Container> getDataContainers(@RequestParam("id_cont") Long id_cont) throws IOException {
        DynamicContent page=null;
        try {
            Node node=contentService.getNodeById(id_cont);
            page=new DynamicContent(node);
            invertNodePToCont(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page.getCont();
    }

    //метод для возврата всех DynamicContent в json
    @RequestMapping(value = "managercms/dynamiccontent/getAllContainers", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<DynamicContent> getAllContainers() throws IOException {
        List<Node> listNode = contentService.getAllNodeFromPrKey("type", "dynamicContent");
        List<DynamicContent> ldynCont=new ArrayList<DynamicContent>();
        for (Node n : listNode) {
            n = contentService.loadFullObject(n);
            DynamicContent dc=new DynamicContent(n);
//            invertNodePToCont(dc);
            ldynCont.add(dc);
        }
        return ldynCont;
    }




}
