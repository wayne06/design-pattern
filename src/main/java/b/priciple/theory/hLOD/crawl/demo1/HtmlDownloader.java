package b.priciple.theory.hLOD.crawl.demo1;

/**
 * 通过 URL 获取网页
 */
public class HtmlDownloader {

    private NetworkTransporter transporter;

    public Html downloadHtml(String url) {
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        return new Html(rawHtml);
    }

}
