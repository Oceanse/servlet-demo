package get_httprequest_response_info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


/**
 * http://localhost:8080/ServletDemo_war_exploded/requestinfo?name=ocean&pd=123
 */
@WebServlet("/requestinfo")
public class GetHttpServletRequestInfo extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();


        //该方法用于获取HTTP请求消息中的请求方式（如GET、POST等）
        out.println("getMethod:" + request.getMethod() + "<br/>");//getMethod:GET

        //协议相关
        //该方法用于获取请求行中的协议名和版本
        out.println("getProtocol:" + request.getProtocol() + "<br/>");//getMethod:GET

        //该方法用于获取请求的协议名，例如http、https或ftp
        out.println("getScheme:" + request.getScheme() + "<br/>");//getScheme:http


        //路径相关
        //该方法用于获取客户端发出请求时的完整URL，包括协议、服务器名、端口号、资源路径等信息，但不包括后面的查询参数部分。注意，getRequestRUL()方法返回的时StringBuffer类型，而不是String类型。
        out.println("getRequestURL:" + request.getRequestURL() + "<br/>");//getRequestURL:http://localhost:8080/ServletDemo_war_exploded/requestinfo

        //该方法用于获取请求URL中属于WEB应用程序的路径(项目上下文路径)，这个路径以"/"开头，表示相对于整个WEB站点的根目录
        out.println("getContextPath" + request.getContextPath() + "<br/>");//getContextPath/ServletDemo_war_exploded






        //客户端和服务端的ip/port
        //该方法用于获取当前请求所指向的主机名，即HTTP请求消息中HOST头字段所对应的主机名部分
        out.println("getServerName:" + request.getServerName() + "<br/>");//getServerName:localhost

        //该方法用于获取当前请求所连接的服务器端口号，即如果HTTP请求消息中HOST头字段所对应的端口号部分
        out.println("getServerPort:" + request.getServerPort() + "<br/>");//getServerPort:8080

        //该方法用于获取客户端的IP地址，其格式类似于"192.168.0.1"(由于是服务器在问，所以客户是远程的)
        out.println("getRemoteAddr:" + request.getRemoteAddr() + "<br/>");//getRemoteAddr:0:0:0:0:0:0:0:1

        //该方法用于获取请求客户端的完整主机名，其格式类似于"pc1.xxxx.cn"。需要注意的是，如果无法解析出客户机的完整主机名，该方法会返回客户端的IP地址(由于是服务器在问，所以客户是远程的)
        out.println("getRemoteHost:" + request.getRemoteHost() + "<br/>");//getRemoteHost:0:0:0:0:0:0:0:1

        //发出请求的客户的端口号。
        out.println("getRemotePort:" + request.getRemotePort() + "<br/>");//getRemotePort:51550





        //请求参数
        //资源路径后面问号以后的所有内容
        out.println("<br/>"+ "<br/>");
        out.println("getQueryString:" + request.getQueryString() + "<br/>");//getQueryString:name=ocean&pd=123

        //该方法用于获取某个指定名称的参数值,如果请求消息中没有包含指定名称的参数返回null
        out.println("request.getParameter: "+request.getParameter("name")+ "<br/>");//request.getParameter: ocean

        Enumeration paramNames = request.getParameterNames();
        //使用循环遍历全部请求参数
        while (paramNames.hasMoreElements()){
            String paramName = (String) paramNames.nextElement();
            out.println(paramName + " : " + request.getParameter(paramName) + "<br/>");
        }




        //使用循环遍历全部请求header
        out.println("<br/>"+ "<br/>");
        Enumeration headerNames = request.getHeaderNames();
        //使用循环遍历请求头，并通过getHeader()方法获取一个指定名称的头字段
        while (headerNames.hasMoreElements()){
            String headerName = (String) headerNames.nextElement();
            out.println(headerName + " : " + request.getHeader(headerName) + "<br/>");
        }
    }





}
