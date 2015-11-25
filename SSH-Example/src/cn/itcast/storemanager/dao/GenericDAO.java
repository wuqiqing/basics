package cn.itcast.storemanager.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

//通用dao的接口
//思路：根据hibernateTemplate来编写
//ID extends Serializable泛型的好处可以统一维护
public interface GenericDAO<T, ID extends Serializable> {

    //保存
    public void save(Object domain);

    //修改
    public void update(Object domain);

    //删除
    public void delete(Object domain);

    //根据id查询
    public T findById(Class<T> domainClass, ID id);

    //查询所有
    public List<T> findAll(Class<T> domainClass);

    //根据条件查询
    //hql命名查询
    public List<T> findByNamedQuery(String queryName, Object... values);


    //qbc离线条件查询
    public List<T> findByCriteria(DetachedCriteria criteria);

    //分页使用：查询所有记录数
    public long findTotalCount(DetachedCriteria criteria);

    //分页使用：查询分页数据
    public List<T> findPageData(DetachedCriteria criteria, int firstResult,
                                int maxResults);


}
