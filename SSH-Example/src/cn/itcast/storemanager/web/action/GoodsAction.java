package cn.itcast.storemanager.web.action;

import cn.itcast.storemanager.domain.Goods;
import cn.itcast.storemanager.service.GoodsService;
import org.apache.struts2.ServletActionContext;

//货物操作的action
public class GoodsAction extends BaseAction<Goods> {
    //注入service
    private GoodsService goodsService;

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    //异步请求根据简记码查询货物
    public String findByNmAjax() {
        //调用serivce查询数据
        //简记码是唯一的，只能查询出一条数据。（业务角度规定）
        result = goodsService.findByNm(model.getNm());
        //只要转换为json的异步请求，都使用json的结果集类型，都可以返回json的结果集的名字
        return "json";
    }

    //异步请求：根据货物名称模糊查询
    public String findByNameLikeAjax() {
        result = goodsService.findByNameLike(model.getName());
        return "json";
    }

    //货物入库
    public String save() {
        //调用serivce
        //service层的方法一定要考虑事务
        goodsService.saveGoods(model);

        return "savejsp";//反复入库，每次入完再跳回入库的页面
    }

    //分页查询
    public String listpage() {
        //初始化分页bean
//		Pagination<Goods> pagination = new Pagination<Goods>();

        //将页面传过来的参数封装到分页bean中
        pagination.setParameterMap(ServletActionContext.getRequest().getParameterMap());//获取到所有的参数

        //将前台用户点击的页码和最大查询的记录数封装到分页bean中
        //判断赋值
//		if(page>0){
//			pagination.setPage(page);
//		}
//		if(pageSize>0){
//			pagination.setPageSize(pageSize);
//		}

        //调用serivce层查询所需要的数据，然后封装到盒子中（分页bean）
        goodsService.findPageData(pagination);
        //将已经封装了返回结果的分页bean放入值栈，让页面去获取相应的数据
        result = pagination;

        return "remainjsp";//跳转到列表

    }

//	//我在值栈中放入一个page属性，然后struts，自动将param自动寻找page名字的key或者属性名，然后试图封装进去（调用settter方法）
//	//页码
//	private int page;
//	public void setPage(int page) {
//		this.page = page;
//	}
//	//最大页数
//	private int pageSize;
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}


}
