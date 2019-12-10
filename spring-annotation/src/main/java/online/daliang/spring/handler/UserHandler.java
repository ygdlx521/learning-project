package online.daliang.spring.handler;

import online.daliang.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created on 2019/12/9.
 *
 * @author daliang
 */

@Controller
public class UserHandler {
    @Autowired
    private UserService userService;
    public void register(){
        userService.doRegister();
    }
}
