package cn.itcast.storemanager.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

//通用dao实现类
public class GenericDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID> {

    @Override
    public void save(Object domain) {
        super.getHibernateTemplate().save(domain);
    }

    @Override
    public void update(Object domain) {
        super.getHibernateTemplate().update(domain);
    }

    @Override
    public void delete(Object domain) {
        super.getHibernateTemplate().delete(domain);
    }

    @Override
    public T findById(Class<T> domainClass, ID id) {
        return super.getHibernateTemplate().get(domainClass, id);
    }

    @Override
    public List<T> findAll(Class<T> domainClass) {
        return super.getHibernateTemplate().loadAll(domainClass);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Object... values) {
        return super.getHibernateTemplate().findByNamedQuery(queryName, values);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria criteria) {
        return super.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public long findTotalCount(DetachedCriteria criteria) {
        //统计是一种特殊的投影查询，返回结果根据具体的类型自动进行封装。
        criteria.setProjection(Projections.rowCount());//Hibernate会改变结果集的封装策略！（）
        //Hibernate的criteria查询，默认的结果集封装策略是ROOT_ENTITY 将结果集封装到根实体对象
        //当使用投影的时候，会改变为PROJECTION 根据投影的结果类型自动封装 （缺点：默认无法再封装回实体对象了）

        List list = super.getHibernateTemplate().findByCriteria(criteria);
//		Query query;
//		query.uniqueResult()--->query.list().get(0);

        return (Long) (list.isEmpty() ? 0 : list.get(0));
    }

    @Override
    public List<T> findPageData(DetachedCriteria criteria, int firstResult,
                                int maxResults) {
        return super.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

}
