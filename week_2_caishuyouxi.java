/*
��δ�������а���û�������ȫ��û��ʵ�ֳɼ������й��ܣ�
ֻ�ܼ�¼�ɼ����������й����Ҹ�ע�͵��ˡ���
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
            System.out.println("�뿪ʼ��Ĳ���");
            System.out.println("  1.��ʼ");
            System.out.println("  2.����Ӣ�۰�");
            System.out.println("  0.������Ϸ");
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
        System.out.println("*  ��ӭ���������Ϸv1.0  *");
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
        System.out.println("�����������µ�һ��������100�����֣�");
        i = out.nextInt();
        for( j=1;i!=num; )
        {

            if(i<num && i>0)
            {
                System.out.println("С��");
                j++;
            }
            else if(i>num && i<100)
            {
                System.out.println("����");
                j++;
            }
            else
            {
                System.out.println("��������");
            }
            System.out.println("�����������µ�һ��������100�����֣�");
            i = out.nextInt();
        }
        System.out.println("���ֱ�Ǹ���ţ���Ϊ"+num);
        System.out.printf("��һ������%d��\n",j);
        FileWriter fw = new FileWriter("paihangbang.txt",true);
        fw.write(String.format("%d\n",j));
        fw.close();
        System.out.println("�벻������һ�Σ���1.yes/2.no��");
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
      System.out.println("�ɼ������ǣ�");
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

