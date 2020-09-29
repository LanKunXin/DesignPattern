package behavior;


import org.junit.Test;


public class TestState {
    /*
    * 模仿线程的五种状态变化
    * */
    @Test
    public void testState(){
        ThreadContext threadContext = new ThreadContext();
        threadContext.start();
        threadContext.getCPU();
        threadContext.suspend();
        threadContext.resume();
        threadContext.getCPU();
        threadContext.stop();
    }
}
//环境（Context）角色：也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
class ThreadContext{
    private ThreadState threadState;

    public ThreadContext() {
        threadState=new New();
    }

    public ThreadState getThreadState() {
        return threadState;
    }
    public void setThreadState(ThreadState threadState) {
        this.threadState = threadState;
    }
    public void start(){
        ((New)threadState).start(this);
    }
    public void getCPU() {
        ((Runnable) threadState).getCPU(this);
    }
    public void suspend() {
        ((Running) threadState).suspend(this);
    }
    public void stop() {
        ((Running) threadState).stop(this);
    }
    public void resume() {
        ((Blocked) threadState).resume(this);
    }
}
//抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
abstract class ThreadState{
    protected String stateName;
}
//具体状态（Concrete    State）角色：实现抽象状态所对应的行为。
class New extends ThreadState{
    public New() {
        stateName="新生状态";
        System.out.println("当前状态为"+stateName);
    }
    public void start(ThreadContext threadContext){
        if(stateName.equals("新生状态")){
            threadContext.setThreadState(new Runnable());
        }else {
            System.out.println("当前线程不是新建状态，不能调用start()方法.");
        }
    }
}
class Runnable extends ThreadState{
    public Runnable() {
        stateName="就绪状态";
        System.out.println("当前状态为"+stateName);
    }
    public void getCPU(ThreadContext threadContext){
        if(stateName.equals("就绪状态")){
            threadContext.setThreadState(new Running());
        }else {
            System.out.println("当前线程不是就绪状态，不能调用getCPU()方法.");
        }
    }
}
class Running extends ThreadState{
    public Running() {
        stateName="执行状态";
        System.out.println("当前状态为"+stateName);
    }
     public void stop(ThreadContext threadContext){
        threadContext.setThreadState(new Dead());
     }
     public void suspend(ThreadContext threadContext){
        if(stateName.equals("执行状态")){
            threadContext.setThreadState(new Blocked());
        }else {
            System.out.println("当前线程不是就绪状态，不能调用supend()方法.");
        }
     }
}
class Blocked extends ThreadState{
    public Blocked() {
        stateName="阻塞状态";
        System.out.println("当前状态为"+stateName);
    }
    public void resume(ThreadContext threadContext){
        if(stateName.equals("阻塞状态")){
            threadContext.setThreadState(new Runnable());
        }else {
            System.out.println("当前线程不是就绪状态，不能调用resume()方法.");
        }
    }
}
class Dead extends ThreadState{
    public Dead() {
        stateName="死亡状态";
        System.out.println("当前状态为"+stateName);
    }
}