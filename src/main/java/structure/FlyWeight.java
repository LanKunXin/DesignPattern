package structure;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.HashMap;

public class FlyWeight {
    /*
     * 模仿从线程池中创建线程
     * */
    @Test
    public void testFlyWeight(){
        ThreadPool threadPool = new ThreadPool();
        Thread thread01 = threadPool.getThread("a");
        thread01.operation(new UnsharedConcreteFlyweight("第一次调用a"));
        Thread thread02 = threadPool.getThread("b");
        thread02.operation(new UnsharedConcreteFlyweight("第一次调用b"));
        Thread thread03 = threadPool.getThread("a");
        thread03.operation(new UnsharedConcreteFlyweight("第二次调用a"));
        Thread thread04 = threadPool.getThread("b");
        thread04.operation(new UnsharedConcreteFlyweight("第二次调用b"));
    }
}
//抽象享元角色（Flyweight）:是所有的具体享元类的基类，为具体享元规范需要实现的公共接口，非享元的外部状态以参数的形式通过方法传入。
interface Thread{
    void operation(UnsharedConcreteFlyweight state);
}
//具体享元（Concrete Flyweight）角色：实现抽象享元角色中所规定的接口。
class ThreadImpl implements Thread{
    private String key;
    public ThreadImpl(String key) {
        this.key = key;
    }
    @Override
    public void operation(UnsharedConcreteFlyweight state) {
        System.out.print("具体享元"+key+"被调用，");
        System.out.println("非享元信息是:"+state.getInfo());
    }
}
//非享元（Unsharable Flyweight)角色：是不可以共享的外部状态，它以参数的形式注入具体享元的相关方法中。
class UnsharedConcreteFlyweight{
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UnsharedConcreteFlyweight(String info) {

        this.info = info;
    }
}
//享元工厂（Flyweight Factory）角色：负责创建和管理享元角色。当客户对象请求一个享元对象时，享元工厂检査系统中是否存在符合要求的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象。
class ThreadPool{
    private HashMap<String,Thread> map=new HashMap<>();
    public Thread getThread(String key){
        Thread thread = map.get(key);
        if(null !=thread){
            System.out.println("具体享元"+key+"已经存在，被成功获取！");
        }else{
            System.out.println("创建具体享元");
            thread=new ThreadImpl(key);
            map.put(key,thread);
        }
        return thread;
    }
}