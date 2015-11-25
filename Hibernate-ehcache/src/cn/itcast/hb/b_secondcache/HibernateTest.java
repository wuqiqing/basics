package cn.itcast.hb.b_secondcache;

import cn.itcast.hb.a_isolation.Book;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class HibernateTest {
    @Test
    //测试二级缓存的存在和使用
    public void testSecondCacheExist() {
        //使用两个session来测试
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //查询：将数据放入二级缓存:T1
        Book book1 = (Book) session.get(Book.class, 2);//从数据库查询数据，发出sql，将数据放入一级缓存和二级缓存（必须先开启二级缓存）
        System.out.println(book1);

        //改变了一级缓存，并没有改变二级缓存
//		book1.setPrice(9999d);
        //为了避免改变一级缓存（一级缓存会自动同步二级缓存），我们手动发出更新语句:T2
        session.createQuery("update Book set price=8888 where id=2").executeUpdate();

        Book book2 = (Book) session.get(Book.class, 2);//从一级缓存查询数据（有一级会优先用一级）
        System.out.println(book2);


        session.getTransaction().commit();//flush的时候，发出update语句，快照更新。同步数据到数据库和快照，如果开启了二级缓存，还会自动同步给二级缓存
        session.close();//一级缓存被销毁，二级缓存仍然存在（它和sessionFactory绑定）


        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();

        //第二个session操作
        Book book3 = (Book) session2.get(Book.class, 2);//从二级缓存查询数据（如果没有二级缓存，则从数据库查询），不需要发出sql，自动将数据同步给一级缓存
        //从二级缓存取出一个对象，要同步给一级缓存，同步的方式是：直接引用对象的地址就可以了（）
        System.out.println(book3);
        Book book4 = (Book) session2.get(Book.class, 2);//自动从一级缓存查询
        System.out.println(book4);

        session2.getTransaction().commit();
        session2.close();

    }


    //测试二级缓存的散装数据
    @Test
    public void testSecondCacheSanzhuang() {
        //使用两个session来测试
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //查询：将数据放入二级缓存
        Book book1 = (Book) session.get(Book.class, 2);//从数据库查询数据，发出sql，将数据放入一级缓存和二级缓存（必须先开启二级缓存）
        System.out.println(book1);

        session.getTransaction().commit();
        session.close();//一级缓存被销毁，二级缓存仍然存在（它和sessionFactory绑定）

        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();

        //第二个session操作
        Book book3 = (Book) session2.get(Book.class, 2);//从二级缓存查询数据（如果没有二级缓存，则从数据库查询），不需要发出sql，自动将数据同步给一级缓存
        System.out.println(book3);

        session2.getTransaction().commit();
        session2.close();

        Session session3 = HibernateUtils.openSession();
        session3.beginTransaction();

        //第二个session操作
        Book book5 = (Book) session3.get(Book.class, 2);//从二级缓存查询数据（如果没有二级缓存，则从数据库查询），不需要发出sql，自动将数据同步给一级缓存
        System.out.println(book5);

        session3.getTransaction().commit();
        session3.close();

    }


    //测试二级缓存的读写方式
    @Test
    public void testSecondCacheReadWrite() {
        //使用两个session来测试
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //查询：将数据放入二级缓存
//		Book book1 =(Book) session.get(Book.class, 2);//从数据库查询数据，发出sql，将数据放入一级缓存和二级缓存（必须先开启二级缓存）
//		System.out.println(book1);

        //使用query将数据放入二级缓存
        session.createQuery("from Book where id=2").list();
//		session.createQuery("from Book").list();//将数据全放入二级缓存

        session.getTransaction().commit();
        session.close();//一级缓存被销毁，二级缓存仍然存在（它和sessionFactory绑定）
        System.out.println("-------------------------");
        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();

        //第二个session操作
//		Book book3 =(Book) session2.get(Book.class, 2);//从二级缓存查询数据（如果没有二级缓存，则从数据库查询），不需要发出sql，自动将数据同步给一级缓存
//		System.out.println(book3);
        //用query来读，不能读取二级缓存
//		Book book3 =(Book)session2.createQuery("from Book where id=2").uniqueResult();
//		System.out.println(book3);

        //批量从二级缓存获取数据
        Iterator<Book> iterator = session2.createQuery("from Book").iterate();//会发sql，只查询了id，别的属性它不查。
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("id:" + book.getId());//不发sql
            System.out.println("name:" + book.getName());//当访问除id之外的属性的时候，优先从缓存中获取数据，如果缓存没有，才发出sql
        }


        session2.getTransaction().commit();
        session2.close();

    }

    @Test
    //目标：插入一个客户，并且给这个客户下10个订单(批量插入)
    public void prepareData() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        //操作
        //保存一个客户
        Customer customer = new Customer();
        customer.setName("Jack");
        customer.setCity("杭州");
        //保存方法
        session.save(customer);

        //下10个订单
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setName(customer.getName() + "_order_" + i);
            order.setPrice(i * 9d);
            //建立关系
            order.setCustomer(customer);
            session.save(order);
        }

        session.getTransaction().commit();
        session.close();

    }

    @Test
    //关联集合级别的二级缓存
    public void testCollectionCache() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        //将数据放入二级缓存（包括类和集合，只要查询就放）
        Customer customer = (Customer) session.get(Customer.class, 2);//放入一级和二级
        //通过导航查询，将关联集合的属性放入二级缓存
        System.out.println(customer.getOrders().size());//将该客户的所有的订单全部查询出来，放入二级缓存

        session.getTransaction().commit();
        session.close();


        System.out.println("----------------------------------");

        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();
        //从二级缓存获取实体对象
        Customer customer2 = (Customer) session2.get(Customer.class, 2);//肯定不发sql，从类级别的缓存区域中获取
        //通过实体对象导航到关联集合
        System.out.println(customer2.getOrders().size());//如果缓存生效，也不发sql，会从关联集合的缓存区域中获取数据。
        System.out.println(customer2.getOrders().iterator().next());//

        session2.getTransaction().commit();
        session2.close();


        System.out.println("----------------------------------");

        Session session3 = HibernateUtils.openSession();
        session3.beginTransaction();
        //从二级缓存获取实体对象
        Customer customer3 = (Customer) session3.get(Customer.class, 2);//肯定不发sql，从类级别的缓存区域中获取
        //通过实体对象导航到关联集合
//		System.out.println(customer2.getOrders().size());//如果缓存生效，也不发sql，会从关联集合的缓存区域中获取数据。
        System.out.println(customer3.getOrders().iterator().next());//

        session3.getTransaction().commit();
        session3.close();
    }

    @Test
    public void testtemp() {
        System.out.println("Java输入输出临时路径：" + System.getProperty("java.io.tmpdir"));
        System.out.println("操作系统用户的主目录：" + System.getProperty("user.home"));
    }

    @Test
    public void testEhcache() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        session.createQuery("from Book").list();

        session.getTransaction().commit();
        session.close();

    }

    @Test
    //查询缓存的测试
    public void testQueryCache() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //将数据放入查询缓存
        session.createQuery("from Book").setCacheable(true)//打开查询缓存的大门，让数据进来
                .list();//放入一级、二级、查询（map：key：sql，value：结果）

        session.getTransaction().commit();
        session.close();
        System.out.println("--------------------");
        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();

        //将数据放入查询缓存
        List list = session2.createQuery("from Book").setCacheable(true)//打开查询缓存的大门，让数据出去
                .list();//根据查询语句，从查询缓存获取数据，如果语句不在查询缓存的key中存在，则无法从查询缓存中获取数据。
        System.out.println(list);
        session2.getTransaction().commit();
        session2.close();

    }

    @Test
    //查询缓存的测试-统计投影
    public void testQueryCacheCount() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();

        //将数据放入查询缓存
        session.createQuery("select count(b) from Book b").setCacheable(true)//打开查询缓存的大门，让数据进来
                .list();//放入一级、二级、查询（map：key：sql，value：结果）

        session.getTransaction().commit();
        session.close();
        System.out.println("--------------------");
        Session session2 = HibernateUtils.openSession();
        session2.beginTransaction();

        //将数据放入查询缓存
        List list = session2.createQuery("select count(b) from Book b").setCacheable(true)//打开查询缓存的大门，让数据出去
                .list();//根据查询语句，从查询缓存获取数据，如果语句不在查询缓存的key中存在，则无法从查询缓存中获取数据。
        System.out.println(list);
        session2.getTransaction().commit();
        session2.close();

    }


    @Test
    //缓存使用率性能监控(前提是开启了性能监视)
    public void testStatistics() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //通过会话工厂，获取统计对象
        Statistics statistics = sessionFactory.getStatistics();

        //初始状态下，命中次数和丢失次数都是0
        System.out.println("二级缓存的命中次数:" + statistics.getSecondLevelCacheHitCount());
        System.out.println("二级缓存的丢失次数:" + statistics.getSecondLevelCacheMissCount());

        Session session1 = sessionFactory.openSession();
        //原理讲解
        //查询前2条订单信息，放入二级缓存
        session1.createQuery("from Book where id<=3").list();

        session1.close();

        System.out.println("-换一个session-------------------------");


        Session session2 = sessionFactory.openSession();
        //查询1号订单
        Book book1 = (Book) session2.get(Book.class, 2);
        System.out.println(book1);
        Book book2 = (Book) session2.get(Book.class, 3);
        System.out.println(book2);

        System.out.println("二级缓存的命中次数:" + statistics.getSecondLevelCacheHitCount());
        System.out.println("二级缓存的丢失次数:" + statistics.getSecondLevelCacheMissCount());

        System.out.println("-------------------------");

        //查询1号订单
        Book book4 = (Book) session2.get(Book.class, 4);
        System.out.println(book4);

        System.out.println("二级缓存的命中次数:" + statistics.getSecondLevelCacheHitCount());
        System.out.println("二级缓存的丢失次数:" + statistics.getSecondLevelCacheMissCount());
        session2.close();
    }


}
