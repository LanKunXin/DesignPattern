package create;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

public class Prototype {
    @Test
    public void testShallowClone() throws CloneNotSupportedException {
        User user = new User();
        user.setGender("1");
        user.setName("LiMing");
        user.setMoney(new Money(5));
        User cloneUser = user.clone();
        cloneUser.setName("ZhangSan");
        cloneUser.getMoney().setCount(10);
        System.out.println(user);
        System.out.println(cloneUser);
    }
}
/*
* 客户类
* */
class User implements Cloneable {
    private String name;
    private String gender;
    private Money money;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", money=" + money +
                '}';
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        Money money = new Money(1);
        clone.setMoney(money.clone());
        return clone;
    }
}
/*
* 客户的存款
* */
class Money implements Cloneable{
    double count;
    public Money clone() throws CloneNotSupportedException {
        return (Money)super.clone();
    }
    public Money(double count) {
        this.count = count;
    }

    public double getCount() {

        return count;
    }

    @Override
    public String toString() {
        return "Money{" +
                "count=" + count +
                '}';
    }

    public void setCount(double count) {
        this.count = count;
    }
}