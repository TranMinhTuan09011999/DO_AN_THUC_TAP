package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;

import java.text.ParseException;
import java.util.List;

public interface WarehouseReceiptService {
    public String save(WarehouseReceiptRequest warehouseReceiptRequest);
    public List<WarehouseReceiptResponse> getAll();
    public List<WarehouseReceiptResponse> getAllWarehouseReceiptBySearch(String warehouseReceiptId, String employeeId, String fromDate, String toDate) throws ParseException;
}
