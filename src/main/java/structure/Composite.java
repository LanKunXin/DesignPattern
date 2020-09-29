package structure;

import com.sun.xml.internal.ws.api.ComponentEx;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Composite {
    @Test
    public void testComposite(){
        Composit composit = new Composit();
        composit.add(new Leaf("a"));
        Leaf b = new Leaf("b");
        composit.add(b);
        composit.add(new Leaf("c"));
        composit.operation();
        composit.remove(b);
        composit.operation();
    }
}
//抽象构件（Component）角色：它的主要作用是为树叶构件和树枝构件声明公共接口，并实现它们的默认行为。在透明式的组合模式中抽象构件还声明访问和管理子类的接口；在安全式的组合模式中不声明访问和管理子类的接口，管理工作由树枝构件完成。
interface Component{
    void add(Component c);
    void remove(Component c);
    void operation();
}
//树叶构件（Leaf）角色：是组合中的叶节点对象，它没有子节点，用于实现抽象构件角色中 声明的公共接口。
class Leaf implements Component{
    private String name;
    public Leaf(String name)
    {
        this.name=name;
    }
    @Override
    public void add(Component c) {

    }
    @Override
    public void remove(Component c) {

    }
    @Override
    public void operation() {
        System.out.println("树叶"+name+"：被访问！");
    }
}
//树枝构件（Composite）角色：是组合中的分支节点对象，它有子节点。它实现了抽象构件角色中声明的接口，它的主要作用是存储和管理子部件，通常包含 Add()、Remove()、GetChild() 等方法。
class Composit implements Component{
    private List<Component> list = new LinkedList<>();
    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }
    @Override
    public void operation() {
        for (Component component : list) {
            component.operation();
        }
    }
}