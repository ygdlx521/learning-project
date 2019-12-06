package online.daliang.login.servlet;

import online.daliang.login.beans.User;
import online.daliang.login.dao.UserDao;
import online.daliang.login.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2019/11/24.
 *
 * @author daliang
 */
@WebServlet(name = "CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserName(userName);
        String msg = "";
        if(user == null){
            msg = "0";
        } else {
            msg = "1";
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(msg);
        System.out.println(msg);
    }
}
