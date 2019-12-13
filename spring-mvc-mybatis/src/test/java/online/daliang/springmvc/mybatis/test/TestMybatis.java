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
  
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(mapper.getClass().getName());
        User userById = mapper.getUserById(1);
        System.out.println(userById);

        //查询:
//			User User = mapper.getUserById(1001);
//			System.out.println("User: " + User );
//			System.out.println("==========================================================");
//        添加
        User UserNew = new User(22, "222", "mybatis");
//        mapper.insertUser(UserNew);

        //System.out.println("==========================================================");
        //修改
        User UserUpdate = new User(22, "222", "mybatis_update");
        mapper.updateUser(UserUpdate);

        System.out.println("==========================================================");
        //删除
        mapper.deleteUser(22);

        //增删改操作必须进行提交
        session.commit();

    }
}

