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
 * http://localhost:8080/ServletDemo_war_exploded/login?userName=admin&userPass=mypd
 * 或者requestdispatcher/login.html登录
 *
 * RequestDispatcher接口提供将请求转发送到另一个资源的功能，它可能是html，servlet或jsp等。
 * 属于服务器跳转，在前后两次执行后，地址栏不变
 * request.getRequestDispatcher(url):  URL中所包含的“/”表示应用程序(项目)的路径。
 *
 * RequestDispatcher.forward方法只能将请求转发给同一个WEB应用中的组件
 * RequestDispatcher rd = request.getRequestDispatcher("https://www.baidu.com/"); rd.forward(request, response);
 *
 * RequestDispatcher.forward方法的调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和响应过程
 *
 * 此接口也可用于包括另一资源的内容。它是servlet协作的一种方式。
 *  forward: response of second servlet is sent to the client. Response of the first servlet is not displayed to the user.
 *  include: response of second servlet is included in the response of the first servlet that is being sent to the client.
 *
 *
 *  对于同一个WEB应用程序的内部资源之间的跳转，特别是跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，
 *  那就应该使用RequestDispatcher.forward方法。不同WEB应用程序之间的重定向，
 *  特别是要重定向到另外一个WEB站点上的资源的情况，都应该使用HttpServletResponse.sendRedirect方法。
 */
@WebServlet("/login")
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

            //  /welcome 是要转发资源对应的url-pattern, “/”表示应用程序(项目)的路径。
            RequestDispatcher rd = request.getRequestDispatcher("/welcome");
            rd.forward(request, response);
            System.out.println("Username is : "+name);
            out.println("Username is : "+name);
        } else {
            out.print("Sorry UserName or Password Error!");
            //登录失败则把转发后的servlet/html响应包含在第一个servlet的响应中
            RequestDispatcher rd = request.getRequestDispatcher("/requestdispatcher/login.html");//“/”表示应用程序(项目)的路径。
            //include 是吧第二个servlet/html的响应包含在发送给客户端的第一个servlet的响应中
            rd.include(request, response);
        }
    }

}

