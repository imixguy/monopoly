package by.nulay.changer.task;

import java.util.List;

/**
 * Created by miha on 22.10.2014.
 */
public interface TaskService<T extends Task, ID extends Number> {
    /**
     * Метод возвращающий список заданий
     * @param perform - возвращать выполненные или нет, если null - то все
     * @param executor  - для конкретного исполнителя, если null - то для всех
     * @return список заданий
     */
    List<T> getTasks(Boolean perform, String executor);
    /**
     * Метод добавляет или изменяет задание
     * @param task - задание которое нужно выполнить или изменить
     * @return задание которое нужно выполнить или изменить
     */
    T addOrChangeTask(T task);
    /**
     * Метод возвращающее следующее задание для конкретного исполнителя
     * @param executor - исполнитель
     * @return следующее задание которое нужно выполнить
     */
    T getNextTask(String executor);
    /**
     * Метод помечает ззадание , как выполненое
     * @param task - выполненное задание
     * @return записалось или нет
     */
    boolean checkPerform(T task);
    boolean checkPerform(ID job_id);

    /**
     * Метод помечает ззадание , как выполненое
     * @param task - выполненное задание
     * @return записалось или нет
     */
    boolean markComleted(T task);
    boolean markComleted(ID job_id);

    Task getTaskForPage(String pageName, String executor);
    /**
     * Метод устанавливает в задание статус
     * @param id_job -  id задания
     * @param status -  статус
     * @return записалось или нет
     */
    boolean setPerformedStatus(ID id_job,Integer status);

    /**
     * Method return task use criteria
     * @param criterion criteria for search tasks
     * @return list task
     */
    List<T> getTasksForCriterion(CriterionTask criterion);
}
