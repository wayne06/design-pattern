package designpattern.structural.decorator.demo2;

/**
 * 装饰器模式相对于简单的组合关系，还有两个比较特殊的地方，见 test1() 和 test2()：
 *
 * 装饰器模式主要解决继承关系过于复杂的问题，通过组合来替代继承。它主要的作用是给原始类添加增强功能。
 * 这也是判断是否该用装饰器模式的一个重要的依据。
 * 除此之外，装饰器模式还有一个特点，那就是可以对原始类嵌套使用多个装饰器。
 * 为了满足这个应用场景，在设计的时候，装饰器类需要跟原始类继承相同的抽象类或者接口。
 *
 */
public class Demo {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test3() {

    }

    /**
     * 第二个比较特殊的地方是：装饰器类是对功能的增强，这也是装饰器模式应用场景的一个重要特点。
     * 实际上，符合“组合关系”这种代码结构的设计模式有很多，比如之前讲过的代理模式、桥接模式，还有现在的装饰器模式。
     * 尽管它们的代码结构很相似，但是每种设计模式的意图是不同的。
     *
     * 就拿比较相似的代理模式和装饰器模式来说：
     * 代理模式中，代理类附加的是跟原始类无关的功能，而在装饰器模式中，装饰器类附加的是跟原始类相关的增强功能。
     *
     * 见 demo3
     */
    private static void test2() {

    }

    /**
     * 第一个比较特殊的地方是：装饰器类和原始类继承同样的父类，这样我们可以对原始类“嵌套”多个装饰器类。
     * 比如，下面这样一段代码，我们对 FileInputStream 嵌套了两个装饰器类：BufferedInputStream 和 DataInputStream，
     * 让它既支持缓存读取，又支持按照基本数据类型来读取数据
     */
    private static void test1() {
        MyInputStream in = new MyFileInputStream("/user/wangzheng/test.txt");
        MyInputStream bin = new MyBufferedInputStream(in);
        MyDataInputStream din = new MyDataInputStream(bin);
        int data = din.readInt();
        System.out.println(data);
    }

}
