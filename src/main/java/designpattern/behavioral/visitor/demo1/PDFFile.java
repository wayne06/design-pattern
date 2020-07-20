package designpattern.behavioral.visitor.demo1;

public class PDFFile extends ResourceFile {

    public PDFFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        //省略：从 PDF 中抽取文本

        // 将抽取出的文本保存在跟 filePath 同名的 .txt 文件中

        System.out.println("Extract PDF");
    }
}
