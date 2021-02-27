package by.imix.cms.material;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 23.05.14
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
@Controller("typeContentController")
public class TypeContentController {
    @RequestMapping(value = "/managers/addtypecontent.html")
    public ModelAndView addTypeContent(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }

    @RequestMapping(value = "/managers/getalltypecontent.html")
    public ModelAndView getAllTypeContent(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }

    @RequestMapping(value = "/managers/removetypecontent.html",method= RequestMethod.POST)
    public ModelAndView removeTypeContent(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }
}
