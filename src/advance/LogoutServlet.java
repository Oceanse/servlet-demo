package advance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/ServletDemo_war_exploded/advance/logout
 * 注销后跳转到主界面
 */
@WebServlet("/advance/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        out.println("您已成功注销!马上跳转到主界面...");
        String mainPage=request.getContextPath()+"/advance/main.jsp";
        response.setHeader("refresh", String.format("2;%s",mainPage));

    }

}
