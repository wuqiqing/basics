package cn.itcast.ssh.web.action;

import cn.itcast.ssh.domain.Book;
import cn.itcast.ssh.service.BookService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//表现层action
public class BookAction extends ActionSupport implements ModelDriven<Book> {
    //new book
    Book book = new Book();
    //action自动装配service，（struts2自动从spring中获取对应的bean）
    //只要提供一个setter方法（前提是使用了整合工厂StrutsSpringObjectFactory），那么struts2会自动到spring的容器中寻找setter方法的bean的名字，如果找到，自动调用setter方法进行注入
    private BookService bookService;

    @Override
    public Book getModel() {
        return book;
    }

    //注意名字必须和bean的名字一致！！！！！！
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //业务方法，保存
    public String add() {
        //调用业务层
        //以前
//		BookService bookService =new BookService();
//		bookService.saveBook(book);
        bookService.saveBook(book);
        System.out.println("添加完成！");

        return NONE;
    }


}
