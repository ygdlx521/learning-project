package online.daliang.springmvc.mybatis.test;

import online.daliang.springmvc.beans.User;
import online.daliang.springmvc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2019/12/12.
 *
 * @author daliang
 */
public class TestMybatis {

    @Test
    public void testSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            System.out.println(mapper.getClass().getName());
            User userById = mapper.getUserById(1);
            System.out.println(userById);

        }
    }
}
