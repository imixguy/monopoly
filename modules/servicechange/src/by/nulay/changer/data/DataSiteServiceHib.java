package by.nulay.changer.data;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by miha on 17.11.2015.
 */
@Transactional(readOnly = true)
public class DataSiteServiceHib<T extends DataSite,ID extends Number> extends GenericHibernateDAO<T,ID> implements DataSiteService<T,ID> {
    private static Logger log= Logger.getLogger(DataSiteServiceHib.class);

//    @Autowired
//    public dataSiteServiceHib(SessionFactory sessionFactory) {
//        super( sessionFactory);
//    }

    @Override
    @Transactional
    public void addDataSite(T dataSite){
        saveOrUpdate(dataSite);
    }

    @Override
    public boolean isAddDataSite(T dataSite){
        List<T> lfr=getByExample(dataSite);
        return lfr.size()!=0;
    }

    @Override
    @Transactional
    //Метод возвращает следующий анекдот, а если такого нету, то возвращает 1 не взятый ранее.
    public T getNextDataSite(){
        T a= getNoWriteDataSite();
        if(a!=null){
            a.setWritea(true);
            saveOrUpdate(a);
        }
        return a;
    }

    public boolean checkedDataSite(String dataSite){
        return checkedDataSite(dataSite,70,5);
    }

     /**
     * проверка анекдота, проверяет по словам больше countsimb символов и процент совпадения procsvp
     * @param discription - информация о фильме
     * @param procsvp - процент совпадения
     * @param countsimb - количество символов которое необходимо проверять
     * @return - идентичный фильм или нет
     */
    private boolean checkedDataSite(String discription, int procsvp, int countsimb){
        String str="SELECT * FROM newdataSite WHERE discription=:discription";
        return getSession().createSQLQuery(str).setString("discription",discription).list().size()>0;
    }

    @Override
    public T getNoWriteDataSite(){
        return (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writea=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<T> getNextDataSiteL(Date date){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.dateCreate>:date AND A.writea=false ORDER BY A.dateCreate").setTimestamp("date", date).setFirstResult(0).setMaxResults(1).list();
        return lA;
    }


//    SELECT n.nid, nr.body FROM `node` n LEFT JOIN `node_revisions` nr on nr.nid=n.nid WHERE n.type='dataSite'
//    and
//            (nr.body LIKE '%трудовика молотком%' OR nr.body LIKE '%получил пять%')

    public boolean isDataSiteHave(T a){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.dataSite=:dataSite").setString("dataSite", a.getDataSite()).list();
        return lA.size()>0;
    }

    @Override
    @Transactional
    public boolean saveDataSite(T a) {
        if(!checkedDataSite(a.getDiscription())) {
            saveOrUpdate(a);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public T getNextDataSiteOK(){
        T a= _getNextDataSiteOK();
        if(a!=null){
            a.setWriteok(true);
            saveOrUpdate(a);
        }
        return a;
    }

    private T _getNextDataSiteOK() {
        T dataSite= (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writeok=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
        return dataSite;
    }
}

