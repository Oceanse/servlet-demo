package advance;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/advance/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        //直接放行sp页面请求,以及注册登录请求
        String requestURI = httpRequest.getRequestURI();
        System.out.println("当前请求地址："+requestURI);
        if(requestURI.endsWith(".jsp") || requestURI.contains("login") || requestURI.contains("register") || requestURI.contains("logout")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        HttpSession session = httpRequest.getSession(false);

        //如果session=null说明session已经失效，需要重新登录；
        //如果session没有包含username和username，说明之前没有登录过，需要重新登陆
        if (session == null || session.getAttribute("username") == null || session.getAttribute("username") == null) {
            System.out.println("需要重新登录");
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/advance/login.jsp");
            return;
        }
        //权限校验
        boolean access = DAO.checkLogin((String) session.getAttribute("username"), (String) session.getAttribute("password"));
        if(access){
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
