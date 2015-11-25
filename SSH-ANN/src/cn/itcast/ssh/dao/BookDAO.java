package cn.itcast.ssh.dao;

import cn.itcast.ssh.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("bookDAO")
public class BookDAO extends HibernateDaoSupport {

    @Autowired
//	private SessionFactory sessionFactory;
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    //保存图书
    public void save(Book book) {
        super.getHibernateTemplate().save(book);
    }

}
