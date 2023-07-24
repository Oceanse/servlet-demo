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
 *  http://localhost:8080/ServletDemo_war_exploded/logoutWithSession
 *
 */
@WebServlet("/logoutWithSession")
public class LogoutServletWithSession extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // 清除数据
        session.invalidate();
        out.print("您已成功注销退出系统!<br/>");
        out.println("<a href=\"/ServletDemo_war_exploded/session/login.html\">登录</a>");
        out.close();
    }
}
