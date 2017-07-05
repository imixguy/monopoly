package by.nulay.changer.parser;

import by.nulay.changer.vk.FilmTake;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * Created by miha on 23.01.2016.
 */
@Component("SerialochkaParser")
public class SerialochkaParser  extends ParserImpl{
    private static Logger log= Logger.getLogger(SerialochkaParser.class);


    public SerialochkaParser(){
    }

    private String urlSite="http://serialochka.ru/";

    public List<FilmTake> startParse() throws IOException {

        Document doc = Jsoup.connect(urlSite).get();
        Elements newsHeadlines = doc.select(".mainprasp tr.serin");
        List<FilmTake> listFilmTake=new ArrayList<FilmTake>();
        Map<String,FilmTake> hrefs = new HashMap<String,FilmTake>();
        for(Element el:newsHeadlines) {
            FilmTake filmTakeF = new FilmTake();
            filmTakeF.setName((el.select(".serinf").text()+" "+el.select(".rsdtv").text()).trim());
            filmTakeF.setDiscription(doc.select(".mainprasp h3.tb").text());
            filmTakeF.setDiscription(filmTakeF.getDiscription().substring(0,filmTakeF.getDiscription().length()-35));
            hrefs.put(el.select(".serinf a").attr("href"),filmTakeF);
        }
        for(String els:hrefs.keySet()){
            Document docS = Jsoup.connect(urlSite+els).get();
            Elements oneF = docS.select(".serialinfo");
            String res=oneF.text();
            res=res.replaceAll("Рейтинг Kinopoisk - Р","Р");
            res=res.replaceAll("Рейтинг ImDb - М","М");
            if(res.endsWith("Место в топе -"))
                res=res.replaceAll("Место в топе -","");
            Elements opisser = docS.select(".opisser");
            String descr=opisser.text();
            descr=descr.replaceAll("Наша рецензия","Рецензия");

//            String name = docS.select(".filmfull h1[itemprop=name]").text()+" "+
//                    docS.select(".filmfull .engname").text()+" "+
//                    docS.select(".filmfull .atitle").text();
            String img="http://serialochka.ru"+docS.select(".filmfullimg img").attr("src");

            FilmTake filmTakeF=hrefs.get(els);
            filmTakeF.setFilm(res);
            //filmTakeF.setName(hrefs.get(els).trim());
            filmTakeF.setDiscription(filmTakeF.getDiscription()+" "+descr);
            filmTakeF.setImg(img);
            filmTakeF.setSight("http://serialochka.ru/");
            filmTakeF.setDateCreate(new Date());
            listFilmTake.add(filmTakeF);

        }
        return listFilmTake;
    }
}
