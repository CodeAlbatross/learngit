import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.lzyyy.Student.*;

/*
*
 * Created by Lzyyy on 2017/8/27.
*/


public class JDBC {
    String url = "jdbc:mysql://localhost:3306/studatabase?characterEncoding=utf8&useSSL=true";
    String user = "root";
    String password = "motomb200";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public JDBC(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }catch(SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    private ResultSet execSQL(String sql,Object... args) throws SQLException {
        conn = DriverManager.getConnection(url,user,password);

        //ResultSet rs = pstmt.executeQuery(sql);
        //建立PreparedStatement对象
        pstmt = conn.prepareStatement(sql);
        //为pStmt对象设置SQL参数值
        for(int i = 0; i < args.length; i++){

            pstmt.setObject(i+1, args[i]);
        }
        //执行SQL语句
        pstmt.execute();

        //返回结果集,如果执行的SQL语句不返回结果集，则返回null
        return pstmt.getResultSet();
    }
    private void closesql(){
        try{
            if(pstmt!=null)
                pstmt.close();
        }catch(SQLException se2){
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        try{
            if(rs!=null)
                rs.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public String CheckUser(String username,String password) {
        /*boolean has_username = false;
        boolean right_password = false;*/
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_info");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
            return null;
        }
        try {
            while (rs.next()) {
                String temp_username = rs.getString("name").trim();

                String temp_password = rs.getString("password").trim();

                if (username.equals(temp_username)) {

                    //has_username = true;

                    if (password.equals(temp_password)) {

                        //right_password = true;

                        return "hasUserNameAndPasswordCorrect";
                    }
                    return "hasUserNameButPasswordInCorrect";
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "hasNoUserName";
    }
    public void AddUser(String username,String password){
       //ResultSet rs = null;
        try{

            String sql = "insert into stu_info value("+"\'"+username+"\'"+","+"\'"+password+"\'"+")";
            rs = execSQL("select*from stu_info");

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);
            //System.out.println("怎么回事");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                closesql();
            }
            catch (Exception e) {
            }
        }

    }
    public String CheckSame(String username,String password)
    {
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_info");
        } catch (Exception e) {
            System.err.println("数据库错误");
            e.printStackTrace();
            return null;
        }
        try{
            while (rs.next())
            {
                String temp_username = rs.getString("name").trim();
                if (username.equals(temp_username))
                {
                    return "same username";
                }
            }

        }catch (Exception E) {}
        return null;
    }
    public void AddStu(String name ,String age ,String number,String major)
    {
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
        }
        try {
            String sql = "insert into stu_table value("+"\'"+name+"\'"+","+"\'"+age+"\'"+","+"\'"+number+"\'"+","+"\'"+major+"\'"+")";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            /*try {
                if(pstmt!=null)pstmt.close();
                if(conn!=null)conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }*/
            closesql();
        }

    }
    public String CheckStu(String name,String number)
    {
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错.");
            e.printStackTrace();
            return null;
        }
        try{
            while (rs.next())
            {
                String temp_name = rs.getString("stu_name").trim();

                String temp_number = rs.getString("stu_number").trim();


                    if(number.equals(temp_number))
                    {
                        return "hassamestudent";
                    }

            }
        }catch (Exception e){}
        return null;
    }
    public void DetStu(String number)
    {
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();

        }
        try{
            while (rs.next())
            {
                String temp_number = rs.getString("stu_number");

                if (temp_number.equals(number))
                {
                    String sql = "delete from stu_table where stu_number="+number;
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate(sql);
                    break;
                }
            }

        }catch (Exception e){}
        finally {
            closesql();
        }
    }
    public String CheckNum(String number)
    {
        //ResultSet rs = null;
        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
            return null;
        }
        try{
            while (rs.next())
            {

                String temp_number = rs.getString("stu_number").trim();

                    if(number.equals(temp_number))
                    {
                        return "hassamesnumber";
                    }
                }
            }
        catch (Exception e){}
        return "donotsamenumber";
    }
    public void ChangeStu(String name ,String age ,String number,String major)
    {
        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
        }
        try {
            String sql =
                    "update stu_table SET stu_name = "+
            "\'"+name+"\'"+","+"stu_age = "+"\'"+age+"\'"+","+"stu_number = "+"\'"+number+"\'"+","+"stu_major = "+"\'"+major+"\'"+" where stu_name = "+"\'"+name+"\'";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            closesql();
        }

    }
    public ArrayList SearchStu(String name,String stdNumber)
    {
        ArrayList<Student> list = new ArrayList<Student>();

        try {
            rs = execSQL("select*from stu_table");
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
        }
        try{
            while (rs.next())
            {
                String temp_name = rs.getString("stu_name").trim();
                String temp_number = rs.getString("stu_number").trim();
                if(temp_name.equals(name) || temp_number.equals(stdNumber))
                {
                    Student student = new Student();
                    student.setName(rs.getString("stu_name"));
                    student.setAge(rs.getString("stu_age"));
                    student.setNumber(rs.getString("stu_number"));
                    student.setMajor(rs.getString("stu_major"));
                    list.add(student);
                    /*System.out.println(student.getName());
                    System.out.println(student.getAge());
                    System.out.println(student.getNumber());
                    System.out.println(student.getMajor());*/
                }
            }

            return list;
        }catch (Exception e){}
        finally {
            closesql();
        }
return null;
    }
}

