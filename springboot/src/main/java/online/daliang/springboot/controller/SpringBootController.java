package online.daliang.springboot.controller;

import online.daliang.springboot.beans.User;
import online.daliang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created on 2019/12/13.
 *
 * @author daliang
 */
@Controller
public class SpringBootController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/testSpringBoot")
    public HashMap<String, String> testSpringBoot(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "daliang");
        map.put("password", "123456");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/select/{id}")
    public User selectUser(@PathVariable("id") Integer id){
        return userService.selectUser(id);
    }





}
