package advance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/ServletDemo_war_exploded/advance/register.jsp
*/
@WebServlet("/advance/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("registering....");
        resp.setContentType("text/html;charset=UTF-8");//设置响应编码，防止out.println(中文)出现乱码
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String department = req.getParameter("department");
        DAO.register(name,password,department); //dao层中向数据库添加数据
        out.println("您已成功注册!马上跳转到登录界面进行登录...");
        String mainPage=req.getContextPath()+"/advance/login.jsp";
        resp.setHeader("refresh", String.format("3;%s",mainPage));
    }
}