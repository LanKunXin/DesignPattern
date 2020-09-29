package behavior;

import org.junit.Test;

public class State {
    @Test
    public void testState(){
        ScoreContext account=new ScoreContext();
        System.out.println("学生成绩状态测试：");
        account.add(30);
        account.add(40);
        account.add(25);
        account.add(-15);
        account.add(-25);
    }
}
//环境（Context）角色：也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
class ScoreContext{
    private AbstractState state;

    public ScoreContext() {
        state=new LowState(this);
    }

    public AbstractState getState() {
        return state;

    }

    public void setState(AbstractState state) {
        this.state = state;
    }
    public void add(int score)
    {
        state.addScore(score);
    }
}
//抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
abstract class AbstractState{
    protected ScoreContext scoreContext;
    protected String stateName;
    protected int score;
    public abstract void checkState();
    public void addScore(int x){
        score+=x;
        System.out.print("加上："+x+"分，\t当前分数："+score );
        checkState();
        System.out.println("分，\t当前状态："+scoreContext.getState().stateName);
    }
}
//具体状态（Concrete    State）角色：实现抽象状态所对应的行为。
class LowState extends AbstractState{
    public LowState(ScoreContext h)
    {
        scoreContext=h;
        stateName="不及格";
        score=0;
    }
    public LowState(AbstractState state)
    {
        scoreContext=state.scoreContext;
        stateName="不及格";
        score=state.score;
    }
    @Override
    public void checkState() {
        if(score>=90)
        {
            scoreContext.setState(new HighState(this));
        }
        else if(score>=60)
        {
            scoreContext.setState(new MiddleState(this));
        }
    }
}
class MiddleState extends AbstractState{
    public MiddleState(AbstractState state) {
        scoreContext=state.scoreContext;
        stateName="中等";
        score=state.score;
    }
    public void checkState()
    {
        if(score<60) {
            scoreContext.setState(new LowState(this));
        }
        else if(score>=90) {
            scoreContext.setState(new HighState(this));
        }
    }
}
class HighState extends AbstractState
{
    public HighState(AbstractState state) {
        scoreContext=state.scoreContext;
        stateName="优秀";
        score=state.score;
    }
    public void checkState() {
        if(score<60) {
            scoreContext.setState(new LowState(this));
        }
        else if(score<90) {
            scoreContext.setState(new MiddleState(this));
        }
    }
}