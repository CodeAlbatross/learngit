import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Lzyyy on 2017/8/30.
 */
@WebServlet(name = "CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    //验证码生成库
    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //随机颜色
    private static Color getRandomColor(int minColor,int maxColor)
    {
        Random random = new Random();

        if(minColor > 255){

            minColor = 255;
        }

        if(maxColor > 255){

            maxColor = 255;
        }

        //获得r的随机颜色值
        int red = minColor + random.nextInt(maxColor-minColor);

        int green = minColor + random.nextInt(maxColor-minColor);

        int blue = minColor + random.nextInt(maxColor-minColor);

        return new Color(red,green,blue);
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int charLength = codeChars.length();
         //关闭缓存区
         response.setHeader("ragma", "No-cache");

         response.setHeader("Cache-Control", "no-cache");

         response.setDateHeader("Expires", 0);

         int wid = 90, high = 30;
         BufferedImage image = new BufferedImage(wid,high,BufferedImage.TYPE_INT_RGB);

         Graphics g = image.getGraphics();//获得用于输出文字的Graphics对象
         Random random = new Random();
         g.setColor(getRandomColor(180, 250));

         g.fillRect(0, 0, wid, high);

         g.setFont(new Font("Times New Roman",Font.ITALIC,high));

         g.setColor(getRandomColor(120, 180));
         StringBuilder checkcode = new StringBuilder();//用户保存最后随机生成的验证码
         //随机字体
         String[] fontNames= {"Times New Roman","Book antiqua","Arial"};
         //随机生成4个验证码
         for (int i=0;i<4;i++)
         {
             //随机设置当前验证码的字符的字体
             g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,high));
             //随机获得当前验证码的字符
             char codeChar = codeChars.charAt(random.nextInt(charLength));
             checkcode.append(codeChar);
             //随机设置当前验证码字符的颜色
             g.setColor(getRandomColor(10, 100));
             //在图形上输出验证码字符，x和y都是随机生成的
             g.drawString(String.valueOf(codeChar), 16*i + random.nextInt(7), high-random.nextInt(6));
         }
         HttpSession session = request.getSession();
         session.setMaxInactiveInterval(5*60);
         session.setAttribute("aa",checkcode.toString());
         g.dispose();
         /*OutputStream outs = response.getOutputStream();
         ImageIO.write(image,"JPEG",outs);*/
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
         encoder.encode(image);

     }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

