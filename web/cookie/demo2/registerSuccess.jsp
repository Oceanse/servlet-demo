<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register success</title>
</head>
<body>
<h1>
    <%
    out.print("Register success and will go to login page after 3 seconds");
    //3s 后跳转到登录界面
    response.setHeader("Refresh","3;URL=/ServletDemo_war_exploded/cookie/demo2/login.html");
    %>
</h1>
</body>
</html>