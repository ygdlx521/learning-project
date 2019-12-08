package online.daliang.login.servlet;

import com.google.gson.Gson;
import online.daliang.login.beans.User;
import online.daliang.login.dao.UserDao;
import online.daliang.login.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 2019/12/8.
 *
 * @author daliang
 */
@WebServlet(name = "GetJsonStrServlet")
public class GetJsonStrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(allUsers);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);

    }
}
