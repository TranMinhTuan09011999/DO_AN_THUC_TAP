package com.minhtuan.commercemanager.dto;

import com.minhtuan.commercemanager.message.response.OrderResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
    private Integer orderDetailId;
    private Double price;
    private Integer quantity;
    private Integer discount;
    private ProductDetailDTO productDetails;
    private OrderResponse orders;
}
