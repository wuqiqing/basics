package cn.itcast.day19_struts2_day02.attribute.setter;

import cn.itcast.day19_struts2_day02.action.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginActionByAttribute extends ActionSupport {
    // 演示属性注入方式 封装实体类 User
    // 实体类User 封装数据 action 提供封装的属性即可
    private String name;
    private String password;

    // 只需要提供setter 方法即可
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        System.out.println(user);
        // 调用业务层....
        return super.execute();
    }

}
