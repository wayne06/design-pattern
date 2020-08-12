package e.opensourced.spring.demo4;

/**
 * 适配器模式在 Spring 中的应用
 *
 * 在 Spring MVC 中，定义一个 Controller 最常用的方式是，通过 @Controller 注解来标记某个类是 Controller 类，
 * 通过 @RequesMapping 注解来标记函数对应的 URL。不过，定义一个 Controller 远不止这一种方法。
 * 我们还可以通过让类实现 Controller 接口或者 Servlet 接口，来定义一个 Controller。
 *
 * 方法一：通过@Controller、@RequestMapping来定义
 * 方法二：实现Controller接口 + xml配置文件:配置DemoController与URL的对应关系
 * 方法三：实现Servlet接口 + xml配置文件:配置DemoController类与URL的对应关系
 *
 *
 * 在应用启动的时候，Spring 容器会加载这些 Controller 类，并且解析出 URL 对应的处理函数，封装成 Handler 对象，存储到 HandlerMapping 对象中。
 * 当有请求到来的时候，DispatcherServlet 从 HandlerMapping 中，查找请求 URL 对应的 Handler，然后调用执行 Handler 对应的函数代码，
 * 最后将执行结果返回给客户端。
 *
 * 但是，不同方式定义的 Controller，其函数的定义（函数名、入参、返回值等）是不统一的。
 * 如上示例代码所示，方法一中的函数的定义很随意、不固定，方法二中的函数定义是 handleRequest()、
 * 方法三中的函数定义是 service()（看似是定义了 doGet()、doPost()，实际上，
 * 这里用到了模板模式，Servlet 中的 service() 调用了 doGet() 或 doPost() 方法，DispatcherServlet 调用的是 service() 方法）。
 * DispatcherServlet 需要根据不同类型的 Controller，调用不同的函数。
 */
public class Demo {

}
