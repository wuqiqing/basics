package cn.itcast.storemanager.web.action;


//import org.apache.log4j.Logger;

import cn.itcast.storemanager.domain.Userinfo;
import cn.itcast.storemanager.service.UserService;
import cn.itcast.storemanager.utils.ServletUtils;

public class UserAction extends BaseAction<Userinfo> {
    //日志
    //log4j的日志
//	private static final Logger LOG =Logger.getLogger(UserAction.class);
    //使用slf4j（日志接口包）:好处，日志的实现可以随便换，但代码不需要变
    //slf4j使用的是静态绑定：（提供三个东西：slf4j的接口包+连接包+具体的日志实现包）
//	private static final Logger LOG =LoggerFactory.getLogger(UserAction.class);

    //模型驱动
//	Userinfo user = new Userinfo();
//
//	@Override
//	public Userinfo getModel() {
//		return user;
//	}

    //注入UserService
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //用户登录
    public String login() {
        LOG.info("用户开始登录了。。。");
        //业务逻辑，调用业务层
        //参数：传对象呢？还是传两个具体参数（如果业务很确定就使用这两个参数，可以用这种方法）
//		userService.login(String user,String );
        Userinfo user = userService.login(model);
        //业务判断
        if (null == user) {
            //登录失败，将错误信息放入actionerror，并跳转会登录
//			this.getActionErrors().add(this.gettex);
            this.addActionError(this.getText("UserAction.loginerror"));
            return "loginjsp";//校验失败或者登录失败，都是跳转回去登录页面
        } else {
            //登录成功，将用户放入session，并跳转到主页
//			ServletActionContext.getRequest().getSession().setAttribute("user", user);
            ServletUtils.setLoginUserToSession(user);
            return SUCCESS;
        }

    }

}
