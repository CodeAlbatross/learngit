
import java.io.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

class test {

    public static void main(String args[]) throws IOException
    {
        int i;
        function b = new function();
        Scanner out = new Scanner(System.in);
        jiemian a = new jiemian();
        a.dayin();
        while (true) 
        {
            System.out.println("请开始你的操作");
            System.out.println("  1.开始");
            System.out.println("  2.猜数英雄榜");
            System.out.println("  0.结束游戏");
            i = out.nextInt();
            switch (i) {
                case 1:

                    b.caishu();
                    break;
                case 2:
                    b.paihang();
                    break;
                case 0:
                    System.out.println("game over");
                    System.exit(0);
            }
        }
    }
}class jiemian
{
    public void dayin()
    {
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("*                        *");
        System.out.println("*  欢迎进入猜数游戏v1.0  *");
        System.out.println("*                        *");
        System.out.println("--------------------------");
    }
}
class function//功能类
{
    public void caishu() throws IOException
    {
        int num,i,j;
        Scanner out = new Scanner(System.in);
        num =  Random();
        System.out.println("请输入你所猜的一个不大于100的数字：");
        i = out.nextInt();
        for( j=1;i!=num; )
        {

            if(i<num && i>0)
            {
                System.out.println("小了");
                j++;
            }
            else if(i>num && i<100)
            {
                System.out.println("大了");
                j++;
            }
            else
            {
                System.out.println("输入有误");
            }
            System.out.println("请输入你所猜的一个不大于100的数字：");
            i = out.nextInt();
        }
        System.out.println("你简直是个天才，答案为"+num);
        System.out.printf("你一共猜了%d次\n",j);
        FileWriter fw = new FileWriter("paihangbang.txt",true);
        fw.write(String.format("%d,",j));
        fw.close();
        System.out.println("想不想再来一次？（1.yes/2.no）");
        int i1 = out.nextInt();
        if(i1==2)
        {
            System.out.println("game over");
            System.exit(0);
        }

    }
    public void paihang() throws IOException
    {
        int i,ch,j=1;
        FileWriter fw = new FileWriter("paihangbang.txt",true);
        FileReader fr = new FileReader("paihangbang.txt");
        StringBuffer str = new StringBuffer();
        while ((ch=fr.read())!=-1)
        {
            str.append((char)ch);
        }
        String str1 = str.toString();

        int[] strint = new int[1024];
        //用“，”当作分隔符，对存储的数据进行切割
        String[] num = str1.split(",");
        //建立treeset对成绩进行排序
        TreeSet a1 = new TreeSet();

        for(i=0;i<num.length;i++)
        {
            strint[i]=Integer.parseInt(num[i]);
            a1.add(strint[i]);
        }
        Iterator it = a1.iterator();
        while (it.hasNext())
        {
            int op = (int)it.next();
            System.out.println("第"+j+"名："+op+"次");
            j++;
        }
        fr.close();
    }
    private int Random()//生成随机数
    {
        int num;
        Random random = new Random();
        num =  random.nextInt(100);
        return num;
    }
}

