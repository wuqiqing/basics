package cn.itcast.storemanager.web.action;

import cn.itcast.storemanager.domain.Store;
import cn.itcast.storemanager.service.StoreService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

//仓库操作相关的action
public class StoreAction extends BaseAction<Store> {


    //注入service
    private StoreService storeService;

    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }


    //保存仓库
    @InputConfig(resultName = "addinput")//更改默认的校验错误后默认跳转的input结果集
    public String add() {
        //调用业务层
        storeService.saveStore(model);
        return "listaction";//查询完成之后，跳转到listaction，进行重新查询数据，并刷新列表
    }

    //查询所有仓库
    public String list() {
        //逻辑
        //调用业务层，查询所有仓库，将返回的list，放入值栈
//		List<Store> storeList= storeService.findAllStoreList();
        //将数据放入值栈2大类三种
        //1.root栈
        //1.1匿名，栈顶
//		ActionContext.getContext().getValueStack().push(storeList);
        //1.2有名字，map栈顶
//		ActionContext.getContext().getValueStack().set("storeList", storeList);
        //2.map栈-#
//		ActionContext.getContext().put("storeList", storeList);

        //调用父类的公用方法
//		super.setToValueStackRoot("storeList", storeList);
//		result=storeList;
        result = storeService.findAllStoreList();//结果对象的引用

        return "listjsp";
    }

    //删除仓库
    public String delete() {
        //调用serivce
        storeService.deleteStoreById(model);

        return "listaction";
    }

    //显示修改页面
    public String editview() {
        //根据id，查询数据，压入栈顶，给页面回显
        Store store = storeService.findStoreById(model.getId());
        //压栈顶
        super.pushToValueStackRoot(store);
        return "editinput";

    }

    //修改
    @InputConfig(resultName = "editinput")
    public String edit() {
        //调用业务层更新数据
        storeService.updateStore(model);
        return "listaction";
    }

    //异步请求所有仓库
    public String findAllStoreAjax() {
        //先查询出结果，然后将结果交给struts插件，（会自动将其转换为json并且写入response）
        result = storeService.findAllStoreList();

        return "json";
    }

    //属性驱动
//	private String name;//因为他在值栈中，struts2的拦截器会自动向值栈中能找到的属性或key来封装属性。
    //在action上增加一个成员变量属性（因为action在root栈，所以属性也在root栈）
//	private Object result;
//	public Object getResult() {
//		return result;
//	}
//	


}
