package cn.itcast.day19_struts2_day02.code.validation;

import cn.itcast.day19_struts2_day02.action.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.commons.lang3.StringUtils;

public class LoginActionValidation extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    // 添加自定义校验器
    @Override
    // 全局校验器
    public void validate() {
        // 该方法 校验目标action 所有的业务方法 在所有的业务方法之前执行...
        // 如果非法 在ActionErrors(存放一般 代码错误 流程出错 ) FiledErrors(主要存放 表单提交参数错误信息 校验错误信息 )
        // 用户名必须非空6位 密码必须纯数字 lang StringUtils
        if (StringUtils.isBlank(user.getName()) || user.getName().length() != 6) {
            // 用户名非法 代码中使用资源文件信息
            this.addFieldError("name_error", this.getText("valid.filed.name"));
        }
    }

    // 局部校验器 部分方法有效 validateXxxx()
    // public void validateLogin() {
    // // 该方法 校验目标action 所有的业务方法 在所有的业务方法之前执行...
    // // 如果非法 在ActionErrors(存放一般 代码错误 流程出错 ) FiledErrors(主要存放 表单提交参数错误信息 校验错误信息 )
    // // 用户名必须非空6位 密码必须纯数字 lang StringUtils
    // if (StringUtils.isBlank(user.getName()) || user.getName().length() != 6) {
    // // 用户名非法
    // this.addFieldError("name_error", "用户名必须6位非空");
    // }
    // }
    @InputConfig(resultName = "register_error")
    // 作用 修改worlflow 拦截器 出现错误信息 默认跳转视图 Input 修改结果集视图名称
    public String register() {
        System.out.println("----register----------" + user);
        return "register";
    }

    // @SkipValidation// 注解 表示当前的业务方法 跳过校验器
    @InputConfig(resultName = "login_error")
    public String login() {
        System.out.println(user);
        return "ok";
    }

    @Override
    public User getModel() {
        return user;
    }
}
