package designpattern.behavioral.template.demo6;

/**
 * A 类事先注册某个函数 m 到 B 类，A 类在调用 B 类的 P 函数的时候，B 类反过来调用 A 类注册给它的 F 函数。
 * 这里的 m 函数就是“回调函数”。A 调用 B，B 反过来又调用 A，这种调用机制就叫作“回调”
 *
 * Java 需要使用包裹了回调函数的类对象，我们简称为回调对象
 *
 * 回调跟模板模式一样，也具有复用和扩展的功能。除了回调函数之外，BClass 类的 process() 函数中的逻辑都可以复用。
 * 如果 ICallback、BClass 类是框架代码，AClass 是使用框架的客户端代码，我们可以通过 ICallback 定制 process() 函数，也就是说，框架因此具有了扩展的能力
 */
public class AClass {

    public static void main(String[] args) {
        BClass b = new BClass();
        b.process(new ICallback() {
            @Override
            public void methodToCallback() {
                System.out.println("call back me.");
            }
        });
    }

}
