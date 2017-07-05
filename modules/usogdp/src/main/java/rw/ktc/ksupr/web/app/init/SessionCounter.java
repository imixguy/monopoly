package rw.ktc.ksupr.web.app.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import rw.ktc.ksupr.web.dao.entity.UserFace;
import rw.ktc.ksupr.web.dao.entity.VisitInfo;
import rw.ktc.ksupr.web.dao.repository.IUserFaceRepository;
import rw.ktc.ksupr.web.dao.repository.IVisitInfoRepository;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dmitry 4ernookiy - 4ernookiy@gmail.com on 08.05.14.
 */
@WebListener
public class SessionCounter implements HttpSessionListener, ServletRequestListener {
    private Logger LOG = LoggerFactory.getLogger(SessionCounter.class);

    private WebApplicationContext context = PGDPWebApplicationInitializer.webApplicationContext;

    private static IVisitInfoRepository visitInfoRepository;

    private static final String ATTRIBUTE_NAME = "rw.ktc.ksupr.web.app.init.SessionCounter";
    private Map<HttpSession, String> sessions = new ConcurrentHashMap<HttpSession, String>();
    private Map<String, VisitInfo> visitorsForIp = new ConcurrentHashMap<String, VisitInfo>();

    private static final List<String> filterIp;
    static{
        filterIp = new ArrayList<>();
        filterIp.add("127.0.0.1");
        filterIp.add("10\\.4\\.\\d+\\.\\d+.*");
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest httpRequest = (HttpServletRequest) event.getServletRequest();
        String ip = httpRequest.getHeader("X-Forwarded-For");
        if (null == ip) {
            ip = httpRequest.getRemoteAddr();
        }
        if (notAllowedForSave(ip)) return;
        VisitInfo visitor = visitorsForIp.get(ip);
        if (null != visitor) {
            visitor.increaseCountRequest();
            return;
        }
        HttpSession session = httpRequest.getSession();
        if (session.isNew()) {
            sessions.put(session, httpRequest.getRemoteAddr());
            VisitInfo visitInfo = new VisitInfo();
            visitInfo.setBeginSession(new Date(session.getCreationTime()));
            visitInfo.setIp(ip);
            visitInfo.setCountRequest(1);
            visitInfo.setAgent(httpRequest.getHeader("User-Agent"));

            if (null != getVisitInfoRepository()) {
                visitInfo = getVisitInfoRepository().save(visitInfo);
            }

            visitorsForIp.put(ip, visitInfo);
            LOG.info("новый пользователь:{}", visitInfo);

        }
    }
    private static boolean notAllowedForSave(String ip){
//        String EXAMPLE_IP_REGEX = "10\\.4\\.\\d+\\.\\d+.*";
        for (int i=0; i< filterIp.size();i++){
            if (ip.matches(filterIp.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        LOG.info("session Destroyed");
        HttpSession session = event.getSession();
        Date lastAccess = new Date(session.getLastAccessedTime());
        String ip = sessions.get(session);
        sessions.remove(session);
        LOG.info("total sessions:" + sessions.size());
        if (null != ip) {
            VisitInfo vi = visitorsForIp.get(ip);
            if (null != vi) {
                visitorsForIp.remove(ip);
                vi.setEndSession(lastAccess);
                LOG.info("Сессия завершена для пользователя:{}", vi);
                if (null != getVisitInfoRepository()) {
                    getVisitInfoRepository().save(vi);
                }
            }
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // NOOP. Useless since we can't obtain IP here.
//        LOG.info("session created : " + event.getSession().getId());
        if (null == getVisitInfoRepository()) {
//            WebApplicationContext context = PGDPWebApplicationInitializer.webApplicationContext;
            IVisitInfoRepository repository = (IVisitInfoRepository) context.getBean(IVisitInfoRepository.class);
            setVisitInfoRepository(repository);
        }
//        if (null != getVisitInfoRepository){
//            IUserFaceRepository repository = (IVisitInfoRepository) context.getBean(IVisitInfoRepository.class);
//            UserFace user = repository.findOne(1);
//            UserFace user = repository.find;
//            event.getSession().setAttribute("USER", );
//        }



//        WebApplicationContext context = PGDPWebApplicationInitializer.webApplicationContext;
//        context

    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        // NOOP. No logic needed.

    }

    public IVisitInfoRepository getVisitInfoRepository() {
        return visitInfoRepository;
    }

    public void setVisitInfoRepository(IVisitInfoRepository visitInfoRepository) {
        this.visitInfoRepository = visitInfoRepository;
    }
}