package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    public OrderDetailDTO toDTO(OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setOrderDetailId(orderDetail.getOrderDetailId());
        orderDetailDTO.setOrders(orderMapper.toOrderResponse(orderDetail.getOrder()));
        orderDetailDTO.setProductDetails(productDetailMapper.toDTO(orderDetail.getProductDetail()));
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setDiscount(orderDetail.getDiscount());
        return orderDetailDTO;
    }
}
