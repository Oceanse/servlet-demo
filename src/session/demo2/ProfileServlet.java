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
 * http://localhost:8080/ServletDemo_war_exploded/profileWithSession
 */
@WebServlet("/profileWithSession")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //如果当前reqeust中的HttpSession为null，当create为true，就创建一个新的Session；当create为false，则返回null
        // session.invalidate()会使得下面session返回null
        HttpSession session = request.getSession(false);

        //说明点击了注销
        if (session == null) {
            out.print("请登录系统1！");
            request.getRequestDispatcher("/session/login.html").include(request, response);
            return;
        }

        User user = (User) session.getAttribute("user");
        //user!=null说明这个session就是登录时候创建的session
        if (user != null) {
            out.print("您好, " + user.getUsername() + " 欢迎您来到个人信息中心！");
            out.print("<hr/>年龄：" + user.getAge()+"<br/>");
            out.print("<a href=\"/ServletDemo_war_exploded/logoutWithSession\">注销</a>|");
        }else {//说明没有登录，直接访问个人信息页面，会创建新的session,但是新的session不会包含登录session域保存的信息
            out.print("请登录系统2！");
            request.getRequestDispatcher("/session/login.html").include(request, response);
        }
        out.close();
    }


}
