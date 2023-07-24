package cookie.demo2;

import cookie.demo2.DAO;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 网页登录：http://localhost:8080/ServletDemo_war_exploded/cookie/demo2/index.html
 * http://localhost:8080/ServletDemo_war_exploded/cookie/demo2/register.html
*/
@WebServlet("/register")
public class RegisteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("registering....");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");//设置编码格式为utf-8
        String username = req.getParameter("username");//从注册界面获得username
        String password = req.getParameter("password");//从注册界面获得password
        DAO.register(username, password); //dao层中向数据库添加数据
        out.print("register success, please login");

        resp.sendRedirect("/ServletDemo_war_exploded/cookie/demo2/registerSuccess.jsp");//重定向到注册成功界面
    }
}