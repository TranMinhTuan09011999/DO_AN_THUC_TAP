package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.message.request.OrderRequest;

public interface OrderDetailService {
    public void save(OrderDetailRequest orderDetailRequest);
}
