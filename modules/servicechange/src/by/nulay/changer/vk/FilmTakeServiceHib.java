package by.nulay.changer.vk;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by miha on 17.11.2015.
 */
@Transactional(readOnly = true)
public class FilmTakeServiceHib<T extends FilmTake,ID extends Number> extends GenericHibernateDAO<T,ID> implements FilmTakeService<T,ID> {
    private static Logger log= Logger.getLogger(FilmTakeServiceHib.class);

//    @Autowired
//    public filmServiceHib(SessionFactory sessionFactory) {
//        super( sessionFactory);
//    }

    @Override
    @Transactional
    public void addFilm(T film){
        saveOrUpdate(film);
    }

    @Override
    public boolean isAddFilm(T film){
        List<T> lfr=getByExample(film);
        return lfr.size()!=0;
    }

    @Override
    @Transactional
    //Метод возвращает следующий анекдот, а если такого нету, то возвращает 1 не взятый ранее.
    public T getNextFilm(){
        T a= getNoWriteFilm();
        if(a!=null){
            a.setWritea(true);
            saveOrUpdate(a);
        }
        return a;
    }

    public boolean checkedFilm(String film){
        return checkedFilm(film,70,5);
    }

     /**
     * проверка анекдота, проверяет по словам больше countsimb символов и процент совпадения procsvp
     * @param discription - информация о фильме
     * @param procsvp - процент совпадения
     * @param countsimb - количество символов которое необходимо проверять
     * @return - идентичный фильм или нет
     */
    private boolean checkedFilm(String discription, int procsvp, int countsimb){
        String str="SELECT * FROM newfilm WHERE discription=:discription";
        return getSession().createSQLQuery(str).setString("discription",discription).list().size()>0;
    }

    @Override
    public T getNoWriteFilm(){
        return (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writea=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<T> getNextFilmL(Date date){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.dateCreate>:date AND A.writea=false ORDER BY A.dateCreate").setTimestamp("date", date).setFirstResult(0).setMaxResults(1).list();
        return lA;
    }


//    SELECT n.nid, nr.body FROM `node` n LEFT JOIN `node_revisions` nr on nr.nid=n.nid WHERE n.type='film'
//    and
//            (nr.body LIKE '%трудовика молотком%' OR nr.body LIKE '%получил пять%')

    public boolean isFilmHave(T a){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.film=:film").setString("film", a.getFilm()).list();
        return lA.size()>0;
    }

    @Override
    @Transactional
    public boolean saveFilm(T a) {
        if(!checkedFilm(a.getDiscription())) {
            saveOrUpdate(a);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public T getNextFilmOK(){
        T a= _getNextFilmOK();
        if(a!=null){
            a.setWriteok(true);
            saveOrUpdate(a);
        }
        return a;
    }

    private T _getNextFilmOK() {
        T film= (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writeok=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
        return film;
    }
}

