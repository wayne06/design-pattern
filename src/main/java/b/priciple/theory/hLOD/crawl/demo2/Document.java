package b.priciple.theory.hLOD.crawl.demo2;

public class Document {

    private Html html;
    private String url;

    public Document(Html html, String url) {
        this.html = html;
        this.url = url;
    }

    public Html getHtml() {
        return html;
    }

    public String getUrl() {
        return url;
    }
}
