package by.nulay.changer.films;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 19.10.14
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */

@Service("parseSerial")
public class ParseSerialImpl implements ParseSerial {
    private static Logger log=Logger.getLogger(ParseSerialImpl.class);
    {
        swithAuthenticator("miha","951");
        switchProxy("10.4.253.70","3128");
    }
    public static void main(String str[]){
        String se="asaf asdf asdf dfsafdasdf_sfsdf_,. 8*er";
        Pattern p=Pattern.compile("</?i>");
        se=se.replaceAll("[^A-Za-zА-Яа-я_\\d]","") ;
        System.out.print(se);
    }

    @Override
    public List<Film> parseListFilm(String rootUrl, String url, FilmService filmService) throws IOException{
        List<Film> films=new ArrayList<Film>();
        Document doc = null;
        try {
            doc = Jsoup.connect(rootUrl + url).timeout(10000).get();
        } catch (Exception e) {
            try {
                Thread.sleep(1000*20);
            } catch (InterruptedException e1) {}
            parseListFilm(rootUrl,url,filmService);
        }
        try {
            Elements newsHeadlines = doc.select("#schedule-list tbody tr");
            for(int i=0;i<newsHeadlines.size();i++){
                obrFilm(newsHeadlines.get(i), rootUrl, filmService, films);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(1000*20);
            } catch (InterruptedException e1) {}
            parseListFilm(rootUrl,url,filmService);

        }
        return films;
    }

    private void obrFilm(Element newsHeadlines,String rootUrl,FilmService filmService,List<Film> films){
        try {
            Element td=newsHeadlines.select("td").get(1);
            String name=td.select(".title").text();
            String year=td.select(".year").text();
            year=year.substring(1,5);
//            (2012 – ...)  (2012 – 2013) (2012)
            String yearEnd=td.select(".year").text();
            if(yearEnd.length()>8 && yearEnd.indexOf("...")<0){
                yearEnd=yearEnd.substring(8,12);
            }else{
                yearEnd=null;
            }
            Film f= null;

            f = filmService.getFilm(name,year);

            if (f == null) {
                Element link = newsHeadlines.select("a").get(0);
                f = parseFilm(rootUrl, link.attr("href"));
                f =saveNewFilm(f,filmService);
            }
            if(f.getStatus()==null) {
                f.setDateEnd(yearEnd);
                if (f.getCountTime().indexOf("мин") > -1) {
                    f.setCountTime(f.getCountTime().substring(0, f.getCountTime().indexOf("м") - 1).trim());
                }
                Elements elDI = td.select(".ser-info div");
                f.setCountSezon(Integer.parseInt(elDI.get(0).select("a").text()));
                String status = elDI.get(1).textNodes().get(0).getWholeText().trim();
                if (status.indexOf("«") >= 0) {
                    status = status.replace(" «", "/");
                    status = status.replace("»", "");
                }
                if(status.equals("Решается дальнейшая судьба проекта")){
                    status="Решается судьба";
                }
                f.setStatus(status);
                f.setTransliterate(trLit(f.getName()));

                films.add(f);
                filmService.addFilm(f);
                String fName=(f.getTransliterate().length()>15)?f.getTransliterate().substring(0,15):f.getTransliterate();
                fName=fName+"_"+f.getDateBegin();
                saveFile2(newsHeadlines.select("td").get(0).select("img").attr("abs:src"), fName, false);
                saveFile2(newsHeadlines.select("td").get(0).select("img").attr("abs:src").replace("/schedule/", "/scheldule/"), fName, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String trLit(String name){
        String mapEn[]={"e","u","ya","shh","ch","sh","h","zh","a","b","v","g","d","e","e","z","i","j","k","l","m",
                "n","o","p","r","s","t","u","f","c","b","b1","b"};
        String mapRu[]={"э","ю","я","щ","ч","ш","х","ж","а","б","в","г","д","е","ё","з","и","й","к","л","м",
                "н","о","п","р","с","т","у","ф","ц","ъ","ы","ь"};
        // Функция транслитерации.


        List<String> ls = new ArrayList<String>();
        StringTokenizer st=new StringTokenizer(name);
        while (st.hasMoreTokens()) {
            ls.add(st.nextToken());
        }
        String se="";
        for(String s:ls) {
            s=s.toLowerCase();
            for (int i = 0; i < mapRu.length; i++) {
                while (s.indexOf(mapRu[i]) >= 0) {
                    s=s.replace(mapRu[i], mapEn[i]);
                }
            }
            se +="_"+s;
        }
        se=se.replaceAll("[^A-Za-zА-Яа-я_\\d]","");
        return se.substring(1,se.length());

    }

    public void switchProxy(String proxy,String port){
        try {
            System.setProperty("http.proxyHost", proxy);
            System.setProperty("http.proxyPort", port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void swithAuthenticator(final String login, final String password){
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password.toCharArray());
            }
        });
    }

    @Override
    public Film parseFilm(String rootUrl, String url) throws IOException {

        Film film=new Film();
        Document doc = Jsoup.connect(rootUrl+url).get();
        Elements span = doc.select("h1.title-basic span");
        film.setName(span.get(0).text());
        film.setAlternativeName(span.get(1).text());
        film.setDateBegin(span.get(2).text().substring(2,6));
        try {
            String dE = span.get(2).text().substring(9, 13);
            film.setDateEnd((dE.equals("...)")) ? null : dE);
        }catch (StringIndexOutOfBoundsException e){
            film.setDateEnd(film.getDateBegin());
        }
        String g="";
        Elements el2=doc.select("div.second-part-info a");
        for(int i=0;i<el2.size();i++){
            g+=el2.get(i).text()+"|";
        }
        if(!g.equals("")) {
            film.setGanr(g.substring(0, g.length() - 1));
        }
        String t=doc.select("div.second-part-info").text();
        film.setCountTime(t.substring(t.lastIndexOf("-")+1,t.length()).trim());
        film.setRaiting(doc.select("#ratStat").text());
        film.setDescription(doc.select("p.body_large.summary").text());

        Elements season=doc.select("[id=full-season]");
        List<List<Seria>> sez=new ArrayList<List<Seria>>();
        for(int i=season.size()-1;i>=0;i--){
            sez.add(parseSeson(season.get(i)));
        }
        film.setListSezon(sez);
        return film;
    }

    private List<Seria> parseSeson(Element elS){
        List<Seria> s=new ArrayList<Seria>();
        Elements elSer=elS.select("#num-season tr");
        for(int i=0;i<elSer.size();i++){
            Element trEl=elSer.get(i);
            Seria seria=new Seria();
            String sdf=trEl.select("td").get(0).text();
            seria.setSezon(Integer.parseInt(sdf.substring(0,sdf.indexOf("x")).trim()));
            seria.setSeria(Integer.parseInt(sdf.substring(sdf.indexOf("x") + 1, sdf.length()).trim()));
            seria.setName(trEl.select("td").get(1).select("b").text());
            seria.setAlternativeName(trEl.select("td").get(1).select("span").text());

            seria.setDateEntered(trEl.select("td").get(3).text());
            s.add(seria);
        }
        return s;
    }

    private int fullCount=65;
    private int num;
    private boolean parseComplete=true;
    @Override
    public void startParse(FilmService filmService) throws IOException{
        if(!parseComplete) return;
        num=64;
        parseComplete=false;
        while(num<fullCount) {
            List<Film> films = parseListFilm("http://www.toramp.com/", "schedule.php?page="+num,filmService);
            num+=1;
            try {
                Thread.sleep(1000*60*3);
            } catch (InterruptedException e) {
                log.error("thred dead");
            }
        }
        parseComplete=true;
    }

    @Override
    public Film saveNewFilm(Film f, FilmService filmService){

        f = filmService.addFilm(f);
        for (List<Seria> sezon : f.getListSezon()) {
            for (Seria s : sezon) {
                s.setId_film(f.getId());
                try {
                    s = filmService.addSeria(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return f;
    }

    public int getPercentComplete(){
        return (int) Math.ceil((Double.parseDouble(""+100)/Double.parseDouble(""+fullCount))*num);
    }

    @Override
    public boolean isParseComplete() {
        return parseComplete;
    }

    private int numimP=0;
    private boolean parseImComplete=true;
    @Override
    public void startParseImage(FilmService filmService) throws IOException {
        if (!parseImComplete) return;
        numimP = 40;
        parseImComplete = false;
        while(numimP<fullCount) {
            List<Film> films = parseListFilm("http://www.toramp.com/", "schedule.php?page="+numimP,filmService);
            numimP+=1;
        }
    }

    private void saveFile(String urlF,String nameImage){
        try {
            URL url = new URL(urlF);
            BufferedImage image = ImageIO.read(url);
            String typ = "jpg";
            File f1 = new File("resources".concat(File.separator).concat("images").concat(File.separator).concat("posters")
                    .concat(File.separator).concat(nameImage).concat(".").concat(typ) );
            ImageIO.write(image, typ, f1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveFile2(String urlF, String nameImage, boolean big) throws IOException{
        InputStream inStream = getConnection(urlF).getInputStream();

        File outFile  = new File(File.separator.concat("resources").concat(File.separator).concat("images").
                concat(File.separator).concat((big)?"bigposters":"posters")
                .concat(File.separator).concat(nameImage).concat(".jpg"));
        FileOutputStream outStream = new FileOutputStream(outFile);

        int c;
        while ((c = inStream.read()) != -1)
        { outStream.write(c); }
        inStream.close();
        outStream.close();
        return true;
    }

    private String USERAGENT_FIREFOX_5_MAC="Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0";
    private HttpURLConnection getConnection(String url) throws IOException{
        HttpURLConnection connection;
        URL server = new URL(url);
        connection = (HttpURLConnection)server.openConnection();
//        (HttpURLConnection)server.
        connection.setRequestProperty("User-agent", USERAGENT_FIREFOX_5_MAC);
        connection.setDoOutput(true);
        connection.setReadTimeout(10000);
        connection.connect();
        return connection;
    }
}
