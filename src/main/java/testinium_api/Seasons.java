package testinium_api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seasons {
    private String season1;
    private String url1;
    private String season2;
    private String url2;

    public String getSeason1() {
        return season1;
    }

    public void setSeason1(String season1) {
        this.season1 = season1;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getSeason2() {
        return season2;
    }

    public void setSeason2(String season2) {
        this.season2 = season2;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public Seasons(String season1, String url1, String season2, String url2) {
        this.season1 = season1;
        this.url1 = url1;
        this.season2 = season2;
        this.url2 = url2;
    }
}
