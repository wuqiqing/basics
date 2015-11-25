package cn.itcast.test;

import cn.itcast.dao.UserMapper;
import cn.itcast.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main1 {

    @Test
    public void testSpringmybatis() {

        ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
        UserMapper userMapper = (UserMapper) app.getBean("userMapper");
        User user = userMapper.findUserByID(1);

        System.out.println(user);

    }

}
