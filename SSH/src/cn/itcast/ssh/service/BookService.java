package cn.itcast.ssh.service;

import cn.itcast.ssh.dao.BookDAO;
import cn.itcast.ssh.domain.Book;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

//业务层
public class BookService {
    //注入dao
    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    //保存图书
    //注意：service中方法的名字，必须符合事务的配置规则save*
    public void saveBook(Book book) {
        //调用dao
        bookDAO.save(book);
    }

    //条件查询:根据图书的名称模糊查询
    public List<Book> findByNameLike(String name) {
        //1.hql命名查询的方式:命名查询的名字，第二个是参数
//		return bookDAO.findByNamedQuery("Book.findByNameLike", "%"+name+"%");

        //2.qbc离线条件查询
        //创建一个离线对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
        //条件你想怎么加就怎么加，不需要关心sql语句怎么写
        criteria.add(Restrictions.like("name", "%" + name + "%"));

        return bookDAO.findByCriteria(criteria);
    }

}
