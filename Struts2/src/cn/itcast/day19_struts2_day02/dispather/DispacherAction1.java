package cn.itcast.day19_struts2_day02.dispather;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 返回字符串 Action 接口 声明默认的结果集视图名称
 *
 * @author Administrator 转发的action 对象
 */
public class DispacherAction1 extends ActionSupport {
    public String dispatcherMethod() {
        System.out.println("dispatcher  1.....");
        return "dispatcher1";
    }
}
