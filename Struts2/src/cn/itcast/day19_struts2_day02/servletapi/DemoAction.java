package cn.itcast.day19_struts2_day02.servletapi;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DemoAction extends ActionSupport implements ServletResponseAware, ServletContextAware {

    @Override
    public String execute() throws Exception {
        // 方式二 可以通过 ServletActionContext
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return super.execute();
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setServletResponse(HttpServletResponse arg0) {
        // TODO Auto-generated method stub

    }

}
