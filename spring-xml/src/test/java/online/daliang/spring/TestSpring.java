package online.daliang.spring;

import online.daliang.spring.handler.UserHandler;
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
        UserHandler userHandler = ctx.getBean("userHandler", UserHandler.class);
        System.out.println(userHandler);
    }

}
