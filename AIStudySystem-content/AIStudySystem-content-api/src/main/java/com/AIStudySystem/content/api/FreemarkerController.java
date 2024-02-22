package com.AIStudySystem.content.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreemarkerController {

    @GetMapping("/testfreemarker")
    public ModelAndView test() {
        // 创建一个模型视图对象：
        ModelAndView modelAndView = new ModelAndView();
        // 根据视图名称test加.ftl后缀，从resources/templates目录下找到test.ftl：
        modelAndView.setViewName("test"); // test.ftl
        // 设置模型数据：
        modelAndView.addObject("broski", "Kyle");
        return modelAndView;
    }
}
