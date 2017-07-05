package by.nulay.changer.vk;

import org.springframework.stereotype.Service;

/**
 * Created by miha on 17.11.2015.
 */
@Service("FilmTakeService")
public class FilmTakeServiceR extends FilmTakeServiceHib<FilmTake,Long> implements FilmTakeService<FilmTake,Long> {
}
