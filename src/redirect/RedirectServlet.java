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
 * HttpServletResponse接口的sendRedirect()方法可以用于将响应重定向到另一个资源，资源可能是servlet，jsp或html文件
 * sendRedirect()是客户端的一种行为,相当于发起新的一项请求；属于两个独立的访问请求和响应过程
 * 客户端地址栏的url会改变
 * eg: response.sendRedirect("servlet2");
 *
 *
 * 对于同一个WEB应用程序的内部资源之间的跳转，特别是跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，
 * 那就应该使用RequestDispatcher.forward方法。不同WEB应用程序之间的重定向，特别是要重定向到另外一个WEB站点上的资源的情况，
 * 都应该使用HttpServletResponse.sendRedirect方法。
 */

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // 直接重定向到：http://www.yiibai.com
        response.sendRedirect("http://www.yiibai.com");

        //两次重定向
        //response.sendRedirect("/ServletDemo_war_exploded/search"); 这里/代表localhost:8080/
        pw.close();
    }
}
