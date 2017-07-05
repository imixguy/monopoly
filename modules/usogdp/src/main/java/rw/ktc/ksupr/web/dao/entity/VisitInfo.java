package rw.ktc.ksupr.web.dao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitry 4ernookiy - 4ernookiy@gmail.com on 08.05.14.
 */
@Entity
@Table(name = "VisitInfo", schema="WEBFACE")
public class VisitInfo {
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginSession;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endSession;
    private String ip;
    private Integer countRequest;
    private String agent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginSession() {
        return beginSession;
    }

    public void setBeginSession(Date beginSession) {
        this.beginSession = beginSession;
    }

    public Date getEndSession() {
        return endSession;
    }

    public void setEndSession(Date endSession) {
        this.endSession = endSession;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void increaseCountRequest() {
        countRequest++;
    }

    public Integer getCountRequest() {
        return countRequest;
    }

    public void setCountRequest(Integer countRequest) {
        this.countRequest = countRequest;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("new visitor - ");
//        sb.append("id=" + id);
        if (null != beginSession) {
            sb.append("begin=" + DATE_FORMAT.format(beginSession));
        }
        if (null != endSession) {
            sb.append(", end=" + DATE_FORMAT.format(endSession));
        }
        sb.append(", countRequest=" + countRequest);
        sb.append(", agent=" + agent);

        return sb.toString();
    }
}
