package d.designpattern.behavioral.visitor.demo6;

/**
 * Single Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的编译时类型来决定
 * Double Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的运行时类型来决定
 *
 * 在面向对象编程语言中，可以把方法调用理解为一种消息传递，也就是“Dispatch”。一个对象调用另一个对象的方法，就相当于给它发送一条消息。
 * 这条消息起码要包含对象名、方法名、方法参数。
 *
 * Single Dispatch 之所以称为“Single”，是因为执行哪个对象的哪个方法，只跟“对象”的运行时类型有关。
 * Double Dispatch 之所以称为“Double”，是因为执行哪个对象的哪个方法，跟“对象”和“方法参数”两者的运行时类型有关
 *
 * 具体到编程语言的语法机制，Single Dispatch 和 Double Dispatch 跟多态和函数重载直接相关。
 * 当前主流的面向对象编程语言（比如，Java、C++、C#）都只支持 Single Dispatch，不支持 Double Dispatch
 *
 * Java 支持多态特性，代码可以在运行时获得对象的实际类型（也就是前面提到的运行时类型），然后根据实际类型决定调用哪个方法。
 * 尽管 Java 支持函数重载，但 Java 设计的函数重载的语法规则是，并不是在运行时，根据传递进函数的参数的实际类型，来决定调用哪个重载函数，
 * 而是在编译时，根据传递进函数的参数的声明类型（也就是前面提到的编译时类型），来决定调用哪个重载函数。
 * 也就是说，具体执行哪个对象的哪个方法，只跟对象的运行时类型有关，跟参数的运行时类型无关。所以，Java 语言只支持 Single Dispatch
 *
 * 为什么支持 Double Dispatch 的语言不需要访问者模式？
 * 假设 Java 语言支持 Double Dispatch，那 demo2 中的 extractor.extract2txt(resourceFile); 就不会报错。
 * 代码会在运行时，根据参数（resourceFile）的实际类型（PdfFile、PPTFile、WordFile），来决定使用 extract2txt 的三个重载函数中的哪一个。
 * 那后面的代码实现就能正常运行了，也就不需要访问者模式了。
 *
 */
public class Demo {

    public static void main(String[] args) {

        SingleDispatchClass demo = new SingleDispatchClass();

        ParentClass p = new ChildClass();

        // 执行哪个对象的方法，由对象的实际类型决定
        // p 的实际类型为 ChildClass ，所以执行 ChildClass 的 f() 方法，执行结果：“f() from ChildClass”
        demo.polymorphismFunction(p);

        // 执行对象的哪个方法，由参数对象的声明类型决定：
        // p 的声明类型为 ParentClass ，所以执行 overloadFunction(ParentClass p) 方法，执行结果：“overloadFunction(ParentClass p)”
        demo.overloadFunction(p);
    }

}
