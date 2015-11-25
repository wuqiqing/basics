package cn.itcast.ssh.service;

import cn.itcast.ssh.dao.BookDAO;
import cn.itcast.ssh.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//业务层
@Service("bookService")
public class BookService {
    //注入dao
    @Autowired
    private BookDAO bookDAO;

    //保存图书
    @Transactional
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
    public void saveBook(Book book) {
        bookDAO.save(book);
//		findAllBook();
    }

    //查询图书
    @Transactional(readOnly = true)
    public void findAllBook() {

    }

}
