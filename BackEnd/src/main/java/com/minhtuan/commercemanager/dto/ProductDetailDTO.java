package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetailDTO {
    private static final long serialVersionUID = 1L;

    private Integer productDetailId;
    private Integer quantity;
    private Double price;
    private String image;
    private ProductDTO product;
    private ColorDTO color;
    private SizeDTO size;
}
