package cn.itcast.day19_struts2_day02.action.xml.validation;

import cn.itcast.day19_struts2_day02.action.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    /**
     * 基于xml 版本 请求参数合法校验
     *
     * @return
     */
    @InputConfig(resultName = "register_error")
    public String register() {
        System.out.println(user);
        return "register_ok";
    }

    @InputConfig(resultName = "login_error")
    public String login() {
        System.out.println(user);
        return "login_ok";
    }

    @Override
    public User getModel() {
        return user;
    }

}
