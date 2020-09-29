package structure;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy {
    @Test
    public void testStaticProxy(){
        UserDaoProxy userDaoProxy = new UserDaoProxy(new UserDao());
        userDaoProxy.save();
    }
    @Test
    public void testDynamicProxy(){
        UserDao userDao = new UserDao();
        IUserDao iUserDao = (IUserDao) java.lang.reflect.Proxy.newProxyInstance(UserDao.class.getClassLoader(), UserDao.class.getInterfaces(), new ProxyHandler(userDao));
        iUserDao.save();
    }
    @Test
    public void testProxyCglib(){
        UserDao userDao = new UserDao();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        MethodInterceptor methodInterceptor=(Object o, Method method, Object[] objects, MethodProxy methodProxy)->{
            System.out.println("--save前--");
            //不要使用method.invoke(o,objects) 不然会出现死循环
            Object invoke = methodProxy.invokeSuper(o,objects);
            System.out.println("--save后--");
            return invoke;
        };
        enhancer.setCallback(methodInterceptor);
        UserDao dao = (UserDao) enhancer.create();
        dao.save();
    }
}
//接口
interface IUserDao{
    void save();
}
//目标对象
class UserDao implements IUserDao{

    public void save() {
        System.out.println("--保存数据--");
    }
}

//代理对象
class UserDaoProxy implements IUserDao{
    IUserDao iUserDao=null;

    public UserDaoProxy(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    public void save() {
        System.out.println("--save前--");
        iUserDao.save();
        System.out.println("--save后--");
    }
}
class ProxyHandler implements InvocationHandler{
    private Object object;
    public ProxyHandler(Object object) {
        this.object = object;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--save前--");
        Object invoke = method.invoke(object, args);
        System.out.println("--save前--");
        return invoke;
    }
}
class ProxyCglib implements MethodInterceptor{

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--save前--");
        Object invoke = method.invoke(o, objects);
        System.out.println("--save前--");
        return invoke;
    }
}
