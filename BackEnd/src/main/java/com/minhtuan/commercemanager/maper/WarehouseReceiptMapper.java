package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import org.springframework.stereotype.Component;

@Component
public class WarehouseReceiptMapper {
    public WarehouseReceiptResponse toWarehouseReceiptResponse(WarehouseReceipt warehouseReceipt){
        WarehouseReceiptResponse warehouseReceiptResponse = new WarehouseReceiptResponse();
        warehouseReceiptResponse.setWarehouseReceiptId(warehouseReceipt.getWarehouseReceiptId());
        warehouseReceiptResponse.setDateOfIssue(warehouseReceipt.getDateOfIssue());
        warehouseReceiptResponse.setAmount(warehouseReceipt.getAmount());
        warehouseReceiptResponse.setFirstname(warehouseReceipt.getEmployee().getFirstname());
        warehouseReceiptResponse.setLastname(warehouseReceipt.getEmployee().getLastname());
        return warehouseReceiptResponse;
    }
}
