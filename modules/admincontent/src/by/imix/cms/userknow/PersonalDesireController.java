package by.imix.cms.userknow;

import by.imix.cms.nodedata.Node;
import by.imix.webcms.controller.ContentNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by miha on 09.09.2014.
 */
@Controller("personalDesireController")
public class PersonalDesireController {

    private ContentNodeService contentService;

    @Autowired
    public PersonalDesireController(ContentNodeService contentService) {
        this.contentService=contentService;
    }

    public ContentNodeService getContentService() {
        return contentService;
    }

    public void setContentService(ContentNodeService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "/user/content/savepersonaldesire.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addTypeContent(PersonalDesire pkForm,HttpSession httpSession) throws IOException {
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
        return new ModelAndView("redirect:/user/content/"+pkForm.getNode().getId()+"/viewpersonaldesire.html");
    }

    @RequestMapping(value = "/user/content/addpersonaldesire.html")
    public ModelAndView addpage(PersonalDesire pk) throws IOException {
        ModelAndView mav=new ModelAndView("redactPersonalDesire");
        mav.addObject("pk", null);
        return mav;
    }

    @RequestMapping(value = "/user/content/{idPage}/editpersonaldesire.html")
    public ModelAndView editpage(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("redactPersonalDesire");
        try {
            Node node=contentService.getNodeById(idPage);
            PersonalDesire pk=new PersonalDesire(node);
            mav.addObject("personalDesire", pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/user/content/{idPk}/viewpersonaldesire.html")
    public ModelAndView nodeView(@PathVariable("idPk") Long idPk) throws IOException {
        ModelAndView mav=new ModelAndView("viewPersonalKnow");
        try {
            Node node= contentService.getNodeById(idPk);
            if(node!=null){
                PersonalDesire pk=new PersonalDesire(node);
                mav.addObject("pk", pk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/user/content/allpersonaldesire.html")
    public ModelAndView getAllpersonalknow() throws IOException {
        ModelAndView mav=new ModelAndView("allpersonalknow");
        try {
            List<Node> listNode= contentService.getAllNodeFromPrKey("type","personaldesire");
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