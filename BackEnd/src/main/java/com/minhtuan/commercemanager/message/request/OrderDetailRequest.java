package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    private String orderId;
    private Integer productDetailId;
    private Double price;
    private Integer quantity;
}
