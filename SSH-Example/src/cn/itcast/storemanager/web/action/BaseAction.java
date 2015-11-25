package cn.itcast.storemanager.web.action;

import cn.itcast.storemanager.page.Pagination;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//action的公用代码
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    //日志抽取:性能有点损失
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //模型驱动，new数据模型
    //问题：如何new
    protected T model;
    //属性驱动
//	private String name;//因为他在值栈中，struts2的拦截器会自动向值栈中能找到的属性或key来封装属性。
    //在action上增加一个成员变量属性（因为action在root栈，所以属性也在root栈）
    protected Object result;
    //初始化分页bean
    protected Pagination<T> pagination = new Pagination<T>();

    //构造方法：初始化model
    public BaseAction() {
        //得到带有泛型的类型，如BaseAction<Userinfo>
        Type superclass = this.getClass().getGenericSuperclass();
        //转换为参数化类型
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        //获取泛型的第一个参数的类型类，如Userinfo
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        //实例化具体的传进来的类型
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T getModel() {
        return model;
    }

    //值栈操作的公用方法
    //将值放入root栈顶，匿名
    protected void pushToValueStackRoot(Object value) {
        ActionContext.getContext().getValueStack().push(value);
    }

    //将值放入root栈顶，有名字（map对象）
    protected void setToValueStackRoot(String key, Object value) {
        ActionContext.getContext().getValueStack().set(key, value);
    }

    //将值放入map栈
    protected void putToValueStackMap(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }

    public Object getResult() {
        return result;
    }

    //我在值栈中放入一个page属性，然后struts，自动将param自动寻找page名字的key或者属性名，然后试图封装进去（调用settter方法）
    //页码
//	protected int page;
    public void setPage(int page) {
        if (page > 0) {
            pagination.setPage(page);
        }
//		this.page = page;
    }

    //最大页数
//	protected int pageSize;
    public void setPageSize(int pageSize) {
        if (pageSize > 0)
            pagination.setPageSize(pageSize);
//		this.pageSize = pageSize;
    }


}
