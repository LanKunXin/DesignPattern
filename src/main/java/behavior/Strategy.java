package behavior;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

public class Strategy {
    /*
    * 模仿加减法
    * */
    @Test
    public void testStrage(){
        AddStrategy addStrategy = new AddStrategy();
        Calculate calculate = new Calculate(addStrategy);
        System.out.println("5+3="+calculate.calculate(5,3));;
        SubStrategy subStrategy = new SubStrategy();
        calculate = new Calculate(subStrategy);
        System.out.println("5-3="+calculate.calculate(5,3));;
    }
}
//抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现。
interface CalStrategy{
   int cal(int num1,int num2);
}
//具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
class AddStrategy implements CalStrategy{
    @Override
    public int cal(int num1, int num2) {
        return num1+num2;
    }
}
class  SubStrategy implements CalStrategy{
    @Override
    public int cal(int num1, int num2) {
        return num1-num2;
    }
}
//环境（Context）类：持有一个策略类的引用，最终给客户端调用。
class Calculate{
    private CalStrategy calStrategy;
    public Calculate(CalStrategy calStrategy) {
        this.calStrategy = calStrategy;
    }
    public int calculate(int a,int b){
        return calStrategy.cal(a,b);
    }
}

