package online.daliang.login.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 2019/12/7.
 *
 * @author daliang
 */
@WebServlet(name = "SessionServlet")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("当前请求的request对象：" +request);
        HttpSession session = request.getSession();
        System.out.println("当前请求的session对象：" + session);
        System.out.println("当前请求的session对象ID：" + session.getId());
        System.out.println("==============================");
    }
}
