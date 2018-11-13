package com.zeowls.data.mapper;

import com.zeowls.data.entity.OrderDetails_data;
import com.zeowls.data.entity.Order_data;
import com.zeowls.domain.entity.Order;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class OrderDetailsMapper extends Mapper<OrderDetails, OrderDetails_data> {
    private Mapper<Order, Order_data> orderMapper;

    @Inject
    public OrderDetailsMapper(Mapper<Order, Order_data> orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDetails map(OrderDetails_data data) {
        if (data == null) return null;
        OrderDetails entity = new OrderDetails();
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setOrders(orderMapper.map(data.getOrders()));
        return entity;
    }

    @Override
    public OrderDetails_data reverse(OrderDetails orderDetails) {
        return null;
    }
}
