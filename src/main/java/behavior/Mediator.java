package behavior;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Mediator {

    @Test
    public void testMediator(){
        ConcreteMediator concreteMediator = new ConcreteMediator();
        ConcreateColleague1 colleague1 = new ConcreateColleague1();
        ConcreateColleague2 colleague2 = new ConcreateColleague2();
        ConcreateColleague3 colleague3 = new ConcreateColleague3();
        concreteMediator.register(colleague1);
        concreteMediator.register(colleague2);
        concreteMediator.register(colleague3);
        colleague2.send();
    }
}
//抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
abstract class AMediator{
    public abstract void register(Colleague colleague);//注册
    public abstract void relay(Colleague colleague,String msg);//转发
}
//具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
class ConcreteMediator extends AMediator{
    private List<Colleague> list = new LinkedList<>();
    @Override
    public void register(Colleague colleague) {
        if(!list.contains(colleague)) {
            list.add(colleague);
            colleague.setaMediator(this);
        }
    }
    @Override
    public void relay(Colleague colleague,String msg) {
        for (Colleague temp : list) {
            if(temp!=colleague){
                temp.receive(msg);
            }
        }
    }
}
//抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
abstract class Colleague{
    protected AMediator aMediator;

    public void setaMediator(AMediator aMediator) {
        this.aMediator = aMediator;
    }
    public abstract void receive(String msg);
    public abstract void send();
}
//具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
class ConcreateColleague1 extends Colleague{
    @Override
    public void receive(String msg) {
        System.out.println("1号接收到的信息是:"+msg);
    }
    @Override
    public void send() {
        aMediator.relay(this,"这条信息是1号发送的");
    }
}
class ConcreateColleague2 extends Colleague{
    @Override
    public void receive(String msg) {
        System.out.println("2号接收到的信息是:"+msg);
    }
    @Override
    public void send() {
        aMediator.relay(this,"这条信息是1号发送的");
    }
}
class ConcreateColleague3 extends Colleague{
    @Override
    public void receive(String msg) {
        System.out.println("3号接收到的信息是:"+msg);
    }
    @Override
    public void send() {
        aMediator.relay(this,"这条信息是1号发送的");
    }
}
