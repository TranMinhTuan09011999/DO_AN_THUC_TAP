package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.message.request.WarehouseReceiptDetailRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptDetailResponse;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;

import java.util.List;

public interface WarehouseReceiptDetailService {
    public void save(WarehouseReceiptDetailRequest warehouseReceiptDetailRequest);
    public List<WarehouseReceiptDetailResponse> getAll(String warehouseReceiptId);
}
