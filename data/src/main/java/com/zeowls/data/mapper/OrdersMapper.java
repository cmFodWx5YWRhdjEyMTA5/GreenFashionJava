package com.zeowls.data.mapper;


import com.zeowls.data.entity.Order_data;
import com.zeowls.data.entity.Orders_data;
import com.zeowls.domain.entity.Order;
import com.zeowls.domain.entity.Orders;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class OrdersMapper extends Mapper<Orders, Orders_data> {

    private Mapper<Order, Order_data> orderMapper;

    @Inject
    public OrdersMapper(Mapper<Order, Order_data> orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Orders map(Orders_data data) {
        if (data == null) return null;
        Orders entity = new Orders();
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setOrders(orderMapper.map(data.getOrders()));
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public Orders_data reverse(Orders data) {
        return null;
    }
}
