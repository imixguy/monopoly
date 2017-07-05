package by.nulay.changer.task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by miha on 22.10.2014.
 */
@Entity
@Table(name = "tasks")
public class Task implements Serializable,Cloneable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;//название
    @Column(name = "executor", length = 100)
    private String executor;//исполнитель
    @Column(name = "content", length = 1000)
    private String content;//содержимое которое нужно выполнить
    @Column(name = "pageName", length = 255)
    private String pageName;//страница на которой это нужно выполнить
    @Column(name = "dateexecution")
    private Date dateexecution;//дата выполнения
    private Boolean performed=false;//выполнено
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

    public String getAdditionalData() {return additionalData;}

    public void setAdditionalData(String additionalData) {this.additionalData = additionalData;}
}