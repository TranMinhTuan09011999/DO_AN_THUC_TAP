package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.message.response.WarehouseReceiptDetailResponse;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import com.minhtuan.commercemanager.model.WarehouseReceiptDetail;
import org.springframework.stereotype.Component;

@Component
public class WarehouseReceiptDetailMapper {
    public WarehouseReceiptDetailResponse toWarehouseReceiptDetailResponse(WarehouseReceiptDetail warehouseReceiptDetail){
        WarehouseReceiptDetailResponse warehouseReceiptDetailResponse = new WarehouseReceiptDetailResponse();
        warehouseReceiptDetailResponse.setProductDetailId(warehouseReceiptDetail.getProductDetail().getProductDetailId());
        warehouseReceiptDetailResponse.setProductName(warehouseReceiptDetail.getProductDetail().getProduct().getProductName());
        warehouseReceiptDetailResponse.setSize(warehouseReceiptDetail.getProductDetail().getSize().getSize());
        warehouseReceiptDetailResponse.setColor(warehouseReceiptDetail.getProductDetail().getColor().getColorName());
        warehouseReceiptDetailResponse.setQuantity(warehouseReceiptDetail.getQuantity());
        warehouseReceiptDetailResponse.setPrice(warehouseReceiptDetail.getPrice());
        return warehouseReceiptDetailResponse;
    }
}
