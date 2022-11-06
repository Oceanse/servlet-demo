import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/errorcode")
public class StatusCodeDemo extends HttpServlet {
    //设置 HTTP 状态码
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        // 设置错误代码和原因
        response.sendError(401, "Need authentication!!!" );
    }
}
