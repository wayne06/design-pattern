package d.designpattern.behavioral.visitor.demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对重载函数编译不通过的问题，解决方案如下：
 *
 * 在执行 resourceFile.accept(extractor) 的时候，根据多态特性，程序会调用实际类型的 accept 函数，比如 PdfFile 的 accept 函数，也就是 extractor.extract2txt(this)
 * 而其中的 this 类型是 PdfFile 的，在编译的时候就确定了，所以会调用 extractor 的 extract2txt(PdfFile pdfFile) 这个重载函数。
 *
 * 这个实现思路的技巧，是理解访问者模式的关键所在
 *
 * 如果继续添加新功能，代码见 demo4
 */
public class ToolApplication {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles("F:\\日语\\N3真题\\201307");
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PDFFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PptFile("c.ppt"));
        return resourceFiles;
    }

}
