package by.nulay.changer.task;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by miha on 10.05.2016.
 */
public class CriterionTask {
    private Long id;
    private String name;//название
    private String executor;//исполнитель
    private String content;//содержимое которое нужно выполнить
    private String pageName;//страница на которой это нужно выполнить
    private Date dateexecution;//дата выполнения
    private Boolean performed;//выполнено
    private Integer performedStatus;//see TaskStatus 0-создано,1-выполнено,2-проверено выполнение
    private String additionalData;//have posibility save in JSON

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Date getDateexecution() {
        return dateexecution;
    }

    public void setDateexecution(Date dateexecution) {
        this.dateexecution = dateexecution;
    }

    public Boolean getPerformed() {
        return performed;
    }

    public void setPerformed(Boolean performed) {
        this.performed = performed;
    }

    public Integer getPerformedStatus() {
        return performedStatus;
    }

    public void setPerformedStatus(Integer performedStatus) {
        this.performedStatus = performedStatus;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}
