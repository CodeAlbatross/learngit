/*
这段代码的排行榜功能没有完成完全，没有实现成绩的排行功能，
只能记录成绩，所以排行功能我给注释掉了。。
*/
import java.io.*;
import java.util.Random;
import java.util.Scanner;
class test {

    public static void main(String args[]) throws IOException
    {
        int i;
        function b = new function();
        Scanner out = new Scanner(System.in);
        jiemian a = new jiemian();
        a.dayin();
        while (true) {
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
class function
{
    public void caishu() throws IOException
    {
        int num,i,j;
        Scanner out = new Scanner(System.in);
        num =  suiji();
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
        fw.write(String.format("%d\n",j));
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
        int i,j;
        FileWriter fw = new FileWriter("paihangbang.txt",true);
        FileReader fr = new FileReader("paihangbang.txt");
        char[] p = new char[1024];
        int ch = fr.read();
        for( i = 0;ch !=-1 ; i++)
        {
            p[i] = (char)ch;
            ch = fr.read();
        }
      System.out.println("成绩依次是：");
        //paixu n = new paixu();
        //n.maopao(p,i);
        for(j = 0;j<i ; j++)
        {
            System.out.print(p[j]);
            //ch = fr.read();
        }
        fr.close();
    }
    private int suiji()
    {
        int num;
        Random random = new Random();
        num =  random.nextInt(100);
        return num;
    }
}
class paixu{
    public void maopao(char a[],int n)
    {
        int i,j;
        int temp;
        for(i=0;i<n-1;i++)
        {
            for(j=1;j<n-i;j++)
            {
                if(a[j]<a[j-1])
                {
                    temp=(int)a[i-1];
                    a[i]=a[i-1];
                    a[i-1]=(char) temp;
                }
            }
        }
        for(j = 0;j<i ; j++)
        {
            System.out.print(a[j]);
            //ch = fr.read();
        }
    }
}

