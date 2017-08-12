import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Lzyyy on 2017/8/11.
 */
public class CRUD {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        /*String sql = "insert into my_student(number,sex,name,id)values('itcast005','male','zhangsan','5');";
            String sql1 = "update my_student set id=id-1";//插入语句
            String sql2 = "delete from my_student where id>4";//删除语句*/
        System.out.println("请输入你的jdbc命令语句：");
        String str;
        str = buffer.readLine();
        Function fun = new Function(str);
        System.out.println("\n修改后的数据：");
        fun.jdbc();
    }

}
class Function
{
    String str;
    Function(String str)
    {
        this.str = str;
    }
    public void jdbc()throws Exception
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            //2.建立连接
            conn = JdbcUtils.getconnection();

            //3.创建语句
            st = conn.createStatement();

            //4.执行语句
            st.executeUpdate(str);//修改语句

            rs = st.executeQuery("select * from my_student;");

            //5.处理结果
            while (rs.next())
            {
                sop(rs.getString("id")+"\t"+rs.getObject("number")
                        +"\t"+rs.getString("name")+"\t\t"+rs.getString("sex"));
            }
        }
        finally {
            try
            {
                if(rs!=null)
                    rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                try{
                    if(st!=null)
                        st.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
                finally {
                    if(conn!=null)
                        try{
                            conn.close();
                        }catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}



