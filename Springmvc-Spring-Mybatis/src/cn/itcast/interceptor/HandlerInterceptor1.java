package cn.itcast.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInterceptor1 implements HandlerInterceptor {
    /*
     * 在处理器映射器之前进行执行
     * return false ：表示拦接  return true 表示放行
     */
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        System.out.println("interceptor1................preHandle");
        return true;
    }

    //postHandle视图还没有返回之前进行执行，可以对视图进行一些额外添加，渲染
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("interceptor1................postHandle");
    }

    //返回视图以后进行执行
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("interceptor1................afterCompletion");

    }

}
