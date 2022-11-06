package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    //这个是添加的jar包里面的一个类。可以在referenced Libraries里面找到

    public static final String driver="com.mysql.jdbc.Driver";

/*这个是本地数据库中的一个database，名字叫User.localhost表示自己的电脑，
3306是一个端口，表示访问的是数据库。后面?之后的就代表的是编号格式以及使用ssl
协议，想了解的可以百度下。
*/

    public static final String url="jdbc:mysql://192.168.56.115:3306/User?useSSL=false";

    public static final String username="root";

    public static final String password="123123";

    public static Connection con=null;

    static{
        try {
            Class.forName(driver);//得到DriverManager，在下面建立连接时使用

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(con==null){
            try {
                con= DriverManager.getConnection(url,username,password);//建立连接,使用的参数就是上面我们所定义的字符串常量。
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
