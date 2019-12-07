package online.daliang.login.dao;

import javafx.scene.chart.ScatterChart;
import online.daliang.login.beans.User;
import online.daliang.login.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/11/18.
 *
 * @author daliang
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUserNameAndPassword(String username, String password) {
        User registered_user = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url = "jdbc:mysql://121.43.40.249:3306/learning_project";
//        String mysql_usr = "daliang";
//        String mysql_passwd = "hyl_911223";
        try {
//            Connection conn = DriverManager.getConnection(url, mysql_usr, mysql_passwd);
            Connection conn = ConnectionUtils.getConn();
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
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registered_user;

    }

    @Override
    public User getUserByUserName(String username) {
        User registered_user = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url = "jdbc:mysql://121.43.40.249:3306/learning_project";
//        String mysql_usr = "daliang";
//        String mysql_passwd = "hyl_911223";
        try {
//            Connection conn = DriverManager.getConnection(url, mysql_usr, mysql_passwd);
            Connection conn = ConnectionUtils.getConn();
            String sql = "select id, user_name, password from register_user where user_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                registered_user = new User();
                registered_user.setId(rs.getInt("id"));
                registered_user.setUsername(rs.getString("user_name"));
                registered_user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registered_user;
    }

    @Override
    public void insertUser(String username, String password) {
        try {
            Connection conn = ConnectionUtils.getConn();
            String sql = "insert into register_user (user_name, password) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection conn = ConnectionUtils.getConn();
            String sql = "select id, user_name, password from register_user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
        }
    }
}
