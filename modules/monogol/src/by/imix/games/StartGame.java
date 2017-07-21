package by.imix.games;

import by.imix.games.monopoly.MonopolyGame;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by miha on 15.12.2014.
 */
public class StartGame {

    public static void main(String str[]){
        ClassPathXmlApplicationContext sd=new ClassPathXmlApplicationContext("monopoly-spring.xml");
        System.out.println("StartGame");
        MonopolyGame monopolyGame = (MonopolyGame) sd.getBean("monopolyGame");
        MonopolyGame game1= (MonopolyGame) sd.getBean("monopolyGame");
        MonopolyGame game2= (MonopolyGame) sd.getBean("monopolyGame");
    }
}
