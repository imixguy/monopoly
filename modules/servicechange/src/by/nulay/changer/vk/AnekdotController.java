package by.nulay.changer.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by miha on 24.07.2014.
 */
@Controller("AnekdotController")
public class AnekdotController {

    @Autowired
    private AnekdotService anekdotService;

    @RequestMapping(value = "changer/nextanekdot1", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Anekdot nextanekdot() throws Exception{
        return anekdotService.getNextAnekdot();
    }

    @RequestMapping(value = "changer/nextanekdot2", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Anekdot> nextanekdot2(@RequestParam("date") String datelong) throws Exception{
        List<Anekdot> anekdot=anekdotService.getNextAnekdotL(new Date(new Long(datelong)));
        return anekdot;
    }

    @RequestMapping(value = "changer/nextanekdot3", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Anekdot nextanekdot3(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return anekdotService.getNextAnekdot();
    }

    @RequestMapping(value = "changer/nextanekdotok", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Anekdot nextanekdot4(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return anekdotService.getNextAnekdotOK();
    }

    //не проверял на работоспособность
    @RequestMapping(value = "changer/saveanekdotjson", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean saveanekdotJSON(@RequestParam("json") String json) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        Anekdot a= null;
//        try {
//            a = mapper.readValue(json, Anekdot.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        anekdotService.saveAnekdot(a);
        return true;
    }

    @RequestMapping(value = "changer/saveanekdot", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean saveanekdot(@RequestParam("anek") String anek, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        Anekdot anekdot=new Anekdot();
        anekdot.setAnekdot(new String(anek.getBytes("ISO-8859-1"),"UTF8"));
        anekdot.setDateCreate(new Date());
        return anekdotService.saveAnekdot(anekdot);
    }

    @RequestMapping(value = "changer/saveanekdot2", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody boolean saveanekdot2(@RequestParam("cadded") Integer cadded,@RequestParam("clike") Integer clike, @RequestParam("anek") String anek, @RequestParam("group") String group, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        Anekdot anekdot=new Anekdot();
        anekdot.setAnekdot(anek);
        anekdot.setCountLike(clike);
        anekdot.setCountAdded(cadded);
        anekdot.setGroup(group);
        anekdot.setDateCreate(new Date());
        return anekdotService.saveAnekdot(anekdot);
    }

    @RequestMapping(value = "changer/nextanekdot4")
    public ModelAndView index(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }
}
