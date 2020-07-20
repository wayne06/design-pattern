package opensourced.spring.demo4;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法二：实现Controller接口 + xml配置文件:配置DemoController与URL的对应关系
 */
public class DemoController2 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Greeting");
        modelAndView.addObject("message", "Dinesh Madhwal");
        return modelAndView;
    }
}
