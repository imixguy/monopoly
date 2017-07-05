package by.nulay.changer.vk;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by miha on 14.09.2015.
 */
@Service("frandsService")
public class FrandsServiceR  extends FrandsServiceHib<Frands,Long> implements FrandsService<Frands,Long> {
//    public FrandsServiceR(SessionFactory sessionFactory) {
//        super(sessionFactory);
//    }
}
