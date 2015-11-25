package cn.itcast.hb.b_secondcache;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Integer id;
    private String name;
    private String city;

    //关系：一个客户对应多个商品:如果你希望元素重复，而且希望有顺序，则可以使用list
    private Set<Order> orders = new HashSet<Order>();

    //
    /*public Customer() {
	}
	//
	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	*/

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

	/*@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", city=" + city
				+ ", orders=" + orders + "]";
	}*/

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", city=" + city + "]";
    }


}
