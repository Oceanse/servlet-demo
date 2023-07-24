package cookie.demo2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 网页登录：http://localhost:8080/ServletDemo_war_exploded/cookie/demo2/index.html
 * http://localhost:8080/ServletDemo_war_exploded/login?username=admin&password=123
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 用户名和密码分别为：admin , 123456， 生产环境中这里应该是查询数据库，来判断用户名密码是否正确
        if (DAO.checkLogin(username, password)) {
            Cookie usernameCookie=new Cookie("username",username);
            Cookie passwdCookie=new Cookie("password",password);

            //设置有效路径
            usernameCookie.setPath(request.getContextPath());
            passwdCookie.setPath(request.getContextPath());

            //cookie有效期1min
            usernameCookie.setMaxAge(60*60);
            passwdCookie.setMaxAge(60*60);

            //添加缓存到响应
            response.addCookie(usernameCookie);
            response.addCookie(passwdCookie);
            response.sendRedirect("/ServletDemo_war_exploded/profile");//重定向到简介页面
            //request.getRequestDispatcher("/profile").include(request,response);//转发
        } else {
            out.print("<font style='color:red;'>用户名或密码错误!请重新登录或者注册</font>");
            request.getRequestDispatcher("/cookie/demo2/index.html").include(request,response);//转发
           // response.sendRedirect("/ServletDemo_war_exploded/cookie/demo2/login.html");//这里采用重定向的话，新的页面将不能新增相应内容"用户名或密码错误!"
        }
        out.close();
    }
}
