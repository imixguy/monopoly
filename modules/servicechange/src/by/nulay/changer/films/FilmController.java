package by.nulay.changer.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by miha on 12.10.2014.
 */
@Controller("FilmController")
public class FilmController {

    @Autowired
    private ParseSerialImpl parseSerial;

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "changer/addfilm", method = RequestMethod.POST)
    public @ResponseBody boolean addFilm(@RequestBody Film film, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");

        return (filmService.addFilm(film)!=null);
    }

    @RequestMapping(value = "changer/getfilm", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Boolean getFilm( HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        final ParseSerialImpl pS=parseSerial;
        final FilmService filmServ=filmService;
        parseSerial.startParseImage(filmService);
        Runnable r=new Runnable() {
            @Override
            public void run() {
                try {
                    pS.startParse(filmServ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();
        return true;
    }

    @RequestMapping(value = "changer/percentcompletefilm", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Integer getPercentComplete(HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return parseSerial.getPercentComplete();
    }

    @RequestMapping(value = "changer/fileassociate", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Boolean getFileassociate(HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");


        File folderFile  = new File(File.separator.concat("resources").concat(File.separator).concat("images").
                concat(File.separator).concat("bigposters"));

        List<Film> lF=filmService.getAllFilm();
        for(Film film:lF) {
            try {
                if(film.getNameFile()==null || film.getNameFile().equals("")){
                    String fName = (film.getTransliterate().length()>15)?film.getTransliterate().substring(0,15):film.getTransliterate();
                    fName=fName+"_"+film.getDateBegin()+".jpg";
                    File file=new File(folderFile,fName);
                    if(file!=null && fName.equals(file.getName())){
                        film.setNameFile(file.getName());
                        filmService.addFilm(film);
                    }else{
                        System.out.println("2 odinak file");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
