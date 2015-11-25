package cn.itcast.storemanager.service;

import cn.itcast.storemanager.dao.GenericDAO;
import cn.itcast.storemanager.domain.Goods;
import cn.itcast.storemanager.domain.History;
import cn.itcast.storemanager.domain.Userinfo;
import cn.itcast.storemanager.page.Pagination;
import cn.itcast.storemanager.utils.BusiConstant;
import cn.itcast.storemanager.utils.DateTimeUtils;
import cn.itcast.storemanager.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class GoodsServiceImpl extends BaseService<Goods, String> implements GoodsService {
    //注入dao
    private GenericDAO<Goods, String> goodsDAO;
    private GenericDAO<History, String> historyDAO;

    public void setGoodsDAO(GenericDAO<Goods, String> goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public void setHistoryDAO(GenericDAO<History, String> historyDAO) {
        this.historyDAO = historyDAO;
    }


    @Override
    public Goods findByNm(String nm) {
        //调用dao
        List<Goods> list = goodsDAO.findByNamedQuery("Goods.findByNm", nm);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Object findByNameLike(String name) {
        return goodsDAO.findByNamedQuery("Goods.findByNameLike", "%" + name + "%");
    }

    @Override
    //入库
    public void saveGoods(Goods goods) {
        //更新或者保存
        //获取隐藏域id
        String id = goods.getId();
        //当前登录人
        Userinfo user = ServletUtils.getLoginUserFromSession();

        //根据id判断，如果id不为空，说明该货物在数据库中存在，只需要更新数量即可
        if (StringUtils.isNotBlank(id)) {//判断不是null，并且，不是"",并且，不是全空格
            //更新
            //Hibernate的更新有两种：update方法和快照更新
            //因为我们这里只更新数量一个字段，推荐使用快照更新
            //先有持久再改一级缓存（字段值）
            Goods persistGoods = goodsDAO.findById(Goods.class, id);
            persistGoods.setAmount(persistGoods.getAmount() + goods.getAmount());//入库逻辑：原来的数量+新增的数量

            //操作日志：日志功能:只有插入、查询、删除
            //瞬时态
            History history = new History();
            history.setAmount(goods.getAmount());//本次操作的数量
            history.setRemain(persistGoods.getAmount());//本次的余量
//			history.setType("1");///*类型1:入库，２：出库*/
            history.setType(BusiConstant.BUZITYPE_IN);///*类型1:入库，２：出库*/
//			history.setDatetime(new Date().toLocaleString());//当前的系统时间
            history.setDatetime(DateTimeUtils.getCurrentDateTimeString());//当前的系统时间
            //当前登陆人如果为null（没登陆），则会出现空指针。因为可以增加登录拦截器，如果你操作的时候，没有登录，则先跳转到登录页面先登录，保证，session中有用户
            history.setUser(user.getName());//设置操作人员（当前登陆人名字）
            history.setGoods(persistGoods);//持久态对象，，Hibernate会自动将goods的id左外外键值插入到历史表中
//			history.setGoods((new Goods()).setId(id));//放置一个脱管对象

            //调用dao的save
            historyDAO.save(history);

        } else {
            //保存：新的货物
            //瞬时态对象
            goodsDAO.save(goods);//持久态

            //插入一条操作日志
            //瞬时态
            History history = new History();
            history.setAmount(goods.getAmount());//本次操作的数量
            history.setRemain(goods.getAmount());//本次的余量
//			history.setType("1");///*类型1:入库，２：出库*/
            history.setType(BusiConstant.BUZITYPE_IN);///*类型1:入库，２：出库*/
            history.setDatetime(DateTimeUtils.getCurrentDateTimeString());//当前的系统时间
            history.setUser(user.getName());//设置操作人员（当前登陆人名字）
            history.setGoods(goods);//持久态对象，，Hibernate会自动将goods的id左外外键值插入到历史表中

            //调用dao的save
            historyDAO.save(history);

        }


    }

    @Override
    //分页查询:离线条件
    public void findPageData(Pagination<Goods> pagination) {
        //1.准备工作：将条件都拿出来，进行拼接
        //构建一个离线条件
        DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);

        //获取前台的条件
        Map<String, String[]> parameterMap = pagination.getParameterMap();
//		String nm=parameterMap.get("nm")==null ?null: parameterMap.get("nm")[0];//key是跟业务息息相关
        String nm = getParameterValue(parameterMap, "nm");
        String name = getParameterValue(parameterMap, "name");
        String storeid = getParameterValue(parameterMap, "store.id");

        if (StringUtils.isNotBlank(nm)) {
            //拼接条件
            criteria.add(Restrictions.eq("nm", nm));
        }
        if (StringUtils.isNotBlank(name)) {
            //拼接条件
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (StringUtils.isNotBlank(storeid)) {
            //拼接条件：
            //拼接关联集合属性的（外键条件拼接）
            //方法一：只需要将一个拥有主键id的store对象给goods的store属性即可
//			Store store = new Store();
//			store.setId(storeid);
//			criteria.add(Restrictions.eq("store", store));
            //方法二：导航封装
            criteria.add(Restrictions.eq("store.id", storeid));
        }


        findGenaricPageData(pagination, criteria, goodsDAO);

    }


//	//获取参数的值
//	private String getParameterValue(Map<String, String[]> parameterMap,String key){
//		String[] values = parameterMap.get(key);
//		return values==null?null:values[0];
//	}

}
