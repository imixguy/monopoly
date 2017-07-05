package by.nulay.changer.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by miha on 17.04.2016.
 */
public interface TaskWebService {

    List<Task> getTasks(Boolean perform, String executor);

    Task addOrChangeTask(String taskjson, HttpServletResponse response);

    Task getNextTask(String executor, HttpServletResponse response);

    List<Task> gettasksForCriteria(String jsoncriterion, HttpServletResponse response);

    Task getTaskForPage(String executor, String pageName, HttpServletResponse response);

    Task getTaskForPage2(String executor, String pageName, HttpServletResponse response);

    boolean checkPerform(Long id_task, HttpServletResponse response);

    boolean markComleted(Long id_task, HttpServletResponse response);

    boolean setPerformedStatus(Long id_task, Integer status, HttpServletResponse response);

    String hello(HttpServletResponse response, HttpServletRequest request);
}
