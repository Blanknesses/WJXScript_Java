public class WJXPostBean {
    //问卷地址
    private static String url = "/joinnew/processjq.ashx";
    //提交类型,默认为1
    private static String submittype = "1";
    //curID,为每个问卷的独特ID
    private String curID;
    //t,一串长度为13的数字,意义未知
    private String t;
    //starttime,发送问卷的时间,转码为URL编码
    private String startTime;
    //ktimes,两位数字,意义不明
    private String ktimes;
    //rn,整数部分长度为10,小数部分长度为8,意义不明
    private String rn;
    //hlv,默认为1
    private static String hlv = "1";
    //jqnonce,意义不明
    private String jqnonce;
    //jqsign,意义不明
    private String jqsign;
    //jpm,意义不明,可能没有
    private String jpm;
    //Http协议版本
    private static String http = "HTTP/1.1";

    //主机地址
    private static String Host = "www.wjx.cn";
    //Connection,默认close
    private static String Connection = "close";
    //用户代理
    private String UserAgent;
    //Content-Type
    private String ContentType;
    //Accept,默认为*/*
    private static String Accept = "*/*";
    //Origin,默认为https://www.wjx.cn
    private static String Origin = "https://www.wjx.cn";

    /**
     * chrome有,firefox没有的部分
     */
    //Sec-Fetch-Site
    private static String SecFetchSite = "same-origin";
    //Sec-Fetch-Mode
    private static String SecFetchMode = "cors";
    //Sec-Fetch-Dest
    private static String SecFetchDest = "empty";
    //Accept-Encoding,默认设置gzip,deflate
    private static String AcceptEncoding = "gzip, deflate";
    //Accept-Language,默认设置zh-CN,zh;q=0.9
    private static String AcceptLanguage = "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2";

    //Referer,为实际问卷URL
    private String Referer;
    //Cookie域
    private String Cookie;
    //data域
    private String submitdata;

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getKtimes() {
        return ktimes;
    }

    public void setKtimes(String ktimes) {
        this.ktimes = ktimes;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getJqnonce() {
        return jqnonce;
    }

    public void setJqnonce(String jqnonce) {
        this.jqnonce = jqnonce;
    }

    public String getJqsign() {
        return jqsign;
    }

    public void setJqsign(String jqsign) {
        this.jqsign = jqsign;
    }

    public String getJpm() {
        return jpm;
    }

    public void setJpm(String jpm) {
        this.jpm = jpm;
    }

    public String getUserAgent() {
        return UserAgent;
    }

    public void setUserAgent(String userAgent) {
        UserAgent = userAgent;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getReferer() {
        return Referer;
    }

    public void setReferer(String referer) {
        Referer = referer;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }

    public String getSubmitdata() {
        return submitdata;
    }

    public void setSubmitdata(String submitdata) {
        this.submitdata = submitdata;
    }

    public static String getUrl() {
        return url;
    }

    public static String getSubmittype() {
        return submittype;
    }

    public static String getHlv() {
        return hlv;
    }

    public static String getHttp() {
        return http;
    }

    public static String getHost() {
        return Host;
    }

    public static String getConnection() {
        return Connection;
    }

    public static String getAccept() {
        return Accept;
    }

    public static String getOrigin() {
        return Origin;
    }

    public static String getSecFetchSite() {
        return SecFetchSite;
    }

    public static String getSecFetchMode() {
        return SecFetchMode;
    }

    public static String getSecFetchDest() {
        return SecFetchDest;
    }

    public static String getAcceptEncoding() {
        return AcceptEncoding;
    }

    public static String getAcceptLanguage() {
        return AcceptLanguage;
    }

    @Override
    public String toString() {
        return "WJXPostBean{" +
                "curID='" + curID + '\'' +
                ", t='" + t + '\'' +
                ", startTime='" + startTime + '\'' +
                ", ktimes='" + ktimes + '\'' +
                ", rn='" + rn + '\'' +
                ", jqnonce='" + jqnonce + '\'' +
                ", jqsign='" + jqsign + '\'' +
                ", jpm='" + jpm + '\'' +
                ", UserAgent='" + UserAgent + '\'' +
                ", ContentType='" + ContentType + '\'' +
                ", Referer='" + Referer + '\'' +
                ", Cookie='" + Cookie + '\'' +
                ", submitdata='" + submitdata + '\'' +
                '}';
    }
}
