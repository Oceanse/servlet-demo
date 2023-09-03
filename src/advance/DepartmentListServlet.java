package advance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/advance/departmentList")
public class DepartmentListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 在此处获取部门列表数据（可以从数据库中获取）
        List<Department> departmentList = DAO.getAllDepartments();
        System.out.println("departmentList = " + departmentList);
        // 将部门列表数据存储到会话属性中
        request.getSession().setAttribute("departmentList", departmentList);
        // 转发到部门列表 JSP 页面
        request.getRequestDispatcher("/advance/department-list.jsp").forward(request, response);
    }
}