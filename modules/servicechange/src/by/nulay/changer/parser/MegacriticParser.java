package by.nulay.changer.parser;

import by.nulay.changer.vk.FilmTake;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miha on 19.01.2016.
 * Class parser megacritic site for select new Films
 */
@Component("MegacriticParser")
public class MegacriticParser extends ParserImpl{
    private static Logger log= Logger.getLogger(MegacriticParser.class);

    public MegacriticParser(){
    }

    private String urlSite="http://www.megacritic.ru/novye-filmy.html";

    public List<FilmTake> startParse() throws IOException {

        Document doc = Jsoup.connect(urlSite).timeout(10*1000).get();
        Elements newsHeadlines = doc.select(".jr_blogview .listItem");
        List<FilmTake> listFilmTake=new ArrayList<FilmTake>();
        for(Element el:newsHeadlines){
            String name=el.select(".contentTitle").text();
            String img=el.select(".contentThumbnail img").attr("src");
            String film=el.select(".jr_customFields").text();
            String discription=el.select(".contentIntrotext").text();

            FilmTake filmTakeF = new FilmTake();
            filmTakeF.setFilm(film);
            filmTakeF.setName(name);
            filmTakeF.setDiscription(discription);
            filmTakeF.setImg(img);
            filmTakeF.setSight("http://www.megacritic.ru/");
            filmTakeF.setDateCreate(new Date());
            listFilmTake.add(filmTakeF);
        }
        return listFilmTake;
    }
}
