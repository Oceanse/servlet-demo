package cookie.demo1;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 浏览器从服务器资源a接收cookie, 然后访问服务器资源b时提交Cookie，服务器资源b需要接收处理Cookie
 */
@WebServlet("/getCookie")// /这里的/是应用上下文路径
public class GetCookie extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            //接收处理浏览器发送的所有Cookie
            Cookie ck[] = request.getCookies();
            for (Cookie cookie : ck) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                out.print(cookieName + " = " + cookieValue+"<br/>");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

