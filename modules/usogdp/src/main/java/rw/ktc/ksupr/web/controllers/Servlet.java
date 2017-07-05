package rw.ktc.ksupr.web.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import rw.ktc.ksupr.web.app.init.PGDPWebApplicationInitializer;
import rw.ktc.ksupr.web.dao.entity.VisitInfo;
import rw.ktc.ksupr.web.dao.repository.IVisitInfoRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dima on 23.03.2015.
 */

@WebServlet("/tests")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext servletContext = getServletContext();

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ApplicationContext ctx2 = PGDPWebApplicationInitializer.webApplicationContext;

        if (ctx != null){
            IVisitInfoRepository repository = (IVisitInfoRepository) ctx.getBean(IVisitInfoRepository.class);
            List<VisitInfo> list = repository.findAll();
            out.println("<h1>" + "ctx" + "</h1>");
            for(int i=0;i<list.size();i++){
                out.println("<h2>" + list.get(i).toString() + "</h2>");
            };
        }
        if (ctx2 != null){
            IVisitInfoRepository repository = (IVisitInfoRepository) ctx2.getBean(IVisitInfoRepository.class);
            List<VisitInfo> list = repository.findAll();
            out.println("<h1>" + "ctx2" + "</h1>");
            for(int i=0;i<list.size();i++){
                out.println("<h2>" + list.get(i).toString() + "</h2>");
            };
        }

//        MyBean myBean = (MyBean)ctx.getBean("myBean");

        // Actual logic goes here.
        out.println("<h1>" + "end" + "</h1>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
