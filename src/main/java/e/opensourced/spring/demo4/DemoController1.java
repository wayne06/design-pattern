package e.opensourced.spring.demo4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 方法一：通过@Controller、@RequestMapping来定义
 */
@Controller
public class DemoController1 {

    @RequestMapping("/employname")
    public ModelAndView getEmployeeName() {
        ModelAndView modelAndView = new ModelAndView("Greeting");
        modelAndView.addObject("message", "Dinesh");
        return modelAndView;
    }

}
