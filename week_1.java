/*
ע��
*/
import java.util.Scanner;
class Demo
{
	public static void main(String args[])
	{
		Scanner out = new Scanner(System.in);
		int x,i,sum=0;
		System.out.println("����i��ֵ�������һ��i�ĺ�:");
		i = out.nextInt();
		for(x=1;x<=i;x++)
		{
			sum+=x;
		}
		System.out.println("sum="+sum);
	}
}