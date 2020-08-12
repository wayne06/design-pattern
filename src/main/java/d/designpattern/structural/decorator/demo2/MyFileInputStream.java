package d.designpattern.structural.decorator.demo2;

public class MyFileInputStream extends MyInputStream {

    protected volatile MyInputStream in;

    public MyFileInputStream(MyInputStream in) {
        this.in = in;
    }

    public MyFileInputStream(String filePath) {
        super();
    }

    //...实现基于文件的读数据接口

}
