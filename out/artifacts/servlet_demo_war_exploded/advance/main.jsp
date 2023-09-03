<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        h1 {
            color: #333;
        }

        p {
            font-size: 18px;
            color: #666;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to the Main Page</h1>
    <%
        String username = (String) session.getAttribute("username");
        if (username != null) {
    %>
    <p>Welcome, <%= username %>!</p>
    <%
    } else {
    %>
    <p>Please register or login</p>
    <%
        }
    %>
    <p><a href="${pageContext.request.contextPath}/advance/employeeList">Employee List</a></p>
    <p><a href="${pageContext.request.contextPath}/advance/departmentList">Department List</a></p>
    <p><a href="register.jsp">Register</a></p>
    <p><a href="login.jsp">Login</a></p>
    <p><a href="${pageContext.request.contextPath}/advance/logout">Logout</a></p>
</div>
</body>
</html>