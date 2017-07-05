package rw.gcktc.webcms.controller.logging;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by miha on 28.05.2016.
 */
@Controller("LoggingController")
public class LoggingController {
    private static Logger logger = Logger.getLogger(LoggingController.class);

    @RequestMapping(value = "changer/task/checkperform", method = RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    public @ResponseBody
    boolean checkPerform(@RequestParam("id") Long id, @RequestParam("log") String log, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        logger.info("Logging UI id="+id+"; log="+log);
        return true;
    }
}
