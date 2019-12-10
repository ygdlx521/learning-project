package online.daliang.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * Created on 2019/12/9.
 *
 * @author daliang
 */
@Repository
public class UserDaoJdbcImpl implements UserDao {
    public void insertUser() {
        System.out.println("UserDaoJdbcImpl insert an user!");
    }
}
