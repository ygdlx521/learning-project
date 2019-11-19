package online.daliang.login.dao;

import online.daliang.login.beans.User;

/**
 * Created on 2019/11/18.
 *
 * @author daliang
 */
public interface UserDao {

    public User getUserByUserNameAndPassword(String username, String password);
}
