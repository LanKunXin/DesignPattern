package behavior;

import org.junit.Test;

public class ChainOfResponsibility {
    /*
    * 规定学生请假小于或等于 2 天，班主任可以批准；
    *             小于或等于 7 天，系主任可以批准；
    *              小于或等于 10 天，院长可以批准；其他情况不予批准
    * */
    //客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
    @Test
    public void testCor(){
        ClassAdviser classAdviser = new ClassAdviser();
        DepartmentHead departmentHead = new DepartmentHead();
        classAdviser.setNext(departmentHead);
        Dean dean = new Dean();
        departmentHead.setNext(dean);
        classAdviser.handleRequest(5);
        classAdviser.handleRequest(2);
        classAdviser.handleRequest(9);
        classAdviser.handleRequest(11);

    }
}
//抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
abstract class Leader{
    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }
    public abstract void handleRequest(int leaveDays);
}
//具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
//班主任
class ClassAdviser extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if(leaveDays >0 && leaveDays<=2){
            System.out.println("班主任批准了");
        }else{
            if(this.getNext()!=null){
                this.getNext().handleRequest(leaveDays);
            }else{
                System.out.println("请假太久没人批");
            }
        }
    }
}
//院长
class Dean extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if(leaveDays >7 && leaveDays<=10){
            System.out.println("院长批准了");
        }else{
            if(this.getNext()!=null){
                this.getNext().handleRequest(leaveDays);
            }else{
                System.out.println("请假太久没人批");
            }
        }
    }
}
//系主任
class DepartmentHead extends Leader{

    @Override
    public void handleRequest(int leaveDays) {
        if(leaveDays >2 && leaveDays<=7){
            System.out.println("系主任批准了");
        }else{
            if(this.getNext()!=null){
                this.getNext().handleRequest(leaveDays);
            }else{
                System.out.println("请假太久没人批");
            }
        }
    }
}
