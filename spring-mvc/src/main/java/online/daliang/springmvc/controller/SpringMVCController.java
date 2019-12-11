package online.daliang.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringMVCController {

    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("Hello SpringMVC!");
        return "success";
    }
}
