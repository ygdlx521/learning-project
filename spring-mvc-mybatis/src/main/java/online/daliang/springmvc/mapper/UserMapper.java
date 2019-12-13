package online.daliang.springmvc.mapper;

import online.daliang.springmvc.beans.User;

/**
 * Created on 2019/12/12.
 *
 * @author daliang
 */
public interface UserMapper {

    public User getUserById(Integer id);


    public void insertUser(User user );


    public void updateUser(User user );


    public void deleteUser(Integer id );

}
