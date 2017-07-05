package by.nulay.changer.parser;

import by.nulay.changer.ChangerException;
import by.nulay.changer.vk.FilmTake;
import by.nulay.changer.vk.FilmTakeService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by miha on 23.01.2016.
 */
public abstract class ParserImpl {
    private static Logger log= Logger.getLogger(SerialochkaParser.class);
    @Autowired
    private FilmTakeService filmTakeService;

    public FilmTakeService getFilmTakeService() {
        return filmTakeService;
    }

    public void startParseAndSave() throws ChangerException{
        try {
            saveFilm(startParse());
        } catch (Exception e) {
            log.error("Not success parse and save Film",e);
            throw new ChangerException("Not success parse and save Film",e);
        }
    }

    public void setFilmTakeService(FilmTakeService filmTakeService) {
        this.filmTakeService = filmTakeService;
    }

    public List<FilmTake> startParse() throws IOException {return null;}

    public void saveFilm(List<FilmTake> listFilmTake){
        for(FilmTake ft:listFilmTake){
            filmTakeService.saveFilm(ft);
            log.info("loadFilm "+ft.getName());
        }
    }
}
