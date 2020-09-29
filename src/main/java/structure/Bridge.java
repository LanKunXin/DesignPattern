package structure;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.security.PrivateKey;

public class Bridge {
    /*
    * 选购手机时有手机牌子和手机配置
    * 手机牌子：小米、华为
    * 手机运行内存：4g、8g
    * 内存：128、256
    * */
    @Test
    public void testBridge(){
        RAM ram = new RAM4G();
        Memory memory = new Memory128G();
        XiaoMi xiaoMi = new XiaoMi();
        xiaoMi.setRam(ram);
        xiaoMi.setMemory(memory);
        xiaoMi.buyPhone();
        ram =new RAM8G();
        memory = new Memory256G();
        HuaWei huaWei = new HuaWei();
        huaWei.setMemory(memory);
        huaWei.setRam(ram);
        huaWei.buyPhone();
    }
}
//抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
abstract class Phone{
    public Memory memory;
    public RAM ram;
    public void setMemory(Memory memory) {
        this.memory = memory;
    }
    public void setRam(RAM ram) {
        this.ram = ram;
    }
    public abstract void buyPhone();
}
//扩展抽象化（Refined    Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
class XiaoMi extends Phone{
    @Override
    public void buyPhone() {
        System.out.println("你所购买的是小米："+ram.getRAM()+"+"+memory.getMemory());
    }
}
class HuaWei extends Phone{

    @Override
    public void buyPhone() {
        System.out.println("你所购买的是华为："+ram.getRAM()+"+"+memory.getMemory());
    }
}
//实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
//品牌
interface Brand{
    String getName();
}
//运行内存
interface RAM{
    String getRAM();
}
interface Memory{
    String getMemory();
}
//具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
class RAM4G implements RAM{

    @Override
    public String getRAM() {
        return "4G";
    }
}
class RAM8G implements RAM{

    @Override
    public String getRAM() {
        return "8G";
    }
}
class Memory128G implements Memory{

    @Override
    public String getMemory() {
        return "128G";
    }
}
class Memory256G implements Memory{

    @Override
    public String getMemory() {
        return "256G";
    }
}

