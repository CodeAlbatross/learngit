import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import com.lzyyy.Student.Student;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lzyyy on 2017/8/28.
 */
@WebServlet(name = "search")
public class search extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=GBK");
        String name = request.getParameter("name");
        String stdNumber = request.getParameter("stdNumber");
        PrintWriter out = response.getWriter();

        JDBC jdbc = new JDBC();
        List<Student> list;
        list = jdbc.SearchStu(name,stdNumber);
       /* for (Student student : list)
        {
            System.out.println(student.getName());
            System.out.println(student.getNumber());
            System.out.println(student.getAge());
            System.out.println(student.getMajor());
        }*/

        if ((name != null && !name.trim().isEmpty()) | (stdNumber != null && !stdNumber.trim().isEmpty()))
        {
            if(!list.isEmpty()){
            request.setAttribute("list",list);
           // request.setAttribute("msg", "查询成功");
            //out.println("查询成功！");
            for (Student student : list)
            {
                out.println("<tr>");
                out.println("<td>"+student.getName()+"</td>");
                out.println("<td>"+student.getNumber()+"</td>");
                out.println("<td>"+student.getAge()+"</td>");
                out.println("<td>"+student.getMajor()+"</td>");
                out.println("</tr>");
            }

            //request.getRequestDispatcher("/function.jsp").forward(request, response);
            return;
        }else
        {
            out.println("无数据");
            //request.getRequestDispatcher("/function.jsp").forward(request, response);
            return;
        }}else {
            out.println("请输入数据");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
