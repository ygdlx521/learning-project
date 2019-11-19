package online.daliang.login.dao;

import online.daliang.login.beans.User;

import java.sql.*;

/**
 * Created on 2019/11/18.
 *
 * @author daliang
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUserNameAndPassword(String username, String password) {
        User registered_user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://121.43.40.249:3306/learning_project";
        String mysql_usr = "daliang";
        String mysql_passwd = "hyl_911223";
        try {
            Connection conn = DriverManager.getConnection(url, mysql_usr, mysql_passwd);
            String sql = "select id, user_name, password from register_user where user_name = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                registered_user = new User();
                registered_user.setId(rs.getInt("id"));
                registered_user.setUsername(rs.getString("user_name"));
                registered_user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registered_user;

    }
}
