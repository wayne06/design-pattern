package priciple.theory.hLOD.crawl.demo2;

public class HtmlDownloader {

    private NetworkTransporter transporter;

    public Html downloadHtml(String url) {
        HtmlRequest htmlRequest = new HtmlRequest(url);
        Byte[] rawHtml = transporter.send(htmlRequest.getAddress(), htmlRequest.getContent().getBytes());
        return new Html(rawHtml);
    }

}
