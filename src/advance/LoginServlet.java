package advance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/ServletDemo_war_exploded/advance/login.jsp
 * 登录成功后把认证信息存放到session域
 */
@WebServlet("/advance/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (DAO.checkLogin2(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            response.sendRedirect(request.getContextPath()+"/advance/main.jsp");
        } else {
            out.print("<font style='color:red;'>用户名或密码错误!请重新登录或者注册</font>");
            request.getRequestDispatcher("/advance/login.jsp").include(request,response);//inlude转发
        }
    }


}

