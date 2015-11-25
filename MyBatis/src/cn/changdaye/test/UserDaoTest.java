package cn.changdaye.test;


import cn.changdaye.dao.UserDao;
import cn.changdaye.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by 常晓虎  on 2015/11/25.
 * 描述：
 */
public class UserDaoTest {
    private SqlSessionFactory sqlSessionFactory = null;

    @org.junit.Before
    public void setUp() throws Exception {
        //加载全局配置文件
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用mybatis的类sqlSesionfactoryBuider创建工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testFindAllUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> allUser = mapper.findAllUser();
        for (User user : allUser) {
            System.out.println(user.getUsername());
        }
    }

    @org.junit.Test
    public void testFindUserByID() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userByID = mapper.findUserByID(1);
        System.out.println(userByID);
    }

    @org.junit.Test
    public void testInsertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setAddress("张三");
        user.setBirthday(new Date());
        user.setUsername("王二");
        user.setSex("那");

        mapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user);
    }

    @org.junit.Test
    public void testUpdateUserByID() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("wanger");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.updateUserByID(user);
    }
}