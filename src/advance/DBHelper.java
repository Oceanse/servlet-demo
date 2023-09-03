package advance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static final String driver="com.mysql.cj.jdbc.Driver";

    public static final String url="jdbc:mysql://172.16.12.128:3306/HRdb?useSSL=false";

    public static final String username="root";

    public static final String password="123123";

    static{
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每次调用都会返回一个新的连接
     * @return
     */
    public  static Connection getConnection(){
          Connection con=null;
            try {
                con= DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
    }
}
