package cn.itcast.hb.a_isolation;

import cn.itcast.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;

public class HibernateTest {
    @Test
    //建表
    public void createTable() {
        HibernateUtils.getSessionFactory();
    }

    @Test
    //事务的隔离级别测试1：//插入
    public void testIsolation1() {

        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, 1);
        book.setName("111122222222");
        //保存
//		Book book = new Book();
//		book.setName("1111");
//		book.setPrice(998d);
//
//		session.save(book);//未提交事务
//		session.update(book);
        session.getTransaction().commit();
//		session.close();
    }

    @Test
    //事务的隔离级别测试2：//查询
    public void testIsolation2() {

        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //保存
        List list = session.createQuery("from Book").list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    //
    public void testSessionThread() {
        //得到工厂
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //手动session管理
        Session session1 = sessionFactory.openSession();//每次都new一个新的对象
        System.out.println(session1.hashCode());
        Session session2 = sessionFactory.openSession();
        System.out.println(session2.hashCode());

        //释放资源
        session1.close();
        session2.close();


        //线程session管理
        Session session3 = sessionFactory.getCurrentSession();//当这个线程第一次访问的要获取session的时候，new一个
        System.out.println(session3.hashCode());
        Session session4 = sessionFactory.getCurrentSession();//当第二次再访问的时候，只需要将原来的这个session对象又拿过来
        System.out.println(session4.hashCode());

        //释放资源：实际应用中，本地线程管理的session不需要手动关闭
        session3.close();
        session4.close();//报异常，session已经关闭

    }

}
