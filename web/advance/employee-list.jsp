<%@ page import="advance.Employee" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h1>Employee List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Department</th>
    </tr>
    <%
        List<Employee> employeeList = (List<Employee>) session.getAttribute("employeeList");
        if (employeeList != null) {
            for (Employee employee : employeeList) {
    %>
    <tr>
        <td><%= employee.getId() %></td>
        <td><%= employee.getName() %></td>
        <td><%= employee.getPassword() %></td>
        <td><%= employee.getDepartment() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
<a href="main.jsp">Back to Main Page</a>
</body>
</html>

