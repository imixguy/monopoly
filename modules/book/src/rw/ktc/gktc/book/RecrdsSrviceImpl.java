package rw.ktc.gktc.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;

import java.util.List;

/**
 * Created by miha on 04.12.2014.
 */
@Service("recrdsService")
@Transactional(readOnly = true)
public class RecrdsSrviceImpl implements RecrdsService {
    private GenericHibernateDAO<Record,Long> gd;
    @Autowired
    public RecrdsSrviceImpl(org.hibernate.SessionFactory sessionFactory) {
        // super(sessionFactory);
        gd =new GenericHibernateDAO<Record,Long>(Record.class,sessionFactory);
    }

    @Override
    @Transactional
    public Record saveRecord(Record record) {
        return gd.saveOrUpdate(record);
    }

    @Override
    public List<Record> getAllRecord() {
        return gd.getAllObject();
    }

    @Override
    public Record getRecordById(Long id) {
        return gd.getById(id,false);
    }

    @Override
    @Transactional
    public boolean removeRecordById(Long id) {
        try {
            gd.deleteFromId(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean removeRecord(Record record) {
        try {
            gd.delete(record);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
