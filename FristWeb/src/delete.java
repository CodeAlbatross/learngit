import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lzyyy on 2017/8/28.
 */
@WebServlet(name = "delete")
public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String stdNumber = request.getParameter("stdNumber");

        PrintWriter out = response.getWriter();
        JDBC jdbc = new JDBC();
        String result = jdbc.CheckNum(stdNumber);
        if ("hassamesnumber".equals(result)) {
            jdbc.DetStu(stdNumber);
            out.println("删除成功");
           // request.setAttribute("msg", "删除成功");
            //request.getRequestDispatcher("/function.jsp").forward(request, response);
            return;
        }else {
            out.println("学号错误");
            //request.setAttribute("msg", "学号错误");
           // request.getRequestDispatcher("/function.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
