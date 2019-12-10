package online.daliang.spring.service;

import online.daliang.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/12/9.
 *
 * @author daliang
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void doRegister(){
        userDao.insertUser();
    }
}
