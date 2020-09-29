package create;

import org.junit.Test;

import java.util.Optional;

public class Singleton {
    @Test
    public void testLazySingleton(){
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);
        LazySingleton instance1 = LazySingleton.getInstance();
        System.out.println(instance1);
    }
    @Test
    public void testHungrySingleton(){
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        System.out.println(hungrySingleton);
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        System.out.println(hungrySingleton1);
    }
    @Test
    public void testDoubleCheck(){
        DoubleCheck instance = DoubleCheck.getInstance();
        System.out.println(instance);
        DoubleCheck instance1 = DoubleCheck.getInstance();
        System.out.println(instance1);
    }
}
class LazySingleton{
    private static LazySingleton lazySingleton;
    //避免在外部被实例化
    private LazySingleton(){

    }
    public static LazySingleton getInstance(){
        lazySingleton =Optional.ofNullable(lazySingleton).orElse(new LazySingleton());
        return lazySingleton;
    }
}
class HungrySingleton{
    private static HungrySingleton hungrySingleton=new HungrySingleton();
    private HungrySingleton(){

    }
    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
class DoubleCheck{
    private static DoubleCheck doubleCheck;
    private DoubleCheck() {
    }
    public static DoubleCheck getInstance(){
        if(doubleCheck==null)
            synchronized (DoubleCheck.class){
            if(doubleCheck==null){
                doubleCheck=new DoubleCheck();
            }
            }
            return doubleCheck;
    }
}