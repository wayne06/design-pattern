package d.designpattern.behavioral.visitor.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式诞生的思维过程：demo1 ~ demo5
 *
 * 假设从网站上爬取了很多资源文件，它们的格式有三种：PDF、PPT、Word。现在要开发一个工具来处理这批资源文件。
 * 这个工具的其中一个功能是，把这些资源文件中的文本内容抽取出来放到 txt 文件中
 *
 * 解决方案：
 * ResourceFile 是一个抽象类，包含一个抽象函数 extract2txt()。PdfFile、PPTFile、WordFile 都继承 ResourceFile 类，并且重写了 extract2txt() 函数。
 * 在 ToolApplication 中，可以利用多态特性，根据对象的实际类型，来决定执行哪个方法
 *
 * 存在问题：
 * 如果工具的功能不停地扩展，不仅要能抽取文本内容，还要支持压缩、提取文件元信息（文件名、大小、更新时间等等）构建索引等一系列的功能，
 * 那如果继续按照上面的实现思路，就会存在这样几个问题：
 * 1. 违背开闭原则，添加一个新的功能，所有类的代码都要修改；
 * 2. 虽然功能增多，每个类的代码都不断膨胀，可读性和可维护性都变差了；
 * 3. 把所有比较上层的业务逻辑都耦合到 PdfFile、PPTFile、WordFile 类中，导致这些类的职责不够单一，变成了大杂烩
 *
 * 解决方案：
 * 拆分解耦，把业务操作跟具体的数据结构解耦，设计成独立的类，见 demo2
 */
public class ToolApplication {

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles("F:\\日语\\N3真题\\201307");
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.extract2txt();
        }
    }

    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PDFFile/PPTFile/WordFile)
        resourceFiles.add(new PDFFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PPTFile("c.ppt"));
        return resourceFiles;
    }

}
