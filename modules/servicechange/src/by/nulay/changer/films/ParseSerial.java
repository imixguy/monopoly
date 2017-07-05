package by.nulay.changer.films;

import java.io.IOException;
import java.util.List;

/**
 * Created by miha on 10.05.2016.
 */
public interface ParseSerial {
    List<Film> parseListFilm(String rootUrl, String url, FilmService filmService) throws IOException;

    Film parseFilm(String rootUrl, String url) throws IOException;

    void startParse(FilmService filmService) throws IOException;

    Film saveNewFilm(Film f, FilmService filmService);

    boolean isParseComplete();

    void startParseImage(FilmService filmService) throws IOException;

    boolean saveFile2(String urlF, String nameImage, boolean big) throws IOException;
}
