package by.nulay.changer.films;

import rw.gcktc.cms.nodedata.service.GenericDAO;

import java.util.List;

/**
 * Created by miha on 12.10.2014.
 */
public interface FilmService<T extends Film,ID extends Number> extends GenericDAO<T ,ID > {

    T addFilm(T film);

    List<T> geeListFilm(String name);

    public Seria addSeria(Seria seria);

    T getFilm(String name, String year);

    List<T> getAllFilm();
}
