package designpattern.behavioral.visitor.demo1;

public class PPTFile extends ResourceFile {

    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        //省略：从 PPT 中抽取文本

        // 将抽取出的文本保存在跟 filePath 同名的 .txt 文件中

        System.out.println("Extract PPT");
    }
}
