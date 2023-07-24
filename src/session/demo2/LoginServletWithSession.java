package session.demo2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当客户端第一次请求会话对象时，服务器会创建一个 Session 对象，并为该 Session 对象分配一个唯一的 SessionID,然后存储到Map(sessionID, Session对象)
 * 服务器将 SessionID 以 Cookie(JSESSIONID”，SessionID的值)的形式发送给客户端浏览器；
 * 客户端浏览器再次发送 HTTP 请求时，会将携带 Cookie(JSESSIONID”，SessionID的值)  随请求一起发送给服务器；
 * 服务器从请求cookie中读取 SessionID，然后根据 SessionID查询Map(sessionID, Session对象) 找到对应的 Session 对象。
 * 因此session机制依赖cookie
 *
 * Session 对象在如下 3 种情况下会被销毁：
 * Session 过期；web.xml中可以设置session的过期时间
 * 调用 session.invalidate() 方法，手动销毁 Session；
 * 服务器关闭或者应用被卸载。
 *
 * Session 对象也是一种域对象，它可以对属性进行setAttribute(k,v) getAttribute(k)等操作，进而实现一次会话中不同请求之间的数据共享。
 * 用户在访问服务器的资源时，可以把数据保存在各自的 Session 中。当用户再次访问该服务器中的其它资源时，其它资源可以从 Session 中取出数据，为用户服务。
 *
 * 网页登录：http://localhost:8080/ServletDemo_war_exploded/session/index.html
 * http://localhost:8080/ServletDemo_war_exploded/loginWithSession?username=ocean&password=123
 */
@WebServlet("/loginWithSession")
public class LoginServletWithSession extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = DB.find(username, password);
        if (user != null) {
            //当客户端第一次请求时，服务器就会创建一个 Session 对象，可以通过request.getSession()获取
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //多个请求共享一个session,所以这里可以用重定向
            response.sendRedirect("/ServletDemo_war_exploded/profileWithSession");
        } else {
            out.print("<font style='color:rec;'>对不起，用户名或密码错误!</font>");
            request.getRequestDispatcher("/session/login.html").include(request, response);
        }
        out.close();
    }


}

