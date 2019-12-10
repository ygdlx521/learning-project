package online.daliang.spring;

import online.daliang.spring.dao.UserDao;
import online.daliang.spring.handler.UserHandler;
import online.daliang.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2019/12/9.
 *
 * @author daliang
 */
public class TestSpring {

    @Test
    public void testSpring(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ctx);
        UserHandler handler = ctx.getBean("userHandler", UserHandler.class);
        System.out.println(handler);
        UserService service =
                ctx.getBean("userService",UserService.class);
        System.out.println("service : " + service);

        UserDao  dao =
                ctx.getBean("userDaoJdbcImpl",UserDao.class);
        System.out.println("dao: " + dao );

        System.out.println("=========================================");
        handler.register();
    }

}
