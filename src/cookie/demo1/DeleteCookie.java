package cookie.demo1;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 清除指定缓存
 */
@WebServlet("/deleteCookie")// /这里的/是应用上下文路径
public class DeleteCookie extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            Cookie nameCookie=new Cookie("username",null);
            Cookie passwdCookie=new Cookie("password",null);
            nameCookie.setMaxAge(0);
            passwdCookie.setMaxAge(0);
            response.addCookie(nameCookie);
            response.addCookie(passwdCookie);
            out.println("The specified cookies has been deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

