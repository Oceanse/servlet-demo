<%--
  Created by IntelliJ IDEA.
  User: epanhai
  Date: 2/19/2020
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Hello</title>
  </head>
  <body>
  <h1>你看到的是index.jsp</h1>

  <p>index.jsp是整个工程的首页，http://服务器的IP地址:tomcat端口号/项目工程名访问的就是index.jsp</p>


  GET请求：点击提交按钮后访问的url是http://localhost:8080/MyServlet_war_exploded/demo?name=xxx&passwd=123<br/><br/>
  POST请求：点击提交按钮后访问的url是http://localhost:8080/MyServlet_war_exploded/demo<br/><br/>
  所以表单包含密码等敏感数据时候建议POST提交<br/><br/>


  action：用来标识将表单交给谁去处理<br/>
          1.以"/"开头的表示绝对地址，即web根目录，比如你用tomcat服务器，那么就是指webapp目录了
            也就是http://localhost:8080。 所以action以/开始时候必须要带上项目名，后面再加上servlet的url-pattern
            所以下面可以这样写： action="/ServletDemo_war_exploded/demo"， 对应的url就是http://localhost:8080/MyServlet_war_exploded/demo<br/>
          2. 不以"/"开头的表示的是相对地址，即相对于当前这个页面的地址,当前页面地址是http://localhost:8080/MyServlet_war_exploded。
             action="demo"请求的url就是http://localhost:8080/MyServlet_war_exploded/demo
  <form action="demo" method="POST">
    name:<br/>
    <input type="text" name="name">  <br/>

    passwd:<br/>
    <input type="text" name="passwd" value="123">
    <br><br>
    <input type="submit" value="Submit">
  </form>
  </body>
</html>
