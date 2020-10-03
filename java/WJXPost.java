import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class WJXPost {
    private WJXPostBean postBean;
    private Yaml yaml = new Yaml();
    private int maxPost;
    private int currentPost = 0;
    private ExecutorService fixedThreadPool;

    public void postInit() {
        try (InputStream in = WJXPost.class.getClassLoader().getResourceAsStream("postConfig.yaml")){
            postBean = new WJXPostBean();
            Map<String, String> map = yaml.loadAs(in, Map.class);
            postBean.setCurID(map.get("curID"));
            postBean.setT(map.get("t"));
            postBean.setStartTime(map.get("starttime"));
            postBean.setKtimes(map.get("ktimes"));
            postBean.setRn(map.get("rn"));
            postBean.setJqnonce(map.get("jqnonce"));
            postBean.setJqsign(map.get("jqsign"));
            postBean.setJpm(map.get("jpm"));
            postBean.setUserAgent(map.get("User-Agent"));
            postBean.setContentType(map.get("Content-Type"));
            postBean.setReferer(map.get("Referer"));
            postBean.setCookie(map.get("Cookie"));
            postBean.setSubmitdata(map.get("submitdata"));

        } catch (IOException e) {
            System.out.println("配置文件读取错误");
        }
    }

    public boolean post(String IP, int port) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHost proxy = new HttpHost(IP, port);
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.wjx.cn")
                    .setPath(postBean.getUrl())
                    .setParameter("submittype", postBean.getSubmittype())
                    .setParameter("curID", postBean.getCurID())
                    .setParameter("t", postBean.getT())
                    .setParameter("starttime", URLDecoder.decode(postBean.getStartTime(), "UTF-8"))
                    .setParameter("ktimes", postBean.getKtimes())
                    .setParameter("rn", postBean.getRn())
                    .setParameter("hlv", postBean.getHlv())
                    .setParameter("jqnonce", postBean.getJqnonce())
                    .setParameter("jqsign", URLDecoder.decode(postBean.getJqsign(), "UTF-8"))
                    .setParameter("jpm", postBean.getJpm())
                    .build();
            HttpPost post = new HttpPost(uri);
            post.setProtocolVersion(HttpVersion.HTTP_1_1);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(5000)
                    .build();

            post.setConfig(requestConfig);

            //Header
            post.setHeader("Host", postBean.getHost());
            post.setHeader("User-Agent", postBean.getUserAgent());
            post.setHeader("Accept", postBean.getAccept());
            post.setHeader("Accept-Language", postBean.getAcceptLanguage());
            post.setHeader("Accept-Encoding", postBean.getAcceptEncoding());
            post.setHeader("Content-Type", postBean.getContentType());
            post.setHeader("Origin", postBean.getOrigin());
            post.setHeader("Connection", postBean.getConnection());
            post.setHeader("Referer", postBean.getReferer());
            post.setHeader("Cookie", postBean.getCookie());
            //Entity
            post.setEntity(new StringEntity("submitdata=" + postBean.getSubmitdata()));


            CloseableHttpResponse response = httpClient.execute(post);
            Document document = Jsoup.parse(EntityUtils.toString(response.getEntity()));
            System.out.println(document.body().ownText());
            if (document.body().ownText().matches("10.*")) {
                System.out.println(IP + ":" + port);
                return true;
            }
            else
                return false;
        } catch (URISyntaxException e) {
            System.out.println("URI错误");
            return false;
        } catch (ClientProtocolException e) {
            System.out.println("客户端协议错误");
            return false;
        } catch (IOException e) {
            System.out.println("IO错误");
            return false;
        }
    }

    public void fixThreadPool(int maxPost, int poolSize, Map<String, Integer> proxySet){
        this.fixedThreadPool = Executors.newFixedThreadPool(poolSize);
        this.maxPost = maxPost;

        Iterator<Map.Entry<String, Integer>> entries = proxySet.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, Integer> entry = entries.next();
            String IP = entry.getKey();
            int port = entry.getValue();
            fixedThreadPool.execute(()->{
                System.out.println(Thread.currentThread());
                while (post(IP, port)){
                    try {
                        updtaeCurrentPost();
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public synchronized void updtaeCurrentPost() {
        currentPost += 1;
        System.out.println(currentPost);
        if (currentPost >= maxPost)
            exit(0);
    }
}