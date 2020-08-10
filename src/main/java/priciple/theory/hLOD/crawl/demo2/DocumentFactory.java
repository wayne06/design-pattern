package priciple.theory.hLOD.crawl.demo2;

public class DocumentFactory {

    private HtmlDownloader downloader;

    public DocumentFactory(HtmlDownloader downloader) {
        this.downloader = downloader;
    }

    public Document createDocument(String url) {
        Html html = downloader.downloadHtml(url);
        return new Document(html, url);
    }
}
