<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <title>在JSP使用JavaBeans示例</title>
</head>

<body>
<div style="margin:auto;text-align:center;">
    <h2>在JSP使用JavaBeans示例</h2>
    <hr/>
    <jsp:useBean id="msg" class="usebean.Message" />
    <p>获取默认设置的信息是：<jsp:getProperty name="msg" property="message" /></p>

    <jsp:setProperty name="msg" property="message" value="Hello JSP..." />
    <p>获取设置的信息是：<jsp:getProperty name="msg" property="message" /></p>

</div>
</body>
</html>