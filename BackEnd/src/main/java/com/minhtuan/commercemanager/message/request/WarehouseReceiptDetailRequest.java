package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseReceiptDetailRequest {
    private String warehouseReceiptId;
    private Integer productDetailId;
    private Integer quantity;
    private Double price;
}
