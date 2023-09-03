package requestdispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * http://localhost:8080/ServletDemo_war_exploded/login?userName=admin&userPass=mypd 或者 requestdispatcher/login.html登录
 *
 * RequestDispatcher接口提供将请求转发送到另一个资源的功能，它可能是html，servlet或jsp等。
 * 转发属于服务器行为，服务器执行转发跳转行为，转发前后共享相同的request对象和response对象，它们属于同一个访问请求和响应过程
 * 转发前后，地址栏url不变
 *
 * 转发forward()无法在后面带参数传递,比如request.getRequestDispatcher(/servlet?name=frank),这样不行,可以程序内通过request.setAttribute("name","frank")来传至下一个资源.
 * 客人理解就是转发过程中的request对象已经包含了所有的请求参数，如果转发地址再次添加参数会覆盖之前的参数
 * sendRedirect()可以带参数传递, 比如request.sendRedirect(/servlet?name=frank)
 *
 * 此接口也可用于包括另一资源的内容。它是servlet协作的一种方式。
 *  forward: response of second servlet is sent to the client. Response of the first servlet is not displayed to the user.
 *  include: response of second servlet is included in the response of the first servlet that is being sent to the client.
 *
 * 转发方式能实现request域的据共享
 * 同一个WEB应用程序的内部资源之间的跳转，跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，那就应该使用RequestDispatcher.forward方法，
 * 不同WEB应用程序之间的重定向，特别是要重定向到另外一个WEB站点上的资源的情况，都应该使用HttpServletResponse.sendRedirect方法
 */
@WebServlet("/logins")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");//设置服务器端编码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("userName");
        String passwd = request.getParameter("userPass");

        // 用户输入的密码是否为：mypd
        if (passwd.equals("mypd") && name.equals("admin")) {
            RequestDispatcher rd = request.getRequestDispatcher("/welcome");
            rd.forward(request, response);//forward转发
            System.out.println("Username is : "+name);
            out.println("Username is : "+name);
        } else {
            out.print("Sorry UserName or Password Error!");
            //登录失败则把转发后的servlet/html响应包含在第一个servlet的响应中
            RequestDispatcher rd = request.getRequestDispatcher("/requestdispatcher/login.html");
            rd.include(request, response);//include 是把第二个servlet/html的响应包含到当前servlet的响应中，然后返回给客户端
        }
    }

}

