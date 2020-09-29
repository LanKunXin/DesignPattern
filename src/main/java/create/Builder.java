package create;

/*
 * 装修客厅的墙，客户只需要把需求告诉产品经理，产品经理再去指挥工人装修
 * 指挥者->建造者->产品
 * */
public class Builder {
    public static void main(String[] args) {
        Worker1 worker1 = new Worker1();
        Worker2 worker2 = new Worker2();
        //让worker1去建造
        ProduceManager produceManager = new ProduceManager(worker1);
        Parlour decorate = produceManager.decorate();
        System.out.println(decorate);
        //让worker2去建造
        produceManager = new ProduceManager(worker2);
        decorate = produceManager.decorate();
        System.out.println(decorate);
    }
}
//产品
class Parlour{
    private String wall;
    private String TV;
    @Override
    public String toString() {
        return "Parlour{" +
                "wall='" + wall + '\'' +
                ", TV='" + TV + '\'' +
                ", soft='" + soft + '\'' +
                '}';
    }
    private String soft;
    public String getWall() {
        return wall;
    }
    public void setWall(String wall) {
        this.wall = wall;
    }
    public String getTV() {
        return TV;
    }
    public void setTV(String TV) {
        this.TV = TV;
    }
    public String getSoft() {
        return soft;
    }
    public void setSoft(String soft) {
        this.soft = soft;
    }
}
//抽象建造者
abstract class Decorator{
    protected Parlour parlour=new Parlour();
    abstract void buildWall();
    abstract void buildTV();
    abstract void buildSoft();
    public Parlour getParlour(){
        return parlour;
    }
}
//具体建造者
class Worker1 extends Decorator{
    void buildWall() {
        parlour.setWall("wall build by woker1");
    }
    void buildTV() {
        parlour.setTV("TV build by worker1");
    }
    void buildSoft() {
        parlour.setSoft("soft build by worker1");
    }
}
class Worker2 extends Decorator{
    void buildWall() {
        parlour.setWall("wall build by woker2");
    }
    void buildTV() {
        parlour.setTV("TV build by worker2");
    }
    void buildSoft() {
        parlour.setSoft("soft build by worker2");
    }
}
//指挥者
class ProduceManager{
    private Decorator decorator;

    public ProduceManager(Decorator decorator) {
        this.decorator = decorator;
    }
    public Parlour decorate(){
        decorator.buildSoft();
        decorator.buildTV();
        decorator.buildWall();
       return decorator.getParlour();
    }
}