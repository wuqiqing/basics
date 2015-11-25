package cn.itcast.ssh.dao;

import cn.itcast.ssh.domain.Book;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

//持久层
//spring为了简化Hibernate开发，提供了一个类，Hibernatetemplate类。
//HibernateDaoSupport更加简化开发，模版类不需要你自己去new，只需要注入sessionFactory
public class BookDAO extends HibernateDaoSupport {

    //保存
    public void save(Book book) {
        //（注入session工厂）获取session，开启事务，操作数据库，提交事务，关闭session
//		HibernateTemplate hibernateTemplate=null;//使用该类不需要关注session，类的底层，帮你开关了session
//		hibernateTemplate.get
//		hibernateTemplate.save(book);
        super.getHibernateTemplate().save(book);
    }

    //更新
    public void update(Book book) {
        super.getHibernateTemplate().update(book);
    }

    //删除
    public void delete(Book book) {
        super.getHibernateTemplate().delete(book);
    }

    //根据id查询一个对象
    public Book findById(Integer id) {
        return super.getHibernateTemplate().get(Book.class, id);
    }

    //查询所有
    public List<Book> findAll() {
        return super.getHibernateTemplate().loadAll(Book.class);
//		return super.getHibernateTemplate().find("from Book");//hql方式
    }

    //条件查询(hql和qbc)
    //hql命名查询(第一个参数是命名查询的名字)
    public List<Book> findByNamedQuery(String queryName, Object... values) {
        return super.getHibernateTemplate().findByNamedQuery(queryName, values);
    }

    //qbc离线条件查询
    public List<Book> findByCriteria(DetachedCriteria criteria) {
        return super.getHibernateTemplate().findByCriteria(criteria);
    }

}
