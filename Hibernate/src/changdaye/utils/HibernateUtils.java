package changdaye.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//hibernate工厂工具类
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        //初始化
        sessionFactory = new Configuration().configure().buildSessionFactory();

        //要在虚拟机关闭的时候调用关闭工厂的方法:使用运行时的关闭的钩子方法
        Runtime.getRuntime().addShutdownHook(new Thread() {
            //一般这种写法用来回收资源
            @Override
            public void run() {
                //关闭工厂
                sessionFactory.close();
                System.out.println("session工厂已经关闭！");
            }
        });

    }

    //得到session工厂
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //得到 会话
    public static Session openSession() {
        return sessionFactory.openSession();
    }

}
