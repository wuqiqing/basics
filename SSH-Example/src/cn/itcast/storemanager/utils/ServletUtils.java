package cn.itcast.storemanager.utils;

import cn.itcast.storemanager.domain.Userinfo;
import org.apache.struts2.ServletActionContext;

//操作serlvet的工具类
public class ServletUtils {

    //登录用户的key
    private static final String loginUserKey = "userinfo";//节约资源

    //将当前登录用户放入session
    //既可以放用户到session，也可以清除session
    public static void setLoginUserToSession(Userinfo user) {
        if (user == null) {
            //清除session
            ServletActionContext.getRequest().getSession().removeAttribute(loginUserKey);
        } else {
            //放入session
            ServletActionContext.getRequest().getSession().setAttribute(loginUserKey, user);
        }
    }

    //从session中获取当前登录用户
    public static Userinfo getLoginUserFromSession() {
        Object object = ServletActionContext.getRequest().getSession().getAttribute(loginUserKey);
        return object == null ? null : (Userinfo) object;
    }
}
