package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductByRoomDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private Integer productDetailId;
    private Double price;
    private String image;
    private Integer percent;

    public ProductByRoomDTO(Object[] objects) {
        this.productId = objects[0] != null ? String.valueOf(objects[0]) : null;
        this.productName = objects[1] != null ? String.valueOf(objects[1]) : null;
        this.productDetailId = objects[2] != null ? Integer.valueOf(objects[2].toString()) : null;
        this.price = objects[3] != null ? Double.valueOf(objects[3].toString()) : null;
        this.image = objects[4] != null ? String.valueOf(objects[4]) : null;
        this.percent = objects[5] != null ? Integer.valueOf(objects[5].toString()) : null;
    }
}
