package cn.itcast.ssh.test;

import cn.itcast.ssh.domain.Book;
import cn.itcast.ssh.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {
    //注入测试的bean
    @Autowired
    private BookService bookService;

    @Test
    //测试保存
    public void testSaveBook() {
        //当成web层action
        Book book = new Book();
        book.setName("约会专家");
        book.setPrice(998d);
        bookService.saveBook(book);
    }

    @Test
    //测试条件查询
    public void testFindByNameLike() {
        List<Book> list = bookService.findByNameLike("约");
        System.out.println(list);
    }

}
