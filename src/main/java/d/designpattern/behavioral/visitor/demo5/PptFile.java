package d.designpattern.behavioral.visitor.demo5;

public class PptFile extends ResourceFile {
    public PptFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    //@Override
    //public void accept(Extractor extractor) {
    //    extractor.extract2txt(this);
    //}
    //
    //@Override
    //public void accept(Compressor compressor) {
    //    compressor.compress(this);
    //}
}
