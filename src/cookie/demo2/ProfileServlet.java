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
 * 查看缓存
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Cookie cks[] = request.getCookies();
        boolean isFound=false;
        if (cks!=null) {
            for (Cookie cookie : cks) {
                if(cookie.getName().equals("name")){
                    out.print("<b>欢迎您来到个人信息中心</b>");
                    out.print("<br>您好, " + cookie.getValue());
                    isFound=true;
                    break;
                }
            }
            if(!isFound){
                out.print("<font style='color:red;'>请先登录!</font>");
                request.getRequestDispatcher("/cookie/demo2/login.html").include(request,response);
            }
        }else{
            out.print("<font style='color:red;'>请先登录!</font>");
            request.getRequestDispatcher("/cookie/demo2/login.html").include(request,response);
        }
        out.close();
    }
}
