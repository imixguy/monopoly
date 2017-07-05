package by.nulay.changer.vk;

import java.util.Date;
import java.util.List;

/**
 * Created by miha on 17.11.2015.
 */
public interface FilmTakeService<T extends FilmTake,ID extends Number> {

    void addFilm(T film);

    boolean isAddFilm(T film);

    T getNextFilm();

    T getNoWriteFilm();

    List<T> getNextFilmL(Date date);

    boolean saveFilm(T a);

    T getNextFilmOK();

    boolean checkedFilm(String film);
}
