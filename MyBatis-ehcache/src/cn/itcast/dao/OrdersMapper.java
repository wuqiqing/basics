package cn.itcast.dao;

import cn.itcast.domain.Orders;

import java.util.List;

public interface OrdersMapper {

    public List<Orders> findOrdersLazyLoading();

}
