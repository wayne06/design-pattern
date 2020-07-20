package designpattern.behavioral.visitor.demo7;

import designpattern.behavioral.visitor.demo7.extractor.Extractor;
import designpattern.behavioral.visitor.demo7.factory.ExtractorFactory;
import designpattern.behavioral.visitor.demo7.file.PdfFile;
import designpattern.behavioral.visitor.demo7.file.PptFile;
import designpattern.behavioral.visitor.demo7.file.ResourceFile;
import designpattern.behavioral.visitor.demo7.file.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例：
 * 有很多资源文件，格式有三种：PDF、PPT、Word。需要开发一个工具来处理这批资源文件：包含抽取文本、压缩文件、提取文件元信息等
 *
 * 方案：
 * demo5 中使用了观察者模式解决，demo7 使用工厂模式来实现：
 * 定义一个包含 extract2txt() 接口函数的 Extractor 接口。PdfExtractor、PPTExtractor、WordExtractor 类实现 Extractor 接口，
 * 并且在各自的 extract2txt() 函数中，分别实现 Pdf、PPT、Word 格式文件的文本内容抽取。
 * ExtractorFactory 工厂类根据不同的文件类型，返回不同的 Extractor
 *
 * 需要添加新的功能的时候，比如压缩资源文件，类似抽取文本内容功能的代码实现，只需要添加一个 Compressor 接口，和，
 * PdfCompressor、PPTCompressor、WordCompressor 三个实现类，以及创建它们的 CompressorFactory 工厂类即可。
 * 唯一需要修改的只有最上层的 ToolApplication 类。基本上符合“对扩展开放、对修改关闭”的设计原则。
 *
 * 如何选择观察者模式和工厂模式：
 * 对于资源文件处理工具这个例子，如果工具提供的功能并不是非常多，只有几个而已，那更推荐使用工厂模式的实现方式，毕竟代码更加清晰、易懂。
 * 相反，如果工具提供非常多的功能，比如有十几个，那更推荐使用访问者模式，因为访问者模式需要定义的类要比工厂模式的实现方式少很多，
 * 类太多也会影响到代码的可维护性。
 *
 */
public class ToolApplication {

    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles("F:\\日语\\N3真题\\201307");
        for (ResourceFile resourceFile : resourceFiles) {
            Extractor extractor = ExtractorFactory.getExtractor(resourceFile.getType());
            extractor.extract2txt(resourceFile);
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
