package opensourced.spring.demo3;

/**
 * 模版模式在 Spring 中的应用
 *
 *  Spring Bean 的创建过程包含哪些主要的步骤？
 *
 *  这其中就涉及模板模式。它也体现了 Spring 的扩展性。利用模板模式，Spring 能让用户定制 Bean 的创建过程。
 *  Spring Bean 的创建过程，可以大致分为两大步：对象的创建和对象的初始化。
 *      1. 对象的创建是通过反射来动态生成对象，而不是 new 方法。不管是哪种方式，说白了，总归还是调用构造函数来生成对象，没有什么特殊的。
 *      2. 对象的初始化有两种实现方式。
 *          2.1 一种是在类中自定义一个初始化函数，并且通过配置文件，显式地告知 Spring，哪个函数是初始化函数。这种初始化方式有一个缺点，
 *          初始化函数并不固定，由用户随意定义，这就需要 Spring 通过反射，在运行时动态地调用这个初始化函数。
 *          而反射又会影响代码执行的性能，那有没有替代方案呢？
 *          2.2 另一种是让类实现 Initializingbean 接口。这个接口包含一个固定的初始化函数定义（afterPropertiesSet() 函数）。
 *          Spring 在初始化 Bean 的时候，可以直接通过 bean.afterPropertiesSet() 的方式，调用 Bean 对象上的这个函数，而不需要使用反射来调用了
 *          尽管这种实现方式不会用到反射，执行效率提高了，但业务代码（DemoClass）跟框架代码（InitializingBean）耦合在了一起。
 *          框架代码侵入到了业务代码中，替换框架的成本就变高了。所以，我并不是太推荐这种写法。
 *
 * 实际上，Spring 针对对象的初始化过程，还做了进一步的细化，将它拆分成了三个小步骤：初始化前置操作、初始化、初始化后置操作。
 * 其中，中间的初始化操作就是我们刚刚讲的那部分；初始化的前置和后置操作，定义在接口 BeanPostProcessor 中。BeanPostProcessor 的接口定义如下：
 * postProcessBeforeInitialization，postProcessAfterInitialization
 *
 * 如何通过 BeanPostProcessor 来定义初始化前置和后置操作？
 * 只需要定义一个实现了 BeanPostProcessor 接口的处理器类，并在配置文件中像配置普通 Bean 一样去配置就可以了。
 * Spring 中的 ApplicationContext 会自动检测在配置文件中实现了 BeanPostProcessor 接口的所有 Bean，
 * 并把它们注册到 BeanPostProcessor 处理器列表中。在 Spring 容器创建 Bean 的过程中，Spring 会逐一去调用这些处理器。
 *
 * Spring Bean 的生命周期：
 * 1. 创建对象
 * 2. 初始化
 *      BeanPostProcessor 前置操作 -> InitializingBean 的 afterPropertiesSet() 方法 -> init-method -> BeanPostProcessor 后置操作
 * 3. 使用
 * 4. 销毁
 *      DisposableBean 的 destroy() 方法 -> destroy-method
 *
 * 这里的模板模式的实现，并不是标准的抽象类的实现方式，而是有点类似我们前面讲到的 Callback 回调的实现方式，
 * 也就是将要执行的函数封装成对象（比如，初始化方法封装成 InitializingBean 对象），传递给模板（BeanFactory）来执行。
 *
 */
public class Demo {

}
