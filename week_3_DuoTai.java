/*
��̬�������Ϊ������ڵĶ�����̬
1.	��̬������
���������ָ�����Լ����������
���������Ҳ���Խ����Լ����������
2.	��̬��ǰ��
������������֮���й�ϵ��Ҫô�̳У�Ҫôʵ�֡�ͨ�������ڸ��ǡ�
3.	��̬�ĺô�
��̬�ĳ��ִ�������˳������չ�ԡ�
4.	��̬�ı׶�
ֻ��ʹ�ø�������÷��ʸ����еĳ�Ա��
��̬ת�͵�ʱ����instancof�ж������Ƿ�һ��
�����������ޣ�������������Ҫ���бȽϵ�ʱ��

*/
abstract class Animal//�����������ϳ�ȡΪ����
{
    abstract void eat();//��ȷ���ķ���Ҫ����
}
class Cat extends Animal
{
    public void eat()
    {
        System.out.println("����");
    }
    public void catchMouse()//����������Ϊ
    {
        System.out.println("ץ����");
    }
}
class Dog extends Animal
{
    public void eat()
    {
        System.out.println("�Թ�ͷ");
    }
    public void kanjia()
    {
        System.out.println("����");
    }
}
class Cow extends Animal
{
    public void eat()
    {
        System.out.println("�Բ�");
    }
    public void gongdi()
    {
        System.out.println("����");
    }
}
class DuotaiDemo
{
    public static void main(String[] args)
    {
        Animal c = new Cat();//����ת��
        Cat a = (Cat) c;
        a.catchMouse();
    }
    public static void function(Animal c)
    {
       c.eat();
    }

}
