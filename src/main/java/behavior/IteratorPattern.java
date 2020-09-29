package behavior;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class IteratorPattern {
    @Test
    public void testIterator(){
        ConcreteAggregate aggregate=new ConcreteAggregate();
        aggregate.add("a");
        aggregate.add("b");
        aggregate.add("c");
        aggregate.add("d");
        Iterator iterator = aggregate.getIterator();
        System.out.println("第一个元素"+iterator.first());
        while(iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
//抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
interface Aggregate{
    void add(Object o);
    void remove(Object o);
    Iterator getIterator();
}
//具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
class ConcreteAggregate implements Aggregate{
    private List<Object> list = new LinkedList<>();
    @Override
    public void add(Object o) {
        if(!list.contains(o)){
            list.add(o);
        }
    }
    @Override
    public void remove(Object o) {
        if(list.contains(o)){
            list.remove(o);
        }
    }

    @Override
    public Iterator getIterator() {
        return new Concretelterator(list);
    }
}
//抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
interface Iterator{
    Object first();
    Object next();
    boolean hasNext();
}
//具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
class Concretelterator implements Iterator{
    private List<Object> list;
    private int index=-1;
    public Concretelterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        index=0;
        return list.get(index);
    }

    @Override
    public Object next() {
        Object o=null;
        if(this.hasNext())
            o=list.get(++index);
        return o;
    }

    @Override
    public boolean hasNext() {
        return index<list.size()-1?true:false;
    }
}