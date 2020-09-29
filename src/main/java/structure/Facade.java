package structure;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

public class Facade {
/*
* 模仿开关 同时控制两层楼的灯。
* */
//客户（Client）角色：通过一个外观角色访问各个子系统的功能。
    @Test
    public void testFacade(){
        Switch aSwitch = new Switch();
        aSwitch.start();
        aSwitch.shutdown();
    }
}
//外观（Facade）角色：为多个子系统对外提供一个共同的接口。
class Switch{
    FirstFloor firstFloor =new FirstFloor();
    SecondFloor secondFloor =new SecondFloor();
    public void start(){
        firstFloor.start();
        secondFloor.start();
    }
    public void shutdown(){
        firstFloor.shutdown();
        secondFloor.shutdown();
    }
}
//子系统（Sub System）角色：实现系统的部分功能，客户可以通过外观角色访问它。
class FirstFloor{
    public void start(){
        System.out.println("一楼 的灯已打开");
    }
    public void shutdown(){
        System.out.println("一楼的灯已关闭");
    }
}
class SecondFloor{
    public void start(){
        System.out.println("二楼的灯已打开");
    }
    public void shutdown(){
        System.out.println("二楼的灯已关闭");
    }
}

