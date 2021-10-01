package com.minhtuan.commercemanager.message.response;

import com.minhtuan.commercemanager.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseReceiptDetailResponse {
    private Integer productDetailId;
    private String productName;
    private String size;
    private String color;
    private Integer quantity;
    private Double price;
}
