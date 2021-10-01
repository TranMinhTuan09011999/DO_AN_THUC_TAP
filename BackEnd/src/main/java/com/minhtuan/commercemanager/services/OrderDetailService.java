package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.message.request.OrderRequest;

import java.util.List;

public interface OrderDetailService {
    public void save(OrderDetailRequest orderDetailRequest);
    public List<OrderDetailDTO> getOrderDetailsByOrderId(String orderId);
}
