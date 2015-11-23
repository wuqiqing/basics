package cn.itcast.day19_struts2_day02.bookaction;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
    public String addBook() {
        System.out.println("----add  book");
        return null;
    }

    public String deleteBook() {
        System.out.println("----delete  book");
        return null;
    }

    public String updateBook() {
        System.out.println("----update  book");
        return null;
    }

    public String queryBook() {
        System.out.println("----query  book");
        return null;
    }
}
