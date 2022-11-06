import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * 通过继承HttpServlet创建Servlet是我们经常用的方法，
 * HttpServlet提供了 Http 相关的方法，它继承 GenericServlet 类，而 GenericServlet 类又实现了 Servlet 接口和 ServletConfig接口
 * http://localhost:8080/MyServlet_war_exploded/ 可以理解为根路径，默认是访问根路径下的index.jsp
 * http://localhost:8080/MyServlet_war_exploded/demo 根据web.xml映射访问HelloServlet
 * HelloServlet在容器中只存在一个对象，也就是在容器环境中是单例的，多个线程共享这一个对象

 */
public class HelloServlet extends HttpServlet {

    private String message;

    /**
     * 通过web.xml中的全类名和反射机制，调用此构造方法，完成servlet实例的创建
     */
    public HelloServlet() {
        System.out.println("HelloServlet() is invoked");
    }


    /**
     * 实例化时候会被调用，且只调用一次；
     * 在服务器启动或者 第一次调用对应于该Servlet 的 URL 时时被加载。
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // 初始化
        message = "Hello, First Servlet!";
        System.out.println("init() in HelloServlet class is called");
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

        //Overrides the name of the character encoding used in the body of this request.
        // This method must be called prior to reading request parameters.
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


        //http://localhost:8080/MyServlet_war_exploded/demo
        //处理index.jsp中的表单数据
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        out.write("<h1> " + name + " </h1>");
        out.write("<h1> " + passwd + " </h1>");


        //ServletConfig is created by the web container for each servlet
        // and can be used to get configuration information from web.xml file.
        ServletConfig servletConfig = this.getServletConfig();
        String charSet = servletConfig.getInitParameter("charSet");
        out.write("<h1> " + charSet + " </h1>");
        out.write("你好啊");
        System.out.println("doPost is called");
    }


    /**
     * 页面提交后，请求参数会呈现在url中，相对不安全
     * http://localhost:8080/ServletDemo_war_exploded/demo?name=ocean&passwd=123
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容的MIME类型,这里返回的是html类型，postman测试的话就会返回html源码，浏览器会将源码渲染成我们看到的网页
        //若设置成  response.setContentType("text/plain"); 那么浏览器无法渲染html标签，呈现的也将是html源码
        //这里也对响应内容的编码方式进行了设置，客户端会利用改编码方案进行解码，若设置为utf-8, 结果就是out.print可以打印中文
        response.setContentType("text/html;charset=UTF-8");

        // 输出文本
        PrintWriter out = response.getWriter();
        out.write("<h1> " + message + " </h1>");

        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        out.write("<h1> " + name + " </h1>");
        out.write("<h1> " + passwd + " </h1>");
        out.write("你好啊");
        System.out.println("doGet is called");
    }

    @Override
    public void destroy() {
        System.out.println("destroy is called");
    }
}
