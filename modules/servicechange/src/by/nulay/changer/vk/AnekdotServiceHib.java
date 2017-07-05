package by.nulay.changer.vk;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.service.hib.GenericHibernateDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miha on 24.07.2014.
 */

@Transactional(readOnly = true)
public class AnekdotServiceHib <T extends Anekdot,ID extends Number> extends GenericHibernateDAO<T,ID> implements AnekdotService<T,ID> {
    private static Logger log= Logger.getLogger(AnekdotServiceHib.class);

//    @Autowired
//    public AnekdotServiceHib(SessionFactory sessionFactory) {
//        super( sessionFactory);
//    }

    @Override
    @Transactional
    public void addAnekdot(T anekdot){
        saveOrUpdate(anekdot);
    }

    @Override
    public boolean isAddAnekdot(T anekdot){
        List<T> lfr=getByExample(anekdot);
        return lfr.size()!=0;
    }

    @Override
    @Transactional
    //Метод возвращает следующий анекдот, а если такого нету, то возвращает 1 не взятый ранее.
    public T getNextAnekdot(){
        T a= getNoWriteAnekdot();
        if(a!=null){
            a.setWritea(true);
            saveOrUpdate(a);
        }
        return a;
    }

    public boolean checkedAnek(String anekdot){
        return checkedAnek(anekdot,70,5);
    }

    //проверка анекдота, проверяет по словам больше countsimb символов и процент совпадения procsvp
    private boolean checkedAnek(String anekdot, int procsvp, int countsimb){
        String str="";
        if(anekdot.length()<50){
            str = "SELECT sd.an FROM anekdot WHERE anekdot ='" + anekdot+"'";
        }else {
            String[] strA = anekdot.toLowerCase().trim().split("[^а-я]");
            List<String> lS = new ArrayList<String>();
            String ch1 = "";
            String ch2 = "";
            String ch3 = "";
            int d = 0;
            for (int i = 0; i < strA.length; i++) {
                if (strA[i].length() > countsimb) {
                    lS.add(strA[i]);
                    ch1 += "sd.ds" + d + "+";
                    ch2 += " (anekdot  REGEXP '" + strA[i] + "') ds" + d + ",";
                    ch3 += strA[i] + "|";
                    d += 1;
                }
            }
            if (d < 2) {
                if (countsimb < 3) {
                    return true;
                }
                return checkedAnek(anekdot, 70, countsimb - 1);
            }
            if (lS.size() == 0) {
                return true;
            }
            int df = (lS.size() * procsvp) / 100;
            //минимальная длина искомого текста
            int lenmin = anekdot.length() - anekdot.length() / 20;
            //максимальная длина искомого текста
            int lenmax = anekdot.length() + anekdot.length() / 20;
            str = "SELECT sd.an,(" + ch1.substring(0, ch1.length() - 1) + ") ks FROM " +
                    "(SELECT anekdot an," + ch2.substring(0, ch2.length() - 1) + " FROM " +
                    "(SELECT LOWER(anekdot) anekdot FROM anekdot WHERE CHAR_LENGTH(anekdot)>" + lenmin + " AND CHAR_LENGTH(anekdot)<" + lenmax + ") an " +
                    "WHERE anekdot REGEXP  '" + ch3.substring(0, ch3.length() - 1) + "') sd " +
                    "WHERE (" + ch1.substring(0, ch1.length() - 1) + ")>=" + df;
        }
        return getSession().createSQLQuery(str).list().size()>0;
    }

    @Override
    public T getNoWriteAnekdot(){
        return (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writea=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
    }

    @Override
    public List<T> getNextAnekdotL(Date date){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.dateCreate>:date AND A.writea=false ORDER BY A.dateCreate").setTimestamp("date", date).setFirstResult(0).setMaxResults(1).list();
        return lA;
    }


//    SELECT n.nid, nr.body FROM `node` n LEFT JOIN `node_revisions` nr on nr.nid=n.nid WHERE n.type='anekdot'
//    and
//            (nr.body LIKE '%трудовика молотком%' OR nr.body LIKE '%получил пять%')

    public boolean isAnekdotHave(T a){
        List<T> lA=getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.anekdot=:anekdot").setString("anekdot", a.getAnekdot()).list();
        return lA.size()>0;
    }

    @Override
    @Transactional
    public boolean saveAnekdot(T a) {
        if(!checkedAnek(a.getAnekdot())) {
            saveOrUpdate(a);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public T getNextAnekdotOK(){
        T a= _getNextAnekdotOK();
        if(a!=null){
            a.setWriteok(true);
            saveOrUpdate(a);
        }
        return a;
    }

    private T _getNextAnekdotOK() {
        T anek= (T) getSession().createQuery("FROM "+getPersistentClass().getName()+" A WHERE A.writeok=false ORDER BY A.dateCreate desc").setFirstResult(0).setMaxResults(1).uniqueResult();
        return anek;
    }
}
