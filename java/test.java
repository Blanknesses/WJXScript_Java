public class test {
    public static void main(String[] args) {
        IPCrawler ipCrawler = new IPCrawler();
        String[] locations = {"上海", "江苏", "浙江", "湖南", "广西", "天津", "重庆", "河北", "山西", "辽宁", "吉林"};

        WJXPost post = new WJXPost();
        post.postInit();
        post.fixThreadPool(20, 15, ipCrawler.getProxy(ipCrawler.getURL(locations)));
    }
}
