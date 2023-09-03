package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class LogFilter implements Filter {

    @Override
    public void  init(FilterConfig config) throws ServletException {

        String logLevel = config.getInitParameter("log-level"); // 获取web.xml初始化参数
        System.out.println("init() in LogFilter class is called");
        System.out.println("logLevel in LogFilter is : " + logLevel);  // 输出初始化参数
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        System.out.println("I am called in LogFilter.doFilter before target method of the HelloServlet class ");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        System.out.println("requestURL="+requestURL);
        System.out.println("contextPath = " + contextPath);
        System.out.println("servletPath = " + servletPath);
        //放行request/response到链的下一个实体，比如Filter或者Servlet, 若注释掉filterChain.doFilter， request/response到此结束
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("I am called in LogFilter.doFilter after target method of the HelloServlet class ");
    }

    @Override
    public void destroy( ){
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
        System.out.println("LogFilter destroy is called");
    }
}
