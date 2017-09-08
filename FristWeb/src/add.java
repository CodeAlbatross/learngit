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
@WebServlet(name = "add")
public class add extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String stdNumber = request.getParameter("stdNumber");
        String major = request.getParameter("major");
        //PrintWriter out = response.getWriter();

        JDBC jdbc = new JDBC();
        System.out.println("正确");
        String result = jdbc.CheckStu(name,stdNumber);
        if(name != null && !name.trim().isEmpty())
        {
            if(age !=null && !age.trim().isEmpty())
            {
                if (stdNumber != null && !stdNumber.trim().isEmpty())
                {
                    if (major != null && !major.trim().isEmpty())
                    {
                        for (int i=age.length();--i>0;)
                        {
                            if (!Character.isDigit(age.charAt(i))){
                                //out.println("年龄请输入数字");
                                request.setAttribute("msg", "年龄请输入数字");
                                request.getRequestDispatcher("/function.jsp").forward(request, response);
                                return;
                            }
                        }

                       if(!"hassamestudent".equals(result)) {
                            jdbc.AddStu(name, age, stdNumber, major);

                            request.setAttribute("msg", "添加成功！");
                            request.getRequestDispatcher("/function.jsp").forward(request, response);
                            return;
                        } else {
                            //out.println("学号重复");
                            request.setAttribute("msg", "学号重复");
                            request.getRequestDispatcher("/function.jsp").forward(request, response);
                            return;
                        }
                    }else{
                        //out.println("请选择专业");
                        request.setAttribute("msg", "请选择专业");
                        request.getRequestDispatcher("/function.jsp").forward(request, response);
                        return;
                    }
                }else{
                    //out.println("学号不能为空");
                    request.setAttribute("msg", "学号不能为空");
                    request.getRequestDispatcher("/function.jsp").forward(request, response);
                    return;
                }
            }else{
                //out.println("年龄不能为空");
                request.setAttribute("msg", "年龄不能为空");
                request.getRequestDispatcher("/function.jsp").forward(request, response);
                return;
            }
        }else{
            //out.println("请填写姓名");
            request.setAttribute("msg", "请填写姓名");
            request.getRequestDispatcher("/function.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
