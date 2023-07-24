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
 * http://localhost:8080/ServletDemo_war_exploded/profile
 * 这里要求浏览器不能禁用缓存,如果从浏览器中禁用Cookie，则无法正常工作
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Cookie cks[] = request.getCookies();

        if (cks != null) {
            //从缓存中获取username和password
            String username = null;
            String password = null;
            for (Cookie cookie : cks) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }

            //如果缓存中没有username或者password，需要重新登陆
            if (username == null || password == null) {
                out.print("<font style='color:red;'>Authenticate failed and please login !</font>");
                request.getRequestDispatcher("/cookie/demo2/login.html").include(request, response);
                return;
            }

            //缓存中存在username和password，并且和数据库中一致，就显示profile信息
            if (DAO.checkLogin(username, password)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                out.print("<b>Welcome to profile page</b>");
                out.print("<br>Hello, " + username);
                return;
            }
        } else { //缓存==null, 可能发生在禁用缓存情况下
            out.print("<font style='color:red;'>Authenticate failed and please login !</font>");
            request.getRequestDispatcher("/cookie/demo2/login.html").include(request, response);
        }

        out.close();
    }
}
