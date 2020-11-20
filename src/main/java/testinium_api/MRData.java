package testinium_api;

public class MRData {
    private String xmlns;
    private String series;
    private String url;
    private String limit;
    private String offset;
    private String total;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "MRData{" +
                "xmlns='" + xmlns + '\'' +
                ", series='" + series + '\'' +
                ", url='" + url + '\'' +
                ", limit='" + limit + '\'' +
                ", offset='" + offset + '\'' +
                ", total='" + total + '\'' +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public MRData(String xmlns, String series, String url, String limit, String offset, String total) {
        this.xmlns = xmlns;
        this.series = series;
        this.url = url;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }
}
