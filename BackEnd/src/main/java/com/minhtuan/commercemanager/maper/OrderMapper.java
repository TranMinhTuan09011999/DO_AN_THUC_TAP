package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    public OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setDateOfOrder(order.getDateOfOrder());
        orderResponse.setFirstnameOfReveiver(order.getFirstnameOfReveiver());
        orderResponse.setLastnameOfReveiver(order.getLastnameOfReveiver());
        orderResponse.setAddressOfReceiver(order.getAddressOfReceiver());
        orderResponse.setPhoneOfReceiver(order.getPhoneOfReceiver());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPayment(order.getPayment());
        return orderResponse;
    }

    public OrderDTO toDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setDateOfOrder(order.getDateOfOrder());
        orderDTO.setFirstnameOfReveiver(order.getFirstnameOfReveiver());
        orderDTO.setLastnameOfReveiver(order.getLastnameOfReveiver());
        orderDTO.setAddressOfReceiver(order.getAddressOfReceiver());
        orderDTO.setPhoneOfReceiver(order.getPhoneOfReceiver());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setEmployee(employeeMapper.toDTO(order.getEmployee()));
        orderDTO.setEmployeeDelivery(employeeMapper.toDTO(order.getDeliveryEmployee()));
        orderDTO.setPayment(order.getPayment());
        return orderDTO;
    }
}
