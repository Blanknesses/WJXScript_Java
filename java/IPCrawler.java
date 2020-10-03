import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class IPCrawler {
    // 66ip网
    private static String proxyURL = "http://www.66ip.cn/areaindex_";
    private Yaml yaml = new Yaml();

    public List<String> getURL(String[] locations){
        List<String> URLList = new ArrayList<>();
        try (InputStream in = WJXPost.class.getClassLoader().getResourceAsStream("pageInfo.yaml")) {
            Map<String, Integer> map = yaml.loadAs(in, Map.class);
            for (String lo: locations) {
                URLList.add(proxyURL + map.get(lo) + "/1.html");
            }
            return URLList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Integer> getProxy(List<String> URLList){
        Map<String, Integer> proxy = new HashMap<>();
        for (String url: URLList){
            try {
                Document htmlBody = Jsoup.connect(url).get();
                Elements elements = htmlBody.select("td:matchesOwn([\\d.]+?)");
                int temp = 1;
                String ip = "";
                for (Element element: elements){
                    if (temp % 3 == 1)
                        ip = element.ownText();
                    else if (temp % 3 == 2) {
                        proxy.put(ip, Integer.parseInt(element.ownText()));
                    }
                    temp += 1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return proxy;
    }
}
