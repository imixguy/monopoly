package rw.ktc.ksupr.web.app.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dima on 19.12.2014.
 */
@WebFilter(value = "/*", description = "Фильтр добавления метки времени!")
public class TimeRenderFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TimeRenderFilter.class);
    public static final String ATTR_START_TIME_RENDERING = "ATTR_BEGIN_RENDER";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Инициализация фильтра для расчета времени");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            request.setAttribute(ATTR_START_TIME_RENDERING, System.currentTimeMillis());
//            String url = request.getRequestURL().toString();
//            String queryString = ((HttpServletRequest)request).getQueryString();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
