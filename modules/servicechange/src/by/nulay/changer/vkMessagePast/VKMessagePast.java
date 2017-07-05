package by.nulay.changer.vkMessagePast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by miha on 20.12.2015.
 */
public class VKMessagePast {
    public  static void main() {
    }

    private String getToken(String client_id,String scope,String redirect_uri,String display,String response_type,String email,String pass) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
// Делаем первый запрос
            HttpPost post = new HttpPost("http://oauth.vk.com/authorize?" +
                    "client_id="+client_id+
                    "&scope="+scope+
                    "&redirect_uri="+redirect_uri+
                    "&display="+display+
                    "&response_type="+response_type);
            HttpResponse response;
            response = httpClient.execute(post);
            post.abort();
//Получаем редирект
            String HeaderLocation = response.getFirstHeader("location").getValue();
            URI RedirectUri = new URI(HeaderLocation);
//Для запроса авторизации необходимо два параметра полученных в первом запросе
//ip_h и to_h
            String ip_h= RedirectUri.getQuery().split("&")[2].split("=")[1];
            String to_h=RedirectUri.getQuery().split("&")[4].split("=")[1];
// Делаем запрос авторизации
            post = new HttpPost("https://login.vk.com/?act=login&soft=1"+
                    "&q=1"+
                    "&ip_h="+ip_h+
                    "&from_host=oauth.vk.com"+
                    "&to="+to_h+
                    "&expire=0"+
                    "&email="+email+
                    "&pass="+pass);
            response = httpClient.execute(post);
            post.abort();
// Получили редирект на подтверждение требований приложения
            HeaderLocation = response.getFirstHeader("location").getValue();
            post = new HttpPost(HeaderLocation);
// Проходим по нему
            response = httpClient.execute(post);
            post.abort();
// Теперь последний редирект на получение токена
            HeaderLocation = response.getFirstHeader("location").getValue();
// Проходим по нему
            post = new HttpPost(HeaderLocation);
            response = httpClient.execute(post);
            post.abort();
// Теперь в след редиректе необходимый токен
            HeaderLocation = response.getFirstHeader("location").getValue();
// Просто спарсим его сплитами
            return HeaderLocation.split("#")[1].split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}

