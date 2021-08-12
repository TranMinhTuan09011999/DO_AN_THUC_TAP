package com.minhtuan.commercemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductConditionDTO {
    private static final long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private Integer productDetailId;
    private String image;
    private Double price;
    private String description;
    private Integer colorId;
    private Integer sizeId;
    private String colorName;
    private String size;
    private String colorCode;

    public ProductConditionDTO(Object[] objects){
        this.productId = objects[0] != null ? String.valueOf(objects[0]) : null;
        this.productName = objects[1] != null ? String.valueOf(objects[1]) : null;
        this.productDetailId = objects[2] != null ? Integer.valueOf(objects[2].toString()) : null;
        this.image = objects[3] != null ? String.valueOf(objects[3]) : null;
        this.price = objects[4] != null ? Double.valueOf(objects[4].toString()) : null;
        this.description = objects[5] != null ? String.valueOf(objects[5]) : null;
        this.sizeId = objects[6] != null ? Integer.valueOf(objects[6].toString()) : null;
        this.colorId = objects[7] != null ? Integer.valueOf(objects[7].toString()) : null;
        this.size = objects[8] != null ? String.valueOf(objects[8]) : null;
        this.colorName = objects[9] != null ? String.valueOf(objects[9]) : null;
        this.colorCode = objects[10] != null ? String.valueOf(objects[10]) : null;
    }
}
