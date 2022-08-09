import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;

public class Parser {

    public static HashMap<String, String> industriesOnYahoo = new HashMap<>();
    public static HashMap<String, String> industriesOnIndeed = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String url = "https://www.indeed.com/cmp/Alphabet";
        if (url.matches(".*\\b" + "yahoo" + "\\b.*")) {
            System.out.println(getTitleFromYahooFinance(url));
            System.out.println(getIndustryFromYahooFinance(url));
        } else if (url.matches(".*\\b" + "indeed" + "\\b.*")) {
            System.out.println(getTitleFromIndeed(url));
            System.out.println(getIndustryFromIndeed(url));
        }
        System.out.println("industriesOnYahoo: " + industriesOnYahoo + "\nindustriesOnIndeed: " + industriesOnIndeed);
    }

    public static String getTitleFromYahooFinance(String url) throws IOException {
        var document = Jsoup.connect(url).get();
        var title = document.select("title").text();
        title = title.split(" Company")[0];
        return title;
    }

    public static String getIndustryFromYahooFinance(String url) throws IOException {
        var document = Jsoup.connect(url).get();
        var industry = document.select("span[class=Fw(600)]").get(1).text();
        industriesOnYahoo.put(getTitleFromYahooFinance(url), industry);
        return industry;
    }

    public static String getTitleFromIndeed(String url) throws IOException {
        var document = Jsoup.connect(url).get();
        var title = document.select("title").text();
        return title.split(" Careers")[0];
    }

    public static String getIndustryFromIndeed(String url) throws IOException {
        var document = Jsoup.parse(url);
        var industry = document.attr("div[class=css-1w0iwyp e1wnkr790]");
        industriesOnIndeed.put(getTitleFromIndeed(url), industry);
        return industry;
    }
}
