package test;

import changdaye.entity.Customer;
import changdaye.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by 常晓虎  on 2015/11/24.
 * 描述：
 */
public class Demo {
    @Test
    public void test1() {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, 1);
        System.out.println(customer.getName());
        customer.setName("changdaye111");

//        session.update(customer);
        session.getTransaction().commit();
    }
}
