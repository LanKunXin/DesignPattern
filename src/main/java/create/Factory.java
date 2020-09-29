package create;

import org.junit.Test;

public class Factory {
    @Test
    public void testFactoryMethod(){
        OrderPizza orderPizza=new BJOrderPizza();
        Pizza pizza = orderPizza.createPizza("cheese");
        System.out.println(pizza);
        orderPizza=new SHOrderPizza();
        pizza= orderPizza.createPizza("cheese");
        System.out.println(pizza);
    }
}
//抽象工厂类
interface OrderPizza{
    Pizza createPizza(String orderType);
}
//具体工厂类
class BJOrderPizza implements OrderPizza{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if("cheese".equals(orderType)){
            pizza=new CheesePizza();
        }else if("greek".equals(orderType)){
            pizza=new GreekPizza();
        }
        return pizza;
    }
}
class SHOrderPizza implements OrderPizza{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if("cheese".equals(orderType)){
            pizza=new GreekPizza();
        }else if("greek".equals(orderType)){
            pizza=new CheesePizza();
        }
        return pizza;
    }
}
//抽象产品
interface Pizza{
    void bake();
}
//具体产品
class CheesePizza implements Pizza{

    @Override
    public void bake() {
        System.out.println("CheesePizza烘培中");
    }
}
class GreekPizza implements Pizza{

    @Override
    public void bake() {
        System.out.println("GreekPizza烘培中");
    }
}


