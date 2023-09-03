import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * http://localhost:8080/ServletDemo_war_exploded/hello
 * HttpServlet提供了 Http 相关的方法，它继承 GenericServlet 类，而 GenericServlet 类又实现了 Servlet 接口和 ServletConfig接口
 */
public class HelloServlet extends HttpServlet {

    private String message;

    /**
     * 通过web.xml中的全类名和反射机制，调用此构造方法，完成servlet实例的创建
     * <load-on-startup>1</load-on-startup>, 所以构造方法启动服务器时候就会被执行
     */
    public HelloServlet() {
        System.out.println("HelloServlet() is invoked");
    }


    /**
     * 实例化时候就会被调用，且只调用一次；
     * <load-on-startup>1</load-on-startup>, 所以构造方法启动服务器时候就会被执行
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // 初始化
        message = "Hello, First Servlet!";
        System.out.println("HelloServlet.init() is called");
    }


    /**
     * 页面提交后，请求参数放置在请求体中，不会呈现在url中
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //个人理解这个参数就是指定请求参数(post方式提交的参数存在于请求体中,get提交的参数存在于url)编码方式
        //不指定的话使用iso-8859-1，解析中文请求参数时候会出现乱码
        request.setCharacterEncoding("UTF-8");


        //设置响应内容的MIME类型,这里返回的是html类型，浏览器会将源码渲染成我们看到的网页但是postman测试的话因为无法渲染就会返回html源码，
        //若设置成response.setContentType("text/plain"); 那么浏览器无法渲染html标签，呈现的也将是html源码
        //若不设置，content-type默认是text/plain, 浏览器将不能展现出响应中的html标签
        //这里也对响应内容的编码方式进行了设置，客户端会利用改编码方案进行解码，若设置为utf-8, 结果就是out.print可以打印中文
        response.setContentType("text/html;charset=UTF-8");

        // 输出文本
        PrintWriter out = response.getWriter();
        out.write("<h1> " + message + " </h1>");

        //http://localhost:8080/ServletDemo_war_exploded 提交登录数据
        String name = request.getParameter("name");
        ServletConfig servletConfig = this.getServletConfig();
        String chineseName = servletConfig.getInitParameter("chineseName");
        out.write("<h1> Welcome " + name + "（" + chineseName + "）" + "! </h1>");
    }


    /**
     * 页面提交后，请求参数会呈现在url中，相对不安全
     * http://localhost:8080/ServletDemo_war_exploded/demo?name=ocean&passwd=123
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //个人理解这个参数就是指定请求参数(post方式提交的参数存在于请求体中,get提交的参数存在于url)编码方式
        //不指定的话使用iso-8859-1，解析中文请求参数时候会出现乱码
        request.setCharacterEncoding("UTF-8");


        //设置响应内容的MIME类型,这里返回的是html类型，浏览器会将源码渲染成我们看到的网页但是postman测试的话因为无法渲染就会返回html源码，
        //若设置成response.setContentType("text/plain"); 那么浏览器无法渲染html标签，呈现的也将是html源码
        //若不设置，content-type默认是text/plain, 浏览器将不能展现出响应中的html标签
        //这里也对响应内容的编码方式进行了设置，客户端会利用改编码方案进行解码，若设置为utf-8, 结果就是out.print可以打印中文
        response.setContentType("text/html;charset=UTF-8");

        // 输出文本
        PrintWriter out = response.getWriter();
        out.write("<h1> " + message + " </h1>");

        //http://localhost:8080/ServletDemo_war_exploded 提交登录数据
        String name = request.getParameter("name");
        ServletConfig servletConfig = this.getServletConfig();
        String chineseName = servletConfig.getInitParameter("chineseName");
        out.write("<h1> Welcome " + name + "（" + chineseName + "）" + "! </h1>");
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet.destroy is called");
    }
}
