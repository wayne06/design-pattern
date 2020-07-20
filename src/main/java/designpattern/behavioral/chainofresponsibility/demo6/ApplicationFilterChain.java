package designpattern.behavioral.chainofresponsibility.demo6;

import org.apache.catalina.core.ApplicationFilterConfig;

import javax.servlet.*;
import java.io.IOException;

/**
 * ApplicationFilterChain 类就是 Tomcat 提供的 FilterChain 的实现类
 */
public class ApplicationFilterChain implements FilterChain {

    private int pos = 0;
    private int n;
    public static final int INCREMENT = 10;
    private ApplicationFilterConfig[] filters;
    private Servlet servlet;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        if (pos < n) {
            ApplicationFilterConfig filterConfig = filters[pos++];
            Filter filter = null;
            try {
                filter = (Filter) Class.forName(filterConfig.getFilterClass()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            filter.doFilter(servletRequest, servletResponse, this);
        } else {
            servlet.service(servletRequest, servletResponse);
        }
    }

    public void addFilter(ApplicationFilterConfig filterConfig) {
        for (ApplicationFilterConfig config : filters) {
            if (config == filterConfig) {
                return;
            }
            if (n == filters.length) {
                ApplicationFilterConfig[] newFilters = new ApplicationFilterConfig[n + INCREMENT];
                System.arraycopy(filters, 0, newFilters, 0, n);
                filters = newFilters;
            }
            filters[n++] = filterConfig;
        }
    }

}
