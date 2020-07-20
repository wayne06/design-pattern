package designpattern.behavioral.visitor.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对 demo1 存在的问题，解决方案如下：
 * 拆分解耦，把业务操作跟具体的数据结构解耦，设计成独立的类
 * 其中最关键的一点设计是，把抽取文本内容的操作，设计成了三个重载函数。
 * 函数重载是 Java、C++ 这类面向对象编程语言中常见的语法机制。
 * 所谓重载函数是指，在同一类中函数名相同、参数不同的一组函数
 *
 * 该解决方案存在的问题在于：extractor.extract2txt(resourceFile) 会编译不通过
 *
 * 多态是一种动态绑定，可以在运行时获取对象的实际类型，来运行实际类型对应的方法。
 * 而函数重载是一种静态绑定，在编译时并不能获取对象的实际类型，而是根据声明类型执行声明类型对应的方法。
 * 在代码中，resourceFiles 包含的对象的声明类型都是 ResourceFile，而我们并没有在 Extractor 类中定义参数类型是 ResourceFile 的 extract2txt() 重载函数，
 * 所以在编译阶段就通过不了，更别说在运行时根据对象的实际类型执行不同的重载函数了
 *
 * 解决方案见 demo3
 *
 */
public class ToolApplication {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile> resourceFiles = listAllResourceFiles("F:\\日语\\N3真题\\201307");
        for (ResourceFile resourceFile : resourceFiles) {
            //extractor.extract2txt(resourceFile);  // 编译不通过
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirector) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PDFFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PptFile("c.ppt"));
        return resourceFiles;
    }
}
