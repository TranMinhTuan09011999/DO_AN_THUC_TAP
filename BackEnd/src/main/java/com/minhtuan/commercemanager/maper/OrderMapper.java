package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.Room;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setDateOfOrder(order.getDateOfOrder());
        orderResponse.setFirstnameOfReveiver(order.getFirstnameOfReveiver());
        orderResponse.setLastnameOfReveiver(order.getLastnameOfReveiver());
        orderResponse.setAddressOfReceiver(order.getAddressOfReceiver());
        orderResponse.setPhoneOfReceiver(order.getPhoneOfReceiver());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }
}
