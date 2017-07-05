package rw.ktc.ksupr.web.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.consumer.OAuth2RestTemplate;
import org.springframework.security.oauth2.consumer.token.OAuth2ClientTokenServices;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * Created by sedler on 09.02.15.
 */
@Service
public class OauthServiseImpl implements OauthServise{

    private String cvURL = null;
    private OAuth2RestTemplate restTemplate;
    private OAuth2ClientTokenServices tokenServices;

    @Override
    public String getUserPermission() {
        byte[] content = (getRestTemplate().getForObject(URI.create(cvURL), byte[].class));
        return new String(content);
    }


    public String getCvURL() {
        return cvURL;
    }

    public void setCvURL(String cvURL) {
        this.cvURL = cvURL;
    }

    public OAuth2RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OAuth2ClientTokenServices getTokenServices() {
        return tokenServices;
    }

    public void setTokenServices(OAuth2ClientTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }
}
