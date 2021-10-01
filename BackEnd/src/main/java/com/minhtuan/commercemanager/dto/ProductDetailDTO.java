package com.minhtuan.commercemanager.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private static final long serialVersionUID = 1L;

    private Integer productDetailId;
    private Integer quantity;
    private Double price;
    private String image;
    private Integer discount;
    private ProductDTO product;
    private ColorDTO color;
    private SizeDTO size;
}
