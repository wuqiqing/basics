package cn.itcast.day19_struts2_day02.dispather;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 返回字符串 Action 接口 声明默认的结果集视图名称
 *
 * @author Administrator
 */
public class RedirectAction extends ActionSupport {
    public String redirect() {
        System.out.println("redirect.....");
        return "redirect";
    }
}
