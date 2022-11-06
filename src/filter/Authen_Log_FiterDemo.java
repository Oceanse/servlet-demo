package filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * chain(链): request--->AuthenFilter--->LogFilter--->Authen_Log_FiterDemo
 * http://localhost:8080/ServletDemo_war_exploded/authen_log?name=ocean
 */
@WebServlet("/authen_log")
public class Authen_Log_FiterDemo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        // 输出文本
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.write("<h3> " + "Hello " + name + " </h1>");
        out.close();
    }

}
