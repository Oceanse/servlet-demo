<%@ page import="advance.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="advance.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Department List</title>
</head>
<body>
<h1>Department List</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
  </tr>
  <%
    List<Department> departmentList = (List<Department>) session.getAttribute("departmentList");
    if (departmentList != null) {
      for (Department department : departmentList) {
  %>
  <tr>
    <td><%= department.getId() %></td>
    <td><%= department.getName() %></td>
  </tr>
  <%
      }
    }
  %>
</table>
<a href="main.jsp">Back to Main Page</a>
</body>
</html>
