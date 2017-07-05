package by.nulay.changer.ordertask;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by miha on 14.09.2015.
 */
@Entity
@Table(name = "ordertasks")
public class OrderTask {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100)
    private String name;//название
    @Column(name = "sexexecutor", length = 4)
    private Integer sexexecutor;//пол исполнителя 0-не важно, 1-муж, 2-жен
    @Column(name = "summExecute")
    private Integer summExecute;//сумма денег на данное задание
    @Column(name = "content", length = 1000)
    private String content;//содержимое которое нужно выполнить
    @Column(name = "pageName", length = 100)
    private String pageName;//страница на которой это нужно выполнить
    @Column(name = "datestartexecution")
    private Date dateStartExecution;//дата начала выполнения
    @Column(name = "dateendexecution")
    private Date dateEndExecution;//дата завершения выполнения
    private Integer countExec;//количество выполнивших
    private Integer countGetTask;//количество взявших задание но еще не выполневших


}
