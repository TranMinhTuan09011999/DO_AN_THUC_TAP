package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;

import java.util.List;

public interface WarehouseReceiptService {
    public String save(WarehouseReceiptRequest warehouseReceiptRequest);
    public List<WarehouseReceiptResponse> getAll();
}
