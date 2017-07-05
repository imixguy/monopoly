package by.nulay.changer.data;

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
 * Created by miha on 17.11.2015.
 */
@Controller("DataSiteController")
public class DataSiteController {

    @Autowired
    private DataSiteService dataSiteService;

    @RequestMapping(value = "changer/nextdataSite1", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    DataSite nextdataSite() throws Exception{
        return dataSiteService.getNextDataSite();
    }

    @RequestMapping(value = "changer/nextdataSite2", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    List<DataSite> nextdataSite2(@RequestParam("date") String datelong) throws Exception{
        List<DataSite> dataSite =dataSiteService.getNextDataSiteL(new Date(new Long(datelong)));
        return dataSite;
    }

    @RequestMapping(value = "changer/nextdataSite3", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    DataSite nextdataSite3(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return dataSiteService.getNextDataSite();
    }

    @RequestMapping(value = "changer/nextdataSiteok", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    DataSite nextdataSite4(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return dataSiteService.getNextDataSiteOK();
    }

    //не проверял на работоспособность
    @RequestMapping(value = "changer/savedataSitejson", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean savedataSiteJSON(@RequestParam("json") String json) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        DataSite a= null;
//        try {
//            a = mapper.readValue(json, DataSite.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        dataSiteService.saveDataSite(a);
        return true;
    }

    @RequestMapping(value = "changer/savedataSite", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean savedataSite(@RequestParam("dataSite") String dataSite, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        DataSite dataSiteF =new DataSite();
        dataSiteF.setDataSite(new String(dataSite.getBytes("ISO-8859-1"),"UTF8"));
        dataSiteF.setDateCreate(new Date());
        return dataSiteService.saveDataSite(dataSiteF);
    }

    @RequestMapping(value = "changer/savedataSite2", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public @ResponseBody boolean savedataSite2(@RequestParam("name") String name,@RequestParam("discription") String discription,@RequestParam("img") String img,@RequestParam("dataSite") String dataSite, @RequestParam("sight") String sight, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        DataSite dataSiteF =new DataSite();
        dataSiteF.setDataSite(dataSite);
        dataSiteF.setName(name);
        dataSiteF.setDiscription(discription);
        dataSiteF.setImgs(img);
        dataSiteF.setSight(sight);
        dataSiteF.setDateCreate(new Date());
        return dataSiteService.saveDataSite(dataSiteF);
    }

    @RequestMapping(value = "changer/nextdataSite4")
    public ModelAndView index(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }
}
