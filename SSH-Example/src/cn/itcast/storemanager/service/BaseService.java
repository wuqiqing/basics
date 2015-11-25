package cn.itcast.storemanager.service;

import cn.itcast.storemanager.dao.GenericDAO;
import cn.itcast.storemanager.page.Pagination;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//公用代码，该类必须用来继承
public abstract class BaseService<T, ID extends Serializable> {

    // 获取参数的值
    protected String getParameterValue(Map<String, String[]> parameterMap,
                                       String key) {
        String[] values = parameterMap.get(key);
        return values == null ? null : values[0];
    }

    //通用分页逻辑
    protected void findGenaricPageData(Pagination<T> pagination,
                                       DetachedCriteria criteria, GenericDAO<T, ID> dao) {
        //2.查询：（所有的分页，都需要查询两次：一次是所有记录数，一次是分页的数据）,调用dao
        //2.1查询总记录数
        long totalCount = dao.findTotalCount(criteria);
        //封装回分页bean
        pagination.setTotalCount(totalCount);

        //2.2：分页的数据list
        //去掉投影，并且重置结果集的封装策略为ROOT_ENTITY
        criteria.setProjection(null);
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

//		int firstResult=(pagination.getPage()-1)*pagination.getPageSize();
//		int maxResults=pagination.getPageSize();
        List<T> pageDataList = dao.findPageData(criteria, pagination.getFirstResult(), pagination.getMaxResults());
        //封装回分页bean
        pagination.setPageDataList(pageDataList);
    }

}
