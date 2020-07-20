package designpattern.structural.decorator.demo2;

public class MyDataInputStream extends MyInputStream {

    protected volatile MyInputStream in;

    public MyDataInputStream(MyInputStream in) {
        this.in = in;
    }

    //...实现读取基本类型数据的接口
    public int readInt() {
        return 0;
    }
}
