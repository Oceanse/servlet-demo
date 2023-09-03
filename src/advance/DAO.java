package advance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread.sleep(1000);
            checkLogin2("ocean", "123");
        }

    }

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    /**
     * 把传递过来的用户名和密码与数据库中的对比校验，这里的校验逻辑要求用户名不能重复
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        con = DBHelper.getConnection();
        String sql = "select * from employee where name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString("password");
                if (pwd.equals(password)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //这里是一些操作数据库之后的一些关闭操作
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ps = null;
            }
        }
        return false;
    }

    /**
     * try-with=resource自动释放资源
     * 把传递过来的用户名和密码与数据库中的对比校验，这里的校验逻辑要求用户名不能重复
     * 关闭流的时候应该先关闭子流，再关闭父流，所以按照ResultSet---->PreparedStatement---->Connection顺序关闭
     * try-with=resource 在try代码块执行完毕后(即使try快内部发生异常)，按照声明的相反顺序进行流的关闭；
     * 但是流关闭后只是释放流资源，对应的对象(比如Connection)还存在，当使用已经关闭的Connection对象会产生：No operations allowed after connection closed
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin2(String username, String password) {

        String sql = "select * from employee where name = ?";
        try (Connection con = DBHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pwd = rs.getString("password");
                    if (pwd.equals(password)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void register(String name, String password, String department) {//向数据库注册一个新的用户
        con = DBHelper.getConnection();//通过DBHelper得到Connection
        String sql = "insert into employee (name,password,department) values (?,?,?)";//这个语句是向表单插入一个user,username和password先设置为？,后面赋值
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, department);
            int b = ps.executeUpdate();//执行插入语句，并返回一个int值，大于0表示添加成功，小于0表示添加失败.
            if (b > 0) {
                System.out.println("新员工注册成功");
            } else {
                System.out.println("新员工注册失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //这里是一些操作数据库之后的一些关闭操作
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ps = null;
            }
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT * FROM employee";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // 处理结果集
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setPassword(rs.getString("password"));
                employee.setDepartment(rs.getString("department"));
                employees.add(employee);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return employees;
    }


    public static List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT * FROM department";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // 处理结果集
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return departments;
    }

}
