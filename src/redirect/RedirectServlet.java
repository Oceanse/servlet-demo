package redirect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/ServletDemo_war_exploded/redirect
 * sendRedirect()是客户端的一种行为,相当于发起新的一项新的请求， 和之前的请求属于两个独立的访问请求和响应过程，两对独立的request/response
 * 重定向是客户端的一种行为，所以重定向后地址栏的url会改变
 * 重定向到另一个资源能是servlet，jsp或html文件
 *
 * 转发forward()无法在后面带参数传递,比如request.getRequestDispatcher(/servlet?name=frank),这样不行,可以程序内通过request.setAttribute("name","frank")来传至下一个资源.
 * 客人理解就是转发过程中的request对象已经包含了所有的请求参数，如果转发地址再次添加参数会覆盖之前的参数
 * sendRedirect()可以带参数传递, 比如request.sendRedirect(/servlet?name=frank)
 *
 * 重定向方式不能实现request域的据共享
 * 同一个WEB应用程序的内部资源之间的跳转，跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，那就应该使用RequestDispatcher.forward方法，
 * 不同WEB应用程序之间的重定向，特别是要重定向到另外一个WEB站点上的资源的情况，都应该使用HttpServletResponse.sendRedirect方法。
 */

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // 直接重定向到：http://www.yiibai.com
        response.sendRedirect("http://www.yiibai.com");

        //两次重定向
        //response.sendRedirect("/ServletDemo_war_exploded/demo"); //重定向是客户端行为，因此路径需要加上项目名
        pw.close();
    }
}
