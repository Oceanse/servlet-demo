package filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter也称之为过滤器，它是Servlet技术中最实用的技术，WEB开发人员通过Filter技术，对web服务器管理的所有web资源：例如Jsp, Servlet,
 * 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
 * Servlet API中提供了一个Filter接口，开发web应用时，如果编写的Java类实现了这个接口，则把这个java类称之为过滤器Filter。通过Filter技术，开发人员可以实现用户在访问某个目标资源之前，对访问的请求和响应进行拦截
 * 过滤器拦截的本质是对URL访问的拦截（因为资源通过URL进行标识）
 * 在web.xml中注册过滤器,实现（继承）Filter接口,重写三个方法：init（初始化），doFilter（执行过滤），destroy（销毁）
 * Filter接口中有一个doFilter方法，当开发人员编写好Filter，并配置对哪个web资源(拦截url)进行拦截后，WEB服务器每次在调用web资源之前，都会先调用一下filter的doFilter方法
 * 因此，在该方法内编写代码可达到如下目的：1 调用目标资源之前，让一段代码执行; 2 是否调用目标资源（即是否让用户访问web资源）
 *
 * servlet可以在服务器已启动的时候就初始化，也可以是第一访问的时候进行初始化，而filter只能是服务器启动初始化
 * <p>
 * ；
 */
public class AuthenFilter implements Filter {


    /* web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置，完成对象的初始化功能，
     从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）。开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
     该方法会在目标servlet的init方法之前执行
     filter的初始化方法init默认在服务器启动时执行
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

        String filterName = config.getInitParameter("filter-name"); // 获取web.xml初始化参数
        System.out.println("init() in AuthenFilter class is called");
        System.out.println("filterName: " + filterName);  // 输出初始化参数
    }


    /**
     * 过滤器中我们可以根据 doFilte() 方法中的 request 对象获取表单参数信息，例如我们可以获取到请求的用户名和密码进行逻辑处理，
     * 也可以通过 response对用户做出回应。比如如果验证用户名不正确，禁止用户访问 web 资源，并且向浏览器输出提示，告诉用户用户名或者密码不正确等等
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse=(HttpServletResponse)response;
        httpResponse.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");

        //在页面输出响应信息
        PrintWriter out = httpResponse.getWriter();


        //web服务器在调用doFilter方法时，会传递一个FilterChain对象进来,它也提供了一个doFilter方法，开发人员可以根据需求决定是否调用此方法，
        //调用该方法，则web服务器就会放行request/respnse到链的下一个实体（这里是LogFilter），否则web 请求/响应到此结束
        if ("ocean".equals(name)) {
            out.write("AuthenFilter.doFilter() is called" + "</br></br>");
            out.write("name is " +  name + " and authentification is passed" + "<br/></br>");
            chain.doFilter(request, response);
            Cookie ck = new Cookie("name", name);
            httpResponse.addCookie(ck);//adding cookie in the response
        } else {
            out.print( name + " is wrong, you can not access the web, please re-login!" + "</br></br>");
            //转发到/filter/login.html， 并把其响应包含到当前servlet的响应，然后一并输出；这里的/代表应用上下文
            request.getRequestDispatcher("/filter/login.html").include(request, response);
        }
    }


    @Override
    public void destroy() {
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
        System.out.println("AuthenFilter destory is called");
    }

}
