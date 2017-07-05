package rw.ktc.gktc.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;

import java.util.List;

/**
 * Created by miha on 27.11.2014.
 */
@Service("bookmanagerimplhib")
public class BookManagerImplHib extends GenericHibernateDAO implements BookManager {

    @Autowired
    public BookManagerImplHib(org.hibernate.SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
