package by.nulay.changer.films;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by miha on 14.09.2015.
 */
@Service("filmService")
public class FilmServiceR extends FilmServiceHib<Film,Long> implements FilmService<Film,Long> {

}
