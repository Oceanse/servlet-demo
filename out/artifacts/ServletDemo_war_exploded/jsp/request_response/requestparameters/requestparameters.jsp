<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>获取所有表单数据</title>
</head>
<body>
<div style="margin: auto; width: 80%;">
    <h2>获取所有表单数据</h2>
    <table width="100%" border="1" align="center">
        <tr bgcolor="#949494">
            <th>Param Name</th>
            <th>Param Value(s)</th>
        </tr>
        <%
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                out.print("<tr><td>" + paramName + "</td>\n");
                String paramValue = request.getParameter(paramName);
                out.println("<td> " + paramValue + "</td></tr>\n");
            }
        %>
    </table>
</div>
</body>
</html>