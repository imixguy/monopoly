package rw.gcktc.cms.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rw.gcktc.cms.nodedata.HistoryNode;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.webcms.controller.ContentNodeService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
@Controller("pageController")
public class PageController {

    private ContentNodeService contentService;

    @Autowired
    public PageController(ContentNodeService contentService) {
        this.contentService=contentService;
    }

    public ContentNodeService getContentService() {
        return contentService;
    }

    public void setContentService(ContentNodeService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "/admin/content/savepage.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addTypeContent(Page pageForm,HttpSession httpSession) throws IOException {
        try {
            if(pageForm.getId()!=null) {
                Node node = contentService.getNodeById(pageForm.getId());
                pageForm.setNode(node);
            }else{
                pageForm.setNode(new HistoryNode());
            }
            pageForm.fillObject();//заполняем ноду новыми данными

            contentService.saveNode(pageForm.getNode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/node/"+pageForm.getNode().getId()+"/viewpage.html");
    }

    @RequestMapping(value = "/admin/content/addpage.html")
    public ModelAndView addpage(Page page) throws IOException {
        ModelAndView mav=new ModelAndView("redactPage");
        mav.addObject("page", null);
        return new ModelAndView("redactPage");
    }

    @RequestMapping(value = "/admin/content/{idPage}/editpage.html")
    public ModelAndView editpage(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("redactPage");
        try {
            HistoryNode node=(HistoryNode) contentService.getNodeById(idPage);
            Page page=new Page(node);
            mav.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/node/{idPage}/viewpage.html")
    public ModelAndView nodeView(@PathVariable("idPage") Long idPage) throws IOException {
        ModelAndView mav=new ModelAndView("viewPage");
        blockNodeView(mav,idPage,"page");
        return mav;
    }

    public ModelAndView blockNodeView(ModelAndView mav, Long idPage, String unicIdObj) throws IOException {
        try {
            Node node= contentService.getNodeById(idPage);
            if(node!=null){
                Page page=new Page(node);
                mav.addObject(unicIdObj, page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/node/allcontent.html")
    public ModelAndView getAllcontent() throws IOException {
        ModelAndView mav=new ModelAndView("allContentPage");
        try {
            List<Node> listNode= contentService.getAllContent();
            mav.addObject("listNode", listNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/node/allcontent2.html")
    public ModelAndView getAllcontent2() throws IOException {
        ModelAndView mav=new ModelAndView("detail");
        try {
            List<Node> listNode= contentService.getAllContent();
            mav.addObject("nodes", listNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
}