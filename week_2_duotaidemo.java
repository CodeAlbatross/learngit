abstract class Animal
{
    abstract void eat();
}
class Cat extends Animal
{
    public void eat()
    {
        System.out.println("吃鱼");
    }
    public void catchMouse()
    {
        System.out.println("抓老鼠");
    }
}
class Dog extends Animal
{
    public void eat()
    {
        System.out.println("吃骨头");
    }
    public void kanjia()
    {
        System.out.println("看家");
    }
}
class Cow extends Animal
{
    public void eat()
    {
        System.out.println("吃草");
    }
    public void gongdi()
    {
        System.out.println("耕地");
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
