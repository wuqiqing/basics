package cn.itcast.day19_struts2_day02.action.model;

import cn.itcast.day19_struts2_day02.action.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 第三种 采用接口注入对象属性
 */

/**
 * ModelDriven 接口注入
 *
 * @author Administrator 1: 页面不需要ognl 表达式 只需要提供对象实例化即可 2: 值栈ValueStack modelDriven User 对象存放栈顶
 */
public class LoginActionByModel extends ActionSupport implements ModelDriven<User> {
    private User user = new User();// 对象声明 但是没初始化 自己手动实例化对象

    @Override
    public String execute() throws Exception {
        System.out.println(user);
        return super.execute();
    }

    @Override
    public User getModel() {
        // 接口自动属性值注入当前user 对象
        return user;
    }

}
