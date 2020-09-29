package behavior;

import org.junit.Test;

public class Memento {
    @Test
    public void testMemento(){
        //发起人 备忘录 管理者
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.setState("first");
        System.out.println("当前状态为"+originator.getState());
        Memo memo = originator.createMemo();
        System.out.println("备忘录中的状态为"+memo.getState());
        caretaker.setMemo(memo);
        originator.setState("second");
        System.out.println("当前状态为"+originator.getState());
        originator.restoreMemo(memo);
        System.out.println("恢复之后的状态为"+originator.getState());
    }
}
//发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里的所有信息。
class Originator{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memo createMemo(){
        return new Memo(state);
    }
    public void restoreMemo(Memo memo){
        this.setState(memo.getState());
    }
}
//备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
class Memo{
    private String state;

    public Memo(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
//管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
class Caretaker{
    private Memo memo;

    public Memo getMemo() {
        return memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
    }
}