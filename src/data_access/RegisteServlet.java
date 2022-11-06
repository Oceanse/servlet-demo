package data_access;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*

注册是从注册界面得到username和password，再通过dao层中registe()来向数据库添加一条用户
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
        DAO.registe(username, password); //dao层中向数据库添加数据
        out.print("register success, please login");
        req.getRequestDispatcher("/data_access/index.html").include(req, resp);//重新跳转到登录界面
    }
}