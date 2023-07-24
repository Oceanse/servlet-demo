package session.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据库
 */
class DB {
    private static List<User> list = new ArrayList();

    static {
        list.add(new User("ocean", "123",33));
        list.add(new User("phy", "123",33));
        list.add(new User("mxz", "123",57));
        list.add(new User("pza", "123",67));
    }

    //查找数据库中是否有该用户
    public static User find(String username, String password) {
        for (User user : list) {

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

