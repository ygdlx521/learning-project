package online.daliang.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSecurity {

    @GetMapping("/hello")
    public String hello(){
        return "hello security!";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }

    @GetMapping("/login")
    public String login(){
        return "please login!";
    }

}
