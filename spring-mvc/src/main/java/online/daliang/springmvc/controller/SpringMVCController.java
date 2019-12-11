package online.daliang.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringMVCController {

    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("Hello SpringMVC!");
        return "success";
    }

    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") Integer age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }
}
