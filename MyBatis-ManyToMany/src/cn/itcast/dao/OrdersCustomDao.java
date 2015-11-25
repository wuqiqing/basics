package cn.itcast.dao;

import cn.itcast.domain.OrdersCustom;

import java.util.List;

public interface OrdersCustomDao {
    public List<OrdersCustom> findOrdersAndUser();
}
