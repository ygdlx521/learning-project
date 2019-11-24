package online.daliang.login.servlet;

import online.daliang.login.beans.User;
import online.daliang.login.dao.UserDao;
import online.daliang.login.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2019/11/23.
 *
 * @author daliang
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserName(username);
        if(user != null){
            req.setAttribute("register_msg","用户已存在！");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            userDao.insertUser(username,password);
            resp.sendRedirect("login.jsp");
        }
    }
}
