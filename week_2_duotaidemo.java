abstract class Animal
{
    abstract void eat();
}
class Cat extends Animal
{
    public void eat()
    {
        System.out.println("����");
    }
    public void catchMouse()
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
        Animal c = new Cat();
        Cat a = (Cat) c;
        a.catchMouse();
    }
    public static void function(Animal c)
    {
       c.eat();
    }

}
