package cn.itcast.day19_struts2_day02.dispather;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * 返回字符串 Action 接口 声明默认的结果集视图名称
 *
 * @author Administrator
 */
public class DispacherAction extends ActionSupport {
    public String dispatcherMethod() {
        ServletActionContext.getRequest().setAttribute("itcast", "hello itcast");
        System.out.println("dispatcher.....");
        return "dispatcher";
    }
}
