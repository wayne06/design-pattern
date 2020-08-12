package d.designpattern.behavioral.chainofresponsibility.demo5;

import javax.servlet.*;
import java.io.IOException;

/**
 * 添加一个过滤器，只需要定义一个实现 javax.servlet.Filter 接口的过滤器类，并且将它配置在 web.xml 配置文件中。
 * Web 容器启动的时候，会读取 web.xml 中的配置，创建过滤器对象。
 * 当有请求到来的时候，会先经过过滤器，然后才由 Servlet 来处理
 *
 * 在 web.xml 配置文件中如下配置：
 * <filter>
 *   <filter-name>logFilter</filter-name>
 *   <filter-class>xxx.LogFilter</filter-class>
 * </filter>
 * <filter-mapping>
 *   <filter-name>logFilter</filter-name>
 *   <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * 添加过滤器非常方便，不需要修改任何代码，定义一个实现 javax.servlet.Filter 的类，再改改配置就搞定了，完全符合开闭原则。
 * Servlet Filter 是如何做到如此好的扩展性的呢 - 利用的就是职责链模式
 *
 * 职责链模式的实现包含处理器接口（IHandler）或抽象类（Handler），以及处理器链（HandlerChain）。
 * 对应到 Servlet Filter，javax.servlet.Filter 就是处理器接口，FilterChain 就是处理器链
 *
 * Servlet 只是一个规范，并不包含具体的实现，所以，Servlet 中的 FilterChain 只是一个接口定义。
 * 具体的实现类由遵从 Servlet 规范的 Web 容器来提供，比如，ApplicationFilterChain 类就是 Tomcat 提供的 FilterChain 的实现类
 *
 */
public class LogFilters implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在创建 Filter 时自动调用
        // 其中 filterConfig 包含这个 Filter 的配置参数，比如 name 之类（从配置文件中读取）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截客户端发送来的请求.");
        chain.doFilter(request, response);
        System.out.println("拦截发送给客户端的响应.");
    }

    @Override
    public void destroy() {
        // 在销毁 Filter 时自动调用
    }

}
