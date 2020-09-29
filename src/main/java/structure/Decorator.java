package structure;

import org.junit.Test;

public class Decorator {
    /*
    * 模仿买奶茶时加东西
    * */
    @Test
    public void testDecorator(){
        MilkTea milkTea = new PearMilkTea();
        milkTea = new AddCoffe(milkTea);
        milkTea = new AddIceTaste(milkTea);
        AddCoffe addCoffe = new AddCoffe(milkTea);
        System.out.println(addCoffe.getMilkTeaName()+"\n合计"+addCoffe.getPrice()+"元");
       /* AddIceTaste addIceTaste = new AddIceTaste(milkTea);
        System.out.println(addIceTaste.getMilkTeaName()+"\n合计"+addIceTaste.getPrice()+"元");*/
    }
}
//抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
interface MilkTea{
    String getMilkTeaName();
    int getPrice();
}
//具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
class PearMilkTea implements MilkTea{
    @Override
    public String getMilkTeaName() {
        return "珍珠奶茶";
    }
    @Override
    public int getPrice() {
        return 15;
    }
}
class HoneyMilkTea implements MilkTea{
    @Override
    public String getMilkTeaName() {
        return "蜂蜜奶茶";
    }
    @Override
    public int getPrice() {
        return 20;
    }
}
//抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
abstract class Taste implements MilkTea{
    protected MilkTea milkTea;

    public Taste(MilkTea milkTea) {
        this.milkTea = milkTea;
    }
}
//具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
class AddIceTaste extends Taste{
    private String description="加冰";
    public AddIceTaste(MilkTea milkTea) {
        super(milkTea);
    }
    @Override
    public String getMilkTeaName() {
        return milkTea.getMilkTeaName()+description;
    }
    @Override
    public int getPrice() {
        return milkTea.getPrice()+5;
    }
}
class AddCoffe extends Taste{
    private String description="加咖啡";
    public AddCoffe(MilkTea milkTea) {
        super(milkTea);
    }
    @Override
    public String getMilkTeaName() {
        return milkTea.getMilkTeaName()+description;
    }
    @Override
    public int getPrice() {
        return milkTea.getPrice()+15;
    }
}