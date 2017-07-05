package rw.gcktc.cms.material.dynamiccontent;

import by.imix.cms.redirect.RedirectView;
import by.imix.cms.redirect.RedirectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by miha on 17.10.2014.
 */
@Transactional(readOnly = true)
public class DynamicContentHandler implements HandlerInterceptor {
    @Autowired
    DynamicContentController dynamicContentController;
    @Autowired
    private RedirectViewService redirectViewService;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null && !modelAndView.getViewName().startsWith("redirect:")){
            RedirectView rw=redirectViewService.checkRedirect(httpServletRequest.getRequestURI());
            if(rw!=null) {
                ModelAndView mav=dynamicContentController.viewDynamicContent(modelAndView,rw.getId_nodeView());
                modelAndView.setViewName(mav.getViewName());
                modelAndView.addAllObjects(mav.getModel());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
    //override postHandle() and afterCompletion()
}

