package by.imix.games.imaginarium.web;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Mikhail_Kachanouski on 7/28/2017.
 * Class every possible action from user for imaginarium game
 * Получить 6 карт каждый игрок
 * 1 игрок становится ведущим
 * Загадывает ассоциацию и озвучивает
 * Выставляет карту спрятано
 * Все игроки выкладывают по 1 карте (связь с ассоциацией)
 * Все кроме ведущего голосуют за какую нить карту(за свою голосовать нельзя)
 * Если карточку ведущего угадали все игроки, то он идет на 3 хода назад (или на поле 1, если он еще не продвинулся дальше третьего поля), а остальные игроки стоят на месте.
 * Если карточку ведущего никто не угадал, то ведущий идет на 2 хода назад. Плюс, очки получают игроки, чьи карточки угадали.
 * В любом другом случае по 3 очка получают все игроки, правильно угадавшие карточку. Ведущий получает 3 очка плюс по очку за каждого угадавшего его игрока.
 * Все игроки получают по одному очку за каждого игрока, который угадал их картинку.
 */
public interface UserActions {

    /**
     * Set association
     * @param association association
     */
    void setAssociation(String association);


    void gameEnd();

    String gameClose();

    void sendMessage(@RequestParam("message") String message);
}
