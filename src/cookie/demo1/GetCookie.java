package cookie.demo1;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Cookie是在多个客户端请求之间持久存储的一小段信息。
 * Cookie具有名称，单个值和可选属性，例如注释，路径和域限定符，生存周期和版本号。
 * 默认情况下，每个请求都被视为新的请求,也就是两次请求毫无关系。
 * 在cookie技术中，servlet响应可以添加cookie返回给客户端，所以cookie存储在浏览器的缓存中。
 * 之后，如果用户发出请求，默认情况下会带上cookie， server端就可以识别出是同一个用户发出的请求
 */
@WebServlet("/getCookie")// /这里的/是应用上下文路径
public class GetCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Cookie ck[] = request.getCookies();
            for (Cookie cookie : ck) {
                if(cookie.getName().equals("username")){
                    out.print("Hello " + cookie.getValue());
                    break;
                }
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/servlet/cookies-in-servlet.html

