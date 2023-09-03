package cookie.demo1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * http://localhost:8080/ServletDemo_war_exploded/addCookie?username=ocean&password=123
 *
 * https://www.cnblogs.com/stu-jyj3621/p/14370990.html
 * Cookie是由服务器端生成并储存在浏览器客户端上的数据，可以存储在浏览器的运行内存或者浏览器所在的磁盘上
 * 浏览器客户端访问服务端资源b: http://localhost:8080/a/b， 服务端资源a产生cookie并添加到响应中发送给浏览器客户端
 * 在cookie有效期内，浏览器客户端访问服务端资源c:http://localhost:8080/a/c 就会携带该cookie
 * 服务端资源c接收并处理该缓存
 * 浏览器是可以禁用Cookie，表示服务器发送过来的Cookie，我浏览器不要，不接收。服务器还是会发送Cookie的，只不过浏览器不再接收。现在有很多网站的使用都是需要开启接收Cookie的
 *
 * Cookie机制是HTTP协议规定的，广泛存在于B/S架构中
 */
@WebServlet("/addCookie")//这里的/是应用上下文路径
public class AddCookie extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");
            String passwd = request.getParameter("password");
            out.print("Welcome, " + username+"<br/><br/>");

            // 创建 cookie 对象
            Cookie ck = new Cookie("username", username);
            Cookie ck2 = new Cookie("password", passwd);


            //设置缓存的有效路径，也就是浏览器在有效路径下访问服务器才会携带该缓存
            //request.getContextPath()=/ServletDemo_war_exploded 后面只要访问/ServletDemo_war_exploded或者其子路径就会带上ck和ck2或者说只要访问/ServletDemo_war_exploded下的资源就会会带上ck和ck2
            //如果不设置缓存有效路径，就采用默认有效路径：假设当前生成cookie的servlet访问路径是 http://localhost:8080/a/b,那么有效路径是http://localhost:8080/a及其子路径
            ck.setPath(request.getContextPath());
            ck2.setPath(request.getContextPath());

            //默认情况下，没有设置Cookie的有效时长（以秒为单位），该Cookie被默认保存在浏览器的运行内存当中，只要浏览器不关闭Cookie存在，只要关闭浏览器Cookie消失
            //我们可以通过设置Cookie的有效时长(大于0)，以保证Cookie保存在硬盘文件当中
            //如果有效时间 >0，则该Cookie对象发送给浏览器之后浏览器将其保存到硬盘文件中。
            //如果有效时间 <0，则该Cookie对象也是被保存在浏览器内存中，待浏览器关闭Cookie消失。
            //如果有效时间 =0，表示删除该Cookie。
            //默认为-1
            //这路cookie有效期60s, 即使关闭浏览器，60s之内重新打开浏览器重新发送请求，在有效路径下浏览器依然可以携带这个cookie访问服务器
            ck.setMaxAge(60);
            ck2.setMaxAge(60);

            //把 cookie 添加到响应中，响应头中增加2个相应的 Set-Cookie 头字段
            //在 HTTP 请求中，Cookie 是明文传递的，容易泄露用户信息，安全性不高。
            response.addCookie(ck);
            response.addCookie(ck2);

            // 创建获取cookie的 button
            out.print("<form action='getCookie' method='GET'>");
            out.print("<input type='submit' value='Go to GetCookie Servlet'>");
            out.print("</form>");

            // 创建删除cookie的 button
            out.print("<form action='deleteCookie' method='GET'>");
            out.print("<input type='submit' value='Go to DeleteCookie Servlet'>");
            out.print("</form>");
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


