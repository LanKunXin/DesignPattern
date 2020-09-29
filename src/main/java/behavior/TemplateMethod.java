package behavior;

import org.junit.Test;

public class TemplateMethod {
    /*
    * 模拟西红柿炒蛋，放油->放西红柿->放鸡蛋
    * */
    @Test
    public void testTM(){
        MeCook meCook = new MeCook();
        meCook.cook();
        ChefCook chefCook = new ChefCook();
        chefCook.cook();
    }
}
//抽象类（Abstract Class）
abstract class Cook{
    public abstract void oil();
    public abstract void tomato();
    public abstract void egg();
    public final void cook(){
        this.oil();
        this.tomato();
        this.egg();
    }
}
//具体子类（Concrete Class）
class MeCook extends Cook{
    @Override
    public void oil() {
        System.out.println("放了1斤油");
    }
    @Override
    public void tomato() {
        System.out.println("放了2个西红柿");
    }
    @Override
    public void egg() {
        System.out.println("放了10个鸡蛋");
    }
}
class ChefCook extends Cook{
    @Override
    public void oil() {
        System.out.println("放了适当的油");
    }
    @Override
    public void tomato() {
        System.out.println("放了适量的西红柿");
    }
    @Override
    public void egg() {
        System.out.println("放了适量的鸡蛋");
    }
}
