package by.imix.cms.redirect;

import javax.persistence.Transient;
import java.util.regex.Pattern;

/**
 * Created by miha on 03.11.2014.
 */

public class RedirectView{
    private String url;
    private Long id_nodeView;
    @Transient
    private Pattern pattern;

    public RedirectView() {
    }

    public RedirectView(String url, Long id_nodeview) {
        setUrl(url);
        this.id_nodeView = id_nodeview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        pattern=Pattern.compile(url);
    }

    public Long getId_nodeView() {
        return id_nodeView;
    }

    public void setId_nodeView(Long id_nodeView) {
        this.id_nodeView = id_nodeView;
    }
//    public void setId_nodeview(Long id_nodeView) {
//        this.id_nodeView = id_nodeView;
//    }
    //todo use Pattern
    public boolean checkURL(String urlR){
        return urlR.matches(this.url);
    }
}
