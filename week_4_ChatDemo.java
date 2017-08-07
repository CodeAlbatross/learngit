/*
编写一个聊天程序
有收数据的部分
有发数据的部分
就要用到多线程技术
*/

import java.io.*;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.net.InetAddress.getByName;

class send implements Runnable
{
    private DatagramSocket ds;
    //创建udp服务。通过DatagramSocket对象
    public send(DatagramSocket ds)
    {
        this.ds = ds;
    }
    public void run()
    {
        try
        {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            String line = null;

            while ((line=buffer.readLine())!=null)
            {
                if("886".equals(line))
                    break;

                byte[] buf = line.getBytes();//确定数据,封装成数据包
                DatagramPacket dp = new DatagramPacket(buf,buf.length, getByName("192.168.2.105"),10003);
                //通过socket服务，将已有的数据包发送出去。通过send方法
                ds.send(dp);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("发送端失败");
        }
    }
}
class rece implements Runnable
{
    private DatagramSocket ds;
    public rece(DatagramSocket ds)
    {
        this.ds = ds;
    }
    public void run()
    {
        try
        {
            while (true)
            {
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);

                //通过服务的receive方法将收到的数据存入到数据包中。
                ds.receive(dp);//阻塞式

                //通过数据包的方法获得其中的数据
                String ip = dp.getAddress().getHostAddress();

                String data = new String(dp.getData(), 0, dp.getLength());

                int port = dp.getPort();

                System.out.println(ip + "::" + data + "::" + port);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("接收端异常");
        }
    }

}
class Demo
{
    public static void main(String[] args)throws Exception
    {
        DatagramSocket sendsocket = new DatagramSocket();
        DatagramSocket recesocket = new DatagramSocket(10003);

        new Thread(new send(sendsocket)).start();
        new Thread(new rece(recesocket)).start();
    }
}