package webervlet_annotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 浏览器访问路径配置可以使用下面注解@WebServlet替换web.xml
 * 下面案例使得一个servlet可以对应很多的请求地址，解决了一个url对应一个Servlet导致的类爆炸的问题
 * http://localhost:8080/ServletDemo_war_exploded/helloWebServlet
 */
@WebServlet(name = "annotationServlet",
        urlPatterns = {"/helloWebServlet", "/hiWebServlet"},
        loadOnStartup = 1,
        initParams = {@WebInitParam(name = "department", value = "dev"), @WebInitParam(name = "role", value = "senior")})

public class WebServletDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public WebServletDemo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        response.addHeader("Access-Control-Allow-Origin", "*");//前端项目可以跨域访问
        if("/helloWebServlet".equals(servletPath)){
            hello(response);
        }else if("/hiWebServlet".equals(servletPath)){
            hi(response);
        }else {
            response.getWriter().println("wrong url");
        }
    }

    public void hello(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello");
    }

    public void hi(HttpServletResponse response) throws IOException {
        response.getWriter().println("hi");
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}