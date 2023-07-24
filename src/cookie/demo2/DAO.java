package cookie.demo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    /**
     * 把传递过来的用户名和密码与数据库中的对比校验，这里的校验逻辑要求用户名不能重复
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        con = DBHelper.getConnection();
        String sql = "select * from user where username = ?";//查询语句，先把username设置为？，后面在赋值
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);//赋值
            rs = ps.executeQuery();//执行查询语句，返回一个ResultSet,这个就是我们数据库里面的user
            if (rs.next()) {//数据库中的username有notnull约束，如果这里用户名=null,会返回false
                String pwd = rs.getString("password");//找到数据类库面user所对应的password
                if (pwd.equals(password)) {//把我们从数据库中找出来的password和传过来的password比较
                    return true; //ture代表验证成功
                } else {
                    return false;//false代表验证失败
                }
            } else {
                return false;//说明指定username的用户不存在
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
        return false;
    }

    public static void register(String username, String password) {//向数据库注册一个新的用户
        con = DBHelper.getConnection();//通过DBHelper得到Connection
        String sql = "insert into user values (?,?)";//这个语句是向表单插入一个user,username和password先设置为？,后面赋值
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);//给username赋值
            ps.setString(2, password);//给password赋值
            int b = ps.executeUpdate();//执行插入语句，并返回一个int值，大于0表示添加成功，小于0表示添加失败.
            if (b > 0) {
                System.out.println("数据添加成功");
            } else {
                System.out.println("数据添加失败");
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
}
