package behavior;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Observer {
    /*
    *   模仿微信公众号发布消息
    * */
    @Test
    public void testObserver(){
     IPublisher iPublisher=new Publisher();
        Customer customer = new Customer("王二狗");
        Customer customer1 = new Customer("李二狗");
        Customer customer2 = new Customer("朱二狗");
        iPublisher.registerCustomer(customer);
        iPublisher.registerCustomer(customer1);
        iPublisher.registerCustomer(customer2);
        ((Publisher) iPublisher).setInfomation("哈哈哈");
    }
}
//抽象主题（Subject）角色：也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
interface IPublisher{
    void registerCustomer(ICustomer customer);
    void removeCustomer(ICustomer customer);
    void notifyCustomer();
}
//具体主题（Concrete    Subject）角色：也叫具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
class Publisher implements IPublisher{
    private List<ICustomer> list=new LinkedList<>();
    private String msg;
    @Override
    public void registerCustomer(ICustomer customer) {
        if(null!=customer){
            for (ICustomer iCustomer : list) {
                if(iCustomer==customer){
                    System.out.println("已经有这个用户了");
                    break;
                }
            }
            list.add(customer);
        }

    }
    @Override
    public void removeCustomer(ICustomer customer) {
        if(null!=customer){
            for (ICustomer iCustomer : list) {
                if(iCustomer==customer){
                    list.remove(customer);
                }
            }
        }
    }

    @Override
    public void notifyCustomer() {
        for (ICustomer iCustomer : list) {
            iCustomer.receive(msg);
        }
    }
    public void setInfomation(String msg){
        this.msg=msg;
        System.out.println("公众号更新消息如下:"+msg);
        this.notifyCustomer();
    }
}
//抽象观察者（Observer）角色：它是一个抽象类或接口，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
interface ICustomer {
    void receive(String msg);
}
//具体观察者（Concrete Observer）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
class Customer implements ICustomer{
    private String name;
    private String msg;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void receive(String msg) {
        this.msg=msg;
        read();
    }

    private void read() {
        System.out.println(name+":收到推送消息"+msg);
    }
}

