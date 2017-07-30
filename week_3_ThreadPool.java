import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test//测试类
{
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(5);//创建一个固定大小的线程池
        Res t = new Res();
        for(int i=0;i<10;i++)
        {
            pool.execute(new Producer(t));
            pool.execute(new Consumer(t));//通过execute(Runnable command)方法提交 Runnable 对象

        }
        pool.shutdown();
    }
}
class Res//建立资源
{
    private String name;
    private int count = 1;//任务编号
    private boolean flag = false;//设置一个标记，当标记为true时任务提交线程等待，任务处理线程唤醒。为false时相反
    private Lock lock = new ReentrantLock();
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();
    public void set(String name) throws InterruptedException
    {
        lock.lock();//获取锁
        try
        {
            while (flag)
                condition_pro.await();//等待任务提交线程被唤醒
            this.name = name + "..." + count++;
            System.out.println(Thread.currentThread().getName() + "...任务提交..." + this.name);
            flag = true;
            condition_con.signal();//唤醒任务处理线程

        }
        finally
        {
            lock.unlock();//释放锁
        }
    }


    public void out() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (!flag)
                condition_con.await();
            System.out.println(Thread.currentThread().getName() + "...执行任务....." + this.name);
            flag = false;
            condition_pro.signal();
        }
        finally {
            lock.unlock();
        }

    }
}
class Producer implements Runnable//任务提交线程实现Runnable方法
{
    private Res res;
    Producer(Res res)
    {
        this.res = res;
    }
    public void run()//复写run()方法
    {
         try
            {
                res.set("任务");
            }
            catch (InterruptedException e)
            {
            }


    }
}
class Consumer implements Runnable//任务处理线程实现Runnable方法
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
