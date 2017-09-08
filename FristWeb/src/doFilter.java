import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lzyyy on 2017/9/3.
 */
@WebFilter(filterName = "doFilter")
public class doFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        System.out.println("拦截器启动");
        HttpServletRequest servletRequest = (HttpServletRequest)req;
        HttpServletResponse servletResponse = (HttpServletResponse)resp;
        HttpSession session = servletRequest.getSession();
        PrintWriter out = resp.getWriter();
        //从session中获取用户名和密码
        String name = (String)session.getAttribute("name");
        String password = (String)session.getAttribute("password");
        //判断用户是否登陆，session中是否为空。
        if (name == null || password == null)
        {
           out.println("你尚未登陆");
           System.out.println("正在跳转");
           //servletResponse.sendRedirect("login.jsp");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

            System.out.println("正在跳转。。。。");
        }else {
            //chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
