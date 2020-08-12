package d.designpattern.structural.decorator.demo2;

public class MyBufferedInputStream extends MyInputStream {

    protected volatile MyInputStream in;

    public MyBufferedInputStream(MyInputStream in) {
        this.in = in;
    }

    //...实现基于缓存的读数据接口
}
