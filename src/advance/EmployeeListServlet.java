package advance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/advance/employeeList")
public class EmployeeListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 在此处获取员工列表数据（可以从数据库中获取）
        List<Employee> employeeList = DAO.getAllEmployees();
        System.out.println("employeeList = " + employeeList);
        // 将员工列表数据存储到会话属性中
        request.getSession().setAttribute("employeeList", employeeList);
        // 转发到员工列表 JSP 页面
        request.getRequestDispatcher("/advance/employee-list.jsp").forward(request, response);
    }
}
