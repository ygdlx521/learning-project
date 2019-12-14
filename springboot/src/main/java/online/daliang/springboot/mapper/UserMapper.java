package online.daliang.springboot.mapper;

import online.daliang.springboot.beans.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created on 2019/12/14.
 *
 * @author daliang
 */
//@Repository
public interface UserMapper {

    @Select("select id, user_name userName, password from register_user where id = #{id}")
    public User getUserById(Integer id);
}
