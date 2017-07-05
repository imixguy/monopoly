package rw.ktc.ksupr.web.app.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dima on 03.11.2014.
 */
public class PGDPWebApplicationInitializer implements WebApplicationInitializer {
    public static WebApplicationContext webApplicationContext;

    private static final Logger logger = LoggerFactory.getLogger(PGDPWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        webApplicationContext = new XmlWebApplicationContext();
//        webApplicationContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
//        webApplicationContext.setConfigLocation("/WEB-INF/dispatcher-config.xml");

        Servlet springDispather = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", springDispather);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        logger.debug("Запуск приложения: {}", "Разобраться б с кодировками");

//        ServletRegistration.Dynamic logbackServlet = container.addServlet("logbackServlet", new ch.qos.logback.classic.ViewStatusMessagesServlet());
//        logbackServlet.setLoadOnStartup(1);
//        logbackServlet.addMapping("/logbackServlet");

//        String contextPath = container.getContextPath();
//        Const.initializeContext( contextPath);
//        logger.info("Будет использован контекст приложения: " + contextPath);

    }
//    @Override
//    public void onStartup(ServletContext container) {
//        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/*");
//    }

}
