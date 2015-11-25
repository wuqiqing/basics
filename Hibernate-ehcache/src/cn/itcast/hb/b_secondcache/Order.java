package cn.itcast.hb.b_secondcache;

public class Order {
    private Integer id;
    private String name;
    private Double price;

    //关系：一个订单属于一个客户
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", name=" + name + ", price=" + price
                + ", customer=" + customer + "]";
    }


}
