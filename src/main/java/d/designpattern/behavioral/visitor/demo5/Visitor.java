package d.designpattern.behavioral.visitor.demo5;

public interface Visitor {

    void visit(WordFile wordFile);

    void visit(PdfFile pdfFile);

    void visit(PptFile pptFile);

}
