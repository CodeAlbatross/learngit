import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test//������
{
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(5);//����һ���̶���С���̳߳�
        Res t = new Res();
        for(int i=0;i<10;i++)
        {
            pool.execute(new Producer(t));
            pool.execute(new Consumer(t));//ͨ��execute(Runnable command)�����ύ Runnable ����

        }
        pool.shutdown();
    }
}
class Res//������Դ
{
    private String name;
    private int count = 1;//������
    private boolean flag = false;//����һ����ǣ������Ϊtrueʱ�����ύ�̵߳ȴ����������̻߳��ѡ�Ϊfalseʱ�෴
    private Lock lock = new ReentrantLock();
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();
    public void set(String name) throws InterruptedException
    {
        lock.lock();//��ȡ��
        try
        {
            while (flag)
                condition_pro.await();//�ȴ������ύ�̱߳�����
            this.name = name + "..." + count++;
            System.out.println(Thread.currentThread().getName() + "...�����ύ..." + this.name);
            flag = true;
            condition_con.signal();//�����������߳�

        }
        finally
        {
            lock.unlock();//�ͷ���
        }
    }


    public void out() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (!flag)
                condition_con.await();
            System.out.println(Thread.currentThread().getName() + "...ִ������....." + this.name);
            flag = false;
            condition_pro.signal();
        }
        finally {
            lock.unlock();
        }

    }
}
class Producer implements Runnable//�����ύ�߳�ʵ��Runnable����
{
    private Res res;
    Producer(Res res)
    {
        this.res = res;
    }
    public void run()//��дrun()����
    {
         try
            {
                res.set("����");
            }
            catch (InterruptedException e)
            {
            }


    }
}
class Consumer implements Runnable//�������߳�ʵ��Runnable����
{
    private Res res;
    Consumer(Res res)
    {
        this.res = res;
    }
    public void run()
    {

            try
            {
                res.out();
            }
            catch (InterruptedException e)
            {
            }


    }
}
