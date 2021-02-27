package by.imix.cms.userknow;

import by.imix.cms.nodedata.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import by.imix.webcms.controller.ContentNodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by miha on 09.09.2014.
 */
@Controller("personalKnowController")
public class PersonalKnowController {
    private ContentNodeService contentService;

    @Autowired
    public PersonalKnowController(ContentNodeService contentService) {
        this.contentService=contentService;
    }

    public ContentNodeService getContentService() {
        return contentService;
    }

    public void setContentService(ContentNodeService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "/user/content/savepersonalknow.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addTypeContent(PersonalKnow pkForm,HttpSession httpSession) throws IOException {
        try {
            if(pkForm.getId()!=null) {
                Node node = contentService.getNodeById(pkForm.getId());
                pkForm.setNode(node);
            }
            pkForm.fillObject();//заполняем ноде новыми данными
            contentService.saveNode(pkForm.getNode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/user/content/"+pkForm.getNode().getId()+"/viewpersonalknow.html");
    }

    @RequestMapping(value = "/user/content/savepersonalknow2.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addTypeContent2(HttpServletRequest httpreq) throws IOException {
        Node node=null;
        try {
            if(httpreq.getParameter("id").length()>0) {
                node = contentService.getNodeById(Long.parseLong((String) httpreq.getAttribute("id")));
            }else{
                node=new Node();
            }

            Enumeration<String> en=httpreq.getParameterNames();
            while(en.hasMoreElements()){
                String key=en.nextElement();
                node.addOnlyOneProperty(key, httpreq.getParameter(key));
            }
            contentService.saveNode(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (node!=null)?new ModelAndView("redirect:/user/content/"+node.getId()+"/viewpersonalknow.html"):new ModelAndView("redirect:user/content/savepersonalknow2.html");
    }

    @RequestMapping(value = "/user/content/addpersonalknow.html")
    public ModelAndView addpage(PersonalKnow pk) throws IOException {
        ModelAndView mav=new ModelAndView("redactPersonalKnow");
        mav.addObject("pk", null);
        return mav;
    }

    @RequestMapping(value = "/user/content/{idPage}/editpersonalknow.html")
    public ModelAndView editpage(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("redactPersonalKnow");
        try {
            Node node=contentService.getNodeById(idPage);
            PersonalKnow pk=new PersonalKnow(node);
            mav.addObject("personalKnow", pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/user/content/{idPk}/viewpersonalknow.html")
    public ModelAndView nodeView(@PathVariable("idPk") Long idPk) throws IOException {
//        ModelAndView mav=new ModelAndView("jsppage");
        ModelAndView mav=new ModelAndView("defpage");
        try {
            Node node= contentService.getNodeById(idPk);
            if(node!=null){
                PersonalKnow pk=new PersonalKnow(node);
                mav.addObject("pk", pk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("titlePage","Данные о знаниях пользователя");
//        mav.addObject("jspPath", "/WEB-INF/views/manager/personalKnowView.jsp");
        mav.addObject("defPath","viewPersonalKnow");
        return mav;
    }


    @RequestMapping(value = "/user/content/allpersonalknow.html")
    public ModelAndView getAllpersonalknow() throws IOException {
        ModelAndView mav=new ModelAndView("allpersonalknow");
        try {
            List<Node> listNode= contentService.getAllNodeFromPrKey("type", "personalknow");
            for(Node node:listNode){
                contentService.loadFullObject(node);
            }
            mav.addObject("listNode", listNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}
