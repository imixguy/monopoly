package by.imix.games.monopoly.web;

import by.imix.games.gamecore.UserRoom;
import by.imix.games.monopoly.GameMonopoly;
import by.imix.games.monopoly.UserMonopoly;

import java.util.List;

/**
 * Created by miha on 05.01.2015.
 */
public interface GameManager{
    UserMonopoly getUserMonopoly();
    //создать комнату
    GameMonopoly createGame();
    //получить список всех комнат
    List<GameMonopoly> getAllGame();
    //получить список игр в которых можно учавствовать конкретному пользователю
    List<GameMonopoly> getAllPermissionRoom(UserMonopoly user);
    //получить комнату по номеру
    GameMonopoly getGameByNumberRoom(long numberRoom);
    //получить игры по пользователю
    List<? extends GameMonopoly> getGameByUser(UserRoom user);
    //получить пользователя для комнаты
    UserMonopoly getUser();
    //игра закончена
    boolean gameEnd(GameMonopoly gameMonopoly);
}
