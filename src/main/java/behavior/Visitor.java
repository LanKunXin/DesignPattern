package behavior;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Visitor {
    /*
    * 艺术公司利用“铜”可以设计出铜像，利用“纸”做打印纸；造币公司利用“铜”可以印出铜币，利用“纸”可以印出纸币
    * */
    @Test
    public void testVisitor(){
    SetMaterial setMaterial = new SetMaterial();
    setMaterial.add(new Paper());
    setMaterial.add(new Cuprum());
    ArtCompany artCompany = new ArtCompany();
    String accept = setMaterial.accept(artCompany);
    System.out.println(accept);

    }
}
//抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素。
interface Company{
    String create(Paper paper);
    String create(Cuprum cuprum);
}
//具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
class ArtCompany implements Company{

    @Override
    public String create(Paper paper) {
        return "打印纸";
    }

    @Override
    public String create(Cuprum cuprum) {
        return "铜像";
    }
}
class Mint implements Company{

    @Override
    public String create(Paper paper) {
        return "纸币";
    }

    @Override
    public String create(Cuprum cuprum) {
        return "铜钱";
    }
}
//抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
interface Material{
    String accept(Company company);
}
//具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，另外具体元素中可能还包含本身业务逻辑的相关操作。
class Cuprum implements Material{

    @Override
    public String accept(Company company) {
        return company.create(this);
    }
}
class Paper implements Material{

    @Override
    public String accept(Company company) {
        return company.create(this);
    }
}
//对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，通常由 List、Set、Map 等聚合类实现。
class SetMaterial{
    private List<Material> list = new ArrayList<>();
    public String accept(Company vistor){
        Iterator<Material> iterator = list.iterator();
        String tmp="";
        while(iterator.hasNext())
        {
            tmp+=((Material) iterator.next()).accept(vistor)+" ";
        }
        return tmp;
    }
    public void add(Material Material){
        list.add(Material);
    }
    public void remove(Material Material){
        list.remove(Material);
    }
}