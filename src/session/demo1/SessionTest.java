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
 * http://localhost:8080/ServletDemo_war_exploded/sessionTest2
 */
@WebServlet({"/sessionTest1", "/sessionTest2"})
public class SessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();

        String servletPath = req.getServletPath();
        if("/sessionTest1".equals(servletPath)){
            //获取当前请求jsessionid所关联的session对象, 如果服务器中的这个session不存在，那么服务器就会创建一个新的session对象和对应的jsessionid,jsessionid会通过set-cookie返还给客户端
            //不断刷新： 因为所有请求中的cookie中的jsessionid相同，所以会对应同一个session对象
            //HttpSession session = req.getSession(); 等价HttpSession session = req.getSession(true);
            HttpSession session1 = req.getSession();
            out.println("session1="+session1);
        }else if ("/sessionTest2".equals(servletPath)){
            HttpSession session2 = req.getSession();
            out.println("session2="+session2);//和上面是同一个session, 因为请求中的cookie中jsessionid相同

            //前请求所在的session设置为无效
            //客户端会删除浏览器中cookie对应的jsessionid，其中包括通常用于跟踪会话的JSESSIONID cookie
            //服务端HttpSession 对象被标记为无效，并且不能再使用该对象来访问会话中的属性或调用会话相关的 API。任何尝试这样做的操作都会抛出 IllegalStateException 异常。
            session2.invalidate();

            //当前请求cookie已经不存在jsessionid,所以服务端会生成新的session对象和jsessionid,jsessionid会通过set-cookie返还给客户端
            HttpSession session3 = req.getSession();
            out.println("session3="+session3);

            //req.getSession(false): 获取当前请求关联的会话对象，如果会话已经存在，则返回会话对象，会话存在就是说服务端存在请求jsessionid所关联的对象，服务端认识这次请求
            // 如果会话不存在，则返回 null。会话不存在就是说服务端不存在请求jsessionid所关联的对象，服务端不认识这次请求
            //false 参数，告诉服务器不要创建新的会话对象
            session3.invalidate();
            HttpSession session4 = req.getSession(false);
            out.println("session4="+session4);
        }
        else {
            out.println("url is wrong");
        }

    }
}
