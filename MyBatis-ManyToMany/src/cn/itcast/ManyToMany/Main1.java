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
        //加载全局配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用mybatis的类sqlSesionfactoryBuider创建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findUserAndItems();

        System.out.println(list);


    }

    @Test
    public void TestCaseInsert() throws Exception {
        //加载全局配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用mybatis的类sqlSesionfactoryBuider创建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setAddress("北京市顺义区");
        user.setBirthday(new Date());
        user.setUsername("小明");
        user.setSex("男");
        userMapper.insertUser(user);

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        Orders orders = new Orders();
        //mybatis是我们自己维护外键关系
        orders.setUserId(user.getId());
        orders.setNumber("1111832434");
        orders.setCreateTime(new Date());

        ordersMapper.insertOrders(orders);


        sqlSession.commit();
        sqlSession.close();


    }
}
