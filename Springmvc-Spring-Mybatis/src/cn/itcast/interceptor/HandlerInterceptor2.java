package cn.itcast.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInterceptor2 implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        System.out.println("interceptor2................preHandle");
        return false;
    }


    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        System.out.println("interceptor2................postHandle");

    }

    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("interceptor2................afterCompletion");

    }


}
