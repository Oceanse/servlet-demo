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
    <style>
      h1 {
        text-align: center;
      }
      body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
      }

      form {
        width: 300px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }

      input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
      }

      input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }

      input[type="submit"]:hover {
        background-color: #0056b3;
      }
    </style>
  </head>
  <body>
  <h1>欢迎来到Servlet</h1>
  <form action="${pageContext.request.contextPath}/hello" method="POST">
    name: <input type="text" name="name" value="ocean">
    <input type="submit" value="Submit">
  </form>
  </body>
</html>
