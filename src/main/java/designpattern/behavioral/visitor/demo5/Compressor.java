package designpattern.behavioral.visitor.demo5;

public class Compressor implements Visitor {

    //public void compress(PptFile pptFile) {
    //    //...
    //    System.out.println("Compress PPT");
    //}
    //
    //public void compress(PdfFile pdfFile) {
    //    //...
    //    System.out.println("Compress PDF");
    //}
    //
    //public void compress(WordFile wordFile) {
    //    //...
    //    System.out.println("Compress Word");
    //}

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println("Compress Word");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println("Compress Pdf");
    }

    @Override
    public void visit(PptFile pptFile) {
        //...
        System.out.println("Compress PPT");
    }
}
