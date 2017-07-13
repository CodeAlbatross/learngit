/*
注释
*/
import java.util.Scanner;
class Demo
{
	public static void main(String args[])
	{
		Scanner out = new Scanner(System.in);
		int x,i,sum=0;
		System.out.println("输入i的值并计算从一到i的和:");
		i = out.nextInt();
		for(x=1;x<=i;x++)
		{
			sum+=x;
		}
		System.out.println("sum="+sum);
	}
}