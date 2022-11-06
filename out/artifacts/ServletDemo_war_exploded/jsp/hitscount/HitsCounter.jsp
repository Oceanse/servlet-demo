<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>页面点击统计</title>
</head>
<body>
<div style="margin: auto; width: 80%">
    <%
        Integer hitsCount = (Integer) application.getAttribute("hitCounter");
        if (hitsCount == null || hitsCount == 0) {
            /* First visit */
            out.println("欢迎您来到我的网站!");
            hitsCount = 1;
        } else {
            /* return visit */
            out.println("欢迎您再次访问我的网站!");
            hitsCount += 1;
        }
        application.setAttribute("hitCounter", hitsCount);
    %>
    <center>
        <p>
            访问总数：<%=hitsCount%></p>

    </center>
</div>
</body>
</html>
