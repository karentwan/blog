package cn.karent.config;

import cn.karent.util.Constant;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by wan on 2017/3/16.
 * 后台登陆过滤器
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if( session.getAttribute(Constant.USER) != null) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
