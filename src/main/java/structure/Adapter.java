package structure;

import org.junit.Test;

public class Adapter {
    /*
    * 适配器类通过继承适配者类和实现目标类，在目标类的方法中去调用适配者类的相应方法
    * */
    /*@Test
    public void testClassAdapter(){
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        voltageAdapter.output5();
    }*/
    /*@Test
    public void testObjectAdapter(){
        Voltage220 voltage220 = new Voltage220();
        VoltageAdapter voltageAdapter = new VoltageAdapter(voltage220);
        voltageAdapter.output5();
    }*/
    @Test
    public void testInterfaceAdapter(){
        VoltageAdapter voltageAdapter = new VoltageAdapter(new Voltage220());
        voltageAdapter.output5v();
    }
}
//适配者类
class Voltage220{
    public int output220(){
        int voltage=220;
        System.out.println("当前电压"+voltage);
        return voltage;
    }
}
//目标接口
interface Voltage5{
    int output5();
}
//适配器类
/*class VoltageAdapter extends Voltage220 implements Voltage5{

    @Override
    public int output5() {
        int voltage220 = this.output220();
        int voltage5 = voltage220 / 44;
        System.out.println("转换之后的电压为"+voltage5);
        return voltage5;
    }
}*/
/*
class VoltageAdapter implements Voltage5{
    private Voltage220 voltage220=null;
    public VoltageAdapter(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }
    public int output5() {
        int voltage5=0;
        if(null != voltage220) {
            int a = voltage220.output220();
            voltage5 = a / 44;
            System.out.println("转换之后的电压为" + voltage5);
        }
        return voltage5;
    }
}*/
//中间适配器
abstract class MediationAdapter implements Voltage5{
    protected Voltage220 voltage220;

    public MediationAdapter(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }
    public int output5(){
        int voltage5=0;
        if(null != voltage220) {
            int a = voltage220.output220();
            voltage5 = a / 44;
            System.out.println("转换之后的电压为" + voltage5);
        }
        return voltage5;
    }
}
// 具体适配器
class VoltageAdapter extends MediationAdapter{
    public VoltageAdapter(Voltage220 voltage220) {
        super(voltage220);
    }
    public int output5v(){
        int output=0;
        if(voltage220!=null){
            output=this.output5();
        }
        System.out.println("当前电压"+output);
        return output;
    }
}