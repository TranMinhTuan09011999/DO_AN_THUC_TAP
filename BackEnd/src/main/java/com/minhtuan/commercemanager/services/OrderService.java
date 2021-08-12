package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;

import java.util.List;

public interface OrderService {
    public String save(OrderRequest orderRequest);
    public List<OrderResponse> getAllOrder();
}
