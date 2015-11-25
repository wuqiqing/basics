package cn.itcast.test;

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
import java.util.List;

public class Main1 {

    @Test
    public void findAllUser() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<Orders> list = ordersMapper.findOrdersLazyLoading();

        System.out.println(list);

    }

    @Test
    public void testOneCache() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //����һ�����棬��һ�β�ѯ
        User user = userMapper.findUserByID(1);

        System.out.println(user);
        //����һ�����棬����һ������ͱ����
        sqlSession.commit();

        //�ڶ��β�ѯ���ݿ�
        User user1 = userMapper.findUserByID(1);

        System.out.println(user1);


    }

    @Test
    public void testSecondCache() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        //ʹ��sqlSession1����ѯId=1���û�
        UserMapper userMapper = sqlSession1.getMapper(UserMapper.class);
        User user = userMapper.findUserByID(1);

        System.out.println(user);

        sqlSession1.commit();
        sqlSession1.close();


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        //sqlSession2�ڶ��β�ѯId=1���û�
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user2 = userMapper2.findUserByID(1);

        System.out.println(user2);


    }


}
