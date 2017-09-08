
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.*;


/**
 * Created by Lzyyy on 2017/8/27.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=GBK");
        String username = request.getParameter("uuid");
        String password = request.getParameter("passwd");
        String checkcode = request.getParameter("checkcode");

        HttpSession session = request.getSession();

        String check_code = (String)session.getAttribute("aa");
        PrintWriter out = response.getWriter();

        System.out.println(username+password+checkcode);
        System.out.println(check_code);
        System.out.println(username+password);

        JDBC jdbc = new JDBC();
        String result = jdbc.CheckUser(username,password);
        if (checkcode.equalsIgnoreCase(check_code)) {
            if ("hasUserNameAndPasswordCorrect".equals(result)) {

                System.out.println("用户名和密码均正确");
                session.setAttribute("name",username);
                session.setAttribute("password",password);
                out.println('1');

            } else if ("hasUserNameButPasswordInCorrect".equals(result)) {


                out.println("密码错误请重新输入！");

                return;

            } else if ("hasNoUserName".equals(result)) {

                out.println("用户名错误！");
                return;
            }
        }else {
            out.println("验证码错误");
        }


    }
}
