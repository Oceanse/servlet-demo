package session.demo1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/ServletDemo_war_exploded/sessionTest1
 * http://localhost:8080/ServletDemo_war_exploded/sessionTest1
 */
@WebServlet({"/sessionTest1", "/sessionTest2"})
public class SessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();

        String servletPath = req.getServletPath();
        if("/sessionTest1".equals(servletPath)){
            //获取当前请求所在的session, 如果session不存在，那么就会创建一个新的session
            //不断刷新： 输出同一个session对象
            //关闭浏览器，重新打开访问上述网站，得到一个新的session对象
            HttpSession session = req.getSession();//等价HttpSession session = req.getSession(true);
            out.println(session);
        }else if ("/sessionTest2".equals(servletPath)){
            //获取当前请求所在的session, 如果session不存在，那么就会返回null
            HttpSession session = req.getSession();
            session.invalidate();//前请求所在的session设置为无效
            HttpSession session2 = req.getSession(false);
            out.println(session2);//null
        }
        else {
            out.println("url is wrong");
        }

    }
}
