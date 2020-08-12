package d.designpattern.structural.adapter.v4;

/**
 * 适配器模式应用场景三：替换依赖的外部系统
 */
public class Demo {

    private IA a;

    public Demo(IA a) {
        this.a = a;
    }




    public static void main(String[] args) {

        // 使用外部系统A
        Demo d1 = new Demo(new A());

        // 使用外部系统B
        // 借助BAdaptor，调用IA接口的地方都无需改动，只需要将BAdaptor如下注入到Demo即可。
        Demo d2 = new Demo(new BAdaptor(new B()));
    }
}
