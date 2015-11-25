package cn.itcast.ssh.web.action;

import cn.itcast.ssh.domain.Book;
import cn.itcast.ssh.service.BookService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//表现层action
@Controller("bookAction")
@Scope("prototype")//必须多例
@Namespace("/")
@ParentPackage("struts-default")
public class BookAction extends ActionSupport implements ModelDriven<Book> {
    //new book
    Book book = new Book();
    //注入service
    @Autowired
    private BookService bookService;

    @Override
    public Book getModel() {
        return book;
    }

    //业务方法，保存
//	@Action(value="book_add",results=@Result(location="/index.jsp",name="success"))
    @Action(value = "book_add", results = {@Result(location = "/index.jsp", name = "success"), @Result(location = "/addbook.jsp", name = "input")})
    public String add() {
        bookService.saveBook(book);
        System.out.println("添加完成！");
        return SUCCESS;
    }


}
