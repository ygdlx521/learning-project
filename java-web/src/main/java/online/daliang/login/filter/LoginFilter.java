package online.daliang.login.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2019/12/8.
 *
 * @author daliang
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化完成！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        if(url.endsWith("/main.jsp")) {
            resp.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器已销毁！");
    }
}
