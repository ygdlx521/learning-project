package online.daliang.login.servlet;

import online.daliang.login.beans.User;
import online.daliang.login.dao.UserDao;
import online.daliang.login.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 2019/11/17.
 *
 * @author daliang
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        System.out.println("Received Login Requests!");
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
//        writer.println("login!!!登录啦");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserNameAndPassword(username, password);
        if(user == null){
            writer.println("登录失败！");
        } else {
            writer.println("登录成功！");
        }

    }
}
