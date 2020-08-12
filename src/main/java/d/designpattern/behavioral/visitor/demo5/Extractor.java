package d.designpattern.behavioral.visitor.demo5;

public class Extractor implements Visitor {

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println("Extract Word");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println("Extract Pdf");
    }

    @Override
    public void visit(PptFile pptFile) {
        //...
        System.out.println("Extract PPT");
    }

    //public void extract2txt(PptFile pptFile) {
    //    //...
    //    System.out.println("Extract PPT");
    //}
    //
    //public void extract2txt(PdfFile pdfFile) {
    //    //...
    //    System.out.println("Extract PDF");
    //}
    //
    //public void extract2txt(WordFile wordFile) {
    //    //...
    //    System.out.println("Extract Word");
    //}

}
