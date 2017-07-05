package by.imix.cms.redirect;

import java.util.List;

/**
 * Created by miha on 03.11.2014.
 */
public interface RedirectViewService{
    List<RedirectView> getRedirectAll();
    RedirectView checkRedirect(String requestURI);
    void updateRedirectList();
}
