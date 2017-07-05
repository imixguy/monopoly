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
 * Created by miha on 17.11.2015.
 */
@Controller("FilmTakeController")
public class FilmTakeController {

    @Autowired
    private FilmTakeService filmTakeService;

    @RequestMapping(value = "changer/nextfilm1", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    FilmTake nextfilm() throws Exception{
        return filmTakeService.getNextFilm();
    }

    @RequestMapping(value = "changer/nextfilm2", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    List<FilmTake> nextfilm2(@RequestParam("date") String datelong) throws Exception{
        List<FilmTake> filmTake =filmTakeService.getNextFilmL(new Date(new Long(datelong)));
        return filmTake;
    }

    @RequestMapping(value = "changer/nextfilm3", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    FilmTake nextfilm3(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return filmTakeService.getNextFilm();
    }

    @RequestMapping(value = "changer/nextfilmok", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    FilmTake nextfilm4(HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        return filmTakeService.getNextFilmOK();
    }

    //не проверял на работоспособность
    @RequestMapping(value = "changer/savefilmjson", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean savefilmJSON(@RequestParam("json") String json) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        FilmTake a= null;
//        try {
//            a = mapper.readValue(json, Film.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        filmTakeService.saveFilm(a);
        return true;
    }

    @RequestMapping(value = "changer/savefilm", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody boolean savefilm(@RequestParam("film") String film, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        FilmTake filmTakeF =new FilmTake();
        filmTakeF.setFilm(new String(film.getBytes("ISO-8859-1"),"UTF8"));
        filmTakeF.setDateCreate(new Date());
        return filmTakeService.saveFilm(filmTakeF);
    }

    @RequestMapping(value = "changer/savefilm2", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public @ResponseBody boolean savefilm2(@RequestParam("name") String name,@RequestParam("discription") String discription,@RequestParam("img") String img,@RequestParam("film") String film, @RequestParam("sight") String sight, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        FilmTake filmTakeF =new FilmTake();
        filmTakeF.setFilm(film);
        filmTakeF.setName(name);
        filmTakeF.setDiscription(discription);
        filmTakeF.setImg(img);
        filmTakeF.setSight(sight);
        filmTakeF.setDateCreate(new Date());
        return filmTakeService.saveFilm(filmTakeF);
    }

    @RequestMapping(value = "changer/nextfilm4")
    public ModelAndView index(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }
}
