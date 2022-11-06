package data_access;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logins")
public class LoginServlet extends HttpServlet {



    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }



     //doGet方法判断从jsp传过来的username和password是否和数据库中的对应，如果对应，则跳转到欢迎界面，如果不对应，则返回登录界面
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");//指定请求参数(post方式提交的参数存在于请求体中,get提交的参数存在于url)编码方式
        String username = req.getParameter("username");//从jsp中获取usernmae
        String password = req.getParameter("password");//从jsp中获取password
        if (DAO.checkLogin(username, password)) { //dao层中判断，如果为true，跳转到欢迎界面
            Cookie nameCookie=new Cookie("username",username);
            Cookie passwdCookie=new Cookie("password",password);
            resp.addCookie(nameCookie);
            resp.addCookie(passwdCookie);
        req.setAttribute("username", username);
         req.getRequestDispatcher("/data_access/success.jsp").forward(req, resp);
        }else{//如果为false，跳转到登录界面，并返回错误信息.
            out.print("your username or passwd is wrong, please register or login again"+"<br/>");
            req.getRequestDispatcher("/data_access/index.html").include(req, resp);
        }


    }
}
