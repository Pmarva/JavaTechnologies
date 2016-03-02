package homeWork4;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Marva on 28.02.2016.
 */
public class HomeWork4 {
    public static void main(String[] args) {

        JSONObject obj = getNews();
        System.out.print(obj);

        try {
            FileWriter fw = new FileWriter("uudised.txt");
            fw.write(obj.toString());
            fw.flush();
            fw.close();
            System.out.println("Edukalt kirjutatud faili");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getNews() {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String time = null;
        try {
            Document document = Jsoup.connect("http://www.omasaar.ee/feed/").parser(Parser.xmlParser()).get();
            Elements elements = document.select("item");
            time = document.select("lastBuildDate").text();

            for (Element element : elements) {
                JSONObject news = new JSONObject();

                String title = element.select("title").text();
                String link = element.select("link").text();
                String publishTime = element.select("pubDate").text();

                news.put("title", title);
                news.put("link", link);
                news.put("publishTime", publishTime);
                array.put(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        obj.put("lastUpdate",time);
        obj.put("news", array);
        return obj;
    }
}
