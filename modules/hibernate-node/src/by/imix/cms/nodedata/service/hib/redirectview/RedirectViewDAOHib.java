package by.imix.cms.nodedata.service.hib.redirectview;

import by.imix.cms.redirect.RedirectView;
import by.imix.cms.redirect.RedirectViewService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.imix.cms.nodedata.service.hib.NodeServiceHib;

import java.util.List;

/**
 * Created by miha on 31.10.2014.
 */
@Service("redirectviewservicehib")
@Transactional(readOnly = true)
public class RedirectViewDAOHib extends NodeServiceHib implements RedirectViewService {
    private static Logger log= Logger.getLogger(RedirectViewDAOHib.class);
    private List<RedirectView> redirectViewList;

    @Autowired
    public RedirectViewDAOHib(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<RedirectView> getRedirectAll() {
        List<RedirectView> list = getSession().createSQLQuery("SELECT id_node as id_nodeView, value as url FROM node_property WHERE keyt LIKE '%redirectUrl%'").
                    addScalar("url").
                    addScalar("id_nodeView", StandardBasicTypes.LONG).
                    setResultTransformer(Transformers.aliasToBean(RedirectView.class)).list();
        return list;
    }


    @Override
    public RedirectView checkRedirect(String urlR) {
        if(redirectViewList==null){
            updateRedirectList();
        }
        RedirectView rw=null;
        if(redirectViewList!=null && redirectViewList.size()>0) {
            for (RedirectView rwch : redirectViewList) {
                if (rwch.checkURL(urlR)) {
                    rw = rwch;
                    break;
                }
            }
        }
        return rw;
    }

    @Override
    public void updateRedirectList() {
        redirectViewList=getRedirectAll();
    }
}
