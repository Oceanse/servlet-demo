package redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/ServletDemo_war_exploded/search?keyword=xxx
 * In this example, we are using sendRedirect method to send request to google server with the request data.
 */
@WebServlet("/search")
public class GoogleSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name=request.getParameter("keyword");
        response.sendRedirect("https://www.google.co.in/#q="+name);
    }
}