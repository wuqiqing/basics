package cn.itcast.day19_struts2_day02.object.setter;

import cn.itcast.day19_struts2_day02.action.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 实体类对象直接封装 必须提供setter getter
 *
 * @author Administrator
 */
public class LoginActionByObject extends ActionSupport {
    private User user;// 注入失败... 没有实例化对象

    // 1: user 要实例化 对象 login.jsp 表单里引入 ongl 表达式即可 普通字符串 放入struts2 开发环境 struts2 认为ognl 表达式
    // 2: 注入的属性不止一个 多个属性... 光有setUser

    public User getUser() {
        System.out.println("---get----");
        return user;
    }

    public void setUser(User user) {
        System.out.println("---set---");
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(user);
        return super.execute();
    }

}
