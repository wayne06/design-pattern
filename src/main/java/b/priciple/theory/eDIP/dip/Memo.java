package b.priciple.theory.eDIP.dip;

/**
 * Dependency Inversion Principle:
 *
 * High-level modules shouldn’t depend on low-level modules. Both modules should depend on abstractions.
 * In addition, abstractions shouldn’t depend on details. Details depend on abstractions.
 *
 * 高层模块（high-level modules）不要依赖低层模块（low-level）。高层模块和低层模块应该通过抽象（abstractions）来互相依赖。
 * 除此之外，抽象（abstractions）不要依赖具体实现细节（details），具体实现细节（details）依赖抽象（abstractions）
 *
 * 所谓高层模块和低层模块的划分，简单来说就是，在调用链上，调用者属于高层，被调用者属于低层。
 * 在平时的业务代码开发中，高层模块依赖底层模块是没有任何问题的。
 * 实际上，这条原则主要还是用来指导框架层面的设计，跟前面讲到的控制反转类似。
 *
 * 拿 Tomcat 这个 Servlet 容器作为例子来解释一下。
 * Tomcat 是运行 Java Web 应用程序的容器。我们编写的 Web 应用程序代码只需要部署在 Tomcat 容器下，便可以被 Tomcat 容器调用执行。
 * 按照之前的划分原则，Tomcat 就是高层模块，我们编写的 Web 应用程序代码就是低层模块。
 * Tomcat 和应用程序代码之间并没有直接的依赖关系，两者都依赖同一个“抽象”，也就是 Servlet 规范。
 * Servlet 规范不依赖具体的 Tomcat 容器和应用程序的实现细节，而 Tomcat 容器和应用程序依赖 Servlet 规范。
 *
 */
public class Memo {
}
