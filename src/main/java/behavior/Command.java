package behavior;

import org.junit.Test;

public class Command {
    /*
    * 模仿点早餐
    * 客户把需求告诉服务员，服务员去调用命令去通知厨师出餐。
    * */
    @Test
    public void testCommand(){
       Breakfast breakfast= new ChangFen();
        Waiter waiter = new Waiter();
        waiter.setChangFen(breakfast);
        waiter.chooseCF();
    }
}
//抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
interface Breakfast{
    void cooking();
}
//具体命令角色（Concrete    Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
class ChangFen implements Breakfast{
    private ChanFenChef chanFenChef;
    public ChangFen() {
        this.chanFenChef=new ChanFenChef();
    }
    public void cooking() {
        System.out.println("通知肠粉师傅去做");
        chanFenChef.cooking();
    }
}
class HunTun implements Breakfast{
    private HunTunChef hunTunChef;
    public HunTun() {
        this.hunTunChef=new HunTunChef();
    }
    public void cooking() {
        System.out.println("通知馄饨师傅去做");
        hunTunChef.cooking();
    }
}
//实现者/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
class ChanFenChef{
    public void cooking(){
        System.out.println("肠粉已做好");
    }
}
class HunTunChef{
    public void cooking() {
        System.out.println("馄饨已做好");
    }
}
//调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
class Waiter{
    private Breakfast changFen,hunTun;
    public Breakfast getChangFen() {
        return changFen;
    }
    public void setChangFen(Breakfast changFen) {
        this.changFen = changFen;
    }
    public Breakfast getHunTun() {
        return hunTun;
    }
    public void setHunTun(Breakfast hunTun) {
        this.hunTun = hunTun;
    }
    public void chooseCF(){
        changFen.cooking();
    }
    public void chooseHT(){
        hunTun.cooking();
    }
}

//调用者 树枝节点
//接收者
//抽象命令
//具体命令 树叶节点