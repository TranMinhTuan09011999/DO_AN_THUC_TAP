package com.minhtuan.commercemanager.dto;

import lombok.*;

import java.util.Date;

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
    private Double price;
    private String description;
    private Integer colorId;
    private Integer sizeId;
    private String colorName;
    private String size;
    private String colorCode;
    private Integer percent;
    private Integer status;
    private Integer quantity;
    private String room;
    private String categoryName;

    public ProductConditionDTO(Object[] objects){
        this.productId = objects[0] != null ? String.valueOf(objects[0]) : null;
        this.productName = objects[1] != null ? String.valueOf(objects[1]) : null;
        this.productDetailId = objects[2] != null ? Integer.valueOf(objects[2].toString()) : null;
        this.price = objects[3] != null ? Double.valueOf(objects[3].toString()) : null;
        this.description = objects[4] != null ? String.valueOf(objects[4]) : null;
        this.sizeId = objects[5] != null ? Integer.valueOf(objects[5].toString()) : null;
        this.colorId = objects[6] != null ? Integer.valueOf(objects[6].toString()) : null;
        this.size = objects[7] != null ? String.valueOf(objects[7]) : null;
        this.colorName = objects[8] != null ? String.valueOf(objects[8]) : null;
        this.colorCode = objects[9] != null ? String.valueOf(objects[9]) : null;
        this.percent = objects[10] != null ? Integer.valueOf(objects[10].toString()) : null;
        this.status = objects[11] != null ? Integer.valueOf(objects[11].toString()) : null;
        this.quantity = objects[12] != null ? Integer.valueOf(objects[12].toString()) : null;
        this.room = objects[13] != null ? String.valueOf(objects[13]) : null;
        this.categoryName = objects[14] != null ? String.valueOf(objects[14]) : null;
    }
}
