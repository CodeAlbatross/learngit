/*
多态可以理解为事物存在的多种形态
1.	多态的体现
父类的引用指向了自己的子类对象
父类的引用也可以接收自己的子类对象
2.	多态的前提
必须是类与类之间有关系。要么继承，要么实现。通常还存在覆盖。
3.	多态的好处
多态的出现大大的提高了程序的扩展性。
4.	多态的弊端
只能使用父类的引用访问父类中的成员。
多态转型的时候，用instancof判断类型是否一致
（子类型有限，当传递类型需要进行比较的时候）

*/
abstract class Animal//各种物种向上抽取为动物
{
    abstract void eat();//不确定的方法要抽象
}
class Cat extends Animal
{
    public void eat()
    {
        System.out.println("吃鱼");
    }
    public void catchMouse()//描述特有行为
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
        Animal c = new Cat();//向上转型
        Cat a = (Cat) c;
        a.catchMouse();
    }
    public static void function(Animal c)
    {
       c.eat();
    }

}
