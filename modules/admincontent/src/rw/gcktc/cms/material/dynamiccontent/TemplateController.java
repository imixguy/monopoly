package rw.gcktc.cms.material.dynamiccontent;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.webcms.controller.ContentNodeService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by miha on 08.12.2014.
 * Контроллер для работы с темплейтами
 */
@Controller("templateController")
public class TemplateController {

    private ContentNodeService contentService;

    @Autowired
    public TemplateController(ContentNodeService contentService) {
        this.contentService=contentService;
    }

    public ContentNodeService getContentService() {
        return contentService;
    }

    public void setContentService(ContentNodeService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping(value = "managercms/dynamiccontent/alltemplates.html")
    public String allTemplates() throws IOException {
        return "manager/dynamicpage/allTemplates";
    }

    @RequestMapping(value = "managercms/dynamiccontent/savedtemplate.html" ,method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveTemplate(Template templateF, HttpServletRequest request) throws IOException {
        try {
            if(templateF.getId()!=null) {
                Node node = contentService.getNodeById(templateF.getId());
                templateF.setNode(node);
            }
            templateF.fillObject();//заполняем ноду новыми данными
            contentService.saveNode(templateF.getNode());
            File file = new File(request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+
                    "views"+File.separator+"templates"+File.separator+"dynamictemplates"+File.separator+"template_id_"+
                    templateF.getId()+".jsp");
            FileUtils.writeStringToFile(file, templateF.getFile(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/managercms/dynamiccontent/alltemplates.html");
    }

    @RequestMapping(value = "managercms/dynamiccontent/adddtemplate.html")
    public ModelAndView addTemplate(Template template) throws IOException {
        ModelAndView mav=new ModelAndView("redactTemplate");
        //page=new DynamicContent();

        mav.addObject("template", template);
        return mav;
    }
}
