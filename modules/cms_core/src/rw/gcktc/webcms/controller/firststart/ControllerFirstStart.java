package rw.gcktc.webcms.controller.firststart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 06.05.14
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
@Controller("FirstController")
@SessionAttributes
public class ControllerFirstStart {
    private static Logger log = Logger.getLogger(ControllerFirstStart.class);

    private ApplicationContext context;
    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping(value = "index.html")
    public String index() throws IOException {
//        log.info("Не поподает сюда - беда блин");
        return "firstPage";
    }

//    @RequestMapping(value = "settings.html")
//    public String settings(HttpServletRequest httpServletRequest) throws IOException {
//        log.info("Не поподает сюда - беда блин");
//        return "firstPage";
//    }
}
