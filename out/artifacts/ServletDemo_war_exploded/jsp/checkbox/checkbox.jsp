<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>复选框数据处理示例</title>
</head>
<body>
<div style="margin: auto; width: 80%;">
    <h2>复选框数据处理示例</h2>
    <ul>
        <li><p>
            <b>数学:</b>
            <%=request.getParameter("maths")%>
        </p></li>
        <li><p>
            <b>物理:</b>
            <%=request.getParameter("physics")%>
        </p></li>
        <li><p>
            <b>化学:</b>
            <%=request.getParameter("chemistry")%>
        </p></li>
    </ul>
</div>
</body>
</html>