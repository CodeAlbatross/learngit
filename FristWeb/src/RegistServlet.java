import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lzyyy on 2017/8/27.
 */
@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rpsw = request.getParameter("rpsw");
        PrintWriter out = response.getWriter();

        JDBC jdbc = new JDBC();
        String result = jdbc.CheckSame(username,password);
        if(username==null||username.trim().isEmpty()){
            out.println("帐号不能为空");
            //request.setAttribute("msg", "帐号不能为空");
            //request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }

        if(password==null||password.trim().isEmpty()){
            out.println("密码不能为空");
            //request.setAttribute("msg", "密码不能为空");
            //request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        if(!password.equals(rpsw)){
            out.println("两次输入的密码不同");
            //request.setAttribute("msg", "两次输入的密码不同");
            //request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        if("same username".equals(result))
        {
            out.println("账号重复");
            request.setAttribute("msg", "账号重复");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }

        jdbc.AddUser(username,password);
        out.println("ヾ(o◕∀◕)ﾉ success~");
        //request.setAttribute("msg","ヾ(o◕∀◕)ﾉ success~");
        //request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
