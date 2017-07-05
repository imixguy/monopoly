package by.nulay.changer.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by miha on 31.07.2014.
 */
@Controller("FrandsController")
public class FrandsController {

    @Autowired
    private FrandsService frandsService;

    @RequestMapping(value = "changer/isuseradd", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean isuseradd(@RequestParam("userid") String userid,@RequestParam("servid") String servid,@RequestParam("frandid") String frandid, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        Frands fr=new Frands();
        fr.setUserid(userid);
        fr.setServid(servid);
        fr.setFrandid(frandid);
        return frandsService.isAddFrands(fr);
    }

    @RequestMapping(value = "changer/addusertogroup", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean addusertogroup(@RequestParam("ogr") String ogr, @RequestParam("userid") String userid,@RequestParam("servid") String servid,@RequestParam("frandid") String frandid, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        Frands fr=new Frands();
        fr.setUserid(userid);
        fr.setServid(servid);
        fr.setFrandid(frandid);
        fr.setOgr(ogr.equals("true"));
        frandsService.addFrands(fr);
        return  true;
    }

    @RequestMapping(value = "changer/isuseraddtouser", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean isuseraddtouser(@RequestParam("ogr") String ogr, @RequestParam("userid") String userid,@RequestParam("servid") String servid,@RequestParam("frandid") String frandid, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        Frands fr=new Frands();
        fr.setUserid(userid);
        fr.setServid(servid);
        fr.setFrandid(frandid);
        return frandsService.isAddFrandsToUser(fr);
    }
}
