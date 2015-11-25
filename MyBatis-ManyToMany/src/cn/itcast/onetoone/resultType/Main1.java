package cn.itcast.onetoone.resultType;

import cn.itcast.dao.OrdersCustomDao;
import cn.itcast.dao.OrdersMapper;
import cn.itcast.domain.Orders;
import cn.itcast.domain.OrdersCustom;
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

        OrdersCustomDao ordersCustomDao = sqlSession.getMapper(OrdersCustomDao.class);

        List<OrdersCustom> list = ordersCustomDao.findOrdersAndUser();

        System.out.println(list);


    }

    @Test
    public void findUserAndOrderByMap() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> list = ordersMapper.findOrdersAndUserByMap();


        System.out.println(list);


    }

    @Test
    public void findOrdersAndDetail() throws Exception {
        //����ȫ�������ļ�
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //ʹ��mybatis����sqlSesionfactoryBuider��������
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

        List<Orders> list = ordersMapper.findOrdersAndDetail();

        System.out.println(list);


    }

}
