package by.nulay.changer.vk;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by miha on 14.09.2015.
 */
@Service("anekdotService")
public class AnekdotServiceR extends AnekdotServiceHib<Anekdot,Long> implements AnekdotService<Anekdot,Long> {
//    public AnekdotServiceR(SessionFactory sessionFactory) {
//        super(sessionFactory);
//    }
}
