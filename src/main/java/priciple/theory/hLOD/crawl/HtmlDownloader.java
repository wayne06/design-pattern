package priciple.theory.hLOD.crawl;

public class HtmlDownloader {

    private NetworkTransporter transporter;

    public Html downloadHtml(String url) {
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        return new Html(rawHtml);
    }

}
