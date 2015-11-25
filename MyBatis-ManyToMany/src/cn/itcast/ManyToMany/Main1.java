package cn.itcast.ManyToMany;

import cn.itcast.dao.OrdersMapper;
import cn.itcast.dao.UserMapper;
import cn.itcast.domain.Orders;
import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Main1 {
    @Test
    public void findUserAndItems() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findUserAndItems();

        System.out.println(list);


    }

    @Test
    public void TestCaseInsert() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setAddress("������˳����");
        user.setBirthday(new Date());
        user.setUsername("С��");
        user.setSex("��");
        userMapper.insertUser(user);

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        Orders orders = new Orders();
        //mybatis�������Լ�ά�������ϵ
        orders.setUserId(user.getId());
        orders.setNumber("1111832434");
        orders.setCreateTime(new Date());

        ordersMapper.insertOrders(orders);


        sqlSession.commit();
        sqlSession.close();


    }
}
