package com.zeowls.data.mapper;


import com.zeowls.data.entity.Address_data;
import com.zeowls.data.entity.Cart_data;
import com.zeowls.data.entity.CurrentState_data;
import com.zeowls.data.entity.Order_data;
import com.zeowls.data.entity.Phone_data;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.Cart;
import com.zeowls.domain.entity.CurrentState;
import com.zeowls.domain.entity.Order;
import com.zeowls.domain.entity.Phone;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class OrderMapper extends Mapper<Order, Order_data> {

    private Mapper<CurrentState, CurrentState_data> stateMapper;
    private Mapper<Cart, Cart_data> cartMapper;
    private Mapper<Phone, Phone_data> phoneMapper;
    private Mapper<Address, Address_data> addressMapper;

    @Inject
    OrderMapper(Mapper<CurrentState, CurrentState_data> stateMapper, Mapper<Cart, Cart_data> cartMapper, Mapper<Phone, Phone_data> phoneMapper, Mapper<Address, Address_data> addressMapper) {
        this.stateMapper = stateMapper;
        this.cartMapper = cartMapper;
        this.phoneMapper = phoneMapper;
        this.addressMapper = addressMapper;
    }


    @Override
    public Order map(Order_data data) {
        if (data == null) return null;
        Order entity = new Order();
        entity.setCurrentState(stateMapper.map(data.getCurrentState()));
        entity.setAddress(addressMapper.map(data.getAddress()));
        entity.setPhone(phoneMapper.map(data.getPhone()));
        entity.setCart(cartMapper.map(data.getCart()));
        entity.setActive(data.getActive());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setShippingDate(data.getShippingDate());
        entity.setIdUser(data.getIdUser());
        entity.setShippingFee(data.getShippingFee());
        return entity;
    }

    @Override
    public Order_data reverse(Order homePage_) {
        return null;
    }
}
