package online.daliang.mavenlearning;

import org.junit.Test;
import static junit.framework.Assert.*;

public class HelloMavenTest {

    @Test
    public void testHello(){
        HelloMaven helloMaven = new HelloMaven();
        String res = helloMaven.sayHello("daliang");
        assertEquals("Hello daliang!",res);
    }
}
