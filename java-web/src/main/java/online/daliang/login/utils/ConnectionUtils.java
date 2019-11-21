package online.daliang.login.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created on 2019/11/20.
 *
 * @author daliang
 */
public class ConnectionUtils {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://121.43.40.249:3306/learning_project" ;
    private static String username = "daliang" ;
    private static String password = "hyl_911223" ;

    private static Properties props = new Properties();

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    static {
        InputStream in = ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties");
        try {
//            props.load(in);
//            driver =props.getProperty("jdbc.driver");
//            url = props.getProperty("jdbc.url");
//            username= props.getProperty("jdbc.username");
//            password = props.getProperty("jdbc.password");
            System.out.println(driver + url + username + password);
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        Connection conn = tl.get();
        if(conn == null) {
            conn = DriverManager.getConnection(url,username,password);
            tl.set(conn);
        }
        return conn;
    }

    public static void closeConn() throws SQLException {
        Connection conn = tl.get();
        if(conn != null){
            conn.close();
        }
        tl.set(null);
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConn();
        Connection con2 = getConn();
        System.out.println(con2 == conn);
    }
}
