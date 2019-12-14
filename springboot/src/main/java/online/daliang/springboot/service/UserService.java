package online.daliang.springboot.service;

import online.daliang.springboot.beans.User;
import online.daliang.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/12/14.
 *
 * @author daliang
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUser(Integer id){
        return userMapper.getUserById(id);
    }
}
